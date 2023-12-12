module com.kpitoys {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires com.almasb.fxgl.all;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires xmlrpc.client;
    requires xmlrpc.common;
    requires xmlrpc.server;
    requires org.apache.commons.codec;


    opens com.server to xmlrpc.client, xmlrpc.common, xmlrpc.server;
    opens com.server.services to xmlrpc.server;
    opens com.client to javafx.fxml;
    opens com.client.authorization.login to javafx.fxml;
    opens com.client.authorization.registration to javafx.fxml;
    opens com.client.browser to javafx.fxml;
    opens com.client.product to javafx.fxml;
    opens com.client.profile to javafx.fxml;
    opens com.common.interfaces to org.apache.commons.codec;


    exports com.server;
    exports com.client;
}