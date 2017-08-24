/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.thinkgem.jeesite.modules.huli.entity.FriendShareLog;
import com.thinkgem.jeesite.modules.huli.service.FriendShareLogService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 狐狸日志管理Controller
 * 
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${huliPath}/shareinfo")
public class FriendShareLogController extends BaseController {

	@Autowired
	private FriendShareLogService friendShareLogService;

	@ResponseBody
	@RequestMapping(value = "")
	public String shareinfo(FriendShareLog shareLog, Model model, RedirectAttributes redirectAttributes) {
		if (shareLog == null || StringUtils.isBlank(shareLog.getOpenid())) {
			return JsonResponseUtil.badResult("openid不能为空");
		}
		logger.info("from={}|type={}result={}|remark={}|openid={}", shareLog.getComeFrom(), shareLog.getType(),
				shareLog.getShareResult(), shareLog.getRemark(), shareLog.getOpenid());
		try {
			shareLog.setUid(0L);
			shareLog.setOptTime(new Date());
			shareLog.setId(null);
			shareLog.setCampaign(0);
			shareLog.setDateInt(Integer.parseInt(DateFormatUtils.format(new Date(), "yyyyMMdd")));
			shareLog.setModule("name");
			if (null == shareLog.getRemark()) {
				shareLog.setRemark("狐狸活动");
			}
			friendShareLogService.save(shareLog);
		} catch (Exception e) {
			return JsonResponseUtil.badResult(e.getMessage());
		}
		return JsonResponseUtil.ok("保存日志保存成功");
	}

	@ModelAttribute
	public FriendShareLog get(@RequestParam(required = false) String id) {
		FriendShareLog entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = friendShareLogService.get(id);
		}
		if (entity == null) {
			entity = new FriendShareLog();
		}
		return entity;
	}

	@ResponseBody
	@RequestMapping(value = { "list" })
	public String list(FriendShareLog friendShareLog, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<FriendShareLog> page = friendShareLogService.findPage(new Page<FriendShareLog>(request, response),
				friendShareLog);
		return JsonResponseUtil.ok(page);
	}

	@ResponseBody
	@RequestMapping(value = "form")
	public String form(FriendShareLog friendShareLog, Model model) {
		return JsonResponseUtil.ok(friendShareLog);
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public String save(FriendShareLog friendShareLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, friendShareLog)) {
			return form(friendShareLog, model);
		}
		friendShareLogService.save(friendShareLog);
		return JsonResponseUtil.ok("保存日志保存成功");
	}

 
}