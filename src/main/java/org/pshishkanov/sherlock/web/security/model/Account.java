package org.pshishkanov.sherlock.web.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * Created by pshishkanov on 21/04/15.
 */

public class Account {

    @Id
    private String id;

    private String username;

    @Transient
    private String password;

    private String passwordHash;

    private Role role;

    public Account() { }

    public Account(String username, String passwordHash, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
