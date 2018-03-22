<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/oeuvre.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Affichage de toutes les oeuvres</title>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <h3>Liste des Oeuvres</h3>
    <table class="table table-hover">
        <tr>
            <th class="col-md-3">Titre</th>
            <th class="col-md-2">Prix</th>
            <th class="col-md-2">Propriétaire</th>
            <th class="col-md-2">Etat</th>
            <th class="col-md-3">Actions</th>
        </tr>
        <c:forEach items="${mesOeuvres}" var="item">
            <tr>
                <td>${item.titreOeuvrevente}</td>
                <td>${item.prixOeuvrevente} €</td>
                <td>${item.proprietaireOeuvrevente.prenomProprietaire} ${item.proprietaireOeuvrevente.nomProprietaire}</td>
                <td>${item.etatOeuvrevente == "R" ? "Déjà Réservée" : "Réservation Possible"}</td>
                <td><a class="btn btn-info" href="modifierOeuvre.htm?id=${item.idOeuvrevente}" role="button"><span
                        class="glyphicon glyphicon-pencil"></span> Modifier</a>
                    <a class="btn btn-success" href="reserverOeuvre.htm?id=${item.idOeuvrevente}" role="button"><span
                            class="glyphicon glyphicon-tags"></span> Réservation</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>