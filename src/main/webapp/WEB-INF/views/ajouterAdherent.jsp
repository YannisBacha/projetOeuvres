<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/oeuvre.css' />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Ajouter un adhérent</title>
</head>
<SCRIPT language="Javascript" type="text/javascript">
    <
    script
    type = "text/javascript"
    src = "js/foncControle.js" ></script>

<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <h3>Ajout d'un adhérent</h3>
    <div class="jumbotron">
        <div class="row">
            <form action="insererAdherent.htm" method="post" onsubmit="return teste()">
                <div class="form-group">
                    <label for="nom">Nom de l'adhérent</label>
                    <input type="text" class="form-control" name="nom" id="nom"/>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom de l'adhérent</label>
                    <input type="text" class="form-control" name="prenom" id="prenom"/>
                </div>
                <div class="form-group">
                    <label for="ville">Ville de l'adhérent</label>
                    <input type="text" class="form-control" name="ville" id="ville"/>
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>