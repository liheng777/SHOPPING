package com.hqyj.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.bean.StockDrug;
import com.hqyj.bean.WareHouse;
import com.hqyj.dao.StockDrugDao;
import com.hqyj.dao.WareHouseDao;
import com.hqyj.service.StockDrugService;
@Service
@Transactional
public class StockDrugServiceImpl implements StockDrugService{
	@Resource
	private StockDrugDao stockDrugDao;
	@Resource
	private WareHouseDao wareHouseDao;
	
	@Override
	public ArrayList<StockDrug> queryAll() {
		// TODO Auto-generated method stub
		return stockDrugDao.queryAll();
	}

	@Override
	public boolean add(StockDrug s) {
		// TODO Auto-generated method stub
		System.out.println(s.getDrug_id());
		if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//当进药编码在库存中不存在时；
			wareHouseDao.add(s);
		}else{
			WareHouse w = new WareHouse();
			w.setStock_number(wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number()+s.getBuy_num());
			w.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
			wareHouseDao.updateAdd(w);//修改库存里的数量
		}
		try {
			stockDrugDao.add(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public StockDrug queryById(int id) {
		// TODO Auto-generated method stub
		StockDrug stockDrug = stockDrugDao.queryById(id);
		return stockDrug;
	}

	@Override
	public int edit(StockDrug s) {
		// TODO Auto-generated method stub
		String drug_id=stockDrugDao.queryById(s.getId()).getDrug_id();//获取要修改的进药编码
		int oldNum = stockDrugDao.queryById(s.getId()).getBuy_num();//获取修改前的进药数量
		int newNum=s.getBuy_num();//获取新修改的药品数量
		int id = wareHouseDao.queryByDrugId(drug_id).getId();//获取要修改的库存中相关数据的id
		try {
			stockDrugDao.edit(s);
			if(s.getDrug_id().equals(drug_id)&&s.getBuy_num()==oldNum){//当进药编码,进药数量都不改变
				return 1;
			}
			if(s.getDrug_id().equals(drug_id)&&s.getBuy_num()!=oldNum){//当进药编码不变时,只修改进药数量
				int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//获取修改之前库存中相应药品的数量
				WareHouse w = new WareHouse();
				w.setId(id);
				int num=stock_number-oldNum+newNum;
				w.setStock_number(num);//把新的药品数量封装到WareHouse对象中
				wareHouseDao.updateRecovery(w);//修改库存中的数据
				return 1;//修改成功
			}
			if(!s.getDrug_id().equals(drug_id)&&s.getBuy_num()==oldNum){//当进药编码改变时,进药数量没有改变
				int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//获取修改之前库存中相应药品的数量
				WareHouse w = new WareHouse();
				w.setId(id);
				w.setStock_number(stock_number-oldNum);//把新的药品数量封装到WareHouse对象中
				wareHouseDao.updateRecovery(w);
				if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//当修改的进药编码在库存中不存在时；
					wareHouseDao.add(s);//在库存中添加新的数据
				}else{
					int stock_number1=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//获取新修改的药品编号在库存中相应药品的数量
					WareHouse w1 = new WareHouse();
					w1.setStock_number(newNum+stock_number1);
					w1.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					System.out.println(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					wareHouseDao.updateAdd(w1);//修改库存里的数量
				}
				return 1;//修改成功
			}
			if(!s.getDrug_id().equals(drug_id)&&s.getBuy_num()!=oldNum){//当进药编码,进药数量都改变
				int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//获取修改之前库存中相应药品的数量
				WareHouse w = new WareHouse();
				w.setId(id);
				w.setStock_number(stock_number-oldNum);//把新的药品数量封装到WareHouse对象中
				wareHouseDao.updateRecovery(w);
				if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//当修改的进药编码在库存中不存在时；
					wareHouseDao.add(s);//在库存中添加新的数据
				}else{
					int stock_number1=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//获取新修改的药品编号在库存中相应药品的数量
					WareHouse w1 = new WareHouse();
					w1.setStock_number(newNum+stock_number1);
					w1.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					System.out.println(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					wareHouseDao.updateAdd(w1);//修改库存里的数量
				}
				return 1;//修改成功
			}
			return 0;//修改失败
		} catch (Exception e) {
			// TODO: handle exception
			return 0;//修改失败
		}
	}

	@Override
	public ArrayList<StockDrug> queryStockDrugByCondition(StockDrug s) {
		// TODO Auto-generated method stub
		ArrayList<StockDrug> stockDrug = stockDrugDao.queryStockDrugByCondition(s);
		return stockDrug;
	}
	
	public WareHouseDao getWareHouseDao() {
		return wareHouseDao;
	}
	public void setWareHouseDao(WareHouseDao wareHouseDao) {
		this.wareHouseDao = wareHouseDao;
	}
	public StockDrugDao getStockDrugDao() {
		return stockDrugDao;
	}
	public void setStockDrugDao(StockDrugDao stockDrugDao) {
		this.stockDrugDao = stockDrugDao;
	}
}
	


