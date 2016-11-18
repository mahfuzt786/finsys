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
public class Stockoutitemtable {
    private String issue_returncode,itemcode,itemname,categoryname,ledgername; 
   private int itemid,ledgerid,categoryid;
   private Double reqquantity,issuequantity,itemvalue;

    public Stockoutitemtable(String CatName, String LedName,String issue_returncode, String itemcode, String itemname, int itemid, int ledgerid, Double reqquantity, Double issuequantity, Double itemvalue,int categoryid) {
        this.issue_returncode = issue_returncode;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.itemid = itemid;
        this.ledgerid = ledgerid;
        this.reqquantity = reqquantity;
        this.issuequantity = issuequantity;
        this.itemvalue = itemvalue;
        this.categoryid=categoryid;
        this.categoryname=CatName;
        this.ledgername=LedName;
    }

    public Stockoutitemtable() {
        this.issue_returncode = "";
        this.itemcode = "";
        this.itemname = "";
        this.itemid = 0;
        this.ledgerid = 0;
        this.reqquantity = 0.0;
        this.issuequantity = 0.0;
        this.itemvalue = 0.0;
        this.categoryid=0;
        this.categoryname="";
        this.ledgername="";
    }
    public String getCategoryname(){
        return categoryname;
    }
    public void setCategoryname(String cn){
        this.categoryname=cn;
    }
    public String getLedgername(){
        return ledgername;
    }
    public void setLedgername(String ln){
        this.ledgername=ln;
    }
    public String getIssue_returncode() {
        return issue_returncode;
    }

    public void setIssue_returncode(String issue_returncode) {
        this.issue_returncode = issue_returncode;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(int ledgerid) {
        this.ledgerid = ledgerid;
    }

    public Double getReqquantity() {
        return reqquantity;
    }

    public void setReqquantity(Double reqquantity) {
        this.reqquantity = reqquantity;
    }

    public Double getIssuequantity() {
        return issuequantity;
    }

    public void setIssuequantity(Double issuequantity) {
        this.issuequantity = issuequantity;
    }

    public Double getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(Double itemvalue) {
        this.itemvalue = itemvalue;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
    
    
   
    
}
