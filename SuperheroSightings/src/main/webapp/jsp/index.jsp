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
        <div class="container-fluid">
	
            <div class="row">
                <div class="col-md-12">
                    <h1>Superhero Sightings</h1>
                </div>
            </div>
 
            <hr/>
            
	<div class="row">
            <div class="col-md-12">
		<div class="row">
                    <div class="col-md-8">
                        
                        
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
                            
                            
                    </div>
                            
                    <div class="col-md-1">
                    </div>
                    <div class="col-md-3">
                    </div>    
                            
                            
                </div>
            </div>
        </div>
                            
                            
	<div class="row">
            <div class="col-md-12">
                <h1>This site tracks information about Superhero and Supervillain sightings. You can report a sighting via the Sightings tab</h1>
            </div>
	</div>         
                        
            
        <br>
        
	<div class="row">
            <div class="col-md-12">
			
                <h2>Latest Sightings</h2>
			
            </div>
	</div>

            
            
            
        <br><br>
        
        
                    
                    
<table id="LatestTenSightingsTable" class="table table-hover entityTable">
    <tr>
        <th width="40%">Name</th>
        <th width="30%">Location</th>
        <th width="30%">Date</th>
    </tr>
    
    
    
<c:forEach var="currentSighting" items="${lastTenSightings}">
    <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
    <tr>
        <td>
            <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
            <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayContactDetails) and the 
            ID of the contact (contactId=) dynamically
            The contactId is passed into the request here so that in the Contact Controller, the request you pass in has the contactId
            -->
            <a class="tableLinks" href="displayPersonDetails?thePersonId=${currentSighting.person.personId}">
            <c:out value="${currentSighting.person.firstName}"/><c:out value="${currentSighting.person.lastName}"/> 
            </a>
            
        </td>
        
        
        <td class ="tableText">
            <c:out value = "${currentSighting.location.locationName}"/>
        </td>
        
        
        <td class="tableText size15FontForCellsWithTooMuchText">
                <c:out value = "${currentSighting.justTheSightingDate}"/>   
        </td>
        

   
    </tr>
</c:forEach>
</table>
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
            
            
            <!--end of container-->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

