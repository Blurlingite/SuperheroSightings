///*
//    Only tested the getById() method because that is the only one that throws an exception
// */
//package com.sg.superherosightings.service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
//import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
//import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
//import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;
//import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;
//
///**
// *
// * @author vishnukdawah
// */
//public class SuperheroSightingsSightingServiceLayerImplTest {
//    
//    SuperheroSightingsSightingServiceLayer sightingServiceLayerTest;
//     SuperheroSightingsPersonServiceLayer personServiceLayerTest;
//     SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayerTest;
//     LocationServiceLayerImpl locationServiceLayerTest;
//     
//    public SuperheroSightingsSightingServiceLayerImplTest() {
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
//    public void setUp() {
//        
//                
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        sightingServiceLayerTest = ctx.getBean("sightingsServiceLayerTest", SuperheroSightingsSightingServiceLayer.class);
//        personServiceLayerTest = ctx.getBean("personServiceLayerTest", SuperheroSightingsPersonServiceLayer.class);
//        superpowerServiceLayerTest = ctx.getBean("superpowerServiceLayerTest", SuperheroSightingsSuperpowerServiceLayer.class);
//        locationServiceLayerTest = ctx.getBean("locationServiceLayerTest", LocationServiceLayerImpl.class);
//        
//        List<Sighting> sightingList = sightingServiceLayerTest.getAllSightings();
//            for(Sighting s: sightingList){
//                sightingServiceLayerTest.deleteSighting(s.getSightingId());
//            }
//        
//        List<Person> personList = personServiceLayerTest.getAllPersons();
//            for(Person p: personList){
//                personServiceLayerTest.deletePerson(p.getPersonId());
//            }
//        
//        List<Superpower> superpowerList = superpowerServiceLayerTest.getAllSuperpowers();
//            for(Superpower s: superpowerList){
//               superpowerServiceLayerTest.deleteSuperpower(s.getSuperpowerId());
//            }
//        
//        List<Location> locationList = locationServiceLayerTest.getAllLocations();
//            for(Location loc: locationList){
//                locationServiceLayerTest.deleteLocation(loc.getLocationId());
//            }
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createSighting method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testCreateSighting() {
//    }
//
//    /**
//     * Test of getSightingById method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testGetSightingById() throws Exception {
//        
//        Superpower s = new Superpower();
//        s.setSuperpowerId(22);
//        s.setSuperpowerName("Nature Power");
//        s.setSuperpowerDescription("Can control nature");
//        
//        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
//        superpowerServiceLayerTest.createSuperpower(s);
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
//        personServiceLayerTest.createPerson(p);
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
//        locationServiceLayerTest.createLocation(loc);
//        
//        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
////        LocalDateTime dt = LocalDateTime.parse("2016-10-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//        Sighting testSighting = new Sighting();
//        testSighting.setIsHeroSighting(true);
//        testSighting.setPerson(p);
//        testSighting.setLocation(loc);
//        testSighting.setJustTheSightingDate(dt);
////        testSighting.setSightingDate(dt);
//        
//
//        sightingServiceLayerTest.createSighting(testSighting);
//        sightingServiceLayerTest.deleteSighting(testSighting.getSightingId());
//       
//        try{
//            sightingServiceLayerTest.getSightingById(testSighting.getSightingId());
//            fail("Failed to encounter EntityNotFoundException");
//        }catch(EntityNotFoundException ex){
//            return;
//        }
//    }
//
//    /**
//     * Test of getAllSightings method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testGetAllSightings() {
//    }
//
//    /**
//     * Test of updateSighting method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testUpdateSighting() {
//    }
//
//    /**
//     * Test of deleteSighting method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testDeleteSighting() {
//    }
//
//    /**
//     * Test of getLatestTenSightings method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testGetLatestTenSightings() {
//    }
//
//    /**
//     * Test of getAllSightingsByLocation method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testGetAllSightingsByLocation() {
//    }
//
//    /**
//     * Test of getAllSightingsByDate method, of class SuperheroSightingsSightingServiceLayerImpl.
//     */
//    @Test
//    public void testGetAllSightingsByDate() {
//    }
//    
//}
