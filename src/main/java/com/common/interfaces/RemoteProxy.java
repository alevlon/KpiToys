package com.common.interfaces;

import com.common.models.Product;

import java.util.List;

public interface RemoteProxy {
    public String getUser(String login);
    public String getUser(int id);
    public String addUser(String serializeUser);
    public String updateUser(String serializeUser);
    public String getProducts(String empty);
    public String getCategories(String empty);
    public String getProductsByFilter(String title, String categoryName);
    public String insertPurchase(int idUser, int idProduct);
    public String getProductsByUserID(int idUser);
}
