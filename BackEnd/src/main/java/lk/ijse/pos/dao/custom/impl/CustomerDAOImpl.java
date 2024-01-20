package lk.ijse.pos.dao.custom.impl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.listener.ContextListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO<Customer,String> {

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        try (Connection connection = ContextListener.pool.getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer")) {
            ResultSet rst = pstm.executeQuery();
            ArrayList<Customer> customerAr = new ArrayList<>();
            while (rst.next()) {
                Customer customer = new Customer(rst.getString(1), rst.getString(2),
                        rst.getString(3));
                customerAr.add(customer);
            }
            return customerAr;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
