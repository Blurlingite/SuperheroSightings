<%-- 
    Document   : organization
    Created on : Dec 29, 2018, 9:09:45 PM
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

    <title>Organizations</title>

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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizationHome">Organizations</a></li>
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
                            
                        <p>Hello : ${pageContext.request.userPrincipal.name} |                  
                            <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
        
                        </p>
                        
                        </c:if>
                        
                    </div>
                                
                                
		</div>


                    <div class = col-md-12>
                            
                        <sec:authorize access="hasRole('ROLE_ADMIN')">

                            <div class="col-md-4">
                                
                                <h2 class = "addEntityForm">
                                    Add An Organization
                                </h2>

                        
                        
                        
                                <!--Note that the HTTP method for the form is POST and that the action is addLocation.  We will map a new controller method to this endpoint-->
       
                                <!--The action tells us which controller endpoint to go to-->
                                <form class="form-horizontal" 
                                      role="form" method="POST" 
                                      action="addOrganization">

               
                                    
                                    <div class="form-group">
                
                                        
                                        <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                
                                        <div class="col-md-8">
                  
                                            <input type="text" class="form-control" name="organizationName" placeholder="Organization Name"/>
                
                                        </div>
            
                                        
                                    </div>
               
               
                                    
                                    <div class="form-group">
                
                                        <label for="add-person-isHero" class="col-md-4 control-label">Organization Type</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="radio" name="isItAHeroOrganization" value="true"> Superhero<br>  <input type="radio" name="isItAHeroOrganization" value="false"> Supervillain<br>
                       
                
                                        </div>
            
                                    </div>
               
               
               
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationDescription" class="col-md-4 control-label">Organization Description:</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationDescription" placeholder="Organization Description:"/>
                
                                        </div>
            
                                        
                                    </div>
               
               
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationStreet" class="col-md-4 control-label">Organization Street</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationStreet" placeholder="Organization Street"/>
                
                                        </div>
           
                                        
                                    </div>
               
               
                
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationCity" class="col-md-4 control-label">Organization City</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationCity" placeholder="Organization City"/>
                
                                        </div>
            
                                        
                                    </div>
               
               
               
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationState" class="col-md-4 control-label">Organization State</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationState" placeholder="Organization State"/>
                
                                        </div>
            
                                        
                                    </div>
            
            
            
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationZipcode" class="col-md-4 control-label">Organization Zipcode</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationZipcode" placeholder="Organization Zipcode"/>
                
                                        </div>
            
                                        
                                    </div>
            
            
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-organizationCountry" class="col-md-4 control-label">Organization Country</label>
                
                                        <div class="col-md-8">
                    
                                            <input type="text" class="form-control" name="organizationCountry" placeholder="Organization Country"/>
                
                                        </div>
            
                                        
                                    </div>
               
                         
               
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-persons" class="col-md-4 control-label">Persons <br>
                    
                                            (hold ctrl or cmd to select multiple):</label>
                
                                        <select name = "personsSelectedByUser" multiple ="true">
                      
                    
                                            <c:forEach var="currentPerson" items="${personList}">
                        
                                                <option value="${currentPerson.personId}">${currentPerson.firstName} ${currentPerson.lastName}</option>
                    
                                            </c:forEach>
                      
                
                                        </select>

            
                                    </div>
               
              
            
                                    <div class="form-group">
                
                                        
                                        <label for="add-users" class="col-md-4 control-label">Admins <br>
                    
                                            (hold ctrl or cmd to select multiple):</label>
                
                                        <select name = "usersSelectedByUser" multiple ="true">
                      
                    
                                            <c:forEach var="currentUser" items="${userList}">
                        
                                                <option value="${currentUser.userName}">${currentUser.userName} (${currentUser.firstName} ${currentUser.lastName})</option>
                    
                                            </c:forEach>
                      
                
                                        </select>

            
                                    </div>
               
               
               
            
                                    <div class="form-group">
                
                                        
                                        <div class="col-md-offset-4 col-md-8">
                    
                                            <input type="submit" class="btn btn-default" value="Add Organization"/>
                
                                        </div>
            
                                        
                                    </div>
          
        
                                </form>


                        </sec:authorize>
                                

                        </div>








                    <div class="col-md-1">
                    </div>


                    <div class="col-md-7">


			<h2>
                            Organizations
			</h2>


 
                        <table id="locationTable" class="table table-hover entityTable">
                            
                            <tr>
                                <th width="15%">Organization Name</th>
                                <th width="5%">Organization Type</th>
                                <th width="30%">Organization Address</th>
                                <th width="20%">Organization Description</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            
    
    
                            <c:forEach var="currentOrganization" items="${organizationList}">
                            
                                <tr>
        
                                    <td>
            
                                        <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
            
                                        <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayContactDetails) and the 
            
                                        ID of the contact (contactId=) dynamically
            
                                        The contactId is passed into the request here so that in the Contact Controller, the request you pass in has the contactId
            
                                        -->
            
                                        <a class="tableLinks" href="displayOrganizationDetails?theOrganizantionId=${currentOrganization.organizationId}">
            
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
        
                                    </td>
        
        
        
                                    <td>
            
                                        <c:out value="${currentOrganization.organizationDescription}"/>
        
                                    </td>

        
                                    <td>

                                        <sec:authorize access="hasRole('ROLE_ADMIN')">

                                            <a class="tableLinks" href="displayEditOrganizationForm?theIdOfOrganization=${currentOrganization.organizationId}">
                                                 Edit
                                            </a>
                                        </sec:authorize>
                                        
                                    </td>
        
                                    <td>
                    
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">

            
                                            <a class="tableLinks" href="deleteOrganization?theOrganizantionId=${currentOrganization.organizationId}">
            
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

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>


