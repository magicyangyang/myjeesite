<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息保存成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/huli/friendWechatLog/">用户信息保存成功列表</a></li>
		<li class="active"><a href="${ctx}/huli/friendWechatLog/form?id=${friendWechatLog.id}">用户信息保存成功<shiro:hasPermission name="huli:friendWechatLog:edit">${not empty friendWechatLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="huli:friendWechatLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="friendWechatLog" action="${ctx}/huli/friendWechatLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">狐狸金服用户uid：</label>
			<div class="controls">
				<form:input path="uid" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="nickName" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信账号标识：</label>
			<div class="controls">
				<form:input path="openid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户的性别，值为1时是男性，值为2时是女性，值为0时是未知：</label>
			<div class="controls">
				<form:input path="gender" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户个人资料填写的省份：</label>
			<div class="controls">
				<form:input path="province" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">普通用户个人资料填写的城市：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国家，如中国为CN：</label>
			<div class="controls">
				<form:input path="country" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空：</label>
			<div class="controls">
				<form:input path="headimgurl" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）：</label>
			<div class="controls">
				<form:input path="privilege" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。：</label>
			<div class="controls">
				<form:input path="isSubscribe" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关注时间：</label>
			<div class="controls">
				<form:input path="subscribeTime" htmlEscape="false" maxlength="21" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">language：</label>
			<div class="controls">
				<form:input path="language" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段：</label>
			<div class="controls">
				<form:input path="unionId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户所在的分组ID：</label>
			<div class="controls">
				<form:input path="groupId" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录时间：</label>
			<div class="controls">
				<input name="loginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${friendWechatLog.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="huli:friendWechatLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>