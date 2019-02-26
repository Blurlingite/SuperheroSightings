/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsSightingServiceLayer {
    
    public Sighting createSighting (Sighting sighting); 
    
    public Sighting getSightingById (int sightingId) throws EntityNotFoundException;
    
    public List<Sighting> getAllSightings(); 
    
    public void updateSighting(Sighting sighting); 
    
    public void deleteSighting (int sightingId);
    
    public List<Sighting> getLatestTenSightings();
    
    public List<Sighting> getAllSightingsByLocation(int locationId);
    
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected);

    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld);


}
