
package finsys;

public class logtable {
    private final int id;
    private final String loginame;
    private final String logindate;
    private final String logouttime;
    public logtable(int ID, String LoginName, String LoginDate,String LogoutTime){
        this.id=ID;
        this.loginame=LoginName;
        this.logindate=LoginDate;
        this.logouttime=LogoutTime;
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

    public String getLogouttime() {
        return logouttime;
    }
    
    
}
