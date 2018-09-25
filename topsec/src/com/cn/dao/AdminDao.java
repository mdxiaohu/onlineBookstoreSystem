package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TAdmin;
import com.cn.entity.TUser;

@Repository("adminDao")
public class AdminDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 用户登录
	public TAdmin adminLogin(TAdmin admin) {
		return sqlSessionTemplate.selectOne("AdminMapper.adminLogin", admin);
	}

	public String UpdateAdmin(TAdmin admin) {
		sqlSessionTemplate.update("AdminMapper.adminUpdate", admin);
		return null;
	}

	public void save(TAdmin admin) {
		sqlSessionTemplate.insert("AdminMapper.save", admin);

	}

	public List<TAdmin> findAll() {
		return sqlSessionTemplate.selectList("AdminMapper.findAll");
	}

	public void delete(int userid) {
		sqlSessionTemplate.delete("AdminMapper.save", userid);

	}

}
