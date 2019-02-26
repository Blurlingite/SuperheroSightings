<%-- 
    Document   : displayOrganizationDetails
    Created on : Dec 30, 2018, 6:14:33 PM
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
        <title>Organization Details</title>
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
                                    
                                    <p>Hello : ${pageContext.request.userPrincipal.name}|                   
                                        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
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
                                            <a href ="displayEditOrganizationForm?theIdOfOrganization=${organizationToDisplay.organizationId}" type="button" class="btn btn-success">
                                                Edit This Organization
                                            </a>
					</div>
                                        
                                    </div>
				</div>
			</div>
                              
                                        
			<div class="row">
                            <div class="col-md-12">
				<h3 class = "DtoDisplayLabel">
                                    Organization Details for ${organizationToDisplay.organizationName}
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
							All Persons
                                                    </h3>
                                                    
                                                    
                                                    <table id="organizationsForPersonTable" class="table table-hover">
                                                        
                                                        
                                                        <br>
                                                        
                                                        <tr>
                                                            
                                                            <th width="25%">Person Name</th>
                                                            <th width="25%">Person Type</th>
                                                            <th width="50%">Person Description</th>

                                                        </tr>
                                                        
                                                        
                                                        
                                             
                                                        <c:forEach var = "currentPerson" items = "${personsFromOrganization}">
                                                            
                                                            <tr>
                                                                
                                                                
                                                                <td>
                                                                    
                                                                    <a href="displayPersonDetails?thePersonId=${currentPerson.personId}">
                                                                        <c:out value ="${currentPerson.firstName}  ${currentPerson.lastName}"/>
                                                                    </a>
                                                               
                                                                </td>
                                                                
                                                                
                                                                <td>
                                                                    
                                                                    
                                                                        <c:set var = "heroOrNot" scope = "session" value = "${currentPerson.isHero}"/>
      
                                                                            <c:if test = "${heroOrNot == true}">
         
                                                                            <p><c:out value = "Superhero"/><p>
      
                                                                            </c:if>
             
                   
                                                                            <c:if test = "${heroOrNot == false}">
         
                                                                                <p><c:out value = "Supervillian"/><p>
      
                                                                            </c:if>
                                                                    
                                                                     
                                                                </td>
                                                                
                                                                
                                                                <td>
                                                                   
                                                                    <c:out value="${currentPerson.descriptionOfPerson}"/>

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
                                                                   All Users
								</h3>
                                                                
                                                                
                                                                <table id="superpowersForPersonTable" class="table table-hover">
                                                        
                                                        
                                                                    <tr>
                                                                        
                                                                        <th width="35%">Username</th>
                                                                        <th width="35%">Name</th>
                                                                        <th width="30%">Email</th>

                                                                    </tr>        
                                                        
                                                        
                                                                    <br>
                                                        
                                                                    <c:forEach var="currentUser" items="${usersFromOrganization}">


                                                                        <tr>
                                                                            
                                                                
                                                                            <td>
                                                                                
                                                                                <a href="displayUserDetails?theUsername=${currentUser.userName}">
                                                                                    <c:out value ="${currentUser.userName}"/>
                                                                                </a>
                                                                                
                                                                            </td>
                                                                
                                                                            
                                                                            <td>
                                                                                
                                                                                <c:out value ="${currentUser.firstName} ${currentUser.lastName}"/>
                                                                            </td>
                                                                
                                                                
                                                                            <td>
                                                                                <c:out value ="${currentUser.email}"/>
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

