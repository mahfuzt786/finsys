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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class User extends javax.swing.JInternalFrame {

    String usercode = "";
    String username = "";
    String userdescription = "";
    String userid = "";
    String role = "";
    String pwd = "";
    String rpwd = "";
    
    database db;
    Usertable i = new Usertable();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID="";
    DefaultTableModel model;
    ArrayList<Usertable> item;
    PatternValidation pattern=new PatternValidation();
    MessageDigest md;
    byte[] message;
    int ucode;
    Logdetails m;
    
    public User(int usercode) {
        initComponents();
        ReloadTable();
        btnadd.setVisible(false);
        btnupdate.setVisible(false);
        btndelete.setVisible(false);
        ucode=usercode;
         db=new database();
      
        Menu m=db.getPrivilege(usercode,101);
        if(m.getAdd_p()==1){
            btnadd.setVisible(true);
            
        }
       if(m.getEdit_p()==1){
            btnupdate.setVisible(true);
            
        }
        if(m.getDelete_p()==1){
            btndelete.setVisible(true);
            
        }
        item = db.getUser();
        
       
    }

    public ArrayList<Usertable> getUserTable() {
        ArrayList<Usertable> itemTable = new ArrayList<Usertable>();
        String query = "select * from finsys.mt_userlogin";
        try {
            PreparedStatement pst = data.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Usertable OpenTab;
            while (rs.next()) {
                //public Usertable(Integer usercode, Integer enabled, String username, String userdescription, String userid, String userpassword, String entrydate, String userrole) {
    
                OpenTab=new Usertable(rs.getInt("usercode"),rs.getInt("enabled"),rs.getString("username"),rs.getString("userdescription")
                ,rs.getString("userid"),"",rs.getString("entrydate"),rs.getString("userrole"));
                itemTable.add(OpenTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemTable;
    }

    private void ReloadTable() {
        ArrayList<Usertable> openingitemlist = getUserTable();
        model = (DefaultTableModel) usertable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < openingitemlist.size(); i++) {
            row[0] = openingitemlist.get(i).getUsercode();
            row[1] = openingitemlist.get(i).getUsername();
            row[2] = openingitemlist.get(i).getUserdescription();
            row[3] = openingitemlist.get(i).getUserid();
            row[4] = openingitemlist.get(i).getUserrole();
            

            model.addRow(row);
        }
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        usertable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    public String md5(String pwd){
         try{
      md=MessageDigest.getInstance("MD5");
      message=md.digest(pwd.getBytes());
      BigInteger number=new BigInteger(1,message);
      String hashtext=number.toString(16);
      while(hashtext.length()<32){
          hashtext="0"+hashtext;
          
      }
      return hashtext;
    }
   catch (NoSuchAlgorithmException ex) {
       throw new RuntimeException(ex);
           // ex.printStackTrace();
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
        txtusername.setText("");
        txtuserdes.setText("");
        txtuserid.setText("");
        rolecombo.setSelectedItem("-");
        txtpwd.setEnabled(true);
        txtrpwd.setEnabled(true);
        txtpwd.setText("");
        txtrpwd.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usertable = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        txtusername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtuserdes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtuserid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtrpwd = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpwd = new javax.swing.JTextField();
        rolecombo = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("User");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("User Table"));
        jPanel2.setOpaque(false);

        usertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Code", "User Name", "User Description", "User Id", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        usertable.setOpaque(false);
        usertable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usertableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usertable);

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Search_16x16.png"))); // NOI18N
        jLabel2.setText("Search : ");

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Delete_16x16.png"))); // NOI18N
        btndelete.setText("delete");
        btndelete.setOpaque(false);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));
        jPanel3.setOpaque(false);

        jLabel1.setText("User Name :");

        jLabel5.setText("Role :");

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Add_16x16.png"))); // NOI18N
        btnadd.setText("add");
        btnadd.setOpaque(false);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
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

        jLabel3.setText("User Description :");

        jLabel9.setText("User Id :");

        jLabel10.setText("Repeat Password :");

        jLabel11.setText("Password :");

        rolecombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Admin", "User" }));

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/icons/Edit_16x16.png"))); // NOI18N
        btnupdate.setText("update");
        btnupdate.setOpaque(false);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtusername))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rolecombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtrpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtuserid)
                            .addComponent(txtuserdes)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtuserdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtuserid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtpwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtrpwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rolecombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnclear)
                    .addComponent(btnupdate))
                .addGap(201, 201, 201))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        ResetRecord();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        
        role=(String)rolecombo.getSelectedItem();
        username=txtusername.getText().trim().toUpperCase();
        userdescription=txtuserdes.getText().trim();
        userid=txtuserid.getText().trim();
        pwd=txtpwd.getText().trim();
        rpwd=txtrpwd.getText().trim();
        String d=md5(pwd);
        
        if("".equals(username)){
             dialogmessage = "PLEASE ENTER USER NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(userdescription)){
             dialogmessage = "PLEASE ENTER USER DESCRIPTION!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(userid)){
             dialogmessage = "PLEASE ENTER USER ID!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if("".equals(pwd)){
             dialogmessage = "PLEASE ENTER PASSWORD !!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if("".equals(rpwd)){
             dialogmessage = "PLEASE ENTER REPEAT PASSWORD!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else if(!pwd.equals(rpwd)){
             dialogmessage = "PASSWORD AND REPEAT PASSWORD DONOT MATCH!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("-".equals(role)){
             dialogmessage = "PLEASE SELECT ROLE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }else{
        db = new database();
        try {

           
                i.setUserdescription(userdescription);
                i.setUserid(userid);
                i.setUsername(username);
                i.setUserpassword(d);
                i.setUserrole(role.charAt(0)+"");
               
                
                //System.out.println("values"+i);
                int maxid=db.getmax("SELECT MAX(usercode) as max FROM finsys.mt_userlogin");
                int result = db.insertUser(i);
                System.out.println(result);
                if (result == 1) {
                    dialogmessage = "USER  ADDED SUCCESSFULLY";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "SUCCESSFULL!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Record Added");
                    m=new Logdetails();
                   int l=m.Initialisem(0,"mt_userlogin",maxid,"A",ucode,"");
                    ResetRecord();
                    ReloadTable();

                } else {
                    dialogmessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                            "WARNING!!", JOptionPane.WARNING_MESSAGE);

                }

            

        } catch (Exception ex) {
            System.out.println("Error while validating :" + ex);
            JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String query = search.getText().toUpperCase();
        filter(query);
    }//GEN-LAST:event_searchKeyReleased

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
                
      
        String query = "delete from finsys.mt_userlogin where usercode='" + ID + "'";
        executeSqlQuery(query, "deleted");
        m=new Logdetails();
        int l=m.Initialisem(0,"mt_userlogin",Integer.valueOf(ID),"D",ucode,"");
        ResetRecord();
                
            }
            else{
                remove(reply);
            }
         }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void usertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usertableMouseClicked
        // TODO add your handling code here:
         int i = usertable.getSelectedRow();
        TableModel model = usertable.getModel();
        ID = model.getValueAt(i, 0).toString();
        
        txtusername.setText(model.getValueAt(i, 1).toString());
        txtuserdes.setText(model.getValueAt(i, 2).toString());
        txtuserid.setText(model.getValueAt(i, 3).toString());
        rolecombo.setSelectedItem(model.getValueAt(i, 4).toString());
        txtpwd.setEnabled(false);
        txtrpwd.setEnabled(false);
    }//GEN-LAST:event_usertableMouseClicked

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // update
        role=(String)rolecombo.getSelectedItem();
        username=txtusername.getText().trim().toUpperCase();
        userdescription=txtuserdes.getText().trim();
        userid=txtuserid.getText().trim();

        if(ID==null){
            dialogmessage = "Please Select Record To Update";
            JOptionPane.showMessageDialog(null,dialogmessage,
                "WARNING!!", JOptionPane.WARNING_MESSAGE);
        }else if("".equals(username)){
             dialogmessage = "PLEASE ENTER USER NAME!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(userdescription)){
             dialogmessage = "PLEASE ENTER USER DESCRIPTION!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(userid)){
             dialogmessage = "PLEASE ENTER USER ID!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        else if("-".equals(role)){
             dialogmessage = "PLEASE SELECT ROLE!!!";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
            else{
                
                String query = "update finsys.mt_userlogin set username='" + username + "',userdescription='" + userdescription+ "',userid='" + userid  + "',userrole='" + role+ "' where usercode='" + ID + "'";
                executeSqlQuery(query, "updated");
                 m=new Logdetails();
                int l=m.Initialisem(0,"mt_userlogin",Integer.valueOf(ID),"U",ucode,"");
                ResetRecord();
                ReloadTable();
            }
        
    }//GEN-LAST:event_btnupdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JComboBox<String> rolecombo;
    private javax.swing.JTextField search;
    private javax.swing.JTextField txtpwd;
    private javax.swing.JTextField txtrpwd;
    private javax.swing.JTextField txtuserdes;
    private javax.swing.JTextField txtuserid;
    private javax.swing.JTextField txtusername;
    private javax.swing.JTable usertable;
    // End of variables declaration//GEN-END:variables

}
