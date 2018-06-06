<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-grid.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-grid.min.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-reboot.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap-reboot.min.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
<title>Cadastro - Form</title>
</head>
<body>
	<div class="container">
	
		<h1>Cadastro</h1>
		<form:form action="${s:mvcUrl('RC#create').build()}" method="post" commandName="user">
			<div class="form-group">
				<label for="name">Nome:</label>
				<input type="text" class="form-control" id="name" name="name">
				<form:errors cssClass="text-danger" path="name"></form:errors>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="text" class="form-control" id="email" name="email">	
				<form:errors cssClass="text-danger" path="email"></form:errors>
			</div>
			<div class="form-group">
				<label for="document">Documento:</label>
				<input type="text" class="form-control" id="document" name="document">
				<form:errors cssClass="text-danger" path="document"></form:errors>
			</div>
			<div class="form-group">
				<label for="password">Senha:</label>
				<input type="text" class="form-control" id="password" name="password">
				<form:errors cssClass="text-danger" path="password"></form:errors>
			</div>
			
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>