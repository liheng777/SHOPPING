package com.hqyj.dao;

import java.util.ArrayList;

import com.hqyj.bean.User;

public interface UserDao {
	public User queryByCondition(User u);
	public void add(User user);
	public ArrayList<User> querydAll();
	public User findUserById(int id);
	public void edit(User u);
	public void delete(int id);
	public ArrayList<User> queryUserByCondition(User u);
}
