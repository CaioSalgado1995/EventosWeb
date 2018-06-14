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
					<a class="nav-link" href="#">Carrinho</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<h2>${event.name}</h2>
		</div>
		<div class="row">
			<ul class="list-group">
				<li class="list-group-item">
 						${event.description}
 					</li>
 					<li class="list-group-item">
 						${event.adress}
 					</li>
 					<li class="list-group-item d-flex justify-content-between align-items-center">
					Número de vagas:
   					<span class="badge badge-primary badge-pill">${event.max}</span>
 					</li>
 					<li class="list-group-item d-flex justify-content-between align-items-center">
					Número de vagas restantes:
   					<span class="badge badge-primary badge-pill">${event.max}</span>
 					</li>
				<li class="list-group-item d-flex justify-content-between align-items-center">
					Preço:
   					<span class="badge badge-primary badge-pill">${event.price}</span>
 					</li>
				<li class="list-group-item">
					<a class="btn btn-primary" href="#">Comprar Ingresso</a>
					<a class="btn btn-primary" href="#">Favoritar</a>
				</li>
			</ul>
			
		</div>
	</div>
</body>
</html>