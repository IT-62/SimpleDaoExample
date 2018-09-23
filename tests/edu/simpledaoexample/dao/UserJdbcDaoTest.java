package edu.simpledaoexample.dao;

import edu.simpledaoexample.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserJdbcDaoTest {
    UserDao userDao;
    User user1;
    User user2;
    @Before
    public void setUp() throws Exception {
        userDao = new UserJdbcDao();
        user1 = new User(0, "user1", "pass");
        user2 = new User(1, "user2", "pass");
    }

    @After
    public void tearDown() throws Exception {
        userDao.closeCon();
        userDao = null;
        user1 = null;
        user2 = null;
    }

    @Test
    public void insert() {
        userDao.insert(user1);
        userDao.insert(user2);
    }

    @Test
    public void getById() {
        userDao.getById(user1.getId());
    }

    @Test
    public void update() {
        user1.setUsername("new_userame");
        userDao.update(user1);
    }

    @Test
    public void delete() {
        userDao.delete(user1);
    }

    @Test
    public void getAll() {
        userDao.getAll().stream().forEach(System.out::println);
    }
}