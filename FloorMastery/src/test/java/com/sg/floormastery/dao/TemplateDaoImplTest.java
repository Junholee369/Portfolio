/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.dao;

import com.sg.floormastery.TestApplicationConfiguration;
import com.sg.floormastery.models.Customer;
import com.sg.floormastery.models.Order;
import com.sg.floormastery.models.Product;
import com.sg.floormastery.models.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author junho
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TemplateDaoImplTest {

    @Autowired
    FlooringOrderDao toTest;

    public TemplateDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FlooringOrderDaoException {
        toTest.deleteAllOrders();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetAllOrder() throws Exception {
    }

    /**
     * Test of getCustomersByOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetCustomersByOrder() throws Exception {
    }

    /**
     * Test of getOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of addOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testAddOrder() {
        try {
            Order toAdd = new Order();
            toAdd.setDate(LocalDate.of(2019, 10, 31));
            List<Customer> customers = new ArrayList();
            Customer c1 = new Customer();
            c1.setCustId(8);
            customers.add(c1);
            toAdd.setCustomers(customers);

            Product prod = new Product();
            prod.setProductid(4);
            toAdd.setProd(prod);

            toAdd.setArea(new BigDecimal("1000.0000"));

            StateTax tax = new StateTax();
            tax.setStateTaxid(1);
            toAdd.setTax(tax);

            Order added = toTest.addOrder(toAdd);

            Order toCheck = toTest.getOrder(added.getOrderNumber());
            assertEquals(LocalDate.of(2019, 10, 31), toCheck.getDate());
            assertEquals(8, toCheck.getCustomers().get(0).getCustId());
            assertEquals(4, toCheck.getProd().getProductid());
            assertEquals(1, toCheck.getTax().getStateTaxid());
            assertEquals(new BigDecimal("1000.0000"), toCheck.getArea());
        } catch (FlooringOrderDaoException ex) {
            fail(ex.getMessage());
        }

    }

    @Test
    public void testAddOrderNullOrder() {
        try {
            Order toAdd = null;
            Order added = toTest.addOrder(toAdd);
            fail("you shouldn't get this exception, something is wrong. can't Add Null ");
        } catch (FlooringOrderDaoException ex) {
        }

    }

    /**
     * Test of editOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class TemplateDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    /**
     * Test of getStateTax method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetStateTax() throws Exception {
    }

    /**
     * Test of getProduct method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetProduct() throws Exception {
    }

    /**
     * Test of getCustomer method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetCustomer() throws Exception {
    }

}
