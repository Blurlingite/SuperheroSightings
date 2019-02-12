<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/SuperpowerJSPs/superpowerHome">Superpowers</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Persons</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organizations</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Locations</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sightings</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/userHome">Users</a></li>

                </ul>    
            </div>
                        
                        <h1>This site tracks information about Superhero and Supervillain sightings. You can report a sighting via the Sightings tab</h1>
            
                        <br>
                        <h2>Latest Sightings</h2>
            
            
            
                                <br><br>

            
            
            
                <c:forEach var="currentSighting" items="${lastTenSightings}">

                    <c:out value = "${currentSighting.person.firstName}"/>    <c:out value = "${currentSighting.person.lastName}"/>            AT          <c:out value = "${currentSighting.location.locationName}"/> ON                     <c:out value = "${currentSighting.justTheSightingDate}"/>   



                    <br><br>
                </c:forEach>
            
            
            
            
            
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

