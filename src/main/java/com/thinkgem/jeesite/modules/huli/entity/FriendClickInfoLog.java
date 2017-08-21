/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 狐狸日志管理Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendClickInfoLog extends DataEntity<FriendClickInfoLog> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 微信openid
	private Long uid;		// 操作人
	private String mobile;		// 用户电话号码
	private String type;		// 操作类型
	private String result;		// 操作结果
	private String remark;		// 备注
	private Integer campaign;		// 活动标识
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// 更新时间
	
	public FriendClickInfoLog() {
		super();
	}

	public FriendClickInfoLog(String id){
		super(id);
	}

	@Length(min=0, max=100, message="微信openid长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	@Length(min=0, max=32, message="用户电话号码长度必须介于 0 和 32 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=100, message="操作类型长度必须介于 0 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="操作结果长度必须介于 0 和 200 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getCampaign() {
		return campaign;
	}

	public void setCampaign(Integer campaign) {
		this.campaign = campaign;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	 
	
}