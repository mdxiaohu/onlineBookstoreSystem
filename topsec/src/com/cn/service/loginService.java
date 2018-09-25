package com.cn.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.cn.dao.AdminDao;
import com.cn.entity.TAdmin;

@Service("loginService")
public class loginService {

	private AdminDao adminDao;

	public AdminDao getAdminDao() {
		return adminDao;
	}

	@Resource(name = "adminDao")
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public String login(String userName, String userPw, int userType) {
		System.out.println("userType" + userType);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "no";

		if (userType == 0)// 系统管理员登陆
		{
			TAdmin admin = new TAdmin();
			admin.setUserName(userName);
			admin.setUserPw(userPw);
			TAdmin adminList = adminDao.adminLogin(admin);

			if (adminList == null) {
				result = "no";
			} else {
				WebContext ctx = WebContextFactory.get();
				HttpSession session = ctx.getSession();
				session.setAttribute("userType", 0);
				session.setAttribute("admin", admin);
				result = "yes";
			}
		}
		if (userType == 1)// 老师登陆
		{

		}
		if (userType == 2)// 学生登陆
		{

		}
		return result;
	}

	public String adminPwEdit(String userPwNew) {
		System.out.println("DDDD");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();

		TAdmin admin = (TAdmin) session.getAttribute("admin");
		admin.setUserPw(userPwNew);
		adminDao.UpdateAdmin(admin);
		session.setAttribute("admin", admin);

		return "yes";
	}


}
