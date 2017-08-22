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
import com.thinkgem.jeesite.modules.huli.entity.FriendSourceCode;
import com.thinkgem.jeesite.modules.huli.service.FriendSourceCodeService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 狐狸兑换码资源管理Controller
 * @author shuai
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/huli/scode")
public class FriendSourceCodeController extends BaseController {

	@Autowired
	private FriendSourceCodeService friendSourceCodeService;
	
	@RequestMapping(value ="list")
	public @ResponseBody String list(FriendSourceCode friendSourceCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		return JsonResponseUtil.ok(friendSourceCodeService.findPage(new Page<FriendSourceCode>(request, response), friendSourceCode)); 
	}

	@RequestMapping(value = "save")
	public @ResponseBody String save(FriendSourceCode friendSourceCode, Model model, RedirectAttributes redirectAttributes) {
		friendSourceCodeService.save(friendSourceCode);
		return JsonResponseUtil.ok("保存资源成功");
	}
	
	@RequestMapping(value = "")
	public @ResponseBody String code(String openid) {
		FriendSourceCode entity = null;
		if (StringUtils.isNotBlank(openid)){
			entity = friendSourceCodeService.getInfobyOpenid(openid);
		}
		if (entity == null){
			return JsonResponseUtil.badResult("您还未参加过此活动");
		}
		return JsonResponseUtil.ok(entity);
	}
 
}