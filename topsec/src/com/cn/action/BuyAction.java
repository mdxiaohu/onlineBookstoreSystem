package com.cn.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import com.cn.dao.OrderDao;
import com.cn.dao.OrderItemDao;
import com.cn.entity.TGoods;
import com.cn.entity.TOrder;
import com.cn.entity.TOrderItem;
import com.cn.entity.TUser;
import com.cn.service.UserService;
import com.cn.util.Cart;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("buyAction")
/**
 * @author xz
 * @购物车和订单类
 * */
public class BuyAction extends ActionSupport {
	private int goodsId;
	private int quantity;
	private UserService userService;
	private int orderId;

	private GoodsDao goodsDAO;
	private OrderDao orderDAO;
	private OrderItemDao orderItemDAO;

	private String message;
	private String path;

	private String odderSonghuodizhi;
	private String odderFukuangfangshi;

	@Action(value = "addToCart", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String addToCart() {
		TGoods goods = goodsDAO.findByGoodsId(goodsId);
		TOrderItem orderItem = new TOrderItem();
		orderItem.setGoods(goods);
		orderItem.setGoodsQuantity(quantity);

		Map session = ServletActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		cart.addGoods(goodsId, orderItem);
		session.put("cart", cart);
		// this.setMessage("");
		this.setPath("myCart.action");
		return "success";
	}

	@Action(value = "myCart", results = { @Result(name = "success", location = "/qiantai/myCart/myCart.jsp") })
	public String myCart() {
		return ActionSupport.SUCCESS;
	}

	@Action(value = "orderQueren", results = { @Result(name = "success", location = "/qiantai/order/orderQueren.jsp") })
	public String orderQueren() {
		Map request = (Map) ServletActionContext.getContext().get("request");

		return ActionSupport.SUCCESS;
	}

	@Action(value = "orderSubmit", results = { @Result(name = "success", location = "/qiantai/order/orderSubmit.jsp"),@Result(name="error",location="/qiantai/order/orderQueren.jsp") })
	public String orderSubmit() {
		Map session = ServletActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		TUser user = (TUser) session.get("user");
		TOrder order = new TOrder();
		String bianhao = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		order.setOrderBianhao(bianhao);
		order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		order.setOrderZhuangtai("yes");
		order.setOrderUserId(user.getUserId());
		order.setOrderJine(cart.getTotalPrice());
		order.setOdderFukuangfangshi(odderFukuangfangshi);
		order.setOdderSonghuodizhi(odderSonghuodizhi);
		
		if(odderFukuangfangshi.equals("积分消费")){
			
			int JiFen=	Integer.valueOf(user.getUserJF())-cart.getTotalPrice();
			if (JiFen>=0) {
				orderDAO.save(order);
				user.setUserJF(String.valueOf(JiFen));
				int up=userService.qiandao(user);
				
					
					order = orderDAO.findByBianhao(bianhao);
					
					for (Iterator it = cart.getItems().values().iterator(); it.hasNext();) {
						
						TOrderItem orderItem = (TOrderItem) it.next();
						orderItem.setOrderId(order.getOrderId());
						orderItem.setGoodsId(orderItem.getGoods().getGoodsId());
						TGoods goods = new TGoods();
						
						goods.setGoodsKucun(orderItem.getGoods().getGoodsKucun()
								- orderItem.getGoodsQuantity());
						goods.setGoodsId(orderItem.getGoods().getGoodsId());
						goodsDAO.bulkUpdate(goods);
						orderItemDAO.save(orderItem);
					}
					
					cart.getItems().clear();
					session.put("cart", cart);
					
					Map request = (Map) ServletActionContext.getContext().get("request");
					request.put("order", order);
					
					return ActionSupport.SUCCESS;
				
			}else{
				
				Map request = (Map) ServletActionContext.getContext().get("request");
				request.put("SYJF", "抱歉,积分不足");
				return "error" ;
			}
			
			
		}else{
			
			
			
		
				orderDAO.save(order);
				
				
				
					
					order = orderDAO.findByBianhao(bianhao);
					
					for (Iterator it = cart.getItems().values().iterator(); it.hasNext();) {
						
						TOrderItem orderItem = (TOrderItem) it.next();
						orderItem.setOrderId(order.getOrderId());
						orderItem.setGoodsId(orderItem.getGoods().getGoodsId());
						TGoods goods = new TGoods();
						
						goods.setGoodsKucun(orderItem.getGoods().getGoodsKucun()
								- orderItem.getGoodsQuantity());
						goods.setGoodsId(orderItem.getGoods().getGoodsId());
						goodsDAO.bulkUpdate(goods);
						orderItemDAO.save(orderItem);
					}
					
					cart.getItems().clear();
					session.put("cart", cart);
					
					Map request = (Map) ServletActionContext.getContext().get("request");
					request.put("order", order);
					
					return ActionSupport.SUCCESS;
		

	}
	}

	@Action(value = "orderDetail", results = { @Result(name = "success", location = "/qiantai/order/orderDetail.jsp") })
	public String orderDetail() {

		List orderItemList = orderItemDAO.findByOrderId(orderId);
		for (int i = 0; i < orderItemList.size(); i++) {
			TOrderItem orderItem = (TOrderItem) orderItemList.get(i);
			orderItem.setGoods(goodsDAO.findByGoodsId(orderItem.getGoodsId()));
		}
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("orderItemList", orderItemList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "orderDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String orderDel() {
		orderDAO.deleteByOrderId(orderId);

		Map session = ServletActionContext.getContext().getSession();
		TUser user = (TUser) session.get("user");

		this.setMessage("删除成功");
		this.setPath("myOrder.action?userId=" + user.getUserId());
		return "success";
	}

	@Action(value = "myOrder", results = { @Result(name = "success", location = "/qiantai/order/myOrder.jsp") })
	public String myOrder() {
		Map session = ServletActionContext.getContext().getSession();
		TUser user = (TUser) session.get("user");
		List orderList = orderDAO.find(user.getUserId());
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("orderList", orderList);

		return ActionSupport.SUCCESS;
	}

	
	@Action(value = "orderMana", results = { @Result(name = "success", location = "/admin/order/orderMana.jsp") })
	public String orderMana() {

		List orderList = orderDAO.findByorderUserId();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("orderList", orderList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "orderDelByAd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String orderDelByAd() {
		TOrder order = orderDAO.findById(orderId);
		orderDAO.deletebyorderId(orderId);
		orderItemDAO.deletebyorderId(orderId);

		this.setMessage("删除成功");
		this.setPath("orderMana.action");
		return "success";
	}

	@Action(value = "orderShouli", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String orderShouli() {
		orderDAO.UpdateZhuantai(orderId);
		this.setMessage("受理订单成功");
		this.setPath("orderMana.action");
		return "success";
	}

	@Action(value = "caiwuMana", results = { @Result(name = "success", location = "/admin/caiwu/caiwuMana.jsp") })
	public String caiwuMana() {
		List orderList = orderDAO.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("orderList", orderList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "caiwuTongji", results = { @Result(name = "success", location = "/admin/caiwu/caiwuTongji.jsp") })
	public String caiwuTongji() {
		HttpServletRequest request1 = ServletActionContext.getRequest();
		String shijian_sta = request1.getParameter("shijian_sta");
		String shijian_end = request1.getParameter("shijian_end");
		TOrder order = new TOrder();
		order.setShijian_sta(shijian_sta);
		order.setShijian_end(shijian_end);

		List orderList = orderDAO.findByTime(order);
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("orderList", orderList);
		return ActionSupport.SUCCESS;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public GoodsDao getGoodsDAO() {
		return goodsDAO;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDAO(GoodsDao goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	public OrderDao getOrderDAO() {
		return orderDAO;
	}

	@Resource(name = "orderDao")
	public void setOrderDAO(OrderDao orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderItemDao getOrderItemDAO() {
		return orderItemDAO;
	}

	@Resource(name = "orderitemDao")
	public void setOrderItemDAO(OrderItemDao orderItemDAO) {
		this.orderItemDAO = orderItemDAO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOdderSonghuodizhi() {
		return odderSonghuodizhi;
	}

	public void setOdderSonghuodizhi(String odderSonghuodizhi) {
		this.odderSonghuodizhi = odderSonghuodizhi;
	}

	public String getOdderFukuangfangshi() {
		return odderFukuangfangshi;
	}

	public void setOdderFukuangfangshi(String odderFukuangfangshi) {
		this.odderFukuangfangshi = odderFukuangfangshi;
	}
	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
}
