package edu.simpledaoexample.dao;

import edu.simpledaoexample.entities.User;

public abstract class UserDao implements Dao<User> {
    public abstract void closeCon() ;
}
