package com.jwt.domain;

import java.util.Arrays;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Role> roles;

    public User(String username, String password, Role... roles) {
        this.username = username;
        this.password = password;
        this.roles = Arrays.asList(roles);
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
