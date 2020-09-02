/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSighting.model;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author junho
 */
@Entity(name = "hero")
public class Hero {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "heroid")
    private int heroId;

    @ManyToOne
    @JoinColumn(name = "superpowerid", nullable = false)
    private Power superPower;

    @Column(name = "name", nullable = false)
    private String name;
    
     @Column(name = "description", nullable = true)
     private String description;
     
    @ManyToMany
    @JoinTable(name="heroorganization",
    joinColumns = {@JoinColumn(name = "heroid")},
    inverseJoinColumns = {@JoinColumn(name = "organizationid")})
    private Set<Organization> organization;

    /**
     * @return the heroId
     */
    public int getHeroId() {
        return heroId;
    }

    /**
     * @param heroId the heroId to set
     */
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    /**
     * @return the superPower
     */
    public Power getSuperPower() {
        return superPower;
    }

    /**
     * @param superPower the superPower to set
     */
    public void setSuperPower(Power superPower) {
        this.superPower = superPower;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the organization
     */
    public Set<Organization> getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(Set<Organization> organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.heroId;
        hash = 89 * hash + Objects.hashCode(this.superPower);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.organization);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }

}