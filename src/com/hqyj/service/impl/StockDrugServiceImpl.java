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
		if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//����ҩ�����ڿ���в�����ʱ��
			wareHouseDao.add(s);
		}else{
			WareHouse w = new WareHouse();
			w.setStock_number(wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number()+s.getBuy_num());
			w.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
			wareHouseDao.updateAdd(w);//�޸Ŀ���������
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
		String drug_id=stockDrugDao.queryById(s.getId()).getDrug_id();//��ȡҪ�޸ĵĽ�ҩ����
		int oldNum = stockDrugDao.queryById(s.getId()).getBuy_num();//��ȡ�޸�ǰ�Ľ�ҩ����
		int newNum=s.getBuy_num();//��ȡ���޸ĵ�ҩƷ����
		int id = wareHouseDao.queryByDrugId(drug_id).getId();//��ȡҪ�޸ĵĿ����������ݵ�id
		try {
			stockDrugDao.edit(s);
			if(s.getDrug_id().equals(drug_id)&&s.getBuy_num()==oldNum){//����ҩ����,��ҩ���������ı�
				return 1;
			}
			if(s.getDrug_id().equals(drug_id)&&s.getBuy_num()!=oldNum){//����ҩ���벻��ʱ,ֻ�޸Ľ�ҩ����
				int stock_number=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
				WareHouse w = new WareHouse();
				w.setId(id);
				int num=stock_number-oldNum+newNum;
				w.setStock_number(num);//���µ�ҩƷ������װ��WareHouse������
				wareHouseDao.updateRecovery(w);//�޸Ŀ���е�����
				return 1;//�޸ĳɹ�
			}
			if(!s.getDrug_id().equals(drug_id)&&s.getBuy_num()==oldNum){//����ҩ����ı�ʱ,��ҩ����û�иı�
				int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
				WareHouse w = new WareHouse();
				w.setId(id);
				w.setStock_number(stock_number-oldNum);//���µ�ҩƷ������װ��WareHouse������
				wareHouseDao.updateRecovery(w);
				if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//���޸ĵĽ�ҩ�����ڿ���в�����ʱ��
					wareHouseDao.add(s);//�ڿ��������µ�����
				}else{
					int stock_number1=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//��ȡ���޸ĵ�ҩƷ����ڿ������ӦҩƷ������
					WareHouse w1 = new WareHouse();
					w1.setStock_number(newNum+stock_number1);
					w1.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					System.out.println(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					wareHouseDao.updateAdd(w1);//�޸Ŀ���������
				}
				return 1;//�޸ĳɹ�
			}
			if(!s.getDrug_id().equals(drug_id)&&s.getBuy_num()!=oldNum){//����ҩ����,��ҩ�������ı�
				int stock_number=wareHouseDao.queryByDrugId(drug_id).getStock_number();//��ȡ�޸�֮ǰ�������ӦҩƷ������
				WareHouse w = new WareHouse();
				w.setId(id);
				w.setStock_number(stock_number-oldNum);//���µ�ҩƷ������װ��WareHouse������
				wareHouseDao.updateRecovery(w);
				if(wareHouseDao.queryByDrugId(s.getDrug_id())==null){//���޸ĵĽ�ҩ�����ڿ���в�����ʱ��
					wareHouseDao.add(s);//�ڿ��������µ�����
				}else{
					int stock_number1=wareHouseDao.queryByDrugId(s.getDrug_id()).getStock_number();//��ȡ���޸ĵ�ҩƷ����ڿ������ӦҩƷ������
					WareHouse w1 = new WareHouse();
					w1.setStock_number(newNum+stock_number1);
					w1.setId(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					System.out.println(wareHouseDao.queryByDrugId(s.getDrug_id()).getId());
					wareHouseDao.updateAdd(w1);//�޸Ŀ���������
				}
				return 1;//�޸ĳɹ�
			}
			return 0;//�޸�ʧ��
		} catch (Exception e) {
			// TODO: handle exception
			return 0;//�޸�ʧ��
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
	


