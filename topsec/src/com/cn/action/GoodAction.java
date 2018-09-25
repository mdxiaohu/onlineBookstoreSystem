package com.cn.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cn.dao.CateLogDao;
import com.cn.dao.GoodsDao;
import com.cn.entity.TGoods;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("goodAction")
/**
 * @author xz
 * @商品类
 * */
public class GoodAction extends ActionSupport {

	private int goodsId;
	private int goodsCatelogId;
	private String goodsName;
	private String goodsMiaoshu;
	private String fujian;
	private String goodsYanse;
	private int goodsShichangjia;
	private int goodsTejia;

	private int catelogId;
	private int goodsKucun;

	private String message;
	private String path;

	private GoodsDao goodsDao;
	private CateLogDao catelogDAO;

	private int rukushuliang;

	@Action(value = "goodsNoTejiaAdd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String goodsNoTejiaAdd() {
		TGoods goods = new TGoods();
		goods.setGoodsCatelogId(goodsCatelogId);
		goods.setGoodsName(goodsName);
		goods.setGoodsMiaoshu(goodsMiaoshu);
		goods.setGoodsPic(fujian);
		goods.setGoodsShichangjia(goodsShichangjia);
		if (goodsTejia == 0)// 特格为0表示没有特价
		{
			goods.setGoodsTejia(goodsShichangjia);// 如果不是特价商品。把这个商品的特价设置为市场价格
			goods.setGoodsIsnottejia("no");
		} else {
			goods.setGoodsTejia(goodsTejia);
			goods.setGoodsIsnottejia("yes");
		}

		goods.setGoodsKucun(goodsKucun);
		goods.setGoodsDel("no");

		goodsDao.save(goods);
		this.setMessage("操作成功");
		this.setPath("goodsManaNoTejia.action");
		return "success";

	}

	@Action(value = "goodsDetailHou", results = { @Result(name = "success", location = "/admin/goods/goodsDetailHou.jsp") })
	public String goodsDetailHou()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		
		TGoods goods=goodsDao.findByGoodsId(goodsId);
		request.put("goods", goods);
		return ActionSupport.SUCCESS;
	}
	
	
	@Action(value = "goodsNoTejiaDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String goodsNoTejiaDel() {
		TGoods goods = goodsDao.findByGoodsId(goodsId);
		goods.setGoodsDel("yes");
		goodsDao.Update(goods);
		this.setMessage("操作成功");
		this.setPath("goodsManaNoTejia.action");
		return "success";
	}

	@Action(value = "goodsManaNoTejia", results = { @Result(name = "success", location = "/admin/goods/goodsManaNoTejia.jsp") })
	public String goodsManaNoTejia() {
		List goodsList = goodsDao.findgoods();
		for (int i = 0; i < goodsList.size(); i++) {
			TGoods goods = (TGoods) goodsList.get(i);
			System.out.println(goods.getGoodsCatelogId());
			goods.setGoodsCatelogName(catelogDAO.findById(
					goods.getGoodsCatelogId()).getCatelogName());
		}
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("goodsList", goodsList);
		return SUCCESS;
	}

	@Action(value = "goodsDetail", results = { @Result(name = "success", location = "/qiantai/goods/goodsDetail.jsp") })
	public String goodsDetail() {
		Map request = (Map) ServletActionContext.getContext().get("request");

		TGoods goods = goodsDao.findByGoodsId(goodsId);
		request.put("goods", goods);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodsShezhiTejia", results = { @Result(name = "success", location = "/common/succ.jsp") })
	public String goodsShezhiTejia() {
		TGoods goods = goodsDao.findByGoodsId(goodsId);
		goods.setGoodsIsnottejia("yes");
		goods.setGoodsTejia(goodsTejia);
		goodsDao.Update(goods);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodsKucun", results = { @Result(name = "success", location = "/admin/goods/goodsKuncun.jsp") })
	public String goodsKucun() {
		List goodsList = goodsDao.findgoods();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("goodsList", goodsList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodsRuku", results = { @Result(name = "success", location = "/common/succ.jsp") })
	public String goodsRuku() {
		TGoods goods = goodsDao.findByGoodsId(goodsId);
		goods.setGoodsKucun(goods.getGoodsKucun() + rukushuliang);
		goods.setGoodsTejia(goodsTejia);
		goodsDao.updateKucun(goods);
		return ActionSupport.SUCCESS;
	}


	@Action(value = "goodsAllYesTejia", results = { @Result(name = "success", location = "/qiantai/goods/goodsAllYesTejia.jsp") })
	public String goodsAllYesTejia() {
		Map request = (Map) ServletActionContext.getContext().get("request");
		List goodsYesTejiaList = goodsDao.findAllYesTejia();
		request.put("goodsYesTejiaList", goodsYesTejiaList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodsAllNoTejia", results = { @Result(name = "success", location = "/qiantai/goods/goodsAllNoTejia.jsp") })
	public String goodsAllNoTejia() {
		Map request = (Map) ServletActionContext.getContext().get("request");
		List goodsYesTejiaList = goodsDao.findAllNoTejia();
		request.put("goodsYesTejiaList", goodsYesTejiaList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodsByCatelog", results = { @Result(name = "success", location = "/qiantai/goods/goodsByCatelog.jsp") })
	public String goodsByCatelog() {
		Map request = (Map) ServletActionContext.getContext().get("request");
		List goodsByCatelogList = goodsDao.findbycatelogId(catelogId);
		request.put("goodsByCatelogList", goodsByCatelogList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "goodSearch", results = { @Result(name = "success", location = "/qiantai/goods/goodSearch.jsp") })
	public String goodSearch() {
		Map request = (Map) ServletActionContext.getContext().get("request");
		String sql = "";
		List goodsList = null;
		if (catelogId == 0) {
			TGoods goods = new TGoods();
			goods.setGoodsName("%" + goodsName + "%");
			goodsList = goodsDao.findByName(goods);

		} else {
			TGoods goods = new TGoods();
			goods.setGoodsName("%" + goodsName + "%");
			goods.setGoodsCatelogId(catelogId);
			goodsList = goodsDao.findBygoodsCatelogId(goods);

		}

		request.put("goodsList", goodsList);

		return ActionSupport.SUCCESS;
	}

	public int getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(int catelogId) {
		this.catelogId = catelogId;
	}

	public int getGoodsCatelogId() {
		return goodsCatelogId;
	}

	public void setGoodsCatelogId(int goodsCatelogId) {
		this.goodsCatelogId = goodsCatelogId;
	}

	public int getRukushuliang() {
		return rukushuliang;
	}

	public void setRukushuliang(int rukushuliang) {
		this.rukushuliang = rukushuliang;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsMiaoshu() {
		return goodsMiaoshu;
	}

	public void setGoodsMiaoshu(String goodsMiaoshu) {
		this.goodsMiaoshu = goodsMiaoshu;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getFujian() {
		return fujian;
	}

	public int getGoodsKucun() {
		return goodsKucun;
	}

	public void setGoodsKucun(int goodsKucun) {
		this.goodsKucun = goodsKucun;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsShichangjia() {
		return goodsShichangjia;
	}

	public void setGoodsShichangjia(int goodsShichangjia) {
		this.goodsShichangjia = goodsShichangjia;
	}

	public int getGoodsTejia() {
		return goodsTejia;
	}

	public void setGoodsTejia(int goodsTejia) {
		this.goodsTejia = goodsTejia;
	}

	public String getGoodsYanse() {
		return goodsYanse;
	}

	public void setGoodsYanse(String goodsYanse) {
		this.goodsYanse = goodsYanse;
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

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public CateLogDao getCatelogDAO() {
		return catelogDAO;
	}

	@Resource(name = "catelogDao")
	public void setCatelogDAO(CateLogDao catelogDAO) {
		this.catelogDAO = catelogDAO;
	}

}
