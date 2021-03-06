package com.midland.web.model;


public class LinkUrlManager{
	/**
	 * 链接关联表
	 **/
	private Integer id;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 链接名
	 **/
	private String linkName;
	/**
	 * 链接
	 **/
	private String linkUrl;
	/**
	 * 联系人
	 **/
	private String contacts;
	/**
	 * 联系方式
	 **/
	private String phone;
	/**
	 * 备注
	 **/
	private String remarks;
	/**
	 * 是否开启 0=开启；1=关闭
	 **/
	private Integer isShow;
	/**
	 * 排序字段
	 **/
	private Integer orderBy;
	/**
	 * 1=删除；0=正常
	 **/
	private Integer isDelete;
	/**
	 * 平台 1=网站；2=微站
	 **/
	private Integer source;
	/**
	 * 
	 **/
	private String cityName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("LinkUrlManager{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (linkName != null) {
			sb.append(", \"linkName\":\"").append(linkName).append("\"");
		}
		if (linkUrl != null) {
			sb.append(", \"linkUrl\":\"").append(linkUrl).append("\"");
		}
		if (contacts != null) {
			sb.append(", \"contacts\":\"").append(contacts).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (remarks != null) {
			sb.append(", \"remarks\":\"").append(remarks).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (orderBy != null) {
			sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		return sb.toString();
	}
}