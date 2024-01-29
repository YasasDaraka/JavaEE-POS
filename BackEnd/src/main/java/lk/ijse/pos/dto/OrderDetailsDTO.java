package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private String oid;
    private String itmCode;
    private int itmQTY;
    private double itmPrice;

    public OrderDetails toEntity(){
        OrderDetails details = new OrderDetails();
        details.setOid(this.oid);
        details.setItmCode(this.itmCode);
        details.setItmQTY(this.itmQTY);
        details.setItmPrice(this.itmPrice);
        return details;
    }
}
