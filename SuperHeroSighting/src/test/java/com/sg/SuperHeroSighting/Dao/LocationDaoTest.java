/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.Dao;

import com.sg.SuperHeroSighting.model.Location;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author junho
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoTest {

    @Autowired
    LocationDao toTest;

    public LocationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        toTest.deleteAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testlocationDaoTestGetAndAdd() {
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLongitude(new BigDecimal("-93.2650"));
        location.setLatitude(new BigDecimal("44.9778"));

        location = toTest.save(location);
//        Location fromDao = toTest.findById(location.getLocationId()).get();

        Location fromDao = toTest.getOne(location.getLocationId());
        assertEquals("Test Name", fromDao.getName());
        assertEquals("Test description", fromDao.getDescription());
        assertEquals("Test address", fromDao.getAddress());
        assertEquals(new BigDecimal("-93.2650"), fromDao.getLongitude());
        assertEquals(new BigDecimal("44.9778"), fromDao.getLatitude());
    }

    @Test
    @Transactional
    public void testGetAllLocation() {

        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLongitude(new BigDecimal("-93.2650"));
        location.setLatitude(new BigDecimal("44.9778"));
        location = toTest.save(location);

        Location location2 = new Location();
        location2.setName("Test Name 2");
        location2.setDescription("Test description 2");
        location2.setAddress("Test address 2");
        location2.setLongitude(new BigDecimal("-99.2650"));
        location2.setLatitude(new BigDecimal("48.9778"));
        location2 = toTest.save(location2);

        List<Location> locations = toTest.findAll();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    @Transactional
    public void testUpdateLocation() {

        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLongitude(new BigDecimal("-93.2650"));
        location.setLatitude(new BigDecimal("44.9778"));
        location = toTest.save(location);

        Location fromDao = toTest.getOne(location.getLocationId());
        assertEquals("Test Name", fromDao.getName());
        assertEquals("Test description", fromDao.getDescription());
        assertEquals("Test address", fromDao.getAddress());
        assertEquals(new BigDecimal("-93.2650"), fromDao.getLongitude());
        assertEquals(new BigDecimal("44.9778"), fromDao.getLatitude());

        location.setName("Update Name");
        location.setDescription("Update description");
        location.setAddress("Update address");
        location.setLongitude(new BigDecimal("-77.2650"));
        location.setLatitude(new BigDecimal("55.9778"));

        toTest.save(location);
        assertNotEquals("Test Name", fromDao.getName());
        assertNotEquals("Test description", fromDao.getDescription());
        assertNotEquals("Test address", fromDao.getAddress());
        assertNotEquals(new BigDecimal("-93.2650"), fromDao.getLongitude());
        assertNotEquals(new BigDecimal("44.9778"), fromDao.getLatitude());

        fromDao = toTest.getOne(location.getLocationId());
        assertEquals("Update Name", fromDao.getName());
        assertEquals("Update description", fromDao.getDescription());
        assertEquals("Update address", fromDao.getAddress());
        assertEquals(new BigDecimal("-77.2650"), fromDao.getLongitude());
        assertEquals(new BigDecimal("55.9778"), fromDao.getLatitude());
        

    }

    @Test
    @Transactional
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLongitude(new BigDecimal("-93.2650"));
        location.setLatitude(new BigDecimal("44.9778"));

        location = toTest.save(location);

        Location fromDao = toTest.getOne(location.getLocationId());
        assertEquals("Test Name", fromDao.getName());
        assertEquals("Test description", fromDao.getDescription());
        assertEquals("Test address", fromDao.getAddress());
        assertEquals(new BigDecimal("-93.2650"), fromDao.getLongitude());
        assertEquals(new BigDecimal("44.9778"), fromDao.getLatitude());

        toTest.deleteById(location.getLocationId());

        fromDao = toTest.findById(location.getLocationId()).orElse(null);
        assertNull(fromDao);
    }

}
