/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsPersonServiceLayer {
    
    public Person createPerson(Person person);
    
    public Person getPersonById(int personId) throws EntityNotFoundException;
    
    public List<Person> getAllPersons();
    
    public void updatePerson(Person person);
    
    public void deletePerson(int personId);
    
    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList);

    public void setPersonsFromSightingsByLocation(List<Person> personList);
    
    public List<Person> getPersonsFromSightingsByLocation();
    
    public Person findPersonForSighting(Sighting sighting);

    public void setGlobalPersonList(List<Person> personList);
    
    public List<Person> getGlobalPersonList();
    
    public List<Person> findPersonsForOrganization(Organization organization);

    public List<Person> findPersonsForSuperpower(Superpower superpower);
    


}
