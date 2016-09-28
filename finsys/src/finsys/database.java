package finsys;

import java.sql.*;

public class database {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    database() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finsys", "finsys", "finsys");
            pst = conn.prepareStatement("select * from finsys.mt_userlogin where userid=? and userpassword=?");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean checkLogin(String uname, String pwd) {
        try {
            pst.setString(1, uname);
            pst.setString(2, pwd);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error while validating" + e);
            return false;
        }
    }

    public int insertemp(Issue_item i) {
        int flag = 0;
        try {
            pst = conn.prepareStatement("INSERT INTO finsys.m_uom(uom_name,uom_abbr) values(?,?)");

            pst.setString(1, i.getItemcode());
            pst.setString(2, i.getItemname());
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating" + e);
            return flag;
        }
    }

}
