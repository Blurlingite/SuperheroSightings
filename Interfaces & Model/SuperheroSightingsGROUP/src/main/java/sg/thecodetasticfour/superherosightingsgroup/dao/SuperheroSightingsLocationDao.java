/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author plainjane
 */
public interface SuperheroSightingsLocationDao {
        
    public Location createLocation(Location location) throws SuperheroSightingsPersistenceException;
         
    public Location getLocationById(int locationId) throws SuperheroSightingsPersistenceException;

    public List<Location> getAllLocations() throws SuperheroSightingsPersistenceException;

    public void updateLocation(Location location) throws SuperheroSightingsPersistenceException;

    public void deleteLocation(int locationId) throws SuperheroSightingsPersistenceException;

    public Location findLocationForSighting(Sighting sighting) throws SuperheroSightingsPersistenceException;
    
    public List<Location> findAllLocationsPersonWasSightedAt(int personId) throws SuperheroSightingsPersistenceException;

}
