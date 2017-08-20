package com.thinkgem.jeesite.modules.huli.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

public final class JsonResponseUtil {

    private static final String JSON_ERRORCODE_NAME = "code";

    private static final String JSON_ERRORMSG_NAME = "message";

    private static final String JSON_DATA_NAME = "data";

    private static final String JSON_OVER_COUNT = "overcount";

    private JsonResponseUtil() {

    }

    public static String badResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  result.toJSONString();
    }

    public static String badResult(int errorCode,String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, errorCode);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  result.toJSONString();
    }
    public static String badResult(Integer code,String cause,int overcount) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, code);
        result.put(JSON_ERRORMSG_NAME, cause);
        result.put(JSON_OVER_COUNT, overcount);
        return  result.toJSONString();
    }

    public static String emptyResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 2);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  result.toJSONString();
    }

    public static String badResult(String cause, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  callback + "(" + result.toJSONString() + ")";
    }

    public static String badResult(Map<String, String> cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  result.toJSONString();
    }

    public static String badResult(int errorCode, Object cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, errorCode);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  result.toJSONString();
    }

    public static String ok() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, "成功");
        return  result.toString();
    }

    public static String okWithEmpty() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, Collections.emptyList());
        return  result.toString();
    }

    public static String ok(String key, Object value) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, ImmutableMap.of(key, value));
        return  result.toJSONString();
    }

    public static String ok(Object object) {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        return  JSON.toJSONString(result);
    }

    public static String ok(Object object, Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        if (params != null && params.size() > 0) {
            Iterator<Entry<String, Object>> keys = params.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) keys.next();
                result.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        return  result.toJSONString();
    }

    public static String okWithPaginate(Object object, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("size", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_DATA_NAME, object);
        result.put(JSON_ERRORCODE_NAME, 0);
        return  result.toJSONString();
    }

    @SuppressWarnings("rawtypes")
    public static String ok(List list) {
        JSONObject result = new JSONObject();
        result.put(JSON_DATA_NAME, list);
        result.put(JSON_ERRORCODE_NAME, 0);
        return  result.toJSONString();
    }

    public static String jsonp(Object object, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        return  callback + "(" + result.toJSONString() + ")";
    }

    public static String ok(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return  result.toJSONString();
    }

    public static String ok(ImmutableMap<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return  result.toJSONString();
    }

    public static <T> String okWithPaginate(List<T> list, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("size", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, list);
        return  result.toJSONString();
    }

    public static String okWithPaginate(Map<String, Object> params, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("size", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return  result.toJSONString();
    }

    public static String okSupportJSONP(Object object, String fun) {
        if (StringUtils.isBlank(fun)) {
            return ok(object);
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        return  fun + "(" + result.toJSONString() + ")";
    }

    public static <T> String okSupportJSONPWithPaginate(List<T> list, String callback, Map<String, Object> params) {
        if (StringUtils.isBlank(callback)) {
            return ok(list);
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, list);

        if (params != null && params.size() > 0) {
            Iterator<Map.Entry<String, Object>> keys = params.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) keys.next();
                result.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        return  callback + "(" + result.toJSONString() + ")";
    }

    public static String badResultSupportJSONP(String cause, String fun) {
        if (StringUtils.isBlank(fun)) {
            return badResult(cause);
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return  fun + "(" + result.toJSONString() + ")";
    }

    public static String okSupportEmptyWithJSONP(String fun) {
        if (StringUtils.isBlank(fun)) {
            return okWithEmpty();
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_ERRORMSG_NAME, "success");
        return  fun + "(" + result.toString() + ")";
    }
    
}
