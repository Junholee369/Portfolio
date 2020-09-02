/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author junho
 */
public class Order {

    private LocalDate date;
    private int orderNumber;
    private List<Customer> customers;
    private BigDecimal area;
    private StateTax tax;
    private Product prod;

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the tax //
     */
    public BigDecimal getTotalTax() {

        return getTotalMaterialCost().add(getTotalLaborCost()).multiply(tax.getTaxRate() ).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    /**
     * @return the Total
     */
    public BigDecimal getTotal() {
        return getTotalMaterialCost().add(getTotalLaborCost().add(getTotalTax()));
    }

    /**
     * @return the tax
     */
    public StateTax getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(StateTax tax) {
        this.tax = tax;
    }

    /**
     * @return the prod
     */
    public Product getProd() {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(Product prod) {
        this.prod = prod;
    }

    /**
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public BigDecimal getTotalMaterialCost() {
        return area.multiply(prod.getMaterialCostPerSquareFoot());
    }

    public BigDecimal getTotalLaborCost() {
        return area.multiply(prod.getLaborCostPerSquareFoot());
        }

}
