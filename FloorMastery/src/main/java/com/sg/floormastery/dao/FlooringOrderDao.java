/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.dao;

import com.sg.floormastery.models.Customer;
import com.sg.floormastery.models.Order;
import com.sg.floormastery.models.Product;
import com.sg.floormastery.models.StateTax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author junho
 */
public interface FlooringOrderDao {

    List<Order> getAllOrder(LocalDate dateChoice)
            throws FlooringOrderDaoException;

    Order getOrder(int id)
            throws FlooringOrderDaoException;

    Order addOrder(Order toAdd)
            throws FlooringOrderDaoException;

    void editOrder(Order edited)
            throws FlooringOrderDaoException;

    void removeOrder(int orderNum)
            throws FlooringOrderDaoException;

    StateTax getStateTax(String state)
            throws FlooringOrderDaoException;

    Product getProduct(String product)
            throws FlooringOrderDaoException;

    Customer getCustomer(String custName)
            throws FlooringOrderDaoException;
    
    List<Customer> getCustomersByOrder(int orderId) 
            throws FlooringOrderDaoException;

    public void deleteAllOrders()
            throws FlooringOrderDaoException;
}
