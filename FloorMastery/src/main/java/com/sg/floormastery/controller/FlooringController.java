/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.controller;

import com.sg.floormastery.dao.FlooringOrderDaoException;
import com.sg.floormastery.models.Order;

import com.sg.floormastery.services.FlooringServiceLayer;
import com.sg.floormastery.services.FlooringValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junho
 */
@RestController
@RequestMapping("/api")
public class FlooringController {

    @Autowired
    FlooringServiceLayer service;

    @GetMapping("/orders")
    @CrossOrigin
    public List<Order> getOrdersByDate(String date) {
        try {
            return service.getAllOrder(LocalDate.parse(date));
        } catch (FlooringOrderDaoException ex) {

        }
        return null;
    }

    @GetMapping("/order/{id}")
    @CrossOrigin
    public Order getById(@PathVariable int id) {
        Order toReturn = null;
        try {
            toReturn = service.getOrderInfo(id);
        } catch (FlooringOrderDaoException | FlooringValidationException ex) {

        }
        return toReturn;
    }

}
