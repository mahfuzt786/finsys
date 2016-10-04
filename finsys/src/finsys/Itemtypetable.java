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
public class Itemtypetable {
    
    private int itemtypeid;
    private String itemtypename;

    public Itemtypetable(int itemtypeid, String itemtypename) {
        this.itemtypeid = itemtypeid;
        this.itemtypename = itemtypename;
    }
    
     public Itemtypetable() {
        this.itemtypeid =0;
        this.itemtypename = "";
    }

    public int getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(int itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public String getItemtypename() {
        return itemtypename;
    }

    public void setItemtypename(String itemtypename) {
        this.itemtypename = itemtypename;
    }
    
    
    
}
