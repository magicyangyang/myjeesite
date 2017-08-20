<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>被邀请人管理</title>
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
		<li><a href="${ctx}/friend/friendInvitee/">被邀请人列表</a></li>
		<li class="active"><a href="${ctx}/friend/friendInvitee/form?id=${friendInvitee.id}">被邀请人<shiro:hasPermission name="friend:friendInvitee:edit">${not empty friendInvitee.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="friend:friendInvitee:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="friendInvitee" action="${ctx}/friend/friendInvitee/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">发起人id：</label>
			<div class="controls">
				<form:input path="initiatorId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发起人微信openid：</label>
			<div class="controls">
				<form:input path="initiatorOpenId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被邀请人微信openid：</label>
			<div class="controls">
				<form:input path="openid" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被邀请人昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被邀请人头像地址：</label>
			<div class="controls">
				<form:input path="headimgurl" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">得分：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">答题状态：0 未答完， 1 已打完：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${friendInvitee.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${friendInvitee.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">受邀请人题答信息表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>邀请人微信id</th>
								<th>被邀请人微信id</th>
								<th>问题1</th>
								<th>答案1</th>
								<th>问题2</th>
								<th>答案2</th>
								<th>问题3</th>
								<th>答案3</th>
								<th>创建时间</th>
								<th>修改时间</th>
								<shiro:hasPermission name="friend:friendInvitee:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="friendAnswerList">
						</tbody>
						<shiro:hasPermission name="friend:friendInvitee:edit"><tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#friendAnswerList', friendAnswerRowIdx, friendAnswerTpl);friendAnswerRowIdx = friendAnswerRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="friendAnswerTpl">//<!--
						<tr id="friendAnswerList{{idx}}">
							<td class="hide">
								<input id="friendAnswerList{{idx}}_id" name="friendAnswerList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="friendAnswerList{{idx}}_delFlag" name="friendAnswerList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_initiatorOpenId" name="friendAnswerList[{{idx}}].initiatorOpenId" type="text" value="{{row.initiatorOpenId}}" maxlength="255" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_inviteeOpenId" name="friendAnswerList[{{idx}}].inviteeOpenId" type="text" value="{{row.inviteeOpenId}}" maxlength="255" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_questionA" name="friendAnswerList[{{idx}}].questionA" type="text" value="{{row.questionA}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_answerA" name="friendAnswerList[{{idx}}].answerA" type="text" value="{{row.answerA}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_questionB" name="friendAnswerList[{{idx}}].questionB" type="text" value="{{row.questionB}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_answerB" name="friendAnswerList[{{idx}}].answerB" type="text" value="{{row.answerB}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_questionC" name="friendAnswerList[{{idx}}].questionC" type="text" value="{{row.questionC}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_answerC" name="friendAnswerList[{{idx}}].answerC" type="text" value="{{row.answerC}}" maxlength="4" class="input-small required"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_createTime" name="friendAnswerList[{{idx}}].createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
									value="{{row.createTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="friendAnswerList{{idx}}_updateTime" name="friendAnswerList[{{idx}}].updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
									value="{{row.updateTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<shiro:hasPermission name="friend:friendInvitee:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#friendAnswerList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var friendAnswerRowIdx = 0, friendAnswerTpl = $("#friendAnswerTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(friendInvitee.friendAnswerList)};
							for (var i=0; i<data.length; i++){
								addRow('#friendAnswerList', friendAnswerRowIdx, friendAnswerTpl, data[i]);
								friendAnswerRowIdx = friendAnswerRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="friend:friendInvitee:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>