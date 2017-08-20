/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 狐狸日志管理Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendShareLog extends DataEntity<FriendShareLog> {
	
	private static final long serialVersionUID = 1L;
	private Long uid;		// 操作人
	private Date optTime;		// 操作时间
	private Integer type;		// 操作类型
	private String url;		// 操作页面url
	private Integer dateInt;		// 操作时间yyyyMMdd用于统计（按天）
	private Integer shareResult;		// 分享结果（1-成功 2-失败3-未知）
	private Integer comeFrom;		// 分享自&hellip;&hellip;（1-WAP 2-微信 3-App）
	private Integer campaign;		// 活动标识
	private String module;		// 操作模块
	private String remark;		// 备注
	private String openid;		// 用户微信id
	
	public FriendShareLog() {
		super();
	}

	public FriendShareLog(String id){
		super(id);
	}

	@NotNull(message="操作人不能为空")
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="操作时间不能为空")
	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	
	@NotNull(message="操作类型不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=1, max=128, message="操作页面url长度必须介于 1 和 128 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@NotNull(message="操作时间yyyyMMdd用于统计（按天）不能为空")
	public Integer getDateInt() {
		return dateInt;
	}

	public void setDateInt(Integer dateInt) {
		this.dateInt = dateInt;
	}
	
	@NotNull(message="分享结果（1-成功 2-失败3-未知）不能为空")
	public Integer getShareResult() {
		return shareResult;
	}

	public void setShareResult(Integer shareResult) {
		this.shareResult = shareResult;
	}
	
	@NotNull(message="分享自&hellip;&hellip;（1-WAP 2-微信 3-App）不能为空")
	public Integer getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}
	
	@NotNull(message="活动标识不能为空")
	public Integer getCampaign() {
		return campaign;
	}

	public void setCampaign(Integer campaign) {
		this.campaign = campaign;
	}
	
	@Length(min=1, max=64, message="操作模块长度必须介于 1 和 64 之间")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Length(min=1, max=256, message="备注长度必须介于 1 和 256 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=1, max=200, message="用户微信id长度必须介于 1 和 200 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}