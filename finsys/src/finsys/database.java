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
        String sql;
        int uomcode;
        System.out.println("Values: " + i.getItemcode() + " " + i.getItemname());
        try {
            sql = "SELECT MAX(uomcode) as max FROM finsys.t_uom";
            uomcode = getmax(sql);
            System.out.println("uomcode: " + uomcode);
            pst = conn.prepareStatement("INSERT INTO finsys.t_uom(uomcode,uomname,uomabbr) values(?,?,?)");

            pst.setInt(1, uomcode);
            pst.setString(2, i.getItemcode());
            pst.setString(3, i.getItemname());
            System.out.println("uomcode1: " + uomcode);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }

    public int getmax(String sql) {
        int maxcode = 0;
        try {
            pst = conn.prepareStatement(sql);

            System.out.println("in getmax" + sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                maxcode = rs.getInt("max");
                maxcode += 1;

            } else {
                maxcode = 1;
            }
            return maxcode;
        } catch (Exception e) {
            System.out.println("Error in max uom" + e);
            return maxcode;
        }

    }

}
