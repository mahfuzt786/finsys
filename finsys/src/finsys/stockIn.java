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
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class stockIn extends javax.swing.JInternalFrame {

     
      String slno;
      String invoiceno;
      String total_amt_value;
     
      String entrydate;
      String transportation_amt;
      String less_per;
       String from_company_id;
      String tax_invoice_no;
    
      String tax_invoice_date;
      String challan_no;
      String challan_date;
      String purchase_order_no;
      String purchase_order_date;
      String vat_per;
      String invoiceid;
      String total_gross_amt;
      String companyname;
      PatternValidation pattern=new PatternValidation();
     String companyid,cname,invoice,invoicedt,challan,challandt,order,orderdt;
     private int itemid;
    private String itemcode,itemname,invid,item_rate,quantity,grossvalue;
    database db;
    Stockintable i = new Stockintable();
    Stockinitemtable j = new Stockinitemtable();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID="";
    public String ID1="";
    DefaultTableModel model;
    DefaultTableModel model1;
    ArrayList<Itemtable> item;
    ArrayList<Companytable> ms;
    //for update items
    String tempinvoiceid,tempitemid,tempquantity,temprate;
    //for items jpanel2
    int TOTALITEMS=0;
    Double TOTALGROSS=0.0,TOTALLESS=0.0,TOTALVAT=0.0,TRANSPORT=0.0,TOTALAMOUNT=0.0;

    /**
     * Creates new form stock in
     */
    public stockIn() {
        
        initComponents();
        ReloadTable();
        setEnabledAll(jPanel2,false);
        
         db=new database();
        ms=db.getCompany();
        item=db.getItem();
        
      
         System.out.println("Line1");
        itemCombo.addItem(new Comboitem(0,"Select Item"));
        for(Itemtable c:item){
        
            itemCombo.addItem(new Comboitem(c.getItemid(),c.getItemname()));
        }
       
        System.out.println("Line2");
        msCombo.addItem(new Comboitem(0,"Select"));
        for(Companytable c:ms){
           
            msCombo.addItem(new Comboitem(c.getCompanyid(),c.getCompanyname()));
        }
        System.out.println("Line3");
       
       
        
    }

    /**
     *
     * @return
     */
    public ArrayList<Stockintable> getStockintable() {
        ArrayList<Stockintable> sTable = new ArrayList<Stockintable>();
        String query = "select t.slno,t.invoiceno,t.total_amt_value,to_char(t.entrydate,'dd-MM-yyyy') AS entrydate ,t.transportation_amt,t.less_per,t.from_company_id "
                + ",t.tax_invoice_no ,to_char(t.tax_invoice_date,'dd-MM-yyyy')AS tax_invoice_date ,t.challan_no ,to_char(t.challan_date,'dd-MM-yyyy') AS challan_date,t.purchase_order_no,to_char(t.purchase_order_date,'dd-MM-yyyy') AS purchase_order_date,"
                + "t.vat_per,invoiceid,t.total_gross_amt ,m.companyname"
                + " from finsys.t_stockin t inner join m_fromcompany m on m.companyid=t.from_company_id ";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockintable sTab;
          
            while (rs.next()) {
                sTab = new Stockintable(rs.getString("slno"), rs.getString("invoiceno"),rs.getString("total_amt_value"), 
                        rs.getString("entrydate"),rs.getString("transportation_amt"), rs.getString("less_per"),
                        rs.getString("from_company_id"), rs.getString("tax_invoice_no"), rs.getString("tax_invoice_date"), rs.getString("challan_no"),
                        rs.getString("challan_date"), rs.getString("purchase_order_no"), rs.getString("purchase_order_date"), 
                        rs.getString("vat_per"), rs.getString("invoiceid"),rs.getString("total_gross_amt"), rs.getString("companyname"));
                sTable.add(sTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTable;
    }

    private void ReloadTable() {
        ArrayList<Stockintable> sitemlist = getStockintable();
        model = (DefaultTableModel) invoicetable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for (int i = 0; i < sitemlist.size(); i++) {
            String ms=sitemlist.get(i).getFrom_company_id()+":"+sitemlist.get(i).getCompanyname();
            String tax=sitemlist.get(i).getTax_invoice_no()+":"+sitemlist.get(i).getTax_invoice_date();
            String challan=sitemlist.get(i).getChallan_no()+":"+sitemlist.get(i).getChallan_date();
            String pur=sitemlist.get(i).getPurchase_order_no()+":"+sitemlist.get(i).getPurchase_order_date();
            row[0]=sitemlist.get(i).getInvoiceid();
            row[1] = ms;
            row[2] = tax;
            row[3] = challan;
            row[4] = pur;
            row[5] = sitemlist.get(i).getVat_per();
            row[6] = sitemlist.get(i).getTransportation_amt();
            row[7] = sitemlist.get(i).getLess_per();
            row[8] = sitemlist.get(i).getEntrydate();
            
           
            model.addRow(row);
        }
    }
    public void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
        invoicetable.setRowSorter(tr);
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
      public int executeSqlQuery1(String query, String message) throws SQLException {
          PreparedStatement pst=null;
        try {
           pst = data.conn.prepareStatement(query);
            if ((pst.executeUpdate()) == 1) {
                ReloadTable();
                JOptionPane.showMessageDialog(null, "Data " + message + " Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Data not " + message);
            }
            
           
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
         return pst.executeUpdate();
    }

    private void ResetRecord() {
        txtinvoiceNo.setText("");
        txtchalanNo.setText("");
        msCombo.setSelectedIndex(0);
        challnodate.setDate(null);
        taxinvoicedate.setDate(null);
        purchasedate.setDate(null);
        txtquantity.setText("");
        itemCombo.setSelectedIndex(0);
        txtvat.setText("");
        txtless.setText("");
        txttransport.setText("");
        txtorderNo.setText("");
         labelInvoiceId.setText("");
        invoiceId.setText("");
        date.setText("");
        labelEntryDate.setText("");

    }

    public ArrayList<Stockinitemtable> getStockinitemtable() {
        ArrayList<Stockinitemtable> sTable = new ArrayList<Stockinitemtable>();
        String query = "select t.invoiceid,t.itemid,t.item_rate,t.quantity ,m.itemcode,m.itemname,(t.item_rate*t.quantity) as grossvalue"
               
                + " from finsys.t_stockin_items t inner join m_item m on m.itemid=t.itemid where t.invoiceid='"+invoiceId.getText().trim()+"'";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockinitemtable siTab;
        
            while (rs.next()) {
                siTab = new Stockinitemtable(rs.getInt("itemid"), rs.getString("itemcode"),rs.getString("itemname"),rs.getString("invoiceid"), 
                        rs.getString("item_rate"),rs.getString("quantity"), rs.getString("grossvalue"));
                      
                sTable.add(siTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTable;
    }
    
     private void ReloadTableItem() {
        ArrayList<Stockinitemtable> sitemlist = getStockinitemtable();
        model1 = (DefaultTableModel) tableItem.getModel();
        model1.setRowCount(0);
        ArrayList<Stockintable> s=db.getStockIn(invoiceid);
        String l="",v="",t="";
        for (int i = 0; i < s.size(); i++) {
            l=s.get(i).getLess_per();
            v=s.get(i).getVat_per();
            t=s.get(i).getTransportation_amt();
           
        }
        
        Object[] row = new Object[6];
       
        for (int i = 0; i < sitemlist.size(); i++) {
            row[0]=sitemlist.get(i).getItemid();
            row[1]=sitemlist.get(i).getItemcode();
            row[2] = sitemlist.get(i).getItemname();
            row[3] = sitemlist.get(i).getItem_rate();
            row[4] = sitemlist.get(i).getQuantity();
            row[5] = sitemlist.get(i).getGrossvalue();
            TOTALITEMS+=1;
            TOTALGROSS+=Double.valueOf(sitemlist.get(i).getGrossvalue());
            
            model1.addRow(row);
        }
        
        TOTALLESS=((Double.valueOf(l)/100)*TOTALGROSS);
        TOTALVAT=(Double.valueOf(v)/100)*TOTALGROSS;
        TRANSPORT=Double.valueOf(t);
        TOTALAMOUNT=(TOTALGROSS+TOTALVAT+TRANSPORT)-TOTALLESS;
        totalitems.setText(TOTALITEMS+"");
        labelGross.setText(TOTALGROSS+"");
        labelvat.setText(TOTALVAT+"");
        labelless.setText(TOTALLESS+"");
        labeltran.setText(TRANSPORT+"");
        labelTotal.setText(TOTALAMOUNT+"");
        
        
    }
     private void ResetRecordItem() {
        
        itemCombo.setSelectedIndex(0);
        txtquantity.setText("");
        rate.setText("");
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoicetable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtinvoiceNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtchalanNo = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtorderNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtvat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txttransport = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtless = new javax.swing.JTextField();
        labelInvoiceId = new javax.swing.JLabel();
        labelEntryDate = new javax.swing.JLabel();
        msCombo = new javax.swing.JComboBox<>();
        taxinvoicedate = new org.jdesktop.swingx.JXDatePicker();
        challnodate = new org.jdesktop.swingx.JXDatePicker();
        purchasedate = new org.jdesktop.swingx.JXDatePicker();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        itemCombo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        rate = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItem = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        labelGross = new javax.swing.JLabel();
        labelvat = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        invoiceId = new javax.swing.JLabel();
        purchaseOrder = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        btnupdateitem = new javax.swing.JButton();
        btnadditem = new javax.swing.JButton();
        btnclearitem = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        totalitems = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        labeltran = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        labelless = new javax.swing.JLabel();
        btndone = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        btndeleteitem = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Stock In Form");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoice Details"));
        jPanel3.setOpaque(false);

        invoicetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice Id", "M/S", "Tax In No. & Dt", "Ch No. & Dt", "Or No. & Dt", "VAT/CST(%)", "Tr. Charge", "Less(%)", "Entry Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        invoicetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoicetableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(invoicetable);

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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tax Invoice No. ");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Chalan No. ");

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

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Purchase Order No. ");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("From :  M/S");

        jLabel6.setText("Date :");

        jLabel7.setText("Date :");

        jLabel8.setText("Date :");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Invoice ID : ");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("VAT / CST (%) ");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Transportation Charge ");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Less (%) ");

        labelInvoiceId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInvoiceId.setText("**********");

        labelEntryDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelEntryDate.setText("**********");

        taxinvoicedate.setFormats("dd-MM-yyyy");

        challnodate.setFormats("dd-MM-yyyy");

        purchasedate.setFormats("dd-MM-yyyy");

        jLabel26.setText("Entry Date :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttransport, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtvat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtless, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelInvoiceId)
                                            .addComponent(msCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtorderNo, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                    .addComponent(txtchalanNo))))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(taxinvoicedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(challnodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(22, 22, 22)))
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEntryDate)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelInvoiceId)
                    .addComponent(labelEntryDate)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(msCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(taxinvoicedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtchalanNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(challnodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtorderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtvat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txttransport, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtless, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btnclear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add Items"));
        jPanel2.setOpaque(false);

        jLabel9.setText("Purchase Order No. :");

        jLabel10.setText("Invoice ID :");

        jLabel13.setText("Date :");

        jLabel17.setText("Item Name :");

        jLabel18.setText("Rate in Rs  :");

        jLabel19.setText("Quantity :");

        tableItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Id", "Item Code", "Item Name", "Rate", "Quantity", "Gross Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableItemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableItem);

        jLabel20.setText("Total Gross Value :");

        jLabel21.setText("VAT/CST :");

        jLabel22.setText("Grand Total :");

        labelGross.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelGross.setText("**********");

        labelvat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelvat.setText("**********");

        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("**********");

        jLabel28.setText("+");

        invoiceId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        invoiceId.setText("**********");

        purchaseOrder.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        purchaseOrder.setText("**********");

        date.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date.setText("**********");

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

        jLabel23.setText("Total Items ::");

        totalitems.setText("totalitems");

        jLabel24.setText("Transport Charge :");

        labeltran.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeltran.setText("**********");

        jLabel29.setText("+");

        jLabel25.setText("Less Amount :");

        labelless.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelless.setText("**********");

        btndone.setText("DONE");
        btndone.setOpaque(false);
        btndone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndoneActionPerformed(evt);
            }
        });

        jLabel31.setText(" -");

        btndeleteitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Delete_16x16.png"))); // NOI18N
        btndeleteitem.setText("delete");
        btndeleteitem.setOpaque(false);
        btndeleteitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteitemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(purchaseOrder))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtquantity))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnadditem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnupdateitem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnclearitem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btndeleteitem)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(invoiceId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalitems))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel22)
                                        .addGap(80, 80, 80)
                                        .addComponent(labelTotal))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel25)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel21)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelless, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelGross, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labeltran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelvat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btndone)
                .addContainerGap())
            .addComponent(jSeparator4)
            .addComponent(jSeparator3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(invoiceId)
                    .addComponent(jLabel13)
                    .addComponent(date))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(purchaseOrder))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadditem)
                    .addComponent(btnupdateitem)
                    .addComponent(btnclearitem)
                    .addComponent(btndeleteitem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(totalitems))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelGross))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel28)
                    .addComponent(labelvat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel29)
                    .addComponent(labeltran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelless, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(labelTotal))
                .addGap(4, 4, 4)
                .addComponent(btndone)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // update
         Date dt=new Date(); 
        String yr=dt.getYear()+"-"+dt.getYear()+1;
        Comboitem g1 =(Comboitem) msCombo.getSelectedItem();
        int companyid=g1.getKey();
       String company=g1.getValue();
        total_amt_value="0";
     
     
       transportation_amt=txttransport.getText().trim();
       less_per=txtless.getText().trim();
       from_company_id=companyid+"";
       tax_invoice_no=txtinvoiceNo.getText().trim();
    
     
      
      challan_no=txtchalanNo.getText().trim();
    
      purchase_order_no=txtorderNo.getText().trim();
     
      vat_per=txtvat.getText().trim();
      
      total_gross_amt=0+"";
      companyname="";
        Date tx = taxinvoicedate.getDate();        
        DateFormat oDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat y = new SimpleDateFormat("yyyy");
        
        
        Date c = challnodate.getDate();        
        
        Date p = purchasedate.getDate();        
         if(companyid==0){
            dialogmessage = "PLEASE SELECT FROM M/S COMPANY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(tax_invoice_no)){
             dialogmessage = "PLEASE ENTER TAX INVOICE NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(challan_no)){
             dialogmessage = "PLEASE ENTER CHALLAN  NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(purchase_order_no)){
             dialogmessage = "PLEASE ENTER PURCHASE ORDER NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(vat_per)){
             dialogmessage = "PLEASE ENTER VAT(IN %).!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(transportation_amt)){
             dialogmessage = "PLEASE ENTER TRANSPORTATION AMOUNT.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(less_per)){
             dialogmessage = "PLEASE ENTER LESS (IN %).!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if(tx==null ||c==null||p==null){
             dialogmessage = "PLEASE SELECT DATES FOR PURCHASE,TAX AND CHALLAN!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else{
       
        String today=oDateFormat.format(dt);
        int year=Integer.valueOf(y.format(dt));
        String tax_date = oDateFormat.format(tx);
        System.out.println("Formated :"+tax_date);
        String pur_date = oDateFormat.format(p);
        String ch_date = oDateFormat.format(c);
        System.out.println("Formated :"+tax_date);
        if(!pattern.ValidateNumeric(transportation_amt)){
           dialogmessage = "INVALID TRANSPORTATION AMOUNT!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidatePer(vat_per)){
           dialogmessage = "INVALID VAT PERCENTAGE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidatePer(less_per)){
           dialogmessage = "INVALID DISCOUNT PERCENTAGE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else{
       
        if (!(companyid==0) && !transportation_amt.equals("")&& !transportation_amt.equals("")
           && !less_per.equals("")&& !from_company_id.equals("")&& !tax_invoice_no.equals("")&& !tax_date.equals("")
           && !challan_no.equals("")&& !ch_date.equals("")&& !purchase_order_no.equals("")&& !pur_date.equals("")
           && !vat_per.equals("")){
        String query;
         try {
             query = "update finsys.t_stockin set transportation_amt='" +transportation_amt + "'"
                     + ",less_per='" +less_per+ "'"
                     + ",from_company_id='" +from_company_id+ "'"
                     + ",tax_invoice_no='" +tax_invoice_no+ "'"
                     + ",tax_invoice_date='" + UtilDate.convertStringToSqlDate("dd-MM-yyyy",tax_date)+ "'"
                     + ",challan_no='" +challan_no+ "'"
                     + ",challan_date='" + UtilDate.convertStringToSqlDate("dd-MM-yyyy",ch_date)+ "'"
                     + ",purchase_order_no='" +purchase_order_no+ "'"
                     + ",purchase_order_date='"+ UtilDate.convertStringToSqlDate("dd-MM-yyyy",pur_date)+ "'"
                     + ",vat_per='" +vat_per+ "'"
                     
                     + " where invoiceid='" + ID + "'";
                 executeSqlQuery(query, "updated");
                ResetRecord();
                ReloadTable();
                ResetForm();
         } catch (ParseException ex) {
             Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
       
        else {
                dialogmessage = "Empty Record !!!";
                dialogtype = JOptionPane.WARNING_MESSAGE;
                JOptionPane.showMessageDialog(null, dialogmessage, dialogs, dialogtype);

            }
       }
        }
       
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
      
        Date dt=new Date();
        String yr=dt.getYear()+"-"+dt.getYear()+1;
        Comboitem g1 =(Comboitem) msCombo.getSelectedItem();
        int companyid=g1.getKey();
        String company=g1.getValue();

        total_amt_value="0";
     
     
       transportation_amt=txttransport.getText().trim();
      
       less_per=txtless.getText().trim();
       from_company_id=companyid+"";
       tax_invoice_no=txtinvoiceNo.getText().trim();
    
     
      
      challan_no=txtchalanNo.getText().trim();
    
      purchase_order_no=txtorderNo.getText().trim();
     
      vat_per=txtvat.getText().trim();
      
      total_gross_amt=0+"";
      companyname="";
        Date tx = taxinvoicedate.getDate();        
        DateFormat oDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat y = new SimpleDateFormat("yyyy");
       
        
        Date c = challnodate.getDate();        
        
        Date p = purchasedate.getDate();        
        if(companyid==0){
            dialogmessage = "PLEASE SELECT FROM M/S COMPANY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(tax_invoice_no)){
             dialogmessage = "PLEASE ENTER TAX INVOICE NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(challan_no)){
             dialogmessage = "PLEASE ENTER CHALLAN  NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(purchase_order_no)){
             dialogmessage = "PLEASE ENTER PURCHASE ORDER NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(vat_per)){
             dialogmessage = "PLEASE ENTER VAT(IN %).!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(transportation_amt)){
             dialogmessage = "PLEASE ENTER TRANSPORTATION AMOUNT.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(less_per)){
             dialogmessage = "PLEASE ENTER LESS (IN %).!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if(tx==null ||c==null||p==null){
             dialogmessage = "PLEASE SELECT DATES FOR PURCHASE,TAX AND CHALLAN!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } else{
       
        String today=oDateFormat.format(dt);
        int year=Integer.valueOf(y.format(dt));
         String tax_date = oDateFormat.format(tx);
        String ch_date = oDateFormat.format(c);
        String pur_date = oDateFormat.format(p);
         System.out.println("Formated :"+tax_date);
         System.out.println("Formated :"+tax_date);
        
       
       
        db = new database();
        
        if(!pattern.ValidateNumeric(transportation_amt)){
           dialogmessage = "INVALID TRANSPORTATION AMOUNT!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidatePer(vat_per)){
           dialogmessage = "INVALID VAT PERCENTAGE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidatePer(less_per)){
           dialogmessage = "INVALID DISCOUNT PERCENTAGE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else{
        try {

           if (!(companyid==0) && !transportation_amt.equals("")&& !transportation_amt.equals("")
           && !less_per.equals("")&& !from_company_id.equals("")&& !tax_invoice_no.equals("")&& !tax_date.equals("")
           && !challan_no.equals("")&& !ch_date.equals("")&& !purchase_order_no.equals("")&& !pur_date.equals("")
           && !vat_per.equals("")) {
               
                  //System.out.println(tax_invoice_date+""+challan_date+""+purchase_order_date);
                  String inv=tax_invoice_no+"/"+company.substring(0, 5)+"/"+year+"-"+(year+1);
        
                  invoiceno=inv;
                  i.setInvoiceid(inv);
                  i.setInvoiceno(inv);
                  i.setChallan_date(ch_date);
                  i.setChallan_no(challan_no);
                  i.setLess_per(less_per);
                  i.setTransportation_amt(transportation_amt);
                  i.setPurchase_order_date(pur_date);
                  i.setPurchase_order_no(purchase_order_no);
                  i.setFrom_company_id(from_company_id);
                  i.setVat_per(vat_per);
                  i.setLess_per(less_per);
                  i.setTax_invoice_no(tax_invoice_no);
                  i.setTax_invoice_date(tax_date);

                int result = db.insertStockIn(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "STOCK IN INVOICE DETAILS ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
                    labelInvoiceId.setText(invoiceno);
                    labelEntryDate.setText(today);
                    invoiceId.setText(invoiceno);
                    date.setText(today);
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
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void invoicetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoicetableMouseClicked
        //Display selected row in textbox
        ResetForm();
        Refresh();
        TableModel mod = invoicetable.getModel();
        setEnabledAll(jPanel2,true);
         DateFormat oDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dt=new Date();
        String today=oDateFormat.format(dt);
      
        int i = invoicetable.getSelectedRow();
        System.out.println(i);
        //GLOBAL INVOICE ID
        invoiceid=(mod.getValueAt(i, 0).toString()).trim();
        //from company
        String s[]= mod.getValueAt(i, 1).toString().split(":");
        for(String d:s){
        System.out.println(d);
        }
       System.out.println(s[1]);
        companyid=s[0];
        cname=s[1];
        
        //Invoice 
        String in[]= mod.getValueAt(i, 2).toString().split(":");
        invoice=in[0];
        invoicedt=in[1];
        //Challan 
        String c[]= mod.getValueAt(i, 3).toString().split(":");
        challan=c[0];
        challandt=c[1];
         //Order 
        String o[]= mod.getValueAt(i, 4).toString().split(":");
        order=o[0];
        orderdt=o[1];
        
        
        setSelectedValue(msCombo,Integer.valueOf(companyid));
       
        txtinvoiceNo.setText(invoice);
        txtchalanNo.setText(challan);
        txtorderNo.setText(order);
        txttransport.setText(mod.getValueAt(i, 6).toString());
        txtless.setText(mod.getValueAt(i, 7).toString());
        txtvat.setText(mod.getValueAt(i, 5).toString());
        
        labelInvoiceId.setText(mod.getValueAt(i, 0).toString());
        invoiceId.setText(mod.getValueAt(i, 0).toString());
        date.setText(today);
        labelEntryDate.setText(mod.getValueAt(i, 8).toString());
        purchaseOrder.setText(order+", Date: "+orderdt);
        ID = mod.getValueAt(i, 0).toString();
        ReloadTableItem();
         try {
             taxinvoicedate.setDate(UtilDate.convertStringToSqlDate("dd-MM-yyyy",invoicedt));
             challnodate.setDate(UtilDate.convertStringToSqlDate("dd-MM-yyyy",challandt));
             purchasedate.setDate(UtilDate.convertStringToSqlDate("dd-MM-yyyy",orderdt));
         } catch (ParseException ex) {
             Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_invoicetableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        //delete
        String sMSGBOX_TITLE = "FINSYS version 1.0";
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
                if("".equals(ID)){
                    dialogmessage = "PLEASE SELECT RECORD TO DELETE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                
                }
                else{
                
                String query = "delete from finsys.t_stockin where invoiceid='" + ID + "'";
                Double gross=0.0,qty=0.0;
                executeSqlQuery(query, "deleted");
                ArrayList<Stockinitemtable> sitemlist = getStockinitemtable();
       
                Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
        
                 for (int i = 0; i < sitemlist.size(); i++) {
                int iid=sitemlist.get(i).getItemid();
                qty=Double.valueOf(sitemlist.get(i).getQuantity());
                gross=Double.valueOf(sitemlist.get(i).getGrossvalue());
                ArrayList<Stocktable> d=db.getStock(iid);
                for(Stocktable c:d){
                totalstockamount=Double.valueOf(c.getAmount());
                totalstockquantity=Double.valueOf(c.getQuantity());
                }
                prevquantity=totalstockquantity-qty;
                prevrate=totalstockamount-gross;
                query = "update finsys.t_stock set quantity='" + prevquantity+ "',amount='" +prevrate+ "' where itemid='" +iid+ "'";
                PreparedStatement  pst=null;
                try {
                    pst = data.conn.prepareStatement(query);
                    int flag = pst.executeUpdate();
                    if(flag==1){
                      query = "delete from finsys.t_stockin_items where invoiceid='" + ID + "' and itemid='"+iid+"'";
                      pst = data.conn.prepareStatement(query);
                      flag = pst.executeUpdate();
                    }
                 
                } catch (SQLException ex) {
                Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
                }
   
        }
        
                 ResetRecord();
                
                }
      } else {
               
                remove(reply);
            }
       
    }//GEN-LAST:event_btndeleteActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String query=search.getText().toUpperCase();
        filter(query);
    }//GEN-LAST:event_searchKeyReleased

    private void btnadditemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadditemActionPerformed
        // TODO add your handling code here:
   
      Comboitem g1 =(Comboitem) itemCombo.getSelectedItem();
      itemid=g1.getKey();
         
       invid=invoiceId.getText().trim();
       item_rate=rate.getText().trim();
       quantity=txtquantity.getText().trim();
      
       //validation
        if(itemid==0){
            dialogmessage = "PLEASE SELECT ITEM NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(item_rate)){
             dialogmessage = "PLEASE ENTER ITEM RATE.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(quantity)){
             dialogmessage = "PLEASE ENTER QUANTITY.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(invid)){
             dialogmessage = "STOCK IN RECORD NOT SELECTED.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if(!pattern.ValidateNumeric(item_rate)){
           dialogmessage = "INVALID ITEM RATE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidateNumeric(quantity)){
           dialogmessage = "INVALID QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } 
       else{
       
       
        db = new database();
        try {

           if (!(itemid==0) && !invoiceid.equals("")&& !item_rate.equals("")
           && !quantity.equals("")) {
               
               j.setItemid(itemid);
               j.setItem_rate(item_rate);
               j.setQuantity(quantity);
               j.setInvoiceid(invid);

                int result = db.insertStockinitem(j);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "ITEM ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
                    
                    ResetRecordItem();
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
        //first deduct from the total stock
       Comboitem g1 =(Comboitem) itemCombo.getSelectedItem();
       itemid=g1.getKey();
       invid=invoiceId.getText().trim();
        Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
       
         
       invid=invoiceId.getText().trim();
       item_rate=rate.getText().trim();
       quantity=txtquantity.getText().trim();
       //validation
        if(itemid==0){
            dialogmessage = "PLEASE SELECT ITEM NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(item_rate)){
             dialogmessage = "PLEASE ENTER ITEM RATE.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(quantity)){
             dialogmessage = "PLEASE ENTER QUANTITY.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(invid)){
             dialogmessage = "STOCK IN RECORD NOT SELECTED.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if(!pattern.ValidateNumeric(item_rate)){
           dialogmessage = "INVALID ITEM RATE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } else  if(!pattern.ValidateNumeric(quantity)){
           dialogmessage = "INVALID QUANTITY!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       } 
       else{
        ArrayList<Stocktable> d=db.getStock(itemid);
         for(Stocktable c:d){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
        }
         System.out.println(totalstockamount+"before update "+totalstockquantity);
        ArrayList<Stockinitemtable> temp=db.getStockinitemtable(invid, String.valueOf(itemid)) ;
         for(Stockinitemtable c:temp){
             totalamt=Double.valueOf(c.getQuantity())*Double.valueOf(c.getItem_rate());
             prevquantity=Double.valueOf(c.getQuantity());
        }
         System.out.println(totalamt+"revert back stock "+prevquantity);
         updatestockamount=totalstockamount-totalamt;
         updatestockquantity=totalstockquantity-prevquantity;
         System.out.println(updatestockamount+"  in"+updatestockamount);
         
       
       
       
        db = new database();
        try {
            
             String query = "update finsys.t_stock set quantity='" + updatestockquantity+ "',amount='" +updatestockamount+ "' where itemid='" +itemid+ "'";
              PreparedStatement  pst = data.conn.prepareStatement(query);
               int flag = pst.executeUpdate();
               System.out.println(flag+"  in");
         
        if(flag==1){
            
        if(ID1==null){
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
       
        else if( itemid==0&& invid.equals("")&& item_rate.equals("")&& quantity.equals("")){
            dialogmessage = "Empty Record!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
        else{
         query = "update finsys.t_stockin_items set item_rate='" + item_rate+ "',quantity='" + quantity + "' where invoiceid='" +invid+ "' and itemid='" + ID1 + "'";
         executeSqlQuery(query, "updated");
         ArrayList<Stocktable> d1=db.getStock(itemid);
         for(Stocktable c:d1){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
        }
          System.out.println(totalstockamount+"  after update "+totalstockquantity);
             query = "update finsys.t_stock set quantity='" +( totalstockquantity+Double.valueOf(quantity))+ "',amount='" +(totalstockamount+Double.valueOf(item_rate)*Double.valueOf(quantity))+ "' where itemid='" +itemid+ "'";
                pst = data.conn.prepareStatement(query);
                flag = pst.executeUpdate();
        ResetRecordItem();
        System.out.println("1");
         ResetForm();
         System.out.println("2");
        ReloadTableItem();
        System.out.println("3");
        
        }
        }
        else{
            dialogmessage = "Failed to Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        
       }
    }//GEN-LAST:event_btnupdateitemActionPerformed

    private void btnclearitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearitemActionPerformed
        // TODO add your handling code here:
        ResetRecordItem();
    }//GEN-LAST:event_btnclearitemActionPerformed

    private void tableItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItemMouseClicked
        
          //Display selected row in textbox
        
         int i = tableItem.getSelectedRow();
        TableModel mo = tableItem.getModel();
       
        setSelectedValue(itemCombo,Integer.valueOf(mo.getValueAt(i, 0).toString()));
        txtquantity.setText(mo.getValueAt(i, 4).toString());
        
        rate.setText(mo.getValueAt(i, 3).toString());
        ID1 = mo.getValueAt(i, 0).toString();
        tempinvoiceid=invoiceId.getText().trim();
        tempitemid=ID1;
        tempquantity=mo.getValueAt(i, 4).toString();
        temprate=mo.getValueAt(i, 3).toString();
       
        
    }//GEN-LAST:event_tableItemMouseClicked

    private void btndoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndoneActionPerformed
        // TODO add your handling code here:
        
         
       
        db = new database();
        try {
        if(invoiceId.getText().trim()==""){
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
       
        else if( TOTALAMOUNT==0.0 && TOTALGROSS==0.0){
            dialogmessage = "Empty Record!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
        else{
        String query = "update finsys.t_stockin set total_gross_amt='" + TOTALGROSS+ "',total_amt_value='" + TOTALAMOUNT + "' where invoiceid='" +invoiceId.getText().trim()+ "'";
       int res= executeSqlQuery1(query, "updated");
       if(res==1){
        ResetRecord();
         ResetForm();
        ReloadTable();
       
        Refresh();
        
       }
       
        }
        

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btndoneActionPerformed

    private void btndeleteitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteitemActionPerformed
        // TODO add your handling code here:
          String sMSGBOX_TITLE = "FINSYS version 1.0";
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
                if("".equals(ID1)|| "".equals(ID)){
                    dialogmessage = "PLEASE SELECT RECORD TO DELETE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                
                }else{
        Double prevquantity=0.0,prevrate=0.0, totalamt=0.0,totalstockamount=0.0,totalstockquantity=0.0,updatestockamount=0.0,updatestockquantity=0.0;
       
        String query = "delete from finsys.t_stockin_items where invoiceid='" + ID + "' and itemid='"+ID1+"'";
     
        executeSqlQuery(query, "deleted");
        
         ArrayList<Stocktable> d=db.getStock(Integer.valueOf(ID1));
            for(Stocktable c:d){
             totalstockamount=Double.valueOf(c.getAmount());
             totalstockquantity=Double.valueOf(c.getQuantity());
            }
            prevquantity=totalstockquantity-Double.valueOf(tempquantity);
            prevrate=totalstockamount-Double.valueOf(tempquantity)*Double.valueOf(temprate);
            query = "update finsys.t_stock set quantity='" + prevquantity+ "',amount='" +prevrate+ "' where itemid='" +ID1+ "'";
             PreparedStatement  pst=null;
            try {
                pst = data.conn.prepareStatement(query);
                 int flag = pst.executeUpdate();
                
                 
            } catch (SQLException ex) {
                Logger.getLogger(stockIn.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            }
            else {
               
                remove(reply);
            }
    }//GEN-LAST:event_btndeleteitemActionPerformed

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
        ID1="";
        ID="";
        
        
   }
   public void Refresh(){
        setEnabledAll(jPanel2,false);
        model1 = (DefaultTableModel) tableItem.getModel();
        model1.setRowCount(0);
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnadditem;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnclearitem;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btndeleteitem;
    private javax.swing.JButton btndone;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnupdateitem;
    private org.jdesktop.swingx.JXDatePicker challnodate;
    private javax.swing.JLabel date;
    private javax.swing.JLabel invoiceId;
    private javax.swing.JTable invoicetable;
    private javax.swing.JComboBox<Comboitem> itemCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelEntryDate;
    private javax.swing.JLabel labelGross;
    private javax.swing.JLabel labelInvoiceId;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelless;
    private javax.swing.JLabel labeltran;
    private javax.swing.JLabel labelvat;
    private javax.swing.JComboBox<Comboitem> msCombo;
    private javax.swing.JLabel purchaseOrder;
    private org.jdesktop.swingx.JXDatePicker purchasedate;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField search;
    private javax.swing.JTable tableItem;
    private org.jdesktop.swingx.JXDatePicker taxinvoicedate;
    private javax.swing.JLabel totalitems;
    private javax.swing.JTextField txtchalanNo;
    private javax.swing.JTextField txtinvoiceNo;
    private javax.swing.JTextField txtless;
    private javax.swing.JTextField txtorderNo;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txttransport;
    private javax.swing.JTextField txtvat;
    // End of variables declaration//GEN-END:variables

}
