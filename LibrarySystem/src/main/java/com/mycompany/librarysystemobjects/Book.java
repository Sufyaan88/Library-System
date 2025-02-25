package com.mycompany.librarysystemobjects;

import com.mycompany.librarysystemdatabase.LibrarySystemDAO;
import com.mycompany.librarysystemdatabase.LibrarySystemDBConnection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Book implements LibrarySystemObject {

    public LibrarySystemDBConnection database = new LibrarySystemDBConnection();
    public LibrarySystemDAO sql = new LibrarySystemDAO();

    private String book_ID, title, genre, condition, author, publisher, description;
    private int year;
    private boolean available;

    public Book(String book_ID, String title, String genre, String condition, String author, String publisher, boolean available, int year, String description) {
        this.book_ID = book_ID;
        this.title = title;
        this.genre = genre;
        this.condition = condition;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.available = available;
        this.description = description;
        setDetails();
    }

    public Book() {
    }

    public LibrarySystemDBConnection getDatabase() {
        return database;
    }

    public LibrarySystemDAO getSql() {
        return sql;
    }

    public String getBook_ID() {
        return book_ID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getCondition() {
        return condition;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public boolean getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public void setDatabase(LibrarySystemDBConnection database) {
        this.database = database;
    }

    public void setSql(LibrarySystemDAO sql) {
        this.sql = sql;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" + "database=" + database + ", sql=" + sql + ", book_ID=" + book_ID + ", title=" + title + ", genre=" + genre + ", condition=" + condition + ", author=" + author + ", publisher=" + publisher + ", year=" + year + ", available=" + available + ", description=" + description + '}';
    }

    @Override
    public void setDetails() {
        database.establishConnection();
        sql.createBookStatementObj(database);
        sql.execBookInsertStatement(book_ID, title, genre, condition, author, publisher, year, available, description);
        database.closeConnection();
    }

    @Override
    public void getDetails(DefaultTableModel tblModel) {
        database.establishConnection();
        sql.createBookRetrieveStatementObj(database);
        sql.execBookRetrieveStatement(tblModel);
        database.closeConnection();
    }

    @Override
    public void updateDetails() {
        database.establishConnection();
        sql.createBookUpdateStatementObj(database);
        sql.execBookUpdateStatement(book_ID, title, genre, condition, author, publisher, year, available, description);
        database.closeConnection();
    }

    @Override
    public void deleteDetails(JTable tbl, DefaultTableModel dtm) {
        database.establishConnection();
        sql.getBookDeletionSelection(tbl, dtm);
        database.closeConnection();
    }

}
