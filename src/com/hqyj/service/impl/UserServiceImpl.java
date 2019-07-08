package com.hqyj.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.bean.User;
import com.hqyj.dao.UserDao;
import com.hqyj.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	
	@Override
	public boolean add(User u) {
		// TODO Auto-generated method stub
		try {
			userDao.add(u);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	@Override
	public ArrayList<User> findAll() {
		// TODO Auto-generated method stub
		
		return userDao.querydAll();
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}
	@Override
	public boolean edit(User u) {
		// TODO Auto-generated method stub
		try {
			userDao.edit(u);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}
	@Override
	public ArrayList<User> queryUserByCondition(User u) {
		// TODO Auto-generated method stub
		return userDao.queryUserByCondition(u);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
