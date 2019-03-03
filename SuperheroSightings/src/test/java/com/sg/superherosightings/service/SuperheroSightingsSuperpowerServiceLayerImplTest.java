///*
//    Only tested the getById() method because that is the only one that throws an exception
// */
//package com.sg.superherosightings.service;
//
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.fail;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
//import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
//import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;
//
///**
// *
// * @author vishnukdawah
// */
//public class SuperheroSightingsSuperpowerServiceLayerImplTest {
//    
//    SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayerTest;
//
//    public SuperheroSightingsSuperpowerServiceLayerImplTest() {
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
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        superpowerServiceLayerTest = ctx.getBean("superpowerServiceLayerTest", SuperheroSightingsSuperpowerServiceLayer.class);
//        
//        
//        
//        List<Superpower> superpowerList = superpowerServiceLayerTest.getAllSuperpowers();
//        for(Superpower s: superpowerList){
//            superpowerServiceLayerTest.deleteSuperpower(s.getSuperpowerId());
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
//     * Test of createSuperpower method, of class SuperheroSightingsSuperpowerServiceLayerImpl.
//     */
//    @Test
//    public void testCreateSuperpower() {
//    }
//
//    /**
//     * Test of getSuperpowerById method, of class SuperheroSightingsSuperpowerServiceLayerImpl.
//     */
//    @Test
//    public void testGetSuperpowerById() throws Exception {
//        
//        Superpower s = new Superpower();
//        s.setSuperpowerName("Superpower Name");
//        s.setSuperpowerDescription("A description");
//        
//        superpowerServiceLayerTest.createSuperpower(s);
//        
//        superpowerServiceLayerTest.deleteSuperpower(s.getSuperpowerId());
//        try{
//        superpowerServiceLayerTest.getSuperpowerById(s.getSuperpowerId());
//        fail("Failed to encounter EntityNotFoundException");
//        }catch(EntityNotFoundException ex){
//            return;
//        }
//    }
//
//    /**
//     * Test of getAllSuperpowers method, of class SuperheroSightingsSuperpowerServiceLayerImpl.
//     */
//    @Test
//    public void testGetAllSuperpowers() {
//    }
//
//    /**
//     * Test of updateSuperpower method, of class SuperheroSightingsSuperpowerServiceLayerImpl.
//     */
//    @Test
//    public void testUpdateSuperpower() {
//    }
//
//    /**
//     * Test of deleteSuperpower method, of class SuperheroSightingsSuperpowerServiceLayerImpl.
//     */
//    @Test
//    public void testDeleteSuperpower() throws Exception {
//    }
//
//    
//}
