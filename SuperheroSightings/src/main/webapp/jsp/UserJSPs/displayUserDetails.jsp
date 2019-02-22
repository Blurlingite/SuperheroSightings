<%-- 
    Document   : displayUserDetails
    Created on : Jan 18, 2019, 1:03:22 PM
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
        <title>User Details</title>
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
                                            <a href="displayEditUserForm?userNameOfUser=${userToDisplay.userName}" type="button" class="btn btn-success">
                                                Edit This User
                                            </a>
					</div>
                                        
                                    </div>
				</div>
			</div>
                                    
			<div class="row">
                            <div class="col-md-12">
				<h3 class = "DtoDisplayLabel">
                                    User Details for ${userToDisplay.userName}
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
							User
                                                    </h3>
                                                    
                                                    <table id="sightingTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            
                                                            <th width="20%">Username</th>
<!--                                                            <th width="20%">Password</th>-->
                                                            <th width="20%">Name</th>
                                                            <th width="20%">Email</th>
                                                            <th width="20%">Authority</th>
                                                        </tr>
                                                        
                                                            
                                                            <tr>

                                                                
                                                                <td>
                                                                    <c:out value="${userToDisplay.userName}"/> 
                                                                </td>  
                                                                
<!--                                                                <td>
                                                                    < c:out value="$ {userToDisplay.userPassword}"/> 

                                                                </td>   -->
                                                                
                                                                <td>
                                                                    <c:out value="${userToDisplay.firstName} ${userToDisplay.lastName}"/> 
                                                                </td>                                          
                                                                
                                                                <td>
                                                                    <c:out value="${userToDisplay.email}"/> 
                                                                </td> 
                                                                
                                                                
                                                                
                                                                <td>
                                                                   <c:forEach var="currentAuthority" items="${allUserAuthorities}">
                                                                          
                                                                        <c:out value="${currentAuthority}"/> 
                                                                        <br>

                                                                    </c:forEach>
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
                                                                    Organizations
								</h3>
                                                                
                                                                
                                                    <table id="sightingTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="25%">Organization Name</th>
                                                            <th width="25%">Organization Type</th>
                                                            <th width="25%">Organization Address</th>
                                                            <th width="25%">Organization Description</th>


                                                        </tr>
                                                        
                                                            <c:forEach var = "currentOrganization" items = "${allOrganizationsOfUser}">
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
                                                                        <c:out value="${currentOrganization.organizationStreet}"/> <c:out value="${currentOrganization.organizationCity}"/> <c:out value="${currentOrganization.organizationState}"/>, <c:out value="${currentOrganization.organizationZipcode}"/> <c:out value=" ${currentOrganization.organizationCountry}"/>
                                                                        <br>
                                                                    </td>  
                                                                
                                                                
                                                                
                                                                    <td>
                                                                        <c:out value="${currentOrganization.organizationDescription}"/> 
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
