package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException ;

    boolean OrderExist(String id) throws SQLException, ClassNotFoundException;
}
