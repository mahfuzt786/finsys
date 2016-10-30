
package finsys;

public class logtable {
    private final int id;
    private final String loginame;
    private final String logindate;
    public logtable(int ID, String LoginName, String LoginDate){
        this.id=ID;
        this.loginame=LoginName;
        this.logindate=LoginDate;
    }
    public int getLoginId(){
        return id;
    }
    public String getLoginName(){
        return loginame;
    }
    public String getLoginDate(){
        return logindate;
    }
}
