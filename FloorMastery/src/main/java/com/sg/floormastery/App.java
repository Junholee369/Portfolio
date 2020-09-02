/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery;

import com.sg.floormastery.controller.FlooringController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author junho
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringView myView = new FlooringView(myIo);
//        FlooringOrderDao myOrderDao = new FlooringOrderDaoFileImpl("data");
//        FlooringProductDao myProductDao = new FlooringProductDaoFileImpl("Products.txt");
//        FlooringStateTaxDao myStateTaxDao = new FlooringStateTaxDaoFileImpl("Tax.txt");

//        FlooringServiceLayer myService = new FlooringServiceLayerImpl(myOrderDao, myProductDao, myStateTaxDao);
//        FlooringController controller = new FlooringController(myService, myView);
