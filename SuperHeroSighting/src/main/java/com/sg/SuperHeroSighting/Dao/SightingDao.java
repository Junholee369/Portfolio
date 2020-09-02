/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.Dao;

import com.sg.SuperHeroSighting.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junho
 */
@Repository
public interface SightingDao extends JpaRepository<Sighting, Integer>{
    
}
