/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

/**
 *
 * @author 320
 */
public class Stockintable {
    
     private String invoiceid="";
     private String entrydate="";
     private String taxinvdate="";
     private  String fromcompany="";
     private String taxinvoiceno="";
    
     private String challanno="";
     private String challandate="";
     private String orderno="";
     private String orderdate="";
     private String vat_per="";
     private String less_per="";
     private String transportation="";

    public Stockintable() {
        
        this.invoiceid="";
        this.entrydate="";
        this.challanno="";
        this.challandate="";
        this.fromcompany="";
       
        this.taxinvoiceno="";
        this.taxinvdate="";
        this.less_per="";
        this.transportation="";
        this.vat_per="";
    }
     
     

    public Stockintable(String invoiceid,String entrydate,String taxinvdate,String fromcompany,String taxinvoiceno,String challanno,String challandate,String orderno
    ,String orderdate,String vat_per,String less_per,String transportation) {
        this.invoiceid=invoiceid;
        this.entrydate=entrydate;
        this.challanno=challanno;
        this.challandate=challandate;
        this.fromcompany=fromcompany;
        
        this.taxinvoiceno=taxinvoiceno;
        this.taxinvdate=taxinvdate;
        this.less_per=less_per;
        this.transportation=transportation;
        this.vat_per=vat_per;
        
        
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }
    
    

    public String getTaxinvdate() {
        return taxinvdate;
    }

    public void setTaxinvdate(String taxinvdate) {
        this.taxinvdate = taxinvdate;
    }

    public String getFromcompany() {
        return fromcompany;
    }

    public void setFromcompany(String fromcompany) {
        this.fromcompany = fromcompany;
    }

    public String getTaxinvoiceno() {
        return taxinvoiceno;
    }

    public void setTaxinvoiceno(String taxinvoiceno) {
        this.taxinvoiceno = taxinvoiceno;
    }

   

    public String getChallanno() {
        return challanno;
    }

    public void setChallanno(String challanno) {
        this.challanno = challanno;
    }

    public String getChallandate() {
        return challandate;
    }

    public void setChallandate(String challandate) {
        this.challandate = challandate;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getVat_per() {
        return vat_per;
    }

    public void setVat_per(String vat_per) {
        this.vat_per = vat_per;
    }

    public String getLess_per() {
        return less_per;
    }

    public void setLess_per(String less_per) {
        this.less_per = less_per;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    
    
    
     
     
    
}
