package com.client.profile;

import com.client.ClientAdapter;
import com.client.SceneController;
import com.client.browser.BrowserProductController;
import com.client.product.ProductController;
import com.common.models.Product;
import com.common.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileController {
    @FXML
    protected Text loginTextField, firstnameTextField, lastnameTextField, phonenumberTextField, emailTextField, birthdateTextField;

    @FXML
    protected GridPane gridPane;

    @FXML
    protected Button goToBrowserButton;

    public static SceneController mediator;
    private ClientAdapter clientService = ClientAdapter.getInstance();

    private List<Product> products = new ArrayList<>();


    public void initialize() {

        products = clientService.getProductsByUserID(mediator.getUser().getId());
        showProducts();

        initializeFields();
        initializeButtons();
    }

    private void initializeButtons() {
        goToBrowserButton.setOnAction(event -> {
            goToBrowserButton.getScene().getWindow().hide();
            mediator.setStage(mediator.browserController, "browser.fxml");
        });
    }
    private void initializeFields() {

        User user = mediator.getUser();

        loginTextField.setText(user.getLogin());
        firstnameTextField.setText(user.getFirstname());
        lastnameTextField.setText(user.getLastname());
        phonenumberTextField.setText(user.getPhoneNumber());
        emailTextField.setText(user.getEmail());
        birthdateTextField.setText(user.getBirthDate().toString());
    }
    private void showProducts() {
        int rows = 0;
        Boolean checkFirst = true;
        gridPane.getChildren().clear();
        for (Product product : products) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("profileProduct.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProfileProductController productController = fxmlLoader.getController();
                productController.setData(product);
                anchorPane.setOnMouseEntered(mouseEvent -> {
                    anchorPane.setStyle("-fx-background-color: #efefef;");
                });
                anchorPane.setOnMouseExited(mouseEvent -> {
                    anchorPane.setStyle("-fx-background-color: WHITE;");
                });
                if (checkFirst) {
                    gridPane.add(anchorPane, 0, rows);
                    checkFirst = false;
                }
                else {
                    gridPane.add(anchorPane, 1, rows++);
                    checkFirst = true;
                }
                GridPane.setMargin(anchorPane, new Insets(10, 0, 10, 0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
