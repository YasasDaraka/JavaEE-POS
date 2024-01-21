package lk.ijse.pos.dao;

import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    Customer search(ID id) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;

    boolean exist(ID id) throws SQLException, ClassNotFoundException;
}