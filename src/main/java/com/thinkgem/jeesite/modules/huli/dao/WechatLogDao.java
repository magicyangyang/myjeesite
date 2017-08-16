/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huli.entity.WechatLog;

/**
 * 微信日志管理DAO接口
 * @author spring
 * @version 2017-08-13
 */
@MyBatisDao
public interface WechatLogDao extends CrudDao<WechatLog> {
	
}