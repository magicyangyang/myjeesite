/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.huli.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huli.entity.FriendWechatLog;
import com.thinkgem.jeesite.modules.huli.entity.User;
import com.thinkgem.jeesite.modules.huli.service.FriendWechatLogService;
import com.thinkgem.jeesite.modules.huli.utils.JsonResponseUtil;


/**
 * 微信日志管理Controller
 * @author spring
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/huli")
public class IndexController extends BaseController {


	@Autowired
	private FriendWechatLogService friendWechatLogService;
	
	/**
     * 存储用户的微信账号信息数据
     * @return
     */
	@RequestMapping("wechatuser")
    public 	@ResponseBody String wechatuser(String userjson, RedirectAttributes redirectAttributes){
		logger.info("[wechatuser] userjson={}",userjson);
    	if(StringUtils.isBlank(userjson)){
    		return JsonResponseUtil.badResult("参数不能为空");
    	}
    	String jsStr = null;
    	try {
    		jsStr = URLDecoder.decode(userjson, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
		       logger.error(e1.getMessage(),e1);
		}
    	if(StringUtils.isBlank(jsStr)){
    		return JsonResponseUtil.badResult("参数decode失败");
    	}
    	User user = null;
    	if(StringUtils.isNotBlank(userjson)){
			user = JSONObject.parseObject(userjson,User.class);
		}
		if(null==user){
			return JsonResponseUtil.badResult("数据不正确,不能解析出对应的user信息");
		}
		if(null==user.getOpenId()){
			return JsonResponseUtil.badResult("数据不正确,user信息中的openid不能为空");
		}
    	try {
    		FriendWechatLog res=friendWechatLogService.saveUser(jsStr);
        	if(null!=res){
        		return JsonResponseUtil.ok(res);
        	}else{
        		return JsonResponseUtil.badResult("保存失败");
        	}
		} catch (Exception e) {
			logger.info("[wechatuser] userjson={}|e={}",userjson,e.getMessage(),e);
		}
    	return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
}
	/**
     * 存储用户的微信账号信息数据
     * @return
     */
	@RequestMapping("getWechatUser")
    public 	@ResponseBody String getWechatUser(String uid, RedirectAttributes redirectAttributes){
		logger.info("[getWechatUser] uid={}",uid);
    	try {
    		FriendWechatLog res=friendWechatLogService.get(uid);
        	if(null!=res){
        		return JsonResponseUtil.ok(res);
        	}else{
        		return JsonResponseUtil.badResult("无此记录");
        	}
		} catch (Exception e) {
			logger.info("[getWechatUser] uid={}|e={}",uid,e.getMessage(),e);
		}
    	return JsonResponseUtil.badResult("系统繁忙,请稍候再试");
}
	
	
}