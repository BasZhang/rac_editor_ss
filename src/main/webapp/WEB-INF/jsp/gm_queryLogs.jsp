<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户日志查询</title>
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./res/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="./res/js/log-infos.js"></script>
		<script type="text/javascript">
			$(function(){
				$(':submit').bind('click', function(){
					$mask = $("<div class=\"datagrid-mask\"></div>")
						.css({display:"block",width:"100%",height:$(window).height()})
						.appendTo("body");
					$pending = $("<div class=\"datagrid-mask-msg\"></div>")
						.html("Processing, please wait ...")
						.css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2})
						.appendTo("body");
					});
				});
		</script>
	</head>
	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询操作记录：</h3>
			UID&emsp;<input id="uid" type="text" name="uid" required="required" value="${uid}"/><br/>
			日期&emsp;<input id="since" name="since" style="width: 150px" class="text-input-small"
					type="text"
					onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
					readonly="readonly" value="${since}" />
			<br />
			日志类型&emsp;
			<input id="logtypeCombo" name="logType" value="${logType}"/>
			<br />
			<button id="submit" type="button">提交</button>
			<h3>查询结果：</h3>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="background-color: #c9c9c9;">
				<thead>
					<tr id="logHint"></tr>
				</thead>
				<tbody id="results">
					<c:forEach var="log" items="${logs}">
						<tr style="height: 22; text-align: left; background-color: #FFFFFF">
							<c:forEach var="col" items="${log}">
								<td>${col}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>


