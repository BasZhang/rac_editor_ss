<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("error");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
		href="./res/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./res/css/main.css">
	<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
  </head>
  <body>
    <h3>用户登录</h3>
    <!-- from的action地址，以及用户名密码的name 。都是spring-security固定的。 -->
   	 <form action="<%=basePath %>j_spring_security_check" method="post">    
        <p>  
            <label for="j_username">Username</label> <input id="j_username"  
                name="j_username" type="text" />  
        </p>  
        <p>  
            <label for="j_password">Password</label> <input id="j_password"  
                name="j_password" type="password" />  
        </p>  
        <input type="submit" value="Login" />  
    </form>
    <%
    	if (error != null) {
    %>
		<a href="<%=basePath %>forgetPsw">忘记密码？</a>
    <%
    	}
    %>
  </body>
</html>
