<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
</head>
<body>
<h3>登录</h3>
<form action="/api/user/login" method="post">
	username:<input type="text" name="username" value="${username}"> <br>
	password:<input type="password" name="password" value="${password}"> <br>
	<button type="submit">登录</button><br>
	${errorMsg}
</form>
</body>
</html>