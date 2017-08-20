/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.friend.dao.FriendInitiatorDao;
import com.thinkgem.jeesite.modules.friend.entity.FriendInitiator;

/**
 * 发起人信息Service
 * @author shuai
 * @version 2017-08-17
 */
@Service
@Transactional(readOnly = true)
public class FriendInitiatorService extends CrudService<FriendInitiatorDao, FriendInitiator> {

	public FriendInitiator get(String id) {
		return super.get(id);
	}
	
	public List<FriendInitiator> findList(FriendInitiator friendInitiator) {
		return super.findList(friendInitiator);
	}
	
	public Page<FriendInitiator> findPage(Page<FriendInitiator> page, FriendInitiator friendInitiator) {
		return super.findPage(page, friendInitiator);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendInitiator friendInitiator) {
		if (friendInitiator.getIsNewRecord()){
			friendInitiator.setId(IdGen.uuid());
			friendInitiator.setCreateTime(new Date());
			friendInitiator.setUpdateTime(new Date());
			dao.insert(friendInitiator);
		}else{
			friendInitiator.setUpdateTime(new Date());
			dao.update(friendInitiator);
		}
		//super.save(friendInitiator);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendInitiator friendInitiator) {
		super.delete(friendInitiator);
	}
	
}