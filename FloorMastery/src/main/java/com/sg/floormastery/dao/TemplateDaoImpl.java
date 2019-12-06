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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author junho
 */
@Component
public class TemplateDaoImpl implements FlooringOrderDao {

    @Autowired
    private JdbcTemplate template;

    private String orderQuery = "SELECT o.*, t.StateAbbrev, t.TaxRate, p.ProductType, p.MatUnitCost, p.LabUnitCost"
            + " FROM orders o"
            + " INNER JOIN products p ON o.ProdId = p.Id"
            + " INNER JOIN taxes t on o.TaxId = t.Id";

    @Override
    @Transactional
    public List<Order> getAllOrder(LocalDate dateChoice) throws FlooringOrderDaoException {

        String query = orderQuery + " WHERE OrderDate = ?";
        List<Order> toReturn = template.query(query, new OrderMapper(), dateChoice);
        
        for(Order o : toReturn){
            List<Customer> customerList = getCustomersByOrder(o.getOrderNumber());
            o.setCustomers(customerList);
            
        }
        
        return toReturn;
    }
    @Override
    public List<Customer> getCustomersByOrder(int orderId) throws FlooringOrderDaoException {

        String query = "SELECT * FROM customers c\n"
                + "inner join customerorders co on co.custId = c.Id\n"
                + "where co.OrderId = ?";

        List<Customer> customers4Order = template.query(query, new CustomerMapper(), orderId);
        
        return customers4Order;
    }

    @Override
    public Order getOrder(int id) throws FlooringOrderDaoException {

        String query = orderQuery + " WHERE o.Id = ?";

        Order toReturn = template.queryForObject(query, new OrderMapper(), id);
        toReturn.setCustomers(getCustomersByOrder(id));
        return toReturn;
    }

    @Override
    @Transactional
    public Order addOrder(Order toAdd) throws FlooringOrderDaoException {
        if(toAdd == null){
            throw new FlooringOrderDaoException("order cannot be null");
            
        }
        if(toAdd.getProd() == null){
            throw new FlooringOrderDaoException("product cannot be null");
            
        }
        if(toAdd.getTax() == null){
            throw new FlooringOrderDaoException("tax cannot be null");
            
        }
        String insert = "INSERT INTO orders (OrderDate,  TaxId, ProdId, Area) VALUES (?,?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                
                toReturn.setDate(1, java.sql.Date.valueOf( toAdd.getDate() ));

                toReturn.setInt(2, toAdd.getTax().getStateTaxid());
                toReturn.setInt(3, toAdd.getProd().getProductid());
                toReturn.setBigDecimal(4, toAdd.getArea());
                
                return toReturn;
            }
             
        };
        
        template.update(psc, holder);
        
        int generatedId = holder.getKey().intValue();
        
        toAdd.setOrderNumber(generatedId);
        String insertCustomer = "insert into customerorders (custId, orderId) values (?, ?)";
        for(Customer c : toAdd.getCustomers()){
            template.update(insertCustomer, c.getCustId(), toAdd.getOrderNumber());
        }
        return toAdd;
    }

    @Override
    public void editOrder(Order edited) throws FlooringOrderDaoException {
        String Edit_Order = "update from orders(OrderDate, TaxId, ProdId, Area) set"
                + "(?,?,?,?) where id = ?";
        
        int RowsAffected = template.update(Edit_Order, edited.getDate(), edited.getTax().getStateTaxid(), edited.getProd().getProductid(), edited.getArea(), edited.getOrderNumber());
        
        if(RowsAffected == 0){
            throw new FlooringOrderDaoException("Cannot Find Order To Edit");
        }
        
         if(RowsAffected > 1){
            throw new FlooringOrderDaoException("Database Does not Have Unique id. You done goofed! ");
        }
    }

    @Override
    public void removeOrder(int orderNum) throws FlooringOrderDaoException {
        String Remove_Order = "delete from orders where id = ?";
        template.update(Remove_Order, orderNum);

    }

    @Override
    public StateTax getStateTax(String state) throws FlooringOrderDaoException {
        String getState = "select * from Taxes where StateAbbrev = ?";
        return template.queryForObject(getState, new TaxMapper(), state);
         
    }

    @Override
    public Product getProduct(String product) throws FlooringOrderDaoException {
        String getProd = "select * from Products where ProductType = ?";
        return template.queryForObject(getProd, new ProductMapper(), product);
    }

    @Override
    public Customer getCustomer(String custName) throws FlooringOrderDaoException {
        String getCust = "select * from Customers where CustName = ?";
        return template.queryForObject(getCust, new CustomerMapper(), custName);
    }

    @Override
    public void deleteAllOrders() throws FlooringOrderDaoException {
        String delAllCustomerOrders = "delete from CustomerOrders";
        String delAllOrders = "delete from Orders";
        template.update(delAllCustomerOrders);
        template.update(delAllOrders);
    }

    private class TaxMapper implements RowMapper<StateTax> {

        @Override
        public StateTax mapRow(ResultSet rs, int i) throws SQLException {
            StateTax toAdd = new StateTax();
            toAdd.setStateTaxid(rs.getInt("Id"));
            toAdd.setState(rs.getString("StateAbbrev"));
            toAdd.setTaxRate(rs.getBigDecimal("TaxRate"));
            return toAdd;
        }

    }
    
    private class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product toAdd = new Product();
            toAdd.setProductid(rs.getInt("Id"));
            toAdd.setProductType(rs.getString("ProductType"));
            toAdd.setMaterialCostPerSquareFoot(rs.getBigDecimal("MatUnitcost"));
            toAdd.setLaborCostPerSquareFoot(rs.getBigDecimal("LabUnitCost"));
            return toAdd;
        }

    }

    private class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet results, int rowNum) throws SQLException {
            Order toAdd = new Order();

            toAdd.setOrderNumber(results.getInt("Id"));

            toAdd.setArea(results.getBigDecimal("Area"));
            toAdd.setDate(LocalDate.parse(results.getString("OrderDate")));

            StateTax orderTax = new StateTax();
            orderTax.setStateTaxid(results.getInt("TaxId"));
            orderTax.setState(results.getString("StateAbbrev"));
            orderTax.setTaxRate(results.getBigDecimal("TaxRate"));

            toAdd.setTax(orderTax);

            Product orderProd = new Product();
            orderProd.setProductid(results.getInt("ProdId"));
            orderProd.setMaterialCostPerSquareFoot(results.getBigDecimal("MatUnitCost"));
            orderProd.setLaborCostPerSquareFoot(results.getBigDecimal("LabUnitCost"));
            orderProd.setProductType(results.getString("ProductType"));

            toAdd.setProd(orderProd);

            return toAdd;
        }

    }

    private class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int i) throws SQLException {
            Customer toAdd = new Customer();

            toAdd.setCustId(rs.getInt("Id"));

            toAdd.setName(rs.getString("CustName"));

            return toAdd;
        }

    }

}
