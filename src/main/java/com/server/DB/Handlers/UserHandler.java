package com.server.DB.Handlers;

import com.common.models.User;

import java.sql.*;

public class UserHandler implements Query{

    private Connection connection;

    public UserHandler(Connection connection) {
        this.connection = connection;
    }

    public User getUser(String login) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(USER_GET_BY_LOGIN);
            preparedStatement.setString(1, login);
            User user = getUserFromStatement(preparedStatement);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUser(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(USER_GET_BY_ID);
            preparedStatement.setInt(1, id);
            return getUserFromStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT);
            constructAddStatement(preparedStatement, user);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE);
            constructUpdateStatement(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getDate("birthdate"));
            user.setCreatedAt(resultSet.getDate("createdAt"));
            user.setId(resultSet.getInt("idusers"));
        }
        return user;
    }
    static void constructAddStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstname());
        preparedStatement.setString(4, user.getLastname());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setDate(7, user.getBirthDate());
    }
    static void constructUpdateStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getFirstname());
        preparedStatement.setString(3, user.getLastname());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setDate(6, user.getBirthDate());
        preparedStatement.setString(7, user.getLogin());
    }
}
