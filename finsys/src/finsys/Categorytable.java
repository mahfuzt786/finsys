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
public class Categorytable {
    private  int categoryid;
    private String categorycode;
    private String categoryname;

    public Categorytable(int id, String ccode, String cname) {
        this.categoryid = id;
        this.categorycode = ccode;
        this.categoryname = cname;
    }

    public Categorytable() {
        this.categoryid = 0;
        this.categorycode = "";
        this.categoryname = "";
    }

   

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    
    
    
    
    
}
