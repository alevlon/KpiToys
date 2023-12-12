package com.client.authorization.registration;

import com.client.authorization.login.LoginController;
import com.common.interfaces.Observable;
import com.common.interfaces.Observer;
import com.common.validation.Validator;
import javafx.scene.paint.Color;

public class RegistrationView implements Observer {
    private RegistrationController registrationController;
    public RegistrationView(RegistrationController registrationController) {
        this.registrationController = registrationController;
    }

    @Override
    public void notification(String message) {
        switch (message) {
            case "validateUsername" ->
                    Validator.validateTextField(registrationController.usernameField,
                            registrationController.usernameLine, Validator.USERNAME_PATTERN);
            case "validatePassword" ->
                    Validator.validateTextField(registrationController.passwordField,
                            registrationController.passwordLine, Validator.PASSWORD_PATTERN);
            case "validateEmail" ->
                    Validator.validateTextField(registrationController.emailField,
                            registrationController.emailLine, Validator.EMAIL_PATTERN);
            case "validateFirstname" ->
                    Validator.validateTextField(registrationController.firstnameField,
                            registrationController.firstnameLine, Validator.FIRSTNAME_PATTERN);
            case "validateLastname" ->
                    Validator.validateTextField(registrationController.lastnameField,
                            registrationController.lastnameLine, Validator.LASTNAME_PATTERN);
            case "validatePhone" ->
                    Validator.validateTextField(registrationController.phoneNumberField,
                            registrationController.phoneNumberLine, Validator.PHONENUMBER_PATTERN);
            case "setIncorrectConfirmPasswordStyle" -> {
                registrationController.confirmPasswordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: white; -fx-background-color: white;");
                registrationController.confirmPasswordField.setPromptText("Passwords don't match");
                registrationController.confirmPasswordLine.setStroke(Color.RED);
            }
            case "setCorrectConfirmPasswordStyle" -> {
                registrationController.confirmPasswordField.setStyle("-fx-prompt-text-fill: #0078FF; -fx-border-color: white; -fx-background-color: white;");
                registrationController.confirmPasswordLine.setStroke(Color.valueOf("#ffac00"));
            }
            case "setUserAlreadyExistStyle" -> {
                registrationController.usernameField.clear();
                registrationController.usernameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: white; -fx-background-color: white;");
                registrationController.usernameField.setPromptText("Username is already in use");
                registrationController.usernameLine.setStroke(Color.RED);
            }
        }
    }
}
