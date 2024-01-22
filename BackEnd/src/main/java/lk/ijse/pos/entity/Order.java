package lk.ijse.pos.entity;

import lk.ijse.pos.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String oid;
    private Date date;
    private String cusID;

    public OrderDTO toDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOid(this.oid);
        orderDTO.setDate(this.date);
        orderDTO.setCusID(this.cusID);
        return orderDTO;
    }
}
