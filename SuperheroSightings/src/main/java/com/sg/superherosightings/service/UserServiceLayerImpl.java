/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsUserServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityAlreadyExistsException;

/**
 *
 * @author vishnukdawah
 */
public class UserServiceLayerImpl implements SuperheroSightingsUserServiceLayer {

    SuperheroSightingsUserDao userDao;
    SuperheroSightingsOrganizationDao organizationDao;

    public UserServiceLayerImpl(SuperheroSightingsUserDao userDao, SuperheroSightingsOrganizationDao organizationDao) {
        this.userDao = userDao;
        this.organizationDao = organizationDao;
    }


    
    
    
    
    @Override
    public User createUser(User user) throws EntityAlreadyExistsException {

        try {
            
            // if the username already exists in the database throw an exception
            if(userDao.getUserByUsername(user.getUserName()) != null){
                throw new EntityAlreadyExistsException(    
                "That Username already exists!");
           
            }
            
            
             return userDao.createUser(user);
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        
        User u = new User();
        try {
            u = userDao.getUserByUsername(username);
             
            List<Organization> organizationsForUser = organizationDao.findOrganizationsForUser(u);
            u.setUserOrganizations(organizationsForUser);
            
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(u == null){
            throw new EntityNotFoundException("Could not find that User");
        }
        return u;    
    }

    @Override
    public List<User> getAllUsers() {
        
        List<User> listOfUsers = new ArrayList<>();
        
        try {
            listOfUsers = userDao.getAllUsers();
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listOfUsers;

    }

    @Override
    public void updateUser(User user) {
        
        try {
            userDao.updateUser(user);
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(String username) {
        
        try {
            userDao.deleteUser(username);
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> findUsersForOrganization(Organization organization) {
        
        try {
            return userDao.findUsersForOrganization(organization);
        } catch (SuperheroSightingsPersistenceException ex) {
//            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
        
    }

    @Override
    public void setGlobalUserList(List<User> userList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getGlobalUserList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
