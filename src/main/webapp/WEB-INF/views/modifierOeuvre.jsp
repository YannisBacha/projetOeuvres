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
    <title>Modification d'une oeuvre</title>
</head>
<script>
    function clearInput() {
        document.getElementById("titre").value = "";
        document.getElementById("prix").value = "";
    }
</script>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <h3>Modification de l'oeuvre <c:out value='${requestScope.monOeuvre.titreOeuvrevente}'/></h3>
    <div class="jumbotron">
        <div class="row">
            <form action="modificationOeuvre.htm" method="post">
                <input type="hidden" name="id" value="${monOeuvre.idOeuvrevente}">
                <div class="form-group">
                    <label for="titre">Titre de l'oeuvre</label>
                    <input type="text" class="form-control" name="titre" id="titre" required
                           value="${monOeuvre.titreOeuvrevente}"/>
                </div>
                <div class="form-group">
                    <label for="prix">Prix de l'oeuvre</label>
                    <input type="number" class="form-control" name="prix" id="prix" required
                           value="${monOeuvre.prixOeuvrevente}"/>
                </div>
                <div class="form-group">
                    <label for="proprietaire">Propriétaire de l'oeuvre</label>
                    <select class="form-control" name="proprietaire" id="proprietaire">
                        <c:forEach items="${mesProprietaires}" var="item">
                            <option value="${item.idProprietaire}">${item.prenomProprietaire} ${item.nomProprietaire}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
                <button type="button" class="btn btn-warning" onclick="clearInput()">Reset</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>