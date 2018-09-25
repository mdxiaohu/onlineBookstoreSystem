package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cn.entity.TAdmin;
import com.cn.entity.TCatelog;
import com.cn.entity.TGoods;
import com.cn.entity.TUser;

@Repository("goodsDao")
public class GoodsDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public TGoods findByGoodsId(int goodsId) {
		return sqlSessionTemplate.selectOne("goodsMapper.findByGoodsId",
				goodsId);
	}

	public List find(int catelogId) {
		return sqlSessionTemplate.selectList("goodsMapper.Find", catelogId);
	}

	public List findgoods() {
		return sqlSessionTemplate.selectList("goodsMapper.findgoods");
	}

	public List findAllYesTejia() {
		return sqlSessionTemplate.selectList("goodsMapper.findAllYesTejia");
	}

	public List findAllNoTejia() {
		return sqlSessionTemplate.selectList("goodsMapper.findAllNoTejia");
	}

	public List findByName(TGoods goods) {
		return sqlSessionTemplate.selectList("goodsMapper.findByName", goods);
	}

	public List findBygoodsCatelogId(TGoods goods) {
		return sqlSessionTemplate.selectList("goodsMapper.findByName", goods);
	}

	public void save(TGoods goods) {
		sqlSessionTemplate.insert("goodsMapper.save", goods);
	}

	public void Update(TGoods goods) {
		sqlSessionTemplate.update("goodsMapper.update", goods);
	}

	public void updateKucun(TGoods goods) {
		sqlSessionTemplate.update("goodsMapper.updateKucun", goods);
	}

	public List<TGoods> findIndexyestejia() {
		return sqlSessionTemplate.selectList("goodsMapper.findIndexyestejia");
	}

	public List<TGoods> findIndexnotejia() {
		return sqlSessionTemplate.selectList("goodsMapper.findIndexnotejia");
	}

	public List<TGoods> findbycatelogId(int catelogId) {
		return sqlSessionTemplate.selectList("goodsMapper.findByFenlei",
				catelogId);
	}

	public void bulkUpdate(TGoods goods) {
		sqlSessionTemplate.selectList("goodsMapper.bulkUpdate", goods);
	}

}
