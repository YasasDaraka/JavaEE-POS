package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl<T,ID> implements ItemDAO<Item,String> {

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        Item itm = null;
        try {
            itm = SQLUtil.execute("SELECT * FROM item WHERE itmCode = ?", resultSet -> {
                while (resultSet.next()) {
                    return new Item(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getDouble(3),resultSet.getInt(4));
                }
                return null;
            },id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return itm;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemAr = new ArrayList<>();
        try {
            List<Item> result = SQLUtil.execute("SELECT * FROM item", resultSet -> {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getDouble(3),resultSet.getInt(4));
                    itemAr.add(item);
                }
                return null;
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }return itemAr;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
