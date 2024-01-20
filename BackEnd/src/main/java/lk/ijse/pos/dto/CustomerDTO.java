package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private String name;
    private String address;

    public Customer toEntity(){
        Customer cus = new Customer();
        cus.setCusID(this.id);
        cus.setCusName(this.name);
        cus.setCusAddress(this.address);
        return cus;
    }
}
