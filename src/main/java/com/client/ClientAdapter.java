package com.client;

import com.client.services.ClientService;
import com.common.interfaces.AlgSerialize;
import com.common.interfaces.Service;
import com.common.models.Category;
import com.common.models.Product;
import com.common.models.User;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class ClientAdapter implements AlgSerialize, Service {
    private static ClientAdapter clientAdapter;
    private ClientService clientService;

    private ClientAdapter(ClientService clientService) {
        this.clientService = clientService;
    }

    public static ClientAdapter getInstance() { // singleton
        if (clientAdapter == null) {
            XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();
            try {
                xmlRpcClientConfig.setServerURL(new URL("http://localhost/3000"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            XmlRpcClient client = new XmlRpcClient(); // client xmlrpc
            client.setConfig(xmlRpcClientConfig);

            clientAdapter = new ClientAdapter(new ClientService(client));
        }

        return clientAdapter;
    }

    @Override
    public User getUser(String login) {
        return (User) deserialize(clientService.getUser(login));
    }

    @Override
    public User getUser(int id) {
        return (User) deserialize(clientService.getUser(id));
    }

    @Override
    public void updateUser(User user) {
        clientService.updateUser(serialize(user));
    }

    @Override
    public void addUser(User user) {
        clientService.addUser(serialize(user));
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>) deserialize(clientService.getProducts("Empty"));
    }

    @Override
    public List<Category> getCategories() {return (List<Category>) deserialize(clientService.getCategories("Empty")); }

    @Override
    public List<Product> getProductsByFilter(String title, String categoryName) {
        return (List<Product>) deserialize(clientService.getProductsByFilter(title, categoryName));
    }

    @Override
    public boolean insertPurchase(int idUser, int idProduct) {
        return (boolean) deserialize(clientService.insertPurchase(idUser, idProduct));
    }

    @Override
    public List<Product> getProductsByUserID(int idUser) {
        return (List<Product>) deserialize(clientService.getProductsByUserID(idUser));
    }
}
