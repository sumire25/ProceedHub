package com.mistysoft.proceedhub.modules.participation.domain;

import lombok.Getter;

import java.util.Set;

@Getter
public class User {
    private final UserId id;
    private final String username;
    private final String password;
    private final Set<Role> roles;

    public User(UserId id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
