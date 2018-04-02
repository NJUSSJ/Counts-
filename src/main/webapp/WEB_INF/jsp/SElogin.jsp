<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>众包标注系统登录</title>
		<link rel="stylesheet" type="text/css" href="temp.css" />
	</head>
	<body>
		<section>
			<h1>众包标注系统</h1>
			<c:if test="${!empty error}">
	   		     <font color="red" align="center"><c:out value="${error}" /></font>
			</c:if>
			<form action="<c:url value="loginCheck.html"/>" method="post">
				用户名：
				<input type="text" name="userName">
				<br>
				密 码：
				<input type="password" name="password">
				<br>
				<input type="submit" value="登录" />
				<input type="reset" value="重置" />
			</form>
		</section>
	</body>
</html>
