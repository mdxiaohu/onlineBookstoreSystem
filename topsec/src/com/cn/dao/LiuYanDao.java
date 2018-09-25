package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TLiuyan;
import com.cn.entity.TUser;

@Repository("liuyanDao")
public class LiuYanDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<TLiuyan> findAll() {
		return sqlSessionTemplate.selectList("LiuyanMapper.findAll");
	}

	public void save(TLiuyan liuyan) {
		sqlSessionTemplate.insert("LiuyanMapper.save", liuyan);
	}

	public TLiuyan findById(int liuyanId) {
		return sqlSessionTemplate.selectOne("LiuyanMapper.findById", liuyanId);
	}

	public void deleteById(int liuyanId) {
		sqlSessionTemplate.delete("LiuyanMapper.deleteById", liuyanId);
	}
}
