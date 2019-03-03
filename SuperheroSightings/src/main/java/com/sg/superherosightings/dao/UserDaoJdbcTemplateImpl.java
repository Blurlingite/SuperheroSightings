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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author vishnukdawah
 */
public class UserDaoJdbcTemplateImpl implements SuperheroSightingsUserDao {
    
    private JdbcTemplate jdbcTemplate;
    

    private static final String SQL_INSERT_USER = "INSERT INTO Users(Username, UserPassword, FirstName, LastName, Email, isAdmin) VALUES(?,?,?,?,?,?);";

    private static final String SQL_INSERT_AUTHORITY = "INSERT INTO Authorities (username, authority) VALUES (?, ?)";
    
    private static final String SQL_GET_AUTHORITIES_FOR_USER = 
            "SELECT a.* " 
          + "FROM Authorities a " 
          + "JOIN Users u on u.Username = a.Username "
          + "WHERE u.Username = ?";
    
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM Users WHERE Username = ?;";

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM Users;";

    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM Users WHERE Username = ?;";

    private static final String SQL_UPDATE_USER = 
            "UPDATE Users SET Username = ?, UserPassword = ?, FirstName = ?, LastName = ?, Email = ?, isAdmin = ? WHERE UserID = ?;";

    private static final String SQL_SELECT_USERS_BY_ORGANIZATION_ID = 
        "SELECT u.* "
      + "FROM Users u "
      + "JOIN OrganizationAdmins oa on u.UserID = oa.UserID "
      + "WHERE oa.OrganizationID = ?";
     
    private static final String SQL_DELETE_AUTHORITIES
        = "delete from authorities where username = ?";
    
    private static final String SQL_INSERT_ORGANIZATION_ADMINS_FOR_USER = "INSERT INTO OrganizationAdmins(OrganizationID, UserID) VALUES(?,?);";

    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User createUser(User user) throws SuperheroSightingsPersistenceException {


        
        jdbcTemplate.update(SQL_INSERT_USER, user.getUserName(), user.getUserPassword(), user.getFirstName(), 
                user.getLastName(), user.getEmail(), user.getIsAdmin());
        
        int userId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        user.setUserId(userId);
        
        // Add user orgs to bridge table in database only if organizations is not null!!!!!!!!!!!!!!!
        List<Organization> organizationsOfUser = user.getUserOrganizations();
        
        if(organizationsOfUser != null){
            
            for(Organization o : organizationsOfUser){
            
                jdbcTemplate.update(SQL_INSERT_ORGANIZATION_ADMINS_FOR_USER, o.getOrganizationId(), user.getUserId());

            }
            
         }
        

        
        // now add the user's username and all their authorities to the Authorities table
        for (String authority : user.getAuthorities()) {
            
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                    user.getUserName(), 
                    authority);
        
        
        }
        
        return user;
        
    }

    @Override
    public User getUserByUsername(String username) throws SuperheroSightingsPersistenceException {
       
        try{
            User fromDatabase = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new UserMapper(),username);
            
            // need an authority mapper here, not a user mapper!!!!
            List<String> userAuthorities = jdbcTemplate.query(SQL_GET_AUTHORITIES_FOR_USER, new AuthorityMapper(), username);
            // after finding authorities set them to user so they aren't null on the User DTO
            fromDatabase.setAuthorities(userAuthorities);
            
            return fromDatabase;
            
        }catch(EmptyResultDataAccessException ex){
            
            return null;
            
        } 
        
    }

    @Override
    public List<User> getAllUsers() throws SuperheroSightingsPersistenceException {
       
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper()); 

    }

    @Override
    public void updateUser(User user) throws SuperheroSightingsPersistenceException {
        
        // delete the user's authorities
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, user.getUserName());
        
        
        user.addAuthority("ROLE_USER");

        
        // If they edit the user, and they decide to change that user from user to admin, add the admin role, ROLE_ADMIN to that user
        if(user.getIsAdmin() == true){
            user.addAuthority("ROLE_ADMIN");
        }

        
        jdbcTemplate.update(SQL_UPDATE_USER, user.getUserName(), user.getUserPassword(), 
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getIsAdmin(), user.getUserId());
        
        // now add the user's username and all their authorities to the Authorities table
        for (String authority : user.getAuthorities()) {
            
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                    user.getUserName(), 
                    authority);
        
        
        }
        
        
    }

    @Override
    public void deleteUser(String username) throws SuperheroSightingsPersistenceException {
        
        // first delete all authorities for this user, otherwise you'll get duplicate authorities in your unit tests
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);

        // now delete the user
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, username);
        
    }

    @Override
    public List<User> findUsersForOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
       
       return jdbcTemplate.query(SQL_SELECT_USERS_BY_ORGANIZATION_ID, 
               new UserMapper(), 
               organization.getOrganizationId());
       
    }
    
    
    
    private void insertOrganizationAdmins(User admin){
        
    }
    
    
    
        private static final class UserMapper implements RowMapper<User>{

            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
           
                User u = new User();
          
                u.setUserId(rs.getInt("UserID"));
                u.setUserName(rs.getString("Username"));
                u.setUserPassword(rs.getString("UserPassword"));
                u.setFirstName(rs.getString("FirstName"));
                u.setLastName(rs.getString("LastName"));
                u.setEmail(rs.getString("Email"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));

                return u;
            
            }
        
        }
    
    
    
        private static final class AuthorityMapper implements RowMapper<String>{

            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
            
                String authority = (rs.getString("Authority"));
            
                return authority;
            
            }

        }
    
    
}
