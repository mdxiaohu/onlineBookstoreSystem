package com.cn.entity;

import java.sql.Timestamp;

/**
 * EbookEntry entity. @author MyEclipse Persistence Tools
 */
public class EbookEntry implements java.io.Serializable {

	// Fields

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EbookEntry [id=" + id + ", ebookCategory=" + ebookCategory
				+ ", title=" + title + ", summary=" + summary + ", uploaduser="
				+ uploaduser + ", createdate=" + createdate + "]";
	}

	private EbookCategory ebookCategory;
	private String title;
	private String summary;
	private String uploaduser;
	private String createdate;

	public EbookCategory getEbookCategory() {
		return ebookCategory;
	}

	public void setEbookCategory(EbookCategory ebookCategory) {
		this.ebookCategory = ebookCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUploaduser() {
		return uploaduser;
	}

	public void setUploaduser(String uploaduser) {
		this.uploaduser = uploaduser;
	}

	// Constructors

	/** default constructor */
	public EbookEntry() {
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}