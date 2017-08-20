/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.friend.web;

import java.util.HashMap;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.friend.entity.FriendInitiator;
import com.thinkgem.jeesite.modules.friend.service.FriendInitiatorService;

/**
 * 发起人信息Controller
 * @author shuai
 * @version 2017-08-17
 */
@Controller
@RequestMapping(value = "friend/initiator")
public class FriendInitiatorController extends BaseController {

	@Autowired
	private FriendInitiatorService friendInitiatorService;
	
	@ModelAttribute
	public FriendInitiator get(@RequestParam(required=false) String id) {
		FriendInitiator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendInitiatorService.get(id);
		}
		if (entity == null){
			entity = new FriendInitiator();
		}
		return entity;
	}
	
	/*@RequiresPermissions("friend:friendInitiator:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(FriendInitiator friendInitiator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendInitiator> page = friendInitiatorService.findPage(new Page<FriendInitiator>(request, response), friendInitiator); 
		model.addAttribute("page", page);
		return "modules/friend/friendInitiatorList";
	}

	/*@RequiresPermissions("friend:friendInitiator:view")*/
	@RequestMapping(value = "form")
	public String form(FriendInitiator friendInitiator, Model model) {
		model.addAttribute("friendInitiator", friendInitiator);
		return "modules/friend/friendInitiatorForm";
	}

	/*@RequiresPermissions("friend:friendInitiator:edit")*/
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String,Object> save(FriendInitiator friendInitiator, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, friendInitiator)){
			return form(friendInitiator, model);
		}*/
		friendInitiatorService.save(friendInitiator);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code","200");
		result.put("message","保存发起人信息成功");
		return result;
		//addMessage(redirectAttributes, "保存发起人信息成功");
		//return "redirect:"+Global.getAdminPath()+"/friend/friendInitiator/?repage";
	}
	
	/*@RequiresPermissions("friend:friendInitiator:edit")*/
	@RequestMapping(value = "delete")
	public String delete(FriendInitiator friendInitiator, RedirectAttributes redirectAttributes) {
		friendInitiatorService.delete(friendInitiator);
		addMessage(redirectAttributes, "删除发起人信息成功");
		return "redirect:"+Global.getAdminPath()+"/friend/friendInitiator/?repage";
	}

}