package com.tbagrel1.gmd.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class MysqlAssistant {
    private static final String url = "jdbc:mysql://neptune.telecomnancy.univ-lorraine.fr:3306/gmd";
    private static final String user = "gmd-read";
    private static final String password = "esial";

    public static void withConnection(Consumer<Connection> func) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("-- Connection established");
            func.accept(connection);
        } catch (SQLException e) {
            System.out.println("Unable to establish the connection: ");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("-- Connection closed");
                } catch (SQLException e) {
                    System.out.println("Unable to close the connection!");
                }
            }
        }
    }
}
