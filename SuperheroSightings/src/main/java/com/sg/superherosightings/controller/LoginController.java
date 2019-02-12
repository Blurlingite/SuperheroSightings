/*
The login controller and JSP for this application will be identical to those we created for the Hello Security application. 

Handles when the user is logging in
 */
package com.sg.superherosightings.controller;

/**
 *
 * @author vishnukdawah
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
//The RequestMapping for this must match the value of login-page in the spring-security.xml file.
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
}