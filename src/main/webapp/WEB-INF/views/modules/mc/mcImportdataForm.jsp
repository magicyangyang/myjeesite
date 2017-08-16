<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导入数据管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mc/mcImportdata/">导入数据列表</a></li>
		<li class="active"><a href="${ctx}/mc/mcImportdata/form?id=${mcImportdata.id}">导入数据<shiro:hasPermission name="mc:mcImportdata:edit">${not empty mcImportdata.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mc:mcImportdata:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mcImportdata" action="${ctx}/mc/mcImportdata/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">导入数据名称：</label>
			<div class="controls">
				<form:input path="importName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导入数据编码：</label>
			<div class="controls">
				<form:input path="importCode" htmlEscape="false" maxlength="256" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属接入方：</label>
			<div class="controls">
				<form:input path="accesspartyId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据结构：</label>
			<div class="controls">
				<form:input path="structure" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 '0&rsquo;:禁用 '1'：启用：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">导入数据明细表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>数据内容</th>
								<th>排序</th>
								<th>备注</th>
								<shiro:hasPermission name="mc:mcImportdata:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="mcImportdataitemList">
						</tbody>
						<shiro:hasPermission name="mc:mcImportdata:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#mcImportdataitemList', mcImportdataitemRowIdx, mcImportdataitemTpl);mcImportdataitemRowIdx = mcImportdataitemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="mcImportdataitemTpl">//<!--
						<tr id="mcImportdataitemList{{idx}}">
							<td class="hide">
								<input id="mcImportdataitemList{{idx}}_id" name="mcImportdataitemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mcImportdataitemList{{idx}}_delFlag" name="mcImportdataitemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="mcImportdataitemList{{idx}}_content" name="mcImportdataitemList[{{idx}}].content" rows="4" class="input-small required">{{row.content}}</textarea>
							</td>
							<td>
								<input id="mcImportdataitemList{{idx}}_sort" name="mcImportdataitemList[{{idx}}].sort" type="text" value="{{row.sort}}" class="input-small required"/>
							</td>
							<td>
								<textarea id="mcImportdataitemList{{idx}}_remarks" name="mcImportdataitemList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="mc:mcImportdata:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#mcImportdataitemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var mcImportdataitemRowIdx = 0, mcImportdataitemTpl = $("#mcImportdataitemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(mcImportdata.mcImportdataitemList)};
							for (var i=0; i<data.length; i++){
								addRow('#mcImportdataitemList', mcImportdataitemRowIdx, mcImportdataitemTpl, data[i]);
								mcImportdataitemRowIdx = mcImportdataitemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="mc:mcImportdata:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>