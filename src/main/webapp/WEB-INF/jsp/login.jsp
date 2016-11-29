<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
<script type="text/javascript" src="/resources/js/util/jquery-3.1.1.min.js"></script>
</head>
<body>
<h1>登录</h1>
<form action="/api/user/login" method="post">
	账号:<input type="text" name="username" value="${username}"> <br>
	密码:<input type="password" name="password" value="${password}"> <br>
	<img alt="验证码" src="/pcrimg"> <br>
	验证码:<input type="text" maxlength="4" name="code"> <br>
	<button type="submit">登录</button><br>
	${msg}
</form>
<script type="text/javascript">
	$("img").on('click', function(){
		$(this)[0].src = "/pcrimg?"+Math.random();
	});
</script>
</body>
</html>