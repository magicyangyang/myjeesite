/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.huli.entity.WechatLog;
import com.thinkgem.jeesite.modules.huli.service.WechatLogService;

/**
 * 微信日志管理Controller
 * @author spring
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/wechatLog")
public class WechatLogController extends BaseController {

	@Autowired
	private WechatLogService wechatLogService;
	
	@ModelAttribute
	public WechatLog get(@RequestParam(required=false) String id) {
		WechatLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wechatLogService.get(id);
		}
		if (entity == null){
			entity = new WechatLog();
		}
		return entity;
	}
	
	@RequiresPermissions("huli:wechatLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(WechatLog wechatLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WechatLog> page = wechatLogService.findPage(new Page<WechatLog>(request, response), wechatLog); 
		model.addAttribute("page", page);
		return "modules/huli/wechatLogList";
	}

	@RequiresPermissions("huli:wechatLog:view")
	@RequestMapping(value = "form")
	public String form(WechatLog wechatLog, Model model) {
		model.addAttribute("wechatLog", wechatLog);
		return "modules/huli/wechatLogForm";
	}

	@RequiresPermissions("huli:wechatLog:edit")
	@RequestMapping(value = "save")
	public String save(WechatLog wechatLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wechatLog)){
			return form(wechatLog, model);
		}
		wechatLogService.save(wechatLog);
		addMessage(redirectAttributes, "保存微信登录日志成功");
		return "redirect:"+Global.getAdminPath()+"/huli/wechatLog/?repage";
	}
	
	@RequiresPermissions("huli:wechatLog:edit")
	@RequestMapping(value = "delete")
	public String delete(WechatLog wechatLog, RedirectAttributes redirectAttributes) {
		wechatLogService.delete(wechatLog);
		addMessage(redirectAttributes, "删除微信登录日志成功");
		return "redirect:"+Global.getAdminPath()+"/huli/wechatLog/?repage";
	}
	
	@RequestMapping(value = {"jsonlist", "json"})
	@ResponseBody
	public Page<WechatLog> jsonlist(WechatLog wechatLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		return wechatLogService.findPage(new Page<WechatLog>(request, response), wechatLog); 
	}

}