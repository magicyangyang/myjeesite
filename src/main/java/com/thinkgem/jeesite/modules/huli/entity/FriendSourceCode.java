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
 * 狐狸兑换码资源管理Entity
 * @author shuai
 * @version 2017-08-22
 */
public class FriendSourceCode extends DataEntity<FriendSourceCode> {
	
	private static final long serialVersionUID = 1L;
	private String actId;		// 活动名称
	private String openid;		// 发起人openid
	private String channelName;		// 渠道标识
	private String code;		// 兑换码
	private Integer score;		// 得分
	private Integer status;		// 资源状态 0 没有赠送过，1 已经赠送过
	private Integer type;		// 资源类型,用来区分是激活码,还是红包等其它资源
	private Integer amount;		// 红包金额 单位：元
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// 更新时间
	
	public FriendSourceCode() {
		super();
	}

	public FriendSourceCode(String id){
		super(id);
	}

	@Length(min=1, max=128, message="活动名称长度必须介于 1 和 128 之间")
	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	
	@Length(min=0, max=64, message="发起人openid长度必须介于 0 和 64 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=1, max=128, message="渠道标识长度必须介于 1 和 128 之间")
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@Length(min=1, max=32, message="兑换码长度必须介于 1 和 32 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@NotNull(message="得分不能为空")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@NotNull(message="资源状态 0 没有赠送过，1 已经赠送过不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@NotNull(message="资源类型,用来区分是激活码,还是红包等其它资源不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@NotNull(message="红包金额 单位：元不能为空")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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