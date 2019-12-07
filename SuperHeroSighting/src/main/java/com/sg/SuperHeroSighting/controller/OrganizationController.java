package com.sg.SuperHeroSighting.controller;

import com.sg.SuperHeroSighting.model.Hero;
import com.sg.SuperHeroSighting.model.Location;
import com.sg.SuperHeroSighting.model.Organization;
import com.sg.SuperHeroSighting.service.HeroService;
import com.sg.SuperHeroSighting.service.LocationService;
import com.sg.SuperHeroSighting.service.OrganizationService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author junho
 */
@Controller
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    LocationService locationService;

    @Autowired
    HeroService heroService;

    @GetMapping("Organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationService.getAllOrganizaitons();
        List<Location> locations = locationService.getAllLocations();
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("organizations", organizations);
        model.addAttribute("locations", locations);
        model.addAttribute("heros", heros);

        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String[] heroIds = request.getParameterValues("heroid");
            
        Organization organization = new Organization();
        organization.setName(name);
        organization.setLocation(locationService.getLocationById(Integer.parseInt(location)));
        organization.setPhone(phone);
        organization.setEmail(email);
        
        Set<Hero> heros = new HashSet<>();
        for(String heroid : heroIds){
            heros.add(heroService.getSuperById(Integer.parseInt(heroid)));
        }
              
        organization.setHero(heros);
        organizationService.addOrganization(organization);
        return "redirect:/Organizations";
    }

    @GetMapping("orgnizationDetail")
    public String orgnizationDetail(Integer id, Model model) {
        Organization organization = organizationService.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

}
