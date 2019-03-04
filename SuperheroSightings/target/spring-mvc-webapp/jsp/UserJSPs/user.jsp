<%-- 
    Document   : user
    Created on : Jan 18, 2019, 11:33:11 AM
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
        <title>Users</title>
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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/userHome">Users</a></li>
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


                <div class = col-md-12>
                    
                <sec:authorize access="hasRole('ROLE_ADMIN')">

                    <div class="col-md-4">
                        <h2 class = "addEntityForm">
                            Add A User 
                        </h2>

                        
                        
                        
                        
                        
                 <!--Note that the HTTP method for the form is POST and that the action is addPerson.  We will map a new controller method to this endpoint-->
       
                 <!--The action tells us which controller endpoint to go to-->
           <form class="form-horizontal" 
              role="form" method="POST" 
              action="addUser">
               
               
            <div class="form-group">
                <label for="add-userName" class="col-md-4 control-label">Username:</label>
                <div class="col-md-8">
                    <c:out value = "${theErrorMessage}"/>
                  <input type="text" class="form-control" name="userName" placeholder="Username"/>       
                </div>
            </div>
               
               
            <div class="form-group">
                <label for="add-userPassword" class="col-md-4 control-label">Password:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="userPassword" placeholder="Password"/>
                </div>
            </div>
               
               
               
            <div class="form-group">
                <label for="add-firstName" class="col-md-4 control-label">First Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name"/>
                </div>
            </div>
               
               
               
            <div class="form-group">
                <label for="add-lastName" class="col-md-4 control-label">Last Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"/>
                </div>
            </div>
               
               
            <div class="form-group">
                <label for="add-email" class="col-md-4 control-label">Email:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="email" placeholder="Email"/>
                </div>
            </div>
               
               
            <div class="form-group">
                <label for="add-isAdmin" class="col-md-4 control-label">Admin?</label>
                <div class="col-md-8">
                    <input type="radio" name="isAdmin" value="true"> Yes<br>
                    <input type="radio" name="isAdmin" value="false"> No<br>
                </div>
            </div> 
   
               
            <div class="form-group">
                <label for="add-organizations" class="col-md-4 control-label">Organizations <br>
                    (hold ctrl or cmd to select multiple):</label>
                <select name = "organizationsSelectedByUser" multiple ="true">

                    <c:forEach var="currentOrganization" items="${organizationList}">
                        <option value="${currentOrganization.organizationId}">${currentOrganization.organizationName}</option>
                    </c:forEach>
                      
                </select>

            </div>
               
               
  
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create User"/>
                </div>
            </div> 

        </form>




                    </div>


                    </sec:authorize>





                    <div class="col-md-1">
                    </div>


                    <div class="col-md-7">


			<h2>
                            Users
			</h2>


        
<!--       
Finally, we need to add code to superpower.jsp to process the List of Superpowers that is being made available 
to the page by the controller.  We'll use the JSTL forEach tag to add a row to the Superpower table for each Superpower in the List.

We are using a JSTL forEach tag to process the Superpowers in the List provided by the Controller.
Each Superpower in the list gets a new table row.
Each row displays info of the Superpower.
We include the text 'Edit' and 'Delete' now as placeholders - we will make them functional links in later steps.-->
<table id="userTable" class="table table-hover entityTable">
    <tr>
        <!--<th width="17%">User No.</th>-->
        <th width="17%">Username</th>
        <th width="17%">First Name</th>
        <th width="17%">Last Name</th>
        <th width="16%"></th>
        <th width="16%"></th>
    </tr>
    
    
    
<c:forEach var="currentUser" items="${userList}">
    <tr>

        
        <td>
                        <a class="tableLinks" href="displayUserDetails?theUsername=${currentUser.userName}">

                        <c:out value="${currentUser.userName}"/>
                        </a>
        </td>
        
        
        <td class="tableText">
            
              <c:out value="${currentUser.firstName}"/>



        </td>
        
        
        <td class="tableText">
            
            <c:out value="${currentUser.lastName}"/>

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

            <a class="tableLinks" href="displayEditUserForm?userNameOfUser=${currentUser.userName}">
            Edit
            </a>
            
        </sec:authorize>

        </td>
        
        
        <td>
            
        <sec:authorize access="hasRole('ROLE_ADMIN')">

            <a class="tableLinks" href="deleteUser?theUsername=${currentUser.userName}">
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



