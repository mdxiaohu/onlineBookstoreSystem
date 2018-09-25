package com.cn.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.cn.dao.GonggaoDao;
import com.cn.entity.TAdmin;
import com.cn.entity.TGonggao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("gonggaoAction")
/**
 * @author xz
 * @公告类
 * */
public class GonggaoAction extends ActionSupport {
	private int gonggaoId;
	private String gonggaoTitle;
	private String gonggaoContent;
	private String gonggaoData;
	private String gonggaoFabuzhe;

	private String message;
	private String path;

	private GonggaoDao gonggaoDAO;

	@Action(value = "gonggaoAdd", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String gonggaoAdd() {

		TGonggao gonggao = new TGonggao();
		gonggao.setGonggaoTitle(gonggaoTitle);
		gonggao.setGonggaoContent(gonggaoContent);
		gonggao.setGonggaoData(new Date().toLocaleString());

		gonggaoDAO.save(gonggao);
		this.setMessage("公告添加完毕");
		this.setPath("gonggaoMana.action");
		return "success";
	}

	@Action(value = "gonggaoMana", results = { @Result(name = "success", location = "/admin/gonggao/gonggaoMana.jsp") })
	public String gonggaoMana() {
		List gonggaoList = gonggaoDAO.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("gonggaoList", gonggaoList);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "gonggaoDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String gonggaoDel() {
		gonggaoDAO.delete(gonggaoId);
		this.setMessage("公告删除成功");
		this.setPath("gonggaoMana.action");
		return "success";
	}

	@Action(value = "gonggaoDetail", results = { @Result(name = "success", location = "/admin/gonggao/gonggaoDetail.jsp") })
	public String gonggaoDetail() {
		TGonggao gonggao = gonggaoDAO.findById(gonggaoId);
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("gonggao", gonggao);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "gonggaoDetailQian", results = { @Result(name = "success", location = "/qiantai/gonggao/gonggaoDetailQian.jsp") })
	public String gonggaoDetailQian() {
		TGonggao gonggao = gonggaoDAO.findById(gonggaoId);
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("gonggao", gonggao);

		return ActionSupport.SUCCESS;
	}

	@Action(value = "gonggaoQian5", results = { @Result(name = "success", location = "/qiantai/gonggao/gonggaoQian5.jsp") })
	public String gonggaoQian5() {
		Map request = (Map) ServletActionContext.getContext().get("request");

		List gonggaoList = gonggaoDAO.findAll();
		if (gonggaoList.size() > 5) {
			gonggaoList = gonggaoList.subList(0, 5);
		}
		request.put("gonggaoList", gonggaoList);
		return ActionSupport.SUCCESS;
	}

	public String getGonggaoContent() {
		return gonggaoContent;
	}

	public void setGonggaoContent(String gonggaoContent) {
		this.gonggaoContent = gonggaoContent;
	}

	public GonggaoDao getGonggaoDAO() {
		return gonggaoDAO;
	}

	@Resource(name = "gonggaoDao")
	public void setGonggaoDAO(GonggaoDao gonggaoDAO) {
		this.gonggaoDAO = gonggaoDAO;
	}

	public String getGonggaoData() {
		return gonggaoData;
	}

	public void setGonggaoData(String gonggaoData) {
		this.gonggaoData = gonggaoData;
	}

	public String getGonggaoFabuzhe() {
		return gonggaoFabuzhe;
	}

	public void setGonggaoFabuzhe(String gonggaoFabuzhe) {
		this.gonggaoFabuzhe = gonggaoFabuzhe;
	}

	public int getGonggaoId() {
		return gonggaoId;
	}

	public void setGonggaoId(int gonggaoId) {
		this.gonggaoId = gonggaoId;
	}

	public String getGonggaoTitle() {
		return gonggaoTitle;
	}

	public void setGonggaoTitle(String gonggaoTitle) {
		this.gonggaoTitle = gonggaoTitle;
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

}
