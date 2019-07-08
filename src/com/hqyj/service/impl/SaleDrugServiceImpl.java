package com.hqyj.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.bean.SaleDrug;
import com.hqyj.bean.WareHouse;
import com.hqyj.dao.SaleDrugDao;
import com.hqyj.dao.WareHouseDao;
import com.hqyj.service.SaleDrugService;
@Service
@Transactional
public class SaleDrugServiceImpl implements SaleDrugService {
	@Resource
	public SaleDrugDao saleDrugDao;
	@Resource
	private WareHouseDao wareHouseDao;
	@Override
	public ArrayList<SaleDrug> queryAll() {
		// TODO Auto-generated method stub
		return saleDrugDao.queryAll();
	}

	@Override
	public int add(SaleDrug s) {
		// TODO Auto-generated method stub
		try {
			if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){
				return -1;//所售药品在库存中不存在
			}else{
				WareHouse wareHouse=wareHouseDao.queryByDrugId(s.getDrug_id());//通过药品编号去库存中查询药品记录
				if(wareHouse.getStock_number()>=s.getSale_number()){//所售药品大于库存中药品的数量
					saleDrugDao.add(s);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//增加成功
				}
				return 0;//所售药品大于库存中药品的数量
			}
		} catch (Exception e) {
			// TODO: handle exception
			return -2;//所售药品增加失败，系统错误
		}
	}
	@Override
	public SaleDrug queryById(int id) {
		// TODO Auto-generated method stub
		return saleDrugDao.queryById(id);
	}

	@Override
	public int edit(SaleDrug s) {
		System.out.println(s.getPrice());
		// TODO Auto-generated method stub
		String drug_id=saleDrugDao.queryById(s.getId()).getDrug_id();//获取要修改的售药编码
		int oldSale_number = saleDrugDao.queryById(s.getId()).getSale_number();//获取修改前的售药数量
		int newSale_number=s.getSale_number();//获取新修改的药品数量
		int id = wareHouseDao.queryByDrugId(drug_id).getId();//获取要修改的库存中相关数据的id
		try {
			if(s.getDrug_id().equals(drug_id)&&s.getSale_number()==oldSale_number){//当售药编码和售药数量都不改变时
				saleDrugDao.edit(s);
				return 1;
			}
			if(s.getDrug_id().equals(drug_id)&&s.getSale_number()!=oldSale_number&&wareHouseDao.queryByDrugId(drug_id).getStock_number()+oldSale_number>=s.getSale_number()){//当售药编码不变时,只修改售药数量,并且所售药品大于库存中药品的数量
				saleDrugDao.edit(s);
				int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//获取修改之前库存中相应药品的数量
				WareHouse w = new WareHouse();
				w.setId(id);
				int num=stock_number+oldSale_number-newSale_number;
				w.setStock_number(num);//把新的药品数量封装到WareHouse对象中
				wareHouseDao.updateRecovery(w);//修改库存中的数据
				return 1;//修改成功
			}
			if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){
				return -1;//所售药品在库存中不存在
			}else{
				WareHouse wareHouse=wareHouseDao.queryByDrugId(s.getDrug_id());//通过药品编号去库存中查询药品记录
				if(!s.getDrug_id().equals(drug_id)&&s.getSale_number()==oldSale_number&&wareHouse.getStock_number()+oldSale_number>=s.getSale_number()){//当售药编码改变时,售药数量没有改变,并且所售药品大于库存中药品的数量
					saleDrugDao.edit(s);
					int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//获取修改之前库存中相应药品的数量
					WareHouse w = new WareHouse();
					w.setId(id);
					w.setStock_number(stock_number+oldSale_number);//把新的药品数量封装到WareHouse对象中
					wareHouseDao.updateRecovery(w);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//增加成功
				}
				if(!s.getDrug_id().equals(drug_id)&&s.getSale_number()!=oldSale_number&&wareHouse.getStock_number()>=s.getSale_number()){//当售药编码和售药数量都改变时,并且所售药品大于库存中药品的数量
					saleDrugDao.edit(s);
					int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//获取修改之前库存中相应药品的数量
					WareHouse w = new WareHouse();
					w.setId(id);
					w.setStock_number(stock_number+oldSale_number);//把新的药品数量封装到WareHouse对象中
					wareHouseDao.updateRecovery(w);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//增加成功
				}
			}
			return 0;//所售药品大于库存中药品的数量	
		} catch (Exception e) {
			// TODO: handle exception
			return -2;//所售药品增加失败，系统错误
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			SaleDrug s = saleDrugDao.queryById(id);//获取删除售药之前的一条记录
			int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//获取之前的库存里的药品数量
			int id1 = wareHouseDao.queryByDrugId(s.getDrug_id()).getId();//获取之前的库存里的药品id
			WareHouse w = new WareHouse();
			w.setId(id1);
			w.setStock_number(s.getSale_number()+stock_number);
			saleDrugDao.delete(id);
			wareHouseDao.updateRecovery(w);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public ArrayList<SaleDrug> querySaleDrugByCondition(SaleDrug s) {
		// TODO Auto-generated method stub
		ArrayList<SaleDrug> list = saleDrugDao.querySaleDrugByCondition(s);
		return list;
	}
	
	public WareHouseDao getWareHouseDao() {
		return wareHouseDao;
	}

	public void setWareHouseDao(WareHouseDao wareHouseDao) {
		this.wareHouseDao = wareHouseDao;
	}

	public SaleDrugDao getSaleDrugDao() {
		return saleDrugDao;
	}

	public void setSaleDrugDao(SaleDrugDao saleDrugDao) {
		this.saleDrugDao = saleDrugDao;
	}
}
