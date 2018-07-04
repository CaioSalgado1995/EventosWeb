<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
<title>Listagem de Eventos</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light"> 
		<a	class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Eventos Curitiba</a>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('EC#getAll').build()}">Listagem</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('FC#getAll').build()}">Favoritos</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('CC#history').build()}">Histórico</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('CC#getAll').build()}">Carrinho</a>
				</li>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item">
						<a class="nav-link" href="${s:mvcUrl('EC#form').build()}">Cadastrar</a>
					</li>
				</security:authorize>
				<li class="nav-item">
					<a class="nav-link" href="/eventos/logout">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h1>Lista de Eventos</h1>
		<c:if test="${emptySet}">
			<h2>Lista vazia!</h2>
		</c:if>
		<c:if test="${!emptySet}">
			<div class="row">
				<form:form action="${s:mvcUrl('SC#search').build()}" method="post" commandName="search">
					<div class="form-group">
						<input class="form-control" type="text" id="search" name="search">
						<form:errors cssClass="text-danger" path="search"></form:errors>
					</div>
					<input type="submit" class="btn btn-primary" value="Pesquisar" style="margin-bottom: 10px;"></a>
				</form:form>
			</div>
			<div class="row" id="events">
				<c:forEach items="${events}" var="event">
					<div class="col-6 col-md-4" style="margin-bottom: 10px;">
						<div class="card">
							<div class="card-header">${event.name}</div>
							<div class="card-body">
								<h5 class="card-title">${event.date}</h5>
								<p class="card-text">${event.description}</p>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									<a class="btn btn-primary" href="${s:mvcUrl('EC#remove').arg(0, event.id).build()}">Remover</a>
								</security:authorize>
								<a href="${s:mvcUrl('EC#detail').arg(0, event.id).build()}" class="btn btn-primary">Saiba mais</a>
								<input type="hidden" value="${event.id}"/>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</body>
</html>