/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.service;

import com.sg.SuperHeroSighting.Dao.OrganizationDao;
import com.sg.SuperHeroSighting.model.Organization;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author junho
 */
@Service
public class OrganizationService {

    @Autowired
    OrganizationDao organizationDao;

    public List<Organization> getAllOrganizaitons() {
        List<Organization> organizations = organizationDao.findAll();
        return organizations;
    }

    public Organization addOrganization(Organization organization) {
        organization = organizationDao.save(organization);
        return organization;
    }

    public void deleteOrganizationById(int id) {
        organizationDao.deleteById(id);
    }

    public Organization getOrganizationById(int id) {
        Organization organization = organizationDao.getOne(id);
        return organization;
    }

    public void editOrganization(Organization organization) {
        organizationDao.save(organization);
    }
    
}
