/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.beans.PropertyVetoException;

/**
 *
 * @author pc1
 */
public class dashboard extends javax.swing.JFrame {

    database db;
    String sMSGBOX_TITLE = "FINSYS version 1.0";
    static Date td = new Date();
    /**
     * Creates new form dashboard
     */// User Details
    static String sUser = "";
    static String sLogin = DateFormat.getDateTimeInstance().format(td);

    public dashboard() {
        initComponents();
        db = new database();
        setIcon();
    }

    public dashboard(String user, Date date) {
        initComponents();
        db = new database();
        setIcon();
        sUser = user;
        td = date;
        juser.setText(sUser);
        jdate.setText(sLogin);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                UnloadWindow();
            }
        });
    }

    protected void UnloadWindow() {
        try {
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {

                setVisible(false);
                System.exit(0);
            }
        } catch (Exception e) {
        }

    }// Close the Windows

    private void loadJInternalFrame(int intWhich) {
        switch (intWhich) {

            case 2:
                UnloadWindow();
                break;

            case 3:
                try {
                    //Addwindow FormAddwindow = new Addwindow(this);
                    //loadForm("Add UOM", FormAddwindow);
                    uom unit = new uom();
                    loadForm("Add UOM", unit);
                    //desktop.add(unit);
                    //unit.show();
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 4:
                try {
                    Costcenter center = new Costcenter();
                    loadForm("Add Cost Center", center);
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 5:
                try {
                    Itemcategory cat = new Itemcategory();
                    loadForm("Add Category", cat);
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 6:
                try {
//                	Subitemcategory s = new Subitemcategory();
//               loadForm("Sub Item Category", s);
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 7:
                runComponents("Calc.exe");
                break;

            case 8:
                runComponents("Notepad.exe");
                break;

            case 9:
                try {
                    Soemaingroup soemain = new Soemaingroup();
                    loadForm("SOE Main Group", soemain);

                } catch (Exception e) {
                    System.out.println("\nError" + e);
                }
                break;
            case 10:
                try {
                    Item item = new Item();
                    loadForm("Item", item);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 11:
                try {
                    Soegroup soe = new Soegroup();
                    loadForm("SOE Group", soe);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 12:
                try {
                    itemtype i = new itemtype();
                    loadForm("ITEM TYPE", i);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;

            case 13:
                //FormHelpwindow = new Helpwindow(this);
                break;
            case 14:
                try {
                    Ledger ledger = new Ledger();
                    loadForm("Ledger", ledger);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 15:
                try {
                    stockIn in = new stockIn();
                    loadForm("stockIn", in);
                    //in.setMaximum(true);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 16:
                try {
                    itemtype type = new itemtype();
                    loadForm("itemtype", type);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 17:
                try {
                    stockOut out = new stockOut();
                    loadForm("stockOut", out);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 18:
                try {
                    issueReport irep = new issueReport();
                    loadForm("issueReport", irep);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 19:
                try {
                    mstore ms = new mstore();
                    loadForm("issueReport", ms);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 20:
                try {
                    ledgerReport ms = new ledgerReport();
                    loadForm("ledgerReport", ms);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 21:
                try {
                    itemwisereport ms = new itemwisereport();
                    loadForm("Store Ledger Report", ms);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;

            case 22:
                try {
                    itemwisestock ms = new itemwisestock();
                    loadForm("Item Wise Stock Report", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            
                 case 30:
                try {
                    eitemwisestock ms = new eitemwisestock();
                    loadForm(" Essential Item Wise Stock Report", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
                
                 case 31:
                try {
                    Filter_Report ms = new Filter_Report();
                    loadForm(" Filter Report", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;

        }

    }

    protected void loadForm(String Title, JInternalFrame clsForm) {

        boolean xForm = isLoaded(Title);
        if (xForm == false) {
            desktop.add(clsForm);
            clsForm.setVisible(true);
            clsForm.show();
        } else {
            try {
                clsForm.setIcon(false);
                clsForm.setSelected(true);

            } catch (PropertyVetoException e) {
            }
        }
    } // Complete Load Form methode

    protected boolean isLoaded(String FormTitle) {
        JInternalFrame Form[] = desktop.getAllFrames();
        for (int i = 0; i < Form.length; i++) {
            if (Form[i].getTitle().equalsIgnoreCase(FormTitle)) {
                Form[i].show();
                try {
                    Form[i].setIcon(false);
                    Form[i].setSelected(true);

                } catch (PropertyVetoException e) {

                }
                return true;
            }
        }
        return false;

    } // Complete to Verify Form loaded or not

    protected void runComponents(String sComponents) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(sComponents);
        } catch (IOException evt) {
            JOptionPane.showMessageDialog(null, evt.getMessage(), "Error Found", JOptionPane.ERROR_MESSAGE);
        }
    }//Run windows programs

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("shivbari-23x23.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jToolBar = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        juser = new javax.swing.JLabel();
        jdate = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_exit = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu_master = new javax.swing.JMenu();
        jMenuItem_uom = new javax.swing.JMenuItem();
        jMenuItem_category = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem_item = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_costcenter = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem_soemaingroup = new javax.swing.JMenuItem();
        jMenuItem_soegroup = new javax.swing.JMenuItem();
        jMenuItem_ledger = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem_settings = new javax.swing.JMenuItem();
        jMenuItem_calculator = new javax.swing.JMenuItem();
        jMenuItem_notepad = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem_issue_report = new javax.swing.JMenuItem();
        Ledgermenu = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem_help = new javax.swing.JMenuItem();
        jMenuItem_help2 = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jMenu5.setText("jMenu5");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FINSYS - 1.0");
        setExtendedState(6);
        setName("frame_dashboard"); // NOI18N

        jToolBar.setBackground(new java.awt.Color(0, 102, 102));
        jToolBar.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Log Out_32x32.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar.add(jButton1);
        jToolBar.add(jSeparator1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Settings_32x32.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Apps-accessories-calculator-icon32x32.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon-32x32.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar.add(jButton4);
        jToolBar.add(jSeparator2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Information_32x32.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Help_32x32.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar.add(jButton6);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/User_16x16.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Calendar_16x16.png"))); // NOI18N

        juser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        juser.setForeground(new java.awt.Color(255, 255, 255));
        juser.setText("juser");

        jdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jdate.setForeground(new java.awt.Color(255, 255, 255));
        jdate.setText("jdate");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(juser, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(juser, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        desktop.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 946, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Log Out_16x16.png"))); // NOI18N
        jMenuItem_exit.setText("Exit");
        jMenuItem_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_exitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_exit);

        jMenuBar1.add(jMenu1);

        jMenu8.setText("Stock");

        jMenuItem2.setText("Stock In");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuItem3.setText("Stock Out");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuBar1.add(jMenu8);

        jMenu_master.setText("Master");

        jMenuItem_uom.setText("Unit of measurement");
        jMenuItem_uom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_uomActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem_uom);

        jMenuItem_category.setText("Item Category");
        jMenuItem_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_categoryActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem_category);

        jMenuItem6.setText("Type");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem6);

        jMenuItem_item.setText("Item");
        jMenuItem_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_itemActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem_item);
        jMenu_master.add(jSeparator3);

        jMenuItem7.setText("M/S Store");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem7);
        jMenu_master.add(jSeparator4);

        jMenuItem_costcenter.setText("Cost Center");
        jMenuItem_costcenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_costcenterActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem_costcenter);

        jMenu9.setText("Summary of Expenditure (SOE)");

        jMenuItem_soemaingroup.setText("SOE Main Group");
        jMenuItem_soemaingroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_soemaingroupActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem_soemaingroup);

        jMenuItem_soegroup.setText("SOE Group");
        jMenuItem_soegroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_soegroupActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem_soegroup);

        jMenu_master.add(jMenu9);

        jMenuItem_ledger.setText("Ledger");
        jMenuItem_ledger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ledgerActionPerformed(evt);
            }
        });
        jMenu_master.add(jMenuItem_ledger);

        jMenuBar1.add(jMenu_master);

        jMenu4.setText("Tools");

        jMenuItem_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Settings_16x16.png"))); // NOI18N
        jMenuItem_settings.setText("Settings");
        jMenu4.add(jMenuItem_settings);

        jMenuItem_calculator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Apps-accessories-calculator-icon.png"))); // NOI18N
        jMenuItem_calculator.setText("Calculator");
        jMenuItem_calculator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_calculatorActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem_calculator);

        jMenuItem_notepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon.png"))); // NOI18N
        jMenuItem_notepad.setText("Notepad");
        jMenuItem_notepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_notepadActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem_notepad);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Reports");

        jMenuItem_issue_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Text Document_16x16.png"))); // NOI18N
        jMenuItem_issue_report.setText("Issue Report");
        jMenuItem_issue_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_issue_reportActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem_issue_report);

        Ledgermenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon.png"))); // NOI18N
        Ledgermenu.setText("Ledger Wise Menu");
        Ledgermenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LedgermenuActionPerformed(evt);
            }
        });
        jMenu6.add(Ledgermenu);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon.png"))); // NOI18N
        jMenuItem8.setText("Store Ledger(Item Wise)");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon.png"))); // NOI18N
        jMenuItem10.setText("Item Wise Stock");
        jMenu6.add(jMenuItem10);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Notepad-icon.png"))); // NOI18N
        jMenuItem9.setText("Essential Item Wise Stock");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem11.setText("Report");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Help");

        jMenuItem_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Information_16x16.png"))); // NOI18N
        jMenuItem_help.setText("About");
        jMenu7.add(jMenuItem_help);

        jMenuItem_help2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Help_16x16.png"))); // NOI18N
        jMenuItem_help2.setText("Help");
        jMenu7.add(jMenuItem_help2);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
            .addComponent(desktop)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktop)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_notepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_notepadActionPerformed
        runComponents("Notepad.exe");
    }//GEN-LAST:event_jMenuItem_notepadActionPerformed

    private void jMenuItem_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_exitActionPerformed
        UnloadWindow();
    }//GEN-LAST:event_jMenuItem_exitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UnloadWindow();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem_calculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_calculatorActionPerformed
        runComponents("Calc.exe");
    }//GEN-LAST:event_jMenuItem_calculatorActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        runComponents("Calc.exe");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        runComponents("Notepad.exe");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem_uomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_uomActionPerformed
        loadJInternalFrame(3);
    }//GEN-LAST:event_jMenuItem_uomActionPerformed

    private void jMenuItem_soegroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_soegroupActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(11);
    }//GEN-LAST:event_jMenuItem_soegroupActionPerformed

    private void jMenuItem_ledgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ledgerActionPerformed
        loadJInternalFrame(14);
    }//GEN-LAST:event_jMenuItem_ledgerActionPerformed

    private void jMenuItem_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_categoryActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(5);
    }//GEN-LAST:event_jMenuItem_categoryActionPerformed

    private void jMenuItem_costcenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_costcenterActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(4);
    }//GEN-LAST:event_jMenuItem_costcenterActionPerformed

    private void jMenuItem_soemaingroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_soemaingroupActionPerformed
        // TODO add your handling code here:raj
        loadJInternalFrame(9);
    }//GEN-LAST:event_jMenuItem_soemaingroupActionPerformed

    private void jMenuItem_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_itemActionPerformed
        // TODO add your handling code here:

        loadJInternalFrame(10);
    }//GEN-LAST:event_jMenuItem_itemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(15);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(12);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(17);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem_issue_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_issue_reportActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(18);
    }//GEN-LAST:event_jMenuItem_issue_reportActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(19);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void LedgermenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LedgermenuActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(20);
    }//GEN-LAST:event_LedgermenuActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(21);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(22);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(30);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(31);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Ledgermenu;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem_calculator;
    private javax.swing.JMenuItem jMenuItem_category;
    private javax.swing.JMenuItem jMenuItem_costcenter;
    private javax.swing.JMenuItem jMenuItem_exit;
    private javax.swing.JMenuItem jMenuItem_help;
    private javax.swing.JMenuItem jMenuItem_help2;
    private javax.swing.JMenuItem jMenuItem_issue_report;
    private javax.swing.JMenuItem jMenuItem_item;
    private javax.swing.JMenuItem jMenuItem_ledger;
    private javax.swing.JMenuItem jMenuItem_notepad;
    private javax.swing.JMenuItem jMenuItem_settings;
    private javax.swing.JMenuItem jMenuItem_soegroup;
    private javax.swing.JMenuItem jMenuItem_soemaingroup;
    private javax.swing.JMenuItem jMenuItem_uom;
    private javax.swing.JMenu jMenu_master;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar;
    private javax.swing.JLabel jdate;
    private javax.swing.JLabel juser;
    // End of variables declaration//GEN-END:variables
}
