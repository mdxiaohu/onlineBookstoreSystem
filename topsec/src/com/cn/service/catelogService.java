package com.cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.CateLogDao;

@Service("catelogService")
public class catelogService {
	private CateLogDao catelogDAO;

	public List findAllCatelog() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List cateLogList = catelogDAO.find();
		return cateLogList;
	}

	public CateLogDao getCatelogDAO() {
		return catelogDAO;
	}

	@Resource(name = "catelogDao")
	public void setCatelogDAO(CateLogDao catelogDAO) {
		this.catelogDAO = catelogDAO;
	}

}
