package com.mycompany.librarysystem;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author user
 */
public class AddUser extends MyFrame {

    //Add panels
    private JPanel pnlTop = new JPanel();
    private JPanel pnlBottom = new JPanel();
    private JPanel pnlMain = new JPanel();

    //Add Labels
    private JLabel lblName = new JLabel("Name");
    private JLabel lblSurname = new JLabel("Surname");
    private JLabel lblEmail = new JLabel("Email");
    private JLabel lblUserType = new JLabel("User Type");
    private JLabel lblMembershipType = new JLabel("Membership Type");
    private JLabel lblMembershipStartDate = new JLabel("Membership Start Date");
    private JLabel lblMembershipExpiryDate = new JLabel("Membership Expiry Date");
    private JLabel lbl1 = new JLabel("");
    private JLabel lbl2 = new JLabel("");

    //Add Combobox
    private JComboBox<String> cboUserType = new JComboBox<>();
    private JComboBox<String> cboMembershipType = new JComboBox<>();

    //Add TextFields
    private JTextField txtName = new JTextField();
    private JTextField txtSurname = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtMembershipStartDate = new JTextField();
    private JTextField txtMembershipExpiryDate = new JTextField();

    //Add Button
    private JButton btnAddUser = new JButton("Add User");

    public AddUser() {
        setButton(btnAddUser, 250, 60);

        setTextField(txtName, 250, 50);
        setTextField(txtSurname, 250, 50);
        setTextField(txtEmail, 250, 50);
        setTextField(txtMembershipStartDate, 250, 50);
        setTextField(txtMembershipExpiryDate, 250, 50);

        setLabel(lblEmail, false);
        setLabel(lblMembershipExpiryDate, false);
        setLabel(lblMembershipStartDate, false);
        setLabel(lblMembershipType, false);
        setLabel(lblName, false);
        setLabel(lblSurname, false);
        setLabel(lblUserType, false);

        setComboBox(cboUserType, 200, 50);
        setComboBox(cboMembershipType, 200, 50);

        pnlTop.setLayout(new GridLayout(8, 2, 5, 1));
        pnlTop.add(lblName);
        pnlTop.add(lblSurname);
        pnlTop.add(txtName);
        pnlTop.add(txtSurname);
        pnlTop.add(lblEmail);
        pnlTop.add(lblUserType);
        pnlTop.add(txtEmail);
        pnlTop.add(cboUserType);
        pnlTop.add(lblMembershipType);
        pnlTop.add(cboMembershipType);
        pnlTop.add(lblMembershipStartDate);
        pnlTop.add(txtMembershipStartDate);
        pnlTop.add(lblMembershipExpiryDate);
        pnlTop.add(txtMembershipExpiryDate);

        cboUserType.addItem("Student");
        cboUserType.addItem("Staff");
        cboUserType.addItem("Faculty");
        cboUserType.addItem("Patron");

        cboMembershipType.addItem("Annual");
        cboMembershipType.addItem("Monthly");
        cboMembershipType.addItem("Weekly");
        cboMembershipType.addItem("Student");

        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the button
        pnlBottom.add(btnAddUser);

        GroupLayout main = new GroupLayout(pnlMain);
        pnlMain.setLayout(main);

        main.setAutoCreateContainerGaps(true);
        main.setAutoCreateGaps(true);

        main.setVerticalGroup(main.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(pnlTop)
                .addComponent(pnlBottom)
        );

        main.setHorizontalGroup(main.createSequentialGroup()
                .addComponent(pnlTop)
                .addComponent(pnlBottom)
        );

        this.add(pnlMain);

        // Set the initial date fields
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtMembershipStartDate.setText(today.format(formatter));
        txtMembershipExpiryDate.setText(today.plusYears(1).format(formatter));
    }

    @Override
    public void setGUI() {
        MyFrame.setFrame(this, 1000, 600);
    }
}
