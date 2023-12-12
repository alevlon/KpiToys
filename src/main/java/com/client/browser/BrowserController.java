package com.client.browser;

import com.client.ClientAdapter;
import com.client.SceneController;
import com.client.product.ProductController;
import com.common.models.Category;
import com.common.models.Product;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BrowserController {

    @FXML
    protected TextField searchTextField;

    @FXML
    protected Button myProfileButton, searchButton;

    @FXML
    protected GridPane gridPane;

    @FXML
    protected ComboBox categoriesComboBox;

    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public static SceneController mediator;
    private ClientAdapter clientService = ClientAdapter.getInstance();


    public void initialize() {

        products = clientService.getProducts();
        showProducts();

        categories = clientService.getCategories();

        ObservableList<String> langs = FXCollections.observableArrayList(categories.stream().map(t -> t.getName()).collect(Collectors.toList()));
        categoriesComboBox.setItems(langs);

        initializeButtons();
    }
    private void initializeButtons() {

        myProfileButton.setOnAction(event -> {
            myProfileButton.getScene().getWindow().hide();
            mediator.setStage(mediator.profileController, "profile.fxml");
        });

        searchButton.setOnAction(event -> {
            products.clear();

            String category = categoriesComboBox.getValue() != null ? categoriesComboBox.getValue().toString() : null;
            products.addAll(clientService.getProductsByFilter(searchTextField.getText(), category));
            showProducts();
        });
    }

    private void showProducts() {
        int rows = 0;
        Boolean checkFirst = true;
        gridPane.getChildren().clear();
        for (Product product : products) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("browserProduct.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                BrowserProductController productController = fxmlLoader.getController();
                productController.setData(product);
                anchorPane.setOnMouseEntered(mouseEvent -> {
                    anchorPane.setStyle("-fx-background-color: #efefef;");
                });
                anchorPane.setOnMouseExited(mouseEvent -> {
                    anchorPane.setStyle("-fx-background-color: WHITE;");
                });
                anchorPane.setOnMouseClicked(mouseEvent -> {
                    searchButton.getScene().getWindow().hide();
                    ProductController.setProduct(product);
                    mediator.setStage(mediator.productController, "product.fxml");
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
