
package finsys;

public class uomtable {
    private final int id;
    private final String uomname;
    private final String uomabbr;
    public uomtable(int ID, String UomName, String UomAbbr){
        this.id=ID;
        this.uomname=UomName;
        this.uomabbr=UomAbbr;
    }
    public int getId(){
        return id;
    }
    public String getUomName(){
        return uomname;
    }
    public String getUomAbbr(){
        return uomabbr;
    }
}
