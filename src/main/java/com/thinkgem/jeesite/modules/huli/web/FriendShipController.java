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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;
import com.thinkgem.jeesite.modules.huli.service.FriendShipService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 邀请关系Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/friendShip")
public class FriendShipController extends BaseController {

	@Autowired
	private FriendShipService friendShipService;
	
	@ModelAttribute
	public FriendShip get(@RequestParam(required=false) String id) {
		FriendShip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendShipService.get(id);
		}
		if (entity == null){
			entity = new FriendShip();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	@ResponseBody
	public String list(FriendShip friendShip, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendShip> page = friendShipService.findPage(new Page<FriendShip>(request, response), friendShip); 
		model.addAttribute("page", page);
		return JsonResponseUtil.ok(page);
	}

	@RequestMapping(value = "form")
	@ResponseBody
	public String form(FriendShip friendShip, Model model) {
		model.addAttribute("friendShip", friendShip);
		return JsonResponseUtil.ok(friendShip);
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public String save(FriendShip friendShip, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendShip)){
			return form(friendShip, model);
		}
		friendShipService.save(friendShip);
		return JsonResponseUtil.ok("保存邀请关系成功");
	}
	
	 

}