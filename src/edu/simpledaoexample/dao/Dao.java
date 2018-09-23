package edu.simpledaoexample.dao;

import edu.simpledaoexample.entities.Base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T extends Base> {
    public abstract void insert(T entity) throws SQLException, IOException;
    public abstract T getById(int id) throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(T entity) throws SQLException;
    public abstract ArrayList<T> getAll() throws SQLException;
}
