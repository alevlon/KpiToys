package com.client.authorization.login;

import com.client.ClientAdapter;
import com.client.SceneController;
import com.common.interfaces.HashAlgorithm;
import com.common.interfaces.Observable;
import com.common.interfaces.Observer;
import com.common.models.User;

import java.util.LinkedList;
import java.util.List;

public class LoginModel implements Observable, HashAlgorithm {
    private List<Observer> observers;
    private String password;

    private String login;

    private User user;

    public LoginModel() {
        observers = new LinkedList<>();
    }

    private ClientAdapter clientAdapter = ClientAdapter.getInstance();

    public void setPassword(String password) {
        this.password = password;
        notifyObservers("validatePassword");
    }
    public void setLogin(String login) {
        this.login = login;
        notifyObservers("validateLogin");
    }

    public boolean checkPassword() {
        getUser();
        boolean b = user.getPassword().equals(getHashCode(password));
        if(!b) {
            notifyObservers("setIncorrectPasswordStyle");
        }
        return b;
    }

    public User getUser() {
        user = clientAdapter.getUser(login);
        if(user != null) {
            notifyObservers("setCorrectLoginStyle");
        } else {
            notifyObservers("setIncorrectLoginStyle");
        }

        return user;
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
