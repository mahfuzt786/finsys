package finsys;

import java.sql.*;
import java.util.ArrayList;

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
    
    
        public int insertCostcenter(Costcentertable i) {
        int flag = 0;
        String sql;
        int centerid;
        System.out.println("Values: " + i.getCentercode() + " " + i.getCentername());
        try {
            sql = "SELECT MAX(centerid) as max FROM finsys.m_costcenter";
            centerid = getmax(sql);
            System.out.println("centercode: " + centerid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_costcenter(centerid,centercode,centername) values(?,?,?)");

            pst.setInt(1, centerid);
            pst.setString(2,i.getCentercode() );
            pst.setString(3,i.getCentername());
            System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }
        
         public int insertCategory(Categorytable i) {
        int flag = 0;
        String sql;
        int catid;
        System.out.println("Values: " + i.getCategoryname());
        try {
            sql = "SELECT MAX(categoryid) as max FROM finsys.m_itemcategory";
            catid = getmax(sql);
            String categorycode="CAT"+catid;
            System.out.println("centercode: " + catid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_itemcategory(categoryid,categorycode,categoryname) values(?,?,?)");

            pst.setInt(1, catid);
            pst.setString(2,categorycode );
            pst.setString(3,i.getCategoryname());
           // System.out.println("centercode1: " + centerid);
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
    
    
     
        public int insertSubCategory(Subitemcategorytable i) {
        int flag = 0;
        String sql;
        int catid;
        System.out.println("Values: " + i.getCategoryid());
        try {
            sql = "SELECT MAX(subcategoryid) as max FROM finsys.m_itemsubcategory";
            catid = getmax(sql);
            String categorycode="SUBCAT"+catid;
            System.out.println("sub catcode: " + catid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_itemsubcategory(categoryid,subcategoryid,subcategorycode,subcategoryname) values(?,?,?,?)");
            pst.setInt(1,i.getCategoryid());
            pst.setInt(2, catid);
            pst.setString(3,categorycode );
            pst.setString(4,i.getSubcategoryname());
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }
      
        
       public int insertSoemaingroup(Soemaingrouptable i) {
        int flag = 0;
        String sql;
       int id;
        System.out.println("Values: " + i.getSoemaingroupcode());
        try {
            sql = "SELECT MAX(soemaingroupid) as max FROM finsys.m_soemaingroup";
            id = getmax(sql);
           
            pst = conn.prepareStatement("INSERT INTO finsys.m_soemaingroup(soemaingroupid,soemaingroupcode,soemaingroupname) values(?,?,?)");

            pst.setInt(1, id);
            pst.setString(2,i.getSoemaingroupcode() );
            pst.setString(3,i.getSoemaingroupname());
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }
       
     public int insertItem(Itemtable i) {
        int flag = 0;
        String sql;
        String itemcode="";
        int itemid=0;
        int uomcd=Integer.valueOf(i.getUomcode());
        System.out.println("Values: " + i.getItemname());
        try {
            sql = "SELECT MAX(itemid) as max FROM finsys.m_item";
            itemid = getmax(sql);
            itemcode="ITEM"+itemid;
            pst = conn.prepareStatement("INSERT INTO finsys.m_item(categoryid,subcategoryid,ledgerid,uomcode,itemid,itemcode,itemname,itemcost) values(?,?,?,?,?,?,?,?)");

            
            pst.setInt(1,i.getCategoryid() );
            pst.setInt(2,i.getSubcategoryid() );
            pst.setInt(3,i.getLedgerid() );
            pst.setInt(4,uomcd);
            pst.setInt(5,itemid );
            pst.setString(6,itemcode);
            pst.setString(7,i.getItemname());
            pst.setDouble(8,i.getItemcost());
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
     
       public int insertSoe(Soegrouptable i) {
        int flag = 0;
        String sql;
        int catid;
        System.out.println("Values: " + i.getSoegroupname());
        try {
            sql = "SELECT MAX(soegroupid) as max FROM finsys.m_soegroup";
            catid = getmax(sql);
            String categorycode="SOE"+catid;
            System.out.println("sub catcode: " + catid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_soegroup(soemaingroupid,soegroupid,soegroupcode,soegroupname) values(?,?,?,?)");
            pst.setInt(1,i.getSoemaingroupid());
            pst.setInt(2, catid);
            pst.setString(3,categorycode );
            pst.setString(4,i.getSoegroupname());
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }
       
        public int insertLedger(Ledgertable i) {
        int flag = 0;
        String sql;
        String lcode="";
        int lid=0;
        
        System.out.println("Values: " + i.getLedgername());
        try {
            sql = "SELECT MAX(ledgerid) as max FROM finsys.m_ledger";
            lid = getmax(sql);
            lcode="LEDGER"+lid;
            pst = conn.prepareStatement("INSERT INTO finsys.m_ledger(soemaingroupid,soegroupid,ledgerid,ledgercode,ledgername) values(?,?,?,?,?)");

            
            pst.setInt(1,i.getSoemaingroupid() );
            pst.setInt(2,i.getSoegroupid());
            pst.setInt(3,lid );
            pst.setString(4,lcode);
            pst.setString(5,i.getLedgername() );
           
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
     
       
       
     public ArrayList<Categorytable> getCategory() {
        ArrayList<Categorytable> catTable = new ArrayList<Categorytable>();
        String query = "select * from finsys.m_itemcategory";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Categorytable centerTab;
            while (rs.next()) {
                centerTab = new Categorytable(rs.getInt("categoryid"), rs.getString("categorycode"), rs.getString("categoryname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
     
      public ArrayList<Subitemcategorytable> getSubcategory(int catid) {
        ArrayList<Subitemcategorytable> catTable = new ArrayList<Subitemcategorytable>();
        String query = "select * from finsys.m_itemsubcategory where categoryid='"+catid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Subitemcategorytable centerTab;
            while (rs.next()) {
                centerTab = new Subitemcategorytable(rs.getInt("categoryid"),rs.getInt("subcategoryid"), rs.getString("subcategorycode"), rs.getString("subcategoryname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
      
      
      
      public ArrayList<uomtable> getUom() {
        ArrayList<uomtable> catTable = new ArrayList<uomtable>();
        String query = "select * from finsys.t_uom";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            uomtable uomTab;
            while (rs.next()) {
                uomTab = new uomtable(rs.getInt("uomcode"), rs.getString("uomname"), rs.getString("uomabbr"));
                catTable.add(uomTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
      
       public ArrayList<Ledgertable> getLedger() {
        ArrayList<Ledgertable> lTable = new ArrayList<Ledgertable>();
        String query = "select * from finsys.m_ledger";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Ledgertable lTab;
            while (rs.next()) {
                lTab = new Ledgertable(rs.getInt("soemaingroupid"),rs.getInt("soegroupid"),rs.getInt("ledgerid"), rs.getString("ledgercode"), rs.getString("ledgername"));
                lTable.add(lTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lTable;
    }
     
       
        public ArrayList<Subitemcategorytable> getSubcategory1() {
        ArrayList<Subitemcategorytable> catTable = new ArrayList<Subitemcategorytable>();
        String query = "select * from finsys.m_itemsubcategory ";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Subitemcategorytable centerTab;
            while (rs.next()) {
                centerTab = new Subitemcategorytable(rs.getInt("categoryid"),rs.getInt("subcategoryid"), rs.getString("subcategorycode"), rs.getString("subcategoryname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }

        
        public ArrayList<Soemaingrouptable> getSoemain() {
        ArrayList<Soemaingrouptable> catTable = new ArrayList<Soemaingrouptable>();
        String query = "select * from finsys.m_soemaingroup";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Soemaingrouptable centerTab;
            while (rs.next()) {
                centerTab = new Soemaingrouptable(rs.getInt("soemaingroupid"), rs.getString("soemaingroupcode"), rs.getString("soemaingroupname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
        
        public ArrayList<Soegrouptable> getSoe() {
        ArrayList<Soegrouptable> catTable = new ArrayList<Soegrouptable>();
        String query = "select * from finsys.m_soegroup";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Soegrouptable centerTab;
            while (rs.next()) {
                centerTab = new Soegrouptable(rs.getInt("soemaingroupid"),rs.getInt("soegroupid"), rs.getString("soegroupcode"), rs.getString("soegroupname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
        
        //get soe group based on soemaingroupid
        
         public ArrayList<Soegrouptable> getSoe1(int soemaingroupid) {
        ArrayList<Soegrouptable> catTable = new ArrayList<Soegrouptable>();
        String query = "select * from finsys.m_soegroup where soemaingroupid='"+soemaingroupid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Soegrouptable centerTab;
            while (rs.next()) {
                centerTab = new Soegrouptable(rs.getInt("soemaingroupid"),rs.getInt("soegroupid"), rs.getString("soegroupcode"), rs.getString("soegroupname"));
                catTable.add(centerTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTable;
    }
}
