package com.cn.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.cn.dao.GoodsDao;
import com.cn.util.Cart;

@Service("cartService")
public class cartService {
	private GoodsDao goodsDAO;

	public String modiNum(int goodsId, int quantity) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (goodsDAO.findByGoodsId(goodsId).getGoodsKucun() < quantity) {
			return "no";
		}

		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateCart(goodsId, quantity);
		session.setAttribute("cart", cart);
		return "yes";
	}

	public String delGoodsFromCart(int goodsId) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.delGoods(goodsId);
		session.setAttribute("cart", cart);
		return "yes";
	}

	public String clearCart() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.getItems().clear();
		session.setAttribute("cart", cart);
		return "yes";
	}

	public GoodsDao getGoodsDAO() {
		return goodsDAO;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDAO(GoodsDao goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

}
