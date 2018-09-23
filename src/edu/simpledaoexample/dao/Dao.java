package edu.simpledaoexample.dao;

import edu.simpledaoexample.entities.Base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T extends Base> {
    public abstract void insert(T entity);
    public abstract T getById(int id);
    public abstract void update(T entity);
    public abstract void delete(T entity);
    public abstract ArrayList<T> getAll();
}
