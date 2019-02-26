/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsSuperpowerServiceLayer {
    
    public Superpower createSuperpower (Superpower superpower);
   
    public Superpower getSuperpowerById (int superpowerId) throws EntityNotFoundException;
   
    public List<Superpower> getAllSuperpowers();
   
    public void updateSuperpower(Superpower superpower);
   
    public void deleteSuperpower (int superpowerId);
    
    public List<Superpower> findSuperpowersForPerson(Person person);

}
