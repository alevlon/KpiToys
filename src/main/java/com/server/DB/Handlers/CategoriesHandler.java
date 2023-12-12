package com.server.DB.Handlers;

import com.common.models.Category;
import com.common.models.Product;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesHandler implements Query{
    private Connection connection;
    public CategoriesHandler(Connection connection) {
        this.connection = connection;
    }


    public List<Category> getCategories() {
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(CATEGORIES_GET_ALL);
            return getCategoriesFromStatement(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Category getCategoryByName(String name) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(CATEGORIES_GET_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("idcategory"),
                        resultSet.getString("name"));
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private List<Category> getCategoriesFromStatement(PreparedStatement preparedStatement) throws SQLException {
        List<Category> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Category category = new Category(
                    resultSet.getInt("idcategory"),
                    resultSet.getString("name"));
            list.add(category);
        }
        return list;
    }
}
