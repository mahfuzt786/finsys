/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

/**
 *
 * @author rupa
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Item extends javax.swing.JInternalFrame {
    
    String categid = "";
    String subcatid = "";
    String ledgerid="";
    String itemname="";
    String uomcode="";
    String itemcost="";
    database db;
    Itemtable i = new Itemtable();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID;
    ArrayList<Categorytable> category;
    ArrayList<Subitemcategorytable> subcat;
    ArrayList<uomtable> uom;
    ArrayList<Ledgertable> ledger;
    /**
     * Creates new form cost center
     */
    public Item() {
        initComponents();
        ReloadTable();
        db=new database();
        category=db.getCategory();
        ledger=db.getLedger();
        uom=db.getUom();
        subcat=db.getSubcategory1();
         System.out.println("Line1");
        jComboBox_category.addItem(new Comboitem(0,"Select Category"));
        for(Categorytable c:category){
        
            jComboBox_category.addItem(new Comboitem(c.getCategoryid(),c.getCategoryname()));
        }
        jComboBox_subcategory.addItem(new Comboitem(0,"Select Sub Category"));
        //checking
        jComboBox_category.addItemListener(new ItemListener() {
            //
            // Listening if a new items of the combo box has been selected.
            //
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                // The item affected by the event.
                Object item = event.getItem();
        
                
                System.out.println("Affected items: " + item.toString());
                if (event.getStateChange() == ItemEvent.SELECTED) {
                     Comboitem g =(Comboitem) jComboBox_category.getSelectedItem();
                     int catid=g.getKey();
                     db=new database();
                     subcat=db.getSubcategory(catid);
                     
                     for(Subitemcategorytable c:subcat){
          
                             jComboBox_subcategory.addItem(new Comboitem(c.getSubcategoryid(),c.getSubcategoryname()));
                     }
     
                   
                }

                if (event.getStateChange() == ItemEvent.DESELECTED) {
                     jComboBox_subcategory.setSelectedIndex(0);
                }
            }
        });

        
        //checking end
        
        
        
//        jComboBox_subcategory.addItem(new Comboitem(0,"Select Sub Category"));
//        for(Subitemcategorytable c:subcat){
//        
//            jComboBox_subcategory.addItem(new Comboitem(c.getSubcategoryid(),c.getSubcategoryname()));
//        }
        System.out.println("Line2");
        jComboBox_ledger.addItem(new Comboitem(0,"Select Ledger"));
        for(Ledgertable c:ledger){
           
            jComboBox_ledger.addItem(new Comboitem(c.getLedgerid(),c.getLedgername()));
        }
        System.out.println("Line3");
        jComboBox_uom.addItem(new Comboitem(0,"Select UOM"));
        for(uomtable c:uom){
       
            jComboBox_uom.addItem(new Comboitem(c.getId(),c.getUomAbbr()));
        }
        
    }

    /**
     *
     * @return
     */
    public ArrayList<Itemtable> getItemTable() {
        ArrayList<Itemtable> iTable = new ArrayList<Itemtable>();
        String query = "select * from finsys.m_item";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Itemtable iTab;
            while (rs.next()) {
                iTab = new Itemtable(rs.getInt("categoryid"),rs.getInt("subcategoryid"),rs.getInt("itemid"),rs.getInt("ledgerid"), rs.getString("itemcode"), rs.getString("itemname"),rs.getString("uomcode"),rs.getDouble("itemcost") );
                
                iTable.add(iTab);
            }
        } catch (Exception e) {
            System.out.println("Exception"+e);
            e.printStackTrace();
        }
        return iTable;
    }

    private void ReloadTable() {
        ArrayList<Itemtable> subcatitemlist = getItemTable();
        DefaultTableModel model = (DefaultTableModel) jtable_subcattable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < subcatitemlist.size(); i++) {
            row[0] = subcatitemlist.get(i).getCategoryid();
            row[1] = subcatitemlist.get(i).getSubcategoryid();
            row[2] = subcatitemlist.get(i).getLedgerid();
            row[3] = subcatitemlist.get(i).getItemid();
            row[4] = subcatitemlist.get(i).getItemname();
            row[5] = subcatitemlist.get(i).getUomcode();
            row[6] = subcatitemlist.get(i).getItemcost();
            model.addRow(row);
        }
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

    private void ResetRecord() {
        jComboBox_category.setSelectedIndex(0);
        jComboBox_subcategory.setSelectedIndex(0);
        jComboBox_ledger.setSelectedIndex(0);
        jComboBox_uom.setSelectedIndex(0);
        txtitemname.setText("");
        txtitemcost.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtitemname = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jComboBox_category = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_subcategory = new javax.swing.JComboBox<>();
        jComboBox_ledger = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_uom = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtitemcost = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_subcattable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Item");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM"));
        jPanel2.setOpaque(false);

        jLabel2.setText("Item Category :");

        jLabel3.setText("Item Name :");

        txtitemname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtitemnameActionPerformed(evt);
            }
        });

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

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jComboBox_category, org.jdesktop.beansbinding.ELProperty.create("${selectedItem}"), jComboBox_category, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });

        jLabel4.setText("Item Sub Category :");

        jComboBox_subcategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_subcategoryActionPerformed(evt);
            }
        });

        jLabel5.setText("Ledger :");

        jLabel6.setText("Unit Of Measurement :");

        jLabel7.setText("Item Price(Per Unit in Rs.)");

        txtitemcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtitemcostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jComboBox_category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_uom, 0, 280, Short.MAX_VALUE)
                            .addComponent(txtitemname)
                            .addComponent(jComboBox_ledger, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox_subcategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtitemcost, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_subcategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_ledger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtitemname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_uom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtitemcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btnclear)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Table"));
        jPanel3.setOpaque(false);

        jtable_subcattable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category ID", "Sub Category ID", "Ledger", "Item ID", "Item Name", "UOM", "Item Price(in Rs.)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_subcattable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_subcattableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtable_subcattable);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Search_16x16.png"))); // NOI18N
        jLabel1.setText("Search : ");

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // update
        Comboitem g =(Comboitem) jComboBox_category.getSelectedItem();
        int catid=g.getKey();
         Comboitem g1 =(Comboitem) jComboBox_uom.getSelectedItem();
        int uomid=g1.getKey();
         Comboitem g2 =(Comboitem) jComboBox_ledger.getSelectedItem();
        int ledid=g2.getKey();
        itemname = txtitemname.getText().trim();
        itemcost=txtitemcost.getText().trim();
        double cost=Double.valueOf(itemcost);
        if(ID==null){
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
       
        else if( catid==0&& uomid==0 && ledid==0 && itemname.equals("")&& itemcost.equals("")){
            dialogmessage = "Empty Record!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
        else{
        String query = "update finsys.m_item set ledgerid='" + ledid + "',uomcode='" + uomid + "',itemname='" + itemname+ "',itemcost='" + itemcost   + "' where itemid='" + ID + "'";
        executeSqlQuery(query, "updated");
        ResetRecord();
        
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
       

        Comboitem g =(Comboitem) jComboBox_category.getSelectedItem();
        int catid=g.getKey();
        Comboitem g3 =(Comboitem) jComboBox_subcategory.getSelectedItem();
        int subcatid=g3.getKey();
        Comboitem g1 =(Comboitem) jComboBox_uom.getSelectedItem();
        int uomid=g1.getKey();
        String uomcd=g1.getKey()+"";
        Comboitem g2 =(Comboitem) jComboBox_ledger.getSelectedItem();
        int ledid=g2.getKey();
        itemname = txtitemname.getText().trim();
        itemcost=txtitemcost.getText().trim();
        double cost=Double.valueOf(itemcost);
        
        db = new database();
        try {

            if( !(catid==0) &&!(subcatid==0) && !(uomid==0) && !(ledid==0) && !itemname.equals("")&& !itemcost.equals("")) {
                i.setCategoryid(g.getKey());
                i.setSubcategoryid(subcatid);
                i.setLedgerid(ledid);
                i.setUomcode(uomcd);
                i.setItemname(itemname);
                i.setItemcost(cost);
                //System.out.println("values"+i);
                int result = db.insertItem(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "ITEM ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
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
                JOptionPane.showMessageDialog((Component) null, dialogmessage, dialogs, dialogtype);

            }

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void jtable_subcattableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_subcattableMouseClicked
        //Display selected row in textbox
        int i = jtable_subcattable.getSelectedRow();
        TableModel model = jtable_subcattable.getModel();
        setSelectedValue(jComboBox_category,Integer.valueOf(model.getValueAt(i, 0).toString()));
        setSelectedValue(jComboBox_subcategory,Integer.valueOf(model.getValueAt(i, 1).toString()));
        setSelectedValue(jComboBox_ledger,Integer.valueOf(model.getValueAt(i, 2).toString()));
        setSelectedValue(jComboBox_uom,Integer.valueOf(model.getValueAt(i, 5).toString()));
        //jComboBox_category.setSelectedItem(model.getValueAt(i, 0).toString());
        txtitemname.setText(model.getValueAt(i, 4).toString());
        txtitemcost.setText(model.getValueAt(i, 6).toString());
        ID = model.getValueAt(i, 3).toString();
    }//GEN-LAST:event_jtable_subcattableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        //delete
         if(ID==null){
            dialogmessage = "Please Select Record To Delete";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }
         else{
        String query = "delete from finsys.m_item where itemid='" + ID + "'";
        executeSqlQuery(query, "deleted");
        ResetRecord();
         }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // search
    }//GEN-LAST:event_searchKeyTyped

    private void txtitemnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtitemnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtitemnameActionPerformed

    private void txtitemcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtitemcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtitemcostActionPerformed

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jComboBox_categoryActionPerformed

    private void jComboBox_subcategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_subcategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_subcategoryActionPerformed
    
    
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
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<Comboitem> jComboBox_category;
    private javax.swing.JComboBox<Comboitem> jComboBox_ledger;
    private javax.swing.JComboBox<Comboitem> jComboBox_subcategory;
    private javax.swing.JComboBox<Comboitem> jComboBox_uom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable_subcattable;
    private javax.swing.JTextField search;
    private javax.swing.JTextField txtitemcost;
    private javax.swing.JTextField txtitemname;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
