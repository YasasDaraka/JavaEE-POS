package lk.ijse.pos.dao.custom.impl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;
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

        try {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
            ArrayList<Customer> customerAr = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3));
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
