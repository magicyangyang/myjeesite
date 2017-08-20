/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.FriendInviter;
import com.thinkgem.jeesite.modules.huli.dao.FriendInviterDao;

/**
 * 发起人信息管理Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendInviterService extends CrudService<FriendInviterDao, FriendInviter> {

	public FriendInviter get(String id) {
		return super.get(id);
	}
	
	public List<FriendInviter> findList(FriendInviter friendInviter) {
		return super.findList(friendInviter);
	}
	
	public Page<FriendInviter> findPage(Page<FriendInviter> page, FriendInviter friendInviter) {
		return super.findPage(page, friendInviter);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendInviter friendInviter) {
		super.save(friendInviter);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendInviter friendInviter) {
		super.delete(friendInviter);
	}
	
}