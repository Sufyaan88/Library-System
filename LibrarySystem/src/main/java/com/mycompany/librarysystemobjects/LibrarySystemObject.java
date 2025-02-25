package com.mycompany.librarysystemobjects;

import javax.swing.JTable;
import javax.swing.table.*;

public interface LibrarySystemObject {

    public void setDetails();

    public void getDetails(DefaultTableModel tblModel);

    public void updateDetails();
    
    public void deleteDetails(JTable tbl, DefaultTableModel dtm);
}
