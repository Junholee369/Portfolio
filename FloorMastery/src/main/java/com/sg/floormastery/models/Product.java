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
public class Product {
    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private int Productid;
 
    
    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the materialCostPerSquareFoot
     */
    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    /**
     * @param materialCostPerSquareFoot the materialCostPerSquareFoot to set
     */
    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    /**
     * @return the laborCostPerSquareFoot
     */
    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    /**
     * @param laborCostPerSquareFoot the laborCostPerSquareFoot to set
     */
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    /**
     * @return the Productid
     */
    public int getProductid() {
        return Productid;
    }

    /**
     * @param Productid the Productid to set
     */
    public void setProductid(int Productid) {
        this.Productid = Productid;
    }
    
    
    
}
   