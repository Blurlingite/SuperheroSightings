/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.dao;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author Blake
 */
public interface SuperheroSightingsOrganizationDao {

    public Organization createOrganization(Organization organization) throws SuperheroSightingsPersistenceException;

    public Organization getOrganizationById(int organizationId) throws SuperheroSightingsPersistenceException;

    public List<Organization> getAllOrganizations() throws SuperheroSightingsPersistenceException;

    public void updateOrganization(Organization organization) throws SuperheroSightingsPersistenceException;

    public void deleteOrganization(int organizationId) throws SuperheroSightingsPersistenceException;

    public List<Organization> findOrganizationsForPerson(Person person) throws SuperheroSightingsPersistenceException;
    
    public List<Organization> findOrganizationsForUser(User user) throws SuperheroSightingsPersistenceException;


}
