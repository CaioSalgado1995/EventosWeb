<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link type="text/css" href="<c:url value='/resources/css/bootstrap-grid.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-grid.min.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-reboot.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-reboot.min.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form:form action="${s:mvcUrl('LC#login').build() }" method="post" commandName="user">
			<div class="form-group">
				<label for="username">Email:</label>
				<input type="text" class="form-control" id=username name="username">
			</div>
			<div class="form-group">
				<label for="password">Senha:</label>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			<div class="row">
				<div class="col">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
				<div class="col">
					<a class="btn btn-primary" href="${s:mvcUrl('RC#register').build()}">Cadastro</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>