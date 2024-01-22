package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oid;
    private Date date;
    private String cusID;

    public Order toEntity() {
        Order order = new Order();
        order.setOid(this.oid);
        order.setDate(this.date);
        order.setCusID(this.cusID);
        return order;
    }
}
