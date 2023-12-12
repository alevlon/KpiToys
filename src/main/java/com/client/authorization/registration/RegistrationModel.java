package com.client.authorization.registration;

import com.client.ClientAdapter;
import com.common.interfaces.HashAlgorithm;
import com.common.interfaces.Observable;
import com.common.interfaces.Observer;
import com.common.models.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RegistrationModel implements Observable, HashAlgorithm {
    List<Observer> observers;

    private String confirmPassword, password, email, firstname, lastname, phoneNumber, username;
    private User user;
    private ClientAdapter clientService = ClientAdapter.getInstance();

    public RegistrationModel() {
        observers = new LinkedList<>();
    }

    public boolean setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        if(confirmPassword.equals(password)) {
            notifyObservers("setCorrectConfirmPasswordStyle");
        } else {
            notifyObservers("setIncorrectConfirmPasswordStyle");
        }
        return confirmPassword.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyObservers("validatePassword");
    }

    public void setEmail(String email) {
        this.email = email;
        notifyObservers("validateEmail");
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        notifyObservers("validateFirstname");
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        notifyObservers("validateLastname");
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyObservers("validatePhone");
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers("validateUsername");
    }

    public User getUser() {
        user = clientService.getUser(username);
        if(user != null) {
            notifyObservers("setUserAlreadyExistStyle");
        }
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.notification(message));
    }
}
