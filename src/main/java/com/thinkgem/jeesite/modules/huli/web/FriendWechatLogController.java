/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.service.FriendWechatLogService;

/**
 * 用户信息管理Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/friendWechatLog")
public class FriendWechatLogController extends BaseController {

	@Autowired
	private FriendWechatLogService friendWechatLogService;
	
	@ModelAttribute
	public FriendWechatLog get(@RequestParam(required=false) String id) {
		FriendWechatLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendWechatLogService.get(id);
		}
		if (entity == null){
			entity = new FriendWechatLog();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = {"list", ""})
	public String list(FriendWechatLog friendWechatLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendWechatLog> page = friendWechatLogService.findPage(new Page<FriendWechatLog>(request, response), friendWechatLog); 
		model.addAttribute("page", page);
		return "modules/huli/friendWechatLogList";
	}

	@ResponseBody
	@RequestMapping(value = "form")
	public String form(FriendWechatLog friendWechatLog, Model model) {
		model.addAttribute("friendWechatLog", friendWechatLog);
		return "modules/huli/friendWechatLogForm";
	}
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(FriendWechatLog friendWechatLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendWechatLog)){
			return form(friendWechatLog, model);
		}
		friendWechatLogService.save(friendWechatLog);
		addMessage(redirectAttributes, "保存用户信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/huli/friendWechatLog/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(FriendWechatLog friendWechatLog, RedirectAttributes redirectAttributes) {
		friendWechatLogService.delete(friendWechatLog);
		addMessage(redirectAttributes, "删除用户信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/huli/friendWechatLog/?repage";
	}

}