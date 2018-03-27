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
    <title>Modification d'un adhérent</title>
</head>

<script>
    function clearInput() {
        document.getElementById("nom").value = "";
        document.getElementById("prenom").value = "";
        document.getElementById("ville").value = "";
    }
</script>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <h3>Modification de l'adhérent <c:out value='${requestScope.monAdherent.nomAdherent}'/></h3>
    <div class="jumbotron">
        <div class="row">
            <form action="modificationAdherent.htm" method="post">
                <input type="hidden" name="id" value="${monAdherent.idAdherent}">
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input type="text" class="form-control" name="nom" id="nom" required
                           value="<c:out value='${requestScope.monAdherent.nomAdherent}'/>"/>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom</label>
                    <input type="text" class="form-control" name="prenom" id="prenom" required
                           value="${monAdherent.prenomAdherent}"/>
                </div>
                <div class="form-group">
                    <label for="ville">Ville</label>
                    <input type="text" class="form-control" name="ville" id="ville" required
                           value="${monAdherent.villeAdherent}"/>
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
                <button type="button" class="btn btn-warning" onclick="clearInput()">Reset</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>