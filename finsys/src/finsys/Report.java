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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Report  {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletOutputStream outstream;
    OutputStream output; 
            
    PrintWriter out = null;
    String reportName="";
    String r="";
      String dt = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
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
     
      public void store_ledger(String s,String e,Integer m,Integer y) throws ParseException, JRException, IOException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        
        HashMap params= new HashMap();
          r="Item_Wise_Stock";
        reportName="storeledger.jrxml";
        params.put("startdate",st);
        params.put("enddate",et);
        params.put("omonth",m);
        params.put("oyr",y);
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
    JasperViewer.viewReport(jp,false);
    byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jp);
    JasperExportManager.exportReportToPdfStream(jp, output);  
     
       
        output.flush();
        output.close();
          



}

  
}
