package com.hqyj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.bean.User;
import com.hqyj.dao.UserDao;
import com.hqyj.service.LoginService;
@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Resource
	private UserDao userDao;
	@Override
	public int login(User user) {
		User u = userDao.queryByCondition(user);
		if(u!=null){
			if(user.getPassword().equals(u.getPassword())&&user.getUsertype().equals(u.getUsertype())){
				return 1;//登陆成功
			}else{
				return 0;//密码错误，登录失败
			}
			
		}else{
			return -1;//用户名不存在
		}
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
