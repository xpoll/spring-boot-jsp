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
	<a href="/loginout">登出</a>
	<shiro:principal />
	<shiro:hasRole name="admin">
		我有角色admin
	</shiro:hasRole>
	<shiro:hasRole name="normal">
		我有角色normal
	</shiro:hasRole>
	<shiro:hasPermission name="admin_permissions">
		我有权限admin_permissions
	</shiro:hasPermission>
	<shiro:hasPermission name="normal_permissions">
		我有权限normal_permissions
	</shiro:hasPermission>
	<jsp:include page="/auth/tree"></jsp:include>
</body>
</html>