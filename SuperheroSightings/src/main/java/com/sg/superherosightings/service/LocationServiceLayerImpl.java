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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;

/**
 *
 * @author vishnukdawah  
 */
public class LocationServiceLayerImpl implements SuperheroSightingsLocationServiceLayer {
    
    SuperheroSightingsLocationDao locationDao;
    
    List<Location> globalLocationList = new ArrayList<>();


    public LocationServiceLayerImpl(SuperheroSightingsLocationDao locationDao) {
        this.locationDao = locationDao;
    }
    
    

    @Override
    public Location createLocation(Location Location) {
        
        try {
            return locationDao.createLocation(Location);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public Location getLocationById(int locationId) throws EntityNotFoundException {
        Location loc = new Location();
        try {
            loc = locationDao.getLocationById(locationId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if(loc == null){
            throw new EntityNotFoundException("Could not find Location");
        }else{
            return loc;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        
        try {
            return locationDao.getAllLocations();
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public void updateLocation(Location location) {
        
        try {
            locationDao.updateLocation(location);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void deleteLocation(int locationId) {
        
        try {
            locationDao.deleteLocation(locationId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public List<Location> findAllLocationsPersonWasSightedAt(int personId) {
        
        try {
            return locationDao.findAllLocationsPersonWasSightedAt(personId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }



    
    
    
}
