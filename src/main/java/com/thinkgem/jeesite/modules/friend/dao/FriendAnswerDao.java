/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.friend.entity.FriendAnswer;

/**
 * 被邀请人信息DAO接口
 * @author spring
 * @version 2017-08-17
 */
@MyBatisDao
public interface FriendAnswerDao extends CrudDao<FriendAnswer> {
	
}