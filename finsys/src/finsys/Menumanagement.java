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
import java.awt.event.ItemEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Menumanagement extends javax.swing.JInternalFrame {

    String Id = "";
    String Month = "";
    String Year = "";
    String Qty = "";
    String Val = "";
    database db;
    openingtable i = new openingtable();
    String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    database data = new database();
    public String ID="";
    DefaultTableModel model;
    ArrayList<Usertable> u,usr;
    ArrayList<Menu> menu;
    Usertable uu;
    Integer tousercode;
    PatternValidation pattern=new PatternValidation();
    
    public Menumanagement(int usercode) {
        initComponents();
        db=new database();
      
        u = db.getUser();
        Date dt=new Date(); 
        disablebtn.setVisible(false);
        enablebtn.setVisible(false);
        usercombo.addItem(new Comboitem(0,"Select User"));
        for(Usertable c:u){
            usercombo.addItem(new Comboitem(c.getUsercode(),c.getUsername()));
        }
        menutable=new JTable(model){
            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Double.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        menutable.setPreferredScrollableViewportSize(menutable.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(menutable);
        getContentPane().add(scrollPane);
    
                
        
        
       
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
        menutable = new javax.swing.JTable();
        submitbtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usercombo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usercodetxt = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        enablebtn = new javax.swing.JButton();
        disablebtn = new javax.swing.JButton();
        useridtxt = new javax.swing.JLabel();
        usernametxt = new javax.swing.JLabel();
        userdestxt = new javax.swing.JLabel();
        userroletxt = new javax.swing.JLabel();
        entxt = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Menu Management");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        jPanel2.setOpaque(false);

        menutable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Assign", "Menu Code", "Menu Name", "Add", "Update", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menutable.setOpaque(false);
        jScrollPane1.setViewportView(menutable);
        if (menutable.getColumnModel().getColumnCount() > 0) {
            menutable.getColumnModel().getColumn(0).setResizable(false);
            menutable.getColumnModel().getColumn(0).setPreferredWidth(50);
            menutable.getColumnModel().getColumn(1).setPreferredWidth(80);
            menutable.getColumnModel().getColumn(3).setResizable(false);
            menutable.getColumnModel().getColumn(3).setPreferredWidth(50);
            menutable.getColumnModel().getColumn(4).setPreferredWidth(50);
            menutable.getColumnModel().getColumn(5).setResizable(false);
            menutable.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        submitbtn.setText("SUBMIT");
        submitbtn.setOpaque(false);
        submitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(submitbtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));
        jPanel3.setOpaque(false);

        jLabel1.setText("User Name :");

        usercombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usercomboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usercombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usercombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("User Details"));
        jPanel4.setOpaque(false);

        jLabel2.setText("User Code: ");

        usercodetxt.setText("*****");

        jLabel5.setText("User Id: ");

        jLabel6.setText("User Name: ");

        jLabel7.setText("User Description : ");

        jLabel8.setText("User Role : ");

        jLabel9.setText("Enabled :");

        enablebtn.setText("Enable");
        enablebtn.setOpaque(false);
        enablebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enablebtnActionPerformed(evt);
            }
        });

        disablebtn.setText("Disable");
        disablebtn.setOpaque(false);
        disablebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disablebtnActionPerformed(evt);
            }
        });

        useridtxt.setText("*****");

        usernametxt.setText("*****");

        userdestxt.setText("*****");

        userroletxt.setText("*****");

        entxt.setText("*****");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(useridtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usercodetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(disablebtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enablebtn))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(userdestxt, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                        .addComponent(userroletxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usernametxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(entxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usercodetxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(useridtxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usernametxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(userdestxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(userroletxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(entxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disablebtn)
                    .addComponent(enablebtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void usercomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usercomboItemStateChanged
        // TODO add your handling code here:
         Object it = evt.getItem();
                
                System.out.println("Affected items: " + it.toString());
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                     
                   
                     Comboitem g1=(Comboitem) usercombo.getSelectedItem();
                     int ucode=g1.getKey();
                     tousercode=ucode;
                     System.out.println("user selected: "+ucode);
                     usr=db.getUser1(ucode);
                     for(Usertable c:usr){
                          usercodetxt.setText(c.getUsercode()+"");
                          useridtxt.setText(c.getUserid()+"");
                          userdestxt.setText(c.getUserdescription()+"");
                          userroletxt.setText(c.getUserrole()+"");
                          entxt.setText(c.getEnabled()+"");
                          usernametxt.setText(c.getUsername()+"");
                          if(c.getEnabled()==1){
                          disablebtn.setVisible(true);
        
                          }
                          else{
                            enablebtn.setVisible(true);
                          }
                          
                     }
                     menu=db.getPrivilege2(ucode);
                     model = (DefaultTableModel) menutable.getModel();
                     model.setRowCount(0);
                    Object[] row = new Object[6];
                    boolean s,a,u,d;
                    for (int i = 0; i < menu.size(); i++) {
                        s=false;
                        a=false;
                        u=false;
                        d=false;
                        System.out.println("usercode: "+menu.get(i).getUsercode());
                        if(menu.get(i).getUsercode()!=0){
                            s=true;
                            if(menu.get(i).getAdd_p()==1){
                                a=true;
                            }
                             if(menu.get(i).getEdit_p()==1){
                                u=true;
                            }
                              if(menu.get(i).getDelete_p()==1){
                                d=true;
                            }
                            
                        }
                            row[0] = s;
                            row[1] = menu.get(i).getMenucode();
                            row[2] = menu.get(i).getMenuname();
                            row[3]=a;
                            row[4]=u;
                            row[5]=d;
                         model.addRow(row);
                     }
                   
                }

    }//GEN-LAST:event_usercomboItemStateChanged

    private void enablebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enablebtnActionPerformed
        // TODO add your handling code here:
        int i=db.Enableuser(Integer.valueOf(usercodetxt.getText().trim()), 1);
         if(i==1){
            JOptionPane.showMessageDialog(null,"User enabled successfully!!!",
                            "WARNING!!", JOptionPane.INFORMATION_MESSAGE);
            Reset();
        }
         else{
         
            JOptionPane.showMessageDialog(null,"Error !!!",
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
            Reset();
        
         }
    }//GEN-LAST:event_enablebtnActionPerformed

    private void disablebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disablebtnActionPerformed
        // TODO add your handling code here:
        int i=db.Enableuser(Integer.valueOf(usercodetxt.getText().trim()), 0);
        if(i==1){
            JOptionPane.showMessageDialog(null,"User disabled successfully!!!",
                            "WARNING!!", JOptionPane.INFORMATION_MESSAGE);
            Reset();
        }
        else{
         
            JOptionPane.showMessageDialog(null,"Error !!!",
                            "WARNING!!", JOptionPane.ERROR_MESSAGE);
            Reset();
        
         }
    }//GEN-LAST:event_disablebtnActionPerformed

    private void Reset(){
         model = (DefaultTableModel) menutable.getModel();
         model.setRowCount(0);
         usercombo.setSelectedIndex(0);
         usercodetxt.setText("*****");
         useridtxt.setText("*****");
         userdestxt.setText("*****");
         userroletxt.setText("*****");
         entxt.setText("*****");
         usernametxt.setText("*****");
         disablebtn.setVisible(false);
         enablebtn.setVisible(false);
    }
    
    private void submitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbtnActionPerformed
        // TODO add your handling code here:
        Integer add=0,edit=0,delete=0;
        ArrayList<Menu> newmenu = new ArrayList<>();;
        Menu n;
        int assigned;
        for(int i=0;i<menutable.getModel().getRowCount();i++)
                  {
                    if ((Boolean) menutable.getModel().getValueAt(i,0))
                    {  
                      System.out.println("\t"+menutable.getModel().getValueAt(i,2));
                      if ((Boolean) menutable.getModel().getValueAt(i,3)){
                          add=1;
                      }
                      if ((Boolean) menutable.getModel().getValueAt(i,4)){
                          edit=1;
                      }
                      if ((Boolean) menutable.getModel().getValueAt(i,5)){
                          delete=1;
                      }
                      
                      n=new Menu(tousercode,Integer.valueOf(menutable.getModel().getValueAt(i,1).toString()),add,edit,delete,0,menutable.getModel().getValueAt(i,2).toString());
                      newmenu.add(n);
                    }
                 }  
        assigned=db.assignMenu(newmenu, tousercode);
        if(assigned==1){
            JOptionPane.showMessageDialog(null,"Menu assigned successfully!!!",
                            "WARNING!!", JOptionPane.INFORMATION_MESSAGE);
            Reset();
        }
    }//GEN-LAST:event_submitbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton disablebtn;
    private javax.swing.JButton enablebtn;
    private javax.swing.JLabel entxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JTable menutable;
    private javax.swing.JButton submitbtn;
    private javax.swing.JLabel usercodetxt;
    private javax.swing.JComboBox<Comboitem> usercombo;
    private javax.swing.JLabel userdestxt;
    private javax.swing.JLabel useridtxt;
    private javax.swing.JLabel usernametxt;
    private javax.swing.JLabel userroletxt;
    // End of variables declaration//GEN-END:variables

}
