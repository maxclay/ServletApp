package com.maxclay.model;

import javax.persistence.*;

/**
 * @author maxclay
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "Users";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  email: ").append(email).append("\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

}
