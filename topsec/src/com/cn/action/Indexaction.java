package com.cn.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cn.dao.GoodsDao;
import com.cn.dao.OrderItemDao;
import com.cn.entity.TGoods;
import com.cn.entity.TOrderItem;
import com.cn.entity.TUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("indexAction")
/**
 * @author xz
 * @首页类
 * */
public class Indexaction extends ActionSupport {
	private GoodsDao goodsDao;
	private OrderItemDao orderitemDao;

	// 系统前准备
	@Action(value = "index", results = { @Result(name = "success", location = "/qiantai/index.jsp") })
	public String index() {
		Map request = (Map) ServletActionContext.getContext().get("request");

		List goodsYesTejiaList = goodsDao.findIndexyestejia();
		if (goodsYesTejiaList.size() > 5) {
			goodsYesTejiaList = goodsYesTejiaList.subList(0, 5);
		}
		request.put("goodsYesTejiaList", goodsYesTejiaList);

		List goodsNoTejiaList = goodsDao.findIndexnotejia();
		if (goodsNoTejiaList.size() > 5) {
			goodsNoTejiaList = goodsNoTejiaList.subList(0, 5);
		}
		request.put("goodsNoTejiaList", goodsNoTejiaList);

		// 排行榜
		List goodsList = new ArrayList();
		List list = orderitemDao.find();
		for (int i = 0; i < list.size(); i++) {
			TOrderItem b = (TOrderItem) list.get(i);
			int goodsId = b.getGoodsId();
			TGoods goods = goodsDao.findByGoodsId(goodsId);
			goodsList.add(goods);
		}
		if (goodsList.size() > 5) {
			goodsList = goodsList.subList(0, 5);
		}
		request.put("goodsList", goodsList);
		// paihangbang

		return ActionSupport.SUCCESS;
	}

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public OrderItemDao getOrderitemDao() {
		return orderitemDao;
	}

	@Resource(name = "orderitemDao")
	public void setOrderitemDao(OrderItemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}

}
