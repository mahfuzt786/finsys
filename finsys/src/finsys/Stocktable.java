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
public class Stocktable {
    private int itemid;
    private String amount,quantity;

    public Stocktable(int itemid, String amount, String quantity) {
        this.itemid = itemid;
        this.amount = amount;
        this.quantity = quantity;
    }

    public Stocktable() {
        
         this.itemid = 0;
        this.amount = "";
        this.quantity = "";
    }

    
    
    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    
}
