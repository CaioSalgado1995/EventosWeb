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
<title>Cadastrar Evento - Form</title>
</head>
<body>
	<div class="container">
		<form:form action="${s:mvcUrl('EC#create').build()}" method="post" commandName="event">
			<div class="form-group">
				<label for="name">Nome:</label>
				<input type="text" class="form-control" id="name" name="name">
				<form:errors cssClass="text-danger" path="name"></form:errors>
			</div>
			<div class="form-group">
				<label for="description">Descrição:</label>
				<input type="text" class="form-control" id="description" name="description">	
				<form:errors cssClass="text-danger" path="description"></form:errors>
			</div>
			<div class="form-group">
				<label for="capacity">Valor:</label>
				<input type="number" class="form-control" id="capacity" name="capacity">
				<form:errors cssClass="text-danger" path="capacity"></form:errors>
			</div>
			<div class="form-group">
				<label for="date">Data do evento:</label>
				<input type="date" class="form-control" id="date" name="date">
				<form:errors cssClass="text-danger" path="date"></form:errors>
			</div>
			<div class="form-group">
				<label for="value">Valor:</label>
				<input type="text" class="form-control" id="value" name="value">
				<form:errors cssClass="text-danger" path="value"></form:errors>
			</div>
			<div class="form-group">
				<label for="adress">Endereço:</label>
				<input type="text" class="form-control" id="adress" name="adress">
			</div>
			
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>