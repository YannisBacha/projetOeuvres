<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="refresh" content="0;URL=javascript:fermer();">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/oeuvre.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Expo : Médiathèque De POLYTECH</title>
</header>


<script language="JavaScript">
    function fermer() {

    }
</script>

<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="jumbotron">
        <div class="row">
            <div class="col-md-6">
                <img class="img-responsive imageAccueil"
                     src="<c:url value="/resources/images/polytech.jpg" />" alt="Logo Poly"/>
            </div>
            <div class="col-md-6">
                Bonjour et bienvenue sur le site de la médiathèque de Polytech.<br/>
                Ici vous pouvez retrouver l'ensemble des oeuvres possédées par l'école.<br/>
                <br/>
                L'ensemble de ces oeuvres peuvent être réservées.
            </div>
        </div>
    </div>
</div>

</body>
</html>