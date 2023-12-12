package com.server.DB;

import com.server.DB.Handlers.CategoriesHandler;
import com.server.DB.Handlers.ProductHandler;
import com.server.DB.Handlers.PurchaseHandler;
import com.server.DB.Handlers.UserHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends DBConfig {
    private Connection connection;

    public UserHandler userHandler;

    public ProductHandler productHandler;

    public CategoriesHandler categoriesHandler;

    public PurchaseHandler purchaseHandler;
    public DBHandler() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.connection = DriverManager.getConnection(url, dbUser, dbPass);

        userHandler = new UserHandler(this.connection);
        productHandler = new ProductHandler(this.connection);
        categoriesHandler = new CategoriesHandler(this.connection);
        purchaseHandler = new PurchaseHandler(this.connection);
    }
}
