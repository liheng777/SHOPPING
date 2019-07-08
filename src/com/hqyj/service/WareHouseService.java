package com.hqyj.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hqyj.bean.WareHouse;

public interface WareHouseService {
	public ArrayList<WareHouse> queryAll();
	public boolean edit(WareHouse w);
	public WareHouse queryById(int id);
//	public List<WareHouse> queryWareHouseByCondition(WareHouse w);
	public ArrayList<WareHouse> queryWareHouseByCondition(@Param("drug_id")String drug_id,@Param("drug_name")String drug_name,@Param("index")int index);
	public ArrayList<WareHouse> showlist(int index);
	public int pagecount(); 
	public int queryPagecount(@Param("drug_id")String drug_id,@Param("drug_name")String drug_name);
}
