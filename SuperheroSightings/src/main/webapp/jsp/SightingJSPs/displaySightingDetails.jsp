<%-- 
    Document   : displaySightingDetails
    Created on : Jan 14, 2019, 2:06:26 PM
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
        <title>Sighting Details</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
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
                                            <a href ="displayEditSightingForm?sightingId=${sightingToDisplay.sightingId}" type="button" class="btn btn-success">
                                                Edit This Sighting
                                            </a>
					</div>
                                                
                                    </sec:authorize>
                                        
                                    </div>
				</div>
			</div>
                                    
			<div class="row">
                            <div class="col-md-12">
				<h3 class = "DtoDisplayLabel">
                                    <!--Sighting Details for $ {sightingToDisplay.sightingDate}-->
                                     Sighting Details for ${sightingToDisplay.justTheSightingDate}

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
                                                    <h3 class = "miniTableTextAlign">
							Person
                                                    </h3>
                                                    
                                                    <table id="sightingTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="25%">Person Name</th>
                                                            <th width="25%">Person Type</th>
                                                            <th width="25%">Superpowers</th>
                                                            <th width="25%">Person Description</th>
                                                        </tr>
                                                        
                                                            
                                                            <tr>
                                                                
                                                                <td>
                                                                    <a href="displayPersonDetails?thePersonId=${sightingToDisplay.person.personId}">
                                                                    <c:out value="${sightingToDisplay.person.firstName}"/> <c:out value="${sightingToDisplay.person.lastName}"/>
                                                                    </a>
                                                                </td>
                                                                
                                                                <td>
                                                                    
                
                                                                    <c:set var = "heroOrNot" scope = "session" value = "${sightingToDisplay.person.isHero}"/>
      
                                                                        <c:if test = "${heroOrNot == true}">
        
                                                                        <p><c:out value = "Superhero"/><p>
      
                                                                        </c:if>
             
                   
                                                                            <c:if test = "${heroOrNot == false}">
         
                                                                            <p><c:out value = "Supervillian"/><p>
      
                                                                            </c:if>
                                                                    
                                                                </td>  
                                                                
                                                                <td>
                                                                    <c:forEach var="currentSuperpower" items="${personPowers}">
                                                                        <c:out value="${currentSuperpower.superpowerName}"/> 
                                                                        <br>

                                                                    </c:forEach>

                                                                </td>   
                                                                
                                                                <td>
                                                                    <c:out value="${sightingToDisplay.person.descriptionOfPerson}"/> 
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
                                                                    Location
								</h3>
                                                                
                                                                
                                                    <table id="sightingTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="25%">Location Name</th>
                                                            <th width="25%">Location Address</th>
                                                            <th width="50%">Location Description</th>
                                                        </tr>
                                                        
                                                            
                                                            <tr>
                                                                
                                                                <td>
                                                                  <a href="displayLocationDetails?theLocationId=${sightingToDisplay.location.locationId}">
                                                                    <c:out value="${sightingToDisplay.location.locationName}"/> 
                                                                  </a>
                                                                </td>
                                                                
                                                                <td>
                                                                    <c:out value="${sightingToDisplay.location.locationStreet}"/> <c:out value="${sightingToDisplay.location.locationZipcode}"/> <c:out value="${sightingToDisplay.location.locationCity}"/>, <c:out value="${sightingToDisplay.location.locationState}"/> <c:out value="${sightingToDisplay.location.locationCountry}"/>
                                                                    <br><br>
                                                                    Latitude: <c:out value="${sightingToDisplay.location.latitude}"/> Longitude: <c:out value="${sightingToDisplay.location.longitude}"/>
                                                                </td>  
                                                                

                                                                
                                                                <td>
                                                                    <c:out value="${sightingToDisplay.location.locationDescription}"/> 
                                                                </td>                                          
                                                                
                                                                
                                                            </tr>
                                                            
                                                    </table>      
                                                                
                                                                
                                                                
                                                                
                                                                
                                                                
                                                                
                                                                
                                                            </div>
								
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
							</div>
						</div>
					</div>
                                                                
                                                                
                                     
                                                                
                                       
                                             <!--All Sightings By Date-->                   
                                                                
                                                                
					<div class="row">
						<div class="col-md-12">
							<div class="row">
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-8">
								<h3 class = "miniTableTextAlign">
                                                                    All Sighting For This Date
								</h3>
                                                                
                                                                
                        <table id="sightingTable" class="table table-hover">
                            <tr>
                                <th width="26%">Date</th>
                                <th width="26%">Name</th>
                                <th width="26%">Location</th>
                            </tr>



                            <c:forEach var="currentSighting" items="${displaySightingsByACertainDate}">

                        <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
                        
                     
                                <tr>

                                    <td>
                                        <a href="displaySighting?sightingId=${currentSighting.sightingId}">
                                            <%--<c:out value="$ {currentSighting.sightingDate}"/>--%>
                                           <c:out value="${sightingToDisplay.justTheSightingDate}"/>

                                        </a>
                                    </td>
                                    
                                    
                                    
                                    <td>
                                    <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
                                    <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayContactDetails) and the
                                    ID of the contact (contactId=) dynamically
                                    The contactId is passed into the request here so that in the Contact Controller, the request you pass in has the contactId
                                    -->
                                    <a href="displayPersonDetails?thePersonId=${currentSighting.person.personId}"><c:out value="${currentSighting.person.firstName}"/><c:out value="${currentSighting.person.lastName}"/></a>
                                    </td>


                                    <td>
                                        <a href="displayLocationDetails?theLocationId=${currentSighting.person.personId}"><c:out value="${currentSighting.location.locationName}"/></a>
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
