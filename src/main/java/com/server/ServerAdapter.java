package com.server;

import com.common.interfaces.AlgSerialize;
import com.common.interfaces.RemoteProxy;
import com.common.models.User;
import com.server.services.ServerService;

public class ServerAdapter implements AlgSerialize, RemoteProxy {
    public static ServerService serverService;

    public String getUser(String login) {
        return serialize(serverService.getUser(login));
    }

    public String getUser(int id) {
        return serialize(serverService.getUser(id));
    }

    public String addUser(String serializeUser) {
        serverService.addUser((User) deserialize(serializeUser));
        return "null";
    }

    public String updateUser(String serializeUser) {
        serverService.updateUser((User) deserialize(serializeUser));
        return "null";
    }

    public String getProducts(String empty) {
        return serialize(serverService.getProducts());
    }

    public String getCategories(String empty) {
        return serialize(serverService.getCategories());
    }

    @Override
    public String getProductsByFilter(String title, String categoryName) {
        if(title.equals("@null@")) { title = null;}
        if(categoryName.equals("@null@")) { categoryName = null;}

        return serialize(serverService.getProductsByFilter(title, categoryName));
    }

    @Override
    public String insertPurchase(int idUser, int idProduct) {
        return serialize(serverService.insertPurchase(idUser, idProduct));
    }

    @Override
    public String getProductsByUserID(int idUser) {
        return serialize(serverService.getProductsByUserID(idUser));
    }
}
