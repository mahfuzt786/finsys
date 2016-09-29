package finsys;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class Addwindow extends JInternalFrame implements ActionListener {

//	Dimension screen 	= Toolkit.getDefaultToolkit().getScreenSize();
    Issue_item i = new Issue_item();
    JFrame JFParentFrame;
    JDesktopPane desktop;
    private JPanel panel1;
    private JPanel panel2;
    private JButton AddBtn;
    private JButton ResetBtn;
    private JButton ExitBtn;
    private JLabel LblUomname, LblUomabbr;
    private JTextField TxtUomname, TxtUomabbr;
    //private JComboBox Emp_Type;
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;

    public static int record;
    String Uomname = "";
    String Uomabbr = "";

    // Class Variables
    clsSettings settings = new clsSettings();
    database db;

    public Addwindow(JFrame getParentFrame) {
        super("ADD UOM ", true, true, true, true);
        db = new database();

        setSize(800, 900);
        JFParentFrame = getParentFrame;
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(7, 7));

        LblUomname = new JLabel(" Unit of Measurement :");
        LblUomabbr = new JLabel(" Abbreviation    :");

        TxtUomname = new JTextField(20);

        TxtUomabbr = new JTextField(20);

        panel1.add(LblUomname);
        panel1.add(TxtUomname);
        panel1.add(LblUomabbr);
        panel1.add(TxtUomabbr);

        panel1.setOpaque(true);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        AddBtn = new JButton("Add");
        ResetBtn = new JButton("Reset");
        ExitBtn = new JButton("Exit");

        panel2.add(AddBtn);
        AddBtn.addActionListener(this);
        panel2.add(ResetBtn);
        ResetBtn.addActionListener(this);
        panel2.add(ExitBtn);
        ExitBtn.addActionListener(this);
        panel2.setOpaque(true);

        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().add(panel1, "CENTER");
        getContentPane().add(panel2, "CENTER");
        setFrameIcon(new ImageIcon("backup.gif"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        //settings.Numvalidator(TxtEmp_No);
    }

    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();

        if (source.equals(AddBtn)) {

            Uomname = "";
            Uomabbr = "";

            Uomname = TxtUomname.getText().trim();
            Uomabbr = TxtUomabbr.getText().trim();

            try {

                if (!Uomname.equals("")
                        && !Uomabbr.equals("")) {

                    i.setItemcode(Uomname);
                    i.setItemname(Uomabbr);
                    int result = db.insertemp(i);

                    if (result == 1) {
                        dialogmessage = "UOM ADDED SUCCESSFULLY";
                        JOptionPane.showMessageDialog(null, dialogmessage,
                                "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Recorded Added");
                        ResetRecord();

                    } else {
                        dialogmessage = "Failed To Insert";
                        JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                                "WARNING!!", JOptionPane.WARNING_MESSAGE);

                    }

                } else {
                    dialogmessage = "Empty Record !!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component) null, dialogmessage, dialogs, dialogtype);

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (source == ResetBtn) {
            ResetRecord();
        } else if (source == ExitBtn) {
            setVisible(false);
            dispose();
        }

    }

    private void ResetRecord() {
        TxtUomname.setText("");
        TxtUomabbr.setText("");

    }

    public void add_Cat_combo(JComboBox cmb) {

        try {

//                Statement stmt = conn.createStatement();
//                
//                String query = "SELECT * FROM Settings";
//                ResultSet rs = stmt.executeQuery(query);
//                               
//                while (rs.next())
//                {
//                	
//
//                	String Txtcmb = rs.getString(2).trim();
//                	record = rs.getInt("Category_Type");
//                	
//                	cmb.addItem(Txtcmb); 
//                	
//                }	
//                	conn.close();
        } catch (Exception ex) {

        }

    }

}
