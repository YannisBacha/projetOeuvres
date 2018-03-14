<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Affichage de tous les adhérents</title>
</head>
<body>
	<div class="container">
		<A class="btn btn-info" href="index.htm" role="button">Retour Accueil</A>
		<P align="center">
			<FONT face="Arial" size="5" color="#004080"><U> <STRONG>Listing&nbsp;des
				Adhérents </STRONG></U></FONT>
		</P>

		<table class="table table-hover" BORDER="1">
			<tr>
				<th>Numero</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Ville</th>
				<th>Actions</th>

			</tr>

			<c:forEach items="${mesAdherents}" var="item">
				<tr>
					<td>${item.idAdherent}</td>
					<td>${item.nomAdherent}</td>
					<td>${item.prenomAdherent}</td>
					<td>${item.villeAdherent}</td>
					<td><a class="btn btn-info" href="#" role="button"><span class="glyphicon glyphicon-pencil"></span>Modifier</a>
					<a class="btn btn-danger" href="supprimerAdherent.htm?id=${item.idAdherent}" role="button"><span class="glyphicon glyphicon-remove-circle"></span>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>