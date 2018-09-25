package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TAdmin;
import com.cn.entity.TCatelog;
import com.cn.entity.TUser;

@Repository("catelogDao")
public class CateLogDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<TCatelog> find() {
		return sqlSessionTemplate.selectList("CateLogMapper.Find");
	}

	public void save(TCatelog catlog) {
		sqlSessionTemplate.insert("CateLogMapper.Save", catlog);
	}

	public TCatelog findById(int catelogId) {
		return sqlSessionTemplate
				.selectOne("CateLogMapper.findById", catelogId);
	}

	public void update(TCatelog catlog) {
		sqlSessionTemplate.update("CateLogMapper.update", catlog);
	}

}
