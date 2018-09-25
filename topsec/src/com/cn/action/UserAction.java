package com.cn.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.cn.entity.TUser;
import com.cn.service.UserService;
import com.cn.util.Cart;
import com.cn.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@ParentPackage("struts-default")
@Controller("userAction")
/**
 * @author xz
 * @前台用户类
 * */
public class UserAction extends ActionSupport {

	private UserService userService;
	private TUser tuser;
	private int userId;
	private String userName;
	private String userPw;
	private String userRealname;
	private String userAddress;
	private String userSex;
	private String userTel;
	private String userEmail;
	private String userQq;
	private String newUserPw;
	private String message;
	private String path;
	//前台用户注册
	@Action(value = "userReg", results = { @Result(name = "success", location = "/common/add_success.jsp"),@Result(name = "error", location = "/qiantai/userinfo/userReg.jsp") })
	public String userReg() {
		int result = checkbyname();

		if (result == 1) {
			TUser user = new TUser();
			user.setUserName(userName);
			user.setUserPw(userPw);
			user.setUserAddress(userAddress);
			user.setUserTel(userTel);
			user.setUserRealname(userRealname);
			user.setUserEmail(userEmail);
			user.setUserSex(userSex);
			user.setUserQq(userQq);
			user.setUserDel("no");
			user.setUserJF(Util.infoJF);
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			String QDtime = formater.format(new Date());
			user.setUserQDtime(QDtime);
			userService.save(user);
			Map session = ServletActionContext.getContext().getSession();
			session.put("user", user);
			return SUCCESS;
		}
		return ERROR;
	}

	// 注册前验证用户名是否已存在
	public int checkbyname() {
		int a = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		TUser userResult = userService.findUserByUserName(userName);
		if (userResult != null) {
			request.setAttribute("msg", "该用户名已存在");

			return a;
		} else {
			a = 1;
			return a;
		}

	}

	// 登录
	@Action(value = "userLogin", results = { @Result(name = "success", location = "/qiantai/default.jsp") })
	public String userLogin() {
		TUser user = new TUser();
		user.setUserName(userName);
		user.setUserPw(userPw);
		TUser users = userService.Login(user);
		if (users == null) {
			this.setMessage("用户名或密码错误");
			this.setPath("qiantai/default.jsp");
		} else {
			Map session = ServletActionContext.getContext().getSession();
			session.put("user", users);
			Cart cart = new Cart();
			session.put("cart", cart);
			this.setMessage("成功登录");
			this.setPath("qiantai/default.jsp");
		}
		return "success";
	}
	//后台管理删除前台用户
	@Action(value = "userDel", results = { @Result(name = "success", location = "/common/succeed.jsp") })
	public String userDel() {
		userService.updateUserDel(userId);

		this.setMessage("删除成功");
		this.setPath("userMana.action");
		return "success";
	}

	//前台用户自修改
	@Action(value = "userEdit")
	public String userEdit() {
		TUser user = userService.findById(userId);

		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPw(userPw);
		user.setUserAddress(userAddress);
		user.setUserTel(userTel);
		user.setUserRealname(userRealname);
		user.setUserEmail(userEmail);
		user.setUserSex(userSex);
		user.setUserQq(userQq);
		user.setUserDel("no");
		userService.Update(user);
		// TUser user=userService.findById(userId);
		Map session = ServletActionContext.getContext().getSession();
		session.put("user", user);
		return null;
	}
	@Action(value = "userXinxi", results = { @Result(name = "success", location = "/admin/order/userXinxi.jsp") })
	public String userXinxi()
	{
		TUser user=userService.findById(userId);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("user", user);
		return ActionSupport.SUCCESS;
	}
	
	public TUser getModel() {
		if (tuser == null) {
			tuser = new TUser();
		}
		return tuser;
	}
	//退出登录
	@Action(value = "userLogout", results = { @Result(name = "success", location = "/qiantai/default.jsp") })
	public String userLogout() {
		Map session = ServletActionContext.getContext().getSession();
		session.remove("user");
		session.remove("info");
		return ActionSupport.SUCCESS;
	}
	//签到
	@Action(value = "userqiandao", results = { @Result(name = "success", location = "/qiantai/default.jsp") })
	public String userqiandao() {
		Map session = ServletActionContext.getContext().getSession();
		tuser =(TUser) session.get("user");
		int id=	tuser.getUserId();
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		String QDtime = formater.format(new Date());
		TUser user=userService.findById(id);
		
		if (user.getUserQDtime().equals(QDtime)) {
			session.put("info", "已签到");
			session.put("user", tuser);
			return ActionSupport.SUCCESS;
		}else{
		tuser.setUserQDtime(QDtime);
		int jf=Integer.valueOf(tuser.getUserJF())+Util.QDJF;
		tuser.setUserJF(String.valueOf(jf));
		int up=userService.qiandao(tuser);
		session.put("info", "已签到");
		session.put("user", tuser);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	//后台查询所有前台用户
	@Action(value = "userMana", results = { @Result(name = "success", location = "/admin/user/userMana.jsp") })
	public String userMana() {
		List userList = userService.findAll();
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}

	
	

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TUser getTuser() {
		return tuser;
	}

	public void setTuser(TUser tuser) {
		this.tuser = tuser;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getNewUserPw() {
		return newUserPw;
	}

	public void setNewUserPw(String newUserPw) {
		this.newUserPw = newUserPw;
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

	public UserService getUserService() {
		return userService;
	}

}
