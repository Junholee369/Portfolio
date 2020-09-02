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
import com.sg.SuperHeroSighting.model.Sighting;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class SightingDaoTest {

    @Autowired
    SightingDao toTest;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    PowerDao powerDao;
    
    public SightingDaoTest() {
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
    public void testSightingGetandAdd() {
        Sighting sighting = new Sighting();
        sighting.setSeenDate(LocalDate.of(2007, 07, 07));
        
        
        Hero hero = new Hero();
        hero.setName("testMan");
        hero.setDescription("tester");

        Power power = new Power();
        power.setPowerType("testPower");

        Organization organization = new Organization();
        organization.setName("oName");
        organization.setDescription("oDescription");
        organization.setPhone("0000000000");
        organization.setEmail("oEmail");
        
        //Location of the Organization
        Location location = new Location();
        location.setName("oLocation");
        location.setAddress("oAddress");
        location.setDescription("oDescription");
        location.setLatitude(new BigDecimal("1234"));
        location.setLongitude(new BigDecimal("1234"));

        location = locationDao.save(location);
        organization.setLocation(location);
        
        Organization coolerOrganization = new Organization();
        coolerOrganization.setName("coolName");
        coolerOrganization.setDescription("coolDescription");
        coolerOrganization.setPhone("0000000000");
        coolerOrganization.setEmail("coolEmail");

        coolerOrganization.setLocation(location);
        
        organization = organizationDao.save(organization);
        coolerOrganization =organizationDao.save(coolerOrganization);
        
        Set<Organization> orgs = new HashSet();
        orgs.add(organization);
        orgs.add(coolerOrganization);
        hero.setOrganization(orgs);
        
        power = powerDao.save(power);
        hero.setSuperPower(power);

        hero = heroDao.save(hero);
        sighting.setHero(hero);
        
        //Location of the Hero Sighting
        Location sightingLocation = new Location();
        sightingLocation.setName("Location Name");
        sightingLocation.setDescription("Location description");
        sightingLocation.setAddress("Location address");
        sightingLocation.setLongitude(new BigDecimal("-93.2650"));
        sightingLocation.setLatitude(new BigDecimal("44.9778"));
                
        sightingLocation = locationDao.save(sightingLocation);
        sighting.setLocation(sightingLocation);
        
        sighting = toTest.save(sighting);
        Sighting fromDao = toTest.getOne(sighting.getSightingId());
        
        assertEquals(LocalDate.of(2007, 07, 07), fromDao.getSeenDate());
        assertEquals(hero.getHeroId(), fromDao.getHero().getHeroId());
        assertEquals("testMan", fromDao.getHero().getName());
        assertEquals(sightingLocation.getLocationId(), fromDao.getLocation().getLocationId());
        assertEquals("Location Name", fromDao.getLocation().getName());
        
    }

}
