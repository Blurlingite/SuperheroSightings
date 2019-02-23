<%-- 
    Document   : editSuperpower
    Created on : Dec 28, 2018, 2:50:39 PM
    Author     : vishnukdawah


Summary - End of Step 18
That does it for this step of our project. We did the following:

Added Spring Security dependencies to the POM
Configured Tomcat to use the Spring Security servlet filter to intercept requests and perform security checks.
Configured Spring Security settings in a separate file and registered that file with the Spring framework.
Created the database tables needed for Spring Security authentication and authorization.
Created a custom controller and view for the login form.
Provided a way for users to log out of the application.
Limited access to selected application endpoints to only users with the required roles.
Customized the rendering of views based on the user that is logged into the application.

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
        <title>Superpowers</title>
                
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" type="text/css">
                

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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/SuperpowerJSPs/superpowerHome">Superpowers</a></li>
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


                <div class = col-md-12>
                        <!--The ROLE_ADMIN is in the database's Authorities table, and gets here in Java through User DTO, DAO and service layer-->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-md-4">
                        <h2 class = "addEntityForm">
                            Add A Superpower
                        </h2>

                        <!--gets it from personHome endpoint --> 
                        
                        
                        
                        
                        
                        
                        
                 <!--Note that the HTTP method for the form is POST and that the action is addPerson.  We will map a new controller method to this endpoint-->
       
                 
    <!--We are using sec:authorize to restrict access to editing and deleting features to only admins, since the adding featire is on this jsp also,
    we will need to use sec:authorize on the add form as well
    sec:authorize will hide things on the page if you are not an admin
    -->


                 <!--The action tells us which controller endpoint to go to-->
           <form class="form-horizontal" 
              role="form" method="POST" 
              action="addSuperpower">
               
               
            <div class="form-group">
                <label for="add-superpower-name" class="col-md-4 control-label">Superpower Name:</label>
                <div class="col-md-8">
                  <input type="text" id="validateSuperpowerName" class="form-control" name="superpowerName" placeholder="Superpower Name"/>
                </div>
            </div>
               
               
            <div class="form-group">
                <label for="add-superpowerDescription" class="col-md-4 control-label">Superpower Description:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="superpowerDescription" placeholder="Last Name"/>
                </div>
                
                <p id="validationError"></p>

            </div>
  
  
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Superpower"/>
                </div>
            </div> 

            </form>

        </sec:authorize>



                    </div>



                    <div class="col-md-1">
                    </div>


                    <div class="col-md-7">


			<h2>
                            Superpowers
			</h2>


        
<!--       
Finally, we need to add code to superpower.jsp to process the List of Superpowers that is being made available 
to the page by the controller.  We'll use the JSTL forEach tag to add a row to the Superpower table for each Superpower in the List.

We are using a JSTL forEach tag to process the Superpowers in the List provided by the Controller.
Each Superpower in the list gets a new table row.
Each row displays info of the Superpower.
We include the text 'Edit' and 'Delete' now as placeholders - we will make them functional links in later steps.-->
<table id="superpowerTable" class="table table-hover entityTable">
    <tr>
        <th width="30%">Superpower Name</th>
        <th width="40%">Superpower Description</th>
        <th width="15%"></th>
        <th width="15%"></th>
    </tr>
    
    
    
<c:forEach var="currentSuperpower" items="${superpowerList}">
    <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
    <tr>
        <td>
            <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
            <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayContactDetails) and the 
            ID of the contact (contactId=) dynamically
            The contactId is passed into the request here so that in the Contact Controller, the request you pass in has the contactId
            -->
            <a class ="tableLinks" href="displaySuperpowerDetails?theSuperpowerId=${currentSuperpower.superpowerId}">
            <c:out value="${currentSuperpower.superpowerName}"/> 
            </a>
            
        </td>
        
        <td class="tableText">
            
            <c:out value="${currentSuperpower.superpowerDescription}"/>

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

    <!--We are using sec:authorize to restrict access to editing and deleting features to only admins, since the adding featire is on this jsp also,
    we will need to use sec:authorize on the add form-->
    
    <!--The ROLE_ADMIN is in the database's Authorities table, and gets here in Java through User DTO, DAO and service layer-->

           <sec:authorize access="hasRole('ROLE_ADMIN')">
               
            <a  class="tableLinks" href="displayEditPowerForm?powerId=${currentSuperpower.superpowerId}">
            Edit
            </a>
            
           </sec:authorize>

        </td>
        <td>
                <!--We are using sec:authorize to restrict access to editing and deleting features to only admins, since the adding featire is on this jsp also,
            we will need to use sec:authorize on the add form-->
                
    <!--The ROLE_ADMIN is in the database's Authorities table, and gets here in Java through User DTO, DAO and service layer--> 
           <sec:authorize access="hasRole('ROLE_ADMIN')">

            <a  class="tableLinks" href="deleteSuperpower?superpowerId=${currentSuperpower.superpowerId}">
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

    <script src="${pageContext.request.userPrincipal.name}/js/jquery.min.js"></script>
    <script src="${pageContext.request.userPrincipal.name}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.userPrincipal.name}/js/scripts.js"></script>
  </body>
</html>


