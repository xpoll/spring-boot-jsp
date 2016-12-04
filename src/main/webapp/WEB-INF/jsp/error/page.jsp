<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>errors</title>
</head>
<body>
	<jsp:include page="/include/title" />
	<h1>errors</h1>
    <div>
    	${url}
    </div>
    <div>
    	${exception.message}
    </div>
    <div>
    	${info}
    </div>
    <div>
    	${exception}
    </div>
</body>
</html>