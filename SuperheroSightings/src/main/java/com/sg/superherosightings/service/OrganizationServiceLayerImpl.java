/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;

/**
 *
 * @author vishnukdawah  
 */
public class OrganizationServiceLayerImpl implements SuperheroSightingsOrganizationServiceLayer {

    SuperheroSightingsOrganizationDao organizationDao;
    SuperheroSightingsPersonDao personDao;
    SuperheroSightingsUserDao userDao;

    public OrganizationServiceLayerImpl(SuperheroSightingsOrganizationDao organizationDao, SuperheroSightingsPersonDao personDao, SuperheroSightingsUserDao userDao) {
        this.organizationDao = organizationDao;
        this.personDao = personDao;
        this.userDao = userDao;
    }


    
    @Override
    public Organization createOrganization(Organization Organization){
       
        try {
            
            return organizationDao.createOrganization(Organization);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
        
        
    }

    @Override
    public Organization getOrganizationById(int organizationId) throws EntityNotFoundException{
        
        Organization organization = new Organization();
        
        try {
            
            organization = organizationDao.getOrganizationById(organizationId);
            
            if(organization == null){
                
                throw new EntityNotFoundException("Could not find that Organization");
                
            }

            List<Person> allPersonsForOrganization = personDao.findPersonsForOrganization(organization);
            organization.setListOfPersons(allPersonsForOrganization);
            
            List<User> usersOfOrganization = userDao.findUsersForOrganization(organization);
            organization.setOrganizationAdmins(usersOfOrganization);
            
            return organization;
                             
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        

    }

    @Override
    public List<Organization> getAllOrganizations(){
        
        try {
            
            return associatePersonsWithOrganizations(organizationDao.getAllOrganizations());
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;

        }
        
    }

    @Override
    public void updateOrganization(Organization Organization)   {

        try {
            
            organizationDao.updateOrganization(Organization);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }

    @Override
    public void deleteOrganization(int organizationId) {
        
        try {
            
            organizationDao.deleteOrganization(organizationId);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public List<Organization> findOrganizationsForPerson(Person person) {
       
        try {
            
            return organizationDao.findOrganizationsForPerson(person);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;

        }
        
    }

    private List<Organization> associatePersonsWithOrganizations(List<Organization> organizationList) throws SuperheroSightingsPersistenceException {
       
        for (Organization currentOrganization : organizationList) {
           
            currentOrganization.setListOfPersons(personDao.findPersonsForOrganization(currentOrganization));
            
        }
       
        return organizationList;
        
    }

    @Override
    public List<Organization> findOrganizationsForUser(User user) {
        
        try {
            
            return organizationDao.findOrganizationsForUser(user);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }

    }

    
}
