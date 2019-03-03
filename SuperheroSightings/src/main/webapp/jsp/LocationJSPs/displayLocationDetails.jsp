<%-- 
    Document   : displayLocationDetails
    Created on : Dec 31, 2018, 2:16:21 PM
    Author     : vishnukdawah
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location Details</title>
                <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
                <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <hr/>

            <div class="row">
		<div class="col-md-12">
			<div class="row">
                            <div class="col-md-7">
                                
                                
					 
                                <div class="navbar">
                                    <ul class="nav nav-tabs">
                                        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
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
                                    
                                    
                            <div class="col-md-4">
                                
                                
	                          <!--If the user is logged in, (the name is not null) greet them and show the log out link-->
                                <c:if test="${pageContext.request.userPrincipal.name != null}">
                                    <p>Hello : ${pageContext.request.userPrincipal.name} | 
                                        <a href="<c:url value="/j_spring_security_logout" />" >
                                            Logout
                                        </a>
                                    </p>
                                </c:if>
                                    
		
                            </div>
                                    
			</div>
                                    
			<div class="row">
                            <div class="col-md-12">
                                <div class="row">
                                        
                                    <div class="col-md-8">
                                    </div>
                                    
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">

                                        <div class="col-md-4">
                                            
                                            <a href ="displayEditLocationForm?theIdOfLocation=${locationToDisplay.locationId}" type="button" class="btn btn-success">
                                                Edit This Location
                                            </a>
                                                
                                        </div>
                                                
                                    </sec:authorize>
                                        
                                </div>
                            </div>
			</div>
                                                
                                                
                                    
			<div class="row">
                            <div class="col-md-12">
                                
                                
				<h3 class = "DtoDisplayLabel">
                                    Location Details for <c:out value ="${locationToDisplay.locationName}"/>
				</h3>
                                
                                
                            </div>
			</div>
                                
                                
                                    
			<div class="row">
                            
                            
				<div class="col-md-2">
				</div>
                            
                            
				<div class="col-md-8 outerBoxOnDetailsPage">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="row">
                                                
                                                
						<div class="col-md-2">
						</div>
                                                
                                                
						<div class="col-md-8">

                                                    <table id="superpowersForPersonTable" class="table table-hover">
                                                        
                                                        
                                                        <tr>
                                                            <th width="35%">Location Name</th>
                                                            <th width="35%">Location Address</th>
                                                            <th width="30%">Location Description</th>


                                                        </tr>        
                                                        
                                                        
                                                        <br>
                                                        


                                                        <tr>
                                                                
                                                            <td>
                                                                <c:out value ="${locationToDisplay.locationName}"/>
                                                            </td>
                                                                
                                                            
                                                            <td>
                                                                <c:out value ="${locationToDisplay.locationStreet} ${locationToDisplay.locationCity} ${locationToDisplay.locationState} ${locationToDisplay.locationZipcode} ${locationToDisplay.locationCountry}"/>
                                                                
                                                                <br><br>
                                                                
                                                                <c:out value ="Latitude: ${locationToDisplay.latitude}"/>
                                                                    
                                                                <br>
                                                                    
                                                                <c:out value ="Longitude: ${locationToDisplay.longitude}"/>
                                                                
                                                            </td>
                                                                
                                                                
                                                            <td>
                                                                <c:out value ="${locationToDisplay.locationDescription}"/>
                                                            </td>            
                                                                
                                                        </tr>
                                                            

                                                    </table>   
                                                    

                                                </div>
                                                
                                                
						<div class="col-md-2">
						</div>
                                                            
                                                            
                                            </div>
					</div>
                                    </div>
                                    
                                    
					<div class="row">
						<div class="col-md-12">
							<div class="row">
                                                            
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-8">
                                                                
                                                                
								<h3 class = "miniTableTextAlign">
                                                                   All Sightings At This Location
								</h3>
                                                                
                                                                
                                                                <table id="sightingTable" class="table table-hover">
                                                                    
                                                                    <tr>
                                                                        <th width="36%">Date</th>
                                                                        <th width="36%">Name</th>
                                                                    </tr>



                                                                    <c:forEach var="currentSighting" items="${allSightingsByLocation}">

                                                                    <!--the forEach adds an extra row for each sighting in the allSightingsByLocation (list of sightings) with these td tags-->
                        
                     
                                                                        <tr>

                                                                            <td>
                                                                                <a href="displaySighting?sightingId=${currentSighting.sightingId}">
                                                                                    <c:out value="${currentSighting.justTheSightingDate}"/>
                                                                                </a>
                                                                            </td>
                                    
                                    
                                    
                                                                            <td>
                                                                            <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
                                                                            <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayPersonDetails) and the
                                                                            ID of the person (thePersonId=) dynamically
                                                                            The person Id is passed into the request here so that in the Person Controller, the request you pass in has the person Id
                                                                            
                                                                            currentSighting.person.firstName
                                                                            currentSighting is the current Sighting DTO
                                                                            person is the Person DTO on that Sighting DTO
                                                                            firstName is the field on the PersonDTO
                                                                            
                                                                            We access the firstname from Person DTO that is from SightingDTO 
                                                                            -->
                                                                                <a href="displayPersonDetails?thePersonId=${currentSighting.person.personId}">
                                                                                    <c:out value="${currentSighting.person.firstName}"/>
                                                                                    <c:out value="${currentSighting.person.lastName}"/>
                                                                                </a>
                                                                            </td>


                                                                        </tr>
                                                                    </c:forEach>
                                                                        
                                                                        
                                                                </table>    
                                                                
                                                                
                                                                
                                                            </div>
								
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
							</div>
						</div>
					</div>
				</div>
				
                                                            
                                <div class="col-md-2">
				</div>
                                                            
			</div>
		</div>
            </div>
        </div>
    </body>
</html>

