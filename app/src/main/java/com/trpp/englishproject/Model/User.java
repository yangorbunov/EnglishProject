package com.trpp.englishproject.Model;

public class User {
    private String userName;
    private String password;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.password = passWord;
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
