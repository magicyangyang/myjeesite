/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.huli.entity.User;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.dao.FriendWechatLogDao;

/**
 * 用户信息管理Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendWechatLogService extends CrudService<FriendWechatLogDao, FriendWechatLog> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected FriendWechatLogDao dao;
	
	
	public FriendWechatLog get(String id) {
		return super.get(id);
	}
	
	public List<FriendWechatLog> findList(FriendWechatLog friendWechatLog) {
		return super.findList(friendWechatLog);
	}
	
	public Page<FriendWechatLog> findPage(Page<FriendWechatLog> page, FriendWechatLog friendWechatLog) {
		return super.findPage(page, friendWechatLog);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendWechatLog friendWechatLog) {
		super.save(friendWechatLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendWechatLog friendWechatLog) {
		super.delete(friendWechatLog);
	}

	public FriendWechatLog saveUser(String userjson) {
		User user = null;
		if(StringUtils.isNotBlank(userjson)){
			user = JSONObject.parseObject(userjson,User.class);
		}
		FriendWechatLog wechatlog = new FriendWechatLog(user);
		FriendWechatLog res = get(wechatlog.getOpenid());
		if(null!=res){
			 return res;
		}else{
			super.save(wechatlog);
		}
		res=get(wechatlog.getOpenid());
		return res;
	}

	public FriendWechatLog getByOpenid(String openid) {
		return dao.getByOpenid(openid);
	}
	
}