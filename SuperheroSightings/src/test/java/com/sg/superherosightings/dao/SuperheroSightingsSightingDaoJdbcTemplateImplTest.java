/*
        ERROR WITH CREATEGET() HAD TO USE ID IN ASSERTEQUALS
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author vishnukdawah
 */
public class SuperheroSightingsSightingDaoJdbcTemplateImplTest {
    
    SuperheroSightingsSightingDao sightingDaoTest;
    SuperheroSightingsSuperpowerDao spDaoTest;
    SuperheroSightingsPersonDao pDaoTest;
    SuperheroSightingsLocationDao locDaoTest;

    
    public SuperheroSightingsSightingDaoJdbcTemplateImplTest() {
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
        sightingDaoTest = ctx.getBean("sightingDaoTest", SuperheroSightingsSightingDao.class);
        spDaoTest = ctx.getBean("superpowerDaoTest", SuperheroSightingsSuperpowerDao.class);
        pDaoTest = ctx.getBean("personDaoTest", SuperheroSightingsPersonDao.class);
        locDaoTest = ctx.getBean("locationDaoTest", SuperheroSightingsLocationDao.class);
        
        
       List<Sighting> testListOfSightings = sightingDaoTest.getAllSightings();
        
        for(Sighting currentSighting : testListOfSightings){
            
            sightingDaoTest.deleteSighting(currentSighting.getSightingId());
            
        }

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of createSighting method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void testCreateGetSighting() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
       
        Sighting fromDao = sightingDaoTest.getSightingById(testSighting.getSightingId());
        
        fromDao.setPerson(p);

        assertEquals(testSighting.getSightingId(), fromDao.getSightingId());
        
    }


    /**
     * Test of getAllSightings method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void testGetAllSightings() throws Exception {
        
       List<Sighting> listOfSightings = sightingDaoTest.getAllSightings();
        
        assertEquals(0, listOfSightings.size());
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);

        listOfSightings = sightingDaoTest.getAllSightings();

        assertEquals(1, listOfSightings.size());
        
    }

    /**
     * Test of updateSighting method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void testUpdateSighting() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
        
        
        
        List<Sighting> listOfSightings = sightingDaoTest.getAllSightings();
        assertEquals(1, listOfSightings.size());
        
        testSighting.setIsHeroSighting(false);
        
        sightingDaoTest.updateSighting(testSighting);
        
        listOfSightings = sightingDaoTest.getAllSightings();
        assertEquals(1, listOfSightings.size());
        
        // assert that the name of the superpower changed
        assertEquals(false, testSighting.getIsHeroSighting());
        
    }

    /**
     * Test of deleteSighting method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void testDeleteSighting() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
  
        List<Sighting> listOfSightings = sightingDaoTest.getAllSightings();
        
        assertEquals(1, listOfSightings.size());
        
        sightingDaoTest.deleteSighting(testSighting.getSightingId());
        
        listOfSightings = sightingDaoTest.getAllSightings();
        
        assertEquals(0, listOfSightings.size());
        
    }
    
    
    
        /**
     * Test of getLatestTenSightings method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void getLatestTenSightings() throws Exception {
        
       Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Easter egg for Randall and Amanda
        Sighting testSightingUno = new Sighting();
        testSightingUno.setIsHeroSighting(true);
        testSightingUno.setPerson(p);
        testSightingUno.setLocation(loc);
        testSightingUno.setJustTheSightingDate(dt);
        
        
        
        Sighting testSightingDos = new Sighting();
        testSightingDos.setIsHeroSighting(true);
        testSightingDos.setPerson(p);
        testSightingDos.setLocation(loc);
        testSightingDos.setJustTheSightingDate(dt);
        
        Sighting testSightingTres = new Sighting();
        testSightingTres.setIsHeroSighting(true);
        testSightingTres.setPerson(p);
        testSightingTres.setLocation(loc);
        testSightingTres.setJustTheSightingDate(dt);
        
        
        Sighting testSightingCuatro = new Sighting();
        testSightingCuatro.setIsHeroSighting(true);
        testSightingCuatro.setPerson(p);
        testSightingCuatro.setLocation(loc);
        testSightingCuatro.setJustTheSightingDate(dt);
        
        
        Sighting testSightingCinco = new Sighting();
        testSightingCinco.setIsHeroSighting(true);
        testSightingCinco.setPerson(p);
        testSightingCinco.setLocation(loc);
        testSightingCinco.setJustTheSightingDate(dt);
        
        Sighting testSightingSeis = new Sighting();
        testSightingSeis.setIsHeroSighting(true);
        testSightingSeis.setPerson(p);
        testSightingSeis.setLocation(loc);
        testSightingSeis.setJustTheSightingDate(dt);
        
        Sighting testSightingSiete = new Sighting();
        testSightingSiete.setIsHeroSighting(true);
        testSightingSiete.setPerson(p);
        testSightingSiete.setLocation(loc);
        testSightingSiete.setJustTheSightingDate(dt);
        
        Sighting testSightingOcho = new Sighting();
        testSightingOcho.setIsHeroSighting(true);
        testSightingOcho.setPerson(p);
        testSightingOcho.setLocation(loc);
        testSightingOcho.setJustTheSightingDate(dt);
        
        Sighting testSightingNueve = new Sighting();
        testSightingNueve.setIsHeroSighting(true);
        testSightingNueve.setPerson(p);
        testSightingNueve.setLocation(loc);
        testSightingNueve.setJustTheSightingDate(dt);
        
        Sighting testSightingDiez = new Sighting();
        testSightingDiez.setIsHeroSighting(true);
        testSightingDiez.setPerson(p);
        testSightingDiez.setLocation(loc);
        testSightingDiez.setJustTheSightingDate(dt);
        
        testSightingUno = sightingDaoTest.createSighting(testSightingUno);
        testSightingDos = sightingDaoTest.createSighting(testSightingDos);
        testSightingTres = sightingDaoTest.createSighting(testSightingTres);
        testSightingCuatro = sightingDaoTest.createSighting(testSightingCuatro);
        testSightingCinco = sightingDaoTest.createSighting(testSightingCinco);
        testSightingSeis = sightingDaoTest.createSighting(testSightingSeis);
        testSightingSiete = sightingDaoTest.createSighting(testSightingSiete);
        testSightingOcho = sightingDaoTest.createSighting(testSightingOcho);
        testSightingNueve = sightingDaoTest.createSighting(testSightingNueve);
        testSightingDiez = sightingDaoTest.createSighting(testSightingDiez);

        
        List<Sighting> Ten = new ArrayList<>();
        
        Ten = sightingDaoTest.getLatestTenSightings();
        assertEquals(10, Ten.size());
        
    }
    
    
        /**
     * Test of getAllSightingsByLocation method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void getAllSightingsByLocation() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
        
        
        List<Sighting> sightingsByLocation = sightingDaoTest.getAllSightingsByLocation(loc.getLocationId());
        
        Sighting fromDao = sightingDaoTest.getSightingById(testSighting.getSightingId());
        
        assertEquals(testSighting.getSightingId(), fromDao.getSightingId());
        assertEquals(1,sightingsByLocation.size());
        
    }
    
        /**
     * Test of getAllSightingsByDate method, of class SuperheroSightingsSightingDaoJdbcTemplateImpl.
     */
    @Test
    public void getAllSightingsByDate() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        // add the superpower to the Superpowers table, otherwise you can't add it to PersonSuperpowers bridge table which will give you an error
        spDaoTest.createSuperpower(s);
        
        List<Superpower> superpowerlist = new ArrayList<>();
        superpowerlist.add(s);
        
        
        Person p = new Person();
        p.setFirstName("George");
        p.setLastName("Shelley");
        p.setIsHero(Boolean.TRUE);
        p.setDescriptionOfPerson("Fake description");
        p.setListOfSuperpowers(superpowerlist);
        
        pDaoTest.createPerson(p);
        
        
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
        
        locDaoTest.createLocation(loc);
        
        
        LocalDate dt = LocalDate.parse("2016-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        Sighting testSighting = new Sighting();
        testSighting.setIsHeroSighting(true);
        testSighting.setPerson(p);
        testSighting.setLocation(loc);
        testSighting.setJustTheSightingDate(dt);
        
        sightingDaoTest.createSighting(testSighting);
        
        
        Sighting fromDao = sightingDaoTest.getSightingById(testSighting.getSightingId());

        assertEquals(testSighting.getSightingDate(), fromDao.getSightingDate());
        
    }
           
    
}
