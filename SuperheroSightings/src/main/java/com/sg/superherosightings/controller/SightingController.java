/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;

/**
 *
 * @author vishnukdawah
 */
@Controller
public class SightingController {
    
    SuperheroSightingsSightingServiceLayer sightingsService;
    SuperheroSightingsPersonServiceLayer personService;
    SuperheroSightingsLocationServiceLayer locationService;

    @Inject
    public SightingController(SuperheroSightingsSightingServiceLayer sightingsService, SuperheroSightingsPersonServiceLayer personService, SuperheroSightingsLocationServiceLayer locationService) {
        this.sightingsService = sightingsService;
        this.personService = personService;
        this.locationService = locationService;
    }
    
    
    
        @RequestMapping(value="/", method=RequestMethod.GET)
    public String newsfeed(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {
        
        List<Sighting> lastTenSightings = sightingsService.getLatestTenSightings();
        
        
        model.addAttribute("lastTenSightings", lastTenSightings);
        
        return "index";
    }
    
    
            @RequestMapping(value="/sightingHome", method=RequestMethod.GET)
    public String sightingHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {
        
        List<Sighting> allSightings = sightingsService.getAllSightings();
        model.addAttribute("allSightings", allSightings);
        
        List<Sighting> improvedDatesAndSightings = new ArrayList<>();
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        for(Sighting s : allSightings){
            
//            LocalDateTime dateAndTime = s.getSightingDate();
            LocalDate date= s.getJustTheSightingDate();
//            String stringDate = date.toString();
//            String stringDate = dateAndTime.toString();

//            String stringDate = dateAndTime.toString();
            
//            LocalDateTime formattedDateAndTime = LocalDateTime.parse(stringDate);
//            LocalDate formattedDate = LocalDate.parse(stringDate);
            s.setJustTheSightingDate(date);

//            s.setSightingDate(formattedDateAndTime);
            
            improvedDatesAndSightings.add(s);
        }
        

        model.addAttribute("improvedDatesAndSightings", improvedDatesAndSightings);
//        model.addAttribute("formatter", formatter);

        List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);

        
        List<Location> availableLocations = locationService.getAllLocations();
        model.addAttribute("availableLocations", availableLocations);

        
         List<Person> personsFromSightingsByACertainLocation = personService.getPersonsFromSightingsByLocation();
        model.addAttribute("personsFromSightingsByACertainLocation", personsFromSightingsByACertainLocation);


        
        List<Person> allPersonsFromSightingsByLocation = personService.getPersonsFromSightingsByLocation();

        
       model.addAttribute("allPersonsFromSightingsByLocation", allPersonsFromSightingsByLocation);


       
       // get the persons and locations by sightingDate in getAllSightingsByDate(), set  and then come here to get those lists from the 
       // service layer. Then add it to the model, so you can display it in sightings.jsp
//       List<Person> personsBySightingDate = personService.getGlobalPersonList();
//       List<Location> locationsBySightingDate = locationService.getGlobalLocationList();
//       
//        model.addAttribute("personsBySightingDate", personsBySightingDate);
//        model.addAttribute("locationsBySightingDate", locationsBySightingDate);
        
        
       
        return "/SightingJSPs/sighting";
    }
    
    
    
    
        @RequestMapping(value="/addSighting", method=RequestMethod.POST)
    public String addSighting(HttpServletRequest request, Model model) throws EntityNotFoundException{
                
        String isHeroSightingString = request.getParameter("isHeroSighting");
        String sightingDateString = request.getParameter("sightingDate");
        
//        2019-01-09T11:11
//        sighingDateString = sightingDateString + " 00:00:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(sightingDateString);
        
        
        
        String thePersonIdString = request.getParameter("thePersonID");
        String theLocationIdString = request.getParameter("theLocationID");
        
        int personIdInt = Integer.parseInt(thePersonIdString);
        Person p = personService.getPersonById(personIdInt);
        
        int locationIdInt = Integer.parseInt(theLocationIdString);
        Location l = locationService.getLocationById(locationIdInt);
        
        
        Boolean isHeroSightingStringBool = Boolean.valueOf(isHeroSightingString);
        
        String dateString = request.getParameter("justTheSightingDate");

        LocalDate theLocalDate = LocalDate.parse(dateString);
    
        
        Sighting addThisSighting = new Sighting();
        addThisSighting.setIsHeroSighting(isHeroSightingStringBool);
        addThisSighting.setJustTheSightingDate(theLocalDate);
//        addThisSighting.setSightingDate(dateTime);
        addThisSighting.setPerson(p);
        addThisSighting.setLocation(l);
        
        sightingsService.createSighting(addThisSighting);
        
        
        return "redirect:/sightingHome";

        
    }
    
    
        @RequestMapping(value="/displaySighting", method=RequestMethod.GET)
    public String displaySighting(HttpServletRequest request, Model model) throws EntityNotFoundException{
                
        String sightingIdString = request.getParameter("sightingId");
  

        int sightingId = Integer.parseInt(sightingIdString);
        Sighting sightingToDisplay =  sightingsService.getSightingById(sightingId);
        
        List<Superpower> personPowers = sightingToDisplay.getPerson().getListOfSuperpowers();
       
        model.addAttribute("sightingToDisplay", sightingToDisplay);
        model.addAttribute("personPowers", personPowers);
        
        
//        LocalDateTime sightingDateAndTime = sightingToDisplay.getSightingDate();
        LocalDate sightingDate = sightingToDisplay.getJustTheSightingDate();

        List<Sighting> displaySightingsByACertainDate = sightingsService.getAllSightingsByLocalDate(sightingDate);

//        List<Sighting> displaySightingsByACertainDate = sightingsService.getAllSightingsByDate(sightingDateAndTime);
        
       model.addAttribute("displaySightingsByACertainDate", displaySightingsByACertainDate);

        
        return "/SightingJSPs/displaySightingDetails";

        
    }
    
    
    
    
            @RequestMapping(value="/displayEditSightingForm", method=RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) throws EntityNotFoundException{
        
        String sightingIdString = request.getParameter("sightingId");

        int sightingIdInt = Integer.parseInt(sightingIdString);

        
        Sighting sightingToEdit = sightingsService.getSightingById(sightingIdInt);
        
        int previousPersonId = sightingToEdit.getPerson().getPersonId();
        model.addAttribute("previousPersonId", previousPersonId);    // add the ID to the model so when you use <c:iF>, it is selected as the current choice in the dropdown menu in editSighting.jsp

        int previousLocationId = sightingToEdit.getLocation().getLocationId();
        model.addAttribute("previousLocationId", previousLocationId);    // add the ID to the model so when you use <c:iF>, it is selected as the current choice in the dropdown menu in editSighting.jsp

        model.addAttribute("sightingToEdit", sightingToEdit);
        
        
        List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        
        
        List<Location> allLocations = locationService.getAllLocations();
        model.addAttribute("allLocations", allLocations);

//        List<Superpower> powersFromDatabase = personToDisplay.getListOfSuperpowers();
        
//        model.addAttribute("powersFromDatabase", powersFromDatabase);
        
        return "/SightingJSPs/editSighting";
        
    }
    
    
    
       @RequestMapping(value="/editSighting", method=RequestMethod.POST)
    public String editSighting(HttpServletRequest request, @ModelAttribute("sightingToEdit") Sighting sighting) throws EntityNotFoundException{
        
        String personIdString = request.getParameter("thePersonID");
        int personIdInt = Integer.parseInt(personIdString);
        
        String locationIdString = request.getParameter("theLocationID");
        int locationIdInt = Integer.parseInt(locationIdString);

        Person personToAddToSighting = personService.getPersonById(personIdInt);
        sighting.setPerson(personToAddToSighting);
        
        Location locationToAddToSighting = locationService.getLocationById(locationIdInt);
        sighting.setLocation(locationToAddToSighting);
        
        sightingsService.updateSighting(sighting);
        
        return "redirect:/sightingHome";
        
    }
    
    
        @RequestMapping(value="/getAllSightingsByLocation", method=RequestMethod.POST)
    public String getAllSightingsByLocation(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {
        
        String chosenLocationString = request.getParameter("selectedIdOfLocation");
        
        int locationIdFromWebpage = Integer.parseInt(chosenLocationString);
        
        List<Sighting> listOfSightings = sightingsService.getAllSightingsByLocation(locationIdFromWebpage);
        
        for(Sighting currentSighting : listOfSightings ){
        
            Person p = personService.findPersonForSighting(currentSighting);
            currentSighting.setPerson(p);
        }
        
        List<Person> personsFromSightings = personService.getAllPersonsSightedAtLocation(listOfSightings);
        
        personService.setPersonsFromSightingsByLocation(personsFromSightings);
        
        
        
        return "redirect:/sightingHome";
    }
    
    
    
    
//     @RequestMapping(value="/getAllSightingsByDate", method=RequestMethod.POST)
//    public String getAllSightingsByDate(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {
//        
//        String selectedDate = request.getParameter("selectedDate");
//        
//        selectedDate = selectedDate + " 00:00";
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//        LocalDateTime sightingDateAndTime = LocalDateTime.parse(selectedDate, formatter);
//        // pass in a string to this method.
//        //This method will convert the string into a LocalDateTime (but since we are only asking for dtae, concatenate the time of 00:00:00 and use that
//        // to run a query against database for all sightings
//        List<Sighting> sightingsByDate = sightingsService.getAllSightingsByDate(sightingDateAndTime);
////        !!  of Superpowers in Person is null
//
//        List<Person> personsFromSightingDates = new ArrayList<>();
//        List<Location> locationFromSightingDates = new ArrayList<>();
//
//        for(Sighting currentSighting: sightingsByDate){
//            
//           personsFromSightingDates.add(currentSighting.getPerson());
//           locationFromSightingDates.add(currentSighting.getLocation());
//        }
//        
//        
//        personService.setGlobalPersonList(personsFromSightingDates);
//        locationService.setGlobalLocationList(locationFromSightingDates);
//
//        return "redirect:/sightingHome";
//    }
    
    
    
        @RequestMapping(value="/deleteSighting", method=RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model){
        

       String sightingIdString = request.getParameter("sightingIdToDelete");
       
       int superpowerId = Integer.parseInt(sightingIdString);
       
        sightingsService.deleteSighting(superpowerId);
        
        return "redirect:/sightingHome";
        
    }
    
    
    
}
    
    

