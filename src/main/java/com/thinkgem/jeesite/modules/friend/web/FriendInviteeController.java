/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.friend.entity.FriendInvitee;
import com.thinkgem.jeesite.modules.friend.service.FriendInviteeService;

/**
 * 被邀请人信息Controller
 * @author spring
 * @version 2017-08-17
 */
@Controller
@RequestMapping(value = "friend/invitee")
public class FriendInviteeController extends BaseController {

	@Autowired
	private FriendInviteeService friendInviteeService;
	
	@ModelAttribute
	public FriendInvitee get(@RequestParam(required=false) String id) {
		FriendInvitee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendInviteeService.get(id);
		}
		if (entity == null){
			entity = new FriendInvitee();
		}
		return entity;
	}
	
	@RequiresPermissions("friend:friendInvitee:view")
	@RequestMapping(value = {"list", ""})
	public String list(FriendInvitee friendInvitee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendInvitee> page = friendInviteeService.findPage(new Page<FriendInvitee>(request, response), friendInvitee); 
		model.addAttribute("page", page);
		return "modules/friend/friendInviteeList";
	}

	@RequiresPermissions("friend:friendInvitee:view")
	@RequestMapping(value = "form")
	public String form(FriendInvitee friendInvitee, Model model) {
		model.addAttribute("friendInvitee", friendInvitee);
		return "modules/friend/friendInviteeForm";
	}

	@RequiresPermissions("friend:friendInvitee:edit")
	@RequestMapping(value = "save")
	public String save(FriendInvitee friendInvitee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendInvitee)){
			return form(friendInvitee, model);
		}
		friendInviteeService.save(friendInvitee);
		addMessage(redirectAttributes, "保存被邀请人成功");
		return "redirect:"+Global.getAdminPath()+"/friend/friendInvitee/?repage";
	}
	
	@RequiresPermissions("friend:friendInvitee:edit")
	@RequestMapping(value = "delete")
	public String delete(FriendInvitee friendInvitee, RedirectAttributes redirectAttributes) {
		friendInviteeService.delete(friendInvitee);
		addMessage(redirectAttributes, "删除被邀请人成功");
		return "redirect:"+Global.getAdminPath()+"/friend/friendInvitee/?repage";
	}

}