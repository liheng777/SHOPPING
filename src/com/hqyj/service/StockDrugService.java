package com.hqyj.service;

import java.util.ArrayList;

import com.hqyj.bean.StockDrug;

public interface StockDrugService {
	public ArrayList<StockDrug> queryAll();
	public boolean add(StockDrug s);
	public StockDrug queryById(int id);
	public int edit(StockDrug s);
	public ArrayList<StockDrug> queryStockDrugByCondition(StockDrug s);
}
