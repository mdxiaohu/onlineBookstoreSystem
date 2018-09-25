package com.cn.service;

import java.util.List;

import javax.annotation.Resource;

import com.cn.dao.UserDao;
import com.cn.entity.TUser;

public interface UserService {

	public TUser Login(TUser user);

	public TUser findById(int userid);

	public List<TUser> findAll();

	public int Update(TUser user);

	public void save(TUser user);

	public TUser findUserByUserName(String username);

	public void updateUserDel(int userId);
	
	public int qiandao(TUser user);
}
