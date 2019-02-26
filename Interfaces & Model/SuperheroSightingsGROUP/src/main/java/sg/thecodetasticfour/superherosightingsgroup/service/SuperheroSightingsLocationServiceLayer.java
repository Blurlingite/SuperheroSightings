/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsLocationServiceLayer {
    
    public Location createLocation(Location location);
         
    public Location getLocationById(int locationId) throws EntityNotFoundException;

    public List<Location> getAllLocations();

    public void updateLocation(Location location);

    public void deleteLocation(int locationId);
    
    public List<Location> findAllLocationsPersonWasSightedAt(int personId);


}
