package com.swad.test.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    
    private static final long serialVersionUID = 6593063193607142801L;
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
