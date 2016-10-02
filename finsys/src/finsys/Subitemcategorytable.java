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
public class Subitemcategorytable {
    private int categoryid;
    private int subcategoryid;
    private String subcategorycode,subcategoryname;

    public Subitemcategorytable(int categoryid, int subcategoryid, String subcategorycode, String subcategoryname) {
        this.categoryid = categoryid;
        this.subcategoryid = subcategoryid;
        this.subcategorycode = subcategorycode;
        this.subcategoryname = subcategoryname;
    }

   public Subitemcategorytable() {
        this.categoryid = 0;
        this.subcategoryid = 0;
        this.subcategorycode = "";
        this.subcategoryname = "";
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(int subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public String getSubcategorycode() {
        return subcategorycode;
    }

    public void setSubcategorycode(String subcategorycode) {
        this.subcategorycode = subcategorycode;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }
    
    
    
}
