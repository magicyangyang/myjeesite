/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;

/**
 * 用户信息管理DAO接口
 * @author shuai
 * @version 2017-08-20
 */
@MyBatisDao
public interface FriendWechatLogDao extends CrudDao<FriendWechatLog> {

	FriendWechatLog getByOpenid(@Param("openid")String openid);
	
}