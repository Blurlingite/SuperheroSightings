<%-- 
    Document   : editOrganization
    Created on : Dec 30, 2018, 6:37:51 PM
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
        <title>Edit Organization</title>
        
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

        
        
                            <sf:form class="form-horizontal" role="form" modelAttribute="getOrganizationToEdit"
                             action="editOrganization" method="POST">
                
                        
                        
                                <div class="form-group">
                            
                    
                                    <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                    
                                    <div class="col-md-8">
                                
                                         <sf:input type="text" class="form-control" id="add-organization-name"
                                          path="organizationName" placeholder="Organization Name"/>
                                         <sf:errors path="organizationName" cssclass="error"></sf:errors>
                                
                                    </div>
                                
                                         
                                </div>
                                
                                
                                
                                <div class="form-group">
                            
                    
                                    <label for="add-organization-Description" class="col-md-4 control-label">Organization Description:</label>
                    
                                    <div class="col-md-8">
                                
                                        <sf:input type="text" class="form-control" id="add-organization-Description"
                                          path="organizationDescription" placeholder="Organization Description"/>
                                        <sf:errors path="organizationDescription" cssclass="error"></sf:errors>
                    
                                        <sf:hidden path="organizationId"/>

                                    </div>
                                
                                
                                </div>
                                                
                                                
                                                
                                <div class="form-group">
                    
                    
                                    <label for="add-organizationStreet" class="col-md-4 control-label">Organization Street:</label>
                    
                                    <div class="col-md-8">
                        
                                            <sf:input type="text" class="form-control" id="add-organizationStreet"
                                              path="organizationStreet" placeholder="Organization Name"/>
                                            <sf:errors path="organizationStreet" cssclass="error"></sf:errors>
                                    </div>
                
                    
                                </div> 
                    
                    
                                <div class="form-group">
                            
                    
                                    <label for="add-organizationCity" class="col-md-4 control-label">Organization City:</label>
                    
                                    <div class="col-md-8">
                                
                                        <sf:input type="text" class="form-control" id="add-organizationCity"
                                          path="organizationCity" placeholder="Organization City"/>
                                        <sf:errors path="organizationCity" cssclass="error"></sf:errors>
                                
                                    </div>
                                
                                
                                </div> 
                    
                    
                    
                                <div class="form-group">
                            
                        
                                    <label for="add-organizationState" class="col-md-4 control-label">Organization State:</label>
                        
                                    <div class="col-md-8">
                                
                                        <sf:input type="text" class="form-control" id="add-organizationState"
                                          path="organizationState" placeholder="Organization State"/>
                                        <sf:errors path="organizationState" cssclass="error"></sf:errors>
                            
                                    </div>
                                
                    
                                </div> 
                    
                    
                                                            
                                <div class="form-group">
                    
                            
                                    <label for="add-organizationZipcode" class="col-md-4 control-label">Organization Zipcode:</label>
                   
                                    <div class="col-md-8">
                                
                                        <sf:input type="text" class="form-control" id="add-organizationZipcode"
                                          path="organizationZipcode" placeholder="Organization Zipcode"/>
                                        <sf:errors path="organizationZipcode" cssclass="error"></sf:errors>
                           
                                    </div>
                            
                                
                                </div> 
                    
                    
                 
                                <div class="form-group">
                    
                            
                                    <label for="add-organizationCountry" class="col-md-4 control-label">Organization Country:</label>
                    
                                     <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-organizationCountry"
                                          path="organizationCountry" placeholder="Organization Country:"/>
                                        <sf:errors path="organizationCountry" cssclass="error"></sf:errors>
                                    </div>
                                
                                
                                </div> 
                    
                    
                    
                                <div class="form-group">
                        
                            
                                    <label for="add-isItAHeroOrganization" class="col-md-4 control-label">Is It A Hero Organization?:</label>
                        
                                    <div class="col-md-8">
                                        <sf:radiobutton path="isItAHeroOrganization" value="true"/>Yes 
                                        <sf:radiobutton path="isItAHeroOrganization" value="false"/>No 
                    
                                        <sf:errors path="isItAHeroOrganization" cssclass="error"></sf:errors>
                                    </div>
                                
                                
                                </div> 
                                                
                                                
                                                
                                <div class="form-group">
                            
                    
                                    <label for="add-personsForOrganization" class="col-md-4 control-label">Persons:</label>
                    
                                    <div class="col-md-8">
                                        <select name = "personsSelectedByUserOnEditPage" multiple ="true">
                                            <c:forEach var="currentPerson" items="${allPersons}">
                                                <option value="${currentPerson.personId}">${currentPerson.firstName} ${currentPerson.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                            
                            
                                </div>                             
                                                
                                                
                                <div class="form-group">
                    
                                        
                                    <label for="add-adminsForOrganization" class="col-md-4 control-label">Users:</label>
                                        
                                    <div class="col-md-8">
                                        <select name = "adminsSelectedByUserOnEditPage" multiple ="true">
                                            <c:forEach var="currentUser" items="${allUsers}">
                                                <option value="${currentUser.userName}">${currentUser.userName}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                           
                            
                                </div>   
                    
                    

                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" class="btn btn-default" value="Update Organization"/>
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
