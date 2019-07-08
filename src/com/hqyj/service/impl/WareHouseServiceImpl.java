package com.hqyj.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.bean.WareHouse;
import com.hqyj.dao.WareHouseDao;
import com.hqyj.service.WareHouseService;
@Service
@Transactional
public class WareHouseServiceImpl implements WareHouseService {
	@Resource
	private WareHouseDao wareHouseDao;
	
	public WareHouseDao getWareHouseDao() {
		return wareHouseDao;
	}

	public void setWareHouseDao(WareHouseDao wareHouseDao) {
		this.wareHouseDao = wareHouseDao;
	}

	@Override
	public ArrayList<WareHouse> queryAll() {
		// TODO Auto-generated method stub
		return wareHouseDao.queryAll();
	}

	@Override
	public boolean edit(WareHouse w) {
		// TODO Auto-generated method stub
		try {
			wareHouseDao.edit(w);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public WareHouse queryById(int id) {
		// TODO Auto-generated method stub
		return wareHouseDao.queryById(id);
	}

//	@Override
//	public List<WareHouse> queryWareHouseByCondition(WareHouse w) {
//		// TODO Auto-generated method stub
//		return wareHouseDao.queryWareHouseByCondition(w);
//	}

	@Override
	public ArrayList<WareHouse> showlist(int index) {
		// TODO Auto-generated method stub
		return wareHouseDao.showlist(index);
	}

	@Override
	public int pagecount() {
		// TODO Auto-generated method stub
		return wareHouseDao.pagecount();
	}

	@Override
	public ArrayList<WareHouse> queryWareHouseByCondition(String drug_id,
			String drug_name, int index) {
		// TODO Auto-generated method stub
		return wareHouseDao.queryWareHouseByCondition(drug_id, drug_name, index);
	}

	@Override
	public int queryPagecount(String drug_id, String drug_name) {
		// TODO Auto-generated method stub
		return wareHouseDao.queryPagecount(drug_id, drug_name);
	}


	


	
}
