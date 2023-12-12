package com.common.interfaces;

import com.common.models.Category;
import com.common.models.Product;
import com.common.models.User;

import java.io.*;
import java.util.Base64;
import java.util.List;

public interface Service {

    User getUser(String username);

    User getUser(int id);

    void addUser(User user);

    void updateUser(User user);

    List<Product> getProducts();

    List<Category> getCategories();

    List<Product> getProductsByFilter(String title, String companyName);
    boolean insertPurchase(int idUser, int idProduct);
    public List<Product> getProductsByUserID(int UserID);
}
