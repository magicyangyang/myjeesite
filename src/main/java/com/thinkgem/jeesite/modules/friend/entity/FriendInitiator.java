/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 发起人信息Entity
 * @author shuai
 * @version 2017-08-17
 */
public class FriendInitiator extends DataEntity<FriendInitiator> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 发起人微信openid
	private String status;		// 活动状态 0 开始活动，1 朋友答题结束
	private String isSendmoney;		// 是否已经给好友分钱
	private String inviteeNum;		// 有效被邀请人数量
	private String score;		// 总得分
	private String code;		// 兑换码
	private String remark;		// 备注
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public FriendInitiator() {
		super();
	}

	public FriendInitiator(String id){
		super(id);
	}

	@Length(min=0, max=32, message="发起人微信openid长度必须介于 0 和 32 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=1, max=2, message="活动状态 0 开始活动，1 朋友答题结束长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=2, message="是否已经给好友分钱长度必须介于 1 和 2 之间")
	public String getIsSendmoney() {
		return isSendmoney;
	}

	public void setIsSendmoney(String isSendmoney) {
		this.isSendmoney = isSendmoney;
	}
	
	@Length(min=1, max=16, message="有效被邀请人数量长度必须介于 1 和 16 之间")
	public String getInviteeNum() {
		return inviteeNum;
	}

	public void setInviteeNum(String inviteeNum) {
		this.inviteeNum = inviteeNum;
	}
	
	@Length(min=1, max=16, message="总得分长度必须介于 1 和 16 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=32, message="兑换码长度必须介于 0 和 32 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=128, message="备注长度必须介于 0 和 128 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="更新时间不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}