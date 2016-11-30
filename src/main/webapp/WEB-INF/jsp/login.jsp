<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>login</title>
	<script type="text/javascript" src="/resources/js/util/jquery-3.1.1.min.js"></script>
	<link rel="icon" type="image/x-icon" href="/resources/image/lm.ico">
</head>
<body>
<h1>登录</h1>
<form id="dataForm">
	账号:<input type="text" name="username" value="${username}"> <br>
	密码:<input type="password" name="password" value="${password}"> <br>
	<img alt="验证码" src="/pcrimg"> <br>
	验证码:<input type="text" maxlength="4" name="code"> <br>
	<button type="submit">登录</button><br>
</form>
<div class="errorMsg" style="color:red;">
</div>
<script type="text/javascript">
	$("img").on('click', function(){
		$(this)[0].src = "/pcrimg?"+Math.random();
	});
	$("#dataForm").submit(function(){
		$.ajax({
			url: '/api/user/login',
			type: 'post',
			data: $("#dataForm").serialize(),
			success: function(data){
				console.info(data)
				if (data.success)
					location = "/index"
				$(".errorMsg").text(data.message)
				$("img").click()
			},
			error: function(){
				
			}
		})
		return false;
	});
</script>
</body>
</html>