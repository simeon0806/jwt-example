package com.jwt.repo;

import com.jwt.domain.Role;
import com.jwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class Repo extends DataCenter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        saveUser("simeon", "simeon", user, admin);
        saveUser("test", "test", user);
    }

    public User findUserByUsername(String username) {
        return users.stream().filter(u -> username.equals(u.getUsername())).findAny().orElse(null);
    }

    public void saveUser(String username, String password, Role... roles) {
        users.add(new User(username, passwordEncoder.encode(password), roles));
    }

}
