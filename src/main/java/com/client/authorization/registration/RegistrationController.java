package com.client.authorization.registration;

import com.client.ClientAdapter;
import com.client.SceneController;
import com.client.authorization.login.LoginModel;
import com.client.authorization.login.LoginView;
import com.common.models.User;
import com.common.validation.Validator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.sql.Date;

public class RegistrationController {
    @FXML
    protected Line birthDateLine, confirmPasswordLine, emailLine, firstnameLine, lastnameLine, passwordLine, phoneNumberLine, usernameLine;

    @FXML
    protected DatePicker birthDatePicker;

    @FXML
    protected PasswordField confirmPasswordField, passwordField;

    @FXML
    protected TextField emailField, firstnameField, lastnameField, phoneNumberField, usernameField;

    @FXML
    protected Button signUpButton, goToSignInButton;

    public static SceneController mediator;

    private ClientAdapter clientService = ClientAdapter.getInstance();

    protected RegistrationModel registrationModel;
    protected RegistrationView registrationView;

    public void initialize() {
        registrationModel = new RegistrationModel();
        registrationView = new RegistrationView(this);
        registrationModel.registerObserver(registrationView);

        initializeButtons();
        initializeFields();
    }

    private void initializeFields() {

        usernameField.textProperty().addListener((observable) ->
            registrationModel.setUsername(usernameField.getText()));

        passwordField.textProperty().addListener((observable) ->
            registrationModel.setPassword(passwordField.getText()));

        emailField.textProperty().addListener((observable) ->
            registrationModel.setEmail(emailField.getText()));

        firstnameField.textProperty().addListener((observable) ->
            registrationModel.setFirstname(firstnameField.getText()));

        lastnameField.textProperty().addListener((observable) ->
            registrationModel.setLastname(lastnameField.getText()));

        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            registrationModel.setPhoneNumber(phoneNumberField.getText());
        });
    }

    public void initializeButtons() {

        signUpButton.setOnAction(event -> {
            if(fieldsValidation() & registrationModel.getUser() == null) {
                clientService.addUser(new User(
                        registrationModel.getUsername(),
                        mediator.getHashCode(registrationModel.getPassword()),
                        registrationModel.getFirstname(),
                        registrationModel.getLastname(),
                        registrationModel.getEmail(),
                        registrationModel.getPhoneNumber(),
                        Date.valueOf(birthDatePicker.getValue())
                ));
                signUpButton.getScene().getWindow().hide();
                mediator.setStage(mediator.loginController, "login.fxml");
            }
        });

        signUpButton.setOnMouseEntered(event -> {
            signUpButton.setStyle("-fx-background-color: #ffac00; -fx-border-color: #ffac00; -fx-border-width: 3;");
            signUpButton.setTextFill(Color.web("#ffffff"));
        });

        signUpButton.setOnMouseExited(event -> {
            signUpButton.setStyle("-fx-background-color: white; -fx-border-color: #ffac00; -fx-border-width: 3");
            signUpButton.setTextFill(Color.web("#ffac00"));
        });

        goToSignInButton.setOnAction(event -> {
            goToSignInButton.getScene().getWindow().hide();
            mediator.setStage(mediator.loginController, "login.fxml");
        });
    }
    private boolean fieldsValidation() {

        return Validator.validateTextField(usernameField, usernameLine, Validator.USERNAME_PATTERN)
                & Validator.validateTextField(passwordField, passwordLine, Validator.PASSWORD_PATTERN)
                & Validator.validateTextField(emailField, emailLine, Validator.EMAIL_PATTERN)
                & Validator.validateTextField(phoneNumberField, phoneNumberLine, Validator.PHONENUMBER_PATTERN)
                & Validator.validateTextField(firstnameField, firstnameLine, Validator.FIRSTNAME_PATTERN)
                & Validator.validateTextField(lastnameField, lastnameLine, Validator.LASTNAME_PATTERN)
                & registrationModel.setConfirmPassword(confirmPasswordField.getText())
                & Validator.validationDatePicker(birthDatePicker, birthDateLine);
    }
}
