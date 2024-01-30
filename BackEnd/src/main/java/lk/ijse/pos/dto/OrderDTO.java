package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oid;
    private LocalDate date;
    private String cusID;
    List<OrderDetailsDTO> orderDetails;

    public OrderDTO(String oid, LocalDate date, String cusID) {
        this.oid = oid;
        this.date = date;
        this.cusID = cusID;
    }

    public Order toEntity() {
        Order order = new Order();
        order.setOid(this.oid);
        order.setDate(this.date);
        order.setCusID(this.cusID);
        return order;
    }
}
