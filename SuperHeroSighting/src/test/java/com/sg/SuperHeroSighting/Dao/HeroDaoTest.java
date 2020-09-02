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
import java.util.List;
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
public class HeroDaoTest {

    @Autowired
    HeroDao toTest;

    @Autowired
    PowerDao powerDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    public HeroDaoTest() {
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
//        powerDao.deleteAll();
//        organizationDao.deleteAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testHeroDaoGetAndAdd() {
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
        coolerOrganization = organizationDao.save(coolerOrganization);

        Set<Organization> orgs = new HashSet();
        orgs.add(organization);
        orgs.add(coolerOrganization);
        hero.setOrganization(orgs);

        power = powerDao.save(power);
        hero.setSuperPower(power);

        hero = toTest.save(hero);
        Hero fromDao = toTest.getOne(hero.getHeroId());

        assertEquals("testMan", fromDao.getName());
        assertEquals(power.getSuperPowerId(), fromDao.getSuperPower().getSuperPowerId());
        assertEquals("testPower", fromDao.getSuperPower().getPowerType());
        assertEquals("tester", fromDao.getDescription());
        assertEquals(2, fromDao.getOrganization().size());
        assertTrue(fromDao.getOrganization().contains(organization));
        assertTrue(fromDao.getOrganization().contains(coolerOrganization));
    }

    @Test
    @Transactional
    public void testGetAllSuper() {
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
        coolerOrganization = organizationDao.save(coolerOrganization);

        Set<Organization> orgs = new HashSet();
        orgs.add(organization);
        orgs.add(coolerOrganization);
        hero.setOrganization(orgs);

        power = powerDao.save(power);
        hero.setSuperPower(power);

        hero = toTest.save(hero);

        Hero hero2 = new Hero();
        hero2.setName("testMan2");
        hero2.setDescription("tester2");

        hero2.setSuperPower(power);
        hero2 = toTest.save(hero2);

        List<Hero> heros = toTest.findAll();
        assertTrue(heros.contains(hero));
        assertTrue(heros.contains(hero2));
    }

    @Test
    @Transactional
    public void testEditHero() {
        Hero hero = new Hero();
        hero.setName("testMan");
        hero.setDescription("tester");

        Power power = new Power();
        power.setPowerType("testPower");

        Power power2 = new Power();
        power2.setPowerType("testPower2");

        Organization organization = new Organization();
        organization.setName("oName");
        organization.setDescription("oDescription");
        organization.setPhone("0000000000");
        organization.setEmail("oEmail");

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
        coolerOrganization = organizationDao.save(coolerOrganization);

        Set<Organization> orgs = new HashSet();
        orgs.add(organization);
        orgs.add(coolerOrganization);
        hero.setOrganization(orgs);

        power2 = powerDao.save(power2);
        power = powerDao.save(power);

        hero.setSuperPower(power);

        hero = toTest.save(hero);

        Hero fromDao = toTest.getOne(hero.getHeroId());
        assertEquals("testMan", fromDao.getName());
        assertEquals(power.getSuperPowerId(), fromDao.getSuperPower().getSuperPowerId());
        assertEquals("testPower", fromDao.getSuperPower().getPowerType());
        assertEquals("tester", fromDao.getDescription());
        assertEquals(2, fromDao.getOrganization().size());
        assertTrue(fromDao.getOrganization().contains(organization));
        assertTrue(fromDao.getOrganization().contains(coolerOrganization));

        hero.setName("UpdateNameMan");
        hero.setSuperPower(power2);
        hero.setDescription("UpdatedDescription");
        hero.setOrganization(orgs);

        toTest.save(hero);
        assertNotEquals("testMan", fromDao.getName());
        assertNotEquals(power.getSuperPowerId(), fromDao.getSuperPower().getSuperPowerId());
        assertNotEquals("testPower", fromDao.getSuperPower().getPowerType());
        assertNotEquals("tester", fromDao.getDescription());
        assertEquals(2, fromDao.getOrganization().size());
        assertTrue(fromDao.getOrganization().contains(organization));
        assertTrue(fromDao.getOrganization().contains(coolerOrganization));

        fromDao = toTest.getOne(hero.getHeroId());
        assertEquals("UpdateNameMan", fromDao.getName());
//        assertEquals(power.getSuperPowerId(), fromDao.getSuperPower().getSuperPowerId());
        assertEquals("testPower2", fromDao.getSuperPower().getPowerType());
        assertEquals("UpdatedDescription", fromDao.getDescription());
        assertEquals(2, fromDao.getOrganization().size());
        assertTrue(fromDao.getOrganization().contains(organization));
        assertTrue(fromDao.getOrganization().contains(coolerOrganization));

    }

    @Test
    @Transactional
    public void testDeleteSuper() {
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
        coolerOrganization = organizationDao.save(coolerOrganization);

        Set<Organization> orgs = new HashSet();
        orgs.add(organization);
        orgs.add(coolerOrganization);
        hero.setOrganization(orgs);

        power = powerDao.save(power);
        hero.setSuperPower(power);

        hero = toTest.save(hero);
        Hero fromDao = toTest.getOne(hero.getHeroId());

        assertEquals("testMan", fromDao.getName());
        assertEquals(power.getSuperPowerId(), fromDao.getSuperPower().getSuperPowerId());
        assertEquals("testPower", fromDao.getSuperPower().getPowerType());
        assertEquals("tester", fromDao.getDescription());
        assertEquals(2, fromDao.getOrganization().size());
        assertTrue(fromDao.getOrganization().contains(organization));
        assertTrue(fromDao.getOrganization().contains(coolerOrganization));

        toTest.deleteById(hero.getHeroId());
        fromDao = toTest.findById(hero.getHeroId()).orElse(null);
        assertNull(fromDao);
    }

}
