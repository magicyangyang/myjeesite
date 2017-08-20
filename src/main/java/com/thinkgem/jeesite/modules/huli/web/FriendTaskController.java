/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendTask;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.service.FriendTaskService;
import com.thinkgem.jeesite.modules.huli.service.FriendWechatLogService;
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
	
	@Autowired
	private FriendWechatLogService friendWechatLogService;
	
	/*@ModelAttribute
	public FriendTask get(@RequestParam(required=false) String id) {
		FriendTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = friendTaskService.get(id);
		}
		if (entity == null){
			entity = new FriendTask();
		}
		return entity;
	}*/
	@ModelAttribute
	public FriendTask get(String inviteOpenId, String taskOpenId) {
		FriendTask entity = null;
		if (StringUtils.isNotBlank(inviteOpenId) && StringUtils.isNotBlank(taskOpenId)){
			entity = friendTaskService.getByOpenids(inviteOpenId, taskOpenId);
		}
		if (entity == null){
			entity = new FriendTask();
			entity.setInviteOpenId(inviteOpenId);
			entity.setTaskOpenId(taskOpenId);
			entity.setQuestionA(1);
			entity.setQuestionB(2);
			entity.setQuestionC(3);
			entity.setAnswerA(0);
			entity.setAnswerB(0);
			entity.setAnswerC(0);
			entity.setScore(0);
			entity.setStatus(0);
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
	public String save(FriendTask friendTask, Integer question, Integer answer, Model model, RedirectAttributes redirectAttributes) {
		// 1. 校验
		if(StringUtils.isBlank(friendTask.getInviteOpenId())){
			return JsonResponseUtil.badResult(1, "发起人openid不能为空！");
		}
		
		if(StringUtils.isBlank(friendTask.getTaskOpenId())){
			return JsonResponseUtil.badResult(1, "被邀请人openid不能为空！");
		}
		
		if(question == null || question<1 || question>3){
			return JsonResponseUtil.badResult(1, "question参数传入错误！");
		}
		
		if(answer == null || answer<0 || answer>1){
			return JsonResponseUtil.badResult(1, "answer参数传入错误！");
		}
		
		if(friendTask.getStatus() != null && friendTask.getStatus() == 1){
			return JsonResponseUtil.badResult(1, "答题已结束！");
		}
		
		if(question == 1){
			if(friendTask.getId() != null){
				return JsonResponseUtil.badResult(1, "第一题已回答，无法再保存第一题答案！");
			}
		}else if(question == 2){
			if(friendTask.getId() == null || friendTask.getAnswerA()==null){
				return JsonResponseUtil.badResult(1, "第一题未回答，无法保存第二题答案！");
			}else if( friendTask.getAnswerA() == 0){
				return JsonResponseUtil.badResult(1, "第一题回答错误，无法保存第二题答案！");
			}else if( friendTask.getAnswerB()!=null && friendTask.getAnswerB() == 1){
				return JsonResponseUtil.badResult(1, "第二题已回答，无法保存第二题答案！");
			}
		}else if(question == 3){
			if(friendTask.getId() == null || friendTask.getAnswerA()==null){
				return JsonResponseUtil.badResult(1, "第一题未回答，无法保存第三题答案！");
			}else if(friendTask.getAnswerA() == 0){
				return JsonResponseUtil.badResult(1, "第一题回答错误，无法保存第三题答案！");
			}else if(friendTask.getAnswerB()==null){
				return JsonResponseUtil.badResult(1, "第二题未回答，无法保存第三题答案！");
			}else if(friendTask.getAnswerB() == 0){
				return JsonResponseUtil.badResult(1, "第二题回答错误，无法保存第三题答案！");
			}else if( friendTask.getAnswerC()!=null && friendTask.getAnswerC() == 1){
				return JsonResponseUtil.badResult(1, "第三题已回答，无法保存第三题答案！");
			}
		}
		
		// 2. 设值
		if(question == 1){
			friendTask.setAnswerA(answer);
			friendTask.setScore(answer);
			friendTask.setStatus(answer==0 ? 1 : 0);
			try {
				FriendWechatLog  wechatLog =friendWechatLogService.getByOpenid(friendTask.getTaskOpenId());
				friendTask.setTaskNickname(wechatLog.getNickName());
				friendTask.setTaskHeadimgurl(wechatLog.getHeadimgurl());
			} catch (Exception e) {
				logger.error("保存答题结果时，获取被邀请人信息失败！", e);
				return JsonResponseUtil.badResult(1, "保存答题结果时，获取被邀请人信息失败！");
			}
		}else if(question == 2){
			friendTask.setAnswerB(answer);
			friendTask.setScore(1+answer);
			friendTask.setStatus(answer==0 ? 1 : 0);
		}else if(question == 3){
			friendTask.setAnswerC(answer);
			friendTask.setScore(2+answer);
			friendTask.setStatus(1);
		}
		
		// 3. 保存
		try {
			friendTaskService.save(friendTask);
		} catch (Exception e) {
			logger.error("保存答题结果失败！", e);
			return JsonResponseUtil.badResult(1, "保存答题结果失败！");
		}
		
		// 4. 返回结果
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("score", friendTask.getScore());
		result.put("status", friendTask.getStatus());
		return JsonResponseUtil.ok(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(FriendTask friendTask, RedirectAttributes redirectAttributes) {
		friendTaskService.delete(friendTask);
		return JsonResponseUtil.ok("删除任务保存成功成功");
	}

}