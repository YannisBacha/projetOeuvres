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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Affichage de toutes les oeuvres</title>
</head>

<script>
    $(document).ready(function () {
        $('#modalReservation').on('show.bs.modal', function (e) {
            $(".modal-body #idOeuvre").val(e.relatedTarget.id);
        })
    });
</script>

<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <h3>Liste des Oeuvres</h3>
    <!-- Modal -->
    <div class="modal fade" id="modalReservation" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Réservation de l'oeuvre</h4>
                </div>
                <div class="modal-body">
                    <form action="reserverOeuvre.htm" method="post">
                        <div class="form-group">
                            <input type="hidden" name="idOeuvre" id="idOeuvre" value=""/>
                            <label for="adherent">Sélectionner un adhérent</label>
                            <select class="form-control" name="adherent" id="adherent">
                                <c:forEach items="${mesAdherents}" var="item">
                                    <option value="${item.idAdherent}">${item.prenomAdherent} ${item.nomAdherent}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <table class="table table-hover">
        <tr>
            <th class="col-md-3">Titre</th>
            <th class="col-md-1">Prix</th>
            <th class="col-md-2">Propriétaire</th>
            <th class="col-md-2">Etat</th>
            <th class="col-md-4">Actions</th>
        </tr>
        <c:forEach items="${mesOeuvres}" var="item">
            <tr>
                <td>${item.titreOeuvrevente}</td>
                <td>${item.prixOeuvrevente} €</td>
                <td>${item.proprietaireOeuvrevente.prenomProprietaire} ${item.proprietaireOeuvrevente.nomProprietaire}</td>
                <c:choose>
                    <c:when test="${item.etatOeuvrevente == 'R'}">
                        <td>Déjà Réservée</td>
                        <td><a class="btn btn-warning" href="cancelReservation.htm?id=${item.idOeuvrevente}"
                               role="button"><span
                                    class="glyphicon glyphicon-import"></span> Annuler</a>
                            <a class="btn btn-success" href="validReservation.htm?id=${item.idOeuvrevente}"
                           role="button"><span
                                class="glyphicon glyphicon-ok"></span> Valider</a></td>
                    </c:when>
                    <c:when test="${item.etatOeuvrevente == 'L'}">
                        <td>Réservation Possible</td>
                        <td><a class="btn btn-info" href="modifierOeuvre.htm?id=${item.idOeuvrevente}"
                               role="button"><span class="glyphicon glyphicon-pencil"></span> Modifier</a>
                            <button class="btn btn-success" data-toggle="modal" data-target="#modalReservation"
                                    id="${item.idOeuvrevente}" type="button"><span
                                    class="glyphicon glyphicon-export"></span> Réservation
                            </button>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>Vente réalisée</td>
                        <td><a class="btn btn-danger" href="cancelReservation.htm?id=${item.idOeuvrevente}"
                               role="button"><span
                                class="glyphicon glyphicon-remove-circle"></span> Reset</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

