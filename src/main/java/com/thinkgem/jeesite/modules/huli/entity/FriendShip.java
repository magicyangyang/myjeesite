/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 邀请关系Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendShip extends DataEntity<FriendShip> {
	
	private static final long serialVersionUID = 1L;
	private String inviteOpenId;		// 发起人id
	private String taskOpenId;		// 被邀请人微信openid
	private String taskNickname;		// 被邀请人昵称
	private String taskHeadimgurl;		// 被邀请人头像地址
	private String remark;		// 备注
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public FriendShip() {
		super();
	}

	public FriendShip(String id){
		super(id);
	}

	@Length(min=1, max=64, message="发起人id长度必须介于 1 和 64 之间")
	public String getInviteOpenId() {
		return inviteOpenId;
	}

	public void setInviteOpenId(String inviteOpenId) {
		this.inviteOpenId = inviteOpenId;
	}
	
	@Length(min=1, max=32, message="被邀请人微信openid长度必须介于 1 和 32 之间")
	public String getTaskOpenId() {
		return taskOpenId;
	}

	public void setTaskOpenId(String taskOpenId) {
		this.taskOpenId = taskOpenId;
	}
	
	@Length(min=0, max=128, message="被邀请人昵称长度必须介于 0 和 128 之间")
	public String getTaskNickname() {
		return taskNickname;
	}

	public void setTaskNickname(String taskNickname) {
		this.taskNickname = taskNickname;
	}
	
	@Length(min=0, max=256, message="被邀请人头像地址长度必须介于 0 和 256 之间")
	public String getTaskHeadimgurl() {
		return taskHeadimgurl;
	}

	public void setTaskHeadimgurl(String taskHeadimgurl) {
		this.taskHeadimgurl = taskHeadimgurl;
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