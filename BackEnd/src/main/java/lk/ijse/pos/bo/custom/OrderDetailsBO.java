package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {

    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException ;

}
