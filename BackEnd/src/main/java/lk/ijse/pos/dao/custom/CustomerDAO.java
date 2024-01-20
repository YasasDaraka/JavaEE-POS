package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;

public interface CustomerDAO<T,ID> extends CrudDAO<Customer,String> {
}
