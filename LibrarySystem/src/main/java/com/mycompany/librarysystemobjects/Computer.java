package com.mycompany.librarysystemobjects;

import com.mycompany.librarysystemdatabase.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Computer implements LibrarySystemObject {

    public LibrarySystemDBConnection database = new LibrarySystemDBConnection();
    public LibrarySystemDAO sql = new LibrarySystemDAO();

    private String computer_number, brand, colour, mac_address, condition;
    private boolean available;

    public Computer() {
    }

    public Computer(String computer_number, String brand, String colour, String mac_address, String condition,
            boolean available) {

        this.available = available;
        this.brand = brand;
        this.colour = colour;
        this.computer_number = computer_number;
        this.condition = condition;
        this.mac_address = mac_address;

        setDetails();

    }

    public String getComputer_number() {
        return computer_number;
    }

    public void setComputer_number(String computer_number) {
        this.computer_number = computer_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void setDetails() {
        database.establishConnection();
        sql.createComputerStatementObj(database);
        sql.execComputerInsertStatement(computer_number, brand, colour, mac_address, condition, available);
        database.closeConnection();
    }

    public void getDetails(DefaultTableModel tblModel) {
        database.establishConnection();
        sql.createComputerRetrieveStatementObj(database);
        sql.execComputerRetrieveStatement(tblModel);
        database.closeConnection();
    }

    @Override
    public void updateDetails() {
        database.establishConnection();
        sql.createComputerUpdateStatementObj(database);
        sql.execComputerUpdateStatement(computer_number, brand, colour, mac_address, condition, available);
        database.closeConnection();
    }

    @Override
    public void deleteDetails(JTable tbl, DefaultTableModel dtm) {
        database.establishConnection();
        sql.getComputerDeletionSelection(tbl, dtm);
        database.closeConnection();
    }

}
