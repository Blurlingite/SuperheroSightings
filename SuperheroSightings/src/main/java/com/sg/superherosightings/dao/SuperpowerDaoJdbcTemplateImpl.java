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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah 
 */
public class SuperpowerDaoJdbcTemplateImpl implements SuperheroSightingsSuperpowerDao {

    private JdbcTemplate jdbcTemplate;

    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_SUPERPOWER = "INSERT INTO Superpowers(Superpower, SuperpowerDescription) VALUES(?,?);";
    
    private static final String SQL_SELECT_SUPERPOWER_BY_ID = "SELECT * FROM Superpowers WHERE SuperpowerID = ?;";

    private static final String SQL_SELECT_ALL_SUPERPOWERS = "SELECT * FROM Superpowers;";
    
    private static final String SQL_DELETE_SUPERPOWER_BY_ID = "DELETE FROM Superpowers WHERE SuperpowerID = ?;";

    private static final String SQL_UPDATE_SUPERPOWER = "UPDATE Superpowers SET Superpower = ?, SuperpowerDescription = ? WHERE SuperpowerID = ?;";

    // Join the bridge table, PersonSuperpowers with Superpowers table by the SuperpowerID in PersonSuperpowers.
    // The WHERE specifies the PersonID in PersonSuperpowers, since 1 person can have many superpowers, so we are using that person's ID to 
    // get all those superpowers
    private static final String SQL_SELECT_SUPERPOWERS_BY_PERSON_ID = 
            "SELECT sp.SuperpowerID, sp.Superpower, sp.SuperpowerDescription "
          + "FROM Superpowers sp "
          + "JOIN PersonSuperpowers psp on sp.SuperpowerID = psp.SuperpowerID WHERE "
          + "psp.PersonID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superpower createSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER, superpower.getSuperpowerName(), superpower.getSuperpowerDescription());
        
        int superpowerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        superpower.setSuperpowerId(superpowerId);
        
        return superpower;
        
    }

    @Override
    public Superpower getSuperpowerById(int superpowerId) throws SuperheroSightingsPersistenceException {
        
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(),superpowerId);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
        
    }

    @Override
    public List<Superpower> getAllSuperpowers() throws SuperheroSightingsPersistenceException {
        
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, new SuperpowerMapper()); 
               
    }
    


    @Override
    public void updateSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER, superpower.getSuperpowerName(), superpower.getSuperpowerDescription(), 
                superpower.getSuperpowerId());
        
    }

    @Override
    public void deleteSuperpower(int superpowerId) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER_BY_ID, superpowerId);
        
    }

    
    // This method will join the bridge table with the Superpowers table in the database to pull all the superpowers that a person has, with that
    // person's ID
    @Override
    public List<Superpower> findSuperpowersForPerson(Person person){
        
       return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_PERSON_ID, 
               new SuperpowerMapper(), 
               person.getPersonId());
       
    }
    
        // lets netbeans access the Superpower table in the database
        private static final class SuperpowerMapper implements RowMapper<Superpower>{

            @Override
            public Superpower mapRow(ResultSet rs, int i) throws SQLException {
           
                Superpower sp = new Superpower();
          
                sp.setSuperpowerId(rs.getInt("SuperpowerID"));
                sp.setSuperpowerName(rs.getString("Superpower"));
                sp.setSuperpowerDescription(rs.getString("SuperpowerDescription"));
            
                return sp;
            
            }
        
        }

   
}
