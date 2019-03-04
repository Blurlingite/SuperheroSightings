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
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" type="text/css">
        
    </head>
    <body>
        
<!--Code Analysis
The action for this form must match the login-processing-url value in the spring-security.xml file.
The names of the username and password inputs must be j_username and j_password, respectively.-->
        
        
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
                        </div>
             
                   </div>  
     

                   <div id="signInPanelBackground" class="col-md-12">   

                        <div class="row">     
                            <!--beginning of container of the login in text and form-->
                            <div class="col-md-12">   
                    
                                <div class="row">
                                    <div id="loginTitle" class ="col-md-12">
                                        <h2>Sign In</h2>
                                    </div>
                                </div>
                    
                    
                                <div class="row">
                        
                                    <div class ="col-md-3 vv">
                                        hh
                                    </div>
                    
                                    <div class ="col-md-6 xx">
                            
                                        <c:if test="${param.login_error == 1}">
                                            <h3>Wrong username or password!</h3>
                                        </c:if>
                                    
                                    
                                        <form class="form-horizontal" role="form" method="post" action="j_spring_security_check">
                                
                                
                                            <div class="form-group">
                                    
                                                <div class="col-md-4 loginLabels">
                                                    <label for="j_username" class="control-label">Username:</label>
                                                </div>
                                    
                                                <div class="col-md-8 loginLabels">
                                                    <input type="text" class="form-control" name="j_username" placeholder="Username"/>
                                                </div>
                                            </div>
                                
                                
                                            <div class="form-group">
                                    
                                                <div class="col-md-4 loginLabels">
                                                    <label for="j_password" class="control-label">Password:</label>
                                                </div>
                                    
                                                <div class="col-md-8">
                                                    <input type="password" class="form-control" name="j_password" placeholder="Password"/>
                                                </div>
                                    
                                            </div>
                                
                                
                                            <div class="form-group">
                                
                                                <div class="col-md-offset-4 col-md-8">
                                                    <input type="submit" class="btn btn-default" id="search-button" value="Sign In"/>
                                                </div>
                                        
                                            </div>
                                
                                
                                        </form>
                            
                            
                                    </div>
                        
                                    <div class ="col-md-3 vv">
                                        hh
                                    </div>


                                </div>
                    
                                        
                                <br>  <br>  <br>  <br>
                    
                            </div>

                    
                    

                    
                    
                    
              <!--end of container of the sign in text and form-->
                </div>
            </div>


                   
                                
                                
                                
                                
                                
       <!--end of entire page col 12 div--> 
        </div>
                                
       <!--end of entire page row div--> 
            </div>
                                
       <!--end of entire page container div-->          
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>