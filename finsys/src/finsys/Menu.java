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
public class Menu {
 private Integer usercode ;
 private Integer menucode,add_p,edit_p,delete_p,tabid;
 private String menuname;

    public Menu(Integer usercode, Integer menucode, Integer add_p, Integer edit_p, Integer delete_p, Integer tabid, String menuname) {
        this.usercode = usercode;
        this.menucode = menucode;
        this.add_p = add_p;
        this.edit_p = edit_p;
        this.delete_p = delete_p;
        this.tabid = tabid;
        this.menuname = menuname;
    }

    public Menu() {
        this.usercode = 0;
        this.menucode = 0;
        this.add_p = 0;
        this.edit_p = 0;
        this.delete_p = 0;
        this.tabid = 0;
        this.menuname = "";
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public Integer getMenucode() {
        return menucode;
    }

    public void setMenucode(Integer menucode) {
        this.menucode = menucode;
    }

    public Integer getAdd_p() {
        return add_p;
    }

    public void setAdd_p(Integer add_p) {
        this.add_p = add_p;
    }

    public Integer getEdit_p() {
        return edit_p;
    }

    public void setEdit_p(Integer edit_p) {
        this.edit_p = edit_p;
    }

    public Integer getDelete_p() {
        return delete_p;
    }

    public void setDelete_p(Integer delete_p) {
        this.delete_p = delete_p;
    }

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
    
    
 
 
}
