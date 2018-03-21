<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/oeuvre.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Affichage de tous les adhérents</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp"/>
		<P align="center" class="titre">
		<h3>Liste des Adhérents</h3>
		</P>

		<table class="table table-hover">
			<tr>
				<th class="col-md-1">Numero</th>
				<th class="col-md-2">Nom</th>
				<th class="col-md-2">Prénom</th>
				<th class="col-md-4">Ville</th>
				<th class="col-md-3">Actions</th>

			</tr>

			<c:forEach items="${mesAdherents}" var="item">
				<tr>
					<td>${item.idAdherent}</td>
					<td>${item.nomAdherent}</td>
					<td>${item.prenomAdherent}</td>
					<td>${item.villeAdherent}</td>
					<td><a class="btn btn-info" href="modifierAdherent.htm?id=${item.idAdherent}" role="button"><span
							class="glyphicon glyphicon-pencil"></span>Modifier</a>
					<a class="btn btn-danger" href="supprimerAdherent.htm?id=${item.idAdherent}" role="button"><span class="glyphicon glyphicon-remove-circle"></span>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>