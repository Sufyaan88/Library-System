package com.mycompany.librarysystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
        
public abstract class MyFrame extends JFrame{

    //ICON
    public static ImageIcon icon = new ImageIcon("LibrarySystem_Icon.png");
    private static Image imgLogo = icon.getImage();

    //FONT TYPE
    private static String fontTypeface = "Arial";

    //COMPONENTS
    private JButton btnExit, btnMinimize, btnExit2, btnLogout;
    private JLabel lblLogo, lblLogo2, lblFooter;
    private static JPanel pnlHeader, pnlFooter, pnlSep, pnlSep2;
    public static JMenuBar menuBar;
    public JMenu menuViewAll, menuEditAll, menuCheckout, menuCart;

    //COLOURS
    public static Color black = new Color(50, 50, 50);
    public static Color white = new Color(245, 245, 245);
    public static Color grey = new Color(180, 180, 180);

    //DEFAULT METHOD EVERY GUI CLASS HAS TO INHERIT
    public abstract void setGUI();

    public MyFrame() {
        icon = new ImageIcon(imgLogo.getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        //MENUBAR & MENUS
        menuBar = new JMenuBar();
        menuBar.setBackground(black);
        menuBar.setBorder(null);

        menuViewAll = new JMenu("View All");
        menuEditAll = new JMenu("Edit All");
        menuCheckout = new JMenu("Checkout");

        menuViewAll.setForeground(white);
        menuViewAll.setFont(new Font(fontTypeface, Font.BOLD, 20));
        menuEditAll.setForeground(menuViewAll.getForeground());
        menuEditAll.setFont(new Font(fontTypeface, Font.BOLD, 20));
        menuCheckout.setForeground(menuViewAll.getForeground());
        menuCheckout.setFont(new Font(fontTypeface, Font.BOLD, 20));

        //LABELS
        lblLogo = new JLabel(icon);
        lblLogo.setPreferredSize(new Dimension(50, 50));
        lblLogo.setBorder(null);

        lblLogo2 = new JLabel(icon);
        lblLogo2.setPreferredSize(new Dimension(50, 50));
        lblLogo2.setBorder(null);

        lblFooter = new JLabel("This is a product of PRT Group 5.");
        lblFooter.setForeground(white);
        lblFooter.setFont(new Font(fontTypeface, Font.BOLD, 10));

        //BUTTONS
        btnExit = new JButton("X");
        btnExit.setBorder(null);
        btnExit.setBackground(menuBar.getBackground());
        btnExit.setForeground(menuViewAll.getForeground());
        btnExit.setPreferredSize(new Dimension(25, 25));
        btnExit.setFont(new Font(fontTypeface, Font.BOLD, 25));

        btnMinimize = new JButton("-");
        btnMinimize.setBorder(null);
        btnMinimize.setBackground(menuBar.getBackground());
        btnMinimize.setForeground(menuViewAll.getForeground());
        btnMinimize.setPreferredSize(new Dimension(30, 30));
        btnMinimize.setFont(new Font(fontTypeface, Font.BOLD, 50));

        btnExit2 = new JButton("X");
        btnExit2.setBorder(null);
        btnExit2.setBackground(white);
        btnExit2.setForeground(black);
        btnExit2.setPreferredSize(new Dimension(25, 25));
        btnExit2.setFont(new Font(fontTypeface, Font.BOLD, 25));

        btnLogout = new JButton("Logout");
        btnLogout.setBorder(null);
        btnLogout.setBackground(menuBar.getBackground());
        btnLogout.setForeground(menuViewAll.getForeground());
        btnLogout.setFont(new Font(fontTypeface, Font.BOLD, 15));
        btnLogout.setPreferredSize(new Dimension(100, 75));

        //PANELS
        pnlHeader = new JPanel();

        pnlSep = new JPanel();
        pnlSep.setPreferredSize(new Dimension(400, 50));
        pnlSep.setBackground(menuBar.getBackground());

        pnlSep2 = new JPanel();
        pnlSep2.setPreferredSize(new Dimension(550, 50));

        pnlFooter = new JPanel();
        pnlFooter.setBackground(black);

        //DOES ALL THE PROCESSING
        eventHandler();
    }

    //ADDS COMPONENTS TO PANELS
    private void addCompToPanels() {
        menuBar.add(lblLogo);
        menuBar.add(menuViewAll);
        menuBar.add(menuEditAll);
        menuBar.add(menuCheckout);
        menuBar.add(pnlSep);
        menuBar.add(btnLogout);

        pnlHeader.add(pnlSep2);
        pnlHeader.add(btnExit2);

        pnlFooter.add(lblFooter);
    }

    //DOES ALL THE PROCESSING
    private void eventHandler() {
        //ADDS COMPONENTS TO PANELS
        addCompToPanels();

        //NAVIGATION
        goToViewAll(menuViewAll, this);
        goToEditAll(menuEditAll, this);
        goToCheckout(menuCheckout, this);
        goToLogin(btnLogout, this);

        //EXPERIMENTAL FEATURES
        minimizeAction(btnMinimize, this);
        exitAction(btnExit);
        exitAction(btnExit2);
    }
    

    //SETS FRAME
    public static void setFrame(JFrame frame, int width, int height) {
        frame.add(pnlFooter, BorderLayout.SOUTH);

        frame.setUndecorated(false);
        frame.pack();
        frame.setJMenuBar(menuBar);
        frame.setTitle("LibrarySystem");
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  //sets frame to the center
        frame.setIconImage(icon.getImage()); //sets icon
    }

    //SETS LOGIN FRAME
    public static void setLoginFrame(JFrame frame) {
        frame.add(pnlHeader, BorderLayout.NORTH);
        frame.add(pnlFooter, BorderLayout.SOUTH);

        frame.setUndecorated(true);
        frame.pack();
        frame.setTitle("LibrarySystem");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  //sets frame to the center
        frame.setIconImage(icon.getImage()); //sets icon
    }

    //DEFAULT PRESET FOR LABELS
    public static void setLabel(JLabel lbl, boolean isHeading) {
        Font fontNormal = new Font(fontTypeface, Font.PLAIN, 20);
        Font fontHeading = new Font(fontTypeface, Font.BOLD, 40);

        lbl.setForeground(black);

        if (isHeading != true) {
            lbl.setFont(fontNormal);
        } else {
            lbl.setFont(fontHeading);
        }

    }

    //DEFAULT PRESET FOR BUTTONS
    public static void setButton(JButton btn, int width, int height) {
        int fontSize = height / 2;
        Font font = new Font(fontTypeface, Font.PLAIN, fontSize);

        btn.setBorder(null);
        btn.setBackground(black);
        btn.setForeground(white);
        btn.setFont(font);
        btn.setPreferredSize(new Dimension(width, height));
    }

    //DEFAULT PRESET FOR TEXTFIELDS
    public static void setTextField(JTextField txt, int width, int height) {
        Font font = new Font(fontTypeface, Font.PLAIN, 20);

        txt.setBorder(null);
        txt.setBackground(black);
        txt.setForeground(white);
        txt.setFont(font);
        txt.setPreferredSize(new Dimension(width, height));
    }

    //DEFAULT PRESET FOR PASSWORDFIELDS
    public static void setPasswordField(JPasswordField txt, int width, int height) {
        Font font = new Font(fontTypeface, Font.PLAIN, 20);

        txt.setBorder(null);
        txt.setBackground(black);
        txt.setForeground(white);
        txt.setFont(font);
        txt.setPreferredSize(new Dimension(width, height));
    }

    //DEFAULT PRESET FOR COMBOBOXS
    public static void setComboBox(JComboBox comboBox, int width, int height) {
        Font font = new Font(fontTypeface, Font.PLAIN, 20);

        comboBox.setFocusable(true);
        comboBox.setBackground(black);
        comboBox.setForeground(white);
        comboBox.setFont(font);
        comboBox.setMaximumSize(new Dimension(width, height));
    }

    //NAVIGATION CONTROLS
    public void goToViewAll(Component comp, Frame frame) {
        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ViewAll newFrame = new ViewAll();
                newFrame.setGUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    //NAVIGATION CONTROLS
    public void goToEditAll(Component comp, Frame frame) {
        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                EditAll newFrame = new EditAll();
                newFrame.setGUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    //NAVIGATION CONTROLS
    public void goToCheckout(Component comp, Frame frame) {
        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Checkout newFrame = new Checkout();
                newFrame.setGUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    //NAVIGATION CONTROLS
    public void goToLogin(Component comp, Frame frame) {
        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "LibrarySystem", 0, 1, icon);

                if (response == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    Login newFrame = new Login();
                    newFrame.setGUI();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    //EXPERIMENTAL FEATURE
    private void showMenuBarAction(Component comp, JMenuBar menuBar) {
        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuBar.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    //EXPERIMENTAL FEATURE
    private void minimizeAction(JButton btn, JFrame frame) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(ICONIFIED);
            }
        });
    }

    //Experimental Feature
    private void exitAction(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Thanks for using LibrarySystem", "LibrarySystem", 1, icon);
                System.exit(0);
            }
        });
    }

}
