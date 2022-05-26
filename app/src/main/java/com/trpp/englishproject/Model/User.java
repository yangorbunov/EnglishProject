package com.trpp.englishproject.Model;

/**
 * @author Yan Gorbunov
 * @version 1.0
 * Model class of users, which user name and password.
 */
public class User {
    String userName;
    String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
