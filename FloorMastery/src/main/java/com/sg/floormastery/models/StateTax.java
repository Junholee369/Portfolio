/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.models;

import java.math.BigDecimal;

/**
 *
 * @author junho
 */
public class StateTax {

    private String state;
    private BigDecimal taxRate;
    private int stateTaxid;
    
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the stateTaxid
     */
    public int getStateTaxid() {
        return stateTaxid;
    }

    /**
     * @param stateTaxid the stateTaxid to set
     */
    public void setStateTaxid(int stateTaxid) {
        this.stateTaxid = stateTaxid;
    }
}
