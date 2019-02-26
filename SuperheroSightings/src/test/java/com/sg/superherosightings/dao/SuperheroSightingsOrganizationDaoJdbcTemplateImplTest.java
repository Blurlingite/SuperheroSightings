/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author vishnukdawah
 */
public class SuperheroSightingsOrganizationDaoJdbcTemplateImplTest {
    
    SuperheroSightingsOrganizationDao organizationDaoTest;
    SuperheroSightingsSuperpowerDao superpowerDaoTest;
    SuperheroSightingsPersonDao personDaoTest;
    SuperheroSightingsUserDao userDaoTest;

    public SuperheroSightingsOrganizationDaoJdbcTemplateImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SuperheroSightingsPersistenceException {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        organizationDaoTest = ctx.getBean("organizationDaoTest", SuperheroSightingsOrganizationDao.class);
        superpowerDaoTest = ctx.getBean("superpowerDaoTest", SuperheroSightingsSuperpowerDao.class);
        personDaoTest = ctx.getBean("personDaoTest", SuperheroSightingsPersonDao.class);
        userDaoTest = ctx.getBean("userDaoTest", SuperheroSightingsUserDao.class);
        
        List<Organization> testOrganizationList = organizationDaoTest.getAllOrganizations();
        List<Superpower> testSuperpowerList = superpowerDaoTest.getAllSuperpowers(); 
        List<Person> testPersonList = personDaoTest.getAllPersons();
        List<User> testListOfUsers = userDaoTest.getAllUsers();

        for(Organization currentOrganization : testOrganizationList){
            
            organizationDaoTest.deleteOrganization(currentOrganization.getOrganizationId());
            
        }
        
        for(Superpower currentSuperpower : testSuperpowerList){
            
            superpowerDaoTest.deleteSuperpower(currentSuperpower.getSuperpowerId());
            
        }
        
        for(Person currentPerson : testPersonList){
            
            personDaoTest.deletePerson(currentPerson.getPersonId());
            
        }

        
        for(User u : testListOfUsers){
            
            userDaoTest.deleteUser(u.getUserName());
            
        }
        
        
        

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of createOrganization and getOrganizationById methods, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     */
    @Test
    public void testCreateGetOrganization() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        organizationDaoTest.createOrganization(o);
        
        List<Organization> listOfOrganizations = organizationDaoTest.getAllOrganizations();
        assertEquals(1, listOfOrganizations.size());
        
        Organization fromDao = organizationDaoTest.getOrganizationById(o.getOrganizationId());
        List<Person> personsFromOrganization = personDaoTest.findPersonsForOrganization(fromDao);
        
        for(Person p : personsFromOrganization){
            
            p.setListOfSuperpowers(superpowerDaoTest.findSuperpowersForPerson(p));
            
        }
        
        fromDao.setListOfPersons(personsFromOrganization);
        fromDao.setOrganizationAdmins(listOfUsers);
        
        assertEquals(o, fromDao);


    }


    /**
     * Test of getAllOrganizations method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     */
    @Test
    public void testGetAllOrganizations() throws Exception {
        
        List<Organization> organizationList = organizationDaoTest.getAllOrganizations();
        assertEquals(0, organizationList.size());
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        organizationDaoTest.createOrganization(o);
        
        organizationList = organizationDaoTest.getAllOrganizations();
        assertEquals(1, organizationList.size());

    }

    /**
     * Test of updateOrganization method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     */
    @Test
    public void testUpdateOrganization() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        
        organizationDaoTest.createOrganization(o);
        
        List<Organization> listOfOrganizations = organizationDaoTest.getAllOrganizations();
        assertEquals(1, listOfOrganizations.size());
        
        // change the name of the organization and then assert that the change was made
        o.setOrganizationName("Forces of Nature");
        
        organizationDaoTest.updateOrganization(o);
        
        Organization fromDao = organizationDaoTest.getOrganizationById(o.getOrganizationId());
        
        fromDao.setOrganizationAdmins(listOfUsers);
        fromDao.setListOfPersons(listOfPersons);
        
        // assert that it is the same organization
        
        assertEquals(o, fromDao);

       
        // assert that the name of the organization is no longer "Test Organization"
        assertNotEquals("Test Organization", fromDao.getOrganizationDescription());
        
    }

    /**
     * Test of deleteOrganization method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     */
    @Test
    public void testDeleteOrganization() throws Exception {      
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        
        organizationDaoTest.createOrganization(o);
        
        List<Organization> organizationList = organizationDaoTest.getAllOrganizations();
        assertEquals(1, organizationList.size());
        
        organizationDaoTest.deleteOrganization(o.getOrganizationId());
        
        organizationList = organizationDaoTest.getAllOrganizations();
        assertEquals(0, organizationList.size());

    }

    /**
     * Test of getOrganizationsForPerson method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOrganizationsForPerson() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        
        organizationDaoTest.createOrganization(o);
        
        List<Organization> listOfOrganizations = new ArrayList<>();
        
        Person testPersonTwo = new Person();
        testPersonTwo.setPersonId(5);
        testPersonTwo.setFirstName("Viridi");
        testPersonTwo.setLastName("GON");
        testPersonTwo.setIsHero(false);
        testPersonTwo.setDescriptionOfPerson("Goddess of Nature");
        testPersonTwo.setListOfSuperpowers(slist);
        testPersonTwo.setListOfOrganizations(listOfOrganizations);
        
        personDaoTest.createPerson(testPersonTwo);
        
        
        List<Organization> organizationsFromDao = organizationDaoTest.findOrganizationsForPerson(testPersonTwo);
        
        assertNotNull(organizationsFromDao);
    
    }
    
    
    
    /**
     * Test of findOrganizationsForUser method, of class SuperheroSightingsOrganizationDaoJdbcTemplateImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOrganizationsForUser() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setPersonId(5);
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.createPerson(testPerson);
        
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
        
        userDaoTest.createUser(u);
        
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
        
        organizationDaoTest.createOrganization(o);
        
        
        List<Organization> organizationsToAddToUser = new ArrayList<>();
        
        
        User uTwo = new User();
        u.setIsEnabled(true);
        u.setUserName("exampleUser");
        u.setUserPassword("examplePassword");
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setEmail("jdoe@gmail.com");
        u.setAuthorities(userAuthorities);
        u.setUserOrganizations(organizationsToAddToUser);
        
        userDaoTest.createUser(u);
        
        
        List<Organization> organizationListTest = organizationDaoTest.findOrganizationsForUser(u);
        
    }
    
      
}
