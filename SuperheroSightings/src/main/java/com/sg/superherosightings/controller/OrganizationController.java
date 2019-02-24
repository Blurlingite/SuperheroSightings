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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsUserServiceLayer;

/**
 *
 * @author vishnukdawah
 */
@Controller
public class OrganizationController {
    
    SuperheroSightingsOrganizationServiceLayer organizationService;
    SuperheroSightingsPersonServiceLayer personService;
    SuperheroSightingsUserServiceLayer userService;
    SuperheroSightingsSuperpowerServiceLayer superpowerService;

    @Inject
    public OrganizationController(SuperheroSightingsOrganizationServiceLayer organizationService, SuperheroSightingsPersonServiceLayer personService, SuperheroSightingsUserServiceLayer userService, SuperheroSightingsSuperpowerServiceLayer superpowerService) {
        this.organizationService = organizationService;
        this.personService = personService;
        this.userService = userService;
        this.superpowerService = superpowerService;
    }
    
    
    
    
    @RequestMapping(value="/organizationHome", method=RequestMethod.GET)
    public String organizationHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException{
        
        List<Organization> organizationList = organizationService.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        
        List<Person> personList = personService.getAllPersons();
        model.addAttribute("personList", personList);
        
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);

        return "/OrganizationJSPs/organization";
        
    }



    
    @RequestMapping(value="/addOrganization", method=RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) throws SuperheroSightingsPersistenceException, EntityNotFoundException{
        
        
        String theOrganizationName = request.getParameter("organizationName");
        String theOrganizationType = request.getParameter("isItAHeroOrganization");
        boolean heroOrVillian = Boolean.parseBoolean(theOrganizationType);
        String theOrganizationDescription = request.getParameter("organizationDescription");

        String theOrganizationStreet = request.getParameter("organizationStreet");
        String theOrganizationCity = request.getParameter("organizationCity");
        String theOrganizationState = request.getParameter("organizationState");
        String theOrganizationZipcode = request.getParameter("organizationZipcode");
        String theOrganizationCountry = request.getParameter("organizationCountry");
        
        
        List<Person> allPersonsInOrganization = new ArrayList<>();
        String[] allStringPersonsChosenByUser = request.getParameterValues("personsSelectedByUser");
        
        for(String currentString : allStringPersonsChosenByUser){
            
            int currentPersonIdInt = Integer.parseInt(currentString);
            Person currentPersonFound = personService.getPersonById(currentPersonIdInt);
            allPersonsInOrganization.add(currentPersonFound);
        }
        
        
        List<User> allUsersInOrganization = new ArrayList<>();
        String[] allStringUsersChosenByUser = request.getParameterValues("usersSelectedByUser");
        
        for(String currentString : allStringUsersChosenByUser){
            
            User currentUserFound = userService.getUserByUsername(currentString);
            allUsersInOrganization.add(currentUserFound);
        }
        
        Organization addThisOrganization = new Organization();
        addThisOrganization.setOrganizationName(theOrganizationName);
        addThisOrganization.setIsItAHeroOrganization(heroOrVillian);
        addThisOrganization.setOrganizationDescription(theOrganizationDescription);
        addThisOrganization.setOrganizationStreet(theOrganizationStreet);
        addThisOrganization.setOrganizationCity(theOrganizationCity);
        addThisOrganization.setOrganizationState(theOrganizationState);
        addThisOrganization.setOrganizationZipcode(theOrganizationZipcode);
        addThisOrganization.setOrganizationCountry(theOrganizationCountry);
        
        
        addThisOrganization.setListOfPersons(allPersonsInOrganization);
        addThisOrganization.setOrganizationAdmins(allUsersInOrganization);

        
        organizationService.createOrganization(addThisOrganization);
        
        
        return "redirect:/organizationHome";
        
    }

  

    
    @RequestMapping(value="/displayOrganizationDetails", method=RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model){
        
        try {
            String organizationIdAsAString = request.getParameter("theOrganizantionId");
            int organizationIdAsAnInt = Integer.parseInt(organizationIdAsAString);
            
            Organization organizationToDisplay = organizationService.getOrganizationById(organizationIdAsAnInt);
            model.addAttribute("organizationToDisplay", organizationToDisplay);
            
            List<Person> personsFromOrganization = organizationToDisplay.getListOfPersons();
            model.addAttribute("personsFromOrganization", personsFromOrganization);
            
            
            List<User> usersFromOrganization = organizationToDisplay.getOrganizationAdmins();
            model.addAttribute("usersFromOrganization", usersFromOrganization);
            
            
            return "/OrganizationJSPs/displayOrganizationDetails";
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(OrganizationController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) throws EntityNotFoundException {
        
       
        String organizationIdParameter = request.getParameter("theIdOfOrganization");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization getOrganizationToEdit = organizationService.getOrganizationById(organizationId);
        
        
        model.addAttribute("getOrganizationToEdit", getOrganizationToEdit);
        
        
      List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        
        
      List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        List<Integer> allUserIDs = new ArrayList<>();
        
        for(User u : allUsers){
            int userId = u.getUserId();
            allUserIDs.add(userId);
            
        }

                model.addAttribute("allUserIDs", allUserIDs);

        

        return "/OrganizationJSPs/editOrganization";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest r, @ModelAttribute("getOrganizationToEdit") Organization organization) throws SuperheroSightingsPersistenceException, EntityNotFoundException {

        String[] personIdStrings = r.getParameterValues("personsSelectedByUserOnEditPage");
        
        List<Person> personsToAddToOrganization = new ArrayList<>();
        
        for(String currentString : personIdStrings){
            
            int currentPersonId = Integer.parseInt(currentString);
            Person currentPerson = personService.getPersonById(currentPersonId);
            personsToAddToOrganization.add(currentPerson);
        }
        
        String[] userIdStrings = r.getParameterValues("adminsSelectedByUserOnEditPage");
        
        List<User> usersToAddToOrganization = new ArrayList<>();
        
        for(String currentString : userIdStrings){
            
            User currentUser = userService.getUserByUsername(currentString);
            usersToAddToOrganization.add(currentUser);
        }
        
        
        organization.setListOfPersons(personsToAddToOrganization);
        organization.setOrganizationAdmins(usersToAddToOrganization);
        
        
        organizationService.updateOrganization(organization);

        return "redirect:/organizationHome";
    }
    
    
    @RequestMapping(value="/deleteOrganization", method=RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException{
        

       String organizationIdString = request.getParameter("theOrganizantionId");
       
       int organizationId = Integer.parseInt(organizationIdString);
       
       organizationService.deleteOrganization(organizationId);
        
        return "redirect:/organizationHome";
        
    }
    
    

   
}
    
    
    
    
    
    
    
    
    

