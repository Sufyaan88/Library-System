package com.mycompany.librarysystem;

import static com.mycompany.librarysystem.MyFrame.icon;
import com.mycompany.librarysystemobjects.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import com.mycompany.librarysystemdatabase.LibrarySystemDAO;

public class EditAll extends MyFrame implements ActionListener {

    // ----------------------------------Label and Button declaration--------------------------
    private final JLabel lblBooks = new JLabel("Books");
    private final JLabel lblComputers = new JLabel("Computers");
    private final JLabel lblVenues = new JLabel("Venues");
    private final JLabel lblUsers = new JLabel("Users");
    private final JPanel pnlMain = new JPanel();
    private final JPanel pnlHeader = new JPanel();
    JScrollPane scrMain = new JScrollPane(pnlMain);

    // ----------------------------------Books Subsection--------------------------
    // --------------Book Panels---------------
    private final JPanel pnlLeftB = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel pnlCenterB = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel pnlRightB = new JPanel(new GridLayout(1, 3, 10, 10));
    private final JPanel pnlBooks = new JPanel(new BorderLayout());
    private JPanel pnlAddBook = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlAddBook2 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JPanel pnlUpdateBook = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlDeleteBook = new JPanel(new GridLayout(2, 1, 5, 5));

    // -------------Books Table-----------------
    private final DefaultTableModel dftBooks = new DefaultTableModel();
    private final JTable tblBooks = new JTable(dftBooks);
    private final JScrollPane scrBooks = new JScrollPane(tblBooks);

    // --------------Book Edit Controls---------------
    JLabel lblSaveBook = new JLabel("Add Record");
    JButton btnViewAsUser = new JButton("View As User");
    JButton btnAddBook = new JButton("Add Book");
    JButton btnSaveBook = new JButton("Save Book");
    JLabel lblUpdateBook = new JLabel("Update Record");
    JButton btnUpdateBook = new JButton("Update Book");
    JLabel lblDeleteBook = new JLabel("Delete Record");
    JButton btnDeleteBook = new JButton("Delete Book");

    // ----------------------------------Computers Subsection--------------------------
    // --------------Computer Panels--------------
    private final JPanel pnlLeftC = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel pnlCenterC = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel pnlRightC = new JPanel(new GridLayout(1, 3, 10, 10));
    private final JPanel pnlComputers = new JPanel(new BorderLayout());
    private JPanel pnlAddComputer = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlAddComputer2 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JPanel pnlUpdateComputer = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlDeleteComputer = new JPanel(new GridLayout(2, 1, 5, 5));

    // -------------Computers Table---------------
    private final DefaultTableModel dftComputers = new DefaultTableModel();
    private final JTable tblComputers = new JTable(dftComputers);
    private final JScrollPane scrComputers = new JScrollPane(tblComputers);

    // --------------Computer Edit Controls---------------
    JLabel lblSaveComputer = new JLabel("Add Record");
    JButton btnAddComputer = new JButton("Add Computer");
    JButton btnSaveComputer = new JButton("Save Computer");
    JLabel lblUpdateComputer = new JLabel("Update Record");
    JButton btnUpdateComputer = new JButton("Update Computer");
    JLabel lblDeleteComputer = new JLabel("Delete Record");
    JButton btnDeleteComputer = new JButton("Delete Computer");

    // ----------------------------------Venue Subsection--------------------------
    // -------------Venue Panels-------------------
    private final JPanel pnlLeftV = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel pnlCenterV = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel pnlRightV = new JPanel(new GridLayout(1, 3, 10, 10));
    private final JPanel pnlVenues = new JPanel(new BorderLayout());
    private JPanel pnlAddVenue = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlAddVenue2 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JPanel pnlUpdateVenue = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlDeleteVenue = new JPanel(new GridLayout(2, 1, 5, 5));

    // -------------Venues Table-------------------
    private final DefaultTableModel dftVenues = new DefaultTableModel();
    private final JTable tblVenues = new JTable(dftVenues);
    private final JScrollPane scrVenues = new JScrollPane(tblVenues);

    // --------------Venue Edit Controls---------------
    JLabel lblSaveVenue = new JLabel("Add Record");
    JButton btnAddVenue = new JButton("Add Venue");
    JButton btnSaveVenue = new JButton("Save Venue");
    JLabel lblUpdateVenue = new JLabel("Update Record");
    JButton btnUpdateVenue = new JButton("Update Venue");
    JLabel lblDeleteVenue = new JLabel("Delete Record");
    JButton btnDeleteVenue = new JButton("Delete Venue");

    // ----------------------------------User Subsection--------------------------
    // -------------User Panels-------------------
    private final JPanel pnlLeftU = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel pnlCenterU = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel pnlRightU = new JPanel(new GridLayout(1, 3, 10, 10));
    private final JPanel pnlUsers = new JPanel(new BorderLayout());
    private JPanel pnlAddUser = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlAddUser2 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JPanel pnlUpdateUser = new JPanel(new GridLayout(2, 1, 5, 5));
    private JPanel pnlDeleteUser = new JPanel(new GridLayout(2, 1, 5, 5));

    // -------------Venues Table-------------------
    private final DefaultTableModel dftUsers = new DefaultTableModel();
    private final JTable tblUsers = new JTable(dftUsers);
    private final JScrollPane scrUsers = new JScrollPane(tblUsers);

    // --------------Venue Edit Controls---------------
    JLabel lblSaveUser = new JLabel("Add Record");
    JButton btnAddUser = new JButton("Add User");
    JButton btnSaveUser = new JButton("Save User");
    JLabel lblUpdateUser = new JLabel("Update Record");
    JButton btnUpdateUser = new JButton("Update User");
    JLabel lblDeleteUser = new JLabel("Delete Record");
    JButton btnDeleteUser = new JButton("Delete User");

    // ----------------------------------Project Objects--------------------------
    Book book = new Book();
    Computer computer = new Computer();
    Venue venue = new Venue();
    User user = new User();
    LibrarySystemDAO dao = new LibrarySystemDAO();

    public EditAll() {
        menuEditAll.setForeground(grey);
        // ----------------------------------Books Section--------------------------
        MyFrame.setLabel(lblBooks, true);
        book.getDetails(dftBooks);
        scrBooks.setPreferredSize(new Dimension(900, tblBooks.getPreferredSize().height + 20));

        lblDeleteBook.setHorizontalAlignment(JLabel.CENTER);
        lblUpdateBook.setHorizontalAlignment(JLabel.CENTER);
        lblSaveBook.setHorizontalAlignment(JLabel.CENTER);
        MyFrame.setLabel(lblDeleteBook, false);
        MyFrame.setLabel(lblUpdateBook, false);
        MyFrame.setLabel(lblSaveBook, false);

        MyFrame.setButton(btnViewAsUser, 250, 40);
        MyFrame.setButton(btnDeleteBook, 150, 40);
        MyFrame.setButton(btnUpdateBook, 150, 40);
        MyFrame.setButton(btnAddBook, 150, 40);
        MyFrame.setButton(btnSaveBook, 150, 40);
        btnSaveBook.setEnabled(false);

        tblBooks.setSelectionBackground(black);
        tblBooks.setSelectionForeground(white);

        // ----------------------------------Computers Section--------------------------
        MyFrame.setLabel(lblComputers, true);
        computer.getDetails(dftComputers);
        scrComputers.setPreferredSize(new Dimension(900, 150));
        tblComputers.setSelectionBackground(black);
        tblComputers.setSelectionForeground(white);

        MyFrame.setButton(btnDeleteComputer, 150, 40);
        MyFrame.setButton(btnUpdateComputer, 150, 40);
        MyFrame.setButton(btnAddComputer, 150, 40);
        MyFrame.setButton(btnSaveComputer, 150, 40);
        btnSaveComputer.setEnabled(false);

        lblDeleteComputer.setHorizontalAlignment(JLabel.CENTER);
        lblUpdateComputer.setHorizontalAlignment(JLabel.CENTER);
        lblSaveComputer.setHorizontalAlignment(JLabel.CENTER);
        MyFrame.setLabel(lblDeleteComputer, false);
        MyFrame.setLabel(lblUpdateComputer, false);
        MyFrame.setLabel(lblSaveComputer, false);

        // ----------------------------------Venues Section--------------------------
        lblDeleteVenue.setHorizontalAlignment(JLabel.CENTER);
        lblUpdateVenue.setHorizontalAlignment(JLabel.CENTER);
        lblSaveVenue.setHorizontalAlignment(JLabel.CENTER);
        MyFrame.setLabel(lblVenues, true);
        MyFrame.setLabel(lblDeleteVenue, false);
        MyFrame.setLabel(lblUpdateVenue, false);
        MyFrame.setLabel(lblSaveVenue, false);
        venue.getDetails(dftVenues);
        scrVenues.setPreferredSize(new Dimension(900, 150));

        MyFrame.setButton(btnDeleteVenue, 150, 40);
        MyFrame.setButton(btnUpdateVenue, 150, 40);
        MyFrame.setButton(btnAddVenue, 150, 40);
        MyFrame.setButton(btnSaveVenue, 150, 40);
        btnSaveVenue.setEnabled(false);

        // ----------------------------------Users Section--------------------------
        lblDeleteUser.setHorizontalAlignment(JLabel.CENTER);
        lblUpdateUser.setHorizontalAlignment(JLabel.CENTER);
        lblSaveUser.setHorizontalAlignment(JLabel.CENTER);
        MyFrame.setLabel(lblUsers, true);
        MyFrame.setLabel(lblDeleteUser, false);
        MyFrame.setLabel(lblUpdateUser, false);
        MyFrame.setLabel(lblSaveUser, false);
        user.getDetails(dftUsers);
        scrUsers.setPreferredSize(new Dimension(900, 150));

        MyFrame.setButton(btnDeleteUser, 150, 40);
        MyFrame.setButton(btnUpdateUser, 150, 40);
        MyFrame.setButton(btnAddUser, 150, 40);
        MyFrame.setButton(btnSaveUser, 150, 40);
        btnSaveUser.setEnabled(false);

        tblVenues.setSelectionBackground(black);
        tblVenues.setSelectionForeground(white);

    }

    @Override
    public void setGUI() {
        // ----------------------------------Books Section--------------------------
        pnlDeleteBook.add(lblDeleteBook);
        pnlDeleteBook.add(btnDeleteBook);
        pnlUpdateBook.add(lblUpdateBook);
        pnlUpdateBook.add(btnUpdateBook);
        pnlAddBook2.add(btnAddBook);
        pnlAddBook2.add(btnSaveBook);
        pnlAddBook.add(lblSaveBook);
        pnlAddBook.add(pnlAddBook2);
        pnlLeftB.add(lblBooks);
        pnlCenterB.add(scrBooks);
        pnlRightB.add(pnlDeleteBook);
        pnlRightB.add(pnlUpdateBook);
        pnlRightB.add(pnlAddBook);
        pnlBooks.add(pnlLeftB, BorderLayout.NORTH);
        pnlBooks.add(pnlCenterB, BorderLayout.CENTER);
        pnlBooks.add(pnlRightB, BorderLayout.SOUTH);
        pnlBooks.setMaximumSize(new Dimension(900, 300));
        tblBooks.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        goToViewAll(btnViewAsUser, this);
        btnDeleteBook.addActionListener(this);
        btnUpdateBook.addActionListener(this);
        btnAddBook.addActionListener(this);
        btnSaveBook.addActionListener(this);

        btnDeleteComputer.addActionListener(this);
        btnUpdateComputer.addActionListener(this);
        btnAddComputer.addActionListener(this);
        btnSaveComputer.addActionListener(this);

        btnDeleteVenue.addActionListener(this);
        btnUpdateVenue.addActionListener(this);
        btnAddVenue.addActionListener(this);
        btnSaveVenue.addActionListener(this);

        btnDeleteUser.addActionListener(this);
        btnUpdateUser.addActionListener(this);
        btnAddUser.addActionListener(this);
        btnSaveUser.addActionListener(this);

        // ----------------------------------Computers Section--------------------------
        pnlDeleteComputer.add(lblDeleteComputer);
        pnlDeleteComputer.add(btnDeleteComputer);
        pnlUpdateComputer.add(lblUpdateComputer);
        pnlUpdateComputer.add(btnUpdateComputer);
        pnlAddComputer2.add(btnAddComputer);
        pnlAddComputer2.add(btnSaveComputer);
        pnlAddComputer.add(lblSaveComputer);
        pnlAddComputer.add(pnlAddComputer2);
        pnlLeftC.add(lblComputers);
        pnlCenterC.add(scrComputers);
        pnlRightC.add(pnlDeleteComputer);
        pnlRightC.add(pnlUpdateComputer);
        pnlRightC.add(pnlAddComputer);
        pnlComputers.add(pnlLeftC, BorderLayout.NORTH);
        pnlComputers.add(pnlCenterC, BorderLayout.CENTER);
        pnlComputers.add(pnlRightC, BorderLayout.SOUTH);
        pnlComputers.setMaximumSize(new Dimension(900, 300));

        // ----------------------------------Venue Section--------------------------
        pnlDeleteVenue.add(lblDeleteVenue);
        pnlDeleteVenue.add(btnDeleteVenue);
        pnlUpdateVenue.add(lblUpdateVenue);
        pnlUpdateVenue.add(btnUpdateVenue);
        pnlAddVenue2.add(btnAddVenue);
        pnlAddVenue2.add(btnSaveVenue);
        pnlAddVenue.add(lblSaveVenue);
        pnlAddVenue.add(pnlAddVenue2);
        pnlLeftV.add(lblVenues);
        pnlCenterV.add(scrVenues);
        pnlRightV.add(pnlDeleteVenue);
        pnlRightV.add(pnlUpdateVenue);
        pnlRightV.add(pnlAddVenue);
        pnlVenues.add(pnlLeftV, BorderLayout.NORTH);
        pnlVenues.add(pnlCenterV, BorderLayout.CENTER);
        pnlVenues.add(pnlRightV, BorderLayout.SOUTH);
        pnlVenues.setMaximumSize(new Dimension(900, 300));

        // ----------------------------------User Section--------------------------
        pnlDeleteUser.add(lblDeleteUser);
        pnlDeleteUser.add(btnDeleteUser);
        pnlUpdateUser.add(lblUpdateUser);
        pnlUpdateUser.add(btnUpdateUser);
        pnlAddUser2.add(btnAddUser);
        pnlAddUser2.add(btnSaveUser);
        pnlAddUser.add(lblSaveUser);
        pnlAddUser.add(pnlAddUser2);
        pnlLeftU.add(lblUsers);
        pnlCenterU.add(scrUsers);
        pnlRightU.add(pnlDeleteUser);
        pnlRightU.add(pnlUpdateUser);
        pnlRightU.add(pnlAddUser);
        pnlUsers.add(pnlLeftU, BorderLayout.NORTH);
        pnlUsers.add(pnlCenterU, BorderLayout.CENTER);
        pnlUsers.add(pnlRightU, BorderLayout.SOUTH);
        pnlUsers.setMaximumSize(new Dimension(900, 300));

        //----------------------------------Panel for 'View User' button----------------------
        pnlHeader.setLayout(new FlowLayout());
        pnlHeader.add(btnViewAsUser);

        // ----------------------------------Placing panels on frame--------------------------
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.add(pnlHeader);
        pnlMain.add(pnlBooks);
        pnlMain.add(pnlComputers);
        pnlMain.add(pnlVenues);
        pnlMain.add(pnlUsers);
        this.add(scrMain);
        MyFrame.setFrame(this, 1000, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //--------------BOOK-------------
        if (e.getSource() == btnAddBook) {
            btnDeleteBook.setEnabled(false);
            btnUpdateBook.setEnabled(false);
            btnSaveBook.setEnabled(true);
            btnAddBook.setEnabled(false);
            dao.addBlankRow(dftBooks);
            JOptionPane.showMessageDialog(null, "A blank row is available to be edited. Scroll in the Books table");
        }
        if (e.getSource() == btnSaveBook) {
            //tblBooks.getCellEditor().stopCellEditing();
            int row = dftBooks.getRowCount() - 1;
            String bookID = (String) dftBooks.getValueAt(row, 0);
            String bookTitle = (String) dftBooks.getValueAt(row, 1);
            String bookGenre = (String) dftBooks.getValueAt(row, 2);
            String bookCondition = (String) dftBooks.getValueAt(row, 3);
            String bookAuthor = (String) dftBooks.getValueAt(row, 4);
            String bookPublisher = (String) dftBooks.getValueAt(row, 5);
            String PubYear = (String) dftBooks.getValueAt(row, 6);
            int bookPubYear = Integer.parseInt(PubYear);
            String available = (String) dftBooks.getValueAt(row, 7);
            boolean bookAvailable = Boolean.parseBoolean(available);
            String bookDescription = (String) dftBooks.getValueAt(row, 8);
            book = new Book(bookID, bookTitle, bookGenre, bookCondition, bookAuthor, bookPublisher, bookAvailable, bookPubYear, bookDescription);

            btnDeleteBook.setEnabled(true);
            btnUpdateBook.setEnabled(true);
            btnAddBook.setEnabled(true);
            btnSaveBook.setEnabled(false);
        }
        if (e.getSource() == btnUpdateBook) {
            System.out.println("update btn pressed");
            int rowSelected = tblBooks.getSelectedRow();
            if (rowSelected != -1) {
                for (int col = 0; col < tblBooks.getColumnCount(); col++) {
                    Object value = tblBooks.getValueAt(rowSelected, col);
                    System.out.println("Column " + col + ": " + value);

                    switch (col) {
                        case 0:
                            book.setBook_ID((String) value);
                            break;
                        case 1:
                            book.setTitle((String) value);
                            break;
                        case 2:
                            book.setGenre((String) value);
                            break;
                        case 3:
                            book.setCondition((String) value);
                            break;
                        case 4:
                            book.setAuthor((String) value);
                            break;
                        case 5:
                            book.setPublisher((String) value);
                            break;
                        case 6:
                            book.setYear(Integer.parseInt((String) value));
                            break;
                        case 7:
                            if (value.equals("true")) {
                                book.setAvailable(true);
                            } else {
                                book.setAvailable(false);
                            }
                            break;
                        case 8:
                            book.setDescription((String) value);
                            break;
                    }
                }
            } else {
                System.out.println("No row selected.");
            }

            book.updateDetails();

        }
        if (e.getSource() == btnDeleteBook) {
            book.deleteDetails(tblBooks, dftBooks);
        }
        //--------------COMPUTER-------------
        if (e.getSource() == btnAddComputer) {
            btnDeleteComputer.setEnabled(false);
            btnUpdateComputer.setEnabled(false);
            btnSaveComputer.setEnabled(true);
            btnAddComputer.setEnabled(false);
            dao.addBlankRow(dftComputers);
            JOptionPane.showMessageDialog(null, "A blank row is available to be edited. Scroll in the Computers table");
        }
        if (e.getSource() == btnSaveComputer) {
            //tblBooks.getCellEditor().stopCellEditing();
            int row = dftComputers.getRowCount() - 1;
            String computerID = (String) dftComputers.getValueAt(row, 0);
            String brand = (String) dftComputers.getValueAt(row, 1);
            String colour = (String) dftComputers.getValueAt(row, 2);
            String macAddress = (String) dftComputers.getValueAt(row, 3);
            String condition = (String) dftComputers.getValueAt(row, 4);
            String available = (String) dftComputers.getValueAt(row, 5);
            boolean computerAvailable = Boolean.parseBoolean(available);
            computer = new Computer(computerID, brand, colour, macAddress, condition, computerAvailable);

            btnDeleteComputer.setEnabled(true);
            btnUpdateComputer.setEnabled(true);
            btnAddComputer.setEnabled(true);
            btnSaveComputer.setEnabled(false);
        }
        if (e.getSource() == btnUpdateComputer) {
            System.out.println("update btn pressed");
            int rowSelected = tblComputers.getSelectedRow();
            if (rowSelected != -1) {
                for (int col = 0; col < tblComputers.getColumnCount(); col++) {
                    Object value = tblComputers.getValueAt(rowSelected, col);
                    System.out.println("Column " + col + ": " + value);

                    switch (col) {
                        case 0:
                            computer.setComputer_number((String) value);
                            break;
                        case 1:
                            computer.setBrand((String) value);
                            break;
                        case 2:
                            computer.setColour((String) value);
                            break;
                        case 3:
                            computer.setMac_address((String) value);
                            break;
                        case 4:
                            computer.setCondition((String) value);
                            break;
                        case 5:
                            if (value.equals("true")) {
                                computer.setAvailable(true);
                            } else {
                                computer.setAvailable(false);
                            }
                            break;
                    }
                }
            } else {
                System.out.println("No row selected.");
            }

            computer.updateDetails();

        }
        if (e.getSource() == btnDeleteComputer) {
            computer.deleteDetails(tblComputers, dftComputers);
        }
        //--------------VENUE-------------
        if (e.getSource() == btnAddVenue) {
            btnDeleteVenue.setEnabled(false);
            btnUpdateVenue.setEnabled(false);
            btnSaveVenue.setEnabled(true);
            btnAddVenue.setEnabled(false);
            dao.addBlankRow(dftVenues);
            JOptionPane.showMessageDialog(null, "A blank row is available to be edited. Scroll in the Venues table");
        }
        if (e.getSource() == btnSaveVenue) {
            //tblBooks.getCellEditor().stopCellEditing();
            int row = dftVenues.getRowCount() - 1;
            String venueNo = (String) dftVenues.getValueAt(row, 0);
            String capacity = (String) dftVenues.getValueAt(row, 1);
            String projector = (String) dftVenues.getValueAt(row, 2);
            String available = (String) dftVenues.getValueAt(row, 3);
            int venueCapacity = Integer.parseInt(capacity);
            boolean venueProjector = Boolean.parseBoolean(projector);
            boolean venueAvailable = Boolean.parseBoolean(available);
            venue = new Venue(venueNo, venueCapacity, venueProjector, venueAvailable);

            btnDeleteVenue.setEnabled(true);
            btnUpdateVenue.setEnabled(true);
            btnAddVenue.setEnabled(true);
            btnSaveVenue.setEnabled(false);
        }
        if (e.getSource() == btnUpdateVenue) {
            System.out.println("update btn pressed");
            int rowSelected = tblVenues.getSelectedRow();
            if (rowSelected != -1) {
                for (int col = 0; col < tblVenues.getColumnCount(); col++) {
                    Object value = tblVenues.getValueAt(rowSelected, col);
                    System.out.println("Column " + col + ": " + value);

                    switch (col) {
                        case 0:
                            venue.setVenueNumber((String) value);
                            break;
                        case 1:
                            venue.setCapacity(Integer.parseInt((String) value));
                            break;
                        case 2:
                            if (value.equals("true")) {
                                venue.setProjector(true);
                            } else {
                                venue.setProjector(false);
                            }
                            break;
                        case 3:
                            if (value.equals("true")) {
                                venue.setAvailable(true);
                            } else {
                                venue.setAvailable(false);
                            }
                            break;
                    }
                }
            } else {
                System.out.println("No row selected.");
            }

            venue.updateDetails();

        }
        if (e.getSource() == btnDeleteVenue) {
            venue.deleteDetails(tblVenues, dftVenues);
        }
        //--------------USER-------------
        if (e.getSource() == btnAddUser) {
            btnDeleteUser.setEnabled(false);
            btnUpdateUser.setEnabled(false);
            btnSaveUser.setEnabled(true);
            btnAddUser.setEnabled(false);
            dao.addBlankRow(dftUsers);
            JOptionPane.showMessageDialog(null, "A blank row is available to be edited. Scroll in the Users table");
        }
        if (e.getSource() == btnSaveUser) {
            //tblBooks.getCellEditor().stopCellEditing();
            int row = dftUsers.getRowCount() - 1;
            String userID = (String) dftUsers.getValueAt(row, 0);
            String venueBooked = (String) dftUsers.getValueAt(row, 1);
            String comBooked = (String) dftUsers.getValueAt(row, 2);
            String penalty = (String) dftUsers.getValueAt(row, 3);
            String name = (String) dftUsers.getValueAt(row, 4);
            String age = (String) dftUsers.getValueAt(row, 5);
            String address = (String) dftUsers.getValueAt(row, 6);
            String department = (String) dftUsers.getValueAt(row, 7);
            String email = (String) dftUsers.getValueAt(row, 8);
            String phone = (String) dftUsers.getValueAt(row, 9);
            String gender = (String) dftUsers.getValueAt(row, 10);
            String admin = (String) dftUsers.getValueAt(row, 11);
            String lost = (String) dftUsers.getValueAt(row, 12);
            String password = (String) dftUsers.getValueAt(row, 13);
            boolean venue = Boolean.parseBoolean(venueBooked);
            boolean com = Boolean.parseBoolean(comBooked);
            boolean userGender = Boolean.parseBoolean(gender);
            boolean isAdmin = Boolean.parseBoolean(admin);
            boolean isLost = Boolean.parseBoolean(lost);
            int userPen = Integer.parseInt(penalty);
            int userAge = Integer.parseInt(age);
            user = new User(userID, name, address, department, email, phone, venue, com, userGender, isAdmin, isLost, userPen, userAge, password);

            btnDeleteUser.setEnabled(true);
            btnUpdateUser.setEnabled(true);
            btnAddUser.setEnabled(true);
            btnSaveUser.setEnabled(false);
        }
        if (e.getSource() == btnUpdateUser) {
            System.out.println("Update button pressed");
            int rowSelected = tblUsers.getSelectedRow();
            if (rowSelected != -1) {
                for (int col = 0; col < tblUsers.getColumnCount(); col++) {
                    Object value = tblUsers.getValueAt(rowSelected, col);
                    System.out.println("Column " + col + ": " + value);

                    switch (col) {
                        case 0:
                            user.setUser_ID((String) value);
                            break;
                        case 1:
                            user.setVenue_Booked(value.equals("true"));
                            break;
                        case 2:
                            user.setComputer_Booked(value.equals("true"));
                            break;
                        case 3:
                            user.setPenalty(Integer.parseInt((String) value));
                            break;
                        case 4:
                            user.setName((String) value);
                            break;
                        case 5:
                            user.setAge(Integer.parseInt((String) value));
                            break;
                        case 6:
                            user.setAddress((String) value);
                            break;
                        case 7:
                            user.setDepartment((String) value);
                            break;
                        case 8:
                            user.setEmail((String) value);
                            break;
                        case 9:
                            user.setPhone_Number((String) value);
                            break;
                        case 10:
                            user.setGender(value.equals("true"));
                            break;
                        case 11:
                            user.setAdmin(value.equals("true"));
                            break;
                        case 12:
                            user.setLost(value.equals("true"));
                            break;
                        case 13:
                            user.setPassword((String) value);
                            break;
                    }
                }

                user.updateDetails();
            } else {
                System.out.println("No row selected.");
            }
        }

        if (e.getSource() == btnDeleteUser) {
            user.deleteDetails(tblUsers, dftUsers);
        }

    }

}
