package com.cn.entity;

/**
 * TGonggao generated by MyEclipse Persistence Tools
 */

public class Tlianjie implements java.io.Serializable {

	// Fields

	private Integer lianjieId;

	private String lianjieWeb;

	private String lianjieName;

	// Constructors

	/** default constructor */
	public Tlianjie() {
	}

	/** full constructor */
	public Tlianjie(String lianjieWeb, String lianjieName) {
		this.lianjieWeb = lianjieWeb;
		this.lianjieName = lianjieName;

	}

	// Property accessors

	public Integer getlianjieId() {
		return this.lianjieId;
	}

	public void setlianjieId(Integer lianjieId) {
		this.lianjieId = lianjieId;
	}

	public String getlianjieWeb() {
		return this.lianjieWeb;
	}

	public void setlianjieWeb(String lianjieWeb) {
		this.lianjieWeb = lianjieWeb;
	}

	public String getlianjieName() {
		return this.lianjieName;
	}

	public void setlianjieName(String lianjieName) {
		this.lianjieName = lianjieName;
	}

}