package com.cn.dao;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TOrder;
import com.cn.entity.TOrderItem;
import com.cn.entity.TUser;
@Repository("orderDao")
public class OrderDao {
	
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	@Resource(name="sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	

	public List<TOrder> find(int userId){
		
		
		
		return sqlSessionTemplate.selectList("OrderMapper.find", userId);
	}
	
	public List<TOrder> findAll(){
		
		
		
		return sqlSessionTemplate.selectList("OrderMapper.findAll");
	}

	public List<TOrder> findByTime(TOrder order){
		
		
		
		return sqlSessionTemplate.selectList("OrderMapper.findByTime",order);
	}
	
	public void UpdateZhuantai(int orderId){
		 sqlSessionTemplate.update("OrderMapper.UpdateZhuantai",orderId);
	}
	
	
public List<TOrder> findByorderUserId(){
		return sqlSessionTemplate.selectList("OrderMapper.findByorderUserId");
	}
public TOrder findById(int orderid){
		
		
		
		return sqlSessionTemplate.selectOne("OrderMapper.findById", orderid);
	}

public void deleteByOrderId(int orderId){
	
	
	
	 sqlSessionTemplate.delete("OrderMapper.deleteByOrderId", orderId);
}

	

public void deletebyorderId(int  orderId){
	
	
	
	 sqlSessionTemplate.delete("OrderMapper.deletebyorderId", orderId);
}

	public void save(TOrder order){
		
		
		
		 sqlSessionTemplate.insert("OrderMapper.save", order);
	}


	
	
	public TOrder findByBianhao(String  bianhao){
		
		
		
		return sqlSessionTemplate.selectOne("OrderMapper.findByBianhao", bianhao);
	}
	
}
