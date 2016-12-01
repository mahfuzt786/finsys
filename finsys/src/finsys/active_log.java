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
public class active_log {
    private int slno;
    private String initial_date;

    public active_log(int slno, String initial_date) {
        this.slno = slno;
        this.initial_date = initial_date;
    }

    public active_log() {
        this.slno = 0;
        this.initial_date = "";
    
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(String initial_date) {
        this.initial_date = initial_date;
    }
    
    
    
    
}
