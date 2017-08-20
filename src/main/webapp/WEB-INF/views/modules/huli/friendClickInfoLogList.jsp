<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日志保存成功管理</title>
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
		<li class="active"><a href="${ctx}/huli/friendClickInfoLog/">日志保存成功列表</a></li>
		<shiro:hasPermission name="huli:friendClickInfoLog:edit"><li><a href="${ctx}/huli/friendClickInfoLog/form">日志保存成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="friendClickInfoLog" action="${ctx}/huli/friendClickInfoLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>info_id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>微信openid：</label>
				<form:input path="openid" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>操作人：</label>
				<form:input path="uid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用户电话号码：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>操作类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>操作结果：</label>
				<form:input path="result" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>活动标识：</label>
				<form:input path="campaign" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${friendClickInfoLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${friendClickInfoLog.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>info_id</th>
				<th>微信openid</th>
				<th>操作人</th>
				<th>用户电话号码</th>
				<th>操作类型</th>
				<th>操作结果</th>
				<th>备注</th>
				<th>活动标识</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="huli:friendClickInfoLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="friendClickInfoLog">
			<tr>
				<td><a href="${ctx}/huli/friendClickInfoLog/form?id=${friendClickInfoLog.id}">
					${friendClickInfoLog.id}
				</a></td>
				<td>
					${friendClickInfoLog.openid}
				</td>
				<td>
					${friendClickInfoLog.uid}
				</td>
				<td>
					${friendClickInfoLog.mobile}
				</td>
				<td>
					${friendClickInfoLog.type}
				</td>
				<td>
					${friendClickInfoLog.result}
				</td>
				<td>
					${friendClickInfoLog.remark}
				</td>
				<td>
					${friendClickInfoLog.campaign}
				</td>
				<td>
					<fmt:formatDate value="${friendClickInfoLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${friendClickInfoLog.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="huli:friendClickInfoLog:edit"><td>
    				<a href="${ctx}/huli/friendClickInfoLog/form?id=${friendClickInfoLog.id}">修改</a>
					<a href="${ctx}/huli/friendClickInfoLog/delete?id=${friendClickInfoLog.id}" onclick="return confirmx('确认要删除该日志保存成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>