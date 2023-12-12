package com.client;

import com.common.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

public class Client extends Application{
    private SceneController sceneController = new SceneController();
    public static void main(String[] args) throws XmlRpcException {
        ClientAdapter clientAdapter = ClientAdapter.getInstance();

        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        sceneController.setStage(sceneController.loginController, "login.fxml");
    }
}
