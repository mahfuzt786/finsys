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
public class Logdetails {
    Masterlog m;
    Stocklog s;
    database db=new database();
    int flag;

    public Logdetails() {
        flag=0;
    }
    
    public int Initialisem(Integer slno, String tablename, Integer uniquetableid, String operation, Integer usercode, String entrydate){
        m=new Masterlog(slno,tablename,uniquetableid,operation,usercode,"");
        flag=db.insertMasterlog(m);
        return flag;
    }
    public int InitialiseS(Integer slno, String tablename, String uniquetableid, String operation, Integer usercode, String entrydate,Integer itemid){
        s=new Stocklog(slno,tablename,uniquetableid,operation,usercode,"",itemid);
        flag=db.insertStocklog(s);
        return flag;
    }
    
    
    
    
}
