/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

/**
 *
 * @author pc1
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class stockOut extends javax.swing.JInternalFrame {
//Stockout
     String issue_returncode ,acc_post,issuedate,receiptno,issue_or_return ,costcenterid,tempqty,tempamt  ;
     Double issueamt_value,transportation_amt ;
     //Items
     String itemreceiptno; 
    int itemid,ledgerid,iitd,catid;
    Double reqquantity,issuequantity,itemvalue;
    //DB
    database db;
    Stockouttable i = new Stockouttable();
    Stockoutitemtable j=new Stockoutitemtable();
    String dialogmessage;
    String dialogs;
    Comboitem g;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID="";
    public String ID1="";
    DefaultTableModel model;
    DefaultTableModel model1;
    ArrayList<Itemtable> item,item1;
    ArrayList<Ledgertable> led;
    ArrayList<Categorytable> cat;
    ArrayList<Costcentertable> cc;
    int ucode;
    Logdetails m;
    //for update items
    String tempinvoiceid,tempitemid,tempquantity,temprate,iss;
    //for items jpanel2
    int TOTALITEMS=0;
    Double TOTALGROSS=0.0,TOTALLESS=0.0,TOTALVAT=0.0,TRANSPORT=0.0,TOTALAMOUNT=0.0,totalstockamount,totalstockquantity;
    PatternValidation pattern=new PatternValidation();
    /**
     * Creates new form stock out
     * @param usercode
     */
    public stockOut(int usercode) {
        this.cat = new ArrayList<>();
        initComponents();
        ReloadTable(); 
        btnadd.setVisible(false);
        btnupdate.setVisible(false);
        btndelete.setVisible(false);
         btnadditem.setVisible(false);
        btnupdateitem.setVisible(false);
        btndeleteitem.setVisible(false);
         db=new database();
       ucode=usercode;
        Menu m=db.getPrivilege(usercode,17);
        if(m.getAdd_p()==1){
            btnadd.setVisible(true);
            btnadditem.setVisible(true);
        }
        if(m.getEdit_p()==1){
            btnupdate.setVisible(true);
           btnupdateitem.setVisible(true);
        }
        if(m.getDelete_p()==1){
            btndelete.setVisible(true);
            btndeleteitem.setVisible(true);
        }
        setEnabledAll(jPanel3,false);
        item=db.getItem();
        cc=db.getCC();
        led=db.getLedger();
        cat=db.getCategory();
        System.out.println("For CC");
        comboCC.addItem(new Comboitem(0,"Select"));
        for(Costcentertable c:cc){
           
            comboCC.addItem(new Comboitem(c.getCenterid(),c.getCentername()));
        }
        System.out.println("For ledger");
        comboLedger.addItem(new Comboitem(0,"Select"));
        for(Ledgertable c:led){
           
            comboLedger.addItem(new Comboitem(c.getLedgerid(),c.getLedgername()));
        }
        
        System.out.println("For Category");
       
        categoryCombo.addItem(new Comboitem(0,"Select"));
        for(Categorytable c:cat){
           
            categoryCombo.addItem(new Comboitem(c.getCategoryid(),c.getCategoryname()));
        }
      
     
     
                   
             

    }

    /**
     *
     * @return
     */
    
    //for stock out
    public ArrayList<Stockouttable> getStockoutTable() {
        
        ArrayList<Stockouttable> sTable = new ArrayList<Stockouttable>();
        //String query = "select issue_returncode,acc_post,issueamt_value,receiptno,transportation_amt,issue_or_return,costcenterid,slno , to_char(issuedate,'dd-MM-yyyy') as issuedate from finsys.t_issue_return";
        String query = "SELECT t_issue_return.issue_returncode,t_issue_return.acc_post,t_issue_return.issueamt_value,t_issue_return.receiptno,t_issue_return.transportation_amt,t_issue_return.issue_or_return,t_issue_return.costcenterid,t_issue_return.slno,to_char(t_issue_return.issuedate,'dd-MM-yyyy') as issuedate, m_costcenter.centerid, m_costcenter.centername from finsys.t_issue_return,finsys.m_costcenter where t_issue_return.costcenterid = m_costcenter.centerid order by t_issue_return.slno desc";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockouttable sTab;
            while (rs.next()) {
            
                sTab = new Stockouttable(rs.getString("issue_returncode"), rs.getString("acc_post"), rs.getString("issuedate"),
               rs.getString("receiptno"), rs.getString("issue_or_return"), rs.getInt("costcenterid"),rs.getString("centername"),rs.getDouble("issueamt_value"),rs.getDouble("transportation_amt") );
                sTable.add(sTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTable;
    }
    //for item
    
    public ArrayList<Stockoutitemtable> getStockoutitemTable() {
         
        ArrayList<Stockoutitemtable> sTable = new ArrayList<Stockoutitemtable>();
        //String query = "select * from finsys.t_issue_items inner join finsys.m_item on m_item.itemid=finsys.t_issue_items.itemid where issue_returncode='"+ID+"'";
        String query = "select * from finsys.t_issue_items,finsys.m_itemcategory,finsys.m_ledger,finsys.m_item where m_itemcategory.categoryid = m_item.categoryid AND m_ledger.ledgerid = t_issue_items.ledgerid AND m_item.itemid = t_issue_items.itemid AND t_issue_items.issue_returncode='"+ID+"'";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockoutitemtable sTab;
            while (rs.next()) {
                
                sTab = new Stockoutitemtable(rs.getString("categoryname"),rs.getString("ledgername"),rs.getString("issue_returncode"),rs.getString("itemcode"),rs.getString("itemname"),rs.getInt("itemid"), rs.getInt("ledgerid"),rs.getDouble("reqquantity"), rs.getDouble("issuequantity"), rs.getDouble("itemvalue"),rs.getInt("categoryid"));
                sTable.add(sTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTable;
    }

    //for stock out
    private void ReloadTable() {
        ArrayList<Stockouttable> sitemlist = getStockoutTable();
        model = (DefaultTableModel) tableStockOut.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < sitemlist.size(); i++) {
            row[0] = sitemlist.get(i).getIssue_returncode();
            row[1] = sitemlist.get(i).getIssue_or_return();
            row[2] = sitemlist.get(i).getIssuedate();
            row[3] = sitemlist.get(i).getAcc_post();
            row[4] = sitemlist.get(i).getCostcenterid()+" - "+sitemlist.get(i).getCentername();


            model.addRow(row);
        }
    }
    //for item
    
    private void ReloadTableItem() {
        ResetForm();
        ArrayList<Stockoutitemtable> sitemlist = getStockoutitemTable();
        
        model1 = (DefaultTableModel) tableitem.getModel();
        model1.setRowCount(0);
      //  ArrayList<Stockouttable> s=db.getStockout(issue_returncode);
        
  
        
        Object[] row = new Object[8];
       
        for (int i = 0; i < sitemlist.size(); i++) {
            row[0] = sitemlist.get(i).getCategoryid()+" : "+sitemlist.get(i).getCategoryname();
            row[1] = sitemlist.get(i).getLedgerid()+" : "+sitemlist.get(i).getLedgername();
            row[2]=sitemlist.get(i).getItemid()+" : "+sitemlist.get(i).getItemcode();
            
            row[3] = sitemlist.get(i).getItemname();
           
            row[4] = sitemlist.get(i).getReqquantity();
            row[5] = sitemlist.get(i).getIssuequantity();
            
            row[6] = sitemlist.get(i).getItemvalue();
             row[7] = sitemlist.get(i).getItemvalue()*sitemlist.get(i).getIssuequantity();
            
            TOTALITEMS+=1;
            TOTALGROSS+=sitemlist.get(i).getItemvalue()*sitemlist.get(i).getIssuequantity();
            
            model1.addRow(row);
        }
        
       
        
        totalitems.setText(TOTALITEMS+"");
        grandtotal.setText(TOTALGROSS+"");
      
        
        
    }
    
    public void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
        tableStockOut.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    public void executeSqlQuery(String query, String message) {
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            if ((pst.executeUpdate()) == 1) {
                ReloadTable();
                JOptionPane.showMessageDialog(null, "Data " + message + " Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Data not " + message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //for stock out
    private void ResetRecord() {
        issueId.setText("");
        comboCC.setSelectedIndex(0);
        acc.setSelectedIndex(0);
        ir.setSelectedIndex(0);
        issuedt.setText("");
        itemissueid.setText("");

    }
    //for items
      private void ResetRecordItem() {
        
        categoryCombo.setSelectedIndex(0);
        itemCombo.setSelectedIndex(0);
        comboLedger.setSelectedIndex(0);
        txtReq.setText("");
        txtIssue.setText("");
        itemstock.setText("");
        ivalue.setText("");
        tempinvoiceid="";
        tempitemid="";
        tempquantity="";
        temprate="";

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        issueId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboCC = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStockOut = new javax.swing.JTable();
        accpost = new javax.swing.JLabel();
        acc = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        ir = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        issuedt = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        itemCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtReq = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIssue = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboLedger = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        itemissueid = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableitem = new javax.swing.JTable();
        btnupdateitem = new javax.swing.JButton();
        btnadditem = new javax.swing.JButton();
        btnclearitem = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        itemstock = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ivalue = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        totalitems = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        grandtotal = new javax.swing.JLabel();
        btndeleteitem = new javax.swing.JButton();
        categoryCombo = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Stock Out");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Out Form"));
        jPanel2.setOpaque(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Issue/Return ID :");

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Add_16x16.png"))); // NOI18N
        btnadd.setText("add");
        btnadd.setOpaque(false);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Edit_16x16.png"))); // NOI18N
        btnupdate.setText("update");
        btnupdate.setOpaque(false);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Cancel_16x16.png"))); // NOI18N
        btnclear.setText("clear");
        btnclear.setOpaque(false);
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        issueId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        issueId.setText("**********");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Cost Center : ");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Search_16x16.png"))); // NOI18N
        jLabel1.setText("Search : ");

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Delete_16x16.png"))); // NOI18N
        btndelete.setText("delete");
        btndelete.setOpaque(false);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        tableStockOut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issue ID", "Issue/Return", "Issue Date", "Account Post", "Cost Center"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableStockOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStockOutMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableStockOut);

        accpost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        accpost.setText("Acc Post : ");

        acc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Y", "N" }));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Issue/Return : ");

        ir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "I", "R" }));

        jLabel12.setText("Issue/Return Date :");

        issuedt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        issuedt.setText("**********");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 0, 153));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("** SELECT A ROW TO EDIT OR DELETE **");
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(issueId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(issuedt))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(accpost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCC, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(issueId)
                    .addComponent(jLabel12)
                    .addComponent(issuedt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accpost)
                    .addComponent(acc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btnclear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Form"));
        jPanel3.setOpaque(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Category :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Item Name :");

        itemCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                itemComboItemStateChanged(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Required Quantity :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Issued Quantity :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Ledger :");

        jLabel9.setText("Issue/Return ID :");

        itemissueid.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemissueid.setText("**********");

        tableitem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Ledger", "Item", "Item Name", "Required Qty.", "Issued Qty.", "Item Value", "Total Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableitem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableitemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableitem);

        btnupdateitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Edit_16x16.png"))); // NOI18N
        btnupdateitem.setText("update");
        btnupdateitem.setOpaque(false);
        btnupdateitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateitemActionPerformed(evt);
            }
        });

        btnadditem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Add_16x16.png"))); // NOI18N
        btnadditem.setText("add");
        btnadditem.setOpaque(false);
        btnadditem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadditemActionPerformed(evt);
            }
        });

        btnclearitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Cancel_16x16.png"))); // NOI18N
        btnclearitem.setText("clear");
        btnclearitem.setOpaque(false);
        btnclearitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearitemActionPerformed(evt);
            }
        });

        jLabel10.setText("Item in Stock:: ");

        itemstock.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemstock.setText("**********");

        jLabel13.setText("Item Value ::");

        ivalue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ivalue.setText("**********");

        jLabel23.setText("Total Items ::");

        totalitems.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        totalitems.setText("**********");

        jLabel24.setText("Grand Total ::");

        grandtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        grandtotal.setText("**********");

        btndeleteitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Delete_16x16.png"))); // NOI18N
        btndeleteitem.setText("delete");
        btndeleteitem.setOpaque(false);
        btndeleteitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteitemActionPerformed(evt);
            }
        });

        categoryCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoryComboItemStateChanged(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 0, 153));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("** SELECT A ROW TO EDIT OR DELETE **");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnadditem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnupdateitem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnclearitem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btndeleteitem, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboLedger, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                                        .addComponent(txtIssue, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtReq))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel9))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemissueid, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(categoryCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(itemCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ivalue)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(itemstock)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalitems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(itemissueid))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ivalue)
                    .addComponent(jLabel10)
                    .addComponent(itemstock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIssue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboLedger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadditem)
                    .addComponent(btnupdateitem)
                    .addComponent(btnclearitem)
                    .addComponent(btndeleteitem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(totalitems)
                    .addComponent(jLabel24)
                    .addComponent(grandtotal))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //for stock out
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // update
        
        
        acc_post=(String)acc.getSelectedItem();
       
        issue_or_return=(String) ir.getSelectedItem();
        Comboitem g1 =(Comboitem) comboCC.getSelectedItem();
        int ccid=g1.getKey();
       String ccname=g1.getValue();
         if("".equals(ID)){
            dialogmessage = "PLEASE SELECT RECORD TO UPDATE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if("-".equals(acc_post)){
            dialogmessage = "PLEASE SELECT ACCOUNT POST!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("-".equals(issue_or_return)){
             dialogmessage = "PLEASE SELECT  ISSUE OR RETURN!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(ccid==0){
             dialogmessage = "PLEASE SELECT COST CENTER!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else{
        if (!(ccid==0) && !issue_or_return.equals("-")
           && !acc_post.equals("-")){
        String query;
        query = "update finsys.t_issue_return set acc_post='" +acc_post + "'"
                + ",issue_or_return='" +issue_or_return+ "'"
                
                + ",costcenterid='" +ccid+ "'"
                
                + " where issue_returncode='" + ID + "'";
        executeSqlQuery(query, "updated");
      ;
                  int j=db.getStoutslno(ID);
                  
                   int l=m.Initialisem(0,"t_issue_return",j,"U",ucode,"");
        ResetRecord();
        Refresh();
        ReloadTable();
        ResetForm();
        }
        else {
                dialogmessage = "Empty Record !!!";
                dialogtype = JOptionPane.WARNING_MESSAGE;
                JOptionPane.showMessageDialog(null, dialogmessage, dialogs, dialogtype);

            }

     
        }

    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed
//for stock out
    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        

        acc_post=(String)acc.getSelectedItem();
        
        issue_or_return=(String) ir.getSelectedItem();
        Comboitem g1 =(Comboitem) comboCC.getSelectedItem();
        int ccid=g1.getKey();
       String ccname=g1.getValue();
       if("-".equals(acc_post)){
            dialogmessage = "PLEASE SELECT ACCOUNT POST!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("-".equals(issue_or_return)){
             dialogmessage = "PLEASE SELECT  ISSUE OR RETURN!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(ccid==0){
             dialogmessage = "PLEASE SELECT COST CENTER!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else{
        db = new database();
        try {

            if (!acc_post.equals("-") && !issue_or_return.equals("-")&& !(ccid==0)) {
               i.setAcc_post(acc_post);
               i.setCostcenterid(ccid);
               i.setIssue_or_return(issue_or_return);
               int maxid=db.getmax("SELECT MAX(slno) as max FROM finsys.t_issue_return");
                int result = db.insertStockout(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "ISSUE OR RETURN DETAILS ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
                     m=new Logdetails();
                   int l=m.Initialisem(0,"t_issue_return",maxid,"A",ucode,"");
                    ResetRecord();
                    ReloadTable();

                } else {
                    dialogmessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                dialogmessage = "Empty Record !!!";
                dialogtype = JOptionPane.WARNING_MESSAGE;
                JOptionPane.showMessageDialog(null, dialogmessage, dialogs, dialogtype);

            }

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void tableStockOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockOutMouseClicked
        //Display selected row in textbox
        int i = tableStockOut.getSelectedRow();
        TableModel mod = tableStockOut.getModel();
        ResetForm();
        ResetRecordItem(); 
        Refresh();
        setEnabledAll(jPanel3,true);
        
        System.out.println(i);
        //GLOBAL INVOICE ID
        issue_returncode=(mod.getValueAt(i, 0).toString()).trim();
        //from company
        String cc = (mod.getValueAt(i, 4).toString()).trim();
        String[] splitcc = cc.split("\\s+");
        setSelectedValue(comboCC,Integer.valueOf(splitcc[0]));
        acc.setSelectedItem(mod.getValueAt(i, 3).toString().trim());
        ir.setSelectedItem(mod.getValueAt(i, 1).toString().trim());
        issueId.setText(issue_returncode);
        issuedt.setText(mod.getValueAt(i, 2).toString().trim());
        itemissueid.setText(issue_returncode);
        ID = mod.getValueAt(i, 0).toString().trim();
        iss=mod.getValueAt(i, 1).toString().trim();
        ReloadTableItem();
    }//GEN-LAST:event_tableStockOutMouseClicked

    //for stock out
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        //delete
        
        String sMSGBOX_TITLE = "FINSYS version 1.0";
        if("".equals(ID)){
                    dialogmessage = "PLEASE SELECT RECORD TO DELETE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                
                }else{
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
               
        String query = "delete from finsys.t_issue_return where issue_returncode='" + ID + "'";
        executeSqlQuery(query, "deleted");
        int j=db.getStoutslno(ID);
      int l=m.Initialisem(0,"t_issue_return",j,"D",ucode,"");
         Double gross=0.0,qty=0.0,iva;
        
        ArrayList<Stockoutitemtable> sitemlist = getStockoutitemTable();
       
       Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
        
        for (int i = 0; i < sitemlist.size(); i++) {
            int iid=sitemlist.get(i).getItemid();
            qty=sitemlist.get(i).getIssuequantity();
            iva=sitemlist.get(i).getItemvalue();
            gross=qty*iva;
            ArrayList<Stocktable> d=db.getStock(iid);
            for(Stocktable c:d){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
            }
            prevquantity=totalstockquantity+qty;
            prevrate=totalstockamount+gross;
            query = "update finsys.t_stock set quantity='" + prevquantity+ "',amount='" +prevrate+ "' where itemid='" +iid+ "'";
             PreparedStatement  pst=null;
            try {
                pst = data.conn.prepareStatement(query);
                 int flag = pst.executeUpdate();
                 if(flag==1){
                      query = "delete from finsys.t_issue_items where issue_returncode='" + ID + "' and itemid='"+iid+"'";
                      pst = data.conn.prepareStatement(query);
                      flag = pst.executeUpdate();
                 }
                 
            } catch (SQLException ex) {
                Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        ResetRecord();
        }
        }
            
                else{
                remove(reply);
            }
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String query=search.getText().toUpperCase();
        filter(query);
    }//GEN-LAST:event_searchKeyReleased

    private void tableitemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableitemMouseClicked
        // TODO add your handling code here:
        
      
                
                  
                   //Display selected row in textbox
        int i = tableitem.getSelectedRow();
        TableModel mod1= tableitem.getModel();
        
        
        System.out.println(i);
        //GLOBAL INVOICE ID
      
        //from company
        String CAT = (mod1.getValueAt(i, 0).toString()).trim();
        String[] splitcat = CAT.split("\\s+");
        setSelectedValue(categoryCombo,Integer.valueOf(splitcat[0]));
        String ITEM = (mod1.getValueAt(i, 2).toString()).trim();
        String[] splititem = ITEM.split("\\s+");
        setSelectedValue(itemCombo,Integer.valueOf(splititem[0]));
        String LED = (mod1.getValueAt(i, 1).toString()).trim();
        String[] splitled = LED.split("\\s+");
        setSelectedValue(comboLedger,Integer.valueOf(splitled[0]));
        
        txtReq.setText(mod1.getValueAt(i, 4).toString().trim());
        txtIssue.setText(mod1.getValueAt(i, 5).toString().trim());
        
             db=new database();
                      ArrayList<Stocktable> d=db.getStock(Integer.valueOf(splititem[0]));
         for(Stocktable c:d){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
        }
         Double ival=totalstockamount/totalstockquantity;
         if(totalstockquantity!=0.0){
                  itemstock.setText(String.valueOf(totalstockquantity));
                  ivalue.setText(String.valueOf(ival));
         }
         else{
            itemstock.setText("**********");
            ivalue.setText("**********");
         }
        ID1 = splititem[0];
        tempqty=mod1.getValueAt(i, 5).toString();
        tempamt=mod1.getValueAt(i, 7).toString();
       
    }//GEN-LAST:event_tableitemMouseClicked

    private void btnadditemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadditemActionPerformed
        // TODO add your handling code here:
        
            
        //first add to the total stock
        
       Comboitem g3 =(Comboitem) categoryCombo.getSelectedItem();
       catid=g3.getKey();
                
       Comboitem g1 =(Comboitem) itemCombo.getSelectedItem();
       itemid=g1.getKey();
       itemreceiptno=itemissueid.getText().trim();
       
       Comboitem g2 =(Comboitem) comboLedger.getSelectedItem();
       ledgerid=g2.getKey();
       double val=0.0; 
       
         
       issue_returncode=itemissueid.getText().trim();
       itemvalue=Double.valueOf(ivalue.getText().trim());
       issuequantity=Double.valueOf(txtIssue.getText().trim());
       reqquantity=Double.valueOf(txtReq.getText().trim());
       
        if(catid==0){
            dialogmessage = "PLEASE SELECT CATEGORY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(itemid==0){
             dialogmessage = "PLEASE SELECT  ITEM NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(txtReq.getText().trim())){
             dialogmessage = "PLEASE ENTER REQUIRED QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(txtIssue.getText().trim())){
             dialogmessage = "PLEASE ENTER ISSUE QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(ledgerid==0){
             dialogmessage = "PLEASE SELECT LEDGER!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(itemvalue==0.0){
             dialogmessage = "NO STOCK FOR SELECTED ITEM!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } else if(!pattern.ValidateNumeric(txtReq.getText().trim())){
            dialogmessage = "INVALID REQUIRED QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }          
       else if(!pattern.ValidateNumeric(txtIssue.getText().trim())){
           dialogmessage = "INVALID ISSUE QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       }
        else{
       if(iss.equals("R")){
           
       
       issuequantity=-issuequantity;
       val=issuequantity;
       }
       
        db = new database();
        
          try {
              
              if(val>Double.valueOf(itemstock.getText().trim())){
            dialogmessage = "ISSUE QUANTITY SHOULD NOT BE GREATER THAN TOTAL STOCK QUANTITY";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }else
            if (!(itemid==0)&& !(itemvalue==0.0)&& !(issuequantity==0.0)&&!(ledgerid==0)&&!(reqquantity==0.0) &&!issue_returncode.equals("")) {
               j.setItemid(itemid);
               j.setIssuequantity(issuequantity);
               j.setItemvalue(itemvalue);
               j.setReqquantity(reqquantity);
               j.setLedgerid(ledgerid);
               j.setIssue_returncode(issue_returncode);
           
                int result = db.insertStockoutitem(j);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "ITEM ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
                     m=new Logdetails();
                   int l=m.InitialiseS(0,"t_issue_items",issue_returncode,"A",ucode,"",itemid);
                    //ResetRecordItem();
                    comboLedger.setSelectedIndex(0);
                    txtReq.setText("");
                    txtIssue.setText("");
                    itemstock.setText("");
                    ivalue.setText("");
                    tempinvoiceid="";
                    tempitemid="";
                    tempquantity="";
                    temprate="";
                    ResetForm();
                    ReloadTableItem();

                } else {
                    dialogmessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                dialogmessage = "Empty Record !!!";
                dialogtype = JOptionPane.WARNING_MESSAGE;
                JOptionPane.showMessageDialog(null, dialogmessage, dialogs, dialogtype);

            }

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnadditemActionPerformed

    private void btnupdateitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateitemActionPerformed
        // TODO add your handling code here:
        
        
        //first add to the total stock
       Comboitem g3 =(Comboitem) categoryCombo.getSelectedItem();
       catid=g3.getKey();
       Comboitem g1 =(Comboitem) itemCombo.getSelectedItem();
       itemid=g1.getKey();
       itemreceiptno=itemissueid.getText().trim();
       Comboitem g2 =(Comboitem) comboLedger.getSelectedItem();
       ledgerid=g2.getKey();
         
       
         
       
       
      
        Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
          if("".equals(ID1)){
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }else if(catid==0){
            dialogmessage = "PLEASE SELECT CATEGORY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(itemid==0){
             dialogmessage = "PLEASE SELECT  ITEM NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(txtReq.getText().trim())){
             dialogmessage = "PLEASE ENTER REQUIRED QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(txtIssue.getText().trim())){
             dialogmessage = "PLEASE ENTER ISSUE QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if(ledgerid==0){
             dialogmessage = "PLEASE SELECT LEDGER!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if(!pattern.ValidateNumeric(txtReq.getText().trim())){
            dialogmessage = "INVALID REQUIRED QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }          
       else if(!pattern.ValidateNumeric(txtIssue.getText().trim())){
           dialogmessage = "INVALID ISSUE QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       }else{
        issuequantity=Double.valueOf(txtIssue.getText().trim());
       reqquantity=Double.valueOf(txtReq.getText().trim());
       
        ArrayList<Stocktable> d=db.getStock(itemid);
         for(Stocktable c:d){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
        }
         
         System.out.println(totalstockamount+"before update "+totalstockquantity);
        ArrayList<Stockoutitemtable> temp=db.getStockoutitem(itemreceiptno, itemid) ;
         for(Stockoutitemtable c:temp){
             totalamt=c.getIssuequantity()*c.getItemvalue();
             prevquantity=c.getIssuequantity();
        }
          if(iss.equals("R")){
            
       totalamt=-totalamt;
       prevquantity=-prevquantity;
       }
         System.out.println(totalamt+"revert back stock "+prevquantity);
         updatestockamount=totalstockamount+totalamt;
         updatestockquantity=totalstockquantity+prevquantity;
         System.out.println(updatestockamount+"  in"+updatestockamount);
         itemvalue=updatestockamount/updatestockquantity;
         if(itemvalue==0.0){
             dialogmessage = "NO STOCK FOR SELECTED ITEM!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } else{
        db = new database();
        try {
            
            
        
            
        if(ID1==null){
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
       
        else if( itemid==0&& itemvalue==0.0&& issuequantity==0.0&&ledgerid==0&& issuequantity==0.0){
            dialogmessage = "Empty Record!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
        else if(issuequantity>totalstockquantity){
            dialogmessage = "ISSUE QUANTITY SHOULD NOT BE GREATER THAN TOTAL STOCK QUANTITY";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }else{
             String query = "update finsys.t_stock set quantity='" + updatestockquantity+ "',amount='" +updatestockamount+ "' where itemid='" +itemid+ "'";
              PreparedStatement  pst = data.conn.prepareStatement(query);
               int flag = pst.executeUpdate();
               System.out.println(flag+"  in");
       if(flag==1){
               
         query = "update finsys.t_issue_items set reqquantity='" + reqquantity+ "',issuequantity='" + issuequantity + "',ledgerid='"+ledgerid+"' where issue_returncode='" +itemreceiptno+ "' and itemid='" + ID1 + "'";
         executeSqlQuery(query, "updated");
          m=new Logdetails();
          int l=m.InitialiseS(0,"t_issue_items",itemreceiptno,"U",ucode,"",Integer.valueOf(ID1));
         ArrayList<Stocktable> d1=db.getStock(itemid);
         for(Stocktable c:d1){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
        }
          System.out.println(totalstockamount+"  after update "+totalstockquantity);
           if(iss.equals("R")){
            
       totalamt=-totalamt;
       issuequantity=-issuequantity;
       }
          
             query = "update finsys.t_stock set quantity='" +( totalstockquantity-issuequantity)+ "',amount='" +(totalstockamount-itemvalue*issuequantity)+ "' where itemid='" +itemid+ "'";
                pst = data.conn.prepareStatement(query);
                flag = pst.executeUpdate();
        //ResetRecordItem();
        //reset throwing exception
        itemstock.setText("");
        ivalue.setText("");
        txtReq.setText("");
        txtIssue.setText("");
        comboLedger.setSelectedIndex(0);
        
        ResetForm();
        System.out.println("1");
         
         System.out.println("2");
        ReloadTableItem();
        System.out.println("3");
       }
        else{
            dialogmessage = "Failed to Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
                }
        
        }
       

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
         }
       }
    }//GEN-LAST:event_btnupdateitemActionPerformed

    private void btnclearitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearitemActionPerformed
        // TODO add your handling code here:
        ResetRecordItem();
    }//GEN-LAST:event_btnclearitemActionPerformed

    private void btndeleteitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteitemActionPerformed
        // TODO add your handling code here:
         Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
        System.out.println(ID+" "+ID1);
         String sMSGBOX_TITLE = "FINSYS version 1.0";
          if("".equals(ID)||"".equals(ID1)){
                    dialogmessage = "PLEASE SELECT RECORD TO DELETE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                
                }
          else{
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
                
                 String query = "delete from finsys.t_stockin_items where invoiceid='" + ID + "' and itemid='"+ID1+"'";
     
                executeSqlQuery(query, "deleted");
                 m=new Logdetails();
          int l=m.InitialiseS(0,"t_issue_items",ID,"U",ucode,"",Integer.valueOf(ID1));
                ArrayList<Stocktable> d=db.getStock(Integer.valueOf(ID1));
                 for(Stocktable c:d){
                    totalstockamount=Double.valueOf(c.getAmount());
                     totalstockquantity=Double.valueOf(c.getQuantity());
                }
                    prevquantity=totalstockquantity+Double.valueOf(tempqty);
                prevrate=totalstockamount+Double.valueOf(tempamt);
                query = "update finsys.t_stock set quantity='" + prevquantity+ "',amount='" +prevrate+ "' where itemid='" +ID1+ "'";
                PreparedStatement  pst=null;
                try {
                pst = data.conn.prepareStatement(query);
                 int flag = pst.executeUpdate();
                
                 
                } catch (SQLException ex) {
                Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                remove(reply);
            }
          }
    }//GEN-LAST:event_btndeleteitemActionPerformed

    private void categoryComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoryComboItemStateChanged
        // TODO add your handling code here:
         Object it = evt.getItem();
                itemCombo.removeAllItems();
                System.out.println("Affected items: " + it.toString());
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                     
                    itemCombo.addItem(new Comboitem(0,"Select Item"));
                
                     Comboitem g1=(Comboitem) categoryCombo.getSelectedItem();
                     int cat1=g1.getKey();
                     System.out.println("cat selected: "+cat1);
                     item1=db.getItem1(cat1);
                     for(Itemtable c:item1){
                          System.out.println("items selected: "+c.getItemname());
                          itemCombo.addItem(new Comboitem(c.getItemid(),c.getItemname()));
                     }
                   
                }

                
    }//GEN-LAST:event_categoryComboItemStateChanged

    private void itemComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemComboItemStateChanged
        // TODO add your handling code here:
          if (evt.getStateChange() == ItemEvent.SELECTED) {
         g =(Comboitem) itemCombo.getSelectedItem();
         iitd=g.getKey();
         
         ArrayList<Stocktable> d=db.getStock(iitd);
                      if(d==null){
                            System.out.println("item id: " );
                          itemstock.setText("0");
                          ivalue.setText("0");
                      }
                      else{
                        for(Stocktable c:d){
                        totalstockamount=Double.valueOf(c.getAmount());
                        totalstockquantity=Double.valueOf(c.getQuantity());
                        System.out.println("item id: " + totalstockamount+"h: "+totalstockquantity);
                             }
                        
                        if(totalstockquantity!=null){
                            
                        itemstock.setText(String.valueOf(totalstockquantity));
                        if("0.0".equals(String.valueOf(totalstockquantity))){
                            ivalue.setText(String.valueOf(0.0));
                        }else{
                        ivalue.setText(String.valueOf(totalstockamount/totalstockquantity));
                        }
                        }
                        }
                       
                      

          }      //System.out.println("item id: " + itid);
        
    }//GEN-LAST:event_itemComboItemStateChanged

    
       public void setSelectedValue(JComboBox combobox,int value){
            Comboitem item;
            for(int i=0;i<combobox.getItemCount();i++){
                item=(Comboitem)combobox.getItemAt(i);
                if(item.getKey()==value){
                    combobox.setSelectedIndex(i);
                    break;
                }
            }
    }
      
   public void setEnabledAll(Object object, boolean state) {
    if (object instanceof Container) {
        Container c = (Container)object;
        Component[] components = c.getComponents();
        for (Component component : components) {
            setEnabledAll(component, state);
            component.setEnabled(state);
        }
    }
    else {
        if (object instanceof Component) {
            Component component = (Component)object;
            component.setEnabled(state);
        }
    }
}
   public void ResetForm(){
       
        TOTALITEMS=0;
        TOTALGROSS=0.0;
        TOTALLESS=0.0;
        TOTALVAT=0.0;
        TRANSPORT=0.0;
        TOTALAMOUNT=0.0;
        
        
        
        
   }
   public void Refresh(){
        setEnabledAll(jPanel3,false);
        model1 = (DefaultTableModel) tableitem.getModel();
        model1.setRowCount(0);
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> acc;
    private javax.swing.JLabel accpost;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnadditem;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnclearitem;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btndeleteitem;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnupdateitem;
    private javax.swing.JComboBox<Comboitem> categoryCombo;
    private javax.swing.JComboBox<Comboitem> comboCC;
    private javax.swing.JComboBox<Comboitem> comboLedger;
    private javax.swing.JLabel grandtotal;
    private javax.swing.JComboBox<String> ir;
    private javax.swing.JLabel issueId;
    private javax.swing.JLabel issuedt;
    private javax.swing.JComboBox<Comboitem> itemCombo;
    private javax.swing.JLabel itemissueid;
    private javax.swing.JLabel itemstock;
    private javax.swing.JLabel ivalue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField search;
    private javax.swing.JTable tableStockOut;
    private javax.swing.JTable tableitem;
    private javax.swing.JLabel totalitems;
    private javax.swing.JTextField txtIssue;
    private javax.swing.JTextField txtReq;
    // End of variables declaration//GEN-END:variables

}
