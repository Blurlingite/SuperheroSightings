///*
//NOTE: Because list variables on DTOs only get assigned in the service layer, I cannot accurately test them in the
//Dao tests because the assert will fail if the lists are not equal, and they cannot be null.
//
//Will move all Dao tests to the Service Layer tests
//
// */
//package com.sg.superherosightings.dao;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
//
///**
// *
// * @author vishnukdawah
// */
//public class SuperheroSightingsLocationDaoJdbcTemplateImplTest {
//    
//    SuperheroSightingsLocationDao locationDaoTest;
//    SuperheroSightingsSightingDao sightingDaoTest;
//    SuperheroSightingsPersonDao personDaoTest;
//    SuperheroSightingsSuperpowerDao superpowerDaoTest;
//    
//    public SuperheroSightingsLocationDaoJdbcTemplateImplTest() {
//        
//
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() throws SuperheroSightingsPersistenceException {
//        
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        locationDaoTest = ctx.getBean("locationDaoTest", SuperheroSightingsLocationDao.class);
//        sightingDaoTest = ctx.getBean("sightingDaoTest",SuperheroSightingsSightingDao.class);
//        personDaoTest = ctx.getBean("personDaoTest",SuperheroSightingsPersonDao.class);
//        superpowerDaoTest = ctx.getBean("superpowerDaoTest",SuperheroSightingsSuperpowerDao.class);
//        
//        
//        List<Location> testLocationList = locationDaoTest.getAllLocations();
//        List<Sighting> testSightingList = sightingDaoTest.getAllSightings();
//        List<Person> testPersonList = personDaoTest.getAllPersons();
//        List<Superpower> testSuperpowerList = superpowerDaoTest.getAllSuperpowers();
//
//
//
//        for(Location currentLocation : testLocationList){
//            
//            locationDaoTest.deleteLocation(currentLocation.getLocationId());
//            
//        }
//        
//        for(Sighting currentSighting : testSightingList){
//            
//            sightingDaoTest.deleteSighting(currentSighting.getSightingId());
//            
//        }
//        
//        for(Person currentPerson : testPersonList){
//            
//            personDaoTest.deletePerson(currentPerson.getPersonId());
//            
//        }
//        
//        for(Superpower currentSuperpower : testSuperpowerList){
//            
//            superpowerDaoTest.deleteSuperpower(currentSuperpower.getSuperpowerId());
//            
//        }
//        
//        
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createLocation and getLocationById methods, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testCreateGetLocation() throws Exception {
//        
//        //For some reason the last assert was not working because loc was not equal to fromDao, so I used their IDs instead in the assertEquals
//        
//        // need to instantiate a Big Decimal object otherwise the test will round the big decimal and the assert will fail
//        BigDecimal expected = new BigDecimal("1321.5073");
//        
//        Location loc = new Location();
//        loc.setLocationId(11);
//        loc.setLocationName("Test Location");
//        loc.setLocationDescription("Test Desc.");
//        loc.setLocationCountry("Country");
//        loc.setLocationState("State");
//        loc.setLocationCity("City");
//        loc.setLocationStreet("Street");
//        loc.setLocationZipcode("Zipcode");
//        loc.setLatitude(expected);
//        loc.setLongitude(expected);
//        
//        locationDaoTest.createLocation(loc);
//        
//        Location fromDao = locationDaoTest.getLocationById(loc.getLocationId());
//
//        
//        assertEquals(loc, fromDao);
//
//        assertEquals(loc.getLocationId(), fromDao.getLocationId());
//        
//    }
//
//
//    /**
//     * Test of getAllLocations method, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testGetAllLocations() throws Exception {
//        
//        List<Location> locationList = locationDaoTest.getAllLocations();
//        assertEquals(0, locationList.size());
//        
//        Location loc = new Location();
//        loc.setLocationId(11);
//        loc.setLocationName("Test Location");
//        loc.setLocationDescription("Test Desc.");
//        loc.setLocationCountry("Country");
//        loc.setLocationState("State");
//        loc.setLocationCity("City");
//        loc.setLocationStreet("Street");
//        loc.setLocationZipcode("Zipcode");
//        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
//        loc.setLongitude(BigDecimal.ONE);
//        
//        locationDaoTest.createLocation(loc);
//        
//        locationList = locationDaoTest.getAllLocations();
//        
//        assertEquals(1, locationList.size());
//
//    }
//
//    /**
//     * Test of updateLocation method, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testUpdateLocation() throws Exception {
//        
//        Location loc = new Location();
//        loc.setLocationId(11);
//        loc.setLocationName("Test Location");
//        loc.setLocationDescription("Test Desc.");
//        loc.setLocationCountry("Country");
//        loc.setLocationState("State");
//        loc.setLocationCity("City");
//        loc.setLocationStreet("Street");
//        loc.setLocationZipcode("Zipcode");
//        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
//        loc.setLongitude(BigDecimal.ONE);
//        
//        locationDaoTest.createLocation(loc);
//        
//        loc.setLocationName("A New Place");
//        locationDaoTest.updateLocation(loc);
//        
//        Location updatedLocation = locationDaoTest.getLocationById(loc.getLocationId());
//        
//        assertNotEquals("Test Location", updatedLocation.getLocationName());
//        
//    }
//
//    /**
//     * Test of deleteLocation method, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testDeleteLocation() throws Exception {
//       
//        List<Location> locationList = locationDaoTest.getAllLocations();
//        assertEquals(0, locationList.size());
//        
//        Location loc = new Location();
//        loc.setLocationId(11);
//        loc.setLocationName("Test Location");
//        loc.setLocationDescription("Test Desc.");
//        loc.setLocationCountry("Country");
//        loc.setLocationState("State");
//        loc.setLocationCity("City");
//        loc.setLocationStreet("Street");
//        loc.setLocationZipcode("Zipcode");
//        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
//        loc.setLongitude(BigDecimal.ONE);
//        
//        locationDaoTest.createLocation(loc);
//        
//        locationList = locationDaoTest.getAllLocations();
//        assertEquals(1, locationList.size());
//        
//        locationDaoTest.deleteLocation(loc.getLocationId());
//        
//        locationList = locationDaoTest.getAllLocations();
//        assertEquals(0, locationList.size());
//        
//
//    }
//    
//
//    /**
//     * Test of testFindLocationForSighting method, of class SuperheroSightingsLocationDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testFindLocationForSighting() throws Exception {
//        
//        
//        
//        Superpower s = new Superpower();
//        s.setSuperpowerId(22);
//        s.setSuperpowerName("Nature Power");
//        s.setSuperpowerDescription("Can control nature");
//        
//        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
//        superpowerDaoTest.createSuperpower(s);
//        
//        List<Superpower> superpowerlist = new ArrayList<>();
//        superpowerlist.add(s);
//        
//        
//        Person p = new Person();
//        p.setFirstName("George");
//        p.setLastName("Shelley");
//        p.setIsHero(Boolean.TRUE);
//        p.setDescriptionOfPerson("Fake description");
//        p.setListOfSuperpowers(superpowerlist);
//        
//        personDaoTest.createPerson(p);
//        
//        
//        Location loc = new Location();
//        loc.setLocationId(11);
//        loc.setLocationName("Test Location");
//        loc.setLocationDescription("Test Desc.");
//        loc.setLocationCountry("Country");
//        loc.setLocationState("State");
//        loc.setLocationCity("City");
//        loc.setLocationStreet("Street");
//        loc.setLocationZipcode("Zipcode");
//        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
//        loc.setLongitude(BigDecimal.ONE);
//        
//        locationDaoTest.createLocation(loc);
//        
//        
//        LocalDate date= LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        
//        Sighting testSighting = new Sighting();
//        testSighting.setIsHeroSighting(true);
//        testSighting.setPerson(p);
//        testSighting.setLocation(loc);
//        testSighting.setJustTheSightingDate(date);
//        
//        sightingDaoTest.createSighting(testSighting);
//       
//        Location fromDao = locationDaoTest.findLocationForSighting(testSighting);
//        
//        assertEquals(loc.getLocationId(), fromDao.getLocationId());
// 
//    }
//       
//}
