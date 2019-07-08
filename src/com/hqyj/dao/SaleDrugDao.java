package com.hqyj.dao;

import java.util.ArrayList;

import com.hqyj.bean.SaleDrug;

public interface SaleDrugDao {
	public ArrayList<SaleDrug> queryAll();
	public void add(SaleDrug s);
	public SaleDrug queryById(int id);
	public void edit(SaleDrug s);
	public void delete(int id);
	public ArrayList<SaleDrug> querySaleDrugByCondition(SaleDrug s);
}
