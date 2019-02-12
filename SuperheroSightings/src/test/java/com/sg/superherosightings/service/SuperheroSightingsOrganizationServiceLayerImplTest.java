/*
    Only tested the getById() method because that is the only one that throws an exception
 */
package com.sg.superherosightings.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsUserServiceLayer;

/**
 *
 * @author vishnukdawah
 */
public class SuperheroSightingsOrganizationServiceLayerImplTest {
    
     SuperheroSightingsPersonServiceLayer personServiceLayerTest;
     SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayerTest;
     SuperheroSightingsOrganizationServiceLayer organizationServiceLayerTest;
     SuperheroSightingsUserServiceLayer userServiceLayerTest;
    
    public SuperheroSightingsOrganizationServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        personServiceLayerTest = ctx.getBean("personServiceLayerTest", SuperheroSightingsPersonServiceLayer.class);
        superpowerServiceLayerTest = ctx.getBean("superpowerServiceLayerTest", SuperheroSightingsSuperpowerServiceLayer.class);
        organizationServiceLayerTest = ctx.getBean("organizationServiceLayerTest", SuperheroSightingsOrganizationServiceLayer.class);
        userServiceLayerTest = ctx.getBean("userServiceLayerTest", SuperheroSightingsUserServiceLayer.class);
        
        List<Person> personList = personServiceLayerTest.getAllPersons();
            for(Person p: personList){
                personServiceLayerTest.deletePerson(p.getPersonId());
            }
        
        List<Superpower> superpowerList = superpowerServiceLayerTest.getAllSuperpowers();
            for(Superpower s: superpowerList){
               superpowerServiceLayerTest.deleteSuperpower(s.getSuperpowerId());
            }
        
        List<Organization> organizationList = organizationServiceLayerTest.getAllOrganizations();
            for(Organization o: organizationList){
                organizationServiceLayerTest.deleteOrganization(o.getOrganizationId());
            }
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getOrganizationById method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testGetOrganizationById() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerServiceLayerTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personServiceLayerTest.createPerson(testPerson);
        
        List<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(testPerson);
        
        List<String> userAuthorities = new ArrayList<>();
        userAuthorities.add("ROLE_USER");
        
        
        User u = new User();
        u.setIsEnabled(true);
        u.setUserName("exampleUser");
        u.setUserPassword("examplePassword");
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setEmail("jdoe@gmail.com");
        u.setAuthorities(userAuthorities);
        
        
        userServiceLayerTest.createUser(u);
        
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(u);
        
        
        
        Organization o = new Organization();
        o.setOrganizationId(11);
        o.setOrganizationName("Test Organization");
        o.setIsItAHeroOrganization(true);
        o.setOrganizationDescription(" A description");
        o.setOrganizationCountry("USA");
        o.setOrganizationState("New York");
        o.setOrganizationCity("New York City");
        o.setOrganizationStreet("11th Street");
        o.setOrganizationZipcode("11111");
        o.setListOfPersons(listOfPersons);      
        o.setOrganizationAdmins(listOfUsers);
        
        
        organizationServiceLayerTest.createOrganization(o);
        
        organizationServiceLayerTest.deleteOrganization(o.getOrganizationId());
        
        try{
            Organization a = organizationServiceLayerTest.getOrganizationById(o.getOrganizationId());
            fail("Failed to encounter EntityNotFoundException");
        }catch(EntityNotFoundException ex){
            return;
        }
    }

    /**
     * Test of createOrganization method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testCreateOrganization() {
    }



    /**
     * Test of getAllOrganizations method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testGetAllOrganizations() {
    }

    /**
     * Test of updateOrganization method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testUpdateOrganization() {
    }

    /**
     * Test of deleteOrganization method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testDeleteOrganization() {
    }

    /**
     * Test of findOrganizationsForPerson method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testFindOrganizationsForPerson() {
    }
    
    
    
    /**
     * Test of findOrganizationsForUser method, of class SuperheroSightingsOrganizationServiceLayerImpl.
     */
    @Test
    public void testFindOrganizationsForUser() {
    }
    
}
