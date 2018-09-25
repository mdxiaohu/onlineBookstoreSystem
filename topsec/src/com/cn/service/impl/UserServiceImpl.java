package com.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.UserDao;
import com.cn.entity.TUser;
import com.cn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public TUser Login(TUser user) {
		// TODO Auto-generated method stub
		return userDao.Login(user);
	}

	public TUser findById(int userid) {
		// TODO Auto-generated method stub
		return userDao.findById(userid);
	}

	public int Update(TUser user) {
		// TODO Auto-generated method stub
		return userDao.Update(user);
	}

	public void save(TUser user) {
		// TODO Auto-generated method stub
		 userDao.Save(user);
	}

	public TUser findUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByUserName(username);
	}

	public List<TUser> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public void updateUserDel(int userId) {
		// TODO Auto-generated method stub
		userDao.updateUserDel(userId);
	}

	public int qiandao(TUser user) {
		// TODO Auto-generated method stub
		return userDao.QianDao(user);
	}

}
