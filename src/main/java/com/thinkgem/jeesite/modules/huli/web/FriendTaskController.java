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
import com.thinkgem.jeesite.modules.huli.entity.FriendTask;
import com.thinkgem.jeesite.modules.huli.service.FriendTaskService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 任务管理Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/friendTask")
public class FriendTaskController extends BaseController {

	@Autowired
	private FriendTaskService friendTaskService;
	
	@ModelAttribute
	public FriendTask get(@RequestParam(required=false) String id) {
		FriendTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendTaskService.get(id);
		}
		if (entity == null){
			entity = new FriendTask();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = {"list", ""})
	public String list(FriendTask friendTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendTask> page = friendTaskService.findPage(new Page<FriendTask>(request, response), friendTask); 
		return JsonResponseUtil.ok(page);
	}

	@ResponseBody
	@RequestMapping(value = "form")
	public String form(FriendTask friendTask, Model model) {
		FriendTask entity = null;
		if (null!=friendTask){
			entity = friendTaskService.get(friendTask);
		}
		if (entity == null){
			entity = new FriendTask();
		}
		return JsonResponseUtil.ok(friendTask);
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public String save(FriendTask friendTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendTask)){
			return form(friendTask, model);
		}
		friendTaskService.save(friendTask);
		return JsonResponseUtil.ok("保存任务保存成功成功");
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(FriendTask friendTask, RedirectAttributes redirectAttributes) {
		friendTaskService.delete(friendTask);
		return JsonResponseUtil.ok("删除任务保存成功成功");
	}

}