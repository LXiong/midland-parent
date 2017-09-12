package com.midland.web.model;

import java.util.Date;

public class RecruitManager{
	/**
	 * 招聘管理表id
	 **/
	private Integer id;
	/**
	 * 招聘类型；1=校招；2=社招
	 **/
	private Integer type;
	/**
	 * 岗位
	 **/
	private String post;
	/**
	 * 招聘人数
	 **/
	private Integer recruitersNum;
	/**
	 * 学历要求；0=不限；1=高中；2=大专；3=本科；4=硕士及硕士以上
	 **/
	private Integer education;
	/**
	 * 工作年限
	 **/
	private Integer workLift;
	/**
	 * 开始时间
	 **/
	private Date startTime;
	/**
	 * 结束时间
	 **/
	private Date endTime;
	/**
	 * 0=已发布；1=未发布
	 **/
	private Integer releaseStatus;
	/**
	 * 岗位描述
	 **/
	private String postDesc;
	/**
	 * 任职要求
	 **/
	private String jobClaim;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 发布时间
	 **/
	private Date releaseTime;
	/**
	 * 接收邮箱
	 **/
	private String email;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getRecruitersNum() {
		return recruitersNum;
	}

	public void setRecruitersNum(Integer recruitersNum) {
		this.recruitersNum = recruitersNum;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getWorkLift() {
		return workLift;
	}

	public void setWorkLift(Integer workLift) {
		this.workLift = workLift;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(Integer releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public String getJobClaim() {
		return jobClaim;
	}

	public void setJobClaim(String jobClaim) {
		this.jobClaim = jobClaim;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("RecruitManager{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (post != null) {
			sb.append(", \"post\":\"").append(post).append("\"");
		}
		if (recruitersNum != null) {
			sb.append(", \"recruitersNum\":\"").append(recruitersNum).append("\"");
		}
		if (education != null) {
			sb.append(", \"education\":\"").append(education).append("\"");
		}
		if (workLift != null) {
			sb.append(", \"workLift\":\"").append(workLift).append("\"");
		}
		if (startTime != null) {
			sb.append(", \"startTime\":\"").append(startTime).append("\"");
		}
		if (endTime != null) {
			sb.append(", \"endTime\":\"").append(endTime).append("\"");
		}
		if (releaseStatus != null) {
			sb.append(", \"releaseStatus\":\"").append(releaseStatus).append("\"");
		}
		if (postDesc != null) {
			sb.append(", \"postDesc\":\"").append(postDesc).append("\"");
		}
		if (jobClaim != null) {
			sb.append(", \"jobClaim\":\"").append(jobClaim).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (releaseTime != null) {
			sb.append(", \"releaseTime\":\"").append(releaseTime).append("\"");
		}
		if (email != null) {
			sb.append(", \"email\":\"").append(email).append("\"");
		}
		return sb.toString();
	}
}