package com.hqyj.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hqyj.bean.StockDrug;
import com.hqyj.bean.WareHouse;

public interface WareHouseDao {
	public ArrayList<WareHouse> queryAll();
	public WareHouse queryByDrugId(String drug_id);
	public void add(StockDrug s);
	public void updateAdd(WareHouse w);
	public void updateRecovery(WareHouse w);
	public void edit(WareHouse w);
	public WareHouse queryById(int id);
//	public List<WareHouse> queryWareHouseByCondition(WareHouse w);
	public ArrayList<WareHouse> queryWareHouseByCondition(@Param("drug_id")String drug_id,@Param("drug_name")String drug_name,@Param("index")int index);
	public void updateSub(WareHouse w);
	public ArrayList<WareHouse> showlist(int index);
	public int queryPagecount(@Param("drug_id")String drug_id,@Param("drug_name")String drug_name);
	public int pagecount();
}
