/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

/**
 *
 * @author vishnukdawah
 */

@Controller
@RequestMapping(value="/SuperpowerJSPs", method=RequestMethod.GET)
public class SuperpowerController {
    
    SuperheroSightingsSuperpowerServiceLayer superpowerService;
    SuperheroSightingsPersonServiceLayer personService;

    // if service layer can't be injected, the program will fail to start, check ur beans
    @Inject
    public SuperpowerController(SuperheroSightingsSuperpowerServiceLayer superpowerService, SuperheroSightingsPersonServiceLayer personService) {
        this.superpowerService = superpowerService;
        this.personService = personService;
    }
    
    //the homepage  for Superpowers. The superpowers will be listed after this method is executed to superpower.jsp
    @RequestMapping(value="/superpowerHome", method=RequestMethod.GET)
    public String superpowerHome(HttpServletRequest request, Model model){
        
        List<Superpower> superpowerList = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);

        return "/SuperpowerJSPs/superpower";
        
    }


    

    
    @RequestMapping(value="/addSuperpower", method=RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request){
        
        String theSuperpowerName = request.getParameter("superpowerName");
        String theSuperpowerDescription = request.getParameter("superpowerDescription");
                
        
        Superpower addThisSuperpower = new Superpower();
        addThisSuperpower.setSuperpowerName(theSuperpowerName);
        addThisSuperpower.setSuperpowerDescription(theSuperpowerDescription);
        
        superpowerService.createSuperpower(addThisSuperpower);
        
        
        return "redirect:/SuperpowerJSPs/superpowerHome";
        
    }
    
    // When you click on a Superpower's name it will take you here and then display the superpower's info on a JSP called displaySuperpowerDetails
        @RequestMapping(value = "/displaySuperpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) throws EntityNotFoundException {
        
        String superpowerIdAsAString = request.getParameter("theSuperpowerId");
        int superpowerIdAsAnInt = Integer.parseInt(superpowerIdAsAString);
        
        Superpower powerToDisplay = superpowerService.getSuperpowerById(superpowerIdAsAnInt);
        model.addAttribute("powerToDisplay", powerToDisplay);
        
        List<Person> personsThatHaveThisSuperpower = personService.findPersonsForSuperpower(powerToDisplay);
        model.addAttribute("personsThatHaveThisSuperpower", personsThatHaveThisSuperpower);

        return "/SuperpowerJSPs/displaySuperpowerDetails";
    }
    
    
    
    
    @RequestMapping(value = "/displayEditPowerForm", method = RequestMethod.GET)
    public String displayEditPowerForm(HttpServletRequest request, Model model) throws EntityNotFoundException {
        
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        Superpower getPowerToEdit = superpowerService.getSuperpowerById(powerId);
        model.addAttribute("getPowerToEdit", getPowerToEdit);
        return "/SuperpowerJSPs/editSuperpower";
    }
    
    
    
    
    
    
    
    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@Valid @ModelAttribute("getPowerToEdit") Superpower superpower, BindingResult result) {

//            if (result.hasErrors()) {
//        return "redirect:/editSuperpower";
//    }
        
        superpowerService.updateSuperpower(superpower);

        return "redirect:superpowerHome";
    }
    
    
    
    
    @RequestMapping(value="/deleteSuperpower", method=RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request, Model model){
        

       String superpowerIdString = request.getParameter("superpowerId");
       
       int superpowerId = Integer.parseInt(superpowerIdString);
       
         superpowerService.deleteSuperpower(superpowerId);
        
        return "redirect:/SuperpowerJSPs/superpowerHome";
        
    }
    
    
    
    
    
        @RequestMapping(value = "/handleSuperpowerExceptions", method = RequestMethod.GET)
    public String handleSuperpowerExceptions(HttpServletRequest request){

        // get the message from a controller endpoint that redirected you here and use it to set message in service layer
        String e = request.getParameter("errorMessage");
//        service.setErrorMessageFromService(e);
        
        return "redirect:/superpower";

        
    }
    
    
    
    
    
}
