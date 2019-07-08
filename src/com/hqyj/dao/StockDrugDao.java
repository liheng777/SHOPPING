package com.hqyj.dao;

import java.util.ArrayList;

import com.hqyj.bean.StockDrug;

public interface StockDrugDao {
	public ArrayList<StockDrug> queryAll();
	public StockDrug queryById(int id);
	public void add(StockDrug s);
	public void edit(StockDrug s);
	public void delete(int id);
	public ArrayList<StockDrug> queryStockDrugByCondition(StockDrug s);
}
