/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.friend.entity.FriendInitiator;

/**
 * 发起人信息DAO接口
 * @author shuai
 * @version 2017-08-17
 */
@MyBatisDao
public interface FriendInitiatorDao extends CrudDao<FriendInitiator> {
	
}