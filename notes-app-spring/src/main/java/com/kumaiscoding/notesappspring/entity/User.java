package com.kumaiscoding.notesappspring.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Not;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "enabled")
    int enabled;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Note> notes;

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
