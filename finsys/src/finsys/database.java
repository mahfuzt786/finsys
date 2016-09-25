package finsys;
import java.sql.*;
public class database {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    database(){
        try{
           Class.forName("org.postgresql.Driver");
           conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/finsys","rajiv","rajiv");
           pst=conn.prepareStatement("select * from finsys.user where username=? and password=?");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public Boolean checkLogin(String uname,String pwd){
      try{
          pst.setString(1, uname);
          pst.setString(2, pwd);
          rs=pst.executeQuery();
          if(rs.next()){
              return true;
          }else{
              return false;
          }
      }catch (Exception e){
          System.out.println("Error while validating" + e);
          return false;
      }  
    }
}
