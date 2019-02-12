/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author vishnukdawah 
 */
public class LocationDaoJdbcTemplateImpl implements SuperheroSightingsLocationDao {
    

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_LOCATION = "INSERT INTO Location(LocationName, LocationDescription, LocationCountry,"
            + " LocationState, LocationCity, LocationStreet, LocationZipCode, Latitude, Longitude)"
            + " VALUES(?,?,?,?,?,?,?,?,?);"; 
    
    private static final String SQL_SELECT_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?;";
    
    private static final String SQL_SELECT_ALL_LOCATIONS = "SELECT * FROM Location;";
    
    private static final String SQL_UPDATE_LOCATION = "UPDATE Location SET LocationName = ?, LocationDescription = ?, LocationCountry = ?,"
            + " LocationState = ?, LocationCity = ?, LocationStreet = ?, LocationZipCode = ?, Latitude = ?, Longitude = ?"
            + " WHERE LocationID = ?;";
    
    private static final String SQL_DELETE_LOCATION = "DELETE FROM Location WHERE LocationID = ?;";
    
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID = 
             "SELECT l.* " 
             + "FROM Sightings s " 
             + "JOIN Location l ON l.LocationID = s.LocationID " 
             + "WHERE SightingID = ?;";
    
    
        private static final String SQL_SELECT_ALL_LOCATIONS_BY_PERSON_ID = 
                "SELECT DISTINCT l.* " 
                +"FROM Location l "
                +"JOIN Sightings s ON s.LocationID = l.LocationID " 
                +"WHERE s.PersonID = ?;";
                

    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location createLocation(Location location) throws SuperheroSightingsPersistenceException {
       
        jdbcTemplate.update(SQL_INSERT_LOCATION, location.getLocationName(), location.getLocationDescription(),
                location.getLocationCountry(), location.getLocationState(), location.getLocationCity(),
                location.getLocationStreet(), location.getLocationZipcode(), location.getLatitude(), location.getLongitude());
  
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        location.setLocationId(locationId);
        
        return location;

    }

    @Override
    public Location getLocationById(int locationId) throws SuperheroSightingsPersistenceException {
        
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID, new LocationMapper(),locationId);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
        
        
    }

    @Override
    public List<Location> getAllLocations() throws SuperheroSightingsPersistenceException {
        
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        
    }

    @Override
    public void updateLocation(Location location) throws SuperheroSightingsPersistenceException {
       
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getLocationName(), location.getLocationDescription(), location.getLocationCountry(), 
                location.getLocationState(), location.getLocationCity(), location.getLocationStreet(), location.getLocationZipcode(),
                location.getLatitude(), location.getLongitude(), location.getLocationId());
        
    }

    @Override
    public void deleteLocation(int locationId) throws SuperheroSightingsPersistenceException {
       
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
        
    }

    @Override
        public Location findLocationForSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
            
            int sightingId = sighting.getSightingId();
           
          return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingId);
           
        }

    @Override
    public List<Location> findAllLocationsPersonWasSightedAt(int personId) throws SuperheroSightingsPersistenceException {
        
        
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS_BY_PERSON_ID, new LocationMapper(), personId);
        
        return locationList;
    }

    

    
    private static final class LocationMapper implements RowMapper<Location>{

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
           
            Location loc = new Location();
          
            loc.setLocationId(rs.getInt("LocationID"));
            loc.setLocationName(rs.getString("LocationName"));
            loc.setLocationDescription(rs.getString("LocationDescription"));
            loc.setLocationCountry(rs.getString("LocationCountry"));
            loc.setLocationState(rs.getString("LocationState"));
            loc.setLocationCity(rs.getString("LocationCity"));
            loc.setLocationStreet(rs.getString("LocationStreet"));
            loc.setLocationZipcode(rs.getString("LocationZipCode"));
            loc.setLatitude(rs.getBigDecimal("Latitude"));
            loc.setLongitude(rs.getBigDecimal("Longitude"));
            
            return loc;
            
        }
        
    }
    
    
    
    
}
