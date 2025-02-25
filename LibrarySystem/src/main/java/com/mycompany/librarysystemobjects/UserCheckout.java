package com.mycompany.librarysystemobjects;

import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserCheckout implements LibrarySystemObject, Serializable {
    
    private String checkout_Id, user_Name, book_title, computer_Id, venue_Id;

    public UserCheckout(String checkout_Id, String user_Name, String book_title, String computer_Id, String venue_Id) {
        this.checkout_Id = checkout_Id;
        this.user_Name = user_Name;
        this.book_title = book_title;
        this.computer_Id = computer_Id;
        this.venue_Id = venue_Id;
    }

    @Override
    public void setDetails() {
    }

    public void setCheckout_Id(String checkout_Id) {
        this.checkout_Id = checkout_Id;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public void setComputer_Id(String computer_Id) {
        this.computer_Id = computer_Id;
    }

    public void setVenue_Id(String venue_Id) {
        this.venue_Id = venue_Id;
    }

    public String getCheckout_Id() {
        return checkout_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getBook_title() {
        return book_title;
    }

    public String getComputer_Id() {
        return computer_Id;
    }

    public String getVenue_Id() {
        return venue_Id;
    }

    @Override
    public void getDetails(DefaultTableModel tblModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteDetails(JTable tbl, DefaultTableModel dtm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
