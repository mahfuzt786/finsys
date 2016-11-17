/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


public class backup extends javax.swing.JInternalFrame {
    String path=null;
    String filename;   
    public backup() {
        initComponents();
        msg.setVisible(false);
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
        fileurl = new javax.swing.JTextField();
        browse = new javax.swing.JButton();
        backup = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        status = new javax.swing.JScrollPane();
        s = new javax.swing.JTextArea();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Database Backup");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/finsys/shivbari-23x23.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Database Backup"));
        jPanel3.setOpaque(false);

        fileurl.setEditable(false);

        browse.setText("Browse");
        browse.setOpaque(false);
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        backup.setText("Backup");
        backup.setOpaque(false);
        backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupActionPerformed(evt);
            }
        });

        msg.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        msg.setForeground(new java.awt.Color(255, 0, 0));
        msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msg.setText("**********");

        s.setColumns(20);
        s.setRows(5);
        status.setViewportView(s);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fileurl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browse))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(backup)
                        .addGap(18, 18, 18)
                        .addComponent(msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileurl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browse))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backup)
                    .addComponent(msg))
                .addGap(18, 18, 18)
                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        JFileChooser fc= new JFileChooser();
//        fc.showOpenDialog(this);
        File j = null ;
        String date= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try{
          


fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
int returnVal = fc.showSaveDialog(this);
if(returnVal == JFileChooser.APPROVE_OPTION) {
     j = fc.getSelectedFile();
}


            path=j.getAbsolutePath();
            path=path.replace('\\','/');
            path=path+"/finsys_"+date+".backup";
            fileurl.setText(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_browseActionPerformed

    private void backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupActionPerformed

 String fpath=fileurl.getText();
        msg.setText("");

        Runtime rt = Runtime.getRuntime();
    Process p;
    ProcessBuilder pb;
    rt = Runtime.getRuntime();
    //D:\\Program Files\\PostgreSQL\\9.6\\bin\\pg_dump.exe
    pb = new ProcessBuilder(
            "D:\\Program Files\\PostgreSQL\\9.6\\bin\\pg_dump.exe",
            "--host", "localhost",
            "--port", "5432",
            "--username", "postgres",
           
            "--format", "custom",
            "--blobs",
            "--verbose", "--file",fpath, "finsys");
    try {
        final Map<String, String> env = pb.environment();
        env.put("PGPASSWORD", "rajiv   ");
        //env.put("PGPASSWORD", "rajiv   ");
        p = pb.start();
        final BufferedReader r = new BufferedReader(
                new InputStreamReader(p.getErrorStream()));
        String line = r.readLine();
        String temp="";
        while (line != null) {
            System.err.println(line);
            
            line = r.readLine();
            temp=temp+line+"\n";
           
            
            msg.setText("Please Wait...");
            msg.setVisible(true);
        }
        
        r.close();
        p.waitFor();
        if (p.exitValue() == 0) {
             msg.setText("Back Up completed!!");
            msg.setVisible(true);                
            System.out.println("Backup created successfully");
                } else {
            msg.setText("There is an error!!");
            msg.setVisible(true);  
                    System.out.println("There is an error");
                }
        s.setText(temp+"\nExit status: "+p.exitValue());
        System.out.println(p.exitValue());

    } catch (IOException | InterruptedException e) {
        System.out.println(e.getMessage());
    }

           
       

    }//GEN-LAST:event_backupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backup;
    private javax.swing.JButton browse;
    private javax.swing.JTextField fileurl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel msg;
    private javax.swing.JTextArea s;
    private javax.swing.JScrollPane status;
    // End of variables declaration//GEN-END:variables

}
