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
public class Itemtable {
  private int categoryid,itemid,itemtypeid; 
 
  private String itemcode ,itemname,uomcode,category,itemtype,uom;
 
    public Itemtable(int categoryid, String Category, String Itemtype, String Uom, int itemid, int ledgerid, String itemcode, String itemname, String uomcode) {
        this.categoryid = categoryid;
       
        this.itemid = itemid;
        this.itemtypeid = ledgerid;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.uomcode = uomcode;
        this.category=Category;
        this.itemtype=Itemtype;
        this.uom=Uom;
    }
    
     public Itemtable() {
        this.categoryid =0;
       
        this.itemid = 0;
        this.itemtypeid = 0;
        this.itemcode = "";
        this.itemname = "";
        this.uomcode = "";
        this.category="";
        this.itemtype="";
        this.uom="";
        
    }
     
    public String getCategory(){
        return category;
    }
    public String getItemtype(){
        return itemtype;
    }
    public String getUom(){
        return uom;
    }
    public void setCategory(String Category){
        this.category=Category;
    }
    public void setItemtype(String Itemtype){
        this.itemtype=Itemtype;
    }
    public void setUom(String Uom){
        this.uom=Uom;
    }
    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

     
   
    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(int itemtype) {
        this.itemtypeid = itemtype;
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

    public String getUomcode() {
        return uomcode;
    }

    public void setUomcode(String uomcode) {
        this.uomcode = uomcode;
    }

 
  
  
  
  
    
}
