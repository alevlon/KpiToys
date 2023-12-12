package com.common.models;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private Date createdAt;

    public User(String login, String password, String firstname, String lastname, String email, String phoneNumber, Date birthDate) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "login='" + login + '\'' + "\n" +
                "password='" + password + '\'' + "\n" +
                "firstname='" + firstname + '\'' + "\n" +
                "lastname='" + lastname + '\'' + "\n" +
                "email='" + email + '\'' + "\n" +
                "phoneNumber='" + phoneNumber + '\'' + "\n" +
                "birthDate=" + birthDate + "\n" +
                "createdAt=" + createdAt + "\n";
    }
}
