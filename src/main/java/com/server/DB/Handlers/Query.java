package com.server.DB.Handlers;

public interface Query {
    public static final String USER_GET_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String USER_GET_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String USER_INSERT = "INSERT INTO users(login, password, firstname, lastname, phoneNumber, " +
            "email, birthdate) VALUES (?,?,?,?,?,?,?)";
    public static final String USER_UPDATE = "UPDATE users SET password = ?, firstname = ?, lastname = ?, phoneNumber = ?, email = ?, birthdate = ? WHERE login = ?";
    public static final String PRODUCTS_GET_ALL = "SELECT * FROM products";
    public static final String PRODUCTS_GET_BY_FILTER = "SELECT * FROM products WHERE title LIKE '%%'";
    public static final String PRODUCTS_GET_BY_USER_ID = "SELECT idproducts, title, description, image, categoryID, price, quentity FROM kpitoys.products \n" +
            "INNER JOIN kpitoys.purchase ON kpitoys.products.idproducts = kpitoys.purchase.idproduct \n" +
            "INNER JOIN kpitoys.users ON kpitoys.users.idusers = kpitoys.purchase.iduser \n" +
            "WHERE kpitoys.users.idusers = ?;";
    public static final String CATEGORIES_GET_ALL = "SELECT * FROM categories";
    public static final String CATEGORIES_GET_BY_NAME = "SELECT * FROM categories WHERE name = ?";
    public static final String PURCHASE_INSERT_NEW = "INSERT INTO purchase (iduser, idproduct) VALUES (?, ?)";


}
