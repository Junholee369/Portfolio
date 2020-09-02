/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author junho
 */
@Entity(name = "superpower")
public class Power {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "superpowerid", nullable = false)
    private int superPowerId;
    
    @Column (name ="powertype", nullable = false)
    private String powerType;

    
    /**
     * @return the superPowerId
     */
    public int getSuperPowerId() {
        return superPowerId;
    }

    /**
     * @param superPowerId the superPowerId to set
     */
    public void setSuperPowerId(int superPowerId) {
        this.superPowerId = superPowerId;
    }

    /**
     * @return the powerType
     */
    public String getPowerType() {
        return powerType;
    }

    /**
     * @param powerType the powerType to set
     */
    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }
    
}
