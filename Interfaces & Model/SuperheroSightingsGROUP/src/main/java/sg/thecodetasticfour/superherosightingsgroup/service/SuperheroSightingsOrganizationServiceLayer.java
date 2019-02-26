/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

import java.util.List;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author vishnukdawah
 */
public interface SuperheroSightingsOrganizationServiceLayer {
    
    public Organization createOrganization(Organization organization);

    public Organization getOrganizationById(int organizationId) throws EntityNotFoundException;

    public List<Organization> getAllOrganizations();

    public void updateOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public List<Organization> findOrganizationsForPerson(Person person);
    
    public List<Organization> findOrganizationsForUser(User user);


    
}
