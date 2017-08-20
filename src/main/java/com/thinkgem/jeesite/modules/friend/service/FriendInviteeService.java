/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.friend.entity.FriendInvitee;
import com.thinkgem.jeesite.modules.friend.dao.FriendInviteeDao;
import com.thinkgem.jeesite.modules.friend.entity.FriendAnswer;
import com.thinkgem.jeesite.modules.friend.dao.FriendAnswerDao;

/**
 * 被邀请人信息Service
 * @author spring
 * @version 2017-08-17
 */
@Service
@Transactional(readOnly = true)
public class FriendInviteeService extends CrudService<FriendInviteeDao, FriendInvitee> {

	@Autowired
	private FriendAnswerDao friendAnswerDao;
	
	public FriendInvitee get(String id) {
		FriendInvitee friendInvitee = super.get(id);
		friendInvitee.setFriendAnswerList(friendAnswerDao.findList(new FriendAnswer(friendInvitee)));
		return friendInvitee;
	}
	
	public List<FriendInvitee> findList(FriendInvitee friendInvitee) {
		return super.findList(friendInvitee);
	}
	
	public Page<FriendInvitee> findPage(Page<FriendInvitee> page, FriendInvitee friendInvitee) {
		return super.findPage(page, friendInvitee);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendInvitee friendInvitee) {
		super.save(friendInvitee);
		for (FriendAnswer friendAnswer : friendInvitee.getFriendAnswerList()){
			if (friendAnswer.getId() == null){
				continue;
			}
			if (FriendAnswer.DEL_FLAG_NORMAL.equals(friendAnswer.getDelFlag())){
				if (StringUtils.isBlank(friendAnswer.getId())){
					friendAnswer.setInviteeId(friendInvitee);
					friendAnswer.preInsert();
					friendAnswerDao.insert(friendAnswer);
				}else{
					friendAnswer.preUpdate();
					friendAnswerDao.update(friendAnswer);
				}
			}else{
				friendAnswerDao.delete(friendAnswer);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendInvitee friendInvitee) {
		super.delete(friendInvitee);
		friendAnswerDao.delete(new FriendAnswer(friendInvitee));
	}
	
}