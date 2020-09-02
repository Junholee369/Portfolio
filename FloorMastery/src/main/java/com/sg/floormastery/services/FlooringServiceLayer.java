package com.sg.floormastery.services;

import com.sg.floormastery.dao.FlooringOrderDaoException;
import com.sg.floormastery.models.Order;
import java.time.LocalDate;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author junho
 */
public interface FlooringServiceLayer {

    public List<Order> getAllOrder(LocalDate readUserDate) throws FlooringOrderDaoException;

    public Order getOrderInfo(int orderNum)throws FlooringOrderDaoException, FlooringValidationException;
    
    public void addOrder(Order toAdd) throws FlooringOrderDaoException, FlooringValidationException;
    
    public void editOrder(Order toEdit) throws FlooringOrderDaoException, FlooringValidationException;

    public void removeOrder(int orderNum) throws FlooringOrderDaoException, FlooringValidationException;

    
    
}
