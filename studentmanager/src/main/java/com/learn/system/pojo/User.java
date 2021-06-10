package com.learn.system.pojo;

public class User {

    Integer id;
    String username;
    String password;
    Integer authority;

    public User(Integer id, String username, String password, Integer authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}
