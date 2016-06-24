<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>数据表编辑器</title>
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="./res/css/main.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./res/js/index.js"></script>
		<link rel="stylesheet" type="text/css" href="./res/css/prettify.css">
		<script type="text/javascript" src="./res/js/prettify.js"></script>
	</head>
	<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
		<div id="mainhead" region="north" border="false"
			style="background: #666; text-align: center">
			<div id="header-inner">
				<table cellpadding="0" cellspacing="0" style="width: 100%;">
					<tr>
						<td rowspan="2" style="width: 20px;"></td>
						<td style="height: 52px;">
							<div style="color: #fff; font-size: 22px; font-weight: bold;">
								<a href="./"
									style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">策划数据表编辑器</a>
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>
							<div style="float: right; color: #fff;">
								您好，
								<sec:authentication property="name" />
								！&emsp;
							</div>
						</td>
						<td>
							<div>
								<input id="changePswIn1" type="password" placeholder="旧密码" style="width: 80px"/>&nbsp;<input id="changePswIn2" type="password" placeholder="新密码" style="width: 80px"/>
								<a id="changePswBtn" href="#" class="easyui-linkbutton" data-options="plain:true" style="color: #ff0">修改</a>
								<a id="cancelPswBtn" href="#" class="easyui-linkbutton" data-options="plain:true" style="color: #ff0">取消</a>
								<a id="changePswLbl" href="#" class="easyui-linkbutton" data-options="plain:true" style="color: #ae6">修改密码</a>
								<a href="./auth/logout" class="easyui-linkbutton" data-options="plain:true" style="color: #ae6">安全退出</a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<div id="optTrees" region="west" border="false" split="true"
			title="&nbsp;" style="width: 200px; padding: 5px;">
		</div>
		<div region="center" border="false">
			<div id="tt" class="easyui-tabs" fit="true" border="false"
				plain="true">
				<div id="guideDiv" title="welcome" href="./res/guide.html">
				</div>
			</div>
		</div>
		<div id="treeMenu" class="easyui-menu" style="width: 120px;">
			<div id="exportInMenu">
				导出
			</div>
		</div>
	</body>
</html>


