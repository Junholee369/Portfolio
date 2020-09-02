/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.service;

import com.sg.SuperHeroSighting.Dao.HeroDao;
import com.sg.SuperHeroSighting.model.Hero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author junho
 */
@Service
public class HeroService {

    @Autowired
    HeroDao heroDao;

    public List<Hero> getAllHeros() {
        List<Hero> heros = heroDao.findAll();
        return heros;
    }

    public Hero addHero(Hero hero) {
        hero = heroDao.save(hero);
        return hero;
    }

    public void deleteSuperById(int id) {
        heroDao.deleteById(id);
    }

    public Hero getSuperById(int id) {
        Hero hero = heroDao.getOne(id);
        return hero;
    }

    public void editSuper(Hero hero) {
        heroDao.save(hero);
    }

}
