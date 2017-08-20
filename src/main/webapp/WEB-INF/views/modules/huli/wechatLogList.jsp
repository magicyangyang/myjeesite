<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信登录日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huli/wechatLog/">微信登录日志列表</a></li>
		<shiro:hasPermission name="huli:wechatLog:edit"><li><a href="${ctx}/huli/wechatLog/form">微信登录日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wechatLog" action="${ctx}/huli/wechatLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>数据id：</label>
				<form:input path="id" htmlEscape="false" maxlength="21" class="input-medium"/>
			</li>
			<li><label>搜易贷用户uid：</label>
				<form:input path="uid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>微信账号标识：</label>
				<form:input path="openid" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>用户的性别，值为1时是男性，值为2时是女性，值为0时是未知：</label>
				<form:input path="gender" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>用户个人资料填写的省份：</label>
				<form:input path="province" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>普通用户个人资料填写的城市：</label>
				<form:input path="city" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>国家，如中国为CN：</label>
				<form:input path="country" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空：</label>
				<form:input path="headimgurl" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）：</label>
				<form:input path="privilege" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。：</label>
				<form:input path="isSubscribe" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>关注时间：</label>
				<form:input path="subscribeTime" htmlEscape="false" maxlength="21" class="input-medium"/>
			</li>
			<li><label>用户使用语言区域：</label>
				<form:input path="language" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段：</label>
				<form:input path="unionId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>用户所在的分组ID：</label>
				<form:input path="groupId" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>登录时间：</label>
				<input name="loginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${wechatLog.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据id</th>
				<th>搜易贷用户uid</th>
				<th>昵称</th>
				<th>微信账号标识</th>
				<th>用户的性别，值为1时是男性，值为2时是女性，值为0时是未知</th>
				<th>用户个人资料填写的省份</th>
				<th>普通用户个人资料填写的城市</th>
				<th>国家，如中国为CN</th>
				<th>用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空</th>
				<th>用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）</th>
				<th>用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。</th>
				<th>关注时间</th>
				<th>用户使用语言区域</th>
				<th>只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段</th>
				<th>公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注</th>
				<th>用户所在的分组ID</th>
				<th>登录时间</th>
				<shiro:hasPermission name="huli:wechatLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wechatLog">
			<tr>
				<td><a href="${ctx}/huli/wechatLog/form?id=${wechatLog.id}">
					${wechatLog.id}
				</a></td>
				<td>
					${wechatLog.uid}
				</td>
				<td>
					${wechatLog.nickName}
				</td>
				<td>
					${wechatLog.openid}
				</td>
				<td>
					${wechatLog.gender}
				</td>
				<td>
					${wechatLog.province}
				</td>
				<td>
					${wechatLog.city}
				</td>
				<td>
					${wechatLog.country}
				</td>
				<td>
					${wechatLog.headimgurl}
				</td>
				<td>
					${wechatLog.privilege}
				</td>
				<td>
					${wechatLog.isSubscribe}
				</td>
				<td>
					${wechatLog.subscribeTime}
				</td>
				<td>
					${wechatLog.language}
				</td>
				<td>
					${wechatLog.unionId}
				</td>
				<td>
					${wechatLog.remark}
				</td>
				<td>
					${wechatLog.groupId}
				</td>
				<td>
					<fmt:formatDate value="${wechatLog.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="huli:wechatLog:edit"><td>
    				<a href="${ctx}/huli/wechatLog/form?id=${wechatLog.id}">修改</a>
					<a href="${ctx}/huli/wechatLog/delete?id=${wechatLog.id}" onclick="return confirmx('确认要删除该微信登录日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>