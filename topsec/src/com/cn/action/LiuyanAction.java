package com.cn.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cn.dao.LiuYanDao;
import com.cn.entity.TLiuyan;
import com.cn.entity.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("LiuyanAction")
/**
 * @author xz
 * @留言类
 * */
public class LiuyanAction extends ActionSupport {
	private int liuyanId;
	private String liuyanTitle;
	private String liuyanContent;

	private LiuYanDao liuyanDao;
	private String message;
	private String path;
	//查看留言
	@Action(value = "liuyanMana", results = { @Result(name = "success", location = "/admin/liuyan/liuyanMana.jsp") })
	public String liuyanMana() {
		List liuyanList = liuyanDao.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("liuyanList", liuyanList);
		return ActionSupport.SUCCESS;
	}
	//添加留言
	@Action(value = "liuyanAdd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String liuyanAdd() {
		TLiuyan liuyan = new TLiuyan();
		liuyan.setLiuyanContent(liuyanContent);
		liuyan.setLiuyanTitle(liuyanTitle);
		liuyan.setLiuyanDate(new Date().toLocaleString());
		Map session = ActionContext.getContext().getSession();

		if (session.get("user") != null) {
			TUser user = (TUser) session.get("user");
			liuyan.setLiuyanUser(user.getUserName());
		}

		liuyanDao.save(liuyan);
		this.setMessage("留言成功");
		this.setPath("liuyanAll.action");
		return "success";
	}
//删除留言
	@Action(value = "liuyanDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String liuyanDel() {
		// TLiuyan liuyan=liuyanDao.findById(liuyanId);
		liuyanDao.deleteById(liuyanId);
		this.setMessage("留言删除成功");
		this.setPath("liuyanMana.action");
		return "success";
	}
	//后台查看留言
	@Action(value = "liuyanAll", results = { @Result(name = "success", location = "/qiantai/liuyan/liuyanAll.jsp") })
	public String liuyanAll() {
		List liuyanList = liuyanDao.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("liuyanList", liuyanList);
		return ActionSupport.SUCCESS;
	}

	public String getLiuyanContent() {
		return liuyanContent;
	}

	public void setLiuyanContent(String liuyanContent) {
		this.liuyanContent = liuyanContent;
	}

	public int getLiuyanId() {
		return liuyanId;
	}

	public void setLiuyanId(int liuyanId) {
		this.liuyanId = liuyanId;
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

	public String getLiuyanTitle() {
		return liuyanTitle;
	}

	public void setLiuyanTitle(String liuyanTitle) {
		this.liuyanTitle = liuyanTitle;
	}

	public LiuYanDao getLiuyanDao() {
		return liuyanDao;
	}

	@Resource(name = "liuyanDao")
	public void setLiuyanDao(LiuYanDao liuyanDao) {
		this.liuyanDao = liuyanDao;
	}

}
