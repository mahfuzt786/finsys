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
import java.util.ArrayList;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author pc1
 */
public class dashboard_new extends javax.swing.JFrame {

    database db;
    String sMSGBOX_TITLE = "FINSYS version 1.0";
    static Date td = new Date();
    /**
     * Creates new form dashboard
     */// User Details
    static String sUser = "";
    static String sLogin = DateFormat.getDateTimeInstance().format(td);
    database data = new database();
    DefaultTableModel model, model1;
    int logId = 0;
    ArrayList<Menu> m = new ArrayList<>();
    static int usercode = 0;
    boolean m1 = false, m2 = false, m3 = false, m4 = false, m5 = false;
    ArrayList<JMenuItem> f = new ArrayList<JMenuItem>();

    public dashboard_new() {

        initComponents();

        ReloadTableStock();
        ReloadTableLog();
        db = new database();

        setIcon();
    }

    public dashboard_new(String user, Date date, int logid, int usercd) {
        initComponents();
        ReloadTableStock();
        ReloadTableLog();
        db = new database();
        setIcon();
        sUser = user;
        td = date;
        juser.setText(sUser);
        jdate.setText(sLogin);
        logId = logid;
        usercode = usercd;
        m = db.getMenu(usercode);
        int i = 0;
        JMenuItem o;
        JSeparator k = new JSeparator();
        for (Menu mm : m) {
            //stock menu
            if (mm.getTabid() == 2) {
                o = new JMenuItem(new SomeAction(mm.getMenuname(), mm.getMenucode()));
                m2 = true;
                StockMenu.add(o);
                i++;
                StockMenu.add(k);
            }
            //master menu
            if (mm.getTabid() == 3) {
                o = new JMenuItem(new SomeAction(mm.getMenuname(), mm.getMenucode()));

                m3 = true;
                jMenu_master.add(o);
                i++;
                jMenu_master.add(k);
            }
            //user management menu
            if (mm.getTabid() == 6) {
                System.out.println(mm.getMenuname() + mm.getMenucode());
                o = new JMenuItem(new SomeAction(mm.getMenuname(), mm.getMenucode()));
                m4 = true;
                Managemenu.add(o);
                i++;
                Managemenu.add(k);
            }

            //report
            if (mm.getTabid() == 4) {
                System.out.println(mm.getMenuname() + mm.getMenucode());
                o = new JMenuItem(new SomeAction(mm.getMenuname(), mm.getMenucode()));
                m1 = true;
                Reportmenu.add(o);
                i++;
                Reportmenu.add(k);
            }

            //db
            if (mm.getTabid() == 5) {
                System.out.println("db"+mm.getMenuname() + mm.getMenucode());
                o = new JMenuItem(new SomeAction(mm.getMenuname(), mm.getMenucode()));
                m5 = true;
                dbmenu.add(o);
                i++;
                dbmenu.add(k);
            }

            System.out.println(m2 + "and" + m3);

        }

        if (m2 == false) {
            StockMenu.setVisible(false);
        }

        if (m3 == false) {
            jMenu_master.setVisible(false);
        }
        if (m1 == false) {
            Reportmenu.setVisible(false);
        }
        if (m4 == false) {
            Managemenu.setVisible(false);
        }

        if (m5 == false) {
            dbmenu.setVisible(false);
        }
        System.out.println("No. of menu: " + i);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                UnloadWindow();
            }
        });
    }

    public ArrayList<logtable> getLogTable() {
        ArrayList<logtable> logTable = new ArrayList<logtable>();
        String query = "select logid,logname,to_char(logdate,'dd-MM-yyyy hh:mm:ss AM')AS logdate,to_char(logouttime,'dd-MM-yyyy hh:mm:ss AM')AS logouttime from finsys.logdetails order by logid desc";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            logtable log;
            while (rs.next()) {
                log = new logtable(rs.getInt("logid"), rs.getString("logname"), rs.getString("logdate"), rs.getString("logouttime"));
                logTable.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logTable;
    }

    private void ReloadTableLog() {
        ArrayList<logtable> logitemlist = getLogTable();
        model = (DefaultTableModel) jtable_logtable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < logitemlist.size(); i++) {
            row[0] = logitemlist.get(i).getLoginId();
            row[1] = logitemlist.get(i).getLoginName();
            row[2] = logitemlist.get(i).getLoginDate();
            row[3] = logitemlist.get(i).getLogouttime();
            model.addRow(row);
        }
    }

    public ArrayList<totalstocktable> getItemTable() {
        ArrayList<totalstocktable> stockTable = new ArrayList<totalstocktable>();
        String query;
        query = "SELECT t_stock.quantity,t_stock.amount,  m_item.itemcode, m_item.itemname,m_itemcategory.categorycode, m_itemcategory.categoryname, m_itemtype.itemtypename,t_uom.uomabbr, t_uom.uomname FROM finsys.t_stock,finsys.m_item,finsys.m_itemcategory,finsys.t_uom,finsys.m_itemtype WHERE t_stock.itemid = m_item.itemid AND m_item.categoryid = m_itemcategory.categoryid AND m_item.itemtypeid = m_itemtype.itemtypeid AND t_uom.uomcode = m_item.uomcode";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            totalstocktable totstock;
            while (rs.next()) {
                totstock = new totalstocktable(rs.getInt("quantity"),
                        rs.getInt("amount"),
                        rs.getString("itemcode"),
                        rs.getString("itemname"),
                        rs.getString("categorycode"),
                        rs.getString("categoryname"),
                        rs.getString("itemtypename"),
                        rs.getString("uomabbr"),
                        rs.getString("uomname"));
                stockTable.add(totstock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockTable;
    }

    private void ReloadTableStock() {
        ArrayList<totalstocktable> stockitemlist = getItemTable();
        model1 = (DefaultTableModel) jtable_itemtable.getModel();
        model1.setRowCount(0);
        Object[] row = new Object[9];
        for (int i = 0; i < stockitemlist.size(); i++) {
            row[0] = stockitemlist.get(i).getItemCode();
            row[1] = stockitemlist.get(i).getItemName();
            row[2] = stockitemlist.get(i).getCategoryCode();
            row[3] = stockitemlist.get(i).getCategoryName();
            row[4] = stockitemlist.get(i).getUomName();
            row[5] = stockitemlist.get(i).getUomAbbr();
            row[6] = stockitemlist.get(i).getItemTypeName();
            row[7] = stockitemlist.get(i).getAmt();
            row[8] = stockitemlist.get(i).getQty();

            model1.addRow(row);
        }
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model1);
        jtable_itemtable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    class SomeAction extends AbstractAction {

        Integer cd = 0;

        public SomeAction(String text, Integer menucode) {
            super(text);

            cd = menucode;
        }

        public void actionPerformed(ActionEvent e) {
            loadJInternalFrame(cd);
            //JOptionPane.showMessageDialog( null, "Action occured: " + getValue( NAME ) );
        }
    }

    protected void UnloadWindow() {
        try {
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {

                System.out.println("in yes" + logId);
                db.logout(logId);

                setVisible(false);
                System.exit(0);
            } else {
                setVisible(true);
                remove(reply);
            }

        } catch (Exception e) {
        }

    }// Close the Windows

    protected void logOut() {
        try {
            System.out.println("Logout :" + logId);
            db.logout(logId);
            setVisible(false);
            loginform login = new loginform();
            login.setVisible(true);
        } catch (Exception e) {
        }

    }

    private void loadJInternalFrame(int intWhich) {
        switch (intWhich) {

            case 2:
                UnloadWindow();
                break;

            case 3:
                try {
                    //Addwindow FormAddwindow = new Addwindow(this);
                    //loadForm("Add UOM", FormAddwindow);
                    uom unit = new uom(usercode);
                    loadForm("Add UOM", unit);
                    //desktop.add(unit);
                    //unit.show();
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 4:
                try {
                    Costcenter center = new Costcenter(usercode);
                    loadForm("Add Cost Center", center);
                } catch (Exception e) {
                    System.out.println("\nError");
                }
                break;

            case 5:
                try {
                    Itemcategory cat = new Itemcategory(usercode);
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
                    Soemaingroup soemain = new Soemaingroup(usercode);
                    loadForm("SOE Main Group", soemain);

                } catch (Exception e) {
                    System.out.println("\nError" + e);
                }
                break;
            case 10:
                try {
                    Item item = new Item(usercode);
                    loadForm("Item", item);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 11:
                try {
                    Soegroup soe = new Soegroup(usercode);
                    loadForm("SOE Group", soe);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 12:
                try {
                    itemtype i = new itemtype(usercode);
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
                    Ledger ledger = new Ledger(usercode);
                    loadForm("Ledger", ledger);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 15:
                try {
                    stockIn in = new stockIn(usercode);
                    loadForm("stockIn", in);
                    //in.setMaximum(true);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 16:
                try {
                    itemtype type = new itemtype(usercode);
                    loadForm("itemtype", type);

                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 17:
                try {
                    stockOut out = new stockOut(usercode);
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
                    mstore ms = new mstore(usercode);
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
                    loadForm(" Report", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 32:
                try {
                    ReportF ms = new ReportF();
                    loadForm(" Filter Report", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 33:
                try {
                    openingStock ms = new openingStock(usercode);
                    loadForm(" openingStock", ms);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 34:
                try {
                    backup bck = new backup(usercode);
                    loadForm("Backup", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 35:
                try {
                    restore bck = new restore(usercode);
                    loadForm("Restore", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;

            case 100:
                try {
                    Menumanagement bck = new Menumanagement(usercode);
                    loadForm("Backup", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 101:
                try {
                    User bck = new User(usercode);
                    loadForm("Backup", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }
                break;
            case 102:
                try {
                    Log_Report bck = new Log_Report();
                    loadForm("Log Report", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }

                break;
            case 103:
                try {
                    Resetpwd bck = new Resetpwd(usercode, sUser);
                    loadForm("Log Report", bck);
                } catch (Exception e) {
                    System.out.println("\nError :" + e);
                }

                break;
                case 104:
                try {
                    Log_Report_Filter bck = new Log_Report_Filter();
                    loadForm("Filter Log Report", bck);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtable_itemtable = new javax.swing.JTable();
        searchitem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_logtable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem_exit = new javax.swing.JMenuItem();
        StockMenu = new javax.swing.JMenu();
        jMenu_master = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem_settings = new javax.swing.JMenuItem();
        jMenuItem_calculator = new javax.swing.JMenuItem();
        jMenuItem_notepad = new javax.swing.JMenuItem();
        Reportmenu = new javax.swing.JMenu();
        dbmenu = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem_help = new javax.swing.JMenuItem();
        jMenuItem_help2 = new javax.swing.JMenuItem();
        Managemenu = new javax.swing.JMenu();

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

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtable_itemtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM CODE", "ITEM NAME", "CATEGORY CODE", "CATEGORY NAME", "UOM NAME", "UOM ABBR", "TYPE", "AMOUNT", "QUANTITY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtable_itemtable);

        searchitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchitemActionPerformed(evt);
            }
        });
        searchitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchitemKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Search_16x16.png"))); // NOI18N
        jLabel3.setText("Search : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchitem, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(searchitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Stock", new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Add_16x16.png")), jPanel1); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jtable_logtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LOG ID", "USERNAME", "LOGIN DATE AND TIME", "LOGOUT DATE AND TIME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtable_logtable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Log Details", new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/User_16x16.png")), jPanel4); // NOI18N

        desktop.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Key_16x16.png"))); // NOI18N
        jMenuItem2.setText("Reset Password");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

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

        StockMenu.setText("Stock");
        jMenuBar1.add(StockMenu);

        jMenu_master.setText("Master");
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

        Reportmenu.setText("Reports");
        jMenuBar1.add(Reportmenu);

        dbmenu.setText("Database");
        jMenuBar1.add(dbmenu);

        jMenu7.setText("Help");

        jMenuItem_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Information_16x16.png"))); // NOI18N
        jMenuItem_help.setText("About");
        jMenu7.add(jMenuItem_help);

        jMenuItem_help2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Help_16x16.png"))); // NOI18N
        jMenuItem_help2.setText("Help");
        jMenu7.add(jMenuItem_help2);

        jMenuBar1.add(jMenu7);

        Managemenu.setText("User Management");
        jMenuBar1.add(Managemenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING)
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

    private void searchitemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchitemKeyReleased
        // TODO add your handling code here:
        String query = searchitem.getText().trim().toUpperCase();

        filter(query);

    }//GEN-LAST:event_searchitemKeyReleased

    public void executeSqlQuery(String query) {
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void searchitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchitemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchitemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        loadJInternalFrame(103);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        logOut();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard_new.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard_new.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard_new.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard_new.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard_new().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Managemenu;
    private javax.swing.JMenu Reportmenu;
    private javax.swing.JMenu StockMenu;
    private javax.swing.JMenu dbmenu;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem_calculator;
    private javax.swing.JMenuItem jMenuItem_exit;
    private javax.swing.JMenuItem jMenuItem_help;
    private javax.swing.JMenuItem jMenuItem_help2;
    private javax.swing.JMenuItem jMenuItem_notepad;
    private javax.swing.JMenuItem jMenuItem_settings;
    private javax.swing.JMenu jMenu_master;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar;
    private javax.swing.JLabel jdate;
    private javax.swing.JTable jtable_itemtable;
    private javax.swing.JTable jtable_logtable;
    private javax.swing.JLabel juser;
    private javax.swing.JTextField searchitem;
    // End of variables declaration//GEN-END:variables
}
