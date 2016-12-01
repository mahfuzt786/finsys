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
public class log_details {
   private int logid ;
  private String logname ,logdate, logouttime;

    public log_details(int logid, String logname, String logdate, String logouttime) {
        this.logid = logid;
        this.logname = logname;
        this.logdate = logdate;
        this.logouttime = logouttime;
    }

    public log_details() {
        this.logid = 0;
        this.logname = "";
        this.logdate = "";
        this.logouttime = "";
    
    }

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getLogdate() {
        return logdate;
    }

    public void setLogdate(String logdate) {
        this.logdate = logdate;
    }

    public String getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(String logouttime) {
        this.logouttime = logouttime;
    }
  
  
}
