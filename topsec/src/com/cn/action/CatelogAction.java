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
import com.cn.entity.TCatelog;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("catelogAction.java")
/**
 * @author xz
 * @商品分类类
 * */
public class CatelogAction extends ActionSupport {
	private int catelogId;
	private String catelogName;
	private String catelogMiaoshu;

	private String message;
	private String path;

	private CateLogDao catelogDAO;
	private GoodsDao goodsDao;

	@Action(value = "catelogMana", results = { @Result(name = "success", location = "/admin/catelog/catelogMana.jsp") })
	public String catelogMana() {
		List<TCatelog> cateLogList = catelogDAO.find();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("cateLogList", cateLogList);
		return SUCCESS;
	}

	@Action(value = "catelogAdd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String catelogAdd() {
		TCatelog catelog = new TCatelog();
		catelog.setCatelogName(catelogName);
		catelog.setCatelogMiaoshu("无");
		catelog.setCatelogDel("no");
		catelogDAO.save(catelog);
		this.setMessage("操作成功");
		this.setPath("catelogMana.action");
		return "success";
	}

	@Action(value = "catelogDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String catelogDel() {
		List goodsList = goodsDao.find(catelogId);
		if (goodsList.size() > 0) {
			this.setMessage("请先删除此类别下的商品");
			this.setPath("catelogMana.action");
		} else {
			TCatelog catelog = catelogDAO.findById(catelogId);
			catelog.setCatelogDel("yes");

			/*
			 * if (!catelog.getCatelogMiaoshu().equals("")) {
			 * 
			 * }else{ catelog.setCatelogMiaoshu("无"); }
			 */

			catelogDAO.update(catelog);
			this.setMessage("操作成功");
			this.setPath("catelogMana.action");
		}
		return "success";
	}

	@Action(value = "catelogAll", results = { @Result(name = "success", location = "/qiantai/catelog/catelogAll.jsp") })
	public String catelogAll() {
		System.out.println("");
		List<TCatelog> cateLogList = catelogDAO.find();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("cateLogList", cateLogList);
		return SUCCESS;
	}

	public int getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(int catelogId) {
		this.catelogId = catelogId;
	}

	public String getCatelogName() {
		return catelogName;
	}

	public void setCatelogName(String catelogName) {
		this.catelogName = catelogName;
	}

	public String getCatelogMiaoshu() {
		return catelogMiaoshu;
	}

	public void setCatelogMiaoshu(String catelogMiaoshu) {
		this.catelogMiaoshu = catelogMiaoshu;
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

	public CateLogDao getCatelogDAO() {
		return catelogDAO;
	}

	@Resource(name = "catelogDao")
	public void setCatelogDAO(CateLogDao catelogDAO) {
		this.catelogDAO = catelogDAO;
	}

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}


}
