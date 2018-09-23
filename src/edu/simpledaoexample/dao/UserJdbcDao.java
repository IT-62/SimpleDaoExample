package edu.simpledaoexample.dao;

import edu.simpledaoexample.entities.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserJdbcDao implements UserDao {
    private final String INSERT_NEW = "INSERT INTO users(username, password) VALUES(?, ?)";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String UPDATE = "UPDATE users SET username=?, password=? WHERE id=?";
    private final String DELETE = "DELETE FROM users where id=?";
    private final String SELECT_ALL = "SELECT * FROM users";
    private final String JDBC_URL = "jdbc:mysql://<host>:<port>/<db_name>";
    private final String USERNAME = "<username>";
    private final String PASSWORD = "<password>";
    private Connection connection;
    private PreparedStatement preparedStatement;
    public UserJdbcDao() {
        try {
            connection = DriverManager.getConnection(JDBC_URL,
                    USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User entity) {
        if(connection != null) {
            try {
                preparedStatement = connection.prepareStatement(INSERT_NEW);
                preparedStatement.setString(1, entity.getUsername());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public User getById(int id) {
        if(connection != null) {
            try {
                preparedStatement = connection.prepareStatement(GET_BY_ID);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String username = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    return new User(id, username, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void update(User entity) {
        if(connection != null) {
            try {
                preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setString(1, entity.getUsername());
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(User entity) {
        if(connection != null) {
            try {
                preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> res = new ArrayList<>();
        if(connection != null) {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL);
                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String userUsername = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);
                    res.add(new User(userId, userUsername, userPassword));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
