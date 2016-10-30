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
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class mstore extends javax.swing.JInternalFrame {

    String ms = "";
    String address = "";
    String phone = "";
    database db;
    ms_item i = new ms_item();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID;
    DefaultTableModel model;
     PatternValidation pattern=new PatternValidation();
    /**
     * Creates new form ms
     */
    public mstore() {
        initComponents();
        ReloadTable();
    }

    /**
     *
     * @return
     */
    public ArrayList<mstable> getmsTable() {
        ArrayList<mstable> msTable = new ArrayList<mstable>();
        String query = "select * from finsys.m_fromcompany order by companyid desc";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            mstable msTab;
            while (rs.next()) {
                msTab = new mstable(rs.getString("companyid"), rs.getString("companyname"), rs.getString("companyaddress"), rs.getString("companyphone"));
                msTable.add(msTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msTable;
    }

    private void ReloadTable() {
        ArrayList<mstable> msitemlist = getmsTable();
        model = (DefaultTableModel) table_mstable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < msitemlist.size(); i++) {
            row[0] = msitemlist.get(i).getMsid();
            row[1] = msitemlist.get(i).getMsname();
            row[2] = msitemlist.get(i).getMsaddress();
            row[3] = msitemlist.get(i).getMsphone();

            model.addRow(row);
        }
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table_mstable.setRowSorter(tr);
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

    private void ResetRecord() {
        txtms.setText("");
        txtaddress.setText("");
        txtphone.setText("");
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
        jLabel3 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtms = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaddress = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_mstable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Unit of Measurement");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("M/S Store"));
        jPanel2.setOpaque(false);

        jLabel2.setText("M/S :");

        jLabel3.setText("Address :");

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

        jLabel4.setText("Phone No. :");

        txtaddress.setColumns(20);
        txtaddress.setRows(5);
        jScrollPane2.setViewportView(txtaddress);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtms)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btnclear))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("M/S Store Record"));
        jPanel3.setOpaque(false);

        table_mstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "M/S", "Address", "Phone No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_mstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_mstableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_mstable);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // update
        if(ID==null){
            dialogmessage = "Please Select Record To Update!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        }else if(txtms.getText().trim().equals("")){
             dialogmessage = "Please Enter M/S!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
            
        }else if(txtaddress.getText().trim().equals("")){
            dialogmessage = "Please Enter Address!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        
        }else if(txtphone.getText().trim().equals("")){
            dialogmessage = "Please Enter Phone No.!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        
        }else{
        String query = "update finsys.m_fromcompany set companyname='" + txtms.getText().trim().toUpperCase() + "',companyaddress='" + txtaddress.getText().trim().toUpperCase() + "',companyphone='" + txtphone.getText().trim().toUpperCase() + "' where companyid='" + ID + "'";
        executeSqlQuery(query, "updated");
        ResetRecord();
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        
        ms= "";
        address = "";
        phone = "";

        ms = txtms.getText().trim().toUpperCase();
        address = txtaddress.getText().trim().toUpperCase();
        phone = txtphone.getText().trim().toUpperCase();
        if("".equals(ms)){
             dialogmessage = "PLEASE ENTER MS COMPANY NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(address)){
             dialogmessage = "PLEASE ENTER ADDRESS!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(phone)){
             dialogmessage = "PLEASE ENTER PHONE NO.!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } else if(!pattern.ValidatePhone(phone)){
           dialogmessage = "INVALID PHONE NO. !!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
                    
       }else{
        db = new database();
        try {

            if (!txtms.equals("") && !txtaddress.equals("")&& !txtphone.equals("")) {
                i.setMsname(ms);
                i.setMsaddress(address);
                i.setMsphone(phone);
                //System.out.println("values"+i);
                int result = db.insertms(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "STORE NAME ADDED SUCCESSFULLY";
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
                JOptionPane.showMessageDialog(null, dialogmessage, dialogs, dialogtype);

            }

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void table_mstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_mstableMouseClicked
        //Display selected row in textbox
        int i = table_mstable.getSelectedRow();
        TableModel model = table_mstable.getModel();
        txtms.setText(model.getValueAt(i, 1).toString());
        txtaddress.setText(model.getValueAt(i, 2).toString());
        txtphone.setText(model.getValueAt(i, 3).toString());
        ID = model.getValueAt(i, 0).toString();
    }//GEN-LAST:event_table_mstableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        //delete
        String sMSGBOX_TITLE = "FINSYS version 1.0";
         if(ID==null){
            dialogmessage = "Please Select Record To Delete!!!";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        }else{
              int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
        String query = "delete from finsys.m_fromcompany where companyid='" + ID + "'";
        executeSqlQuery(query, "deleted");
        ResetRecord();
         }else{
            remove(reply);
            }}
    }//GEN-LAST:event_btndeleteActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String query = search.getText().toUpperCase();
        filter(query);
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search;
    private javax.swing.JTable table_mstable;
    private javax.swing.JTextArea txtaddress;
    private javax.swing.JTextField txtms;
    private javax.swing.JTextField txtphone;
    // End of variables declaration//GEN-END:variables

}
