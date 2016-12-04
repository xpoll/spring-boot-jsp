<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>user</title>
	<script type="text/javascript" src="/resources/js/util/jquery-3.1.1.min.js"></script>
</head>
<body>
	<jsp:include page="/include/title" />
	<h1>user</h1>
	<form id="dataForm">
		用户名:<input type="text" name="username" /><br>
		密码:<input type="password" name="password" /><br>
		角色:<input type="text" name="roles" /><br>
		名字:<input type="text" name="name" /><br>
		年龄:<input type="number" maxlength="2" name="age" /><br>
		<button>提交</button>
	</form>

<script type="text/javascript">
	$("#dataForm").submit(function(){
		$.ajax({
			url: '/api/user/add',
			type: 'post',
			data: $("#dataForm").serialize(),
			success: function(data){
				console.info(data)
			},
			error: function(){
				
			}
		})
		return false;
	});
</script>
</body>
</html>