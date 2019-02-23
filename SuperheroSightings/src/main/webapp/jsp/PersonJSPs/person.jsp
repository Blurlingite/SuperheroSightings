<%-- 
    Document   : person
    Created on : Jan 16, 2019, 3:20:00 PM
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

    <title>Persons</title>

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
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/personHome">Persons</a></li>
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
                            Add A Person
                        </h2>

                        <!--gets it from personHome endpoint --> 
                        
                        
                        
                        
                        
                        
                        
                 <!--Note that the HTTP method for the form is POST and that the action is addPerson.  We will map a new controller method to this endpoint-->
       
                 <!--The action tells us which controller endpoint to go to-->
           <form class="form-horizontal" 
              role="form" method="POST" 
              action="addPerson">
            <div class="form-group">
                <label for="add-person-first-name" class="col-md-4 control-label">Person First Name:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="personFirstName" placeholder="Person First Name"/>
                </div>
            </div>
               
            <div class="form-group">
                <label for="add-person-last-name" class="col-md-4 control-label">Person Last Name</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="personLastName" placeholder="Person Last Name"/>
                </div>
            </div>
               
               
                                    <div class="form-group">
                <label for="add-person-isHero" class="col-md-4 control-label">Person Type</label>
                <div class="col-md-8">
                    
                      <input type="radio" name="isHero" value="true"> Superhero<br>  <input type="radio" name="isHero" value="false"> Supervillain<br>
                       
                </div>
            </div>
               
               
                           <div class="form-group">
                <label for="add-person-description" class="col-md-4 control-label">Person Description</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="personDescription" placeholder="Person Description"/>
                </div>
            </div>
   
            <div class="form-group">
                <label for="add-superpowers" class="col-md-4 control-label">Superpowers <br>
                    (hold ctrl or cmd to select multiple):</label>
                <select name = "powersSelectedByUser" multiple ="true">
                      
                    <c:forEach var="currentPower" items="${allSuperpowers}">
                        <option value="${currentPower.superpowerId}">${currentPower.superpowerName}</option>
                    </c:forEach>
                      
                </select>

            </div>
               
               
               
            <div class="form-group">
                <label for="add-organizations" class="col-md-4 control-label">Organizations <br>
                    (hold ctrl or cmd to select multiple):</label>
                <select name = "organizationsSelectedByUser" multiple ="true">
                      
                    <c:forEach var="currentOrganization" items="${allOrganizations}">
                        <option value="${currentOrganization.organizationId}">${currentOrganization.organizationName}</option>
                    </c:forEach>
                      
                </select>

            </div>

               

         
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Person"/>
                </div>
            </div>
        </form>

                    </sec:authorize>


                    </div>








                    <div class="col-md-1">
                    </div>


                    <div class="col-md-7">


			<h2>
                            Persons
			</h2>


<!--       
Finally, we need to add code to contacts.jsp to process the List of Contacts that is being made available 
to the page by the controller.  We'll use the JSTL forEach tag to add a row to the My Contacts table for each Contact in the List.

We are using a JSTL forEach tag to process the Contacts in the List provided by the Controller.
Each Contact in the list gets a new table row.
Each row displays the first and last name and company of the Contact.
We include the text 'Edit' and 'Delete' now as placeholders - we will make them functional links in later steps.-->
<table id="contactTable" class="table table-hover entityTable">
    <tr>
        <th width="40%">Name</th>
        <th width="30%">Superhero/Supervillian/Human</th>
        <th width="30%">Person Description</th>
        <th width="15%"></th>
        <th width="15%"></th>
    </tr>
    
    
    
<c:forEach var="currentPerson" items="${personList}">
    <!--the forEach adds an extra row for each contact in the contactList with these td tags-->
    <tr>
        <td>
            <!--"?” in URL acts as separator, it indicates end of URL resource path and start of query parameters-->
            <!--Enclose the name in an anchor tag (for clickable link) That link needs the right method name where it's coming from (displayContactDetails) and the 
            ID of the contact (contactId=) dynamically
            The contactId is passed into the request here so that in the Contact Controller, the request you pass in has the contactId
            -->
            <a class="tableLinks" href="displayPersonDetails?thePersonId=${currentPerson.personId}">
            <c:out value="${currentPerson.firstName}"/><c:out value="${currentPerson.lastName}"/> 
            </a>
            
        </td>
        
        
        <td class ="tableText">
                <c:set var = "heroOrNot" scope = "session" value = "${currentPerson.isHero}"/>
      <c:if test = "${heroOrNot == true}">
         <p><c:out value = "Superhero"/><p>
      </c:if>
             
                   <c:if test = "${heroOrNot == false}">
         <p><c:out value = "Supervillian"/><p>
      </c:if>
        </td>
        
        
        <td class="tableText size15FontForCellsWithTooMuchText">
                <c:out value="${currentPerson.descriptionOfPerson}"/>
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

            <a class="tableLinks" href="displayEditPersonForm?personId=${currentPerson.personId}">
            Edit
            </a>
        </sec:authorize>
        </td>
        <td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">

            <a class="tableLinks" href="deletePerson?personIdToDelete=${currentPerson.personId}">
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
