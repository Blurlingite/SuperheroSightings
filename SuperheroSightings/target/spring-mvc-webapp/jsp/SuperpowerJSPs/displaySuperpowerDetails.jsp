<%-- 
    Document   : displaySuperpowerDetails
    Created on : Dec 28, 2018, 9:44:36 PM
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
        <title>JSP Page</title>
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
                                        
<!--					<div class="col-md-4">
                                            <a href ="" type="button" class="btn btn-success">
                                                LInkToEditJSP
                                            </a>
					</div>-->
                                        
                                    </div>
				</div>
			</div>
                                    
			<div class="row">
                            <div class="col-md-12">
				<h3 class = "DtoDisplayLabel">
                                    Superpower Details for ${powerToDisplay.superpowerName}
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
							Superpower Info
                                                    </h3>
                                                    
                                                    <table id="sightingTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="50%">Superpower Name</th>
                                                            <th width="50%">Superpower Description</th>
                         
                                                        </tr>
                                                        
                                                            
                                                            <tr>
                                                                
                                                                <td>
                                                                    ${powerToDisplay.superpowerName}
                                                                </td>
                                                                
                                                                <td>
                                                                    ${powerToDisplay.superpowerDescription}
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
                                                                    Persons That Have This Superpower
								</h3>
                                                                
                                                                
                                                    <table id="personsBySuperpowerTable" class="table table-hover">
                                                        
                                                        <tr>
                                                            <th width="25%">Person Name</th>
                                                            <th width="25%">Person Type</th>
                                                            <th width="50%">Person Description</th>


                                                        </tr>
                                                        
                                                        
                                                        
                                                       
                                                            <c:forEach var = "currentPerson" items = "${personsThatHaveThisSuperpower}">
                                                                <tr>
                                                                
                                                                    <td>
                                                                        
                                                                    <!--This url has ../ before the controller endpoint because we are in the SuperpowerJSPs folder due to the 
                                                                        global Request Mapping value  of the SuperpowerController (@RequestMapping(value="/SuperpowerJSPs", method=RequestMethod.GET)

                                                                        The SuperpowerController is the only controller that has a global Request Mapping value, just because I wanted to try it
                                                                         But we are trying to get to the displayPersonDetails JSP in the PersonJSPs folder.
                                                                        
                                                                        The ../ makes you exit the SuperpowerJSPs folder and back to the regular jsp folder
                                                                        where you can then navigate to the displayPersonDetails endpoint in the Person controller
                                                                        --> 
                                                                        <a href="../displayPersonDetails?thePersonId=${currentPerson.personId}">
                                                                            <c:out value="${currentPerson.firstName} ${currentPerson.lastName}"/> 
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
				</div>
				
                                <div class="col-md-2">
				</div>
			</div>
		</div>
            </div>
        </div>
    </body>
</html>

