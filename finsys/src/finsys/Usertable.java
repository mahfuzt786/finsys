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
public class Usertable {
   
    private Integer usercode,enabled;
    private String username;
    private String userdescription,userid,userpassword,entrydate,userrole;

    public Usertable(Integer usercode, Integer enabled, String username, String userdescription, String userid, String userpassword, String entrydate, String userrole) {
        this.usercode = usercode;
        this.enabled = enabled;
        this.username = username;
        this.userdescription = userdescription;
        this.userid = userid;
        this.userpassword = userpassword;
        this.entrydate = entrydate;
        this.userrole = userrole;
    }

    public Usertable() {
        this.usercode = 0;
        this.enabled = -1;
        this.username = "";
        this.userdescription = "";
        this.userid = "";
        this.userpassword = "";
        this.entrydate = "";
        this.userrole = "";
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserdescription() {
        return userdescription;
    }

    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
    
    
    
    
   
    
}
