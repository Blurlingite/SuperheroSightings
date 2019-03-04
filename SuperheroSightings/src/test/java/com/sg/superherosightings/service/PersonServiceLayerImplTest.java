/*
    Only tested the getById() method because that is the only one that throws an exception
 */
package com.sg.superherosightings.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

/**
 *
 * @author vishnukdawah
 */
public class PersonServiceLayerImplTest {
    
     SuperheroSightingsPersonServiceLayer personServiceLayerTest;
     SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayerTest;

    public PersonServiceLayerImplTest() {
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

        List<Person> personList = personServiceLayerTest.getAllPersons();
        for(Person p: personList){
            personServiceLayerTest.deletePerson(p.getPersonId());
        }
        
        List<Superpower> superpowerList = superpowerServiceLayerTest.getAllSuperpowers();
        for(Superpower s: superpowerList){
            superpowerServiceLayerTest.deleteSuperpower(s.getSuperpowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createPerson method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testCreatePerson() {
    }

    /**
     * Test of getPersonById method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testGetPersonById() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerId(22);
        s.setSuperpowerName("Nature Power");
        s.setSuperpowerDescription("Can control nature");
        
        superpowerServiceLayerTest.createSuperpower(s);
        
        List<Superpower> slist = new ArrayList<>();
        slist.add(s);

        
        Person testPerson = new Person();
        testPerson.setFirstName("Viridi");
        testPerson.setLastName("GON");
        testPerson.setIsHero(false);
        testPerson.setDescriptionOfPerson("Goddess of Nature");
        testPerson.setListOfSuperpowers(slist);
        
        personServiceLayerTest.createPerson(testPerson);
        
        personServiceLayerTest.deletePerson(testPerson.getPersonId());
        
        try{
            Person fromDao = personServiceLayerTest.getPersonById(testPerson.getPersonId());
            fail("Failed to encounter EntityNotFoundException");
        }catch(EntityNotFoundException ex){
            return;
        }
        
    }

    /**
     * Test of getAllPersons method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testGetAllPersons() {
        
       
    }

    /**
     * Test of updatePerson method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testUpdatePerson() {
    }

    /**
     * Test of deletePerson method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testDeletePerson() {
    }

    /**
     * Test of getAllPersonsSightedAtLocation method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testGetAllPersonsSightedAtLocation() {
    }

    /**
     * Test of setPersonsFromSightingsByLocation method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testSetPersonsFromSightingsByLocation() {
    }

    /**
     * Test of getPersonsFromSightingsByLocation method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testGetPersonsFromSightingsByLocation() {
    }

    /**
     * Test of findPersonForSighting method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testFindPersonForSighting() {
    }


    /**
     * Test of findPersonsForOrganization method, of class SuperheroSightingsPersonServiceLayerImpl.
     */
    @Test
    public void testFindPersonsForOrganization() {
    }
    
}
