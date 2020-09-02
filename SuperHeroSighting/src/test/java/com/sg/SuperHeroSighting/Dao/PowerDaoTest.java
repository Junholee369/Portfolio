/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.Dao;

import com.sg.SuperHeroSighting.model.Power;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class PowerDaoTest {

    @Autowired
    PowerDao toTest;

    public PowerDaoTest() {
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
    public void testPowerGetAndAdd() {
        Power power = new Power();
        power.setPowerType("Test Power");

        power = toTest.save(power);

        Power fromDao = toTest.getOne(power.getSuperPowerId());
        assertEquals("Test Power", fromDao.getPowerType());

    }

    @Test
    @Transactional
    public void testGetAllPower() {
        Power power = new Power();
        power.setPowerType("Test Power 1");
        power = toTest.save(power);

        Power power2 = new Power();
        power2.setPowerType("Test Power 2");
        power2 = toTest.save(power2);

        List<Power> powers = toTest.findAll();

        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));

    }

    @Test
    @Transactional
    public void testUpdatePower() {
        Power power = new Power();
        power.setPowerType("Test Power");
        power = toTest.save(power);

        Power fromDao = toTest.getOne(power.getSuperPowerId());
        assertEquals("Test Power", fromDao.getPowerType());

        power.setPowerType("Updated Power");
        toTest.save(power);

        assertNotEquals("Test Power", fromDao.getPowerType());

        fromDao = toTest.getOne(power.getSuperPowerId());
        assertEquals("Updated Power", fromDao.getPowerType());
    }

    @Test
    @Transactional
    public void testDeletePowerById() {
        Power power = new Power();
        power.setPowerType("Test Power");
        power = toTest.save(power);

        Power fromDao = toTest.getOne(power.getSuperPowerId());
        assertEquals("Test Power", fromDao.getPowerType());

        toTest.deleteById(fromDao.getSuperPowerId());
        
        fromDao = toTest.findById(power.getSuperPowerId()).orElse(null);
        assertNull(fromDao);
    }

}
