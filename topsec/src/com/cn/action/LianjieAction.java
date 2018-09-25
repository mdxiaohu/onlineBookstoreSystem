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

import com.cn.dao.LianjieDao;
import com.cn.entity.Tlianjie;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("lianjieAction")
/**
 * @author xz
 * @链接类
 * */
public class LianjieAction extends ActionSupport {
	private int lianjieId;
	private String lianjieWeb;
	private String lianjieName;

	private String message;
	private String path;

	private LianjieDao lianjieDAO;

	@Action(value = "lianjieAdd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String lianjieAdd() {
		Tlianjie lianjie = new Tlianjie();
		lianjie.setlianjieName(lianjieName);
		lianjie.setlianjieWeb(lianjieWeb);
		lianjieDAO.save(lianjie);
		this.setMessage(" 添加完毕");
		this.setPath("lianjieMana.action");
		return "success";
	}

	@Action(value = "lianjieMana", results = { @Result(name = "success", location = "/admin/lianjie/lianjieMana.jsp") })
	public String lianjieMana() {
		List lianjieList = lianjieDAO.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("lianjieList", lianjieList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "lianjieDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	  public String lianjieDel() {
		  lianjieDAO.deletebyId(lianjieId);
	  this.setMessage("删除成功"); this.setPath("lianjieMana.action"); return
	  "success"; }
	
	@Action(value = "lianjieQian", results = { @Result(name = "success", location = "/qiantai/lianjie/lianjieQian.jsp") })
	  public String lianjieQian() { Map
	  request=(Map)ServletActionContext.getContext().get("request");
	  
	  List lianjieList=lianjieDAO.findAll();
	  
	  request.put("lianjieList", lianjieList); return ActionSupport.SUCCESS; }
	 
	
	public int getLianjieId() {
		return lianjieId;
	}

	public void setLianjieId(int lianjieId) {
		this.lianjieId = lianjieId;
	}

	public String getLianjieWeb() {
		return lianjieWeb;
	}

	public void setLianjieWeb(String lianjieWeb) {
		this.lianjieWeb = lianjieWeb;
	}

	public String getLianjieName() {
		return lianjieName;
	}

	public void setLianjieName(String lianjieName) {
		this.lianjieName = lianjieName;
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

	public LianjieDao getLianjieDAO() {
		return lianjieDAO;
	}

	@Resource(name = "lianjieDao")
	public void setLianjieDAO(LianjieDao lianjieDAO) {
		this.lianjieDAO = lianjieDAO;
	}

	




}
