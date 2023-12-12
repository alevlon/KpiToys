package com.server.services;

import com.common.interfaces.Service;
import com.common.models.Category;
import com.common.models.Product;
import com.common.models.User;
import com.server.DB.DBHandler;

import java.util.List;

public class ServerService implements Service {
    private static DBHandler dbHandler;

    public ServerService(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public User getUser(String login) {
        return dbHandler.userHandler.getUser(login);
    }

    @Override
    public User getUser(int id) {
        return dbHandler.userHandler.getUser(id);
    }

    @Override
    public void addUser(User user) {
        dbHandler.userHandler.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        dbHandler.userHandler.updateUser(user);
    }

    @Override
    public List<Product> getProducts() {
        return dbHandler.productHandler.getProducts();
    }

    @Override
    public List<Category> getCategories() {return dbHandler.categoriesHandler.getCategories(); }

    @Override
    public List<Product> getProductsByFilter(String title, String categoryName) {return dbHandler.productHandler.getProductsByFilter(title, categoryName);}

    @Override
    public boolean insertPurchase(int idUser, int idProduct) {return  dbHandler.purchaseHandler.insertPurchase(idUser, idProduct); }

    @Override
    public List<Product> getProductsByUserID(int idUser) { return dbHandler.productHandler.getProductsByIdUser(idUser); }
}
