/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsPersonDao {
    
    public Person createPerson(Person person) throws SuperheroSightingsPersistenceException;
    
    public Person getPersonById(int personId) throws SuperheroSightingsPersistenceException;
    
    public List<Person> getAllPersons() throws SuperheroSightingsPersistenceException;
    
    public void updatePerson(Person person) throws SuperheroSightingsPersistenceException;
    
    public void deletePerson(int personId) throws SuperheroSightingsPersistenceException;

    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList) throws SuperheroSightingsPersistenceException;

    public Person findPersonForSighting(Sighting sighting) throws SuperheroSightingsPersistenceException;

    public List<Person> findPersonsForOrganization(Organization organization) throws SuperheroSightingsPersistenceException;
    
    public List<Person> findPersonsForSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException;



}
