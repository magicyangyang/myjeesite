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
import com.thinkgem.jeesite.modules.huli.entity.FriendTask;
import com.thinkgem.jeesite.modules.huli.dao.FriendTaskDao;

/**
 * 任务管理Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendTaskService extends CrudService<FriendTaskDao, FriendTask> {

	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected FriendTaskDao dao;
	
	public FriendTask get(String id) {
		return super.get(id);
	}
	
	public List<FriendTask> findList(FriendTask friendTask) {
		return super.findList(friendTask);
	}
	
	public Page<FriendTask> findPage(Page<FriendTask> page, FriendTask friendTask) {
		return super.findPage(page, friendTask);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendTask friendTask) {
		super.save(friendTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendTask friendTask) {
		super.delete(friendTask);
	}

	public List<FriendTask> getTasksByInviteOpenid(String inviteOpenid) {
		return dao.getTasksByInviteOpenid(inviteOpenid);
	}
	
}