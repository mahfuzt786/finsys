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
public class Stockinitemtable {
    
    private int itemid;
    private String itemcode,itemname,invoiceid,item_rate,quantity,grossvalue;

    public Stockinitemtable(int itemid, String itemcode, String itemname, String invoiceid, String item_rate, String quantity, String grossvalue) {
        this.itemid = itemid;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.invoiceid = invoiceid;
        this.item_rate = item_rate;
        this.quantity = quantity;
        this.grossvalue=grossvalue;
    }

    public Stockinitemtable() {
        
        this.itemid = 0;
        this.itemcode = "";
        this.itemname = "";
        this.invoiceid = "";
        this.item_rate = "";
        this.quantity = "";
        this.grossvalue="";
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
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

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getItem_rate() {
        return item_rate;
    }

    public void setItem_rate(String item_rate) {
        this.item_rate = item_rate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(String grossvalue) {
        this.grossvalue = grossvalue;
    }
    
    
   
}
