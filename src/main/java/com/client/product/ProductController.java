package com.client.product;

import com.client.ClientAdapter;
import com.client.SceneController;
import com.common.models.Product;
import com.common.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ProductController {
    @FXML
    protected Text headingField, priceField, usernameField;

    @FXML
    protected TextArea descriptionField;


    @FXML
    protected ImageView image;

    @FXML
    protected Button purchaseButton, goToBrowserButton;

    protected static Product product;

    protected User user;
    public static SceneController mediator;

    private ClientAdapter clientService = ClientAdapter.getInstance();

    public static void setProduct(Product product) {
        ProductController.product = product;
    }

    public void initialize() {
        image.setImage(product.getImage());

        initializeFields();
        initializeButtons();
    }
    private void initializeButtons() {

        goToBrowserButton.setOnAction(event -> {
            goToBrowserButton.getScene().getWindow().hide();
            mediator.setStage(mediator.browserController, "browser.fxml");
        });
        purchaseButton.setOnAction(actionEvent -> {
            clientService.insertPurchase(mediator.getUser().getId(), product.getId());
            goToBrowserButton.getScene().getWindow().hide();
            mediator.setStage(mediator.browserController, "browser.fxml");
        });

        purchaseButton.setOnMouseEntered(event -> {
            purchaseButton.setStyle("-fx-background-color: #ffac00; -fx-border-color: #ffac00; -fx-border-width: 3;");
            purchaseButton.setTextFill(Color.web("#ffffff"));
        });
        purchaseButton.setOnMouseEntered(event -> {
            purchaseButton.setStyle("-fx-background-color: #ffac00; -fx-border-color: #ffac00; -fx-border-width: 1;");
            purchaseButton.setTextFill(Color.web("#ffffff"));
        });
    }
    private void initializeFields() {
        usernameField.setText(mediator.getUser().getLogin());
        headingField.setText(product.getTitle());
        priceField.setText((int)product.getPrice() + " грн.");
        descriptionField.setText(product.getDescription());
    }
}
