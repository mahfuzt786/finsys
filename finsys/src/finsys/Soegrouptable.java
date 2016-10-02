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
public class Soegrouptable {
    private int soemaingroupid;
    private int soegroupid;
    private String soegroupcode,soegroupname;

    public Soegrouptable(int soemaingroupid, int soegroupid, String soegroupcode, String soegroupname) {
        this.soemaingroupid = soemaingroupid;
        this.soegroupid = soegroupid;
        this.soegroupcode = soegroupcode;
        this.soegroupname = soegroupname;
    }

   public Soegrouptable() {
        this.soemaingroupid = 0;
        this.soegroupid = 0;
        this.soegroupcode = "";
        this.soegroupname = "";
    }

    public int getSoemaingroupid() {
        return soemaingroupid;
    }

    public void setSoemaingroupid(int soemaingroupid) {
        this.soemaingroupid = soemaingroupid;
    }

    public int getSoegroupid() {
        return soegroupid;
    }

    public void setSoegroupid(int soegroupid) {
        this.soegroupid = soegroupid;
    }

    public String getSoegroupcode() {
        return soegroupcode;
    }

    public void setSoegroupcode(String soegroupcode) {
        this.soegroupcode = soegroupcode;
    }

    public String getSoegroupname() {
        return soegroupname;
    }

    public void setSoegroupname(String soegroupname) {
        this.soegroupname = soegroupname;
    }
    
    
    
}
