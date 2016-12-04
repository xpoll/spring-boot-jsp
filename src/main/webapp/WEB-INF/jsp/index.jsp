<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
</head>
<body>
	<h3>hello</h3>
	<shiro:principal />
	<a href="/loginout">登出</a>
	<jsp:include page="/auth/tree"></jsp:include>
</body>
</html>