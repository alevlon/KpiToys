package com.server.DB.Handlers;

import com.common.models.Category;
import com.common.models.Product;
import com.common.models.User;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductHandler implements Query {

    private Connection connection;
    private CategoriesHandler categoriesHandler;

    public ProductHandler(Connection connection) {
        categoriesHandler = new CategoriesHandler(connection);
        this.connection = connection;
    }


    public List<Product> getProducts() {
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(PRODUCTS_GET_ALL);
            return getProductsFromStatement(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Product> getProductsByFilter(String title, String categoryName) {
        try {
            Category category = categoriesHandler.getCategoryByName(categoryName);
            String statement = "SELECT * FROM products WHERE title LIKE '%" + title + "%'";
            if (category != null) statement += " AND categoryID = " + category.getId();
            PreparedStatement preparedStatement = this.connection.prepareStatement(statement);
            return getProductsFromStatement(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Product> getProductsByIdUser(int idUser) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(PRODUCTS_GET_BY_USER_ID);
            preparedStatement.setInt(1, idUser);
            return getProductsFromStatement(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private List<Product> getProductsFromStatement(PreparedStatement preparedStatement) throws SQLException {
        List<Product> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Product ad = new Product(
                    resultSet.getInt("idproducts"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    new Image(resultSet.getBinaryStream("image")),
                    resultSet.getInt("categoryID"),
                    resultSet.getInt("price"),
                    resultSet.getInt("quentity"));
            list.add(ad);
        }
        return list;
    }

}
