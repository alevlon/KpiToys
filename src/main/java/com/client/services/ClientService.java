package com.client.services;

import com.common.interfaces.RemoteProxy;
import com.common.models.Product;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ClientService implements RemoteProxy {
    private XmlRpcClient client;

    public ClientService(XmlRpcClient client) {
        this.client = client;
    }

    public String getUser(String login) {
        try {
            return (String) client.execute("SERVER.getUser", Collections.singletonList(login));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUser(int id) {
        try {
            return (String) client.execute("SERVER.getUser", Collections.singletonList(id));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateUser(String serializedUser) {
        try {
            client.execute("SERVER.updateUser", Collections.singletonList(serializedUser));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addUser(String serializedUser) {
        try {
            client.execute("SERVER.addUser", Collections.singletonList(serializedUser));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProducts(String empty) {
        try {
            return (String) client.execute("SERVER.getProducts", Collections.singletonList("Null"));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCategories(String empty) {
        try {
            return (String) client.execute("SERVER.getCategories", Collections.singletonList("Null"));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProductsByFilter(String title, String categoryName) {
        Vector vector = new Vector();
        vector.addElement(title == null ? "@null@" : title);
        vector.addElement(categoryName == null ? "@null@" : categoryName);
        try {
            return (String) client.execute("SERVER.getProductsByFilter", vector);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insertPurchase(int idUser, int idProduct) {
        Vector vector = new Vector();
        vector.addElement(idUser);
        vector.addElement(idProduct);
        try {
            return (String) client.execute("SERVER.insertPurchase", vector);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProductsByUserID(int idUser) {
        try {
            return (String) client.execute("SERVER.getProductsByUserID", Collections.singletonList(idUser));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }
}
