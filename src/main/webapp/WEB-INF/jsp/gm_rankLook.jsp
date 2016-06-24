<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GM排行榜查询</title>
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./res/calendar/WdatePicker.js"></script>
	</head>
	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<h3>
				全服查询：
			</h3>
			<form class="pop-response" action="gm_lookAll" method="post">
				排行类别&emsp;
				<select name="type">
					<option value="1">
						全服排行
					</option>
					<option value="3">
						地域排行
					</option>
				</select>
				<br />
				区域ID&emsp;
				<select name="area">
					<option value="100000">
						华北
					</option>
					<option value="200000">
						东北
					</option>
					<option value="300000">
						华东
					</option>
					<option value="400000">
						华中
					</option>
					<option value="500000">
						西南
					</option>
					<option value="600000">
						西北
					</option>
					<option value="800000">
						华南
					</option>
				</select>
				<br />
				赛事ID&emsp;
				<select name="gate">
					<c:forEach items="${list}" var="vector">
						<option value="${vector}">
							${vector}
						</option>
					</c:forEach>
				</select>
				<br />
				<button type="submit">
					提交
				</button>
			</form>
		</div>

		<div style="padding: 5px; border: 1px solid #ddd;">
			<h3>
				个人查询：
			</h3>
			<form class="pop-response" action="gm_lookSelf" method="post">
				用户ID&emsp;
				<input type="text" name="uid" required="required" size="60"/>
				<br />
				排行类别&emsp;
				<select name="type">
					<option value="1">
						全服排行
					</option>
					<option value="3">
						地域排行
					</option>
					<option value="2">
						好友排行
					</option>
				</select>
				<br />
				区域ID&emsp;
				<select name="area">
					<option value="100000">
						华北
					</option>
					<option value="200000">
						东北
					</option>
					<option value="300000">
						华东
					</option>
					<option value="400000">
						华中
					</option>
					<option value="500000">
						西南
					</option>
					<option value="600000">
						西北
					</option>
					<option value="800000">
						华南
					</option>
				</select>
				<br />
				赛事ID&emsp;
				<select name="gate">
					<c:forEach items="${list}" var="vector">
						<option value="${vector}">
							${vector}
						</option>
					</c:forEach>
				</select>
				<br />
				<button type="submit">
					提交
				</button>
			</form>
		</div>
	</body>
</html>