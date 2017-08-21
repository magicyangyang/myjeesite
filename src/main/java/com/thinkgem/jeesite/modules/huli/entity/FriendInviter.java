/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 发起人信息管理Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendInviter extends DataEntity<FriendInviter> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 发起人微信openid
	private Integer status;		// 活动状态 0 开始活动，1 朋友答题结束
	private Integer isSendmoney;		// 是否已经给好友分钱
	private Integer inviteNum;		// 有效被邀请人数量
	private Integer score;		// 总得分
	private String code;		// 兑换码
	private String remark;		// 备注
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// 更新时间
	
	public FriendInviter() {
		super();
	}

	public FriendInviter(String id){
		super(id);
	}

	@Length(min=0, max=32, message="发起人微信openid长度必须介于 0 和 32 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@NotNull(message="活动状态 0 开始活动，1 朋友答题结束不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@NotNull(message="是否已经给好友分钱不能为空")
	public Integer getIsSendmoney() {
		return isSendmoney;
	}

	public void setIsSendmoney(Integer isSendmoney) {
		this.isSendmoney = isSendmoney;
	}
	
	@NotNull(message="有效被邀请人数量不能为空")
	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}
	
	@NotNull(message="总得分不能为空")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
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