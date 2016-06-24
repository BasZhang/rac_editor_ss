<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GM工具</title>
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
	</head>
	<body>
		<c:if test="${msg != null}">
			<div class="demo-info" style="margin-bottom:10px">
				<div class="demo-tip icon-tip">&nbsp;</div>
				<div>${msg}</div>
	    	</div>
		</c:if>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<h3>请输入昵称或UID：</h3>
			<form class="pop-response" action="ggm_queryAll" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<button type="submit">搜索</button>
			</form>
		</div>
	</body>
</html>