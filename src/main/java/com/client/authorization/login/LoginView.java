package com.client.authorization.login;

import com.common.interfaces.Observer;
import com.common.validation.Validator;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;


public class LoginView implements Observer {

    private LoginController loginController;
    public LoginView(LoginController loginController) {
        this.loginController = loginController;
    }
    @Override
    public void notification(String message) {
        switch (message) {
            case "validateLogin" ->
                    Validator.validateTextField(
                            loginController.usernameField,
                            loginController.usernameLine,
                            Validator.USERNAME_PATTERN);
            case "validatePassword" ->
                    Validator.validateTextField(
                            loginController.passwordField,
                            loginController.passwordLine,
                            Validator.PASSWORD_PATTERN);
            case "setCorrectLoginStyle" -> {
                loginController.usernameField.setStyle("-fx-prompt-text-fill: #ffac00; -fx-border-color: white; -fx-background-color: white;");
                loginController.usernameLine.setStroke(Color.valueOf("#ffac00"));
            }
            case "setIncorrectLoginStyle" -> {
                loginController.usernameField.clear();
                loginController.usernameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: white; -fx-background-color: white;");
                loginController.usernameField.setPromptText("User doesn't exists");
                loginController.usernameLine.setStroke(Color.RED);
            }
            case "setIncorrectPasswordStyle" -> {
                loginController.passwordField.clear();
                loginController.passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: white; -fx-background-color: white;");
                loginController.passwordField.setPromptText("Incorrect password");
                loginController.passwordLine.setStroke(Color.RED);
            }
        }
    }
}
