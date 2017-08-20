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
 * 任务管理Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendTask extends DataEntity<FriendTask> {
	
	private static final long serialVersionUID = 1L;
	private String inviteOpenId;		// invite_open_id
	private String taskOpenId;		// 邀请人微信id
	private String taskNickname;		// task_nickname
	private String taskHeadimgurl;		// 被邀请人微信id
	private Integer questionA;		// 问题1
	private Integer answerA;		// 答案1
	private Integer questionB;		// 问题2
	private Integer answerB;		// 答案2
	private Integer questionC;		// 问题3
	private Integer answerC;		// 答案3
	private Integer score;		// 当前答完题目所得分数(每道题1分)
	private Integer status;		// 答题状态,看是否已经结束。0为正在答题，1为结束(结束代表从abc三题顺序answer为0情况，若c题为1也是结束)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// 修改时间
	
	public FriendTask() {
		super();
	}

	public FriendTask(String id){
		super(id);
	}

	public String getInviteOpenId() {
		return inviteOpenId;
	}

	public void setInviteOpenId(String inviteOpenId) {
		this.inviteOpenId = inviteOpenId;
	}
	
	@Length(min=1, max=255, message="邀请人微信id长度必须介于 1 和 255 之间")
	public String getTaskOpenId() {
		return taskOpenId;
	}

	public void setTaskOpenId(String taskOpenId) {
		this.taskOpenId = taskOpenId;
	}
	
	@Length(min=0, max=255, message="task_nickname长度必须介于 0 和 255 之间")
	public String getTaskNickname() {
		return taskNickname;
	}

	public void setTaskNickname(String taskNickname) {
		this.taskNickname = taskNickname;
	}
	
	@Length(min=1, max=255, message="被邀请人微信id长度必须介于 1 和 255 之间")
	public String getTaskHeadimgurl() {
		return taskHeadimgurl;
	}

	public void setTaskHeadimgurl(String taskHeadimgurl) {
		this.taskHeadimgurl = taskHeadimgurl;
	}
	
	@NotNull(message="问题1不能为空")
	public Integer getQuestionA() {
		return questionA;
	}

	public void setQuestionA(Integer questionA) {
		this.questionA = questionA;
	}
	
	@NotNull(message="答案1不能为空")
	public Integer getAnswerA() {
		return answerA;
	}

	public void setAnswerA(Integer answerA) {
		this.answerA = answerA;
	}
	
	@NotNull(message="问题2不能为空")
	public Integer getQuestionB() {
		return questionB;
	}

	public void setQuestionB(Integer questionB) {
		this.questionB = questionB;
	}
	
	@NotNull(message="答案2不能为空")
	public Integer getAnswerB() {
		return answerB;
	}

	public void setAnswerB(Integer answerB) {
		this.answerB = answerB;
	}
	
	@NotNull(message="问题3不能为空")
	public Integer getQuestionC() {
		return questionC;
	}

	public void setQuestionC(Integer questionC) {
		this.questionC = questionC;
	}
	
	@NotNull(message="答案3不能为空")
	public Integer getAnswerC() {
		return answerC;
	}

	public void setAnswerC(Integer answerC) {
		this.answerC = answerC;
	}
	
	@NotNull(message="当前答完题目所得分数(每道题1分)不能为空")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@NotNull(message="答题状态,看是否已经结束。0为正在答题，1为结束(结束代表从abc三题顺序answer为0情况，若c题为1也是结束)不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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