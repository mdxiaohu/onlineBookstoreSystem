package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TUser;

@Repository("userDao")
public class UserDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 用户登录
	public TUser Login(TUser user) {
		return sqlSessionTemplate.selectOne("UserMapper.Login", user);
	}
	// 用户签到
	public int QianDao(TUser user) {
		return sqlSessionTemplate.update("UserMapper.QianDao", user);
	}

	// 根据ID查询
	public TUser findById(int userid) {
		return sqlSessionTemplate.selectOne("UserMapper.findById", userid);
	}

	public int Update(TUser user) {

		return sqlSessionTemplate.update("UserMapper.update", user);
	}

	public void updateUserDel(int userId) {

		sqlSessionTemplate.update("UserMapper.updateUserDel", userId);
	}

	public void Save(TUser user) {
		sqlSessionTemplate.insert("UserMapper.save", user);
	}

	public TUser findUserByUserName(String username) {

		return sqlSessionTemplate.selectOne("UserMapper.findUserByUserName",
				username);

	}

	public List<TUser> findAll() {

		return sqlSessionTemplate.selectList("UserMapper.findAll");

	}

}
