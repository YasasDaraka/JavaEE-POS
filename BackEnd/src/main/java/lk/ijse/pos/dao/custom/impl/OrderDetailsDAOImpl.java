package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

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
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
