/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah  
 */
public class PersonDaoJdbcTemplateImpl implements SuperheroSightingsPersonDao {
    
    private static final String SQL_INSERT_PERSON = "INSERT INTO Person(FirstName, LastName, isHero, PersonDescription)" 
            + "VALUES(?,?,?,?);";
    
    private static final String SQL_GET_PERSON_BY_ID = "SELECT * FROM Person WHERE PersonID = ?;";
    
    private static final String SQL_GET_ALL_PERSONS = "SELECT * FROM Person;";
    
    private static final String SQL_UPDATE_PERSON = "UPDATE Person SET FirstName = ?, LastName = ?, isHero = ?, PersonDescription = ? WHERE PersonID = ?;";

    private static final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM Person WHERE PersonID = ?;";
    
    private static final String SQL_INSERT_PERSON_SUPERPOWERS = "INSERT INTO PersonSuperpowers(PersonID, SuperpowerID) VALUES(?,?);";

    private static final String SQL_DELETE_PERSON_SUPERPOWERS = "DELETE FROM PersonSuperpowers WHERE PersonID = ?";
    
     private static final String SQL_GET_PERSON_FOR_SIGHTING = 
             "SELECT p.* " 
             + "FROM Sightings s " 
             + "JOIN Person p ON p.PersonID = s.PersonId " 
             + "WHERE SightingID = ?;";


           // this should be in Person Dao because you are getting a List of Persons. You get Persons from the Person Dao
private static final String SQL_SELECT_PERSONS_BY_ORGANIZATION_ID = 
        "SELECT p.PersonID, p.FirstName, p.LastName, p.isHero, p.PersonDescription " 
        + "FROM Person p " 
        + "JOIN OrganizationMembers om ON p.PersonID = om.PersonID " 
        + "WHERE om.OrganizationID = ?;";     
                
  private static final String SQL_INSERT_ORGANIZATION_MEMBERS = "INSERT INTO OrganizationMembers(OrganizationID, PersonID) VALUES(?,?);";


  private static final String SQL_DELETE_ORGANIZATION_MEMBERS = "DELETE FROM OrganizationMembers WHERE PersonID = ?";

  private static final String SQL_SELECT_PERSONS_BY_SUPERPOWER = 
        "SELECT p.* " 
      + "FROM Person p " 
      + "JOIN PersonSuperpowers ps ON p.PersonID = ps.PersonID " 
      + "WHERE ps.SuperpowerID = ?;";

    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person createPerson(Person person) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_INSERT_PERSON, person.getFirstName(), person.getLastName(),
                person.getIsHero(), person.getDescriptionOfPerson());
        
        person.setPersonId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        
        // this will insert the person's ID and the IDs of each superpower that person has into the bridge table, PersonSuperpowers
        insertPersonSuperpowers(person);
        
//        if(person.getListOfOrganizations() != null){
//            insertPersonIntoOrganizationMembers(person);
//        }
        // a person doesn't have to be a part of any organizations, so we will 
        // only add that person to the OrganizationMembers bridge table if the list of organizations variable on Person DTO is not null
        // if it is null, then we don't need to make an entry in OrganizationMembers bridge table because there are no organizations
        if(person.getListOfOrganizations() != null){
            insertOrganizationMembers(person);
        }
        return person;
    }

    @Override
    public Person getPersonById(int personId) throws SuperheroSightingsPersistenceException {
        
        try{
            // get Person from the database, but oh no! While that person is here in java, 
            //it needs a list of superpowers (as told by the bridge table, PersonSuperpowers in the database), See PersonServiceLayer
            Person person = jdbcTemplate.queryForObject(SQL_GET_PERSON_BY_ID, new PersonMapper(), personId);

            return person;
        }catch(EmptyResultDataAccessException ex){  // thrown if the data was empty?
            return null;
        }
    }

    @Override
    public List<Person> getAllPersons() throws SuperheroSightingsPersistenceException {
      
          return jdbcTemplate.query(SQL_GET_ALL_PERSONS, new PersonMapper());

    }

    @Override
    public void updatePerson(Person person) throws SuperheroSightingsPersistenceException {
        
       jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getIsHero(), person.getDescriptionOfPerson(), person.getPersonId());
       
        // delete PersonSuperpowers relationship with SQL_DELETE_PERSON_SUPERPOWERS (remove the person with the old info from the PersonSuperpowers bridge table)
        // and then reset them by putting the person (with the new info) back in the PersonSuperpowers bridge table
        // using insertPersonSuperpowers()
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPERPOWERS, person.getPersonId());
        insertPersonSuperpowers(person);
        
        // same thing for OrganizationMembers
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, person.getPersonId());

        if(person.getListOfOrganizations() != null){
            insertOrganizationMembers(person);
        }
    }

    @Override
    public void deletePerson(int personId) throws SuperheroSightingsPersistenceException {
        
        // delete PersonSuperpowers relationship for this person by removing the person from the PersonSuperpowers bridge table
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPERPOWERS, personId);
        
        // delete Person from Person table in database
        jdbcTemplate.update(SQL_DELETE_PERSON_BY_ID, personId);
    }
    

    
    
    
      // This method creates an entry in the PersonSuperpowers bridge table, whicj will associate the given Book with each of its Authors.
    // For every Person there is a list of superpowers, so add them all to the bridge table in the database
    private void insertPersonSuperpowers(Person person){
        
        // Why are these final?
        final int personId = person.getPersonId();    // person ID of the person you passed in, for easier re-use it is assigned to a variable
        final List<Superpower> superpowers = person.getListOfSuperpowers();  // all the superpowers assigned to the Person DTO's list variable
        
        // for every superpower in the Person DTO's list variable, go into the bridge table and add them all. The person will be the same, but
        // the loop will go through every superpower, so "currentSuperpower.getSuperpowerId()" will be different with each iteration
        
        //Think of it this way, since you have a List<Superpower> in the Person DTO, everytime you have a Person, you must add it's list of superpowers to the bridge table in the database, it doesn't just magically happen
        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(SQL_INSERT_PERSON_SUPERPOWERS, 
                            personId, 
                            currentSuperpower.getSuperpowerId());
    }
        
        
    }
    
    
    // same as insertPersonSuperpowers() but for OrganizationMembers bridge table (dealing with the adding the person's organizations that the user picks from the drop down menu on the form)
    private void insertOrganizationMembers(Person person){
        
        final int personId = person.getPersonId();    // person ID of the person you passed in, for easier re-use it is assigned to a variable
        final List<Organization> organizations = person.getListOfOrganizations();  
        
        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_MEMBERS, 
                            currentOrganization.getOrganizationId(), personId);
    }
        
        
    }
    
    

    @Override
    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList) throws SuperheroSightingsPersistenceException {
       
       List<Person> personsFromSightings = new ArrayList<>();
        
        for(Sighting currentSighting : sightingList){
            personsFromSightings.add(currentSighting.getPerson());
        }
        
        return personsFromSightings;
    }



    @Override
    public List<Person> findPersonsForOrganization(Organization organization) throws SuperheroSightingsPersistenceException {

        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_ORGANIZATION_ID, 
               new PersonMapper(), 
               organization.getOrganizationId());

    }
    
    @Override
    public Person findPersonForSighting(Sighting sighting) throws SuperheroSightingsPersistenceException{
           
        int sightingId = sighting.getSightingId();
           
        return jdbcTemplate.queryForObject(SQL_GET_PERSON_FOR_SIGHTING, new PersonMapper(), sightingId);
           
       }
    
    
    @Override
    public List<Person> findPersonsForSuperpower(Superpower superpower){
        
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_SUPERPOWER, new PersonMapper(), superpower.getSuperpowerId());

        
    }
    
    
    
    private void insertPersonIntoOrganizationMembers(Person person){
        
        final int personId = person.getPersonId();
        final List<Organization> organizationOfPerson = person.getListOfOrganizations();
        
        for(Organization currentOrganization : organizationOfPerson){
            
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_MEMBERS, currentOrganization.getOrganizationId(), personId);

        }
        
    }
    
    
    
    
        // lets netbeans access the Person table in the database
    private static final class PersonMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
           
            Person p = new Person();
            
            p.setPersonId(rs.getInt("PersonID"));  // PersonID is exactly how that column appears in the database
            p.setFirstName(rs.getString("FirstName"));
            p.setLastName(rs.getString("LastName"));
            p.setIsHero(rs.getBoolean("isHero"));
            p.setDescriptionOfPerson(rs.getString("PersonDescription"));
            
            return p;
            
        }
        
        
        
    }
    
    
  
    }
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  

