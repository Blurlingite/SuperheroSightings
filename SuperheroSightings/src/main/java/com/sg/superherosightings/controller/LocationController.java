/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;

/**
 *
 * @author vishnukdawah
 */
@Controller
public class LocationController {
    
        SuperheroSightingsLocationServiceLayer locationService;
        SuperheroSightingsSightingServiceLayer sightingsService;

    @Inject
     public LocationController(SuperheroSightingsLocationServiceLayer locationService, SuperheroSightingsSightingServiceLayer sightingsService) {
        this.locationService = locationService;
        this.sightingsService = sightingsService;
    }
        
    
    @RequestMapping(value="/locationHome", method=RequestMethod.GET)
    public String locationHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException{
        
        List<Location> locationList = locationService.getAllLocations();
        model.addAttribute("locationList", locationList);
        

        return "/LocationJSPs/location";
        
    }


    
    
    
    @RequestMapping(value="/addLocation", method=RequestMethod.POST)
    public String addLocation(HttpServletRequest request) throws SuperheroSightingsPersistenceException, EntityNotFoundException{

        
        String theLocationName = request.getParameter("locationName");
        String theLocationDescription = request.getParameter("locationDescription");

        String theLocationStreet = request.getParameter("locationStreet");
        String theLocationCity = request.getParameter("locationCity");
        String theLocationState = request.getParameter("locationState");
        String theLocationZipcode = request.getParameter("locationZipcode");
        String theLocationCountry = request.getParameter("locationCountry");
        
        
        String theLocationLatitudeString = request.getParameter("latitude");
        String theLocationLongitudeString = request.getParameter("longitude");
        
        BigDecimal theLocationLatitude = new BigDecimal(theLocationLatitudeString).setScale(4, RoundingMode.HALF_UP);
        BigDecimal theLocationLongitude = new BigDecimal(theLocationLongitudeString).setScale(4, RoundingMode.HALF_UP);

        
        Location addThisLocation = new Location();
        addThisLocation.setLocationName(theLocationName);
        addThisLocation.setLocationDescription(theLocationDescription);
        addThisLocation.setLocationStreet(theLocationStreet);
        addThisLocation.setLocationCity(theLocationCity);
        addThisLocation.setLocationState(theLocationState);
        addThisLocation.setLocationZipcode(theLocationZipcode);
        addThisLocation.setLocationCountry(theLocationCountry);
        addThisLocation.setLatitude(theLocationLatitude);
        addThisLocation.setLongitude(theLocationLongitude);
        
        
        locationService.createLocation(addThisLocation);
        
        
        return "redirect:/locationHome";
        
    }
        
    
    
        
    @RequestMapping(value="/displayLocationDetails", method=RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException, EntityNotFoundException{
        
        String locationIdAsAString = request.getParameter("theLocationId");
        int locationIdAsAnInt = Integer.parseInt(locationIdAsAString);
        
        Location locationToDisplay = locationService.getLocationById(locationIdAsAnInt);
        model.addAttribute("locationToDisplay", locationToDisplay);
        
        
        List<Sighting> allSightingsByLocation = sightingsService.getAllSightingsByLocation(locationIdAsAnInt);
        model.addAttribute("allSightingsByLocation", allSightingsByLocation);

        
        return "/LocationJSPs/displayLocationDetails";
        
    }
    
        
    
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) throws EntityNotFoundException, SuperheroSightingsPersistenceException {
        
       
        String locationIdParameter = request.getParameter("theIdOfLocation");
        int locationId = Integer.parseInt(locationIdParameter);
        Location getLocationToEdit = locationService.getLocationById(locationId);
        
        
        model.addAttribute("getLocationToEdit", getLocationToEdit);

        return "/LocationJSPs/editLocation";
    }
    
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@ModelAttribute("getLocationToEdit") Location location) {

        locationService.updateLocation(location);

        return "redirect:locationHome";
    }
    
    
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String theLocationIdString = request.getParameter("theLocationId");
        
        int theLocationIdInt = Integer.parseInt(theLocationIdString);
        
        locationService.deleteLocation(theLocationIdInt);

        return "redirect:locationHome";
    }
    
    
        
    
    
    
    

}
