<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导入数据管理</title>
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
		<li class="active"><a href="${ctx}/mc/mcImportdata/">导入数据列表</a></li>
		<shiro:hasPermission name="mc:mcImportdata:edit"><li><a href="${ctx}/mc/mcImportdata/form">导入数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mcImportdata" action="${ctx}/mc/mcImportdata/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="mc:mcImportdata:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mcImportdata">
			<tr>
				<td><a href="${ctx}/mc/mcImportdata/form?id=${mcImportdata.id}">
					${mcImportdata.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${mcImportdata.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="mc:mcImportdata:edit"><td>
    				<a href="${ctx}/mc/mcImportdata/form?id=${mcImportdata.id}">修改</a>
					<a href="${ctx}/mc/mcImportdata/delete?id=${mcImportdata.id}" onclick="return confirmx('确认要删除该导入数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>