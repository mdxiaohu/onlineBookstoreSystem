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

import com.cn.dao.AdminDao;
import com.cn.entity.TAdmin;
import com.cn.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;
@Namespace("/")
@ParentPackage("struts-default")
@Controller("adminAction")
/**
 * @author xz
 * @后台用户类
 * */
public class AdminAction extends ActionSupport
{
	private int userId;
	private String userName;
	private String userPw;
	 
	private String message;
	private String path;
	
	private int index=1;

	private AdminDao adminDAO;
	
	
	@Action(value="adminAdd",results={@Result(name="success",location="/common/succeed.jsp")})
	public String adminAdd()
	{
		TAdmin admin=new TAdmin();
		admin.setUserName(userName);
		admin.setUserPw(userPw);
		adminDAO.save(admin);
		this.setMessage("操作成功");
		this.setPath("adminManage.action");
		return "success";
	}
	
	@Action(value="adminManage",results={@Result(name="success",location="/admin/index/adminManage.jsp")})
	public String adminManage()
	{
		List adminList=adminDAO.findAll();
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("adminList", adminList);
		return ActionSupport.SUCCESS;
	}
	@Action(value="adminManageFenye",results={@Result(name="success",location="/admin/index/adminManageFenye.jsp")})
	public String adminManageFenye()
	{
		List adminList=adminDAO.findAll();
		int pageSize=3;
		int fromIndex = (index - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, adminList.size());
		List adminListFenye = adminList.subList(fromIndex, toIndex);
		

        Pagination p = new Pagination();
        p.setIndex(index);
        p.setPageSize(pageSize);
        p.setTotle(adminList.size());
        p.setData(adminListFenye);
        p.setPath("adminManageFenye.action?");

		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("page", p);
		return ActionSupport.SUCCESS;
	}
	@Action(value="adminDel",results={@Result(name="success",location="/admin/index/adminManage.jsp")})
	public String adminDel()
	{
		adminDAO.delete(userId);
		this.setMessage("删除成功");
		this.setPath("adminManage.action");
		return "succeed";
	}
	
	


	public AdminDao getAdminDAO() {
		return adminDAO;
	}

	@Resource(name="adminDao")
	public void setAdminDAO(AdminDao adminDAO) {
		this.adminDAO = adminDAO;
	}

	public String getMessage()
	{
		return message;
	}

	public int getIndex()
	{
		return index;
	}



	public void setIndex(int index)
	{
		this.index = index;
	}



	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPw()
	{
		return userPw;
	}

	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}

}
