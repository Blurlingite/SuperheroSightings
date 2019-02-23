<%-- 
    Document   : sighting
    Created on : Jan 14, 2019, 1:07:27 PM
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
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sightings</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightingHome">Sightings</a></li>
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
                            <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if> 
                    </div>
		</div>


                <div class = col-md-12>
                    <div class="col-md-4">

                        <h2 class = "addEntityForm">
                            Add A Sighting
                        </h2>


                <!--The action tells us which controller endpoint to go to-->
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="addSighting">
               
                            
               
                            <div class="form-group col-md-12">
                                
                                <div class="col-md-4">
                                    <label for="add-isHeroSighting" class="col-md-4 control-label">Sighting Type</label>
                                </div>
                                
<!--                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="isHeroSighting" placeholder="isHeroSighting"/>
                                </div>-->
                                
                                <div class="col-md-8">
                    
                                <input type="radio" name="isHeroSighting" value="true"> Superhero<br>  <input type="radio" name="isHeroSighting" value="false"> Supervillain<br>
                       
                                </div>
                                
                            </div>
                            
                            
                            
                            <div class="form-group col-md-12">
                                
                                <div class="col-md-4">
                                    <label for="add-sightingDate" class="col-md-4 control-label">Sighting Date:</label>
                                </div>

                                <div class="col-md-8">
                                    <input type="date" class="form-control" name="justTheSightingDate" placeholder="Sighting Date:"/>
                                </div>
                                
                            </div>
               
               
                            <div class="form-group col-md-12">
                                
                                <div class="col-md-4">
                                    <label for="add-Person" class="col-md-4 control-label">Person</label>
                                </div>
                                
                                <div class="col-md-8">
                                    <select class ="form-control" name ="thePersonID">
                                        <c:forEach var="currentPerson" items="${allPersons}">
                                            <option value = "${currentPerson.personId}"> ${currentPerson.firstName} ${currentPerson.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                    
                    
                    
                            <div class="form-group col-md-12">
                                
                                <div class="col-md-4">
                                    <label for="add-Person" class="col-md-4 control-label">Location</label>
                                </div>
                                
                                <div class="col-md-8">
                                    <select class ="form-control" name ="theLocationID">
                                        <c:forEach var="currentLocation" items="${availableLocations}">
                                            <option value = "${currentLocation.locationId}">${currentLocation.locationName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>


                            <div class="form-group col-md-12">
                                <br>
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Report Sighting"/>
                                </div>
                            </div>
               
               
               
               
                        </form>



                    </div>








                    <div class="col-md-1">
                    </div>
                    
                    
                    <div class="col-md-7">
                        
                        
			<h2>
                            Sightings
			</h2>
                        
                        
                        <table id="sightingTable" class="table table-hover entityTable">
                            <tr>
                                <th width="26%">Date</th>
                                <th width="5">Type</th>
                                <th width="26%">Name</th>
                                <th width="26%">Location</th>
                                <th width="6%"></th>
                                <th width="6%"></th>
                            </tr>



                            <c:forEach var="currentSighting" items="${improvedDatesAndSightings}">

                        <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
                        
                     
                                <tr>

                                    <td>
                                        <a class="tableLinks" href="displaySighting?sightingId=${currentSighting.sightingId}">
                                            <c:out value="${currentSighting.justTheSightingDate}"/>
                                            <%--<c:out value="${currentSighting.justTheSightingDate}"/>--%>

                                        </a>

                                    </td>
                                    
                                   
                                    
                                    
                                    <td class ="tableText">
                                        
                                        <c:set var = "heroOrNot" scope = "session" value = "${currentSighting.isHeroSighting}"/>
      
                                            <c:if test = "${heroOrNot == true}">
         
                                                <p><c:out value = "Superhero"/><p>
      
                                            </c:if>
             
                   
                                            <c:if test = "${heroOrNot == false}">
         
                                                <p><c:out value = "Supervillian"/><p>
      
                                            </c:if> 
                                        

                                    </td>
                                    
                                    
                                    <td>
                                        
                                    <a class="tableLinks" href="displayPersonDetails?thePersonId=${currentSighting.person.personId}"><c:out value="${currentSighting.person.firstName}"/><c:out value="${currentSighting.person.lastName}"/></a>
                                    </td>


                                    <td>
                                        <a class="tableLinks" href="displayLocationDetails?theLocationId=${currentSighting.location.locationId}"><c:out value="${currentSighting.location.locationName}"/></a>
                                    </td>




                                    <td>

                                     <!-- Here we have turned the Edit text into a link, much like with the Delete text 
                                    The Edit link for each entry in the table goes to the displayEditLocationForm controller endpoint and contains the
                                    theIdOfLocation request parameter value associated with the entry. When rendered, the link will look something like
                                    this if the ID is 1: http://localhost:8080/SuperheroSightingsSpringMVC/displayEditLocationForm?theIdOfLocation=1

                                    Notice that the URL pattern is displayEditLocationForm and not editLocation -  we must get the Location from
                                    the DAO and then display it in the Edit Location form. The form will submit to the editLocation endpoint, which we'll create below. 
                                    This is the pattern you should use for edit functionality, because edit functionality always needs two controller endpoints:  
                                    one to display the edit form (that's what we're doing here) and one to process the data submitted in the edit form (editLocation in Location Controller).
                                    -->

        <sec:authorize access="hasRole('ROLE_ADMIN')">

                                        <a class="tableLinks" href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                            Edit
                                        </a>
        </sec:authorize>
                                    </td>
                                    <td>
                                                <sec:authorize access="hasRole('ROLE_ADMIN')">

                                        <a class="tableLinks" href="deleteSighting?sightingIdToDelete=${currentSighting.sightingId}">
                                            Delete
                                        </a>
                                                </sec:authorize>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        
                    </div>
                </div>
            </div>
	</div>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>

