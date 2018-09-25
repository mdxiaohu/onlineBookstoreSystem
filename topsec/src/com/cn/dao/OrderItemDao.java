package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TOrderItem;
import com.cn.entity.TUser;

@Repository("orderitemDao")
public class OrderItemDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 用户登录
	public List<TOrderItem> find() {
		return sqlSessionTemplate.selectList("OrderItemMapper.find");
	}

	public void save(TOrderItem orderitem) {
		sqlSessionTemplate.insert("OrderItemMapper.save", orderitem);
	}

	public List<TOrderItem> findByOrderId(int orderId) {
		return sqlSessionTemplate.selectList("OrderItemMapper.findByOrderId",
				orderId);
	}

	public void deletebyorderId(int orderId) {

		sqlSessionTemplate.delete("OrderItemMapper.deletebyorderId", orderId);
	}
}
