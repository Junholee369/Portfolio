/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.service;

import com.sg.SuperHeroSighting.Dao.PowerDao;
import com.sg.SuperHeroSighting.model.Power;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author junho
 */
@Service
public class PowerService {

    @Autowired
    PowerDao powerDao;

    public List<Power> getAllPowers() {
        List<Power> powers = powerDao.findAll();
        return powers;
    }

    public Power addPower(Power power) {
        power = powerDao.save(power);
        return power;
    }

    public void deletePowerById(int id) {
        powerDao.deleteById(id);

    }

    public Power getPowerById(int id) {
        Power power = powerDao.getOne(id);
        return power;
    }

    public void editPower(Power power) {
        powerDao.save(power);

    }

}
