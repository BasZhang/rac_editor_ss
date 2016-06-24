<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源版本浏览器</title>
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./res/js/deploy_browse.js"></script>
	</head>
	<body>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<table style="width: 100%">
				<tbody>
					<tr>
						<td>
							<c:forEach var="oneChannel" items="${channels}">
								<a href="#" class="easyui-linkbutton"
									data-options="toggle:true,group:'g1',plain:true"
									onclick="javascript:refreshPage('${oneChannel}')">${oneChannel}</a>
							</c:forEach>
						</td>
						<td class="right-align">
							<a id="pushButton" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-redo'"
								onclick="javascript:pushChannel()">推</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="resUploadMenu" style="width: 120px;"></div>
		</div>
		<table id="browseTree"></table>
	</body>
</html>


