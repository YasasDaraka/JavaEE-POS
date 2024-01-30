package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.listener.ContextListener;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println("Bo save method");

        try (Connection con = ContextListener.pool.getConnection()) {
            con.setAutoCommit(false);

            String insertOrderSql = "INSERT INTO orders(oid, date, cusID) VALUES(?, ?, ?)";
            try (PreparedStatement pstm = con.prepareStatement(insertOrderSql)) {
                pstm.setString(1, dto.getOid());
                pstm.setDate(2, Date.valueOf(dto.getDate()));
                pstm.setString(3, dto.getCusID());

                if (pstm.executeUpdate() <= 0) {
                    System.out.println("Order not added blog");
                    con.rollback();
                    return false;
                }
            }

            String insertOrderDetailsSql = "INSERT INTO orderDetails(oid, itmCode, itmQTY, itmPrice) VALUES(?, ?, ?, ?)";
            String updateItemSql = "UPDATE item SET itmName = ?, itmPrice = ?,itmQTY = ? WHERE itmCode = ?";

            for (OrderDetailsDTO odDTO : dto.getOrderDetails()) {
                System.out.println("OrderDetailsDTO loop blog");

                try (PreparedStatement pstm2 = con.prepareStatement(insertOrderDetailsSql)) {
                    pstm2.setString(1, odDTO.getOid());
                    pstm2.setString(2, odDTO.getItmCode());
                    pstm2.setInt(3, odDTO.getItmQTY());
                    pstm2.setDouble(4, odDTO.getItmPrice());

                    if (pstm2.executeUpdate() <= 0) {
                        System.out.println("OrderDetailsDTO loop detailAdded blog");
                        con.rollback();
                        return false;
                    }

                    System.out.println("item update start blog");
                    Item item = (Item) itemDAO.search(odDTO.getItmCode());

                    if (item != null) {
                        int current = item.getItmQTY();
                        int ordered = odDTO.getItmQTY();
                        item.setItmQTY(current - ordered);
                    } else {
                        System.out.println("Item not found");
                        con.rollback();
                        return false;
                    }

                    System.out.println("item convert blog");

                    try (PreparedStatement pstm3 = con.prepareStatement(updateItemSql)) {
                        pstm3.setString(1, item.getItmName());
                        pstm3.setDouble(2, item.getItmPrice());
                        pstm3.setInt(3, item.getItmQTY());
                        pstm3.setString(4, odDTO.getItmCode());

                        if (pstm3.executeUpdate() <= 0) {
                            System.out.println("item added  blog");
                            con.rollback();
                            return false;
                        }

                        System.out.println("item update");
                    }
                }
            }
            con.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQL Error");
            return false;
        }
    }

    @Override
    public OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException {
        Order order = (Order) orderDAO.search(id);
        if (order != null) {
            return order.toDTO();
        }
        return null;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();
        ArrayList<OrderDTO> orderAr = new ArrayList<>();

        for (Order order : all) {
            OrderDTO dto = order.toDTO();
            orderAr.add(dto);
        }
        return orderAr;
    }

    @Override
    public boolean OrderExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
