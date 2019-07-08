package com.hqyj.service;

import java.util.ArrayList;
import java.util.List;

import com.hqyj.bean.User;

public interface UserService {
	public boolean add(User u);
	public ArrayList<User> findAll();
	public User findUserById(int id);
	public boolean edit(User u);
	public void delete(int id);
	public List<User> queryUserByCondition(User u);
}
