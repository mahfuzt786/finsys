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
public class Ledgertable {
    
    private int ledgerid,soegroupid , soemaingroupid;
    private String ledgercode,ledgername ;

    public Ledgertable( int soemaingroupid,int soegroupid, int ledgerid, String ledgercode, String ledgername) {
        this.ledgerid = ledgerid;
        this.soegroupid = soegroupid;
        this.soemaingroupid = soemaingroupid;
        this.ledgercode = ledgercode;
        this.ledgername = ledgername;
    }
    
    public Ledgertable() {
        this.ledgerid = 0;
        this.soegroupid = 0;
        this.soemaingroupid = 0;
        this.ledgercode = "";
        this.ledgername = "";
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
