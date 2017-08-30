/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendClickInfoLog;
import com.thinkgem.jeesite.modules.huli.entity.FriendShip;
import com.thinkgem.jeesite.modules.huli.entity.FriendTask;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.entity.User;
import com.thinkgem.jeesite.modules.huli.service.FriendClickInfoLogService;
import com.thinkgem.jeesite.modules.huli.service.FriendShipService;
import com.thinkgem.jeesite.modules.huli.service.FriendTaskService;
import com.thinkgem.jeesite.modules.huli.service.FriendWechatLogService;
import com.thinkgem.jeesite.modules.huli.utils.DateUtil;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;

/**
 * 微信日志管理Controller
 * 
 * @author spring
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${huliPath}")
public class IndexController extends BaseController {

	@Autowired
	private FriendWechatLogService friendWechatLogService;

	@Autowired
	private FriendShipService friendShipService;

	@Autowired
	private FriendTaskService friendTaskService;
	
	@Autowired
	private FriendClickInfoLogService friendClickInfoLogService;

	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping(value ="wechatuser")
	public @ResponseBody String wechatuser(String userjson, RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(userjson)) {
			return JsonResponseUtil.badResult("参数不能为空");
		}
		String jsStr = null;
		try {
			jsStr = URLDecoder.decode(userjson, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage(), e1);
		}
		if (StringUtils.isBlank(jsStr)) {
			return JsonResponseUtil.badResult("参数decode失败");
		}
		jsStr=StringEscapeUtils.unescapeHtml4(jsStr);
		logger.info("[wechatuser] userjson={}", jsStr);
		User user = null;
		if (StringUtils.isNotBlank(jsStr)) {
			user = JSONObject.parseObject(jsStr, User.class);
		}
		if (null == user) {
			return JsonResponseUtil.badResult("数据不正确,不能解析出对应的user信息");
		}
		if (null == user.getOpenId()) {
			return JsonResponseUtil.badResult("数据不正确,user信息中的openid不能为空");
		}
		try {
			FriendWechatLog res = friendWechatLogService.saveUser(user);
			if (null != res) {
				return JsonResponseUtil.ok(res);
			} else {
				return JsonResponseUtil.badResult("保存失败");
			}
		} catch (Exception e) {
			logger.info("[wechatusererror] userjson={}|e={}", userjson, e.getMessage(), e);
		}
		return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
	}

	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping("saveWechatuser")
	public @ResponseBody String saveWechatuser(FriendWechatLog wechatlog, RedirectAttributes redirectAttributes) {
		if (null == wechatlog || StringUtils.isBlank(wechatlog.getOpenid())) {
			return JsonResponseUtil.badResult("参数不能为空");
		}
		logger.info("[saveWechatuser] wechatlog={}", wechatlog.toString());
		try {
			FriendWechatLog res = friendWechatLogService.saveFriendWechatLog(wechatlog);
			if (null != res) {
				return JsonResponseUtil.ok(res);
			} else {
				return JsonResponseUtil.badResult("保存失败");
			}
		} catch (Exception e) {
			logger.info("[saveWechatusererror] wechatlog={}|e={}", wechatlog, e.getMessage(), e);
		}
		return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
	}

	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping("getWechatUser")
	public @ResponseBody String getWechatUser(String openid, RedirectAttributes redirectAttributes) {
		logger.info("[getWechatUser] openid={}", openid);
		if (StringUtils.isBlank(openid)) {
			return JsonResponseUtil.badResult("参数不能为空");
		}
		try {
			FriendWechatLog res = friendWechatLogService.getByOpenid(openid);
			if (null != res) {
				return JsonResponseUtil.ok(res);
			} else {
				return JsonResponseUtil.badResult("无此记录");
			}
		} catch (Exception e) {
			logger.info("[getWechatUsererror] openid={}|e={}", openid, e.getMessage(), e);
		}
		return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
	}

	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping("bind")
	public @ResponseBody String bind(String taskOpenid, String inviteOpenid, RedirectAttributes redirectAttributes) {
		logger.info("[bind] taskOpenid={}|inviteOpenid={}", taskOpenid, inviteOpenid);
		if (StringUtils.isBlank(taskOpenid) || StringUtils.isBlank(inviteOpenid)) {
			return JsonResponseUtil.badResult("参数不能为空");
		}
		if (taskOpenid.equals(inviteOpenid)) {
			return JsonResponseUtil.badResult("邀请人不能邀请自己");
		}
		FriendWechatLog taskUser = friendWechatLogService.getByOpenid(taskOpenid);
		if (null == taskUser) {
			return JsonResponseUtil.badResult("被邀请人,请先关注狐狸慧赚公众号");
		}
		FriendWechatLog inviteUser = friendWechatLogService.getByOpenid(inviteOpenid);
		if (null == inviteUser) {
			return JsonResponseUtil.badResult("邀请人,请先关注狐狸慧赚公众号");
		}
		try {
			String res = friendShipService.saveShip(taskUser, inviteUser);
			if (null == res) {
				return JsonResponseUtil.ok("保存成功");
			} else {
				return JsonResponseUtil.badResult(res);
			}
		} catch (Exception e) {
			logger.info("[binderror] taskOpenid={}|inviteOpenid={}", taskOpenid, inviteOpenid, e);
		}
		return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
	}

	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping("task_info")
	public @ResponseBody String task_info(String inviteOpenId, String taskOpenId, RedirectAttributes redirectAttributes) {
		logger.info("[task_info] inviteOpenId={}|taskOpenId={}", inviteOpenId,taskOpenId);
		if (StringUtils.isBlank(inviteOpenId)&&StringUtils.isBlank(taskOpenId)) {
			return JsonResponseUtil.badResult("参数不能为空");
		}
		if(StringUtils.isNotBlank(inviteOpenId)&&StringUtils.isBlank(taskOpenId)){
			FriendWechatLog inviteUser = friendWechatLogService.getByOpenid(inviteOpenId);
			if (null == inviteUser) {
				return JsonResponseUtil.badResult("你好,请先关注狐狸慧赚公众号");
			}
			try {
				List<FriendTask> tasklist = friendTaskService.getTasksByInviteOpenid(inviteOpenId);
				if (null != tasklist&& !tasklist.isEmpty()) {
					for (FriendTask friendTask : tasklist) {
						friendTask.setInviteHeadimgurl(inviteUser.getHeadimgurl());
						friendTask.setInviteNickname(inviteUser.getNickName());
					}
					return  task_ok(tasklist,inviteUser);
				} else {
					return task_fail(3, "太没面子了，还没有人帮你赚钱",inviteUser);
				}
			} catch (Exception e) {
				logger.info("[binderror] inviteOpenid={}|e={}", inviteOpenId, e.getMessage(), e);
			}
		}
		if(StringUtils.isNotBlank(taskOpenId)){
			FriendWechatLog taskUser = friendWechatLogService.getByOpenid(taskOpenId);
			if (null == taskUser) {
				return JsonResponseUtil.badResult("你好,请先关注狐狸慧赚公众号");
			}
			if(StringUtils.isBlank(inviteOpenId)){
				List<FriendShip> shipList = friendShipService.getShipByTaskOpenid(taskOpenId);
				if(null==shipList||shipList.isEmpty()){
					return JsonResponseUtil.badResult("暂无邀请人邀请你做任务");
				}
				 inviteOpenId = shipList.get(0).getInviteOpenId();
			}
			try {
				List<FriendTask> tasklist = friendTaskService.getTasksByFriendShip(inviteOpenId,taskOpenId);
				FriendWechatLog inviteUser = friendWechatLogService.getByOpenid(inviteOpenId);
				if (null != tasklist&& !tasklist.isEmpty()) {
					for (FriendTask friendTask : tasklist) {
						friendTask.setInviteHeadimgurl(inviteUser.getHeadimgurl());
						friendTask.setInviteNickname(inviteUser.getNickName());
					}
				}else{
					tasklist = friendTaskService.getTasksByInviteOpenid(inviteOpenId);
					if (null != tasklist&& !tasklist.isEmpty()) {
						for (FriendTask friendTask : tasklist) {
							friendTask.setInviteHeadimgurl(inviteUser.getHeadimgurl());
							friendTask.setInviteNickname(inviteUser.getNickName());
						}
					}
				}
				return task_ok(tasklist,inviteUser);
			} catch (Exception e) {
				logger.info("[task_infoerror] inviteOpenid={}|e={}", inviteOpenId, e.getMessage(), e);
			}
		}
		return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
	}
	
    private static String task_ok(List<FriendTask> list,FriendWechatLog invite) {
        JSONObject result = new JSONObject();
        Map<String,String> inviteInfo = new HashMap<String,String>();
        inviteInfo.put("inviteHeadimgurl", invite.getHeadimgurl());
        inviteInfo.put("inviteNickname", invite.getNickName());
        inviteInfo.put("inviteOpenId", invite.getOpenid());
        result.put("data", list==null?Collections.emptyList():list);
        result.put("errorCode", 0);
        result.put("inviteInfo", inviteInfo);
        return  result.toJSONString();
    }

    private static String task_fail(int errorCode,String cause,FriendWechatLog invite) {
    	Map<String,String> inviteInfo = new HashMap<String,String>();
        inviteInfo.put("inviteHeadimgurl", invite.getHeadimgurl());
        inviteInfo.put("inviteNickname", invite.getNickName());
        inviteInfo.put("inviteOpenId", invite.getOpenid());
        JSONObject result = new JSONObject();
        result.put("errorCode", errorCode);
        result.put("errorMessage", cause);
        result.put("inviteInfo", inviteInfo);
        return  result.toJSONString();
    }
    
	/**
	 * 存储用户的微信账号信息数据
	 * 
	 * @return
	 */
	@RequestMapping("status")
	public @ResponseBody String status() {
		String beginDateStr = Global.getConfig("activiti.startTime");
		String endDateStr = Global.getConfig("activiti.endTime");
		if (StringUtils.isBlank(beginDateStr)) {
			return JsonResponseUtil.badResult("请配置活动开始时间");
		}
		if (StringUtils.isBlank(endDateStr)) {
			return JsonResponseUtil.badResult("请配置活动结束时间");
		}
		Date beginDay = DateUtil.parseDate(beginDateStr, "yyyy-MM-dd HH:mm:ss");
		Date endDay = DateUtil.parseDate(endDateStr, "yyyy-MM-dd HH:mm:ss");
		boolean isbefore = DateUtil.isBeforeThanDay(beginDay, new Date());
		boolean isfinish = DateUtil.isBeforeThanDay(new Date(),endDay);
		logger.info("[activityTime]startTime ={}|endTime={}|isbefore={}|isfinish={}", beginDateStr, endDateStr,isbefore,isfinish);
		// 当前活动状态（0:未开始 1:进行中 2:已结束）
		Map<String, Integer> currentStatus = new HashMap<String, Integer>();
		if (!isbefore) {
			currentStatus.put("currentStatus", 0);
			return JsonResponseUtil.ok(currentStatus);
		}
		if (!isfinish) {
			currentStatus.put("currentStatus", 2);
			return JsonResponseUtil.ok(currentStatus);
		}
		currentStatus.put("currentStatus", 1);
		return JsonResponseUtil.ok(currentStatus);
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping("iscome")
	public @ResponseBody String iscome(String openid,int type) {
		if(StringUtils.isBlank(openid)||type<=0){
		      return JsonResponseUtil.badResult("参数不正确");
		}
		FriendWechatLog res = friendWechatLogService.getByOpenid(openid);
		if(null==res){
			  return JsonResponseUtil.badResult("你好,请先关注狐狸慧赚公众号");
		}
		  JSONObject result = new JSONObject();
	      result.put("data",res);
	      FriendClickInfoLog clickInfo = friendClickInfoLogService.getClickInfoByOpenidAndType(openid,type);
	      if(null!=clickInfo){
	    	  result.put("success",true);
	      }else{
	    	  result.put("success",false);
	      }
	      result.put("errorCode",0);
		return result.toJSONString();
	}
}