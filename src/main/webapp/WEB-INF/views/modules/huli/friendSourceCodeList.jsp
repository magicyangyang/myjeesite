<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源管理</title>
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
		<li class="active"><a href="${ctx}/huli/friendSourceCode/">资源列表</a></li>
		<shiro:hasPermission name="huli:friendSourceCode:edit"><li><a href="${ctx}/huli/friendSourceCode/form">资源添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="friendSourceCode" action="${ctx}/huli/friendSourceCode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>活动名称：</label>
				<form:input path="actId" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>发起人openid：</label>
				<form:input path="openid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>渠道标识：</label>
				<form:input path="channelName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>兑换码：</label>
				<form:input path="code" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>活动日期：</label>
				<form:input path="actDay" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>资源状态 0 没有赠送过，1 已经赠送过：</label>
				<form:input path="status" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>资源类型,用来区分红包还是返现还是加息券：</label>
				<form:input path="type" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>红包金额 单位：元：</label>
				<form:input path="amount" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${friendSourceCode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${friendSourceCode.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>主键</th>
				<th>活动名称</th>
				<th>发起人openid</th>
				<th>渠道标识</th>
				<th>兑换码</th>
				<th>活动日期</th>
				<th>资源状态 0 没有赠送过，1 已经赠送过</th>
				<th>资源类型,用来区分红包还是返现还是加息券</th>
				<th>红包金额 单位：元</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="huli:friendSourceCode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="friendSourceCode">
			<tr>
				<td><a href="${ctx}/huli/friendSourceCode/form?id=${friendSourceCode.id}">
					${friendSourceCode.id}
				</a></td>
				<td>
					${friendSourceCode.actId}
				</td>
				<td>
					${friendSourceCode.openid}
				</td>
				<td>
					${friendSourceCode.channelName}
				</td>
				<td>
					${friendSourceCode.code}
				</td>
				<td>
					${friendSourceCode.actDay}
				</td>
				<td>
					${friendSourceCode.status}
				</td>
				<td>
					${friendSourceCode.type}
				</td>
				<td>
					${friendSourceCode.amount}
				</td>
				<td>
					<fmt:formatDate value="${friendSourceCode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${friendSourceCode.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="huli:friendSourceCode:edit"><td>
    				<a href="${ctx}/huli/friendSourceCode/form?id=${friendSourceCode.id}">修改</a>
					<a href="${ctx}/huli/friendSourceCode/delete?id=${friendSourceCode.id}" onclick="return confirmx('确认要删除该资源吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>