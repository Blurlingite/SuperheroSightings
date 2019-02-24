/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;

/**
 *
 * @author vishnukdawah  
 */
public class SightingServiceLayerImpl implements SuperheroSightingsSightingServiceLayer {

    SuperheroSightingsSightingDao sightingDao;
    SuperheroSightingsLocationDao locationDao;
    SuperheroSightingsPersonDao personDao;
    SuperheroSightingsSuperpowerDao superpowerDao;
    SuperheroSightingsOrganizationDao organizationDao;


    public SightingServiceLayerImpl(SuperheroSightingsSightingDao sightingDao, SuperheroSightingsLocationDao locationDao, SuperheroSightingsPersonDao personDao, SuperheroSightingsSuperpowerDao superpowerDao) {
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.personDao = personDao;
        this.superpowerDao = superpowerDao;
    }

    
    
    @Override
    public Sighting createSighting(Sighting sighting) {
       
        try {
            
            return sightingDao.createSighting(sighting);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public Sighting getSightingById(int sightingId) throws EntityNotFoundException {
        
        Sighting retrievedSighting = new Sighting();

        try {
            
            retrievedSighting = sightingDao.getSightingById(sightingId);
            
            if(retrievedSighting == null){
                
                throw new EntityNotFoundException("Could not find Sighting");
                
            }
            
            Location loc = locationDao.findLocationForSighting(retrievedSighting);
            Person p = personDao.findPersonForSighting(retrievedSighting);
            // must find the superpowers for person in database and set it each time you have a new Person DTO
            p.setListOfSuperpowers(superpowerDao.findSuperpowersForPerson(p));
            retrievedSighting.setPerson(p);
            retrievedSighting.setLocation(loc);
            
            return retrievedSighting;

        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public List<Sighting> getAllSightings() {
        
        try {
            
            List<Sighting> allSightings = sightingDao.getAllSightings();
            
            return associatePersonsAndLocationsWithSightings(allSightings);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public void updateSighting(Sighting sighting) {
        
        try {
            
            sightingDao.updateSighting(sighting);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void deleteSighting(int sightingId) {
        
        try {
            
            sightingDao.deleteSighting(sightingId);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }



    @Override
    public List<Sighting> getLatestTenSightings() {
        
        try {
            
            List<Sighting> allSightings = sightingDao.getLatestTenSightings();
            
            // find Person and Location for each Sighting so they are not null
            return associatePersonsAndLocationsWithSightings(allSightings);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;

        }
        
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(int locationId) {
        
        try {
            
            List<Sighting> sightingsByLocation = sightingDao.getAllSightingsByLocation(locationId);
            
            return associatePersonsAndLocationsWithSightings(sightingsByLocation);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }
    
    
    
    private List<Sighting> associatePersonsAndLocationsWithSightings(List<Sighting> sightingList) throws SuperheroSightingsPersistenceException{
        
        for (Sighting currentSighting : sightingList) {

            //  set Person
                Person personToSetToSighting = personDao.findPersonForSighting(currentSighting);
                List<Superpower> superpowersForPerson = superpowerDao.findSuperpowersForPerson(personToSetToSighting);
                
                
                personToSetToSighting.setListOfSuperpowers(superpowersForPerson);
                
                
                currentSighting.setPerson(personToSetToSighting);
                
            // set Location
                Location locationToSetToSighting = locationDao.findLocationForSighting(currentSighting);
                currentSighting.setLocation(locationToSetToSighting);
                
        }
        
        return sightingList;
        
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) {
        
        try {
            
            List<Sighting> sightingList = sightingDao.getAllSightingsByDate(dateSelected);
            
            // associate Person and Location so they aren't null
            return  associatePersonsAndLocationsWithSightings(sightingList);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;

        }
        
    }

    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld) {
       
        try {
            
            List<Sighting> sightingList = sightingDao.getAllSightingsByLocalDate(ld);
            
            // associate Person and Location so they aren't null
            return  associatePersonsAndLocationsWithSightings(sightingList);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
             
            return null;

        }
        
    }
    
    
}
