<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GM邮件工具</title>
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
		<h3>查询用户邮件箱：</h3>
			<form class="pop-response" action="ggm_queryBox" method="post">
			UID&emsp;<input type="text" name="uid" required="required"/>（若查系统邮件填 System_mail）
			<br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询邮件：</h3>
			<form class="pop-response" action="ggm_queryMail" method="post">
			邮件ID&emsp;<input type="text" name="mailId" required="required"/><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>删除全服邮件：</h3>
			<form class="pop-response" action="ggm_delMail" method="post">
			邮件ID&emsp;<input type="text" name="mailId" required="required"/><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>发送全服邮件：</h3>
			<p>
			在有效期前登陆的玩家都能看到，全服邮件。过了有效期看不到。
			</p>
			<form class="pop-response" action="ggm_sendMany" method="post">
			发送者&emsp;<input type="text" name="sender" required="required"/><br/>
			标题&emsp;<input type="text" name="title"/><br/>
			内容&emsp;<input type="text" name="content"/><br/>
			游戏币&emsp;<input type="text" name="coin" required="required"/><br/>
			充值币&emsp;<input type="text" name="money" required="required"/><br/>
			声望&emsp;<input type="text" name="prestige" required="required"/><br/>
			有效期至&emsp;
			<input name="validThru" style="width: 150px" class="text-input-small"
					type="text"
					onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" value="" /><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>发送私人邮件：</h3>
			<form class="pop-response" action="ggm_sendMail" method="post">
			发送者&emsp;<input type="text" name="sender" required="required"/><br/>
			接收者UID&emsp;<input type="text" name="uid" required="required"/><br/>
			标题&emsp;<input type="text" name="title"/><br/>
			内容&emsp;<input type="text" name="content"/><br/>
			游戏币&emsp;<input type="text" name="coin" required="required"/><br/>
			充值币&emsp;<input type="text" name="money" required="required"/><br/>
			声望&emsp;<input type="text" name="prestige" required="required"/><br/>
			<button type="submit">提交</button>
			</form>
		</div>
	</body>
</html>