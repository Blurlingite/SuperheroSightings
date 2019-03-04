<%-- 
    Document   : editLocation
    Created on : Dec 31, 2018, 2:37:04 PM
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
        <title>Edit Location</title>
            
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
                                        
                                <div class="col-md-4">  
                                </div>
                                
                            </div>
                        </div>
                    </div> 
                                
                    
                    <div class="row">
                        
                        <div class="col-md-12">
                        </div>
                        
                    </div>
                                
                                
                    <div class="row">
                            
                            
			<div class="col-md-2">
			</div>
                            
                            
			<div class="col-md-8">  
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                                
                                                
                                        <div class="col-md-2">
					</div>


                                        <div class="col-md-8 theEditForms">
                                            
                                            <div class="col-md-12">
                                                <p id="editLocationLabel">Edit Location</p>
                                            </div>


        
                                            <sf:form class="form-horizontal" role="form" modelAttribute="getLocationToEdit"
                                                    action="editLocation" method="POST">
                       
                       
                                                <div class="form-group">
                            
                            
                                                    <label for="add-locationName" class="col-md-4 control-label">Location Name:</label>
                            
                                                    <div class="col-md-8">
                                                        <sf:input type="text" class="form-control" id="add-locationDescription"
                                                              path="locationName" placeholder="Location Name"/>
                                                        <sf:errors path="locationName" cssclass="error"></sf:errors>
                                                    </div>
                            
                            
                                                </div>
                            
                            
                                                <div class="form-group">
                            
                            
                                                    <label for="add-locationDescription" class="col-md-4 control-label">Location Description</label>
                            
                                                        <div class="col-md-8">
                                                            <sf:input type="text" class="form-control" id="add-locationDescription"
                                                              path="locationDescription" placeholder="Location Description"/>
                                                            <sf:errors path="locationDescription" cssclass="error"></sf:errors>
                                                        </div>
                            
                            
                                                </div>
                       
                       
                       
                       
                                                 <div class="form-group">
                            
                            
                                                    <label for="add-locationStreet" class="col-md-4 control-label">Location Street</label>
                            
                                                        <div class="col-md-8">
                                                            <sf:input type="text" class="form-control" id="add-locationStreet"
                                                                path="locationStreet" placeholder="Location Street"/>
                                                            <sf:errors path="locationStreet" cssclass="error"></sf:errors>
                                                        </div>
                            
                            
                                                </div>      
                       
                       
                 
                       
                       
                       
                                                <div class="form-group">
                    
                            
                                                    <label for="add-locationCity" class="col-md-4 control-label">Location City</label>
                    
                                                    <div class="col-md-8">
                                                        <sf:input type="text" class="form-control" id="add-locationCity"
                                                                 path="locationCity" placeholder="Location City"/>
                                                        <sf:errors path="locationCity" cssclass="error"></sf:errors>
                                                    </div>
                            
                            
                                                </div>   
                       
                       
                       
                       
                       
                                                <div class="form-group">
                   
                          
                                                    <label for="add-locationState" class="col-md-4 control-label">Location State</label>
                        
                                                        <div class="col-md-8">
                                                            <sf:input type="text" class="form-control" id="add-locationState"
                                                                path="locationState" placeholder="Location State"/>
                                                            <sf:errors path="locationState" cssclass="error"></sf:errors>
                                                        </div>
                            
                            
                                                </div>   
                       
                       
                       
                       
                       
                                                <div class="form-group">
                    
                            
                                                    <label for="add-locationCountry" class="col-md-4 control-label">Location Country</label>
                    
                                                        <div class="col-md-8">
                                                            <sf:input type="text" class="form-control" id="add-locationCountry"
                                                                path="locationCountry" placeholder="Location Country"/>
                                                            <sf:errors path="locationCountry" cssclass="error"></sf:errors>
                                                        </div>
                            
                            
                                                </div>   
                       
                       
                       
                                                <div class="form-group">
                    
                            
                                                    <label for="add-locationZipcode" class="col-md-4 control-label">Location Zipcode</label>
                    
                                                    <div class="col-md-8">
                                                        <sf:input type="text" class="form-control" id="add-locationZipcode"
                                                           path="locationZipcode" placeholder="Location Zipcode"/>
                                                        <sf:errors path="locationZipcode" cssclass="error"></sf:errors>
                                                    </div>
                            
                            
                                                </div>   
                       
                       
                       
                       
                                                <div class="form-group">
                    
                            
                                                    <label for="add-latitude" class="col-md-4 control-label">Location Latitude</label>
                    
                                                        <div class="col-md-8">
                                                            <sf:input type="text" class="form-control" id="add-latitude"
                                                               path="latitude" placeholder="Location Latitude"/>
                                                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                                                        </div>
                            
                            
                                                </div>   
                            
                       
                                                <div class="form-group">
                    
                            
                                                    <label for="add-longitude" class="col-md-4 control-label">Location Longitude</label>
                    
                                                    <div class="col-md-8">
                                                        <sf:input type="text" class="form-control" id="add-longitude"
                                                             path="longitude" placeholder="Location Longitude"/>
                                                        <sf:errors path="longitude" cssclass="error"></sf:errors>
                        
                        
                                                        <sf:hidden path="locationId"/>

                                                    </div>
                                
                                
                                                </div>   
                       
                       
                       

                                                <div class="form-group">
                                                    <div class="col-md-offset-4 col-md-8">
                                                        <input type="submit" class="btn btn-default" value="Update Location"/>
                                                    </div>
                                                </div>
                                
                                
                                            </sf:form> 
                                            
                                        </div>
                                        
                                        
                                        <div class="col-md-2">
                                            
					</div>
                                        
                                        
                                    </div>
				</div>
                            </div>
                            
                        </div>                            
                    </div>
                </div>
            </div>
        </div>




                
                
    </body>
</html>
