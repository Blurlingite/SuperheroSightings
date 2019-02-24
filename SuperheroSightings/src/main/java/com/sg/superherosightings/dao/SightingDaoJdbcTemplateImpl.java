/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author vishnukdawah  
 */
public class SightingDaoJdbcTemplateImpl implements SuperheroSightingsSightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    private static final String SQL_INSERT_SIGHTING = 
              "INSERT INTO Sightings(isHeroSighting, PersonID, LocationID, SightingDate) "
            + "VALUES(?,?,?, ?);";
    
    private static final String SQL_SELECT_SIGHTING_BY_ID = 
              "SELECT * "
            + "FROM Sightings "
            + "WHERE SightingID = ?;";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS = 
              "SELECT * "
            + "FROM Sightings";
    
    private static final String SQL_UPDATE_SIGHTING =
              "UPDATE Sightings SET isHeroSighting = ?, PersonID = ?, LocationID = ?,  SightingDate = ? "
            + "WHERE SightingID = ?;";
    
    private static final String SQL_DELETE_SIGHTING = 
              "DELETE FROM Sightings "
            + "WHERE SightingID = ?";
    
    
    private static final String SQL_SELECT_LATEST_TEN_SIGHTINGS = 
              "SELECT * "
            + "FROM Sightings "
            + "ORDER BY SightingDate DESC LIMIT 10;";


    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION = 
              "SELECT * " 
            + "FROM Sightings " 
            + "WHERE LocationID = ?";
    
    
    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE = 
            "SELECT * " 
          + "FROM Sightings " 
          + "WHERE SightingDate >= ? " 
          + "AND SightingDate < ? + INTERVAL 1 DAY;";


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting createSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getIsHeroSighting(),
                sighting.getPerson().getPersonId(), 
                sighting.getLocation().getLocationId(),
                sighting.getJustTheSightingDate()
        );
       
        
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingId(sightingId);
        
        return sighting;
        
    }

    @Override
    public Sighting getSightingById(int sightingId) throws SuperheroSightingsPersistenceException {
       
        Sighting retrievedSighting = new Sighting();
        
        try{
            retrievedSighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(),sightingId);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
        
        return retrievedSighting;
        
    }

    @Override
    public List<Sighting> getAllSightings() throws SuperheroSightingsPersistenceException {
        
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        
    }

    @Override
    public void updateSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
                
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, 
                sighting.getIsHeroSighting(), 
                sighting.getPerson().getPersonId(), 
                sighting.getLocation().getLocationId(),
                sighting.getJustTheSightingDate(),
                sighting.getSightingId());

    }

    @Override
    public void deleteSighting(int sightingId) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
        
    }


    @Override
    public List<Sighting> getLatestTenSightings() throws SuperheroSightingsPersistenceException {
       
        return jdbcTemplate.query(SQL_SELECT_LATEST_TEN_SIGHTINGS, new SightingMapper());
        
    }
    
    
    // To be used to get a list of sightings by location and then we will extract the Persons in each one in the service layer to get a list of persons sighted at a location
    // To fulfill this requirement: The system must be able to report all of the superheroes sighted at a particular location
    @Override 
    public List<Sighting> getAllSightingsByLocation(int locationId) throws SuperheroSightingsPersistenceException{
        
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION, new SightingMapper(), locationId);
    
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) throws SuperheroSightingsPersistenceException {
        
        // convert LocalDateTime to a LocalDate to be used in query
        LocalDate dateToQueryBy = dateSelected.toLocalDate();
        
        String dateToLookFor = dateToQueryBy.toString();
        
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE, new SightingMapper(), dateToLookFor, dateToLookFor);
    
    }
    
    

    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld) throws SuperheroSightingsPersistenceException {

        String dateToLookFor = ld.toString();
        
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE, new SightingMapper(), dateToLookFor, dateToLookFor);
        
    }
    

        private static final class SightingMapper implements RowMapper<Sighting>{

            @Override
            public Sighting mapRow(ResultSet rs, int i) throws SQLException {
           
                Sighting s = new Sighting();
          
                s.setSightingId(rs.getInt("SightingID"));
                s.setIsHeroSighting(rs.getBoolean("isHeroSighting"));
                s.setJustTheSightingDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());   // have to getTimestamp then convert to LocalDateTime with toLocalDateTime()
           
                return s;
            
            }
        
        }
    
}
