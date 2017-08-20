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
 * 被邀请人信息Entity
 * @author spring
 * @version 2017-08-17
 */
public class FriendAnswer extends DataEntity<FriendAnswer> {
	
	private static final long serialVersionUID = 1L;
	private FriendInvitee inviteeId;		// invitee_id 父类
	private String initiatorOpenId;		// 邀请人微信id
	private String inviteeOpenId;		// 被邀请人微信id
	private String questionA;		// 问题1
	private String answerA;		// 答案1
	private String questionB;		// 问题2
	private String answerB;		// 答案2
	private String questionC;		// 问题3
	private String answerC;		// 答案3
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public FriendAnswer() {
		super();
	}

	public FriendAnswer(String id){
		super(id);
	}

	public FriendAnswer(FriendInvitee inviteeId){
		this.inviteeId = inviteeId;
	}

	@Length(min=1, max=64, message="invitee_id长度必须介于 1 和 64 之间")
	public FriendInvitee getInviteeId() {
		return inviteeId;
	}

	public void setInviteeId(FriendInvitee inviteeId) {
		this.inviteeId = inviteeId;
	}
	
	@Length(min=1, max=255, message="邀请人微信id长度必须介于 1 和 255 之间")
	public String getInitiatorOpenId() {
		return initiatorOpenId;
	}

	public void setInitiatorOpenId(String initiatorOpenId) {
		this.initiatorOpenId = initiatorOpenId;
	}
	
	@Length(min=1, max=255, message="被邀请人微信id长度必须介于 1 和 255 之间")
	public String getInviteeOpenId() {
		return inviteeOpenId;
	}

	public void setInviteeOpenId(String inviteeOpenId) {
		this.inviteeOpenId = inviteeOpenId;
	}
	
	@Length(min=1, max=4, message="问题1长度必须介于 1 和 4 之间")
	public String getQuestionA() {
		return questionA;
	}

	public void setQuestionA(String questionA) {
		this.questionA = questionA;
	}
	
	@Length(min=1, max=4, message="答案1长度必须介于 1 和 4 之间")
	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	
	@Length(min=1, max=4, message="问题2长度必须介于 1 和 4 之间")
	public String getQuestionB() {
		return questionB;
	}

	public void setQuestionB(String questionB) {
		this.questionB = questionB;
	}
	
	@Length(min=1, max=4, message="答案2长度必须介于 1 和 4 之间")
	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	
	@Length(min=1, max=4, message="问题3长度必须介于 1 和 4 之间")
	public String getQuestionC() {
		return questionC;
	}

	public void setQuestionC(String questionC) {
		this.questionC = questionC;
	}
	
	@Length(min=1, max=4, message="答案3长度必须介于 1 和 4 之间")
	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
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
	@NotNull(message="修改时间不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}