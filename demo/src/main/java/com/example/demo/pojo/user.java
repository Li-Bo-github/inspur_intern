package com.example.demo.pojo;

public class user {
    int id;
    String username;
    String password;
    int authority;
    public user(Integer id, String username, String password, Integer authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public user() {
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
