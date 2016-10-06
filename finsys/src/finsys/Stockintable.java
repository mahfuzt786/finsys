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
     private String slno;
     private String invoiceno;
     private String total_amt_value;
     
     private String entrydate;
     private String transportation_amt;
     private String less_per;
     private  String from_company_id;
     private String tax_invoice_no;
    
     private String tax_invoice_date;
     private String challan_no;
     private String challan_date;
     private String purchase_order_no;
     private String purchase_order_date;
     private String vat_per;
     private String invoiceid;
     private String total_gross_amt;
     private String companyname;

    public Stockintable(String slno, String invoiceno, String total_amt_value, String entrydate, String transportation_amt,String less_per,
            String from_company_id, String tax_invoice_no, String tax_invoice_date, String challan_no, String challan_date,
            String purchase_order_no, String purchase_order_date, String vat_per, String invoiceid, String total_gross_amt, String companyname) {
        this.slno = slno;
        this.invoiceno = invoiceno;
        this.total_amt_value = total_amt_value;
        this.entrydate = entrydate;
        this.transportation_amt = transportation_amt;
        this.from_company_id = from_company_id;
        this.tax_invoice_no = tax_invoice_no;
        this.tax_invoice_date = tax_invoice_date;
        this.challan_no = challan_no;
        this.challan_date = challan_date;
        this.purchase_order_no = purchase_order_no;
        this.purchase_order_date = purchase_order_date;
        this.vat_per = vat_per;
        this.invoiceid = invoiceid;
        this.total_gross_amt = total_gross_amt;
        this.companyname = companyname;
        this.less_per = less_per;
    }

    public Stockintable() {
        this.slno = "";
        this.invoiceno = "";
        this.total_amt_value = "";
        this.entrydate = "";
        this.transportation_amt = "";
        this.from_company_id = "";
        this.tax_invoice_no = "";
        this.tax_invoice_date = "";
        this.challan_no = "";
        this.challan_date = "";
        this.purchase_order_no = "";
        this.purchase_order_date = "";
        this.vat_per = "";
        this.invoiceid = "";
        this.total_gross_amt = "";
        this.companyname = "";
        this.less_per ="";
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getTotal_amt_value() {
        return total_amt_value;
    }

    public void setTotal_amt_value(String total_amt_value) {
        this.total_amt_value = total_amt_value;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getTransportation_amt() {
        return transportation_amt;
    }

    public void setTransportation_amt(String transportation_amt) {
        this.transportation_amt = transportation_amt;
    }

    public String getFrom_company_id() {
        return from_company_id;
    }

    public void setFrom_company_id(String from_company_id) {
        this.from_company_id = from_company_id;
    }

    public String getTax_invoice_no() {
        return tax_invoice_no;
    }

    public void setTax_invoice_no(String tax_invoice_no) {
        this.tax_invoice_no = tax_invoice_no;
    }

    public String getTax_invoice_date() {
        return tax_invoice_date;
    }

    public void setTax_invoice_date(String tax_invoice_date) {
        this.tax_invoice_date = tax_invoice_date;
    }

    public String getChallan_no() {
        return challan_no;
    }

    public void setChallan_no(String challan_no) {
        this.challan_no = challan_no;
    }

    public String getChallan_date() {
        return challan_date;
    }

    public void setChallan_date(String challan_date) {
        this.challan_date = challan_date;
    }

    public String getPurchase_order_no() {
        return purchase_order_no;
    }

    public void setPurchase_order_no(String purchase_order_no) {
        this.purchase_order_no = purchase_order_no;
    }

    public String getPurchase_order_date() {
        return purchase_order_date;
    }

    public void setPurchase_order_date(String purchase_order_date) {
        this.purchase_order_date = purchase_order_date;
    }

    public String getVat_per() {
        return vat_per;
    }

    public void setVat_per(String vat_per) {
        this.vat_per = vat_per;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getTotal_gross_amt() {
        return total_gross_amt;
    }

    public void setTotal_gross_amt(String total_gross_amt) {
        this.total_gross_amt = total_gross_amt;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLess_per() {
        return less_per;
    }

    public void setLess_per(String less_per) {
        this.less_per = less_per;
    }

    
    
   
    
}
