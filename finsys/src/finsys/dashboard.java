/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;
import java.lang.*;
import java.beans.PropertyVetoException;

/**
 *
 * @author pc1
 */
public class dashboard extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form dashboard
     */
    database db;
    JDesktopPane desktop = new JDesktopPane();
    String sMSGBOX_TITLE	= "FINSYS version 1.0";
    
    // Menu Bar Variables

    JMenuBar menubar = new JMenuBar();

    JMenu menuFile = new JMenu("File");
    JMenu menuUom = new JMenu("UOM");
    JMenu menuTools = new JMenu("Tools");
    JMenu menuReports = new JMenu("Reports");
    JMenu menuHelp = new JMenu("Help");

    // Menu Item

    JMenuItem itemExit = new JMenuItem();

    JMenuItem itemAdd = new JMenuItem();
    JMenuItem itemEdit = new JMenuItem();
    JMenuItem itemDelete = new JMenuItem();

    JMenuItem itemSettings = new JMenuItem();
    JMenuItem itemCalculator = new JMenuItem();
    JMenuItem itemNotePad = new JMenuItem();

    JMenuItem itemEmprpt = new JMenuItem();

    JMenuItem itemAuthor = new JMenuItem();
    JMenuItem itemHelp = new JMenuItem();
    
    // JPanel 

JPanel panel_Bottom = new JPanel();
JPanel panel_Top = new JPanel();

// Label 

JLabel lblUsername = new JLabel("User Name:");
JLabel lblLogDetails = new JLabel("Time Login :");
JLabel lblTimeNow = new JLabel();

// TextField
JTextField username = new JTextField();
JTextField logtime = new JTextField();

// JInternalFrame variables
//Remember to uncomment

Addwindow FormAddwindow;
//Editwindow FormEditwindow;
//Deletewindow FormDeletewindow;
//Settingswindow FormSettingswindow;

//Remember to uncomment

// Date variables

static Date td  = new Date();

// String Variables

static Statement stmtLogin;


//Class Variables
clsSettings settings 	= new clsSettings();

// User Details
	static String sUser		= "";
	static String sLogin 	= DateFormat.getDateTimeInstance().format(td);
	 
	

    public dashboard() {
        initComponents();
        db=new database();
        setIcon();
    }

    public dashboard(String user, Date date){
        
        super("FINANCIAL SYSTEM");
     sUser = user;
     td = date; 
    
    JTextField username = new JTextField();
    username.setEditable(false);
	JTextField logtime = new JTextField();
	logtime.setEditable(false);
	username.setText(sUser);
	logtime.setText(sLogin);
    
     panel_Bottom.setLayout(new FlowLayout());
     panel_Bottom.setPreferredSize(new Dimension(10,25));
    // panel_Bottom.add(lblUserIcon);
     panel_Bottom.add(lblUsername);
     panel_Bottom.add(username);
     panel_Bottom.add(lblLogDetails);
     panel_Bottom.add(logtime);

     
     panel_Top.setLayout(new BorderLayout());
     panel_Top.setPreferredSize(new Dimension(10,65));
     panel_Top.add(createJToolBar(),BorderLayout.PAGE_START);
          
     desktop.setBackground(Color.WHITE);
     desktop.setAutoscrolls(true);
     desktop.setBorder(BorderFactory.createLoweredBevelBorder());
     desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
     
     getContentPane().add(panel_Top,BorderLayout.PAGE_START);
     getContentPane().add(desktop,BorderLayout.CENTER);
     getContentPane().add(panel_Bottom,BorderLayout.PAGE_END);
     
     
     
     addWindowListener(new WindowAdapter(){
         
     public void windowClosing(WindowEvent e)
     {
         UnloadWindow();
     }
     });
     
     setJMenuBar(CreateJMenuBar());
     setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
     setIconImage(new ImageIcon("./src/finsys/Business.png").getImage());
     setSize(700,700);
     setLocation(2,2);
     show();
        
        
    }
    
    
    protected JMenuBar CreateJMenuBar()
    {
       
        
        // creating Submenu
        // Menu File
        menuFile.add(settings.setJMenuItem(itemExit,"Quit","./src/finsys/exit.png"));
        
        itemExit.addActionListener(this);
        
        // MEnu Employee
        menuUom.add(settings.setJMenuItem(itemAdd,"Add UOM","./src/finsys/info.png"));
        menuUom.add(settings.setJMenuItem(itemEdit,"Edit UOM","./src/finsys/edit.png"));
        menuUom.addSeparator();
        menuUom.add(settings.setJMenuItem(itemDelete,"Delete UOM","./src/finsys/delete.png"));
        
        
        itemAdd.addActionListener(this);
        itemEdit.addActionListener(this);
        itemDelete.addActionListener(this);
        
        // setting tool bar
        menuTools.add(settings.setJMenuItem(itemSettings,"Settings","./src/finsys/setting.png"));
        menuTools.add(settings.setJMenuItem(itemCalculator,"Calculator","./src/finsys/calc.png"));
        menuTools.addSeparator();
        menuTools.add(settings.setJMenuItem(itemNotePad,"NotePad","./src/finsys/notepad.png"));
        
        
        itemSettings.addActionListener(this);
        itemCalculator.addActionListener(this);
        itemNotePad.addActionListener(this);
        
        // setting Reports bar
          
        menuReports.add(settings.setJMenuItem(itemEmprpt,"Issue Report","./src/finsys/emp_rpt.png"));
         menuTools.addSeparator();
          menuTools.addSeparator();
        itemEmprpt.addActionListener(this);
        
        // setting Help
        
        menuHelp.add(settings.setJMenuItem(itemAuthor,"About System","./src/finsys/xp.png"));
        menuHelp.add(settings.setJMenuItem(itemHelp,"Help","./src/finsys/help.png"));
        
        itemAuthor.addActionListener(this);
        itemHelp.addActionListener(this);
        
        // adding menuitem to menubar
        
        menubar.add(settings.setJMenu(menuFile));
        menubar.add(settings.setJMenu(menuUom));
        menubar.add(settings.setJMenu(menuTools));
        menubar.add(settings.setJMenu(menuReports));
        menubar.add(settings.setJMenu(menuHelp));
       return menubar;

    }
    
    protected JToolBar createJToolBar()
    {
        JToolBar toolbar = new JToolBar("Toolbar");
        
        toolbar.add(settings.CreateJToolbarButton("Exit", "./src/finsys/exit.png", "File_Exit",
                JToolBarActionListener));
			toolbar.addSeparator();
			toolbar.addSeparator();

        toolbar.add(settings.CreateJToolbarButton("Add - UOM", "./src/finsys/info.png", "Emp_Add",
                JToolBarActionListener));
        
        toolbar.add(settings.CreateJToolbarButton("Edit - UOM", "./src/finsys/edit.png", "Emp_Edit",
                JToolBarActionListener));
        		toolbar.addSeparator();

        toolbar.add(settings.CreateJToolbarButton("Delete - UOM", "./src/finsys/delete.png","Emp_Delete",
                JToolBarActionListener));
		toolbar.addSeparator();
		toolbar.addSeparator();
		

        
        toolbar.add(settings.CreateJToolbarButton("FinSYS Settings", "./src/finsys/setting.png","Settings",
                JToolBarActionListener));
        toolbar.add(settings.CreateJToolbarButton("FinSYS calculator", "./src/finsys/calc.png","Tools_Calculator",
                JToolBarActionListener));
        toolbar.add(settings.CreateJToolbarButton("NotePad", "./src/finsys/notepad.png","Tools_NotePad",
                JToolBarActionListener));
				toolbar.addSeparator();
				toolbar.addSeparator();

        
        toolbar.add(settings.CreateJToolbarButton("FinSYS - Report", "./src/finsys/emp_rpt.png","Reports_Employee",
                JToolBarActionListener));
        

        
        toolbar.add(settings.CreateJToolbarButton("Help ", "./src/finsys/xp.png","Help_Author",
                JToolBarActionListener));
        
        toolbar.add(settings.CreateJToolbarButton("Help - Help", "./src/finsys/help.png","Help_Help",
                JToolBarActionListener));
        return toolbar;
        
    }
    
    ActionListener JToolBarActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            
            if (source == "File_Exit")
            {
                loadJInternalFrame(2);
            }
            else if (source == "Emp_Add")
            {
                loadJInternalFrame(3);                
            }
            else if (source == "Emp_Edit")
            {
                loadJInternalFrame(4);                
            }
            else if (source == "Emp_Delete")
            {
                loadJInternalFrame(5);                
            }
            else if (source == "Settings")
            {
                loadJInternalFrame(6);                
            }
            else if (source == "Tools_Calculator")
            {
                loadJInternalFrame(7);                
            }
            else if (source == "Tools_NotePad")
            {
                loadJInternalFrame(8);
            }
            else if (source == "Reports_Employee")
            {
                loadJInternalFrame(9);
            }
            
            else if (source == "Help_Author")
            {
                loadJInternalFrame(11);
            }
            else if (source == "Help_Help")
            {
                loadJInternalFrame(12);
            }
        }
    
    };
    
      
    public void actionPerformed(ActionEvent event)
    {
        Object object = event.getSource();
        
        if (object ==  itemExit)
        {
            loadJInternalFrame(2);
        }
        else if (object == itemAdd)
        {
            loadJInternalFrame(3);
        }
        else if ( object == itemEdit)
        {
            loadJInternalFrame(4);
        }
        else if (object == itemDelete)
        {
            loadJInternalFrame(5);
        }
        else if (object == itemSettings)
        {
            loadJInternalFrame(6);
        }
        else if (object == itemCalculator)
        {
            loadJInternalFrame(7);
            
        }
        else if (object == itemNotePad)
        {
            loadJInternalFrame(8);
        }
        else if (object == itemEmprpt)
        {
            loadJInternalFrame(9);
        }
        
        else if (object == itemAuthor)
        {
            loadJInternalFrame(12);
        }
        else if (object == itemHelp)
        {
            loadJInternalFrame(13);
        }
    }
    private void loadJInternalFrame(int intWhich)
    {
        switch(intWhich)
        {
            
            case 2:
                System.exit(0);
                break;
            
            case 3:
                try {
                	FormAddwindow = new Addwindow(this);
               loadForm("Add UOM", FormAddwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 4:
                try {
//                	FormEditwindow = new Editwindow(this);
//               loadForm("Edit Employee", FormEditwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 5:
                try {
//                	FormDeletewindow = new Deletewindow(this);
//               loadForm("Delete Employee", FormDeletewindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 6:
                try {
//                	FormSettingswindow = new Settingswindow(this);
//               loadForm("Settings of Employee", FormSettingswindow);
                }
                catch(Exception e)
                {
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
            	try{
//            		FormEmprptwindow = new Emprptwindow(this);
//               		loadForm("Employee PaySlip", FormEmprptwindow);
            	
                }
                catch(Exception e)
                {
                	System.out.println("\nError" + e );
                }
                break;
                
               
            case 12:
                //FormAuthorwindow = new Authorwindow(this);
                break;
            
            case 13:
                //FormHelpwindow = new Helpwindow(this);
                break;
                
                
        }
        
    }
    	protected void runComponents(String sComponents)
	{
		Runtime rt = Runtime.getRuntime();
		try{rt.exec(sComponents);}
		catch(IOException evt){JOptionPane.showMessageDialog(null,evt.getMessage(),"Error Found",JOptionPane.ERROR_MESSAGE);}
	}

protected void loadForm(String Title, JInternalFrame clsForm)
{

boolean xForm = isLoaded(Title);
if (xForm == false)
{
desktop.add(clsForm);
clsForm.setVisible(true);
clsForm.show();
}
else
{
try {
clsForm.setIcon(false);
clsForm.setSelected(true);

}
catch(PropertyVetoException e)
{}
 }
} // Complete Load Form methode


protected boolean isLoaded(String FormTitle)
{
 	JInternalFrame Form[] = desktop.getAllFrames();
	for ( int i = 0; i < Form.length; i++)
	{
	if (Form[i].getTitle().equalsIgnoreCase(FormTitle))
		{
			Form[i].show();
			try
			{
			Form[i].setIcon(false);
			Form[i].setSelected(true);
			
			}
			catch(PropertyVetoException e)
			{
				
				}
			return true;
		}	
	}
	return false;
} // Complete to Verify Form loaded or not

protected void UnloadWindow()
{
try
   {
	int reply = JOptionPane.showConfirmDialog(this,"Are you sure to exit?",sMSGBOX_TITLE,JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE);
		if (reply == JOptionPane.YES_OPTION)
			{
			
			setVisible(false);
			System.exit(0);
			}
   }
	catch(Exception e)
	{}

}// Close the Windows

  
	public static void setlogin(String sUsername, Date sDate)
	{
		sUser  = sUsername;
		td	   = sDate;
		
		
	}//Set Login    
	

    
    
    





        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FINSYS - 1.0");
        setExtendedState(6);
        setLocationByPlatform(true);
        setName("frame_dashboard"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("./src/finsys/shivbari-23x23.png"))); 
    }

    
}
