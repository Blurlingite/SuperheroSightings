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
public class SuperheroSightingsSuperpowerDaoJdbcTemplateImplTest {
    
    SuperheroSightingsSuperpowerDao superpowerDaoTest;
    SuperheroSightingsPersonDao personDaoTest;
    SuperheroSightingsOrganizationDao organizationDaoTest;
    SuperheroSightingsUserDao userDaoTest;


    public SuperheroSightingsSuperpowerDaoJdbcTemplateImplTest() {
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
        superpowerDaoTest = ctx.getBean("superpowerDaoTest", SuperheroSightingsSuperpowerDao.class);
        personDaoTest = ctx.getBean("personDaoTest", SuperheroSightingsPersonDao.class );
        organizationDaoTest = ctx.getBean("organizationDaoTest", SuperheroSightingsOrganizationDao.class);
        userDaoTest = ctx.getBean("userDaoTest", SuperheroSightingsUserDao.class);

        
        List<Superpower> testSuperpowerList = superpowerDaoTest.getAllSuperpowers();
        List<Person> testPersonList = personDaoTest.getAllPersons();
        List<Organization> testOrganizationList = organizationDaoTest.getAllOrganizations();
        List<User> testUserList = userDaoTest.getAllUsers();

        
        
        for(Superpower sp : testSuperpowerList){
            
            superpowerDaoTest.deleteSuperpower(sp.getSuperpowerId());
        }
        
        
        for(Person p : testPersonList){
            personDaoTest.deletePerson(p.getPersonId());
        }
        

        for(Organization currentOrganization : testOrganizationList){
            organizationDaoTest.deleteOrganization(currentOrganization.getOrganizationId());
        }
        
        
        for(User u : testUserList){
            
            userDaoTest.deleteUser(u.getUserName());
        }
        

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of createSuperpower method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void testCreateGetSuperpower() throws Exception {
        
        Superpower s = new Superpower();
        s.setSuperpowerName("Fire Power");
        s.setSuperpowerDescription("Can shoot fire");
        
        superpowerDaoTest.createSuperpower(s);
        
        Superpower fromDao = superpowerDaoTest.getSuperpowerById(s.getSuperpowerId());
        
        assertEquals(s, fromDao);

    }


    /**
     * Test of getAllSuperpowers method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void testGetAllSuperpowers() throws Exception {
        
        List<Superpower> listOfPowers = superpowerDaoTest.getAllSuperpowers();
        
        assertEquals(0, listOfPowers.size());
        
        Superpower x = new Superpower();
        x.setSuperpowerName("Gravity Power");
        x.setSuperpowerDescription("Can control gravity");

        superpowerDaoTest.createSuperpower(x);
        
        listOfPowers = superpowerDaoTest.getAllSuperpowers();
        
        assertEquals(1, listOfPowers.size());
 
        
        
    }

    /**
     * Test of updateSuperpower method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void testUpdateSuperpower() throws Exception {
        
        List<Superpower> listOfPowers = superpowerDaoTest.getAllSuperpowers();
        
        assertEquals(0, listOfPowers.size());
        
        Superpower x = new Superpower();
        x.setSuperpowerName("Time Power");
        x.setSuperpowerDescription("Can control time");

        superpowerDaoTest.createSuperpower(x);
        
        listOfPowers = superpowerDaoTest.getAllSuperpowers();
        assertEquals(1, listOfPowers.size());
        
        x.setSuperpowerName("Space Power");
        x.setSuperpowerDescription("Can control space");
        
        superpowerDaoTest.updateSuperpower(x);
        
        listOfPowers = superpowerDaoTest.getAllSuperpowers();
        assertEquals(1, listOfPowers.size());
        
        // assert that the name of the superpower changed
        assertEquals("Space Power", x.getSuperpowerName());
        

        
    }

    /**
     * Test of deleteSuperpower method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void testDeleteSuperpower() throws Exception {
        
        Superpower x = new Superpower();
        x.setSuperpowerName("Time Power");
        x.setSuperpowerDescription("Can control time");

        superpowerDaoTest.createSuperpower(x);
  
        List<Superpower> listOfPowers = superpowerDaoTest.getAllSuperpowers();
        
        assertEquals(1, listOfPowers.size());
        
        superpowerDaoTest.deleteSuperpower(x.getSuperpowerId());
        
        listOfPowers = superpowerDaoTest.getAllSuperpowers();
        
        assertEquals(0, listOfPowers.size());
        
        
        
    }
    
    
        /**
     * Test of findSuperpowersForPerson method, of class SuperheroSightingsSuperpowerDaoJdbcTemplateImpl.
     */
    @Test
    public void findSuperpowersForPerson() throws Exception {
        
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

       
        List<Superpower> personPowers = superpowerDaoTest.findSuperpowersForPerson(testPerson);
        
        assertEquals(1, personPowers.size());
        
        
    }
    
    
}
