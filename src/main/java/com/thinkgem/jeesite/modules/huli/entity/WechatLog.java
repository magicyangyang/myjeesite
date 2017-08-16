/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信日志管理Entity
 * @author spring
 * @version 2017-08-13
 */
public class WechatLog extends DataEntity<WechatLog> {
	
	private static final long serialVersionUID = 1L;
	private Long uid;		// 搜易贷用户uid
	private String nickName;		// 昵称
	private String openid;		// 微信账号标识
	private String gender;		// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province;		// 用户个人资料填写的省份
	private String city;		// 普通用户个人资料填写的城市
	private String country;		// 国家，如中国为CN
	private String headimgurl;		// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String privilege;		// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String isSubscribe;		// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private Long subscribeTime;		// 关注时间
	private String language;		// 用户使用语言区域
	private String unionId;		// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String remark;		// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private String groupId;		// 用户所在的分组ID
	private Date loginTime;		// 登录时间
	
	public WechatLog() {
		super();
	}

	public WechatLog(String id){
		super(id);
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
	
	@Length(min=0, max=2, message="用户的性别，值为1时是男性，值为2时是女性，值为0时是未知长度必须介于 0 和 2 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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
	
	@Length(min=0, max=2, message="用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。长度必须介于 0 和 2 之间")
	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	
	@Length(min=0, max=20, message="用户使用语言区域长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=10, message="用户所在的分组ID长度必须介于 0 和 10 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
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