package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Item item = (Item) itemDAO.search(id);
        if(item != null) {
            return item.toDTO();
        }
        return null;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> itemDto = new ArrayList<>();

        for (Item item : all) {
            ItemDTO itemDTO = item.toDTO();
            itemDto.add(itemDTO);
        }
        return itemDto;
    }

    @Override
    public boolean ItemExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
