package lk.ijse.pos.entity;

import lk.ijse.pos.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String cusID;
    private String cusName;
    private String cusAddress;

    public CustomerDTO toDTO(){
        CustomerDTO cusDTO = new CustomerDTO();
        cusDTO.setId(this.cusID);
        cusDTO.setName(this.cusName);
        cusDTO.setAddress(this.cusAddress);
        return cusDTO;
    }
}
