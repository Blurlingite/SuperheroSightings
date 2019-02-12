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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author vishnukdawah  
 */
public class OrganizationDaoJdbcTemplateImpl implements SuperheroSightingsOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    

private static final String SQL_INSERT_ORGANIZATION = "INSERT INTO Organizations(OrganizationName, isHeroOrganization, OrganizationDescription, OrganizationCountry, OrganizationState, OrganizationCity, OrganizationStreet, OrganizationZipCode) " 
        + "VALUES(?,?,?,?,?,?,?,?);";   
private static final String SQL_SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organizations WHERE OrganizationID = ?;"; 

private static final String SQL_SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organizations;"; 

private static final String SQL_UPDATE_ORGANIZATION = "UPDATE Organizations SET OrganizationName = ?, isHeroOrganization = ?,"
        + " OrganizationDescription = ?, OrganizationCountry = ?, OrganizationState = ?, OrganizationCity = ?, OrganizationStreet = ?,"
        + " OrganizationZipCode = ?  WHERE OrganizationID = ?;";

private static final String SQL_DELETE_ORGANIZATION_BY_ID = "DELETE FROM Organizations WHERE OrganizationID = ?;"; 

private static final String SQL_INSERT_ORGANIZATION_MEMBERS = "INSERT INTO OrganizationMembers(OrganizationID, PersonID) VALUES(?,?);";

private static final String SQL_DELETE_ORGANIZATION_MEMBERS = "DELETE FROM OrganizationMembers WHERE OrganizationID = ?;";

private static final String SQL_GET_ORGANIZATIONS_BY_PERSON_ID = 
        
        "SELECT o.* " 
        + "FROM Organizations o "
        + "JOIN OrganizationMembers om on om.OrganizationID = o.OrganizationID "
        + "WHERE om.PersonID = ?;";


private static final String SQL_INSERT_ORGANIZATION_ADMINS = "INSERT INTO OrganizationAdmins(OrganizationID, UserID) VALUES(?,?);";

private static final String SQL_DELETE_ORGANIZATION_ADMINS = "DELETE FROM OrganizationAdmins WHERE OrganizationID = ?;";

private static final String SQL_SELECT_ORGANIZATIONS_BY_USER_ID = 
        "SELECT o.* "
      + "FROM Organizations o "
      + "JOIN OrganizationAdmins oa ON o.OrganizationID = oa.OrganizationID "
      + "WHERE oa.UserID = ?;";


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization createOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
        
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, organization.getOrganizationName(), organization.getIsItAHeroOrganization(),
                organization.getOrganizationDescription(), organization.getOrganizationCountry(), organization.getOrganizationState(),
                organization.getOrganizationCity(), organization.getOrganizationStreet(), organization.getOrganizationZipcode());
        
        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(organizationId);
        
        insertOrganizationMembers(organization);

        insertOrganizationAdmins(organization);
        return organization;
    }

    @Override
    public Organization getOrganizationById(int organizationId) throws SuperheroSightingsPersistenceException {
        
        try{
            
            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationId);
        
            return organization;
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
        
    }

    @Override
    public List<Organization> getAllOrganizations() throws SuperheroSightingsPersistenceException {
        
           return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());

    }

    @Override
    public void updateOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
       
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, organization.getOrganizationName(), organization.getIsItAHeroOrganization(),
                organization.getOrganizationDescription(), organization.getOrganizationCountry(), organization.getOrganizationState(),
                organization.getOrganizationCity(), organization.getOrganizationStreet(), organization.getOrganizationZipcode(),
                organization.getOrganizationId());
        
        // delete OrganizationMembers relationship with SQL_DELETE_ORGANIZATION_MEMBERS (remove the organization with the old info from the OrganizationMembers bridge table)
        // and then reset them by putting the organization (with the new info) back in the OrganizationMembers bridge table
        // using insertOrganizationMembers()
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, organization.getOrganizationId());
        
        // do the same for OrganizationAdmins bridge table
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_ADMINS, organization.getOrganizationId());

        insertOrganizationMembers(organization);
        insertOrganizationAdmins(organization);
        
    }

    @Override
    public void deleteOrganization(int organizationId) throws SuperheroSightingsPersistenceException {
       
        // delete OrganizationMembers relationship for this organization by removing the organization from the OrganizationMembers bridge table
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, organizationId);
        
        // delete Person from Person table in database
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_BY_ID, organizationId);
        
        
        
    }

    @Override
    public List<Organization> findOrganizationsForPerson(Person person) throws SuperheroSightingsPersistenceException {
        
        List<Organization> o = jdbcTemplate.query(SQL_GET_ORGANIZATIONS_BY_PERSON_ID, new OrganizationMapper(), person.getPersonId());
        return o;
    }

   
 
    private void insertOrganizationMembers(Organization organization){
        
        final int organizationId = organization.getOrganizationId();
        final List<Person> personList = organization.getListOfPersons();
        
        
        for (Person currentPerson : personList) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_MEMBERS, 
                            organizationId, 
                            currentPerson.getPersonId());
        }
    }
    
    
    private void insertOrganizationAdmins(Organization organization){
        
        final int organizationId = organization.getOrganizationId();
        final List<User> userList = organization.getOrganizationAdmins();
        
        
        for (User currentUser : userList) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_ADMINS, 
                            organizationId, 
                            currentUser.getUserId());
        }
    }

    @Override
    public List<Organization> findOrganizationsForUser(User user) throws SuperheroSightingsPersistenceException {
       
       return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_USER_ID, 
               new OrganizationMapper(), 
               user.getUserId());
        
    }

   
    
        private static final class OrganizationMapper implements RowMapper<Organization>{

            @Override
            public Organization mapRow(ResultSet rs, int i) throws SQLException {
            
            
                Organization o = new Organization();
            
                o.setOrganizationId(rs.getInt("OrganizationID"));
                o.setOrganizationName(rs.getString("OrganizationName"));
                o.setIsItAHeroOrganization(rs.getBoolean("isHeroOrganization"));
                o.setOrganizationDescription(rs.getString("OrganizationDescription"));
                o.setOrganizationCountry(rs.getString("OrganizationCountry"));
                o.setOrganizationState(rs.getString("OrganizationState"));
                o.setOrganizationCity(rs.getString("OrganizationCity"));
                o.setOrganizationStreet(rs.getString("OrganizationStreet"));
                o.setOrganizationZipcode(rs.getString("OrganizationZipCode"));

                return o;
            
            }
            
        }
    

    
}
