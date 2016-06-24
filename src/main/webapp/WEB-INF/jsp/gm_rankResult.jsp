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

			<c:if test="${type == '3'}">
				<h3>
					地域排行：
				</h3>
				<h3>
					地区ID：${areaId}
				</h3>
			</c:if>
			
			<c:if test="${type == '2'}">
             <h3>好友排行：</h3>
            </c:if>
          
			<c:if test="${type == '1'}">
               <h3>全服排行：</h3>
             </c:if>
             
			
			<h3>
				赛事ID：${gate}
			</h3>

			<c:if test="${not empty uid}">
				<h3>
					用户ID：${uid}
				</h3>
				<h3>
					用户最佳时间：${data.selfBestTime}
				</h3>
				<h3>
					用户排名：${data.selfRank}
				</h3>
			</c:if>


			<table id="stat" border="1" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td width="50" align="center">
							名次
						</td>
						<td width="300" align="center">
							用户ID
						</td>
						<td width="150" align="center">
							用户昵称
						</td>
						<td width="50" align="center">
							用户等级
						</td>
						<td width="150" align="center">
							最佳时间
						</td>
					</tr>
					<c:forEach items="${data.rankList}" var="vector" varStatus="status">
						<tr>
							<td width="50" align="center">
								${status.count}
							</td>
							<td width="300" align="center">
								${vector.uid}
							</td>
							<td width="150" align="center">
								${vector.nickName}
							</td>
							<td width="50" align="center">
								${vector.level}
							</td>
							<td width="150" align="center">
								${vector.bestTime}
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

	</body>
</html>