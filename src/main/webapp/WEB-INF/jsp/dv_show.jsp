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
		<script type="text/javascript" src="./res/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="./res/js/json_formatter.js"></script>
		<script type="text/javascript" src="./res/js/pop_resp.js"></script>
	</head>
	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<h3>超时：</h3>
			<form class="pop-response" action="dv_timeout" method="post">
				UID&emsp;
				<input type="text" name="uid" required="required" />
				<br />
				秒数&emsp;
				<input type="text" name="seconds" required="required" />
				<br />
				<button type="submit">
					提交
				</button>
			</form>
		</div>
	</body>
</html>