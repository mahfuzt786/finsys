/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

/**
 *
 * @author hp
 */
import java.awt.Container;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


public class Report extends JFrame  {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletOutputStream outstream;
    OutputStream output; 
            
    PrintWriter out = null;
    String reportName="";
    String r="";
    String dt = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    public Report() {
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
    }
    public void issue_slip(String s,String e) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
        reportName="issue_slip.jrxml";
        r="Issue_Slip";
        params.put("startdate",st);
        params.put("enddate",et);
        printreport(params);
    }
     public void return_slip(String s,String e) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
        reportName="return_slip.jrxml";
        r="Return_Slip";
        params.put("startdate",st);
        params.put("enddate",et);
        printreport(params);
    }
     public void ledger_wise(String s,String e) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
          r="Ledger";
        reportName="led.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        printreport(params);
    }
     public void stockin(String s,String e) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
          r="StockIn";
        reportName="stockin.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        printreport(params);
    }
     
      public void store_ledger(String s,String e,Integer m,Integer y) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        
        HashMap params= new HashMap();
          r="Store_Ledger";
        reportName="s.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("omonth",m);
        params.put("oyr",y);
        printreport(params);
    }
      
     public void item_wise(String s,String e,Integer m,Integer y) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        
        HashMap params= new HashMap();
          r="Item_Wise_Stock";
        reportName="itemwise.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("omonth",m);
        params.put("oyr",y);
        printreport(params);
    }
     
      public void eitem_wise(String s,String e,Integer m,Integer y) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        
        HashMap params= new HashMap();
          r="Essential_Item_Wise_Stock";
        reportName="essentialitemwise.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("omonth",m);
        params.put("oyr",y);
        printreport(params);
    }
      
       public void filter_report(String s,String e,int status) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
         DateFormat mn = new SimpleDateFormat("MM");
         DateFormat yr = new SimpleDateFormat("yyyy");
        Integer m=Integer.valueOf(mn.format(st));
        Integer y=Integer.valueOf(yr.format(st));
        switch(status){
            case 1: issue_slip(s,e); break;
            case 2: ledger_wise(s,e);break;
            case 3: store_ledger(s,e,m,y);break;
            case 4: item_wise(s,e,m,y);break;
            case 5: stockin(s,e);break;
            case 6: eitem_wise(s,e,m,y);break;
            case 7: return_slip(s,e); break;
        }
    }
       
      public void ccissueslip(String s,String e,Integer ccid) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
          r="Cost_Center_Issue_Slip";
        reportName="ccissue_slip.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("ccid", ccid);
        printreport(params);
    }
       public void rccissueslip(String s,String e,Integer ccid) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
          r="Cost_Center_Return_Slip";
        reportName="ccreturn_slip.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("ccid", ccid);
        printreport(params);
    }
        
        public void issuecode_issueslip(String code) throws ParseException, JRException, IOException{
       
        HashMap params= new HashMap();
          r="IssueCode_Issue_Slip";
        reportName="issuecode_issue_slip.jrxml";
       
        params.put("iss", code);
        printreport(params);
    }
          public void returncode_issueslip(String code) throws ParseException, JRException, IOException{
       
        HashMap params= new HashMap();
          r="ReturnCode_Issue_Slip";
        reportName="returncode_issue_slip.jrxml";
       
        params.put("iss", code);
        printreport(params);
    }
        public void issuedate_issueslip(String dt) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",dt);
        HashMap params= new HashMap();
          r="IssueDate_Issue_Slip";
        reportName="issuedate_issue_slip.jrxml";
       
        params.put("iss", st);
        printreport(params);
    }
        
         public void returndate_issueslip(String dt) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",dt);
        HashMap params= new HashMap();
          r="ReturnDate_Issue_Slip";
        reportName="issuedate_return_slip.jrxml";
       
        params.put("iss", st);
        printreport(params);
    }
      
         public void ledgerwise_ledger(String s,String e,Integer lid) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
          r="LedgerWise";
        reportName="ledgerwise_led.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("lid", lid);
        printreport(params);
    }
         public void categorystoreledger(String s,String e,Integer catid) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
         
         DateFormat mn = new SimpleDateFormat("MM");
         DateFormat yr = new SimpleDateFormat("yyyy");
        Integer m=Integer.valueOf(mn.format(st));
        Integer y=Integer.valueOf(yr.format(st));
        HashMap params= new HashMap();
        System.out.println(s+" "+e+" "+m+" "+" "+y+" "+catid);
          r="Store_Ledger_Categorywise";
        reportName="scat.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("omonth",m);
        params.put("oyr",y);
        params.put("cid",catid);
        printreport(params);
    }
         
         public void invoiceidstockin(String code) throws ParseException, JRException, IOException{
       
        HashMap params= new HashMap();
          r="StockIn_invoiceid";
        reportName="invoiceidstockin.jrxml";
       
        params.put("invid", code);
        printreport(params);
    }
      
         
          public void log_report(String s,String e,int status) throws ParseException, JRException, IOException{
              HashMap params= new HashMap();
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
         
        params.put("startdate",st);
        params.put("enddate",et);
        
        switch(status){
            case 1: r="Stockin_log";reportName="masterlogstockin.jrxml"; break;
            case 2: r="Stockout_log";reportName="masterlogstockout.jrxml";break;
            case 3: r="UOM_log";reportName="masterloguom.jrxml";break;
            case 4: r="Iemtype_log";reportName="masterlogitype.jrxml";break;
            case 5: r="Itemcategory_log";reportName="masterlogcat.jrxml";break;
            case 6:r="Item_log";reportName="masterlogitem.jrxml";break;
            case 7:r="MS_log";reportName="masterlogms.jrxml";break;
            case 8: r="Costcenter_log";reportName="masterlogcc.jrxml";break;
            case 9: r="Soegroup_log";reportName="masterlogsoegroup.jrxml";break;
            case 10:r="Soemaingroup_log";reportName="masterlogsoemaingroup.jrxml";break;
            case 11:r="Ledger_log";reportName="masterlogledger.jrxml";break;
            case 12: r="Openingstock_log";reportName="masterlogostock.jrxml";break;
            case 13: r="Stockinitems_log";reportName="masterlogstockinitems.jrxml";break;
            case 14:r="Stockoutitems_log";reportName="masterlogstockoutitems.jrxml";break;
           case 15:r="User_log";reportName="masterloguserlogin.jrxml";break;
           case 16:r="Backup_log";reportName="masterlogbackup.jrxml";break;
           case 17:r="Restore_log";reportName="masterlogrestore.jrxml";break;
           case 18:r="Count_log";reportName="masterlogcount.jrxml";break;
           
        
        }
         printreport(params);
    }
      
public void printreport(HashMap params) throws JRException, IOException{
     output = new FileOutputStream(new File("E:/FINSYS/"+r+dt+".pdf"));
        ArrayList al = new ArrayList();
        String m=new String();
       
        
    String filePath=System.getProperty("user.dir");
    System.out.println(filePath);
    database db=new database();
    
   
     String dir=System.getProperty("user.dir");
     params.put("SUBREPORT_DIR", dir+"/src/reports/");

    JasperDesign jd  = JRXmlLoader.load(dir+"/src/reports/"+reportName);
    JasperReport jr = JasperCompileManager.compileReport(dir+"/src/reports/"+reportName);
    JasperPrint  jp = JasperFillManager.fillReport(jr, params,db.conn);
    //JasperViewer.viewReport(jp,false);
    JRViewer viewer = new JRViewer(jp);
    byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jp);
    JasperExportManager.exportReportToPdfStream(jp, output);  
     Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
       
        output.flush();
        output.close();
          



}

   public void log_reportu(String s,String e,int status,int ucode) throws ParseException, JRException, IOException{
              HashMap params= new HashMap();
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
         
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("ucode",ucode);
        switch(status){
            case 1: r="Stockinuser_log";reportName="masterlogstockinu.jrxml"; break;
            case 2: r="Stockoutuser_log";reportName="masterlogstockoutu.jrxml";break;
            case 3: r="UOMuser_log";reportName="masterloguomu.jrxml";break;
            case 4: r="Iemtypeuser_log";reportName="masterlogitypeu.jrxml";break;
            case 5: r="Itemcategoryuser_log";reportName="masterlogcatu.jrxml";break;
            case 6:r="Itemuser_log";reportName="masterlogitemu.jrxml";break;
            case 7:r="MSuser_log";reportName="masterlogmsu.jrxml";break;
            case 8: r="Costcenteruser_log";reportName="masterlogccu.jrxml";break;
            case 9: r="Soegroupuser_log";reportName="masterlogsoegroupu.jrxml";break;
            case 10:r="Soemaingroupuser_log";reportName="masterlogsoemaingroupu.jrxml";break;
            case 11:r="Ledgeruser_log";reportName="masterlogledgeru.jrxml";break;
            case 12: r="Openingstockuser_log";reportName="masterlogostocku.jrxml";break;
            case 13: r="Stockinitemsuser_log";reportName="masterlogstockinitemsu.jrxml";break;
            case 14:r="Stockoutitemsuser_log";reportName="masterlogstockoutitemsu.jrxml";break;
           case 15:r="User_loguser";reportName="masterloguserloginu.jrxml";break;
           case 16:r="Backupuser_log";reportName="masterlogbackupu.jrxml";break;
           case 17:r="Restoreuser_log";reportName="masterlogrestoreu.jrxml";break;
           case 18:r="Countuser_log";reportName="masterlogcountu.jrxml";break;
           
        
        }
         printreport(params);
    }
  
}
