/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huli.entity.FriendSourceCode;

/**
 * 狐狸兑换码资源管理DAO接口
 * @author shuai
 * @version 2017-08-22
 */
@MyBatisDao
public interface FriendSourceCodeDao extends CrudDao<FriendSourceCode> {

	FriendSourceCode getInfobyOpenid(@Param("openid")String openid);

	FriendSourceCode getCodeInfoByUnUsedScore(@Param("score")int score);
	
}