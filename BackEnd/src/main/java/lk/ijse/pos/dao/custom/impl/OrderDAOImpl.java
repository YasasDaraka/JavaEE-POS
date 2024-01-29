package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl<T,ID> implements OrderDAO<Order,String> {
    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        Order order = null;
        try {

            order = new SQLUtil().execute("SELECT * FROM orders WHERE oid = ?", resultSet -> {
                while (resultSet.next()) {
                    return  new Order(resultSet.getString(1), (LocalDate) resultSet.getObject(2),
                            resultSet.getString(3));
                }
                return null;
            },id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return order;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            List<Order> result = new SQLUtil().execute("SELECT *\n" +
                    "FROM orders\n" +
                    "ORDER BY\n" +
                    "  CAST(SUBSTRING(oid, 5) AS SIGNED),\n" +
                    "  SUBSTRING(oid, 1, 4)", resultSet -> {
                while (resultSet.next()) {
                    Order order = new Order(resultSet.getString(1), (LocalDate) resultSet.getObject(2),
                            resultSet.getString(3));
                    orders.add(order);
                }
                return null;
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return orders;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
