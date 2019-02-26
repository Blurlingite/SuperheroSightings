/*
    Only tested the getById() method because that is the only one that throws an exception
 */
package com.sg.superherosightings.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;

/**
 *
 * @author vishnukdawah
 */
public class SuperheroSightingsLocationServiceLayerImplTest {
    
     LocationServiceLayerImpl locationServiceLayerTest;

    public SuperheroSightingsLocationServiceLayerImplTest() {
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
        locationServiceLayerTest = ctx.getBean("locationServiceLayerTest", LocationServiceLayerImpl.class);
        
        List<Location> locationList = locationServiceLayerTest.getAllLocations();

            for(Location currentLocation : locationList){
                locationServiceLayerTest.deleteLocation(currentLocation.getLocationId());
            }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createLocation method, of class LocationServiceLayerImpl.
     */
    @Test
    public void testCreateLocation() {
    }

    /**
     * Test of getLocationById method, of class LocationServiceLayerImpl.
     */
    @Test
    public void testGetLocationById() throws Exception {
        
         Location loc = new Location();
        loc.setLocationName("Test Location");
        loc.setLocationDescription("Test Desc.");
        loc.setLocationCountry("Country");
        loc.setLocationState("State");
        loc.setLocationCity("City");
        loc.setLocationStreet("Street");
        loc.setLocationZipcode("Zipcode");
        loc.setLatitude(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP));
        loc.setLongitude(BigDecimal.ONE);
        
        locationServiceLayerTest.createLocation(loc);
        
        locationServiceLayerTest.deleteLocation(loc.getLocationId());
        
        try{
         locationServiceLayerTest.getLocationById(loc.getLocationId());
            fail("Failed to encounter EntityNotFoundException");
        }catch(EntityNotFoundException ex){
            return;
        }
     
    }

    /**
     * Test of getAllLocations method, of class LocationServiceLayerImpl.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of updateLocation method, of class LocationServiceLayerImpl.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of deleteLocation method, of class LocationServiceLayerImpl.
     */
    @Test
    public void testDeleteLocation() {
    }

    
}
