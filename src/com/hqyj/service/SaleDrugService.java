package com.hqyj.service;

import java.util.ArrayList;

import com.hqyj.bean.SaleDrug;

public interface SaleDrugService {
	public ArrayList<SaleDrug> queryAll();
	public int add(SaleDrug s);
	public SaleDrug queryById(int id);
	public int edit(SaleDrug s);
	public void delete(int id);
	public ArrayList<SaleDrug> querySaleDrugByCondition(SaleDrug s);
}
