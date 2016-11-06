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
public class Menutable {
    private int menucode,tabid;
    private String menuname,javafilename;

    public Menutable(int menucode, int tabid, String menuname, String javafilename) {
        this.menucode = menucode;
        this.tabid = tabid;
        this.menuname = menuname;
        this.javafilename = javafilename;
    }

    public Menutable() {
        this.menucode = 0;
        this.tabid = 0;
        this.menuname = "";
        this.javafilename = "";
    }

    public int getMenucode() {
        return menucode;
    }

    public void setMenucode(int menucode) {
        this.menucode = menucode;
    }

    public int getTabid() {
        return tabid;
    }

    public void setTabid(int tabid) {
        this.tabid = tabid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getJavafilename() {
        return javafilename;
    }

    public void setJavafilename(String javafilename) {
        this.javafilename = javafilename;
    }
    
    
    
    
}
