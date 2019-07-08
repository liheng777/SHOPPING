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
				return -1;//����ҩƷ�ڿ���в�����
			}else{
				WareHouse wareHouse=wareHouseDao.queryByDrugId(s.getDrug_id());//ͨ��ҩƷ���ȥ����в�ѯҩƷ��¼
				if(wareHouse.getStock_number()>=s.getSale_number()){//����ҩƷ���ڿ����ҩƷ������
					saleDrugDao.add(s);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//���ӳɹ�
				}
				return 0;//����ҩƷ���ڿ����ҩƷ������
			}
		} catch (Exception e) {
			// TODO: handle exception
			return -2;//����ҩƷ����ʧ�ܣ�ϵͳ����
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
		String drug_id=saleDrugDao.queryById(s.getId()).getDrug_id();//��ȡҪ�޸ĵ���ҩ����
		int oldSale_number = saleDrugDao.queryById(s.getId()).getSale_number();//��ȡ�޸�ǰ����ҩ����
		int newSale_number=s.getSale_number();//��ȡ���޸ĵ�ҩƷ����
		int id = wareHouseDao.queryByDrugId(drug_id).getId();//��ȡҪ�޸ĵĿ����������ݵ�id
		try {
			if(s.getDrug_id().equals(drug_id)&&s.getSale_number()==oldSale_number){//����ҩ�������ҩ���������ı�ʱ
				saleDrugDao.edit(s);
				return 1;
			}
			if(s.getDrug_id().equals(drug_id)&&s.getSale_number()!=oldSale_number&&wareHouseDao.queryByDrugId(drug_id).getStock_number()+oldSale_number>=s.getSale_number()){//����ҩ���벻��ʱ,ֻ�޸���ҩ����,��������ҩƷ���ڿ����ҩƷ������
				saleDrugDao.edit(s);
				int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
				WareHouse w = new WareHouse();
				w.setId(id);
				int num=stock_number+oldSale_number-newSale_number;
				w.setStock_number(num);//���µ�ҩƷ������װ��WareHouse������
				wareHouseDao.updateRecovery(w);//�޸Ŀ���е�����
				return 1;//�޸ĳɹ�
			}
			if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){
				return -1;//����ҩƷ�ڿ���в�����
			}else{
				WareHouse wareHouse=wareHouseDao.queryByDrugId(s.getDrug_id());//ͨ��ҩƷ���ȥ����в�ѯҩƷ��¼
				if(!s.getDrug_id().equals(drug_id)&&s.getSale_number()==oldSale_number&&wareHouse.getStock_number()+oldSale_number>=s.getSale_number()){//����ҩ����ı�ʱ,��ҩ����û�иı�,��������ҩƷ���ڿ����ҩƷ������
					saleDrugDao.edit(s);
					int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
					WareHouse w = new WareHouse();
					w.setId(id);
					w.setStock_number(stock_number+oldSale_number);//���µ�ҩƷ������װ��WareHouse������
					wareHouseDao.updateRecovery(w);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//���ӳɹ�
				}
				if(!s.getDrug_id().equals(drug_id)&&s.getSale_number()!=oldSale_number&&wareHouse.getStock_number()>=s.getSale_number()){//����ҩ�������ҩ�������ı�ʱ,��������ҩƷ���ڿ����ҩƷ������
					saleDrugDao.edit(s);
					int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
					WareHouse w = new WareHouse();
					w.setId(id);
					w.setStock_number(stock_number+oldSale_number);//���µ�ҩƷ������װ��WareHouse������
					wareHouseDao.updateRecovery(w);
					wareHouse.setStock_number(wareHouse.getStock_number()-s.getSale_number());
					wareHouseDao.updateSub(wareHouse);
					return 1;//���ӳɹ�
				}
			}
			return 0;//����ҩƷ���ڿ����ҩƷ������	
		} catch (Exception e) {
			// TODO: handle exception
			return -2;//����ҩƷ����ʧ�ܣ�ϵͳ����
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			SaleDrug s = saleDrugDao.queryById(id);//��ȡɾ����ҩ֮ǰ��һ����¼
			int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//��ȡ֮ǰ�Ŀ�����ҩƷ����
			int id1 = wareHouseDao.queryByDrugId(s.getDrug_id()).getId();//��ȡ֮ǰ�Ŀ�����ҩƷid
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
