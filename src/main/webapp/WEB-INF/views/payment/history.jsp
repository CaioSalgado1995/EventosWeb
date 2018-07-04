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
<title>Histórico de compras</title>
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
					<a class="nav-link" href="${s:mvcUrl('PC#getAll').build()}">Histórico</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('CC#getAll').build()}">Carrinho</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h1>Histórico</h1>
		<c:if test="${emptySet}">
			<h2>Lista vazia!</h2>
		</c:if>
		<c:if test="${!emptySet}">
			<div class="row">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nome</th>
							<th scope="col">Local</th>
							<th scope="col">Valor</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${eventsPayed}" var="eventCart">
							<tr>
								<th scope="row">${eventCart.id}</th>
								<th>${eventCart.name}</th>
								<th>${eventCart.adress}</th>
								<th>${eventCart.price}</th>
								<th>P</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-md-4">
					<a class="btn btn-primary" href="${s:mvcUrl('EC#getAll').build()}">Listagem</a>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>