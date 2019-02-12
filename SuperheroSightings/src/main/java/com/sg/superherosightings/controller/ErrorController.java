/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vishnukdawah
 */
@Controller
class ErrorController {
    // map this end point to /error to match the configuration in web.xml
    @RequestMapping(value = "/error")
    public String customError(HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        // retrieve some useful information from the request
        Integer statusCode
          = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        Throwable throwable
          = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = null;
        exceptionMessage = httpStatus.getReasonPhrase();

        // casting it to a string here? Why are we doing that if its already a string?
        String requestUri
          = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        
        

        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        
                String errorB = (String) request.getAttribute("javax.servlet.error.exception_type");


                                String errorD = (String) request.getAttribute("javax.servlet.error.servlet_name");

        
        // format the message for the view
        // is this like prepared statements, where you plug in things after the string, to be put into the string?
        // the {0} would get statusCode
        // the {1} would get requestUri
        // the {2} would get exceptionMessage
        String message = MessageFormat.format("{0} returned for {1}: {2} {3}",
                statusCode, requestUri, exceptionMessage);

        model.addAttribute("errorMessage", message);
        return "customError";
    }
    
    
// Code Analysis
//We map this method to the /error endpoint to match the web.xml configuration.
//The bulk of the code in this method is for gathering useful information about the request and the error message:
//HTTP Status code.
//Any information from the thrown error.
//Error message.
//We format the information and make it available to the the customError JSP.
    
    
}
