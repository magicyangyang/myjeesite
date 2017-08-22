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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.huli.entity.FriendSourceCode;
import com.thinkgem.jeesite.modules.huli.service.FriendSourceCodeService;

/**
 * 狐狸兑换码资源管理Controller
 * @author shuai
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/friendSourceCode")
public class FriendSourceCodeController extends BaseController {

	@Autowired
	private FriendSourceCodeService friendSourceCodeService;
	
	@ModelAttribute
	public FriendSourceCode get(@RequestParam(required=false) String id) {
		FriendSourceCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendSourceCodeService.get(id);
		}
		if (entity == null){
			entity = new FriendSourceCode();
		}
		return entity;
	}
	
	@RequiresPermissions("huli:friendSourceCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(FriendSourceCode friendSourceCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendSourceCode> page = friendSourceCodeService.findPage(new Page<FriendSourceCode>(request, response), friendSourceCode); 
		model.addAttribute("page", page);
		return "modules/huli/friendSourceCodeList";
	}

	@RequiresPermissions("huli:friendSourceCode:view")
	@RequestMapping(value = "form")
	public String form(FriendSourceCode friendSourceCode, Model model) {
		model.addAttribute("friendSourceCode", friendSourceCode);
		return "modules/huli/friendSourceCodeForm";
	}

	@RequiresPermissions("huli:friendSourceCode:edit")
	@RequestMapping(value = "save")
	public String save(FriendSourceCode friendSourceCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendSourceCode)){
			return form(friendSourceCode, model);
		}
		friendSourceCodeService.save(friendSourceCode);
		addMessage(redirectAttributes, "保存资源成功");
		return "redirect:"+Global.getAdminPath()+"/huli/friendSourceCode/?repage";
	}
	
	@RequiresPermissions("huli:friendSourceCode:edit")
	@RequestMapping(value = "delete")
	public String delete(FriendSourceCode friendSourceCode, RedirectAttributes redirectAttributes) {
		friendSourceCodeService.delete(friendSourceCode);
		addMessage(redirectAttributes, "删除资源成功");
		return "redirect:"+Global.getAdminPath()+"/huli/friendSourceCode/?repage";
	}

}