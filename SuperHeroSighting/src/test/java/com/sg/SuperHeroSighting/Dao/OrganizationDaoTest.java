/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.Dao;

import com.sg.SuperHeroSighting.model.Hero;
import com.sg.SuperHeroSighting.model.Location;
import com.sg.SuperHeroSighting.model.Organization;
import com.sg.SuperHeroSighting.model.Power;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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
public class OrganizationDaoTest {

    @Autowired
    OrganizationDao toTest;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;
    
    @Autowired
    PowerDao powerDao;

    public OrganizationDaoTest() {
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
    public void testOrganizationAddAndGet() {
        Organization organization = new Organization();
        organization.setName("testName");
        organization.setDescription("testDescription");
        organization.setPhone("0000000000");
        organization.setEmail("testEmail");

        Location location = new Location();
        location.setName("testLocation");
        location.setAddress("testAddress");
        location.setDescription("testDescription");
        location.setLatitude(new BigDecimal("1234"));
        location.setLongitude(new BigDecimal("1234"));
        
        Hero hero = new Hero();
        hero.setName("testMan");
        hero.setDescription("tester");

        Power power = new Power();
        power.setPowerType("testPower");

        power = powerDao.save(power);
        hero.setSuperPower(power);
        
        Hero OWO = new Hero();
        OWO.setName("OWOMan");
        OWO.setDescription("OWODescription");
        OWO.setSuperPower(power);
        
        hero = heroDao.save(hero);
        OWO = heroDao.save(OWO);
        
        Set<Hero> heros = new HashSet();
        heros.add(hero);
        heros.add(OWO);
        organization.setHero(heros);
         
        location = locationDao.save(location);
        organization.setLocation(location);
        
        organization = toTest.save(organization);
        Organization fromDao = toTest.getOne(organization.getOrganizationId());

        assertEquals("testName", fromDao.getName());
        assertEquals("testDescription", fromDao.getDescription());
        assertEquals("0000000000", fromDao.getPhone());
        assertEquals("testEmail", fromDao.getEmail());
        assertEquals(location.getLocationId(), fromDao.getLocation().getLocationId());
        assertEquals("testLocation", fromDao.getLocation().getName());
        assertTrue(fromDao.getHero().contains(hero));
        assertTrue(fromDao.getHero().contains(OWO));
    }

}
