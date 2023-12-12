package com.client.authorization.login;

import com.client.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class LoginController{

    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected Button logInButton, signUpButton;
    @FXML
    protected Line passwordLine, usernameLine;

    public static SceneController mediator;

    protected LoginModel loginModel;
    protected LoginView loginView;
    public void initialize() {
        loginModel = new LoginModel();
        loginView = new LoginView(this);
        loginModel.registerObserver(loginView);


        initializeButtons();
        initializeFields();
    }
    public void initializeFields() {
        passwordField.textProperty().addListener((observable) ->
                loginModel.setPassword(passwordField.getText()));
        usernameField.textProperty().addListener((observable) ->
                loginModel.setLogin(usernameField.getText()));
    }
    public void initializeButtons() {

        logInButton.setOnAction(event -> {
            if(loginModel.checkPassword()){
                signUpButton.getScene().getWindow().hide();
                mediator.setUser(loginModel.getUser());
                mediator.setStage(mediator.browserController, "browser.fxml");
            }
        });
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            mediator.setStage(mediator.registrationController, "registration.fxml");
        });

        logInButton.setOnMouseEntered(event -> {
            logInButton.setStyle("-fx-background-color: #ffac00; -fx-border-color: #ffac00; -fx-border-width: 3;");
            logInButton.setTextFill(Color.web("#ffffff"));
        });
        signUpButton.setOnMouseEntered(event -> {
            signUpButton.setStyle("-fx-background-color: #ffac00; -fx-border-color: #ffac00; -fx-border-width: 1;");
            signUpButton.setTextFill(Color.web("#ffffff"));
        });

        logInButton.setOnMouseExited(event -> {
            logInButton.setStyle("-fx-background-color: white; -fx-border-color: #ffac00; -fx-border-width: 3;");
            logInButton.setTextFill(Color.web("#ffac00"));
        });
        signUpButton.setOnMouseExited(event -> {
            signUpButton.setStyle("-fx-background-color: white; -fx-border-color: #ffac00; -fx-border-width: 1");
            signUpButton.setTextFill(Color.web("#ffac00"));
        });
    }
}
