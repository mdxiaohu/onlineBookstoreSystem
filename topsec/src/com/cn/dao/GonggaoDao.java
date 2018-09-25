package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TGonggao;
import com.cn.entity.TOrder;
import com.cn.entity.TUser;

@Repository("gonggaoDao")
public class GonggaoDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<TGonggao> findAll() {
		return sqlSessionTemplate.selectList("GonggaoMapper.findAll");
	}

	public void save(TGonggao gonggao) {
		sqlSessionTemplate.insert("GonggaoMapper.save", gonggao);
	}

	public TGonggao findById(int gonggaoid) {
		return sqlSessionTemplate
				.selectOne("GonggaoMapper.findById", gonggaoid);
	}

	public void delete(int gonggaoid) {
		sqlSessionTemplate.delete("GonggaoMapper.deletebyid", gonggaoid);
	}

}
