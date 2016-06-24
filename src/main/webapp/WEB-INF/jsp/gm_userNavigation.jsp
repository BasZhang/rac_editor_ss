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
		<script type="text/javascript" src="./res/js/json_formatter.js"></script>
		<script type="text/javascript" src="./res/js/pop_resp.js"></script>
	</head>
	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询用户基本信息：</h3>
			<form class="pop-response" action="gm_queryPlayer" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>加钱：</h3>
			<form class="pop-response" action="gm_addVirtual" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<table>
				<tbody>
					<c:forEach var="currencyName" items="${currencyNames}">
					<tr>
						<td>
							${currencyName}<input type="hidden" name="ctypes" required="required" value="${currencyName}"/>
						</td>
						<td>
							<input type="text" name="amounts" required="required" value="0"/><br/>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>修改等级：</h3>
			<form class="pop-response" action="gm_changeLevel" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			等级&emsp;<input type="text" name="level" required="required"/><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询好友：</h3>
			<form class="pop-response" action="gm_queryFriends" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询赛车：</h3>
			<form class="pop-response" action="gm_queryCars" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询赛事：</h3>
			<form class="pop-response" action="gm_queryTours" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			<button type="submit">提交</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
		<h3>查询比赛：</h3>
			<form class="pop-response" action="gm_queryMatches" method="post">
			UID或昵称<input type="text" name="uid" required="required"/>
			关键字类型<select class="easyui-combobox"
					name="byNick" style="width: 50px;">
						<option value="true">昵称</option>
						<option value="false">UID</option>
			</select><br/>
			赛事ID&emsp;<input type="text" name="tid" required="required"/><br/>
			<button type="submit">提交</button>
			</form>
		</div>
	</body>
</html>