<%@page isErrorPage="true" %><!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>搜易贷-页面出错</title>
    <link rel="stylesheet" type="text/css" href="https://static.souyidai.com/www/css/main.css">
    <script language="javascript" src="https://static.souyidai.com/www/js/jquery1.10.2.min.js"></script>
</head>
<body class="light-page-bg">
<!--mainPage-->
<div class="cf main-offsetHeight">
    <!--头部-->
    <div class="main">
        <div class="borrow-page-top page-top33">
            <div class="borrow-top-right">
                <a href="https://help.souyidai.com/help/guide/">新手指引</a><a href="https://help.souyidai.com/help/">帮助中心</a><a href="javascript:void(0);" class="ico-borrow ico-phone"></a><span>4008-989-666</span>
            </div>
            <%--<div class="borrow-top-left">--%>
                <%--<c:if test="${empty user}">--%>
                    <%--<div style="display: none;"> 您好，欢迎来到搜易贷！<a href="https://passport.souyidai.com">[登录]</a><a href="https://passport.souyidai.com/regist.html">[免费注册]</a></div>--%>
                <%--</c:if>--%>
                <%--<!--登录前end-->--%>
                <%--<!--登录后-->--%>
                <%--<c:if test="${not empty user}">--%>
                    <%--<div class="login-after">--%>
                        <%--<a href="https://passport.souyidai.com/myaccount/capital">欢迎你，${user}</a><a href="https://passport.souyidai.com/logout?backurl=">【安全退出】</a>--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <%--<!--登录后end-->--%>
            <%--</div>--%>
        </div>
        <div class="navigation">
            <span class="borrow-logo"><a href="/"><img src="https://static.souyidai.com/www/images/new-logo.png"></a></span>
        </div>
    </div>
    <!--头部end-->
    <!--内容部分-->
    <div class="main ">
        <div class="busy-500 report-errors">
            <p class="busy-img"><img src="https://static.souyidai.com/www/images/500-busy.png"></p>
            <div class="busy-infor">
                <p class="busy-msg">对不起，系统繁忙，请稍后重试！</p>
                <p class="btn-busy"><a href="javascript:window.location.reload();">刷新一下再试试</a><a href="/">返回首页</a> </p>
            </div>
        </div>
        <p class="busy-bg"></p>
    </div>
    <!--内容部分end-->
</div>
<!--mainPage-end-->
<!--footer()-->
<div class="footer light-page-footer">
    <p class="main">版权所有 &copy; 2014 搜易贷（搜狐集团（NASDAQ：SOHU）旗下公司）All Rights Reserved 京ICP备14032951号</p>
</div>
<!--footer-end-->
<script language="javascript" src="https://static.souyidai.com/www/js/main.js"></script>
</body>
</html>