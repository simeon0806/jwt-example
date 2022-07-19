package com.jwt.repo;

import com.jwt.domain.Role;
import com.jwt.domain.constants.RoleConstants;
import com.jwt.domain.User;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {

    protected final static List<User> users = new ArrayList<>(20);

    protected final static Role user = new Role(RoleConstants.ROLE_USER);
    protected final static Role admin = new Role(RoleConstants.ROLE_ADMIN);

}
