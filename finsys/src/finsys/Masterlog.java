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
public class Masterlog {
    private Integer slno; 
    private String tablename;
    private Integer uniquetableid;
    private String operation;
    private Integer usercode;
    private String entrydate;
   

    public Masterlog(Integer slno, String tablename, Integer uniquetableid, String operation, Integer usercode, String entrydate) {
        this.slno = slno;
        this.tablename = tablename;
        this.uniquetableid = uniquetableid;
        this.operation = operation;
        this.usercode = usercode;
        this.entrydate = entrydate;
    }

    public Masterlog() {
        this.slno = 0;
        this.tablename = "";
        this.uniquetableid = 0;
        this.operation = "";
        this.usercode = 0;
        this.entrydate = "";
    }

    public Integer getSlno() {
        return slno;
    }

    public void setSlno(Integer slno) {
        this.slno = slno;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Integer getUniquetableid() {
        return uniquetableid;
    }

    public void setUniquetableid(Integer uniquetableid) {
        this.uniquetableid = uniquetableid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }
  
    
    
}
