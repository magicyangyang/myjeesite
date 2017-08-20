/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;

/**
 * 邀请关系DAO接口
 * @author shuai
 * @version 2017-08-20
 */
@MyBatisDao
public interface FriendShipDao extends CrudDao<FriendShip> {

	FriendShip getShipByOpenid(String taskOpenid, String inviteOpenid);

	List<FriendShip> getShipByTaskOpenid(String openid);

	List<FriendShip> getShipByInviteOpenid(String openid);
	
}