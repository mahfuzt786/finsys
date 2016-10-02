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
public class Comboitem {
    private  int key;
    private  String value;

    public Comboitem(int k, String v) {
        this.key = k;
        this.value = v;
    }
    public Comboitem(){
        this.key = 0;
        this.value = "";
    }

    
    
    

    
    
    @Override
    public String toString(){
        return value;
    }

    public int getKey() {
        return key;
    }

    public void setKey( int  key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
}
