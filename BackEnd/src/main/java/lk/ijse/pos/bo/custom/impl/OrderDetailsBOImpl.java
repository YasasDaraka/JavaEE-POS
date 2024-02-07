package lk.ijse.pos.bo.custom.impl;
import lk.ijse.pos.bo.custom.OrderDetailsBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.OrderDetails;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        ArrayList<OrderDetailsDTO> orderAr = new ArrayList<>();

        for (OrderDetails order : all) {
            OrderDetailsDTO dto = order.toDto();
            orderAr.add(dto);
        }
        return orderAr;
    }
}
