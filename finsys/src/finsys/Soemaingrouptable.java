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
public class Soemaingrouptable  {
    private int soemaingroupid;
    private String soemaingroupcode,soemaingroupname;

    public Soemaingrouptable(int soemaingroupid, String soemaingroupcode, String soemaingroupname) {
        this.soemaingroupid = soemaingroupid;
        this.soemaingroupcode = soemaingroupcode;
        this.soemaingroupname = soemaingroupname;
    }

     public Soemaingrouptable() {
       this.soemaingroupid = 0;
        this.soemaingroupcode = "";
        this.soemaingroupname = "";
    }

    public int getSoemaingroupid() {
        return soemaingroupid;
    }

    public void setSoemaingroupid(int soemaingroupid) {
        this.soemaingroupid = soemaingroupid;
    }
    
  

    public String getSoemaingroupcode() {
        return soemaingroupcode;
    }

    public void setSoemaingroupcode(String soemaingroupcode) {
        this.soemaingroupcode = soemaingroupcode;
    }

    public String getSoemaingroupname() {
        return soemaingroupname;
    }

    public void setSoemaingroupname(String soemaingroupname) {
        this.soemaingroupname = soemaingroupname;
    }
    
    
}
