/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.controller;

import com.sg.SuperHeroSighting.model.Power;
import com.sg.SuperHeroSighting.service.PowerService;
import java.util.List;
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
public class PowerController {

    @Autowired
    PowerService powerService;

    @GetMapping("SuperPowers")
    public String displayPowers(Model model) {
        List<Power> powers = powerService.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {
        String powerName = request.getParameter("powerName");

        Power power = new Power();
        power.setPowerType(powerName);

        powerService.addPower(power);

        return "redirect:/SuperPowers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        powerService.deletePowerById(id);

        return "redirect:/SuperPowers";
    }

    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powerService.getPowerById(id);

        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powerService.getPowerById(id);

        power.setPowerType(request.getParameter("powerName"));

        powerService.editPower(power);
        return "redirect:/SuperPowers";
    }

}
