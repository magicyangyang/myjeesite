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
import com.thinkgem.jeesite.modules.huli.entity.FriendSourceCode;
import com.thinkgem.jeesite.modules.huli.dao.FriendShipDao;
import com.thinkgem.jeesite.modules.huli.dao.FriendSourceCodeDao;

/**
 * 狐狸兑换码资源管理Service
 * @author shuai
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class FriendSourceCodeService extends CrudService<FriendSourceCodeDao, FriendSourceCode> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected FriendSourceCodeDao  dao;
	
	public FriendSourceCode get(String id) {
		return super.get(id);
	}
	
	public List<FriendSourceCode> findList(FriendSourceCode friendSourceCode) {
		return super.findList(friendSourceCode);
	}
	
	public Page<FriendSourceCode> findPage(Page<FriendSourceCode> page, FriendSourceCode friendSourceCode) {
		return super.findPage(page, friendSourceCode);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendSourceCode friendSourceCode) {
		super.save(friendSourceCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendSourceCode friendSourceCode) {
		super.delete(friendSourceCode);
	}

	public FriendSourceCode getInfobyOpenid(String openid, int score) {
		return dao.getInfobyOpenid(openid);
	}
	
	@Transactional(readOnly = false)
	public String getCodeByScore(String openid, int score) {
		FriendSourceCode code = dao.getInfobyOpenid(openid);
		if(code==null){
			code=dao.getCodeInfoByUnUsedScore(score);
			code.setOpenid(openid);
			code.setIsNewRecord(true);
			code.setStatus(1);
			save(code);
		}
		return code.getCode();
	}

	public FriendSourceCode getInfobyOpenid(String openid) {
		return  dao.getInfobyOpenid(openid);
	}
	
}