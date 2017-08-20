/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 被邀请人信息Entity
 * @author spring
 * @version 2017-08-17
 */
public class FriendInvitee extends DataEntity<FriendInvitee> {
	
	private static final long serialVersionUID = 1L;
	private String initiatorId;		// 发起人id
	private String initiatorOpenId;		// 发起人微信openid
	private String openid;		// 被邀请人微信openid
	private String nickname;		// 被邀请人昵称
	private String headimgurl;		// 被邀请人头像地址
	private String score;		// 得分
	private String status;		// 答题状态：0 未答完， 1 已打完
	private String remark;		// 备注
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private List<FriendAnswer> friendAnswerList = Lists.newArrayList();		// 子表列表
	
	public FriendInvitee() {
		super();
	}

	public FriendInvitee(String id){
		super(id);
	}

	@Length(min=1, max=64, message="发起人id长度必须介于 1 和 64 之间")
	public String getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}
	
	@Length(min=1, max=32, message="发起人微信openid长度必须介于 1 和 32 之间")
	public String getInitiatorOpenId() {
		return initiatorOpenId;
	}

	public void setInitiatorOpenId(String initiatorOpenId) {
		this.initiatorOpenId = initiatorOpenId;
	}
	
	@Length(min=1, max=32, message="被邀请人微信openid长度必须介于 1 和 32 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=128, message="被邀请人昵称长度必须介于 0 和 128 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=256, message="被邀请人头像地址长度必须介于 0 和 256 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=1, max=16, message="得分长度必须介于 1 和 16 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=1, max=16, message="答题状态：0 未答完， 1 已打完长度必须介于 1 和 16 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public List<FriendAnswer> getFriendAnswerList() {
		return friendAnswerList;
	}

	public void setFriendAnswerList(List<FriendAnswer> friendAnswerList) {
		this.friendAnswerList = friendAnswerList;
	}
}