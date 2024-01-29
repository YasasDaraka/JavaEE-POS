package lk.ijse.pos.dto;

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
}
