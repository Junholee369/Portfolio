/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.controller;

import com.sg.SuperHeroSighting.model.Hero;
import com.sg.SuperHeroSighting.model.Organization;
import com.sg.SuperHeroSighting.model.Power;
import com.sg.SuperHeroSighting.service.HeroService;
import com.sg.SuperHeroSighting.service.OrganizationService;

import com.sg.SuperHeroSighting.service.PowerService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author junho
 */
@Controller
public class HeroController {

    @Autowired
    HeroService heroService;

    @Autowired
    PowerService powerService;

    @Autowired
    OrganizationService organizationService;

    @GetMapping("Supers")
    public String displayHeros(Model model) {
        List<Hero> heros = heroService.getAllHeros();
        List<Power> powers = powerService.getAllPowers();
        List<Organization> organizations = organizationService.getAllOrganizaitons();
        model.addAttribute("heros", heros);
        model.addAttribute("powers", powers);
        model.addAttribute("organizaitons", organizations);
        return "heros";
    }

    @PostMapping("addSuper")
    public String addSuper(HttpServletRequest request) {
        String superName = request.getParameter("superName");
        String description = request.getParameter("description");
        String power = request.getParameter("power");
        String[] organizationIds = request.getParameterValues("organizationid");

        Hero hero = new Hero();
        hero.setName(superName);
        hero.setDescription(description);
        hero.setSuperPower(powerService.getPowerById(Integer.parseInt(power)));

        Set<Organization> organizations = new HashSet<>();
        for (String organizationid : organizationIds) {
            organizations.add(organizationService.getOrganizationById(Integer.parseInt(organizationid)));
        }

        hero.setOrganization(organizations);
        heroService.addHero(hero);

        return "redirect:/Supers";
    }

    @GetMapping("editSuper")
    public String editSuper(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroService.getSuperById(id);
        List<Power> powers = powerService.getAllPowers();
        List<Organization> organizations = organizationService.getAllOrganizaitons();

        model.addAttribute("hero", hero);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);

        return "editSuper";
    }

    @PostMapping("editSuper")
    public String performEditSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroService.getSuperById(id);
        String powerId = request.getParameter("powerId");
        String[] organizationIds = request.getParameterValues("organizationid");

        hero.setName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("description"));
        hero.setSuperPower(powerService.getPowerById(Integer.parseInt(powerId)));

        Set<Organization> organizations = new HashSet<>();
        for (String organizationid : organizationIds) {
            organizations.add(organizationService.getOrganizationById(Integer.parseInt(organizationid)));
        }

        hero.setOrganization(organizations);

        heroService.editSuper(hero);
        return "redirect:/Supers";
    }

    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        heroService.deleteSuperById(id);
        return "redirect:/Supers";
    }

    @GetMapping("heroDetail")
    public String heroDetail(Integer id, Model model) {
        Hero hero = heroService.getSuperById(id);
        model.addAttribute("hero", hero);
        return "heroDetail";
    }

}
