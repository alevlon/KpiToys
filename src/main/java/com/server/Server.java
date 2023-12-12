package com.server;

import com.common.interfaces.AlgSerialize;
import com.common.models.Category;
import com.server.DB.DBHandler;
import com.server.services.ServerService;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.*;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;


public class Server {
    public static void main(String[] args)  {

        WebServer webServer = new WebServer(80);

        try {
            DBHandler dbHandler = new DBHandler();
            ServerAdapter.serverService = new ServerService(dbHandler);

            PropertyHandlerMapping propertyHandlerMapping = new PropertyHandlerMapping();

            try {

                propertyHandlerMapping.addHandler("SERVER", ServerAdapter.class);

            } catch (XmlRpcException e) {
                System.out.println("XmlRpcException.\n" + e.getMessage());
                e.printStackTrace();
            }

            webServer.getXmlRpcServer().setHandlerMapping(propertyHandlerMapping);

            webServer.start();

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
