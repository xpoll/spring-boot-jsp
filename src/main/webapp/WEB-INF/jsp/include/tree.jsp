<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>tree</title>
	<script type="text/javascript" src="/resources/js/util/jquery-3.1.1.min.js"></script>
	<style type="text/css">
		.auth {
			display: block;
			width: 202px;
			border: 1px solid red;
		}
		.auth1 {
			display: block;
			width: 200px;
			border: 1px solid white;
		}
		.auth1 span {
			display: block;
			width: 100%;
			height: 100%;
		}
		.auth2 {
			display: block;
			width: 198px;
			height: 25px;
			border: 1px solid white;
			line-height: 25px;
		}
		.auth2 span {
			display: block;
			width: 100%;
			height: 100%;
		}
	</style>
</head>
<body>
	<h1>tree</h1>
	<div class="auth">
		<c:forEach items="${authList}" varStatus="i" var="item1">
		<div class="auth1">
			<span>
				<a href='${item1.value.resources}' data-type='${item1.key}'>${item1.value.name}</a><br>
			</span>
			<c:forEach items="${item1.value.children}" varStatus="j" var="item2">
			<div class="auth2">
				<span>
					<a href='${item2.value.resources}' data-type='${item2.key}'>${item2.value.name}</a><br>
				</span>
			</div>
			</c:forEach>
		</div>
		<br>
		</c:forEach>
	</div>
	<script type="text/javascript" src="/resources/js/tree.js"></script>
</body>
</html>