package com.client;

import com.client.authorization.login.LoginController;
import com.client.authorization.registration.RegistrationController;
import com.client.browser.BrowserController;
import com.client.product.ProductController;
import com.client.profile.ProfileController;
import com.common.interfaces.HashAlgorithm;
import com.common.interfaces.Mediator;
import com.common.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneController implements Mediator, HashAlgorithm {

    private Stage stage;
    private static Scene scene;
    private User user;


    public LoginController loginController = new LoginController();
    public RegistrationController registrationController = new RegistrationController();
    public BrowserController browserController = new BrowserController();
    public ProductController productController = new ProductController();
    public ProfileController profileController = new ProfileController();

    public SceneController() {
        loginController.mediator = this;
        registrationController.mediator = this;
        browserController.mediator = this;
        productController.mediator = this;
        profileController.mediator = this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Object space, String filePath) {
        FXMLLoader fxmlLoader = new FXMLLoader(space.getClass().getResource(filePath));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
//        stage.getIcons().add(new Image("W:\\KPI\\KpiToys\\src\\main\\resources\\images\\main_icon_big.png"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Kpi Toys");
        stage.show();
    }
}
