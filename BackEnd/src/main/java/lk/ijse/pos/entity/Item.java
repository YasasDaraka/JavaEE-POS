package lk.ijse.pos.entity;

import lk.ijse.pos.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String itmCode;
    private String itmName;
    private double itmPrice;
    private int itmQTY;

    public ItemDTO toDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItmCode(this.itmCode);
        itemDTO.setItmName(this.itmName);
        itemDTO.setItmPrice(this.itmPrice);
        itemDTO.setItmQTY(this.itmQTY);
        return itemDTO;
    }
}
