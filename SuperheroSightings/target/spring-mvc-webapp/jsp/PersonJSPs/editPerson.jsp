<%-- 
    Document   : displayEditPersonForm
    Created on : Jan 3, 2019, 9:42:45 AM
    Author     : vishnukdawah
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Person</title>
        
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

                            <sf:form class="form-horizontal" role="form" modelAttribute="personToDisplay"
                                     action="editPerson" method="POST">
                  
                  
                  
                  
                                <div class="form-group">
                            
                                    
                                    <label for="add-personFirstName" class="col-md-4 control-label">First Name:</label>
                            
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-personFirstName"
                                        path="firstName" placeholder="First Name"/>
                                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                                    </div>
                                    
                                    
                                </div>
                  
                  
                  
                                <div class="form-group">
                            
                                    
                                    <label for="add-personDescription" class="col-md-4 control-label">Last Name:</label>
                            
                                    <div class="col-md-8">
                                        <sf:input type="text" class="form-control" id="add-personLastName"
                                        path="lastName" placeholder="Last Name"/>
                                        <sf:errors path="lastName" cssclass="error"></sf:errors>
                    
                                        <sf:hidden path="personId"/>
                                    </div>
                                    
                                    
                                </div>
                  
                  
                  
                                    <div class="form-group">
                            
                                        
                                        <label for="add-isHero" class="col-md-4 control-label">Person Type</label>
                            
                                        <div class="col-md-8">
 
                                            <sf:radiobutton path="isHero" value="true"/>Superhero 
                                            <sf:radiobutton path="isHero" value="false"/>Supervillain 
                    
                                            <sf:errors path="isHero" cssclass="error"></sf:errors>

                                        </div>
                                            
                                            
                                    </div> 
                  

                                    <div class="form-group">
                             
                                        
                                        <label for="add-descriptionOfPerson" class="col-md-4 control-label">Person Description:</label>
                            
                                        <div class="col-md-8">
                                            <sf:input type="text" class="form-control" id="add-descriptionOfPerson"
                                            path="descriptionOfPerson" placeholder="isHero"/>
                                            <sf:errors path="descriptionOfPerson" cssclass="error"></sf:errors>
                                        </div>
                                        
                                        
                                    </div>           
                  
                  
                  
                                    <!--The path is a variable (a list of ints called listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO in the PersonDTO) in the Person DTO so that when mu;tiple choices are chosen,
                                        you will get a list of superpowerIds (as shown by the itemValue being the superpowerId field of the Superpower DTO
                  
                                        The items attribute is the list you will iterate over (in this case, it is allPowers, a variable in the Controller endpoint displayPersonEditForm
                  
                                        itemLabel is the field name of the Superpower DTO (superpowerName) that will be displayed on the screen
                                        It won't display "superpowerName" but rather, the value currently their for example "Power Of Flight"
                  
                                        itemValue is the field name of the Superpower DTO (superpowerId) that will be passed into the Controller endpoint editPerson, as shown by this form's action attribute
                  
                                        The idea here is to have the user select multiple choices, each choice is attached with a superpowerId.
                                        So we set the path to something in the Person DTO that can take many ints, the List<Integer> variable listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO
                                        Once we have that, in the controller we can get this list variable with the Person DTO getter method
                                        Then we can go through each ID in the list and get each superpower
                                        Then we can add those superpowers to a list and set that on the Person DTO
                                    -->
                    
                                    <div class="form-group">
                    
                                        
                                        <label for="add-superpowersForPerson" class="col-md-4 control-label">Superpowers:</label>
                    
                                        <div class="col-md-8">
                                            <sf:select path="listOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO">
                                                <sf:options items="${allPowers}"  itemLabel="superpowerName" itemValue="superpowerId"/>
                                            </sf:select>
                                        </div>
                                        
                                        
                                    </div> 
                  
 
                                    <div class="form-group">
                    
                                        
                                        <label for="add-organizationsForPerson" class="col-md-4 control-label">Organizations</label>
                    
                                        <div class="col-md-8">
                                            <select name = "organizationsSelectedByUser" multiple ="true">
                                                <c:forEach var="currentOrganization" items="${allOrganizations}">
                                                    <option value="${currentOrganization.organizationId}">${currentOrganization.organizationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div> 
                                        
                                        
                                    </div> 
                  
                  
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <input type="submit" class="btn btn-default" value="Update Person"/>
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
