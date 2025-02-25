package com.mycompany.librarysystem;

import com.mycompany.librarysystemdatabase.LibrarySystemDAO;
import com.mycompany.librarysystemobjects.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class ViewAll extends MyFrame implements ActionListener {

    private JPanel pnlBooks, pnlVenues, pnlComputers;
    private JButton btnBooks, btnVenue, btnComputer, btnBookCheckoutRead;
    private JTable tblBook, tblVenue, tblComputer;
    private JLabel lblBook, lblVenue, lblComputer;
    private DefaultTableModel dtmBook, dtmVenue, dtmComputer;
    private JScrollPane scrBook, scrVenue, scrComputer;
    private ImageIcon cart = new ImageIcon("cart.png");

    //------------------------------------------------------------//
    Book book = new Book();
    Venue venue = new Venue();
    Computer computer = new Computer();
    LibrarySystemDAO dao = new LibrarySystemDAO();

    public ViewAll() {
        cart = new ImageIcon(cart.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH));
        menuViewAll.setForeground(grey);

        // JPanels
        pnlBooks = new JPanel(new BorderLayout());
        pnlVenues = new JPanel(new BorderLayout());
        pnlComputers = new JPanel(new BorderLayout());

        //------------------------------------------------------------//
        // JButton
        btnBooks = new JButton("Add Book");
        MyFrame.setButton(btnBooks, 200, 50);
        btnBookCheckoutRead = new JButton(cart);
        MyFrame.setButton(btnBookCheckoutRead, 200, 50);
        MyFrame.menuBar.add(btnBookCheckoutRead);

        btnVenue = new JButton("Add Venue");
        MyFrame.setButton(btnVenue, 200, 50);

        btnComputer = new JButton("Add Computer");
        MyFrame.setButton(btnComputer, 200, 50);

        //------------------------------------------------------------//
        // JLabel
        lblBook = new JLabel("Books");
        MyFrame.setLabel(lblBook, true);

        lblVenue = new JLabel("Venues");
        MyFrame.setLabel(lblVenue, true);

        lblComputer = new JLabel("Computers");
        MyFrame.setLabel(lblComputer, true);

        //------------------------------------------------------------//
        // JTables and DefaultTableModel
        tblBook = new JTable();
        tblVenue = new JTable();
        tblComputer = new JTable();

        dtmBook = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make all cells non-editable
            }
        };
        tblBook.setModel(dtmBook);
        tblBook.setSelectionBackground(black);
        tblBook.setSelectionForeground(white);
        tblBook.setCellSelectionEnabled(false);
        tblBook.setRowSelectionAllowed(true);

        book.getDetails(dtmBook);

        tblBook.setShowGrid(true);
        tblBook.setGridColor(Color.BLACK);
        tblBook.setBackground(null);

        dtmVenue = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make all cells non-editable
            }
        };
        tblVenue.setModel(dtmVenue);
        tblVenue.setSelectionBackground(black);
        tblVenue.setSelectionForeground(white);
        tblVenue.setCellSelectionEnabled(false);
        tblVenue.setRowSelectionAllowed(true);

        venue.getDetails(dtmVenue);

        tblVenue.setShowGrid(true);
        tblVenue.setGridColor(Color.BLACK);
        tblVenue.setBackground(null);

        dtmComputer = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make all cells non-editable
            }
        };
        tblComputer.setModel(dtmComputer);
        tblComputer.setSelectionBackground(black);
        tblComputer.setSelectionForeground(white);
        tblComputer.setCellSelectionEnabled(false);
        tblComputer.setRowSelectionAllowed(true);

        computer.getDetails(dtmComputer);

        tblComputer.setShowGrid(true);
        tblComputer.setGridColor(Color.BLACK);
        tblComputer.setBackground(null);
//------------------------------------------------------------//

        scrBook = new JScrollPane(tblBook);
        scrBook.setPreferredSize(new Dimension(900, 100));
        scrComputer = new JScrollPane(tblComputer);
        scrComputer.setPreferredSize(new Dimension(900, 100));
        scrVenue = new JScrollPane(tblVenue);
        scrVenue.setPreferredSize(new Dimension(900, 100));

        // Layouts
        // Books panel
        JPanel pnlBookContent = new JPanel();
        pnlBookContent.setLayout(new BoxLayout(pnlBookContent, BoxLayout.Y_AXIS));
        pnlBookContent.add(scrBook);
        pnlBookContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlBookContent.add(cmbBook1);
        pnlBookContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlBookContent.add(cmbBook2);
        pnlBookContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlBookContent.add(cmbBook3);

        JPanel pnlTopBook = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTopBook.add(lblBook);

        JPanel pnlBottomBook = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBottomBook.add(btnBooks);

        pnlBooks.add(pnlTopBook, BorderLayout.NORTH);
        pnlBooks.add(pnlBookContent, BorderLayout.CENTER);
        pnlBooks.add(pnlBottomBook, BorderLayout.SOUTH);

        //------------------------------------------------------------//
        // Venues panel
        JPanel pnlVenueContent = new JPanel();
        pnlVenueContent.setLayout(new BoxLayout(pnlVenueContent, BoxLayout.Y_AXIS));
        pnlVenueContent.add(scrVenue);
        pnlVenueContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlVenueContent.add(cmbVenue1);
        pnlVenueContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlVenueContent.add(cmbVenue2);
        pnlVenueContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlVenueContent.add(cmbVenue3);

        JPanel pnlTopVenue = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTopVenue.add(lblVenue);

        JPanel pnlBottomVenue = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBottomVenue.add(btnVenue);

        pnlVenues.add(pnlTopVenue, BorderLayout.NORTH);
        pnlVenues.add(pnlVenueContent, BorderLayout.CENTER);
        pnlVenues.add(pnlBottomVenue, BorderLayout.SOUTH);

        //------------------------------------------------------------//
        // Computers panel
        JPanel pnlComputerContent = new JPanel();
        pnlComputerContent.setLayout(new BoxLayout(pnlComputerContent, BoxLayout.Y_AXIS));
        pnlComputerContent.add(scrComputer);
        pnlComputerContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlComputerContent.add(cmbComputer1);
        pnlComputerContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlComputerContent.add(cmbComputer2);
        pnlComputerContent.add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        //pnlComputerContent.add(cmbComputer3);

        JPanel pnlTopComputer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTopComputer.add(lblComputer);

        JPanel pnlBottomComputer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBottomComputer.add(btnComputer);

        pnlComputers.add(pnlTopComputer, BorderLayout.NORTH);
        pnlComputers.add(pnlComputerContent, BorderLayout.CENTER);
        pnlComputers.add(pnlBottomComputer, BorderLayout.SOUTH);

        //------------------------------------------------------------//
        // Main panel 
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        JPanel leftPadding = new JPanel();
        leftPadding.setPreferredSize(new Dimension(50, 0));
        JPanel rightPadding = new JPanel();
        rightPadding.setPreferredSize(new Dimension(50, 0));

        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
        pnlContent.add(pnlBooks);
        pnlContent.add(pnlComputers);
        pnlContent.add(pnlVenues);

        pnlMain.add(leftPadding, BorderLayout.WEST);
        pnlMain.add(pnlContent, BorderLayout.CENTER);
        pnlMain.add(rightPadding, BorderLayout.EAST);

        JScrollPane mainScrollPane = new JScrollPane(pnlMain);
        this.add(mainScrollPane, BorderLayout.CENTER);

        btnBooks.addActionListener(this);
        btnBookCheckoutRead.addActionListener(this);
        btnComputer.addActionListener(this);
        btnVenue.addActionListener(this);
    }
//------------------------------------------------------------//

    @Override
    public void setGUI() {
        MyFrame.setFrame(this, 1000, 600);
    }

    public void writeToFileBooks(ArrayList<String> aListBooks) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("checkout.ser"));
            out.writeObject(aListBooks);
            JOptionPane.showMessageDialog(null, "Saved to cart successfully");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public ArrayList<String> readFromFileBooks() {
//        ArrayList<String> books = new ArrayList<>();
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("checkout.ser"));
//            books = (ArrayList<String>) in.readObject();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return books;
//    }
    public void writeToFile(String filename, Object data) {
        File file = new File(filename);
        boolean append = file.exists(); // Check if file already exists

        try (
                FileOutputStream fos = new FileOutputStream(file, append); ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {

            oos.writeObject(data);
            System.out.println("Data written successfully!");
            JOptionPane.showMessageDialog(null, "Item added to cart successfully");

        } catch (IOException e) {
            System.out.println("IO error " + e);
        }
    }

    public void readFromFile(String filename, JTextArea txtArea) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            String items;
            ArrayList<String> list = new ArrayList<>();
            while (fis.available() > 0) {
                items = (String) ois.readObject();
                System.out.println("Read object: " + items);
                list.add(items);
            }
            if (txtArea == null) {
                JOptionPane.showMessageDialog(null, list.stream().collect(Collectors.joining("")));
            } else {
                txtArea.append(list.stream().collect(Collectors.joining("")));
            }
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file " + e);
        }
    }

    // Custom ObjectOutputStream to avoid writing header during append
    public static class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // prevent writing the header for appending
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBooks) {
            int selectedrow = tblBook.getSelectedRow();
            if (selectedrow > -1) {
                String title = (String) tblBook.getValueAt(selectedrow, 1);
                String condition = (String) tblBook.getValueAt(selectedrow, 3);
                writeToFile("checkout.ser", title + " -> " + condition + " codnition \n");
            } else {
                JOptionPane.showMessageDialog(null, "No book selected");
            }

        }
        if (e.getSource() == btnBookCheckoutRead) {
            //JOptionPane.showMessageDialog(null, readFromFileBooks());
            readFromFile("checkout.ser", null);
        }
        if (e.getSource() == btnComputer) {
            int selectedrow = tblComputer.getSelectedRow();
            if (selectedrow > -1) {
                String compNum = (String) tblComputer.getValueAt(selectedrow, 0);
                writeToFile("checkout.ser", "Computer number: " + compNum);
            } else {
                JOptionPane.showMessageDialog(null, "No computer selected");
            }
        }
        if (e.getSource() == btnVenue) {
            int selectedrow = tblVenue.getSelectedRow();
            if (selectedrow > -1) {
                String venNum = (String) tblVenue.getValueAt(selectedrow, 0);
                writeToFile("checkout.ser", "Venue number: " + venNum);
            } else {
                JOptionPane.showMessageDialog(null, "No venue selected");
            }
        }
    }

}
