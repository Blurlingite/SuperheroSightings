/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsUserServiceLayer {
    
    public User createUser (User user) throws EntityAlreadyExistsException;
   
    public User getUserByUsername (String username) throws EntityNotFoundException;
   
    public List<User> getAllUsers();
   
    public void updateUser(User user);
   
    public void deleteUser (String username);
    
    public List<User> findUsersForOrganization (Organization organization);
    
    
}
