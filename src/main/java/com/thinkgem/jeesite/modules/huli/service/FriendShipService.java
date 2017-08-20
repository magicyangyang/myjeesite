/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;
import com.thinkgem.jeesite.modules.huli.dao.FriendShipDao;

/**
 * 邀请关系Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendShipService extends CrudService<FriendShipDao, FriendShip> {

	public FriendShip get(String id) {
		return super.get(id);
	}
	
	public List<FriendShip> findList(FriendShip friendShip) {
		return super.findList(friendShip);
	}
	
	public Page<FriendShip> findPage(Page<FriendShip> page, FriendShip friendShip) {
		return super.findPage(page, friendShip);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendShip friendShip) {
		super.save(friendShip);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendShip friendShip) {
		super.delete(friendShip);
	}
	
}