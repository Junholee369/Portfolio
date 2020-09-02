/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.services;

import com.sg.floormastery.dao.FlooringOrderDao;
import com.sg.floormastery.dao.FlooringOrderDaoException;
import com.sg.floormastery.models.Customer;

import com.sg.floormastery.models.Order;
import com.sg.floormastery.models.Product;
import com.sg.floormastery.models.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author junho
 */
@Component
public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    @Autowired
    private FlooringOrderDao oDao;

    @Override
    public List<Order> getAllOrder(LocalDate readUserDate) throws FlooringOrderDaoException {
        return oDao.getAllOrder(readUserDate);
    }

    @Override
    public Order getOrderInfo(int id) throws FlooringOrderDaoException, FlooringValidationException {
     
        if (oDao.getOrder(id) == null) {
            throw new FlooringValidationException("This order is not avaliable");
        }

        return oDao.getOrder(id);
    }

    @Override
    public void addOrder(Order toAdd) throws FlooringValidationException {

        if (toAdd == null) {
            throw new FlooringValidationException("This is null.");
        }

        if (!(toAdd.getDate().compareTo(LocalDate.of(2060, 1, 1)) < 0 && toAdd.getDate().compareTo(LocalDate.now()) >= 0)) {
            throw new FlooringValidationException("the date is not in range");
        }

        try {

            if (toAdd.getArea() == null || toAdd.getArea().compareTo(BigDecimal.ZERO) < 0) {
                throw new FlooringValidationException(toAdd.getArea() + " Not a Valid Area Number");
            }
            StateTax st = oDao.getStateTax(toAdd.getTax().getState());

            if (st == null) {
                throw new FlooringValidationException("Could not find tax for the State you have entered " + toAdd.getTax().getState());
            }
            toAdd.setTax(st);

            Product p = oDao.getProduct(toAdd.getProd().getProductType());
            if (p == null) {
                throw new FlooringValidationException("Could not find the Product you have entered " + toAdd.getTax().getState());
            }
            toAdd.setProd(p);

            List<Customer> listCustomers = new ArrayList();
            for (Customer c : toAdd.getCustomers()) {
                Customer toCheck = oDao.getCustomer(c.getName());
                if (toCheck == null || c.getName().equals("")) {
                    throw new FlooringValidationException("Could not find the Customer you have entered " + toAdd.getTax().getState());
                }
                listCustomers.add(toCheck);

            }
            toAdd.setCustomers(listCustomers);

            oDao.addOrder(toAdd);

        } catch (FlooringOrderDaoException ex) {

            throw new FlooringValidationException(" Woops, something went wrong with the add service layer DAO. ");
        }

    }

    @Override
    public void editOrder(Order toEdit) throws FlooringOrderDaoException, FlooringValidationException {

        try {

            if (toEdit == null) {
                throw new FlooringValidationException("This is null.");
            }

//            if (pDao.getProduct(toEdit.getProductType()) == null || toEdit.getProductType().isBlank()) {
//                throw new FlooringValidationException("The Product you Entered which is this -> " + toEdit.getProductType() + " is Invalid.");
//
//            }
            if (toEdit.getArea() == null || toEdit.getArea().compareTo(BigDecimal.ZERO) < 0) {
                throw new FlooringValidationException("The Area value you entered which is " + toEdit.getArea() + "is Invalid.");
            }

//            if (toEdit.getCustomerName() == null || toEdit.getCustomerName().isBlank()) {
//                throw new FlooringValidationException(toEdit.getCustomerName() + " Not a Valid Name");
//            }
//            if (tDao.getTaxByState(toEdit.getState()) == null || toEdit.getState().isBlank()) {
//
//                throw new FlooringValidationException("We don't have " + toEdit.getState() + " on the record. ");
//            }
//
//            toEdit.setLaborCostPerSquareFoot(pDao.getProduct(toEdit.getProductType()).getLaborCostPerSquareFoot());
//
//            toEdit.setMaterialCostPerSquareFoot(pDao.getProduct(toEdit.getProductType()).getMaterialCostPerSquareFoot());
//
//            toEdit.setTaxRate(tDao.getTaxByState(toEdit.getState()).getTaxRate());
//
            oDao.editOrder(toEdit);

        } catch (FlooringOrderDaoException ex) {

            throw new FlooringValidationException(" Woops, something went wrong with the edit service layer DAO. ");
        }

    }

    @Override
    public void removeOrder(int orderNum) throws FlooringOrderDaoException, FlooringValidationException {
        try {
            if (oDao.getOrder(orderNum) == null) {
                throw new FlooringValidationException("This order is not avaliable");
            }

            oDao.removeOrder(orderNum);
        } catch (FlooringOrderDaoException ex) {

            throw new FlooringValidationException(" Woops, something went wrong with the edit service layer DAO. ");
        }

    }

}
