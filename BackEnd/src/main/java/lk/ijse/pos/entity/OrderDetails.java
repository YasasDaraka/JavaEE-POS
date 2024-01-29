package lk.ijse.pos.entity;

import lk.ijse.pos.dto.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String oid;
    private String itmCode;
    private int itmQTY;
    private double itmPrice;

    public OrderDetailsDTO toDto(){
        OrderDetailsDTO details = new OrderDetailsDTO();
        details.setOid(this.oid);
        details.setItmCode(this.itmCode);
        details.setItmQTY(this.itmQTY);
        details.setItmPrice(this.itmPrice);
        return details;
    }

}
