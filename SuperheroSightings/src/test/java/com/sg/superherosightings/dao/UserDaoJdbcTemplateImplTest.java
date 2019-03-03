///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.superherosightings.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
//import sg.thecodetasticfour.superherosightingsgroup.dto.User;
//
///**
// *
// * @author vishnukdawah
// */
//public class UserDaoJdbcTemplateImplTest {
//    
//    SuperheroSightingsUserDao userDaoTest;
//    SuperheroSightingsOrganizationDao organizationDaoTest;
//    SuperheroSightingsPersonDao personDaoTest;
//    SuperheroSightingsSuperpowerDao superpowerDaoTest;
//
//    
//    public UserDaoJdbcTemplateImplTest() {
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
//        userDaoTest = ctx.getBean("userDaoTest", SuperheroSightingsUserDao.class);
//        organizationDaoTest = ctx.getBean("organizationDaoTest", SuperheroSightingsOrganizationDao.class);
//        personDaoTest = ctx.getBean("personDaoTest", SuperheroSightingsPersonDao.class);
//        superpowerDaoTest = ctx.getBean("superpowerDaoTest", SuperheroSightingsSuperpowerDao.class );
//
//        
//        List<User> testUserList = userDaoTest.getAllUsers();
//        List<Organization> testOrganizationList = organizationDaoTest.getAllOrganizations();
//        List<Person> testPersonList = personDaoTest.getAllPersons();
//        List<Superpower> testSuperpowerList = superpowerDaoTest.getAllSuperpowers();
//        
//        for(User u : testUserList){
//            
//            userDaoTest.deleteUser(u.getUserName());
//        }
//        
//        
//        
//        for(Organization currentOrganization : testOrganizationList){
//            organizationDaoTest.deleteOrganization(currentOrganization.getOrganizationId());
//        }
//        
//        for(Person p : testPersonList){
//            personDaoTest.deletePerson(p.getPersonId());
//        } 
//        
//        
//        for(Superpower currentSuperpower : testSuperpowerList){
//            
//            superpowerDaoTest.deleteSuperpower(currentSuperpower.getSuperpowerId());
//            
//        }
//        
//        
//        
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createUser method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testCreateGetUser() throws Exception {
//        
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ROLE_USER");
//        
//        User u = new User();
//        u.setUserName("exampleUser");
//        u.setUserPassword("examplePassword");
//        u.setFirstName("John");
//        u.setLastName("Doe");
//        u.setEmail("jdoe@gmail.com");
//        u.setAuthorities(userAuthorities);
//        
//        
//        userDaoTest.createUser(u);
//        
//        User fromDao = userDaoTest.getUserByUsername(u.getUserName());
//        
//        assertEquals(u, fromDao);
//        
//        
//        
//    }
//
//
//    /**
//     * Test of getAllUsers method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testGetAllUsers() throws Exception {
//        
//        List<User> listOfUsers = userDaoTest.getAllUsers();
//        
//        assertEquals(0, listOfUsers.size());
//        
//        
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ROLE_USER");
//        
//        User u = new User();
//        u.setUserName("exampleUser");
//        u.setUserPassword("examplePassword");
//        u.setFirstName("John");
//        u.setLastName("Doe");
//        u.setEmail("jdoe@gmail.com");
//        u.setAuthorities(userAuthorities);
//        
//        userDaoTest.createUser(u);
//        
//        listOfUsers = userDaoTest.getAllUsers();
//        
//        assertEquals(1, listOfUsers.size());
//    }
//
//    /**
//     * Test of updateUser method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testUpdateUser() throws Exception {
//        
//        
//        List<User> listOfUsers = userDaoTest.getAllUsers();
//        
//        assertEquals(0, listOfUsers.size());
//        
//        
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ROLE_USER");
//        
//        User u = new User();
//        u.setUserName("exampleUser");
//        u.setUserPassword("examplePassword");
//        u.setFirstName("John");
//        u.setLastName("Doe");
//        u.setEmail("jdoe@gmail.com");
//        u.setAuthorities(userAuthorities);
//        
//        userDaoTest.createUser(u);
//        
//        listOfUsers = userDaoTest.getAllUsers();
//        assertEquals(1, listOfUsers.size());
//        
//        u.setFirstName("Jackie");
//        u.setLastName("Burkhart");
//        
//        userDaoTest.updateUser(u);
//        
//        listOfUsers = userDaoTest.getAllUsers();
//        assertEquals(1, listOfUsers.size());
//        
//        assertEquals("Jackie", u.getFirstName());
//        assertEquals("Burkhart", u.getLastName());
//
//
//    }
//
//    /**
//     * Test of deleteUser method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testDeleteUser() throws Exception {
//        
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ROLE_USER");
//        
//        
//        User u = new User();
//        u.setUserName("exampleUser");
//        u.setUserPassword("examplePassword");
//        u.setFirstName("John");
//        u.setLastName("Doe");
//        u.setEmail("jdoe@gmail.com");
//        u.setAuthorities(userAuthorities);
//        
//        
//        userDaoTest.createUser(u);
//  
//        List<User> listOfUsers = userDaoTest.getAllUsers();
//        
//        assertEquals(1, listOfUsers.size());
//        
//        userDaoTest.deleteUser(u.getUserName());
//        
//        listOfUsers = userDaoTest.getAllUsers();
//        
//        assertEquals(0, listOfUsers.size());
//    }
//
//    /**
//     * Test of findUsersForOrganization method, of class UserDaoJdbcTemplateImpl.
//     */
//    @Test
//    public void testFindUsersForOrganization() throws Exception {
//        
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ROLE_USER");
//        
//        User u = new User();
//        u.setUserName("exampleUser");
//        u.setUserPassword("examplePassword");
//        u.setFirstName("John");
//        u.setLastName("Doe");
//        u.setEmail("jdoe@gmail.com");
//        u.setAuthorities(userAuthorities);
//        
//        userDaoTest.createUser(u);
//        
//        List<User> listOfUsers = new ArrayList<>();
//        listOfUsers.add(u);
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
//        List<Superpower> slist = new ArrayList<>();
//        slist.add(s);
//
//        
//        Person testPerson = new Person();
//        testPerson.setPersonId(5);
//        testPerson.setFirstName("Viridi");
//        testPerson.setLastName("GON");
//        testPerson.setIsHero(false);
//        testPerson.setDescriptionOfPerson("Goddess of Nature");
//        testPerson.setListOfSuperpowers(slist);
//        
//        personDaoTest.createPerson(testPerson);
//        
//        List<Person> listOfPersons = new ArrayList<>();
//        listOfPersons.add(testPerson);
//        
//        Organization o = new Organization();
//        o.setOrganizationName("Test Organization");
//        o.setIsItAHeroOrganization(true);
//        o.setOrganizationDescription(" A description");
//        o.setOrganizationCountry("USA");
//        o.setOrganizationState("New York");
//        o.setOrganizationCity("New York City");
//        o.setOrganizationStreet("11th Street");
//        o.setOrganizationZipcode("11111");
//        o.setListOfPersons(listOfPersons);
//        o.setOrganizationAdmins(listOfUsers);      
//        
//        organizationDaoTest.createOrganization(o);
//        
//        List<User> adminsOfOrganization = userDaoTest.findUsersForOrganization(o);
//        
//        assertEquals(1, adminsOfOrganization.size());
//        
//        
//    }
//    
//}
