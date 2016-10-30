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

public class uom extends javax.swing.JInternalFrame {

    String Uomname = "";
    String Uomabbr = "";
    database db;
    Issue_item i = new Issue_item();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID;
    DefaultTableModel model;

    /**
     * Creates new form uom
     */
    public uom() {
        initComponents();
        ReloadTable();
    }

    /**
     *
     * @return
     */
    public ArrayList<uomtable> getUomTable() {
        ArrayList<uomtable> uomTable = new ArrayList<uomtable>();
        String query = "select * from finsys.t_uom";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            uomtable uomTab;
            while (rs.next()) {
                uomTab = new uomtable(rs.getInt("uomcode"), rs.getString("uomname"), rs.getString("uomabbr"));
                uomTable.add(uomTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uomTable;
    }

    private void ReloadTable() {
        ArrayList<uomtable> uomitemlist = getUomTable();
        model = (DefaultTableModel) jtable_uomtable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < uomitemlist.size(); i++) {
            row[0] = uomitemlist.get(i).getId();
            row[1] = uomitemlist.get(i).getUomName();
            row[2] = uomitemlist.get(i).getUomAbbr();

            model.addRow(row);
        }
    }
    public void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
        jtable_uomtable.setRowSorter(tr);
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
        uomname.setText("");
        uomabbr.setText("");

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
        uomname = new javax.swing.JTextField();
        uomabbr = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_uomtable = new javax.swing.JTable();
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("UOM"));
        jPanel2.setOpaque(false);

        jLabel2.setText("Unit of Measurement :");

        jLabel3.setText("Abbreviation :");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uomabbr, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(uomname))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(uomname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(uomabbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btnclear))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("UOM Table"));
        jPanel3.setOpaque(false);

        jtable_uomtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "UOM", "Abbreviation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_uomtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_uomtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtable_uomtable);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            dialogmessage = "Please Select Record To Update";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        }else if(uomname.getText().trim().equals("")){
             dialogmessage = "Please Enter UOM Name";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
            
        }else if(uomabbr.getText().trim().equals("")){
            dialogmessage = "Please Enter UOM Abbreviation";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
        
        }else{
             
        String query = "update finsys.t_uom set uomname='" + uomname.getText().toUpperCase() + "',uomabbr='" + uomabbr.getText().toUpperCase() + "' where uomcode='" + ID + "'";
        executeSqlQuery(query, "updated");
        ResetRecord();
         }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        Uomname = "";
        Uomabbr = "";

        Uomname = uomname.getText().trim().toUpperCase();
        Uomabbr = uomabbr.getText().trim().toUpperCase();
         if("".equals(Uomname)){
             dialogmessage = "PLEASE ENTER UOM NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else  if("".equals(Uomabbr)){
             dialogmessage = "PLEASE ENTER UOM ABBREVIAION !!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else{
        db = new database();
        try {

            if (!Uomname.equals("") && !Uomabbr.equals("")) {
                i.setItemcode(Uomname);
                i.setItemname(Uomabbr);
                //System.out.println("values"+i);
                int result = db.insertemp(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "UOM ADDED SUCCESSFULLY";
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

    private void jtable_uomtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_uomtableMouseClicked
        //Display selected row in textbox
        int i = jtable_uomtable.getSelectedRow();
        TableModel model = jtable_uomtable.getModel();
        uomname.setText(model.getValueAt(i, 1).toString());
        uomabbr.setText(model.getValueAt(i, 2).toString());
        ID = model.getValueAt(i, 0).toString();
    }//GEN-LAST:event_jtable_uomtableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        //delete
         String sMSGBOX_TITLE = "FINSYS version 1.0";
          if(ID==null){
            dialogmessage = "Please Select Record To Delete";
                    JOptionPane.showMessageDialog(null,dialogmessage,
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }else{
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete this record?", sMSGBOX_TITLE, JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //System.out.println(reply);
            if (reply == JOptionPane.YES_OPTION) {
           
        String query = "delete from finsys.t_uom where uomcode='" + ID + "'";
        executeSqlQuery(query, "deleted");
        ResetRecord();
           
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable_uomtable;
    private javax.swing.JTextField search;
    private javax.swing.JTextField uomabbr;
    private javax.swing.JTextField uomname;
    // End of variables declaration//GEN-END:variables

}
