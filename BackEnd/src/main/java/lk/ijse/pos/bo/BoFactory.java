package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.bo.custom.impl.OrderBOImpl;
import lk.ijse.pos.bo.custom.impl.OrderDetailsBOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, ORDER,ORDER_DETAILS
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsBOImpl();
            default:
                return null;
        }
    }

}
