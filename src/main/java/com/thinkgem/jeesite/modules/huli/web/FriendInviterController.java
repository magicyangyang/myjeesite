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
import com.thinkgem.jeesite.modules.huli.entity.FriendInviter;
import com.thinkgem.jeesite.modules.huli.service.FriendInviterService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 发起人信息管理Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/code")
public class FriendInviterController extends BaseController {

	@Autowired
	private FriendInviterService friendInviterService;
	
	@RequestMapping(value = "")
	@ResponseBody
	public String code(String openid, RedirectAttributes redirectAttributes) {
		return JsonResponseUtil.ok(friendInviterService.getInfoByOpenid(openid));
	}
	
	@ModelAttribute
	public FriendInviter get(@RequestParam(required=false) String id) {
		FriendInviter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendInviterService.get(id);
		}
		if (entity == null){
			entity = new FriendInviter();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list"})
	@ResponseBody
	public String list(FriendInviter friendInviter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendInviter> page = friendInviterService.findPage(new Page<FriendInviter>(request, response), friendInviter); 
		return  JsonResponseUtil.ok(page);
	}

	@RequestMapping(value = "form")
	@ResponseBody
	public String form(FriendInviter friendInviter, Model model) {
		return JsonResponseUtil.ok(friendInviter);
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public String save(FriendInviter friendInviter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendInviter)){
			return form(friendInviter, model);
		}
		friendInviterService.save(friendInviter);
		return JsonResponseUtil.ok("保存发起人信息保存成功成功");
	}
	
	 

}