/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendInviter;
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;
import com.thinkgem.jeesite.modules.huli.entity.FriendTask;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.service.FriendInviterService;
import com.thinkgem.jeesite.modules.huli.service.FriendShipService;
import com.thinkgem.jeesite.modules.huli.service.FriendSourceCodeService;
import com.thinkgem.jeesite.modules.huli.service.FriendTaskService;
import com.thinkgem.jeesite.modules.huli.service.FriendWechatLogService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 任务管理Controller
 * @author shuai
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${huliPath}/qa")
public class FriendTaskController extends BaseController {

	@Autowired
	private FriendTaskService friendTaskService;
	
	@Autowired
	private FriendWechatLogService friendWechatLogService;
	
	@Autowired
	private FriendInviterService friendInviterService;
	
	@Autowired
	private  FriendSourceCodeService friendSourceCodeService;
	
	@Autowired
	private FriendShipService friendShipService;
	
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
	@RequestMapping(value = {"list"})
	public String list(FriendTask friendTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FriendTask> page = friendTaskService.findPage(new Page<FriendTask>(request, response), friendTask); 
		return JsonResponseUtil.ok(page);
	}

	@ResponseBody
	@RequestMapping(value = "form")
	public String form(String taskOpenid, String inviteOpenid, Model model) {
		FriendTask entity = friendTaskService.getByOpenids(inviteOpenid, taskOpenid);
		if (entity == null){
			entity = new FriendTask();
			entity.setInviteOpenId(inviteOpenid);
			entity.setTaskOpenId(taskOpenid);
			entity.setQuestionA(1);
			entity.setQuestionB(2);
			entity.setQuestionC(3);
			entity.setAnswerA(0);
			entity.setAnswerB(0);
			entity.setAnswerC(0);
			entity.setScore(0);
			entity.setStatus(0);
		}
		return JsonResponseUtil.ok(entity);
	}

	
	@ResponseBody
	@RequestMapping(value = "")
	public String save(String taskOpenid, String inviteOpenid, Integer question, Integer answer, Model model, RedirectAttributes redirectAttributes) {
		// 1. 校验
		if(StringUtils.isBlank(taskOpenid)){
			return JsonResponseUtil.badResult("被邀请人openid不能为空！");
		}
		if(question == null || question<1 || question>3){
			return JsonResponseUtil.badResult("question参数传入错误！");
		}
		if(answer == null || answer<0 || answer>1){
			return JsonResponseUtil.badResult("answer参数传入错误！");
		}
		boolean isHaveShip = false;
		List<FriendShip> friendShips = friendShipService.getShipByTaskOpenid(taskOpenid);
		if(null!=friendShips&&!friendShips.isEmpty()){
			   for (FriendShip ship:friendShips) {
				   String dbinviteOpenid = ship.getInviteOpenId();
				   if(null!=dbinviteOpenid){
					   isHaveShip = true;
					   if(StringUtils.isBlank(inviteOpenid)){
							 inviteOpenid=dbinviteOpenid;
						}
					   break;
				   }
			}
		}
		if(!isHaveShip){
			FriendWechatLog taskUser = friendWechatLogService.getByOpenid(taskOpenid);
			if (null == taskUser) {
				return JsonResponseUtil.badResult("被邀请人,请先关注狐狸慧赚公众号");
			}
			if(StringUtils.isBlank(inviteOpenid)){
				return JsonResponseUtil.badResult("暂时没有人邀请你呦！");
			}
			FriendWechatLog inviteUser = friendWechatLogService.getByOpenid(inviteOpenid);
			if (null == inviteUser) {
				return JsonResponseUtil.badResult("邀请人,请先关注狐狸慧赚公众号");
			}
			 friendShipService.saveShip(taskUser, inviteUser);
		}
		if(StringUtils.isBlank(inviteOpenid)){
			return JsonResponseUtil.badResult("暂时没有人邀请你呦！");
		}
		FriendTask friendTask = friendTaskService.getByOpenids(inviteOpenid, taskOpenid);
		if (friendTask == null){
			friendTask = new FriendTask();
			friendTask.setInviteOpenId(inviteOpenid);
			friendTask.setTaskOpenId(taskOpenid);
			friendTask.setQuestionA(1);
			friendTask.setQuestionB(2);
			friendTask.setQuestionC(3);
			friendTask.setAnswerA(0);
			friendTask.setAnswerB(0);
			friendTask.setAnswerC(0);
			friendTask.setScore(0);
			friendTask.setStatus(0);
		}
		if(friendTask.getStatus() != null && friendTask.getStatus() == 1){
			return JsonResponseUtil.badResult("答题已结束！");
		}
		if(question == 1){
			if(friendTask.getId() != null){
				return JsonResponseUtil.badResult("第一题已回答，无法再保存第一题答案！");
			}
		}else if(question == 2){
			if(friendTask.getId() == null || friendTask.getAnswerA()==null){
				return JsonResponseUtil.badResult("第一题未回答，无法保存第二题答案！");
			}else if( friendTask.getAnswerA() == 1){
				return JsonResponseUtil.badResult("第一题回答错误，无法保存第二题答案！");
			}else if( friendTask.getAnswerB()!=null && friendTask.getAnswerB() == 1&&friendTask.getStatus()==1){
				return JsonResponseUtil.badResult("第二题已回答，无法保存第二题答案！");
			}
		}else if(question == 3){
			if(friendTask.getId() == null || friendTask.getAnswerA()==null){
				return JsonResponseUtil.badResult("第一题未回答，无法保存第三题答案！");
			}else if(friendTask.getAnswerA() == 1){
				return JsonResponseUtil.badResult("第一题回答错误，无法保存第三题答案！");
			}else if(friendTask.getAnswerB()==null){
				return JsonResponseUtil.badResult("第二题未回答，无法保存第三题答案！");
			}else if(friendTask.getAnswerB() == 1){
				return JsonResponseUtil.badResult("第二题回答错误，无法保存第三题答案！");
			}else if( friendTask.getAnswerC()!=null && friendTask.getAnswerC() == 1&&friendTask.getStatus()==1){
				return JsonResponseUtil.badResult("第三题已回答，无法保存第三题答案！");
			}
		}
		// 2. 设值
		if(question == 1){
			friendTask.setAnswerA(answer);
			friendTask.setScore(answer==0 ? 5: 0);
			friendTask.setStatus(answer==0 ? 0 : 1);
			try {
				FriendWechatLog  wechatLog =friendWechatLogService.getByOpenid(friendTask.getTaskOpenId());
				friendTask.setTaskNickname(wechatLog.getNickName());
				friendTask.setTaskHeadimgurl(wechatLog.getHeadimgurl());
			} catch (Exception e) {
				logger.error("保存答题结果时，获取被邀请人信息失败！", e);
				return JsonResponseUtil.badResult("保存答题结果时，获取被邀请人信息失败！");
			}
		}else if(question == 2){
			friendTask.setAnswerB(answer);
			friendTask.setScore(5+(answer==0 ? 8: 0));
			friendTask.setStatus(answer==0 ? 0 : 1);
		}else if(question == 3){
			friendTask.setAnswerC(answer);
			friendTask.setScore(13+(answer==0 ? 10: 0));
			friendTask.setStatus(1);
		}
		
		// 3. 保存
		try {
			friendTaskService.save(friendTask);
			if(friendTask.getStatus()==1){
				updateInviteStatus(friendTask);
			}
		} catch (Exception e) {
			logger.error("保存答题结果失败！", e);
		}
		
		// 4. 返回结果
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("score", friendTask.getScore());
		result.put("status", friendTask.getStatus());
		return JsonResponseUtil.ok(result);
	}

	private void updateInviteStatus(FriendTask friendTask) {
		List<FriendTask> tasks = friendTaskService.getTasksByInviteOpenid(friendTask.getInviteOpenId());
		if(null==tasks||tasks.isEmpty()){
			logger.info("[nodata]friendTask={}",friendTask);
			return;
		}
		FriendInviter friendInviter = friendInviterService.getInfoByOpenid(friendTask.getInviteOpenId());
		if(friendInviter==null){
			friendInviter = new FriendInviter();
			friendInviter.setCode("");
			friendInviter.setCreateTime(new Date());
			friendInviter.setUpdateTime(new Date());
			friendInviter.setOpenid(friendTask.getInviteOpenId());
			friendInviter.setStatus(0);
			friendInviter.setIsSendmoney(0);
			friendInviter.setInviteNum(2);
			friendInviter.setScore(friendTask.getScore());
		}
		if(null==friendInviter.getId()){
			friendInviter.setIsNewRecord(true);
			friendInviter.setId(IdGen.uuid());
		}
		if(tasks.size()==1){
			friendInviter.setCreateTime(new Date());
			friendInviter.setUpdateTime(new Date());
			friendInviter.setOpenid(friendTask.getInviteOpenId());
			friendInviter.setStatus(0);
			friendInviter.setIsSendmoney(0);
			friendInviter.setInviteNum(1);
			friendInviter.setScore(friendTask.getScore());
		}else{
			int score = 0;
			if(tasks.size()<=3){
				for (FriendTask task : tasks) {
					score +=task.getScore();
				}
			}else{
				for (int i=0;i<=3;i++) {
					score +=tasks.get(i).getScore();
				}
			}
			friendInviter.setCode(tasks.size()==3?getCodeByScore(friendTask.getInviteOpenId(),score):friendInviter.getCode());
			friendInviter.setUpdateTime(new Date());
			friendInviter.setStatus(tasks.size()>=3?1:0);
			friendInviter.setIsSendmoney(tasks.size()>=3?1:0);
			friendInviter.setInviteNum(tasks.size());
			friendInviter.setScore(score);
		}
		logger.info("[updateInviteStatus]={}|friendInviter={}",friendTask,friendInviter);
		friendInviterService.save(friendInviter);
	}
	private String getCodeByScore(String openid, int score) {
		return friendSourceCodeService.getCodeByScore(openid,score);
	}

}