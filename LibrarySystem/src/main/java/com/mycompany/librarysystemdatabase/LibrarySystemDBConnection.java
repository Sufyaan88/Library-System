package com.mycompany.librarysystemdatabase;

import java.sql.*;

public class LibrarySystemDBConnection {

    String dbURL = "jdbc:derby://localhost:1527/LibrarySystemDatabase";
    String username = "administrator";
    String password = "admin";
    public Connection con;

    public void establishConnection() {
        try {
            System.out.println("About To Get A Connection...");
            con = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connection Established Successfully...");
        } catch (SQLNonTransientConnectionException e) {
            System.out.println("ERROR: " + e);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void closeConnection() {
        try {
            System.out.println("About To Close Connection...");
            con.close();
            System.out.println("Connection Closed Successfully...");
        } catch (SQLException err) {
            System.out.println("ERROR: " + err);
        }
    }
}
