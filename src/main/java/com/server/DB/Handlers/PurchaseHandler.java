package com.server.DB.Handlers;

import com.common.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PurchaseHandler implements Query{
    private Connection connection;
    public PurchaseHandler(Connection connection) {
        this.connection = connection;
    }

    public boolean insertPurchase(int idUser, int idProduct) {
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(PURCHASE_INSERT_NEW);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idProduct);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
