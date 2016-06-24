<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
	</head>

	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<h3>
				添加用户：
			</h3>
			<form class="pop-response" action="s_addUser" method="post">
				<table>
					<tbody>
						<tr>
							<td>用户名</td>
							<td>
								<input type="text" name="username" required="required" style="width: 100px;"/>
							</td>
						</tr>
						<tr>
							<td>密码</td>
							<td>
								<input type="password" name="password" required="required" style="width: 100px;"/>
							</td>
						</tr>
						<tr>
							<td>用户类型</td>
							<td>
								<select class="easyui-combobox" name="type"
									style="width: 100px;">
									<option value="ADMIN">管理员</option>
									<option value="DEV">开发者</option>
									<option value="COMM">GM</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<button type="submit">
					提交
				</button>
			</form>
		</div>
	</body>
</html>
