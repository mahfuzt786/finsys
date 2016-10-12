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
import java.io.IOException;
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
  
    public void issue_slip(String s,String e) throws ParseException, JRException{
        Date st=UtilDate.convertStringToSqlDate("dd-MM-yyyy",s);
        Date et=UtilDate.convertStringToSqlDate("dd-MM-yyyy",e);
        HashMap params= new HashMap();
        
        params.put("startdate",st);
        params.put("enddate",et);
        printreport(params);
    }
public void printreport(HashMap params) throws JRException{
     
        ArrayList al = new ArrayList();
        String m=new String();
        String  reportName="";
        
    String filePath=System.getProperty("user.dir");
    System.out.println(filePath);
    database db=new database();
    
   
     String dir=System.getProperty("user.dir");

    JasperDesign jd  = JRXmlLoader.load(dir+"/src/reports/issue_slip.jrxml");
    JasperReport jr = JasperCompileManager.compileReport(dir+"/src/reports/issue_slip.jrxml");
    JasperPrint  jp = JasperFillManager.fillReport(jr, params,db.conn);
    JasperViewer.viewReport(jp);


}
    
  
}
