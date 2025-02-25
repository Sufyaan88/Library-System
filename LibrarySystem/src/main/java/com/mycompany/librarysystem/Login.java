package com.mycompany.librarysystem;

import com.mycompany.librarysystemobjects.Computer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.mycompany.librarysystemdatabase.*;

public class Login extends MyFrame implements ActionListener{

    JLabel lblLogo, lblUsername, lblPassword;
    JButton btnLogin;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JPanel pnl, pnlMain;
    LibrarySystemDAO dao = new LibrarySystemDAO();

    public Login() {
        //Label
        lblLogo = new JLabel(icon);
        lblLogo.setText("LibrarySystem");
        setLabel(lblLogo, true);

        lblUsername = new JLabel("Username");
        MyFrame.setLabel(lblUsername, false);

        lblPassword = new JLabel("Password");
        MyFrame.setLabel(lblPassword, false);

        //Button
        btnLogin = new JButton("Login");
        MyFrame.setButton(btnLogin, 100, 50);   //sets UI decor

        //TextField
        txtUsername = new JTextField();
        MyFrame.setTextField(txtUsername, 250, 50);

        txtPassword = new JPasswordField();
        MyFrame.setPasswordField(txtPassword, 250, 50);

        //Panel
        pnl = new JPanel();
        pnlMain = new JPanel();
    }

    public void userEventHandler() {
        this.dispose();
        ViewAll view = new ViewAll();
        view.setGUI();
        view.setVisible(true);
    }
    
    public void adminEventHandler(){
        this.dispose();
        EditAll edit = new EditAll();
        edit.setGUI();
        edit.setVisible(true);
    }
    
    @Override
    public void setGUI() {
        GroupLayout grp = new GroupLayout(pnl);
        pnl.setLayout(grp);

        grp.setAutoCreateContainerGaps(true);
        grp.setAutoCreateGaps(true);

        grp.setHorizontalGroup(grp.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        grp.setVerticalGroup(grp.createSequentialGroup()
                .addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pnlMain.setLayout(new FlowLayout());

        pnlMain.add(pnl);

        this.add(pnlMain);
        MyFrame.setLoginFrame(this);
        
        btnLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            dao.validateLogin(username, password);
            
        }
    }

}
