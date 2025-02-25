package com.mycompany.librarysystemobjects;

import com.mycompany.librarysystemdatabase.LibrarySystemDAO;
import com.mycompany.librarysystemdatabase.LibrarySystemDBConnection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Venue implements LibrarySystemObject {

    public LibrarySystemDBConnection database = new LibrarySystemDBConnection();
    public LibrarySystemDAO sql = new LibrarySystemDAO();

    private String venue_number;
    private int capacity;
    private boolean projector, available;

    public Venue() {
    }

    public Venue(String venue_number, int capacity, boolean projector, boolean available) {
        this.venue_number = venue_number;
        this.capacity = capacity;
        this.projector = projector;
        this.available = available;

        setDetails();
    }

    public String getVenueNumber() {

        return venue_number;
    }

    public void setVenueNumber(String venue_number) {
        this.venue_number = venue_number;
    }

    public int getCapacity() {

        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean hasProjector() {

        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
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
        sql.createVenueStatementObj(database);
        sql.execVenueInsertStatement(venue_number, capacity, projector, available);
        database.closeConnection();
    }

    public void getDetails(DefaultTableModel tblModel) {
        database.establishConnection();
        sql.createVenueRetrieveStatementObj(database);
        sql.execVenueRetrieveStatement(tblModel);
        database.closeConnection();
    }

    @Override
    public void updateDetails() {
        database.establishConnection();
        sql.createVenueUpdateStatementObj(database);
        sql.execVenueUpdateStatement(venue_number, capacity, projector, available);
        database.closeConnection();
    }

    @Override
    public void deleteDetails(JTable tbl, DefaultTableModel dtm) {
        database.establishConnection();
        sql.getVenueDeletionSelection(tbl, dtm);
        database.closeConnection();
    }

}
