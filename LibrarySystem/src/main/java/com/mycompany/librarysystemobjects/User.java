package com.mycompany.librarysystemobjects;

import com.mycompany.librarysystemdatabase.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class User implements LibrarySystemObject {

    public LibrarySystemDBConnection database = new LibrarySystemDBConnection();
    public LibrarySystemDAO sql = new LibrarySystemDAO();

    private String user_ID, name, address, department, email, phone_Number;
    private boolean venue_Booked, computer_Booked, gender, admin, lost;
    private int penalty, age;
    protected String password;

    public User() {
    }

    public User(String user_ID, String name, String address, String department, String email, String phone_Number, boolean venue_Booked, boolean computer_Booked, boolean gender, boolean admin, boolean lost, int penalty, int age, String password) {
        this.user_ID = user_ID;
        this.name = name;
        this.address = address;
        this.department = department;
        this.email = email;
        this.phone_Number = phone_Number;
        this.venue_Booked = venue_Booked;
        this.computer_Booked = computer_Booked;
        this.gender = gender;
        this.admin = admin;
        this.lost = lost;
        this.penalty = penalty;
        this.age = age;
        this.password = password;

        setDetails();
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public boolean isVenue_Booked() {
        return venue_Booked;
    }

    public void setVenue_Booked(boolean venue_Booked) {
        this.venue_Booked = venue_Booked;
    }

    public boolean isComputer_Booked() {
        return computer_Booked;
    }

    public void setComputer_Booked(boolean computer_Booked) {
        this.computer_Booked = computer_Booked;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setDetails() {
        database.establishConnection();
        sql.createUserStatementObj(database);
        sql.execUserInsertStatement(user_ID, venue_Booked, computer_Booked, penalty, name, age, address, department, email, phone_Number, gender, admin, lost, password);
        database.closeConnection();
    }

    @Override
    public void getDetails(DefaultTableModel tblModel) {
        database.establishConnection();
        sql.createUserRetrieveStatementObj(database);
        sql.execUserRetrieveStatement(tblModel);
        database.closeConnection();
    }

    @Override
    public void updateDetails() {
        database.establishConnection();
        sql.createUserUpdateStatementObj(database);
        sql.execUserUpdateStatement(user_ID, venue_Booked, computer_Booked, penalty, name, age, address, department, email, phone_Number, gender, admin, lost, password);
        database.closeConnection();
    }

    @Override
    public void deleteDetails(JTable tbl, DefaultTableModel dtm) {
        database.establishConnection();
        sql.getUserDeletionSelection(tbl, dtm);
        database.closeConnection();
    }

}
