<%-- 
    Document   : location
    Created on : Dec 31, 2018, 12:08:13 PM
    Author     : vishnukdawah

We include this "sec" annotation in this location jsp and not the others
It will be used to restrict access to adding, deleting and editing locations, and since the other location JSPs do those, if
we restrict access here, we can automatically restrict access to those features as well b/c no one can get to those JSPs without
coming to this one first
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

    <title>Locations</title>

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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locationHome">Locations</a></li>
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
                                <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                            </p>
                        </c:if>
                    </div>
                                
                                
		</div>


                <div class = col-md-12>
                        <!--The ROLE_ADMIN is in the database's Authorities table, and gets here in Java through User DTO, DAO and service layer-->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-md-4">
                        

                            <h2>
                                Add A Location
                            </h2>

                        
                        
                        
                            <!--Note that the HTTP method for the form is POST and that the action is addLocation.  We will map a new controller method to this endpoint-->
       
                            <!--The action tells us which controller endpoint to go to-->

                            <form class="form-horizontal" 
                                  role="form" method="POST" 
                                  action="addLocation">

               
                                <div class="form-group">
                    
                    
                                    <label for="add-locationName" class="col-md-4 control-label">Location Name:</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationName" placeholder="Location Name"/>
                                    </div>
                    
                    
                                </div>
               
               
               
                                <div class="form-group">
                    
                    
                                    <label for="add-locationDescription" class="col-md-4 control-label">Location Description:</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationDescription" placeholder="Location Description:"/>
                                    </div>
                
                
                                </div>
               
               
            
                                <div class="form-group">
                    
                    
                                    <label for="add-locationStreet" class="col-md-4 control-label">Location Street</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationStreet" placeholder="Location Street"/>
                                    </div>
                
                
                                </div>
               
               
                
                                <div class="form-group">
                    
                    
                                    <label for="add-locationCity" class="col-md-4 control-label">Location City</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationCity" placeholder="Location City"/>
                                    </div>
                    
                    
                                </div>
               
               
               
                                <div class="form-group">
                    
                    
                                    <label for="add-locationState" class="col-md-4 control-label">Location State</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationState" placeholder="Location State"/>
                                    </div>
                
                
                                </div>
               
               
               
                                <div class="form-group">
                    
                    
                                    <label for="add-locationZipcode" class="col-md-4 control-label">Location Zipcode</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationZipcode" placeholder="Location Zipcode"/>
                                    </div>
                
                
                                </div>
               
               
               
                                <div class="form-group">
                    
                    
                                    <label for="add-locationCountry" class="col-md-4 control-label">Location Country</label>
                    
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="locationCountry" placeholder="Location Country"/>
                                    </div>
                
                
                                </div>
               
               
            
                                <div class="form-group">
                
                        
                                    <label for="add-latitude" class="col-md-4 control-label">Location Latitude</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="latitude" placeholder="Location Latitude"/>
                
                                        </div>
            
                        
                                </div>
            
            
            
                                <div class="form-group">
                
                                    <label for="add-longitude" class="col-md-4 control-label">Location Longitude</label>
                
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="longitude" placeholder="Location Longitude"/>
                                    </div>
           
                
                                </div>              
               
               
               
                                <div class="form-group">
                
                    
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" class="btn btn-default" value="Add Location"/>
                                    </div>
            
                    
                                </div>
          
                            </form>

        </sec:authorize>



                    </div>








                    <div class="col-md-1">

                    </div>


                    <div class="col-md-7">


			<h2>
                            Locations
			</h2>


                    <!-- Finally, we need to add code to location.jsp to process the List of Locations that is being made available 
                        to the page by the controller.  We'll use the JSTL forEach tag to add a row to the locationTable for each Location in the List.

                            We are using a JSTL forEach tag to process the Locations in the List provided by the Controller.
                            Each Location in the list gets a new table row.
                            Each row displays the name, address, and description of the Location.
                    -->

                        <table id="locationTable" class="table table-hover">
                            
                            
                            <tr>
                                <th width="26%">Location Name</th>
                                <th width="26%">Location Address</th>
                                <th width="26%">Location Description</th>
                                <th width="11%"></th>
                                <th width="11%"></th>
                            </tr>
    
    
    
                            <c:forEach var="currentLocation" items="${locationList}">
                            <!--the forEach adds an extra row for each location in the locationList with these td tags-->
                            
                                <tr>
                                    
                                    
                                    <td>
                                        
                                        
                                    <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
                                    <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayLocationDetails) and the 
                                    ID of the location (locationId=) dynamically
                                    The locationId is passed into the request here so that in the Location Controller, the request you pass in has the lopcationId
                                    -->
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

                                        <a href="displayEditLocationForm?theIdOfLocation=${currentLocation.locationId}">
                                            Edit
                                        </a>
                                            
                                    </sec:authorize>

                                
                                    </td>
            
            
                                    <td>
                                        
                                        
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
  
                                            <a href="deleteLocation?theLocationId=${currentLocation.locationId}">
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
<!--</div>-->

    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>

