package lk.ijse.pos.dao.custom;


import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Order;

public interface OrderDAO<T,ID> extends CrudDAO<Order,String> {
}
