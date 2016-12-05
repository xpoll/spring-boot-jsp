<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>title</title>
</head>
<body>
	你好<shiro:user>,<shiro:principal property="name" /> <a href="/loginout">退出</a></shiro:user>
	<shiro:guest>
		<a href="/login">请登录</a>
	</shiro:guest>
	<a href="/index">首页</a>
</body>
</html>