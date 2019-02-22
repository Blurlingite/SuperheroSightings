<%-- 
    Document   : editSighting
    Created on : Jan 14, 2019, 4:15:39 PM
    Author     : vishnukdawah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>
        
<div class="container-fluid relativeContainer">
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
                        <p>Hello : ${pageContext.request.userPrincipal.name}|                  
                            <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if>  
                            </div>
                            
                            
			</div>
                           			</div>
         
                            <div class ="col-md-12 centerContentInContainer">        
			<div class="row">
				<div class="col-md-2">
<!--                                    jjjj-->
				</div>
				
                                <div class="col-md-8" style = "background-color:yellow">
                                    <label for="theEditSightingForm" form="form1">Edit Sighting</label>
                                    
                                    <sf:form id = "theEditSightingForm" class="form-horizontal" role="form" modelAttribute="sightingToEdit"
                                            action="editSighting" method="POST">
                                        
                                        <div class="form-group">
                                            <label for="add-isHeroSighting" class="col-md-4 control-label">Sighting Type:</label>
                                            <div class="col-md-8">
                                                
                                                <sf:radiobutton path="isHeroSighting" value="true"/>Superhero 
                                                <sf:radiobutton path="isHeroSighting" value="false"/>Supervillain
                                                
<!--                                                < sf:input type="text" class="form-control" id="add-isHeroSighting"
                                                        path="isHeroSighting" placeholder="isHeroSighting"/>-->
                                                <%--<sf:errors path="isHeroSighting" cssclass="error"></sf:errors>--%>
                                            </div>
                                        </div>
               
                                        
                                        <div class="form-group">
                                            <label for="add-sightingDate" class="col-md-4 control-label">Sighting Date:</label>
                                            <div class="col-md-8">
                                                <sf:input type="date" class="form-control" id="add-sightingDate"
                                                        path="justTheSightingDate" placeholder="sightingDate"/>
                                                <%--<sf:errors path="sightingDate" cssclass="error"></sf:errors>--%>
                    
                                                <sf:hidden path="SightingId"/>

                                            </div>
                                        </div>
                                                
                                                
                                                
                                        <div class="form-group">
                                            <label for="add-Person" class="col-md-4 control-label">Person</label>
                                            <div class="col-md-8">
                        
                                                <select class ="form-control" name ="thePersonID">
                                                    <c:forEach var="currentPerson" items="${allPersons}">
                                
                                                    <!--Set 2 variables, one has the ID from the Sighting Controller (The personId that was passed in) and 
                                                    the other has the current person in the loop's ID. If they are equal, that option is selected in the dropdown-->
                                                        <c:set var = "thePreviousPerson"  scope = "session" value = "${previousPersonId}"/>
                                                        <c:set var = "idOfCurrentPerson"  scope = "session" value = "${currentPerson.personId}"/>

                                                <!--c choose lets you use the c when and c otherwise clauses to simulate an if else block
                                                    c when is like an if clause
                                                    c otherwise is like an else clause-->
                                                        <c:choose>
                                            
                                                            <c:when test = "${thePreviousPerson == idOfCurrentPerson}">
                                                                <option value = "${currentPerson.personId}" selected> ${currentPerson.firstName} ${currentPerson.lastName}</option>
                                                            </c:when>
                                 
                                                            <c:otherwise>
                                                                <option value = "${currentPerson.personId}"> ${currentPerson.firstName} ${currentPerson.lastName}</option>
                                                            </c:otherwise>
                                            
                                                        </c:choose>


                                                    </c:forEach>


                                                </select>
                            
                                                <%--<sf:errors path="Person" cssclass="error"></sf:errors>--%>
                                            </div>
                                        </div> 
                    
                    
                                        <div class="form-group">
                                            <label for="add-Location" class="col-md-4 control-label">Location</label>
                                            <div class="col-md-8">
                        
                                                <select class ="form-control" name ="theLocationID">
                            
                                                    <c:forEach var="currentLocation" items="${allLocations}">
                                
                                                        <c:set var = "thePreviousLocation"  scope = "session" value = "${previousLocationId}"/>
                                                        <c:set var = "idOfCurrentLocation"  scope = "session" value = "${currentLocation.locationId}"/>
                                
                                                        <c:choose>
                                            
                                                        <c:when test = "${thePreviousLocation == idOfCurrentLocation}">
                                                            <option value = "${currentLocation.locationId}" selected> ${currentLocation.locationName}</option>
                                                        </c:when>
                                 
                                                        <c:otherwise>
                                                            <option value = "${currentLocation.locationId}"> ${currentLocation.locationName}</option>
                                                        </c:otherwise>
                                            
                                                        </c:choose>
                                
                                                    </c:forEach>
                                        
                                                </select>
                                                    <%--<sf:errors path="Location" cssclass="error"></sf:errors>--%>
                                            </div>
                                        </div> 
                    
        
  
                                                
 

                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-8">
                                                <input type="submit" class="btn btn-default" value="Update Sighting"/>
                                            </div>
                                        </div>
                                    </sf:form> 
                                    
                                    
                                    
                                    
				</div>
                            
                            
				<div class="col-md-2">
<!--                                    hhhh-->
				</div>
                            
                            
			</div>
                                 			</div>

		<!--</div>-->
	</div>
</div>
        
        
        
        
        
    </body>
</html>
