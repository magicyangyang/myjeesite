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
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.dao.FriendShipDao;

/**
 * 邀请关系Service
 * @author shuai
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class FriendShipService extends CrudService<FriendShipDao, FriendShip> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected FriendShipDao dao;
	
	
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

	public String saveShip(FriendWechatLog taskUser, FriendWechatLog inviteUser) {
		 FriendShip relationShip = dao.getShipByOpenid(taskUser.getOpenid(),inviteUser.getOpenid());
		 if(null==relationShip){
			 FriendShip friendShip = new FriendShip();
			 friendShip.setInviteOpenId(inviteUser.getOpenid());
			 friendShip.setTaskOpenId(taskUser.getOpenid());
			 friendShip.setTaskHeadimgurl(taskUser.getHeadimgurl());
			 friendShip.setTaskNickname(taskUser.getNickName());
			 friendShip.setRemark("");
			 try {
				 save(friendShip);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return e.getMessage();
			}
			 return null;
		 }
		return "已经存在邀请关系";
	}
	
	public List<FriendShip> getShipByTaskOpenid(String openid) {
		return dao.getShipByTaskOpenid(openid);
	}
	public List<FriendShip> getShipByInviteOpenid(String openid) {
		return dao.getShipByInviteOpenid(openid);
	}
	
	
}