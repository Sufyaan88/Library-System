package com.mycompany.librarysystem;

import com.mycompany.librarysystemdatabase.*;
import com.mycompany.librarysystemobjects.*;

public class RunLibrarySystem {

    public static Login frame = new Login();
    public static LibrarySystemDBConnection db = new LibrarySystemDBConnection();
    public static LibrarySystemDAO sql = new LibrarySystemDAO();

    public static void main(String[] args) {
        frame.setGUI();
    }
}
