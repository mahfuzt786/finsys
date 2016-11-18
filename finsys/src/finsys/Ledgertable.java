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
public class Ledgertable  {
    
    private int ledgerid,soegroupid , soemaingroupid;
    private String ledgercode,ledgername,soe,soemain ;

    public Ledgertable( int soemaingroupid,String Soe,int soegroupid, String Soemain, int ledgerid, String ledgercode, String ledgername) {
        this.ledgerid = ledgerid;
        this.soegroupid = soegroupid;
        this.soe = Soe;
        this.soemaingroupid = soemaingroupid;
        this.soemain = Soemain;
        this.ledgercode = ledgercode;
        this.ledgername = ledgername;
    }
    
    public Ledgertable() {
        this.ledgerid = 0;
        this.soegroupid = 0;
        this.soemaingroupid = 0;
        this.ledgercode = "";
        this.ledgername = "";
        this.soe = "";
        this.soemain = "";
    }
    public String getSoe(){
        return soe;
    }
    public void setSoe(String SOE){
        this.soe=SOE;
    }
    public String getSoemain(){
        return soemain;
    }
    public void setSoemain(String SOEMAIN){
        this.soemain=SOEMAIN;
    }

    public int getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(int ledgerid) {
        this.ledgerid = ledgerid;
    }

    public int getSoegroupid() {
        return soegroupid;
    }

    public void setSoegroupid(int soegroupid) {
        this.soegroupid = soegroupid;
    }

    public int getSoemaingroupid() {
        return soemaingroupid;
    }

    public void setSoemaingroupid(int soemaingroupid) {
        this.soemaingroupid = soemaingroupid;
    }

    public String getLedgercode() {
        return ledgercode;
    }

    public void setLedgercode(String ledgercode) {
        this.ledgercode = ledgercode;
    }

    public String getLedgername() {
        return ledgername;
    }

    public void setLedgername(String ledgername) {
        this.ledgername = ledgername;
    }

    
    
    
}
