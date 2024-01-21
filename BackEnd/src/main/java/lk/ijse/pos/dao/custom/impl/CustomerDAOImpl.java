package lk.ijse.pos.dao.custom.impl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO<Customer,String> {

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer(cusID, cusName, cusAddress) " +
                "VALUES(?, ?, ?)",rs->null,dto.getCusID(),dto.getCusName(),dto.getCusAddress());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        Customer cus = null;
        try {
             cus = SQLUtil.execute("SELECT * FROM customer WHERE cusId = ?", resultSet -> {
                while (resultSet.next()) {
                    return new Customer(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3));
                }
                return null;
            },id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return cus;
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
        ArrayList<Customer> customerAr = new ArrayList<>();
        try {
            List<Customer> result = SQLUtil.execute("SELECT * FROM customer", resultSet -> {
                while (resultSet.next()) {
                    Customer customer = new Customer(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3));
                    customerAr.add(customer);
                }
                return null;
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return customerAr;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
