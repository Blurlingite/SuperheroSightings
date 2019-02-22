<%-- 
    Document   : editSuperpower
    Created on : Jan 17, 2019, 6:28:22 PM
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
        <title>Edit Superpower</title>
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
                        <p>Hello : ${pageContext.request.userPrincipal.name}
        |                   <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if> 
                        </div>
                                
                    </div>
                                
                                
                </div>
         
                  
                <div class ="col-md-12 centerContentInContainer">        
                    <div class="row">
			<div class="col-md-2">
                             
			</div>
				
                 
                        
                <div class="col-md-8" style = "background-color:yellow">
                   
                    <label for="theEditSightingForm" form="form1">Edit Superpower</label>
         
                                    
                    <sf:form class="form-horizontal" role="form" modelAttribute="getPowerToEdit"
                            action="editSuperpower" method="POST">
                        
                        
                        <div class="form-group">
                            <label for="add-superpowerName" class="col-md-4 control-label">Superpower Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-superpowerName"
                                        path="superpowerName" placeholder="Superpower Name"/>
                                <sf:errors path="superpowerName" cssclass="error"></sf:errors>
                            </div>
                        </div>
                            
                            
                        <div class="form-group">
                            <label for="add-superpower-Description" class="col-md-4 control-label">Superpower Description</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-superpower-Description"
                                          path="superpowerDescription" placeholder="Superpower Description"/>
                                <sf:errors path="superpowerDescription" cssclass="error"></sf:errors>
                    
                                <sf:hidden path="superpowerId"/>
                            </div>
                        </div>
 

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Superpower"/>
                            </div>
                        </div>
                                
                                
                    </sf:form>    
                    
                    
                                    
		</div>
                            
                            
                <div class="col-md-2">
<!--                    hhhh-->
		</div>
                            
                            
                    </div>
                </div>

		<!--</div>-->
            </div>
        </div>
        
        
        
        
        
    </body>
</html>

