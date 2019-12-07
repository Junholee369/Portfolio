/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author junho
 */
@Entity(name = "sighting")
public class Sighting {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sightingid")
    private int sightingId;
    
    @Column(name = "seendate", nullable = false)
    private LocalDate seenDate ;
    
    @ManyToOne
    @JoinColumn(name = "heroid", nullable = false)
    private Hero hero;
    
    @ManyToOne
    @JoinColumn(name = "locationid", nullable = false)
    private Location location;

    /**
     * @return the sightingId
     */
    public int getSightingId() {
        return sightingId;
    }

    /**
     * @param sightingId the sightingId to set
     */
    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    /**
     * @return the seenDate
     */
    public LocalDate getSeenDate() {
        return seenDate;
    }

    /**
     * @param seenDate the seenDate to set
     */
    public void setSeenDate(LocalDate seenDate) {
        this.seenDate = seenDate;
    }

    /**
     * @return the hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * @param hero the hero to set
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
