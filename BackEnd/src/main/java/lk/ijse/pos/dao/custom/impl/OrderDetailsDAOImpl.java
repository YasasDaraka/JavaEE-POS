package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl<T,ID> implements OrderDetailsDAO<OrderDetails,String> {
    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return new SQLUtil().execute("INSERT INTO orderDetails(oid, itmCode, itmQTY, itmPrice) " +
                "VALUES(?, ?, ?, ?)",rs->null,dto.getOid(),dto.getItmCode(),dto.getItmQTY(),dto.getItmPrice());
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orders = new ArrayList<>();
        try {
            List<Order> result = new SQLUtil().execute("SELECT *\n" +
                    "FROM orderDetails\n" +
                    "ORDER BY\n" +
                    "  CAST(SUBSTRING(oid, 5) AS SIGNED),\n" +
                    "  SUBSTRING(oid, 1, 4)", resultSet -> {
                while (resultSet.next()) {
                    OrderDetails order = new OrderDetails(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3),resultSet.getDouble(4));
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
