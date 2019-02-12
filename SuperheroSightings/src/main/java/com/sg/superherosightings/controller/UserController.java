/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsUserServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityAlreadyExistsException;

/**
 *
 * @author vishnukdawah
 */
@Controller
public class UserController {
    
//Create a private PasswordEncoder class variable. Make sure you import the org.springframework.security.crypto.password.PasswordEncoder. 
    //There is another PasswordEncoder interface in the Spring Security library but it has been deprecated and you will get a weird variable with a strikethrough in it
   private PasswordEncoder encoder;
//   boolean trueRole = true;
   
   
    SuperheroSightingsUserServiceLayer userService;
    SuperheroSightingsOrganizationServiceLayer organizationService;

//    Have Spring use auto-wired constructor injection to hand our class an instance of the PasswordEncoder.
    //The BCryptPasswordEncoder spring-security.xml bean entry that we created in the last step will be handed to us at 
    // runtime (BCryptPasswordEncoder implements PasswordEncoder).
    
    
    // if service layer can't be injected, the program will fail to start, check ur beans
    @Inject
    public UserController(PasswordEncoder encoder, SuperheroSightingsUserServiceLayer userService, SuperheroSightingsOrganizationServiceLayer organizationService) {
        this.encoder = encoder;
        this.userService = userService;
        this.organizationService = organizationService;
    }
    
    //the homepage  for Superpowers. The superpowers will be listed after this method is executed to superpower.jsp
    @RequestMapping(value="/userHome", method=RequestMethod.GET)
    public String userHome(HttpServletRequest request, Model model){
        
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        
        List<Organization> organizationList = organizationService.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        
        String theErrorMessage = request.getParameter("errorMsg");
        model.addAttribute("theErrorMessage", theErrorMessage);


        return "/UserJSPs/user";
        
    }





    
    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public String addUser(HttpServletRequest request, String userName, String userPassword, String firstName, String lastName, String email, Boolean isEnabled, Model model) throws EntityNotFoundException{
        
        
        String[] organizationsChosenByUser = request.getParameterValues("organizationsSelectedByUser");
        List<Organization> allOrganizationsFromUser = new ArrayList<>();
        
        for(String currentString : organizationsChosenByUser){
            
            int organizationIdInt = Integer.parseInt(currentString);
            Organization currentOrganizationFound = organizationService.getOrganizationById(organizationIdInt);
            allOrganizationsFromUser.add(currentOrganizationFound);
        }
        
        
//        We must take the incoming password variable (in the parameter of this method) and hash it using the password encoder
// then we will set that password to the user (make sure in ur database to have the password field set to a reasoable amount like 100 for example,
// because it will be long
        String hashedPasswordUsingEncoderInThisMethodsConstructor = encoder.encode(userPassword);
        User u = new User();
        
        u.setUserName(userName);
        u.setUserPassword(hashedPasswordUsingEncoderInThisMethodsConstructor);

//        u.setUserPassword(userPassword);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setIsEnabled(isEnabled);
        u.setUserOrganizations(allOrganizationsFromUser);
        
         // All users have ROLE_USER, only add ROLE_ADMIN if the is an Admin 
        // box is checked
        u.addAuthority("ROLE_USER");
//        if (null != request.getParameter("isEnabled")) {
//            u.addAuthority("ROLE_ADMIN");
//        }

//        // to control display of user's role
//         trueRole = true; 
        
//Uncomment here
        if (true == isEnabled) {
//            trueRole = true;
//            u.setTrueRole(true);
            u.addAuthority("ROLE_ADMIN");
        }else if(false == isEnabled){
//            trueRole = false;
//            u.setTrueRole(false);
            u.addAuthority("ROLE_USER");
            u.setIsEnabled(true);
        }

//        if (true != isEnabled) {
//            u.addAuthority("ROLE_ADMIN");
//        }else if(false != isEnabled){
//            u.addAuthority("ROLE_USER");
//            u.setIsEnabled(true);
//        }
// End uncomment here


        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
//        u.addAuthority("ROLE_USER");
//        if (null != request.getParameter("isAdmin")) {
//            u.addAuthority("ROLE_ADMIN");
//        }
        
        try {
            userService.createUser(u);
        } catch (EntityAlreadyExistsException ex) {
           String userExistsAlready = ex.getMessage();
           model.addAttribute("userExistsAlready", userExistsAlready);
           return "redirect:/handleUserExceptions";
        }
        
//       model.addAttribute("trueRole", trueRole);

        return "redirect:/userHome";
        
    }
    
    
    
    @RequestMapping(value="/displayUserDetails", method=RequestMethod.GET)
    public String displayUserDetails(HttpServletRequest request, Model model) throws EntityNotFoundException{
        
        String usernameString = request.getParameter("theUsername");
        
        User userToDisplay = userService.getUserByUsername(usernameString);
        model.addAttribute("userToDisplay", userToDisplay);
        
        List<Organization> allOrganizationsOfUser = userToDisplay.getUserOrganizations();
        model.addAttribute("allOrganizationsOfUser", allOrganizationsOfUser);

        List<String> allUserAuthorities = userToDisplay.getAuthorities();
        model.addAttribute("allUserAuthorities", allUserAuthorities);

        return "/UserJSPs/displayUserDetails";
        
    }
    
    
    
    
    @RequestMapping(value="/displayEditUserForm", method=RequestMethod.GET)
    public String displayEditUserForm(HttpServletRequest request, Model model) throws EntityNotFoundException{
        
        String username = request.getParameter("userNameOfUser");

        
        User userToDisplay = userService.getUserByUsername(username);

        model.addAttribute("userToDisplay", userToDisplay);
        
        List<Organization> organizationsOfUser = userToDisplay.getUserOrganizations();
        
        model.addAttribute("organizationsOfUser", organizationsOfUser);
        
        List<Organization> allOrganizations = organizationService.getAllOrganizations();
        model.addAttribute("allOrganizations", allOrganizations);


        return "/UserJSPs/editUser";
        
    }
    
    
    @RequestMapping(value="/editUser", method=RequestMethod.POST)
    public String editUser(HttpServletRequest request, @ModelAttribute("userToDisplay") User user, Model model) throws EntityNotFoundException, SuperheroSightingsPersistenceException{

       String hashTheEditedPassword = encoder.encode(user.getUserPassword());
       user.setUserPassword(hashTheEditedPassword);
       
        List<Integer> organizationIds = user.getAllOrganizationIdsToPopulateOrganizationListInUserDTO();
        List<Organization> organizationsToSetToUser = new ArrayList<>();
        
        for(Integer currentId : organizationIds){
            
            Organization currentOrganization = organizationService.getOrganizationById(currentId);
            organizationsToSetToUser.add(currentOrganization);
        }
        
        
        
        if(user.getIsEnabled() == true){
            user.addAuthority("ROLE_ADMIN");
            user.addAuthority("ROLE_USER");
        }else{
            user.addAuthority("ROLE_USER");
        }
        
        
        

        
        user.setUserOrganizations(organizationsToSetToUser);
        userService.updateUser(user);

        return "redirect:/userHome";
        
    }
    
    
        
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String theUserNameString = request.getParameter("theUsername");
        
        userService.deleteUser(theUserNameString);

        return "redirect:userHome";
    }
    
    
    
    @RequestMapping(value = "/handleUserExceptions", method = RequestMethod.GET)
    public String handleUserExceptions(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String errorMsg = request.getParameter("userExistsAlready");
        
        model.addAttribute("errorMsg", errorMsg);
  
        return "redirect:userHome";
    }
    
    
        

    
    
    
    
        
    
    
    
    
        
    
    
    
    
        
    
    
    
    
    
}
