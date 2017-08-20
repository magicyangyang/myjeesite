/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.FriendShareLog;
import com.thinkgem.jeesite.modules.huli.dao.FriendShareLogDao;

/**
 * 狐狸日志管理Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendShareLogService extends CrudService<FriendShareLogDao, FriendShareLog> {

	public FriendShareLog get(String id) {
		return super.get(id);
	}
	
	public List<FriendShareLog> findList(FriendShareLog friendShareLog) {
		return super.findList(friendShareLog);
	}
	
	public Page<FriendShareLog> findPage(Page<FriendShareLog> page, FriendShareLog friendShareLog) {
		return super.findPage(page, friendShareLog);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendShareLog friendShareLog) {
		super.save(friendShareLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendShareLog friendShareLog) {
		super.delete(friendShareLog);
	}
	
}