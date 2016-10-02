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
public class Costcentertable  {
     private int centerid;
    private String centercode;
    private String centername;
    public Costcentertable(int id, String ccode, String cname){
        this.centerid=id;
        this.centercode=ccode;
        this.centername=cname;
    }

   public Costcentertable() {
        this.centerid=0;
        this.centercode="";
        this.centername="";
//To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the centerid
     */
    public int getCenterid() {
        return centerid;
    }

    /**
     * @param centerid the centerid to set
     */
    public void setCenterid(int centerid) {
        this.centerid = centerid;
    }

    /**
     * @return the centercode
     */
    public String getCentercode() {
        return centercode;
    }

    /**
     * @param centercode the centercode to set
     */
    public void setCentercode(String centercode) {
        this.centercode = centercode;
    }

    /**
     * @return the centername
     */
    public String getCentername() {
        return centername;
    }

    /**
     * @param centername the centername to set
     */
    public void setCentername(String centername) {
        this.centername = centername;
    }
    
    
}
