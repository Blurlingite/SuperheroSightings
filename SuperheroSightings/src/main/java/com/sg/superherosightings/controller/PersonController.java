/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

/**
 *
 * @author vishnukdawah
 */
@Controller
public class PersonController {
    
    SuperheroSightingsPersonServiceLayer personService;
    SuperheroSightingsSuperpowerServiceLayer theSuperpowerService;
    SuperheroSightingsOrganizationDao theOrganizationService;
    SuperheroSightingsLocationServiceLayer locationService;

    // if service layer can't be injected, the program will fail to start, check ur beans
    @Inject
    public PersonController(SuperheroSightingsPersonServiceLayer personService, SuperheroSightingsSuperpowerServiceLayer theSuperpowerService, SuperheroSightingsOrganizationDao theOrganizationService, SuperheroSightingsLocationServiceLayer locationService) {
        this.personService = personService;
        this.theSuperpowerService = theSuperpowerService;
        this.theOrganizationService = theOrganizationService;
        this.locationService = locationService;
    }
    
    
    
    
        //the homepage  for Persons. The persons will be listed after this method is executed to person.jsp
    @RequestMapping(value="/personHome", method=RequestMethod.GET)
    public String personHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException{
        
        List<Person> personList = personService.getAllPersons();
        model.addAttribute("personList", personList);
        
        // so user can select multiple superpowers when they go to add a person
        List<Superpower> allSuperpowers = theSuperpowerService.getAllSuperpowers();
        model.addAttribute("allSuperpowers", allSuperpowers);
        
        // so user can select multiple organizations when they go to add a person
        List<Organization> allOrganizations = theOrganizationService.getAllOrganizations();
        model.addAttribute("allOrganizations", allOrganizations);
        
        

        return "/PersonJSPs/person";
        
    }






    
    @RequestMapping(value="/addPerson", method=RequestMethod.POST)
    public String addPerson(HttpServletRequest request, Model model) throws EntityNotFoundException, SuperheroSightingsPersistenceException{
             
        // list to hold all the powers you will set to the Person DTO
        List<Superpower> allSuperpowersToAddToPerson = new ArrayList<>();
        
        // this array handles every option selected in the drop down menu when the user is picking the superpowers to add to the person
        // each option's value has a superpowerId attached to it which will be passed into this array
        // the getParameterValues works just like getParameter, except it can take in multiple values with just one
        // string (the string you gave the select tag in the person.jsp which was powersSelectedByUser )
        String[] superpowerIdStringsFromDropDownMenu = request.getParameterValues("powersSelectedByUser");
        
        // now that you have an array of strings of superpower IDs, convert each one into an int, get the superpower by that int and
        // add it to the list of superpowers.
        // Now you can set that list of superpowers onto the Person DTO, and then use the update method
        for(String currentString : superpowerIdStringsFromDropDownMenu){

            int currentSuperpowerInt = Integer.parseInt(currentString);
            
            Superpower currentSuperpower = theSuperpowerService.getSuperpowerById(currentSuperpowerInt);
            
            allSuperpowersToAddToPerson.add(currentSuperpower);
        }
        
        
        // Now do the same thing for organizations
        List<Organization> allOrganizationsToAddToPerson = new ArrayList<>();

        String[] organizationIdStringsFromDropDownMenu = request.getParameterValues("organizationsSelectedByUser");
        
        for(String currentString : organizationIdStringsFromDropDownMenu){

            int currentOrganizationInt = Integer.parseInt(currentString);
            
            Organization currentOrganization = theOrganizationService.getOrganizationById(currentOrganizationInt);
            
            allOrganizationsToAddToPerson.add(currentOrganization);
        }
        
        
        
        
        
        
        
        String thePersonFirstName = request.getParameter("personFirstName");
        String thePersonLastName = request.getParameter("personLastName");
        
        String isHeroString = request.getParameter("isHero");
        Boolean isHeroBool = Boolean.parseBoolean(isHeroString);
        String thePersonDescription = request.getParameter("personDescription");
            

        
        Person addThisPerson = new Person();
        addThisPerson.setFirstName(thePersonFirstName);
        addThisPerson.setLastName(thePersonLastName);
        addThisPerson.setIsHero(isHeroBool);
        addThisPerson.setDescriptionOfPerson(thePersonDescription);
        addThisPerson.setListOfSuperpowers(allSuperpowersToAddToPerson);    // set all the superpowers you got from the drop down menu to the Person DTO
        addThisPerson.setListOfOrganizations(allOrganizationsToAddToPerson);
        personService.createPerson(addThisPerson);
        
        
        return "redirect:/personHome";

        
    }
    
    @RequestMapping(value="/displayPersonDetails", method=RequestMethod.GET)
    public String displayPersonDetails(HttpServletRequest request, Model model) throws EntityNotFoundException{
        
        String personIdString = request.getParameter("thePersonId");
        int personIdInt = Integer.parseInt(personIdString);
        
        Person personDetails = personService.getPersonById(personIdInt);
        
        
        List<Superpower> superpowerDetailsForPerson = personDetails.getListOfSuperpowers();
        
        model.addAttribute("personDetails", personDetails);
        model.addAttribute("superpowerDetailsForPerson", superpowerDetailsForPerson);

        
        List<Organization> organizationDetailsForPerson = personDetails.getListOfOrganizations();
        
        model.addAttribute("organizationDetailsForPerson", organizationDetailsForPerson);
        
        
        
        List<Location> allLocationsForPerson = locationService.findAllLocationsPersonWasSightedAt(personDetails.getPersonId());
        model.addAttribute("allLocationsForPerson", allLocationsForPerson);

        return "/PersonJSPs/displayPersonDetails";
        
    }
    
    
        @RequestMapping(value="/displayEditPersonForm", method=RequestMethod.GET)
    public String displayEditPersonForm(HttpServletRequest request, Model model) throws EntityNotFoundException, SuperheroSightingsPersistenceException{
        
        String personIdString = request.getParameter("personId");

        int personIdInt = Integer.parseInt(personIdString);
        
        Person personToDisplay = personService.getPersonById(personIdInt);

        model.addAttribute("personToDisplay", personToDisplay);
        
        List<Superpower> powersFromDatabase = personToDisplay.getListOfSuperpowers();
        
        model.addAttribute("powersFromDatabase", powersFromDatabase);
        
        
       List<Superpower> allPowers = theSuperpowerService.getAllSuperpowers();
        model.addAttribute("allPowers", allPowers);

        List<Organization> allOrganizations = theOrganizationService.getAllOrganizations();
        model.addAttribute("allOrganizations", allOrganizations);

        return "/PersonJSPs/editPerson";
        
    }
    
    
    
    @RequestMapping(value="/editPerson", method=RequestMethod.POST)
    public String editPerson(HttpServletRequest request, @ModelAttribute("personToDisplay") Person person) throws EntityNotFoundException, SuperheroSightingsPersistenceException{

        String[] organizationIdsSelectedFromDropDownMenu = request.getParameterValues("organizationsSelectedByUser");

        List<Organization> organizationsToAddToPerson = new ArrayList<>();
        
        for(String currentString: organizationIdsSelectedFromDropDownMenu){
            int currentOrganizationIdInt = Integer.parseInt(currentString);
            Organization currentOrganization = theOrganizationService.getOrganizationById(currentOrganizationIdInt);        
            organizationsToAddToPerson.add(currentOrganization);
        }
        
        
        List<Integer> superpowerIdInts = person.getListOfSuperpowerIdsToPopulateSuperpowerListInPersonDTO();
        List<Superpower> listOfSuperpowersToGetFromListOfInts = new ArrayList<>();
    
        for(Integer currentInt : superpowerIdInts){
        
            Superpower superpowerToAddToPersonDTO = theSuperpowerService.getSuperpowerById(currentInt);
            listOfSuperpowersToGetFromListOfInts.add(superpowerToAddToPersonDTO);
        
        }
    
        person.setListOfSuperpowers(listOfSuperpowersToGetFromListOfInts);
        person.setListOfOrganizations(organizationsToAddToPerson);

        personService.updatePerson(person);

        return "redirect:/personHome";
        
    }
    
    
    
    
    

    
    
    
    
    
    
    
    
        @RequestMapping(value="/deletePerson", method=RequestMethod.GET)
    public String deletePerson(HttpServletRequest request, Model model) throws EntityNotFoundException{
        
        String personIdString = request.getParameter("personIdToDelete");
        int personIdInt = Integer.parseInt(personIdString);
        
        personService.deletePerson(personIdInt);

        return "redirect:/personHome";
        
    }
    
    
      
            @RequestMapping(value = "/handlePersonExceptions", method = RequestMethod.GET)
    public String handlePersonExceptions(HttpServletRequest request){

        // get the message from a controller endpoint that redirected you here and use it to set message in service layer
        String e = request.getParameter("errorMessage");
//        service.setErrorMessageFromService(e);
        
        return "redirect:/personHome";

        
    }
    
    
      
    
    
    
      
    
    
    
      
    
    
    
      
    
    
    
    
    
    
}
