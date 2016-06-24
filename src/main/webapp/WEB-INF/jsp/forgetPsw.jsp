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
				重置密码：
			</h3>
			<form class="pop-response" action="s_forgetPsw" method="post">
				<table>
					<tbody>
						<tr>
							<td>您的用户名</td>
							<td>
								<input type="text" name="username" required="required" style="width: 200px;"/>
							</td>
						</tr>
						<tr>
							<td>您的邮箱</td>
							<td>
								<input class="easyui-validatebox" data-options="required:true,validType:'email'" type="text" name="email" style="width: 200px;"/>
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
