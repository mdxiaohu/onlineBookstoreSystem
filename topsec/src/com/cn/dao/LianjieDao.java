package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TUser;
import com.cn.entity.Tlianjie;

@Repository("lianjieDao")
public class LianjieDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<Tlianjie> findAll() {
		return sqlSessionTemplate.selectList("LianjieMapper.findAll");

	}

	public void save(Tlianjie lianjie) {

		sqlSessionTemplate.insert("LianjieMapper.save", lianjie);
	}

	public Tlianjie findById(int lianjieId) {

		return sqlSessionTemplate.selectOne("LianjieMapper.findById", lianjieId);
	}
	
	public void deletebyId(int lianjieId) {

		sqlSessionTemplate.delete("LianjieMapper.deletebyId", lianjieId);
	}
	
}
