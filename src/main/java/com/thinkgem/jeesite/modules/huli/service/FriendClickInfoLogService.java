/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.FriendClickInfoLog;
import com.thinkgem.jeesite.modules.huli.dao.FriendClickInfoLogDao;
import com.thinkgem.jeesite.modules.huli.dao.FriendShipDao;

/**
 * 狐狸日志管理Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendClickInfoLogService extends CrudService<FriendClickInfoLogDao, FriendClickInfoLog> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected FriendClickInfoLogDao dao;
	
	public FriendClickInfoLog get(String id) {
		return super.get(id);
	}
	
	public List<FriendClickInfoLog> findList(FriendClickInfoLog friendClickInfoLog) {
		return super.findList(friendClickInfoLog);
	}
	
	public Page<FriendClickInfoLog> findPage(Page<FriendClickInfoLog> page, FriendClickInfoLog friendClickInfoLog) {
		return super.findPage(page, friendClickInfoLog);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendClickInfoLog friendClickInfoLog) {
		super.save(friendClickInfoLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendClickInfoLog friendClickInfoLog) {
		super.delete(friendClickInfoLog);
	}

	public FriendClickInfoLog getClickInfoByOpenidAndType(String openid, int type) {
		return dao.getClickInfoByOpenidAndType(openid,type);
	}
	
}