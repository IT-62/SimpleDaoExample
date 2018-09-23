package edu.simpledaoexample.entities;

public class User extends Base {
    private String username;
    private String password;
    public User(int id, String username, String password) {
        super(id);

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
