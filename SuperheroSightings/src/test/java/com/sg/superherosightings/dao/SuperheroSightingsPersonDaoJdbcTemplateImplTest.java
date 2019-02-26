/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah
 */
public class SuperheroSightingsPersonDaoJdbcTemplateImplTest {
    
    SuperheroSightingsPersonDao personDaoTest;
    SuperheroSightingsSuperpowerDao superpowerDaoTest;
    SuperheroSightingsLocationDao locationDaoTest;
    SuperheroSightingsSightingDao sightingDaoTest;

    public SuperheroSightingsPersonDaoJdbcTemplateImplTest() {
        
                
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
        personDaoTest = ctx.getBean("personDaoTest", SuperheroSightingsPersonDao.class);
        
        // need a reference to the Superpower DAO in the Person DAO test so you can add a superpower, then add it to the person, then add the person
        superpowerDaoTest = ctx.getBean("superpowerDaoTest", SuperheroSightingsSuperpowerDao.class );
        
        locationDaoTest = ctx.getBean("locationDaoTest", SuperheroSightingsLocationDao.class );
        sightingDaoTest = ctx.getBean("sightingDaoTest", SuperheroSightingsSightingDao.class );
        
        List<Person> testPersonList = personDaoTest.getAllPersons();
        
        List<Superpower> testSuperpowerList = superpowerDaoTest.getAllSuperpowers();
        
        List<Location> testLocationList = locationDaoTest.getAllLocations();

        List<Sighting> testSightingList = sightingDaoTest.getAllSightings();

        for(Person currentPerson : testPersonList){
            
            personDaoTest.deletePerson(currentPerson.getPersonId());
            
        }
        
        for(Superpower currentSuperpower : testSuperpowerList){
            
            superpowerDaoTest.deleteSuperpower(currentSuperpower.getSuperpowerId());
            
        }
        
        
        for(Location currentLocation : testLocationList){
            
            locationDaoTest.deleteLocation(currentLocation.getLocationId());
            
        }
                
                
                
        for(Sighting currentSighting : testSightingList){
            
            sightingDaoTest.deleteSighting(currentSighting.getSightingId());
            
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of createPerson method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testCreateGetPerson() throws Exception {
        
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
        
        Person fromDao = personDaoTest.getPersonById(testPerson.getPersonId());

        assertEquals(testPerson.getPersonId(), fromDao.getPersonId());
        
    }



    /**
     * Test of getAllPersons method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testGetAllPersons() throws Exception {
      
        // assert that there is nothing in the database yet when testing this method
        List<Person> personList = personDaoTest.getAllPersons();
        
        assertEquals(0,personList.size());
        
        Superpower one = new Superpower();
        one.setSuperpowerId(44);
        one.setSuperpowerName("Levitation");
        one.setSuperpowerDescription("Can levitate");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(one);

        Superpower two = new Superpower();
        two.setSuperpowerId(44);
        two.setSuperpowerName("Psychic");
        two.setSuperpowerDescription("Can move thimgs with their mind");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(two);

        List<Superpower> jeanGreysPowers = new ArrayList<>();
        jeanGreysPowers.add(one);
        jeanGreysPowers.add(two);

        
        Person p = new Person();
        p.setPersonId(33);
        p.setFirstName("Jean");
        p.setLastName("Grey");
        p.setIsHero(true);
        p.setDescriptionOfPerson("Member of the X-Men");
        p.setListOfSuperpowers(jeanGreysPowers);
        
        personDaoTest.createPerson(p);
        
        personList = personDaoTest.getAllPersons();
        
        assertEquals(1, personList.size());
        
    }

    /**
     * Test of updatePerson method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testUpdatePerson() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(23);
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
        
        // assert that the name is Viridi
        assertEquals("Viridi", testPerson.getFirstName());

        
        testPerson.setFirstName("UUUUU");
        testPerson.setLastName("VVVVV");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personDaoTest.updatePerson(testPerson);
        
        // assert that the name is now UUUUU

        assertEquals("UUUUU", testPerson.getFirstName());
        
    }

    /**
     * Test of deletePerson method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testDeletePerson() throws Exception {
        
        // assert that there is nothing in the database yet when testing this method
        List<Person> personList = personDaoTest.getAllPersons();
        
        assertEquals(0,personList.size());
        
        
        Superpower s = new Superpower();
        s.setSuperpowerId(23);
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

        
        // assert that there is now 1 person in the database
        personList = personDaoTest.getAllPersons();
        
        assertEquals(1,personList.size());
        
        
        // delete the 1 person in database and assert that there is now 0 persons in the database
        personDaoTest.deletePerson(testPerson.getPersonId());
        
        personList = personDaoTest.getAllPersons();
        
        assertEquals(0,personList.size());
        

    }
    
    
     /**
     * Test of testGetAllPersonsSightedAtLocation method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void testGetAllPersonsSightedAtLocation() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        personDaoTest.createPerson(p);
        
        
        Location loc = new Location();
        loc.setLocationId(11);
        loc.setLocationName("Test Location");
        loc.setLocationDescription("Test Desc.");
        loc.setLocationCountry("Country");
        loc.setLocationState("State");
        loc.setLocationCity("City");
        loc.setLocationStreet("Street");
        loc.setLocationZipcode("Zipcode");
        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
        loc.setLongitude(BigDecimal.ONE);
        
        locationDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
        
        List<Sighting> sightingList = new ArrayList<>();
        sightingList.add(testSighting);
        List<Person> personsAtLocation = personDaoTest.getAllPersonsSightedAtLocation(sightingList);
       
       assertNotNull(sightingList);
        
    }
    
    /**
     * Test of findPersonsForOrganization method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void findPersonsForOrganization() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        personDaoTest.createPerson(p);
        
        List<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(p);

        Organization o = new Organization();
        o.setListOfPersons(listOfPersons);
        o.setOrganizationName("Test Organization");
        o.setIsItAHeroOrganization(true);
        o.setOrganizationDescription(" A description");
        o.setOrganizationCountry("USA");
        o.setOrganizationState("New York");
        o.setOrganizationCity("New York City");
        o.setOrganizationStreet("11th Street");
        o.setOrganizationZipcode("11111");
        
        assertNotNull(personDaoTest.findPersonsForOrganization(o));
    
    }
    
    
    /**
     * Test of findPersonForSighting method, of class SuperheroSightingsPersonDaoJdbcTemplateImpl.
     */
    @Test
    public void findPersonForSighting() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        superpowerDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        personDaoTest.createPerson(p);
        
        
        Location loc = new Location();
        loc.setLocationId(11);
        loc.setLocationName("Test Location");
        loc.setLocationDescription("Test Desc.");
        loc.setLocationCountry("Country");
        loc.setLocationState("State");
        loc.setLocationCity("City");
        loc.setLocationStreet("Street");
        loc.setLocationZipcode("Zipcode");
        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
        loc.setLongitude(BigDecimal.ONE);
        
        locationDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
        
        Person personFromSighting = personDaoTest.findPersonForSighting(testSighting);
        
        assertNotNull(personFromSighting);
        
    }
    
    
}
