package com.mycompany.librarysystem;

import com.mycompany.librarysystemdatabase.LibrarySystemDAO;
import com.mycompany.librarysystemdatabase.LibrarySystemDBConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.mycompany.librarysystem.ViewAll;

public class Checkout extends MyFrame {

    private JPanel pnlCenter, pnlSouth;
    private JLabel lblCheckout;
    private JButton btnComplete, btnCancel;
    private JTextArea checkoutTxt;
    private String checkout_Number = "2999", user_ID = "100398721", bookName = "Romeo & Juliet", venueID = "B8", computerID = "C4";
    private int bookTime = 3, venueTime = 2, computerTime = 2;
    ViewAll view = new ViewAll();

    public Checkout() {
        menuCheckout.setForeground(grey);
        // Initialize the Checkout JPanel
        JPanel Checkout = new JPanel(new BorderLayout());

        // JPanels
        pnlCenter = new JPanel();
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
        pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //JLabel
        lblCheckout = new JLabel("Are you sure?");

        // JButtons
        btnComplete = new JButton("Complete");
        MyFrame.setButton(btnComplete, 200, 50);
        btnCancel = new JButton("Cancel");
        MyFrame.setButton(btnCancel, 200, 50);

        // JTextArea
        checkoutTxt = new JTextArea("Confirm Checkout List:", 5, 30);
        checkoutTxt.setFont(new Font("Arial", Font.BOLD, 15));
        checkoutTxt.setEditable(false);
        //checkoutTxt.setText("Checkout Number: " + checkout_Number + "\n" + "User ID: " + user_ID + "\n\n" + "Book: " + bookName + "\n" + "Checkout-Time: " + bookTime + " days\n\n" + "Venue: " + venueID + "\n Venue time: " + venueTime + "hrs\n\n" + "Computer: " + computerID + "\n" + "Computer time: " + computerTime + " hrs");

//        checkoutTxt.setText("Books:\nHamlet: 13/08/2024 - 23/08/2024 \nRomeo & Juliet: 13/08/2024 - 23/08/2024 \nOrthello: 13/08/2024 - 23/08/2024\n\n"
//                + "\nComputers Booked\n B9 - Date: 13/08/2024 \nTime: 13:00 - 14:00\n\n"
//                + "\nVenue Booked: \nDate: 13/08/2024 \nTime: 15:00 - 16:00");
        JScrollPane scrollPane = new JScrollPane(checkoutTxt);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding components to the center panel
        pnlCenter.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlCenter.add(scrollPane);
        pnlCenter.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adding buttons to the south panel
        pnlSouth.add(lblCheckout);
        pnlSouth.add(btnComplete);
        pnlSouth.add(btnCancel);

        // Adding panels to the main Checkout panel
        Checkout.add(pnlCenter, BorderLayout.CENTER);
        Checkout.add(pnlSouth, BorderLayout.SOUTH);

        // Add Checkout panel to the frame
        this.add(Checkout);
        complete(btnComplete);
        cancel(btnCancel);
        
        view.readFromFile("checkout.ser", checkoutTxt);
    }

    @Override
    public void setGUI() {
        MyFrame.setFrame(this, 1000, 600);
    }

    public void complete(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrarySystemDBConnection database = new LibrarySystemDBConnection();
                database.establishConnection();
                System.out.print("It is successful");
                lblCheckout.setText("Success");
                LibrarySystemDAO lsDAO = new LibrarySystemDAO();
                lsDAO.createUserCheckoutStatementObj(database);
                lsDAO.execUserCheckoutInsertStatement(checkout_Number, user_ID, bookName, bookTime, venueID, venueTime, computerID, computerTime);
            }
        });
    }

    public void cancel(JButton btn) {
        btn.addActionListener((ActionEvent e) -> {
            System.out.print("Checkout Cancelled");
            checkoutTxt.setText("");
            goToViewAll(btnCancel, this);
        });
    }
}
