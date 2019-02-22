<%-- 
    Document   : displayPersonDetails
    Created on : Dec 29, 2018, 6:26:32 PM
    Author     : vishnukdawah
--%>

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
        <title>Person Details</title>
                <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
                <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
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
                        <p>Hello : ${pageContext.request.userPrincipal.name}
        |                   <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if>  
		
                            </div>
                                    
			</div>
                                    
			<div class="row">
				<div class="col-md-12">
                                    <div class="row">
                                        
					<div class="col-md-8">
                                        </div>
                                        
					<div class="col-md-4">
                                            <a href ="displayEditPersonForm?personId=${personDetails.personId}" type="button" class="btn btn-success">
                                                Edit This Person
                                            </a>
					</div>
                                        
                                    </div>
				</div>
			</div>
                                    
			<div class="row">
                            <div class="col-md-12">
				<h3 class = "DtoDisplayLabel">
                                    Person Details for ${personDetails.firstName} ${personDetails.lastName}
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
							All Organizations
                                                    </h3>
                                                    
                                                    <table id="organizationsForPersonTable" class="table table-hover">
                                                        
                                                    <br>
                                                        
                                                        <tr>
                                                            <th width="25%">Organization Name</th>
                                                            <th width="25%">Organization Type</th>
                                                            <th width="25%">Organization Description</th>
                                                            <th width="25%">Organization Address</th>

                                                        </tr>
                                                        
                                                        <c:forEach var="currentOrganization" items="${organizationDetailsForPerson}">

                                                            <tr>
                                                                
                                                                <td>
                                                                   <a href="displayOrganizationDetails?theOrganizantionId=${currentOrganization.organizationId}">

                                                                        <c:out value="${currentOrganization.organizationName}"/>
                                                                   </a>
                                                                </td>
                                                                
                                                                <td>
                                                                    
                                                                        <c:set var = "heroOrNot" scope = "session" value = "${currentOrganization.isItAHeroOrganization}"/>
      
                                                                            <c:if test = "${heroOrNot == true}">
         
                                                                            <p><c:out value = "Superhero"/><p>
      
                                                                            </c:if>
             
                   
                                                                            <c:if test = "${heroOrNot == false}">
         
                                                                                <p><c:out value = "Supervillian"/><p>
      
                                                                            </c:if>
                                                                    
                                                                    
                                                                </td>
                                                                
                                                                
                                                                <td>
                                                                    <c:out value="${currentOrganization.organizationDescription}"/>
                                                                </td>  
                                                                
                                                                <td>
                                                                    <c:out value="${currentOrganization.organizationStreet}"/> <c:out value="${currentOrganization.organizationCity}"/> <c:out value="${currentOrganization.organizationState}"/>, <c:out value="${currentOrganization.organizationZipcode}"/> <c:out value=" ${currentOrganization.organizationCountry}"/>
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
                                    
                                    
					<div class="row">
						<div class="col-md-12">
							<div class="row">
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-8">
								<h3 class = "miniTableTextAlign">
                                                                   All Superpowers
								</h3>
                                                                
                                                                
                                                    <table id="superpowersForPersonTable" class="table table-hover">
                                                        
                                                        
                                                        <tr>
                                                            <th width="50%">Superpower Name</th>
                                                            <th width="50%">Superpower Description</th>

                                                        </tr>        
                                                        
                                                        
                                                        <br>
                                                        
                                                        <c:forEach var="currentPower" items="${superpowerDetailsForPerson}">


                                                            <tr>
                                                                
                                                                <td>
                                                                   <a href="SuperpowerJSPs/displaySuperpowerDetails?theSuperpowerId=${currentPower.superpowerId}">
                                                                        <c:out value ="${currentPower.superpowerName}"/>
                                                                   </a>
                                                                </td>
                                                                
                                                                <td>
                                                                    <c:out value ="${currentPower.superpowerDescription}"/>
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
                                        
                                        
                                        
                                        
					<div class="row">
						<div class="col-md-12">
							<div class="row">
                                                            
                                                            <div class="col-md-2">
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-8">
								<h3 class = "miniTableTextAlign">
                                                                   All Locations This Person Was Sighted
								</h3>
                                                                
                                                                
                                                    <table id="superpowersForPersonTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="26%">Location Name</th>
                                                            <th width="26%">Location Address</th>
                                                            <th width="26%">Location Description</th>
                                                            <th width="11%"></th>
                                                            <th width="11%"></th>
                                                        </tr>       
                                                        
                                                        
                                                        <br>
                                                        
                                                            
                                                        <c:forEach var="currentLocation" items="${allLocationsForPerson}">
                                                            
                                                        <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
        
                                                        <tr>
           
                                                            <td>

                                                                <a href="displayLocationDetails?theLocationId=${currentLocation.locationId}">
                                                                    <c:out value="${currentLocation.locationName}"/> 
                                                                </a>
            
                                                            </td>

                                                            
                                                            <td>
               
                                                                <c:out value ="${currentLocation.locationStreet} ${currentLocation.locationCity} ${currentLocation.locationState} ${currentLocation.locationZipcode} ${currentLocation.locationCountry}"/> 
               
                                                                <br><br>
               
                                                                <c:out value ="Latitude: ${currentLocation.latitude}"/> 
               
                                                                <br>
               
                                                                <c:out value ="Longitude: ${currentLocation.longitude}"/>

            
                                                            </td>
            
        
            
                                                            <td>
                
                                                                <c:out value="${currentLocation.locationDescription}"/>

            
                                                            </td>

            
                                                            <td>
            

<!--                                                                <sec:authorize access="hasRole('ROLE_ADMIN')">

                
                                                                    <a href="displayEditLocationForm?theIdOfLocation=$ {currentLocation.locationId}">
               
                                                                        Edit
                
                                                                    </a>
                        
                                                                </sec:authorize>-->

            
                                                            </td>
            
            
            
                                                            <td>
                                                                
                      
<!--                                                                <sec:authorize access="hasRole('ROLE_ADMIN')">
  
            
                                                                    <a href="deleteLocation?theLocationId=$ {currentLocation.locationId}">
            
                                                                        Delete
            
                                                                    </a>
                    
                                                                </sec:authorize>-->

            
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

