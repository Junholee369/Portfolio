/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.service;

import com.sg.SuperHeroSighting.Dao.LocationDao;
import com.sg.SuperHeroSighting.model.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author junho
 */
@Service
public class LocationService {

    @Autowired
    LocationDao locationDao;

    public List<Location> getAllLocations() {
        List<Location> locations = locationDao.findAll();
        return locations;
    }

    public Location addLocation(Location location) {
        location = locationDao.save(location);
        return location;
    }

    public void deleteLocationById(int id) {
        locationDao.deleteById(id);
    }

    public Location getLocationById(int id) {
        Location location = locationDao.getOne(id);
        return location;
    }

    public void editLocation(Location location) {
        locationDao.save(location);
    }
    

    

}
