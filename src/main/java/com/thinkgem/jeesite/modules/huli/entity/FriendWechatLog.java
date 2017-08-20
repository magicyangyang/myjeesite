/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息管理Entity
 * @author shuai
 * @version 2017-08-20
 */
public class FriendWechatLog extends DataEntity<FriendWechatLog> {
	
	private static final long serialVersionUID = 1L;
	private Long uid;		// 狐狸金服用户uid
	private String nickName;		// 昵称
	private String openid;		// 微信账号标识
	private Integer gender;		// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province;		// 用户个人资料填写的省份
	private String city;		// 普通用户个人资料填写的城市
	private String country;		// 国家，如中国为CN
	private String headimgurl;		// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String privilege;		// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private Integer isSubscribe;		// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private Long subscribeTime;		// 关注时间
	private String language;		// language
	private String unionId;		// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String remark;		// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private Integer groupId;		// 用户所在的分组ID
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;		// 登录时间
	
	public FriendWechatLog() {
		super();
	}

	public FriendWechatLog(String id){
		super(id);
	}
	
	public FriendWechatLog(String openId,boolean j){
		this.openid =openId;
	}

	public FriendWechatLog(User user) {
		this.uid = null;
		this.nickName = user.getNickName();
		this.openid = user.getOpenId();
		this.gender = user.getGender();
		this.province = user.getProvince();
		this.city = user.getCity();
		this.country = user.getCountry();
		this.headimgurl = user.getHeadimgurl();
		if(null!=user.getPrivilege()){
			this.privilege = user.getPrivilege().toJSONString();
		}
		this.isSubscribe = user.isSubscribe()==true?1:0;
		this.subscribeTime = user.getSubscribeTime();
		this.language = user.getLanguage();
		this.unionId = user.getUnionId();
		this.remark = user.getRemark();
		this.groupId = user.getGroupId();
		this.loginTime = new Date();
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	@Length(min=0, max=15, message="昵称长度必须介于 0 和 15 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=100, message="微信账号标识长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=50, message="用户个人资料填写的省份长度必须介于 0 和 50 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=50, message="普通用户个人资料填写的城市长度必须介于 0 和 50 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=50, message="国家，如中国为CN长度必须介于 0 和 50 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Length(min=0, max=2000, message="用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空长度必须介于 0 和 2000 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=0, max=1000, message="用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）长度必须介于 0 和 1000 之间")
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	
	@Length(min=0, max=20, message="language长度必须介于 0 和 20 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Length(min=0, max=50, message="只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段长度必须介于 0 和 50 之间")
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	@Length(min=0, max=200, message="公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注长度必须介于 0 和 200 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}