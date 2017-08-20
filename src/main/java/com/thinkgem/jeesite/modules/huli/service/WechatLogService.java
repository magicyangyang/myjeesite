/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huli.entity.WechatLog;
import com.thinkgem.jeesite.modules.huli.dao.WechatLogDao;

/**
 * 微信日志管理Service
 * @author spring
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class WechatLogService extends CrudService<WechatLogDao, WechatLog> {

	public WechatLog get(String id) {
		return super.get(id);
	}
	
	public List<WechatLog> findList(WechatLog wechatLog) {
		return super.findList(wechatLog);
	}
	
	public Page<WechatLog> findPage(Page<WechatLog> page, WechatLog wechatLog) {
		return super.findPage(page, wechatLog);
	}
	
	@Transactional(readOnly = false)
	public void save(WechatLog wechatLog) {
		super.save(wechatLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(WechatLog wechatLog) {
		super.delete(wechatLog);
	}
	
}