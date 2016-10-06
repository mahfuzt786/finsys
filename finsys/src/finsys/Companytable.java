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
public class Companytable {
     private int companyid;
     private String companyname;

    public Companytable(int companyid, String companyname) {
        this.companyid = companyid;
        this.companyname = companyname;
    }

    public Companytable() {
        this.companyid = 0;
        this.companyname = "";
        
        
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    
    
     
     
    
}
