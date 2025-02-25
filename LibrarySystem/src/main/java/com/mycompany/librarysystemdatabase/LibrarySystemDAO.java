package com.mycompany.librarysystemdatabase;

import com.mycompany.librarysystem.EditAll;
import com.mycompany.librarysystemobjects.*;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.*;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import com.mycompany.librarysystem.*;
import java.io.*;

public class LibrarySystemDAO {

    Statement statement;
    PreparedStatement prepStatement;
    LibrarySystemDBConnection database;

    private void createStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            System.out.println("Creating Statement object...");
            statement = database.con.createStatement();
            System.out.println("Statement object created successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void addBlankRow(DefaultTableModel dftModel) {
        try {
            Object[] blankrow = new Object[dftModel.getRowCount()];
            dftModel.addRow(blankrow);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

//--------------------------------------------------Book Section--------------------------------------------------
    public void createBookStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "INSERT INTO Book (Book_ID, Title, Genre, Condition, Author, Publisher, Publication_Year, Available, Description)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("Creating Book Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Book Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createBookTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    private void createBookTable() {
        try {
            createStatementObj(database);
            System.out.println("Creating Book Table...");
            statement.executeUpdate("create table Book (Book_ID VARCHAR(50) PRIMARY KEY, Title VARCHAR(50), Genre VARCHAR(50), Condition VARCHAR(50), Author VARCHAR(50), Publisher VARCHAR(50), Publication_Year INT, Available BOOLEAN, Description VARCHAR(50))");    //ADD COLUMNS
            System.out.println("Book Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execBookInsertStatement(String book_ID, String book_Title, String book_Genre, String book_Condition, String book_Author, String book_Publisher, int publication_Year, boolean isAvailable, String book_Description) {
        try {
            createBookStatementObj(database);
            prepStatement.setString(1, book_ID);
            prepStatement.setString(2, book_Title);
            prepStatement.setString(3, book_Genre);
            prepStatement.setString(4, book_Condition);
            prepStatement.setString(5, book_Author);
            prepStatement.setString(6, book_Publisher);
            prepStatement.setInt(7, publication_Year);
            prepStatement.setBoolean(8, isAvailable);
            prepStatement.setString(9, book_Description);
            System.out.println("Executing insert...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Save was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A book with this Book_ID already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createBookTable();
            execBookInsertStatement(book_ID, book_Title, book_Genre, book_Condition, book_Author, book_Publisher, publication_Year, isAvailable, book_Description);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void createBookRetrieveStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "SELECT * FROM Book";
            System.out.println("Creating Book Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Book Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createBookTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createBookUpdateStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "UPDATE Book SET title=?, Genre=?, Condition=?, Author=?, Publisher=?, publication_Year=?, Available=?, Description=? WHERE Book_ID = ?";
            System.out.println("Creating Book Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Book Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execBookRetrieveStatement(DefaultTableModel tblModel) {
        try {
            createBookRetrieveStatementObj(database);
            System.out.println("Executing query...");
            ResultSet results = prepStatement.executeQuery();
            System.out.println("Query was successful...");

            //get meta data to fetch column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing data and columns in the table model
            tblModel.setRowCount(0);
            tblModel.setColumnCount(0);

            //set column names
            for (int i = 1; i <= columnCount; i++) {
                tblModel.addColumn(metaData.getColumnName(i));
                System.out.println("Column names set");
            }
            //set rows data
            while (results.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = results.getObject(i);
                }
                tblModel.addRow(rowData);
                System.out.println("Row data added");
            }

        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execBookUpdateStatement(String book_ID, String book_Title, String book_Genre, String book_Condition, String book_Author, String book_Publisher, int publication_Year, boolean isAvailable, String book_Description) {
        try {
            createBookUpdateStatementObj(database);
            prepStatement.setString(1, book_Title);
            prepStatement.setString(2, book_Genre);
            prepStatement.setString(3, book_Condition);
            prepStatement.setString(4, book_Author);
            prepStatement.setString(5, book_Publisher);
            prepStatement.setInt(6, publication_Year);
            prepStatement.setBoolean(7, isAvailable);
            prepStatement.setString(8, book_Description);
            prepStatement.setString(9, book_ID);
            System.out.println("Executing query...");
            prepStatement.executeUpdate();
            System.out.println("Query was successful...");
            JOptionPane.showMessageDialog(null, "Update was successful");

        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execBookDeleteStatement(LibrarySystemDBConnection database, String id) {
        try {
            this.database = database;
            String sql = "DELETE from Book WHERE Book_ID = ?";
            System.out.println("Creating Book Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Book Statement object created successfully...");
            prepStatement.setString(1, id);
            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Record deletion unsuccessful");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getBookDeletionSelection(JTable tblBooks, DefaultTableModel dtmBooks) {
        int[] selectedRows = tblBooks.getSelectedRows();
        Arrays.sort(selectedRows);
        System.out.println("Rows successfully sorted");
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int rows = selectedRows[i];
            String id = (String) dtmBooks.getValueAt(rows, 0);
            System.out.println("ID has been retrieved");
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + id,
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                execBookDeleteStatement(database, id);
                System.out.println("Record Deleted Successfully");
                dtmBooks.removeRow(rows);
            }
        }

    }
//--------------------------------------------------Computer Section--------------------------------------------------

    public void createComputerStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "INSERT INTO Computer (Computer_Number, Brand, Colour, MAC_Address, Condition ,Available)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            System.out.println("Creating Computer Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Computer Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createComputerTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createComputerUpdateStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "UPDATE COMPUTER SET  Brand=?, Colour=?, MAC_Address=?, Condition=?, Available=?  WHERE Computer_Number = ?";
            System.out.println("Creating Computer Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Computer Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createComputerRetrieveStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "SELECT * FROM Computer";
            System.out.println("Creating Computer Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Computer Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createComputerTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    private void createComputerTable() {
        try {
            createStatementObj(database);
            System.out.println("Creating Computer Table...");
            statement.executeUpdate("create table Computer (Computer_Number VARCHAR(50) PRIMARY KEY, Brand VARCHAR(50), Colour VARCHAR(50), MAC_Address VARCHAR(12), Condition VARCHAR(50), Available BOOLEAN)");    //ADD COLUMNS
            System.out.println("Computer Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execComputerInsertStatement(String computer_Number, String computer_Brand, String computer_Colour, String mac_Address, String Condition, boolean isAvailable) {
        try {
            createComputerStatementObj(database);
            prepStatement.setString(1, computer_Number);
            prepStatement.setString(2, computer_Brand);
            prepStatement.setString(3, computer_Colour);
            prepStatement.setString(4, mac_Address);
            prepStatement.setString(5, Condition);
            prepStatement.setBoolean(6, isAvailable);
            System.out.println("Executing insert...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Save was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A computer with this Computer_Number already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createComputerTable();
            execComputerInsertStatement(computer_Number, computer_Brand, computer_Colour, mac_Address, Condition, isAvailable);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execComputerUpdateStatement(String computer_Number, String computer_Brand, String computer_Colour, String mac_Address, String Condition, boolean isAvailable) {
        try {
            createComputerUpdateStatementObj(database);
            prepStatement.setString(1, computer_Brand);
            prepStatement.setString(2, computer_Colour);
            prepStatement.setString(3, mac_Address);
            prepStatement.setString(4, Condition);
            prepStatement.setBoolean(5, isAvailable);
            prepStatement.setString(6, computer_Number);
            System.out.println("Executing update...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Update was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A computer with this Computer_Number already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createComputerTable();
            execComputerInsertStatement(computer_Number, computer_Brand, computer_Colour, mac_Address, Condition, isAvailable);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execComputerRetrieveStatement(DefaultTableModel tblModel) {
        try {
            createComputerRetrieveStatementObj(database);
            System.out.println("Executing query...");
            ResultSet results = prepStatement.executeQuery();
            System.out.println("Query was successful...");

            //get meta data to fetch column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing data and columns in the table model
            tblModel.setRowCount(0);
            tblModel.setColumnCount(0);

            //set column names
            for (int i = 1; i <= columnCount; i++) {
                tblModel.addColumn(metaData.getColumnName(i));
                System.out.println("Column names set");
            }
            //set rows data
            while (results.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = results.getObject(i);
                }
                tblModel.addRow(rowData);
                System.out.println("Row data added");
            }

        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execComputerDeleteStatement(LibrarySystemDBConnection database, String id) {
        try {
            this.database = database;
            String sql = "DELETE from Computer WHERE Computer_Number= ?";
            System.out.println("Creating Computer Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Computer Statement object created successfully...");
            prepStatement.setString(1, id);
            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Record deletion unsuccessful");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getComputerDeletionSelection(JTable tbl, DefaultTableModel dtm) {
        int[] selectedRows = tbl.getSelectedRows();
        Arrays.sort(selectedRows);
        System.out.println("Rows successfully sorted");
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int rows = selectedRows[i];
            String id = (String) dtm.getValueAt(rows, 0);
            System.out.println("ID has been retrieved");
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + id,
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                execComputerDeleteStatement(database, id);
                System.out.println("Record Deleted Successfully");
                dtm.removeRow(rows);
            }
        }

    }

//--------------------------------------------------User Section--------------------------------------------------
    public void createUserStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "INSERT INTO Users (User_ID, Venue_Booked, Computer_Booked, Penalty, Name, Age, Address, Department, Email, Phone_Number, Gender, Admin, Lost, Password)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("Creating User Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("User object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createUserTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createUserUpdateStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "UPDATE USERS SET Venue_Booked=?, Computer_Booked=?, Penalty=?, Name=?, Age=?, Address=?, Department=?, Email=?, Phone_Number=?, Gender=?, Admin=?, Lost=?, Password=? WHERE User_ID=?";
            System.out.println("Creating User Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("User Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("SQL Syntax Error: " + e.toString());
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void execUserUpdateStatement(String userId, boolean venueBooked, boolean computerBooked, int penalty, String name, int age, String address, String department, String email, String phoneNumber, boolean gender, boolean admin, boolean lost, String password) {
        try {
            createUserUpdateStatementObj(database); // Updated method for users
            prepStatement.setBoolean(1, venueBooked);
            prepStatement.setBoolean(2, computerBooked);
            prepStatement.setInt(3, penalty);
            prepStatement.setString(4, name);
            prepStatement.setInt(5, age);
            prepStatement.setString(6, address);
            prepStatement.setString(7, department);
            prepStatement.setString(8, email);
            prepStatement.setString(9, phoneNumber);
            prepStatement.setBoolean(10, gender);
            prepStatement.setBoolean(11, admin);
            prepStatement.setBoolean(12, lost);
            prepStatement.setString(13, password);
            prepStatement.setString(14, userId); // User_ID for the WHERE clause

            System.out.println("Executing update...");
            prepStatement.executeUpdate();
            System.out.println("Update was successful...");
            JOptionPane.showMessageDialog(null, "Update was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A user with this User_ID already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createUserTable(); // Method to create the USERS table if needed
            execUserInsertStatement(userId, venueBooked, computerBooked, penalty, name, age, address, department, email, phoneNumber, gender, admin, lost, password); // Insert method if the user doesn't exist
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void createUserRetrieveStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "SELECT * FROM Users";
            System.out.println("Creating User Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("User Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            System.out.println(e);
            createUserTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    private void createUserTable() {
        try {
            createStatementObj(database);
            System.out.println("Creating User Table...");
            statement.executeUpdate("CREATE TABLE USERS (User_ID VARCHAR(50) PRIMARY KEY, Venue_Booked BOOLEAN, Computer_Booked BOOLEAN, Penalty INT, Name VARCHAR(50), Age INT, Address VARCHAR(50), Department VARCHAR(50), Email VARCHAR(50), Phone_Number VARCHAR(50), Gender BOOLEAN, Admin BOOLEAN, Lost BOOLEAN, Password VARCHAR(50))");    //ADD COLUMNS
            System.out.println("User Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execUserInsertStatement(String user_ID, boolean venue_Booked, boolean computer_Booked, int penalty, String user_Name, int user_Age, String user_Address, String department, String user_Email, String user_Phone_Number, boolean isMale, boolean isAdmin, boolean hasLostItem, String user_Password) {
        try {
            createUserStatementObj(database);
            prepStatement.setString(1, user_ID);
            prepStatement.setBoolean(2, venue_Booked);
            prepStatement.setBoolean(3, computer_Booked);
            prepStatement.setInt(4, penalty);
            prepStatement.setString(5, user_Name);
            prepStatement.setInt(6, user_Age);
            prepStatement.setString(7, user_Address);
            prepStatement.setString(8, department);
            prepStatement.setString(9, user_Email);
            prepStatement.setString(10, user_Phone_Number);
            prepStatement.setBoolean(11, isMale);
            prepStatement.setBoolean(12, isAdmin);
            prepStatement.setBoolean(13, hasLostItem);
            prepStatement.setString(14, user_Password);
            System.out.println("Executing insert...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Save was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A user with this User_ID already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createUserTable();
            execUserInsertStatement(user_ID, venue_Booked, computer_Booked, penalty, user_Name, user_Age, user_Address, department, user_Email, user_Phone_Number, isMale, isAdmin, hasLostItem, user_Password);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execUserRetrieveStatement(DefaultTableModel tblModel) {
        try {
            createUserRetrieveStatementObj(database);
            System.out.println("Executing query...");
            ResultSet results = prepStatement.executeQuery();
            System.out.println("Query was successful...");

            //get meta data to fetch column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing data and columns in the table model
            tblModel.setRowCount(0);
            tblModel.setColumnCount(0);

            //set column names
            for (int i = 1; i <= columnCount; i++) {
                tblModel.addColumn(metaData.getColumnName(i));
                System.out.println("Column names set");
            }
            //set rows data
            while (results.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = results.getObject(i);
                }
                tblModel.addRow(rowData);
                System.out.println("Row data added");
            }

        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execUserDeleteStatement(LibrarySystemDBConnection database, String id) {
        try {
            this.database = database;
            String sql = "DELETE from Users WHERE User_ID = ?";
            System.out.println("Creating Users Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Users Statement object created successfully...");
            prepStatement.setString(1, id);
            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Record deletion unsuccessful");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getUserDeletionSelection(JTable tbl, DefaultTableModel dtm) {
        int[] selectedRows = tbl.getSelectedRows();
        Arrays.sort(selectedRows);
        System.out.println("Rows successfully sorted");
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int rows = selectedRows[i];
            String id = (String) dtm.getValueAt(rows, 0);
            System.out.println("ID has been retrieved");
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + id,
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                execUserDeleteStatement(database, id);
                System.out.println("Record Deleted Successfully");
                dtm.removeRow(rows);
            }
        }
    }

//--------------------------------------------------UserCheckout Section--------------------------------------------------
    public void createUserCheckoutStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "INSERT INTO User_Checkout (Checkout_Number, User_ID, Book_Item, Book_Borrow_Duration, Venue_Item, Venue_Duration, Computer_Item, Computer_Duration)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("Creating User_Checkout Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("User_Checkout Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createUserCheckoutTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    private void createUserCheckoutTable() {
        try {
            createStatementObj(database);
            System.out.println("Creating User_Checkout Table...");
            statement.executeUpdate("create table User_Checkout (Checkout_Number VARCHAR(50) PRIMARY KEY, User_ID VARCHAR(50), Book_Item VARCHAR(50), Book_Borrow_Duration INTEGER, Venue_Item VARCHAR(50), Venue_Duration INTEGER, Computer_Item VARCHAR(50), Computer_Duration INTEGER)");    //ADD COLUMNS
            System.out.println("User_Checkout Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execUserCheckoutInsertStatement(String checkout_Number, String user_ID, String book_Item, int book_Borrow_Duration, String venue_Item, int venue_Duration, String computer_Item, int computer_Duration) {
        try {
            createUserCheckoutStatementObj(database);
            prepStatement.setString(1, checkout_Number);
            prepStatement.setString(2, user_ID);
            prepStatement.setString(3, book_Item);
            prepStatement.setInt(4, book_Borrow_Duration);
            prepStatement.setString(5, venue_Item);
            prepStatement.setInt(6, venue_Duration);
            prepStatement.setString(7, computer_Item);
            prepStatement.setInt(8, computer_Duration);
            System.out.println("Executing insert...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A checkout with this checkout_Number already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createUserCheckoutTable();
            execUserCheckoutInsertStatement(checkout_Number, user_ID, book_Item, book_Borrow_Duration, venue_Item, venue_Duration, computer_Item, computer_Duration);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void validateLogin(String username, String password) {
        LibrarySystemDBConnection db = new LibrarySystemDBConnection();
        ResultSet rs = null;
        boolean exists = false;
        String name = null;
        ObjectOutputStream out;
        ObjectInputStream in = null;

        try {
            db.establishConnection();
            String sql = "Select Admin, Name From Users Where User_Id = ? And Password = ?";
            prepStatement = db.con.prepareStatement(sql);
            System.out.println("Preparing statement...");
            prepStatement.setString(1, username);
            prepStatement.setString(2, password);
            rs = prepStatement.executeQuery();
            System.out.println("Result set retrieved");
            if (rs.next()) {
                exists = true;
                name = rs.getString("Name");
                boolean isAdmin = rs.getBoolean("Admin");
                if (isAdmin) {
                    System.out.println("Redirect to Edit All");
                    JOptionPane.showMessageDialog(null, "Welcome Admin User: " + username);
                    RunLibrarySystem.frame.adminEventHandler();
                } else {
                    System.out.println("Redirect to View All");
                    JOptionPane.showMessageDialog(null, "Welcome User: " + username);
                    out = new ObjectOutputStream(new FileOutputStream("checkout.ser"));
                    out.writeObject(name + "\n");
                    out.close();
                    in = new ObjectInputStream(new FileInputStream("checkout.ser"));
                    RunLibrarySystem.frame.userEventHandler();
                    while (true) {
                        String read = (String) in.readObject();
                        JOptionPane.showMessageDialog(null, read);
                    }
                }
            }
            if (!exists) {
                System.out.println("Invalid login credentials");
                JOptionPane.showMessageDialog(null, "Invalid user credentials");
            }

        } catch (SQLException e) {
            System.out.println("Error logging in " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error class not found " + e);
        } finally {
            db.closeConnection();
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("IO Error " + e);
            }
        }
    }

//--------------------------------------------------Venue Section--------------------------------------------------
    public void createVenueStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "INSERT INTO Venue (Venue_Number, Capacity, Projector, Available)"
                    + "VALUES (?, ?, ?, ?)";
            System.out.println("Creating Venue Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Venue Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createVenueTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createVenueUpdateStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "UPDATE VENUE SET  Capacity=?, Projector=?, Available=? WHERE Venue_Number = ?";
            System.out.println("Creating Venue Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Computer Venue object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void createVenueRetrieveStatementObj(LibrarySystemDBConnection database) {
        try {
            this.database = database;
            String sql = "SELECT * FROM Venue";
            System.out.println("Creating Venue Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Venue Statement object created successfully...");
        } catch (SQLSyntaxErrorException e) {
            createVenueTable();
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    private void createVenueTable() {
        try {
            createStatementObj(database);
            System.out.println("Creating Venue Table...");
            statement.executeUpdate("create table Venue (Venue_Number VARCHAR(50) PRIMARY KEY, Capacity INT, Projector BOOLEAN, Available BOOLEAN)");    //ADD COLUMNS
            System.out.println("Venue Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execVenueInsertStatement(String venue_Number, int venue_Capacity, boolean hasProjector, boolean isAvailable) {
        try {
            createVenueStatementObj(database);
            prepStatement.setString(1, venue_Number);
            prepStatement.setInt(2, venue_Capacity);
            prepStatement.setBoolean(3, hasProjector);
            prepStatement.setBoolean(4, isAvailable);
            System.out.println("Executing insert...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Save was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A venue with this Venue_Number already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createVenueTable();
            execVenueInsertStatement(venue_Number, venue_Capacity, hasProjector, isAvailable);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public void execVenueUpdateStatement(String venue_Number, int venue_Capacity, boolean hasProjector, boolean isAvailable) {
        try {
            createVenueUpdateStatementObj(database);
            prepStatement.setInt(1, venue_Capacity);
            prepStatement.setBoolean(2, hasProjector);
            prepStatement.setBoolean(3, isAvailable);
            prepStatement.setString(4, venue_Number);
            System.out.println("Executing update...");
            prepStatement.executeUpdate();
            System.out.println("Insert was successful...");
            JOptionPane.showMessageDialog(null, "Update was successful");
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "A venue with this Venue_Number already exists");
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLSyntaxErrorException e) {
            createVenueTable();
            execVenueInsertStatement(venue_Number, venue_Capacity, hasProjector, isAvailable);
        } catch (SQLException e) {
            System.out.println("ERROR:" + e);;
        }
    }

    public Venue venues[] = new Venue[2];
    int i = 0;

    public void execVenueRetrieveStatement(DefaultTableModel tblModel) {
        try {
            createVenueRetrieveStatementObj(database);
            System.out.println("Executing query...");
            ResultSet results = prepStatement.executeQuery();
            System.out.println("Query was successful...");

            //get meta data to fetch column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing data and columns in the table model
            tblModel.setRowCount(0);
            tblModel.setColumnCount(0);

            //set column names
            for (int i = 1; i <= columnCount; i++) {
                tblModel.addColumn(metaData.getColumnName(i));
                System.out.println("Column names set");
            }
            //set rows data
            while (results.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = results.getObject(i);
                }
                tblModel.addRow(rowData);
                System.out.println("Row data added");
            }

        } catch (SQLException e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void execVenueDeleteStatement(LibrarySystemDBConnection database, String id) {
        try {
            this.database = database;
            String sql = "DELETE from Venue WHERE Venue_Number= ?";
            System.out.println("Creating Venue Statement object...");
            prepStatement = database.con.prepareStatement(sql);
            System.out.println("Venue Statement object created successfully...");
            prepStatement.setString(1, id);
            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Record deletion unsuccessful");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getVenueDeletionSelection(JTable tbl, DefaultTableModel dtm) {
        int[] selectedRows = tbl.getSelectedRows();
        Arrays.sort(selectedRows);
        System.out.println("Rows successfully sorted");
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int rows = selectedRows[i];
            String id = (String) dtm.getValueAt(rows, 0);
            System.out.println("ID has been retrieved");
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + id,
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                execVenueDeleteStatement(database, id);
                System.out.println("Record Deleted Successfully");
                dtm.removeRow(rows);
            }
        }

    }
}
