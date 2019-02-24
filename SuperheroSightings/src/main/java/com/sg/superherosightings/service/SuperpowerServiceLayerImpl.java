/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

/**
 *
 * @author vishnukdawah  
 */
public class SuperpowerServiceLayerImpl implements SuperheroSightingsSuperpowerServiceLayer {

    SuperheroSightingsSuperpowerDao superpowerDao;

    public SuperpowerServiceLayerImpl(SuperheroSightingsSuperpowerDao superpowerDao) {
        this.superpowerDao = superpowerDao;
    }
    
    
    @Override
    public Superpower createSuperpower(Superpower superpower) {

        try {
            
             return superpowerDao.createSuperpower(superpower);
             
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public Superpower getSuperpowerById(int superpowerId) throws EntityNotFoundException {
        
        Superpower s = new Superpower();
        
        try {
            
            s = superpowerDao.getSuperpowerById(superpowerId);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        if(s == null){
            
            throw new EntityNotFoundException("Could not find that Superpower");
            
        }
        
        return s;
        
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        
        List<Superpower> listOfSuperpowers = new ArrayList<>();
        
        try {
            
            listOfSuperpowers = superpowerDao.getAllSuperpowers();
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return listOfSuperpowers;
        
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        
        try {
            
            superpowerDao.updateSuperpower(superpower);
        
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
       
        try {
            
            superpowerDao.deleteSuperpower(superpowerId);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public List<Superpower> findSuperpowersForPerson(Person person) {
        
        try {
            
            return superpowerDao.findSuperpowersForPerson(person);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    
}
