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
		<li class="active"><a href="${ctx}/huli/friendShareLog/">日志保存成功列表</a></li>
		<shiro:hasPermission name="huli:friendShareLog:edit"><li><a href="${ctx}/huli/friendShareLog/form">日志保存成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="friendShareLog" action="${ctx}/huli/friendShareLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>操作人：</label>
				<form:input path="uid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>操作时间：</label>
				<input name="optTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${friendShareLog.optTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>操作类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>操作页面url：</label>
				<form:input path="url" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>操作时间yyyyMMdd用于统计（按天）：</label>
				<form:input path="dateInt" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>分享结果（1-成功 2-失败3-未知）：</label>
				<form:input path="shareResult" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>分享自&hellip;&hellip;（1-WAP 2-微信 3-App）：</label>
				<form:input path="comeFrom" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>活动标识：</label>
				<form:input path="campaign" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>操作模块：</label>
				<form:input path="module" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>用户微信id：</label>
				<form:input path="openid" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>操作人</th>
				<th>操作时间</th>
				<th>操作类型</th>
				<th>操作页面url</th>
				<th>操作时间yyyyMMdd用于统计（按天）</th>
				<th>分享结果（1-成功 2-失败3-未知）</th>
				<th>分享自&hellip;&hellip;（1-WAP 2-微信 3-App）</th>
				<th>活动标识</th>
				<th>操作模块</th>
				<th>备注</th>
				<th>用户微信id</th>
				<shiro:hasPermission name="huli:friendShareLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="friendShareLog">
			<tr>
				<td><a href="${ctx}/huli/friendShareLog/form?id=${friendShareLog.id}">
					${friendShareLog.id}
				</a></td>
				<td>
					${friendShareLog.uid}
				</td>
				<td>
					<fmt:formatDate value="${friendShareLog.optTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${friendShareLog.type}
				</td>
				<td>
					${friendShareLog.url}
				</td>
				<td>
					${friendShareLog.dateInt}
				</td>
				<td>
					${friendShareLog.shareResult}
				</td>
				<td>
					${friendShareLog.comeFrom}
				</td>
				<td>
					${friendShareLog.campaign}
				</td>
				<td>
					${friendShareLog.module}
				</td>
				<td>
					${friendShareLog.remark}
				</td>
				<td>
					${friendShareLog.openid}
				</td>
				<shiro:hasPermission name="huli:friendShareLog:edit"><td>
    				<a href="${ctx}/huli/friendShareLog/form?id=${friendShareLog.id}">修改</a>
					<a href="${ctx}/huli/friendShareLog/delete?id=${friendShareLog.id}" onclick="return confirmx('确认要删除该日志保存成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>