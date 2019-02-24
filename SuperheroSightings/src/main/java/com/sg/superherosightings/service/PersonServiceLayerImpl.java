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
import javax.inject.Inject;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;

/**
 *
 * @author vishnukdawah  
 */
public class PersonServiceLayerImpl implements SuperheroSightingsPersonServiceLayer {

    SuperheroSightingsPersonDao personDao;
    SuperheroSightingsSuperpowerDao superpowerDao;
    SuperheroSightingsOrganizationDao organizationDao;
    
    List<Person> personsFromSightingsByLocation = new ArrayList<>();
    
    List<Person> globalPersonList = new ArrayList<>();

    @Inject
     public PersonServiceLayerImpl(SuperheroSightingsPersonDao personDao, SuperheroSightingsSuperpowerDao superpowerDao, SuperheroSightingsOrganizationDao organizationDao) {
        this.personDao = personDao;
        this.superpowerDao = superpowerDao;
        this.organizationDao = organizationDao;
    }


    
    @Override
    public Person createPerson(Person person) {
        
        Person p = new Person();
        
        try {
            
            p = personDao.createPerson(person);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return p;
        
    }



    @Override
    public Person getPersonById(int personId) throws EntityNotFoundException {
        
        Person personRetrieved = new Person();
        
        try {
            
            personRetrieved = personDao.getPersonById(personId);
            
            if(personRetrieved == null){
                
                throw new EntityNotFoundException("Could not find Person");
                
            }
           // Let's get it that list of superpowers by going into the bridge table and pulling out each superpower where 
            //the person's ID is the one passed in
            // Then use the setter method in the Person DTO to assign the list of superpowers (the List<Superpower> variable in the Person DTO)
            //to that Person DTO
            personRetrieved.setListOfSuperpowers(superpowerDao.findSuperpowersForPerson(personRetrieved));
            personRetrieved.setListOfOrganizations(organizationDao.findOrganizationsForPerson(personRetrieved));
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        

        return personRetrieved;
        
    }

    @Override
    public List<Person> getAllPersons() {
        
        List<Person> allPersons = new ArrayList<>();
        
        try {
            
            allPersons = personDao.getAllPersons();
            
        // use associateSuperpowersWithPersons() to first find each person's superpowers using findSuperpowersForPerson() in an enhanced for loop
        // Then assign those superpowers to each person they belong to, so the Person DTO will not have a null List<Superpower> field here on the java side
            associateSuperpowersAndOrganizationsWithPersons(allPersons);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return allPersons;
        
    }

    @Override
    public void updatePerson(Person person) {
        
        try {
            
            personDao.updatePerson(person);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void deletePerson(int personId) {
        
        try {
            
            personDao.deletePerson(personId);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    
    
    private List<Person> associateSuperpowersAndOrganizationsWithPersons(List<Person> personList){
        
        for (Person currentPerson : personList) {
            
            try {
                // find all the superpowers assigned to that person with the helper method findSuperpowersForPerson()
                // and then set those superpowers to the person using the setter method in the Person DTO
                List<Superpower> listOfSuperpowers = superpowerDao.findSuperpowersForPerson(currentPerson);
                currentPerson.setListOfSuperpowers(listOfSuperpowers);
                
                List<Organization> listOfOrganizations = organizationDao.findOrganizationsForPerson(currentPerson);
                currentPerson.setListOfOrganizations(listOfOrganizations);
                
            } catch (SuperheroSightingsPersistenceException ex) {
                
                Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
        }
        
        return personList;
        
    }

    @Override
    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList) {
        
        try {
            
            return personDao.getAllPersonsSightedAtLocation(sightingList);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public void setPersonsFromSightingsByLocation(List<Person> personList) {
       
        this.personsFromSightingsByLocation = personList;
        
    }

    @Override
    public List<Person> getPersonsFromSightingsByLocation() {
       
        return personsFromSightingsByLocation;
        
    }

    @Override
    public Person findPersonForSighting(Sighting sighting) {
         
        try {
            
            Person p = personDao.findPersonForSighting(sighting);
            p.setListOfSuperpowers(superpowerDao.findSuperpowersForPerson(p));
            
            return p;
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;

        }
        
    }

    @Override
    public void setGlobalPersonList(List<Person> personList) {
       
        this.globalPersonList = personList;
        
    }

    @Override
    public List<Person> getGlobalPersonList() {
       
        return globalPersonList;
        
    }

    @Override
    public List<Person> findPersonsForOrganization(Organization organization) {
        
        try {
            
            return personDao.findPersonsForOrganization(organization);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }

    @Override
    public List<Person> findPersonsForSuperpower(Superpower superpower) {
        
        try {
            
            return personDao.findPersonsForSuperpower(superpower);
            
        } catch (SuperheroSightingsPersistenceException ex) {
            
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }
    
    
}
