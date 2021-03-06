/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendClickInfoLog;
import com.thinkgem.jeesite.modules.huli.service.FriendClickInfoLogService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 狐狸日志管理Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${huliPath}/clickinfo")
public class FriendClickInfoLogController extends BaseController {

	@Autowired
	private FriendClickInfoLogService friendClickInfoLogService;
	
	@RequestMapping(value = "")
	@ResponseBody
	public String clickinfo(FriendClickInfoLog viewclickLog, Model model, RedirectAttributes redirectAttributes) {
		 if (viewclickLog == null || StringUtils.isBlank(viewclickLog.getOpenid())) {
	            return JsonResponseUtil.badResult("openid不能为空");
	        }
	    logger.info("mobile={}|type={}result={}|remark={}|openid={}", viewclickLog.getMobile(), viewclickLog.getType(), viewclickLog.getResult(), viewclickLog.getRemark(), viewclickLog.getOpenid());
		try {
			Date startTime = new Date();
			FriendClickInfoLog clickLog = new FriendClickInfoLog();
			clickLog.setCreateTime(startTime);
			clickLog.setUpdateTime(startTime);
			clickLog.setMobile(viewclickLog.getMobile()==null?"":viewclickLog.getMobile());
			clickLog.setType(viewclickLog.getType());
			clickLog.setRemark(viewclickLog.getRemark()==null?"":viewclickLog.getMobile());
			clickLog.setResult(viewclickLog.getResult()==null?"1":viewclickLog.getMobile());
			clickLog.setOpenid(viewclickLog.getOpenid());
			clickLog.setCampaign(0);
	    	logger.info("save log mobile:{},openid:{}",viewclickLog.getOpenid(),viewclickLog.getOpenid());
			friendClickInfoLogService.save(clickLog);
		} catch (Exception e) {
			 return JsonResponseUtil.badResult(e.getMessage());
		}
		return JsonResponseUtil.ok("保存日志保存成功成功");
	}
	
	
	
	@ModelAttribute
	public FriendClickInfoLog get(@RequestParam(required=false) String id) {
		FriendClickInfoLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendClickInfoLogService.get(id);
		}
		if (entity == null){
			entity = new FriendClickInfoLog();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list"})
	@ResponseBody
	public String list(FriendClickInfoLog friendClickInfoLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendClickInfoLog> page = friendClickInfoLogService.findPage(new Page<FriendClickInfoLog>(request, response), friendClickInfoLog); 
		return JsonResponseUtil.ok(page);
	}

	@RequestMapping(value = "form")
	public String form(FriendClickInfoLog friendClickInfoLog, Model model) {
		FriendClickInfoLog friendClickInfoLogdb=friendClickInfoLogService.get(friendClickInfoLog);
		return JsonResponseUtil.ok(friendClickInfoLogdb);
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public String save(FriendClickInfoLog friendClickInfoLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendClickInfoLog)){
			return form(friendClickInfoLog, model);
		}
		friendClickInfoLogService.save(friendClickInfoLog);
		return JsonResponseUtil.ok("保存日志保存成功成功");
	}
	
	 
}