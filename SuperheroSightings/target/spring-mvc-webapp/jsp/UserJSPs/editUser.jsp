<%-- 
    Document   : editUser
    Created on : Jan 22, 2019, 5:48:22 PM
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
        <title>Edit User</title>
            
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
 
    </head>
    <body>
        <div class="container-fluid relativeContainer">
            
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
                </div>
                        
                        
                <div class ="col-md-12">        
                    <div class="row">
			<div class="col-md-2">
                             
			</div>
				
                 
                        
                        <div class="col-md-8 theEditForms"> 
                    
                    
                            <sf:form class="form-horizontal" role="form" modelAttribute="userToDisplay"
                                   action="editUser" method="POST">
         
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-userName" class="col-md-4 control-label">Username:</label>
                                    
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-userName"
                                              path="userName" placeholder="Username"/>
                                        <sf:errors path="userName" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>      
                       
                       
                   
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-userPassword" class="col-md-4 control-label">Password:</label>
                                    
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-userPassword"
                                                path="userPassword" placeholder="Password"/>
                                        <sf:errors path="userPassword" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>   
                       
                       
                       
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-firstName" class="col-md-4 control-label">First Name:</label>
                                    
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-firstName"
                                                 path="firstName" placeholder="First Name"/>
                                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                        
                                        <sf:hidden path="userId"/>

                                    </div>
                                        
                                        
                                </div>   
                       
                       
                       
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-lastName" class="col-md-4 control-label">Last Name:</label>
                                    
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-lastName"
                                                 path="lastName" placeholder="Last Name"/>
                                        <sf:errors path="lastName" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>   
                       
                       
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-email" class="col-md-4 control-label">Email:</label>
                                    
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-email"
                                                  path="email" placeholder="Email"/>
                                        <sf:errors path="email" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>   
                       
                       
                       
                       
                                <div class="form-group">
                                    
                                    
                                    <label for="add-isEnabled" class="col-md-4 control-label">Are they an Admin?</label>
                                    
                                    <div class="col-md-8">
                                        <sf:radiobutton path="isAdmin" value="true"/>Yes 
                                        <sf:radiobutton path="isAdmin" value="false"/>No 

                                        <sf:errors path="isAdmin" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>   
                       
      
                       
                                <div class="form-group">
                                    
                    
                                    <label for="add-organizationsForPerson" class="col-md-4 control-label">Organizations</label>
                    
                                    <div class="col-md-8">
                                        <sf:select path="allOrganizationIdsToPopulateOrganizationListInUserDTO">
                                            <sf:options items="${allOrganizations}"  itemLabel="organizationName" itemValue="organizationId"/>
                                        </sf:select>
                                    </div> 
                                    
                                    
                                </div>  
                       
                       
                       

                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" class="btn btn-default" value="Update User"/>
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
                
                
    </body>
</html>
