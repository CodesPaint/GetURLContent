/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    Connection connection;
    String url = "jdbc:postgresql://localhost:5432/geturlcontent";
    String user = "postgres";
    String password = "31";

    public Connection dbConnection() {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.getMessage();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected!!");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed To Connect");
        }

        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        db.dbConnection();
    }
}
