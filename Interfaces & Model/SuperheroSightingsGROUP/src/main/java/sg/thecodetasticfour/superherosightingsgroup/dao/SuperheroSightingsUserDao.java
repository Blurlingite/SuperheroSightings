/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;


import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author Blake
 */
public interface SuperheroSightingsUserDao {
     public User createUser (User user) throws SuperheroSightingsPersistenceException;
   
    public User getUserByUsername (String username) throws SuperheroSightingsPersistenceException;
   
    public List<User> getAllUsers() throws SuperheroSightingsPersistenceException;
   
    public void updateUser(User user) throws SuperheroSightingsPersistenceException;
   
    public void deleteUser (String username) throws SuperheroSightingsPersistenceException;
    
    public List<User> findUsersForOrganization (Organization organization) throws SuperheroSightingsPersistenceException;
}
