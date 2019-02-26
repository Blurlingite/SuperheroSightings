/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author zissah
 */
public interface SuperheroSightingsSightingDao {
    
    public Sighting createSighting (Sighting sighting) throws SuperheroSightingsPersistenceException;
    
    public Sighting getSightingById (int sightingId) throws SuperheroSightingsPersistenceException;
    
    public List<Sighting> getAllSightings() throws SuperheroSightingsPersistenceException;
    
    public void updateSighting(Sighting sighting) throws SuperheroSightingsPersistenceException;
    
    public void deleteSighting (int sightingId) throws SuperheroSightingsPersistenceException;
    
    public List<Sighting> getLatestTenSightings() throws SuperheroSightingsPersistenceException;
    
    public List<Sighting> getAllSightingsByLocation(int locationId) throws SuperheroSightingsPersistenceException;
    
    public List<Sighting> getAllSightingsByDate(LocalDateTime ld) throws SuperheroSightingsPersistenceException;
    
    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld) throws SuperheroSightingsPersistenceException;


}
