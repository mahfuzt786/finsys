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
public class mstable  {

    private final String msid;
    private final String msname;
    private final String msaddress;
    private final String msphone;
    
    public mstable(String Msid,String Msname,String Msaddress,String Msphone){
        this.msid=Msid;
        this.msname=Msname;
        this.msaddress=Msaddress;
        this.msphone=Msphone;
    }
    public String getMsid() {
        return msid;
    }

    public String getMsname() {
        return msname;
    }
    public String getMsaddress() {
        return msaddress;
    }
    public String getMsphone() {
        return msphone;
    }
}
