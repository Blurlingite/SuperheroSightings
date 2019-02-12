<%-- 
    Document   : login
    Created on : Jan 22, 2019, 11:55:04 AM
    Author     : vishnukdawah

This JSP will be displayed first so the user must login to use the website
This JSP must exist outside the jsp folder because it serves as a landing page (A page anyone can get to)
Everyone needs to get to this page so they can login
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings: Login</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <h2>Login Page</h2>

            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
                
                
                <!--The action for this form must match the login-processing-url value in the spring-security.xml file.-->
            <form class="form-horizontal" 
                  role="form" 
                  method="post" 
                   action="j_spring_security_check">
                
                <!--The names of the username and password inputs must be j_username and j_password, respectively.-->
                <div class="form-group">
                    <label for="j_username" 
                           class="col-md-4 control-label">Username:</label>
                    <div class="col-md-8">
                        <input type="text" 
                               class="form-control" 
                               name="j_username" 
                               placeholder="Username"/>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label for="j_password" 
                           class="col-md-4 control-label">Password:</label>
                    <div class="col-md-8">
                        <input type="password" 
                               class="form-control" 
                               name="j_password" 
                               placeholder="Password"/>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" 
                               class="btn btn-default" 
                               id="search-button" 
                               value="Sign In"/>
                    </div>
                </div>
                
                
            </form> 
                
                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
