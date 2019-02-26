/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author zissah
 */
public interface SuperheroSightingsSuperpowerDao {
   
    public Superpower createSuperpower (Superpower superpower) throws SuperheroSightingsPersistenceException;
   
    public Superpower getSuperpowerById (int superpowerId) throws SuperheroSightingsPersistenceException;
   
    public List<Superpower> getAllSuperpowers() throws SuperheroSightingsPersistenceException;
   
    public void updateSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException;
   
    public void deleteSuperpower (int superpowerId) throws SuperheroSightingsPersistenceException;
    
    public List<Superpower> findSuperpowersForPerson(Person person) throws SuperheroSightingsPersistenceException;

}
