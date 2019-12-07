/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.controller;

import com.sg.SuperHeroSighting.model.Location;
import com.sg.SuperHeroSighting.service.LocationService;
import java.math.BigDecimal;
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
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("Locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String locationName = request.getParameter("locationName");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        BigDecimal longitude = new BigDecimal(request.getParameter("longitude"));
        BigDecimal latitude = new BigDecimal(request.getParameter("latitude"));

        Location location = new Location();
        location.setName(locationName);
        location.setDescription(description);
        location.setAddress(address);
        location.setLongitude(longitude);
        location.setLatitude(latitude);

        locationService.addLocation(location);

        return "redirect:/Locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocaiton(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        locationService.deleteLocationById(id);

        return "redirect:/Locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationService.getLocationById(id);

        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationService.getLocationById(id);

        location.setName(request.getParameter("locationName"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setLongitude(new BigDecimal(request.getParameter("longitude")));
        location.setLatitude(new BigDecimal(request.getParameter("latitude")));

        locationService.editLocation(location);
        return "redirect:/Locations";
    }

}
