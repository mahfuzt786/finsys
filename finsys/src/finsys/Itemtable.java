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
  private int categoryid,itemid,ledgerid; 
 
  private String itemcode ,itemname,uomcode;
 
    public Itemtable(int categoryid, int itemid, int ledgerid, String itemcode, String itemname, String uomcode) {
        this.categoryid = categoryid;
       
        this.itemid = itemid;
        this.ledgerid = ledgerid;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.uomcode = uomcode;
        
    }
    
     public Itemtable() {
        this.categoryid =0;
       
        this.itemid = 0;
        this.ledgerid = 0;
        this.itemcode = "";
        this.itemname = "";
        this.uomcode = "";
        
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

    public int getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(int ledgerid) {
        this.ledgerid = ledgerid;
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
