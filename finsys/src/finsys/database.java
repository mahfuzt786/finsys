package finsys;

import static java.lang.String.format;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class database {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
//DB CONNECTION
    database() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finsys", "finsys", "finsys");
            pst = conn.prepareStatement("select * from finsys.mt_userlogin where userid=? and userpassword=?");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//CHECKING LOGINN CREDENTIALS
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

    
    //INSERT FUNCTION FOR MASTER TABLE
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
    public int insertms(ms_item i) {
        int flag = 0;
        String sql;
        int msid;
        System.out.println("Values: " + i.getMsname() + " " + i.getMsaddress() +" " + i.getMsphone());
        try {
            sql = "SELECT MAX(companyid) as max FROM finsys.m_fromcompany";
            msid = getmax(sql);
            System.out.println("MS id: " + msid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_fromcompany(companyid,companyname,companyaddress,companyphone) values(?,?,?,?)");

            pst.setInt(1, msid);
            pst.setString(2, i.getMsname());
            pst.setString(3, i.getMsaddress());
            pst.setString(4, i.getMsphone());
            System.out.println("Ms id: " + msid);
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
            pst = conn.prepareStatement("INSERT INTO finsys.m_item(categoryid,itemtypeid,uomcode,itemid,itemcode,itemname) values(?,?,?,?,?,?)");

            
            pst.setInt(1,i.getCategoryid() );
           
            pst.setInt(2,i.getItemtypeid());
            pst.setInt(3,uomcd);
            pst.setInt(4,itemid );
            pst.setString(5,itemcode);
            pst.setString(6,i.getItemname());
            
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            if(flag==1){
                  pst = conn.prepareStatement("INSERT INTO finsys.t_stock(itemid,quantity,amount) values(?,?,?)");
                   pst.setInt(1,itemid );
           
                    pst.setDouble(2,0);
                    pst.setDouble(3,0);
                    flag=pst.executeUpdate();
            }
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
     
      public int insertStockIn(Stockintable i) {
        int flag = 0;
        String sql;
        int slno;
       System.out.println("Values: " );
        try {
            sql = "SELECT MAX(slno) as max FROM finsys.t_stockin";
            slno = getmax(sql);
            
            pst = conn.prepareStatement("INSERT INTO finsys.t_stockin(slno,invoiceno"
          +",transportation_amt,less_per,from_company_id,tax_invoice_no ,tax_invoice_date ,challan_no"
          + " ,challan_date,purchase_order_no,purchase_order_date,vat_per,invoiceid) values("
          +"?,?,"
          +"?,?,?,?,?,?,"
          +"?,?,?,?,?)");

            
            pst.setInt(1,slno );
           
            pst.setString(2,i.getInvoiceid());
            pst.setDouble(3,Double.valueOf(i.getTransportation_amt()));
            pst.setDouble(4,Double.valueOf(i.getLess_per()) );
            pst.setInt(5,Integer.valueOf(i.getFrom_company_id()));
            pst.setString(6,i.getTax_invoice_no());
            pst.setDate(7, UtilDate.convertStringToSqlDate("dd-MM-yyyy",i.getTax_invoice_date()));
            pst.setString(8,i.getChallan_no());
            pst.setDate(9, UtilDate.convertStringToSqlDate("dd-MM-yyyy",i.getChallan_date()));
            pst.setString(10,i.getPurchase_order_no());
            pst.setDate(11, UtilDate.convertStringToSqlDate("dd-MM-yyyy",i.getPurchase_order_date()));
            pst.setDouble(12,Double.valueOf(i.getVat_per()));
            pst.setString(13, i.getInvoiceid());
          
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
     
        
        public int insertItemtype(Itemtypetable i) {
        int flag = 0;
        String sql;
        int iid;
        System.out.println("Values: " + i.getItemtypename());
        try {
            sql = "SELECT MAX(itemtypeid) as max FROM finsys.m_itemtype";
            iid = getmax(sql);
           
            System.out.println("centercode: " + iid);
            pst = conn.prepareStatement("INSERT INTO finsys.m_itemtype(itemtypeid,itemtypename) values(?,?)");

            pst.setInt(1, iid);
            pst.setString(2,i.getItemtypename());
            
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating :" + e);
            return flag;
        }
    }
        
        public int insertStockinitem(Stockinitemtable i) {
        int flag = 0;
        String sql;
        Double totalstockamount=0.0,totalstockquantity=0.0;
        System.out.println("Values: " + i.getItemid());
        try {
           
            pst = conn.prepareStatement("INSERT INTO finsys.t_stockin_items(invoiceid,itemid,item_rate,quantity) values(?,?,?,?)");

            
            pst.setString(1,i.getInvoiceid() );
           
            pst.setInt(2,i.getItemid());
           
            pst.setDouble(3,Double.valueOf(i.getItem_rate()));
            pst.setInt(4,Integer.valueOf(i.getQuantity()));
            
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
             if(flag==1){
                  ArrayList<Stocktable> d=getStock(Integer.valueOf(i.getItemid()));
                 for(Stocktable c:d){
                 totalstockamount=Double.valueOf(c.getAmount());
                 totalstockquantity=Double.valueOf(c.getQuantity());
                 }
               String query = "update finsys.t_stock set quantity='" +( totalstockquantity+Double.valueOf(i.getQuantity()))+ "',amount='" +(totalstockamount+Double.valueOf(i.getItem_rate())*Double.valueOf(i.getQuantity()))+ "' where itemid='" +i.getItemid()+ "'";
                pst = conn.prepareStatement(query);
                flag = pst.executeUpdate();
            }
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
        
       public int insertStockout(Stockouttable i) {
        int flag = 0;
        String sql;
        int slno;
        String issue_return_code="";
       System.out.println("Values: " );
        try {
            sql = "SELECT MAX(slno) as max FROM finsys.t_issue_return";
            slno = getmax(sql);
            issue_return_code="BO"+i.getCostcenterid()+slno;
            pst = conn.prepareStatement("INSERT INTO finsys.t_issue_return(slno,issue_returncode"
          +",acc_post,receiptno,issue_or_return,costcenterid ,"
                    + "issueamt_value ,transportation_amt"
          + " ) values(?,?,"
          +"?,?,?,?,"
         
          +"?,?)");

            
            pst.setInt(1,slno );
           
            pst.setString(2,issue_return_code);
           
            pst.setString(3,i.getAcc_post());
            pst.setString(4, issue_return_code);
            pst.setString(5,i.getIssue_or_return());
            pst.setInt(6,i.getCostcenterid());
            pst.setDouble(7,i.getIssueamt_value());
            pst.setDouble(8, 0);
          
            flag = pst.executeUpdate();
           
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
    
       
         public int insertStockoutitem(Stockoutitemtable i) {
        int flag = 0;
        String sql;
        Double totalstockamount=0.0,totalstockquantity=0.0;
        System.out.println("Values: " + i.getItemid());
        try {
          
            pst = conn.prepareStatement("INSERT INTO finsys.t_issue_items(itemid,reqquantity,issuequantity,issue_returncode,itemvalue,ledgerid) values(?,?,?,?,?,?)");

            
            pst.setInt(1,i.getItemid());
           
            pst.setDouble(2,i.getReqquantity());
           
            pst.setDouble(3,Math.abs(i.getIssuequantity()));
            pst.setString(4,i.getIssue_returncode());
            pst.setDouble(5,i.getItemvalue());
            pst.setInt(6,i.getLedgerid());
            
           // System.out.println("centercode1: " + centerid);
            flag = pst.executeUpdate();
             if(flag==1){
                  ArrayList<Stocktable> d=getStock(i.getItemid());
                 for(Stocktable c:d){
                 totalstockamount=Double.valueOf(c.getAmount());
                 totalstockquantity=Double.valueOf(c.getQuantity());
                 }
                
                
               String query = "update finsys.t_stock set quantity='" +( totalstockquantity-i.getIssuequantity())+ "',amount='" +(totalstockamount-(i.getItemvalue()*i.getIssuequantity()))+ "' where itemid='" +i.getItemid()+ "'";
                pst = conn.prepareStatement(query);
                flag = pst.executeUpdate();
            }
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
         
         
   public int insertOpening(openingtable i) {
        int flag = 0;
        String sql;
        int slno;

       System.out.println("Values: " );
        try {
            sql = "SELECT MAX(slno) as max FROM finsys.t_openingstock";
            slno = getmax(sql);
            
            pst = conn.prepareStatement("INSERT INTO finsys.t_openingstock(slno,itemid"
          +",mnth,yr,ostockqty,ostockvalue"
          + ") values("
          +"?,?,"
      
          +"?,?,?,?)");
          pst.setInt(1,slno );
           
            pst.setInt(2,i.getId());
            pst.setInt(3,i.getMonth());
            pst.setInt(4,i.getYear());
            pst.setDouble(5,Double.valueOf(i.getQuantity()));
            pst.setDouble(6,Double.valueOf(i.getValue()) );
           
            flag = pst.executeUpdate();
           
            return flag;
        } catch (Exception e) {
            System.out.println("Error while validating in insert :" + e);
            return flag;
        }
    }
    
     
        //GET FUNCTION FOR MASTER TABLE
       
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
         
           public ArrayList<Itemtypetable> getItemtype() {
        ArrayList<Itemtypetable> iTable = new ArrayList<Itemtypetable>();
        String query = "select * from finsys.m_itemtype";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Itemtypetable iTab;
            while (rs.next()) {
                iTab = new Itemtypetable(rs.getInt("itemtypeid"),rs.getString("itemtypename"));
                iTable.add(iTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iTable;
    }
           
     public ArrayList<Itemtable> getItem() {
        ArrayList<Itemtable> iTable = new ArrayList<Itemtable>();
        String query = "select * from finsys.m_item";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Itemtable iTab;
            while (rs.next()) {
                iTab = new Itemtable(rs.getInt("categoryid"),rs.getInt("itemid"),rs.getInt("itemtypeid"),
                        rs.getString("itemcode"),rs.getString("itemname"),rs.getString("uomcode"));
                iTable.add(iTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iTable;
    }
     
     public ArrayList<Companytable> getCompany() {
        ArrayList<Companytable> cTable = new ArrayList<Companytable>();
        String query = "select * from finsys.m_fromcompany";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Companytable cTab;
            while (rs.next()) {
                cTab = new Companytable(rs.getInt("companyid"),rs.getString("companyname"));
                cTable.add(cTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
     public ArrayList<Stockintable> getStockIn(String inv) {
        ArrayList<Stockintable> cTable = new ArrayList<Stockintable>();
         String query = "select t.slno,t.invoiceno,t.total_amt_value,to_char(t.entrydate,'dd-MM-yyyy') AS entrydate ,t.transportation_amt,t.less_per,t.from_company_id "
                + ",t.tax_invoice_no ,to_char(t.tax_invoice_date,'dd-MM-yyyy')AS tax_invoice_date ,t.challan_no ,to_char(t.challan_date,'dd-MM-yyyy') AS challan_date,t.purchase_order_no,to_char(t.purchase_order_date,'dd-MM-yyyy') AS purchase_order_date,"
                + "t.vat_per,invoiceid,t.total_gross_amt ,m.companyname"
                + " from finsys.t_stockin t inner join m_fromcompany m on m.companyid=t.from_company_id "
                 + "where t.invoiceid='"+inv+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockintable sTab;
            while (rs.next()) {
                sTab = new Stockintable(rs.getString("slno"), rs.getString("invoiceno"),rs.getString("total_amt_value"), 
                        rs.getString("entrydate"),rs.getString("transportation_amt"), rs.getString("less_per"),
                        rs.getString("from_company_id"), rs.getString("tax_invoice_no"), rs.getString("tax_invoice_date"), rs.getString("challan_no"),
                        rs.getString("challan_date"), rs.getString("purchase_order_no"), rs.getString("purchase_order_date"), 
                        rs.getString("vat_per"), rs.getString("invoiceid"),rs.getString("total_gross_amt"), rs.getString("companyname"));
                
                cTable.add(sTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
     
      public ArrayList<Stockinitemtable> getStockinitemtable(String inv,String iid) {
        ArrayList<Stockinitemtable> sTable = new ArrayList<Stockinitemtable>();
        String query = "select t.invoiceid,t.itemid,t.item_rate,t.quantity ,m.itemcode,m.itemname,(t.item_rate*t.quantity) as grossvalue"
               
                + " from finsys.t_stockin_items t inner join m_item m on m.itemid=t.itemid where t.invoiceid='"+inv+"' and t.itemid='"+iid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockinitemtable siTab;
        
            while (rs.next()) {
                siTab = new Stockinitemtable(rs.getInt("itemid"), rs.getString("itemcode"),rs.getString("itemname"),rs.getString("invoiceid"), 
                        rs.getString("item_rate"),rs.getString("quantity"), rs.getString("grossvalue"));
                      
                sTable.add(siTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTable;
    }
      
      public ArrayList<Stocktable> getStock(int itemid) {
        ArrayList<Stocktable> cTable = new ArrayList<Stocktable>();
        String query = "select * from finsys.t_stock where itemid='"+itemid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stocktable cTab;
            while (rs.next()) {
                cTab = new Stocktable(rs.getInt("itemid"),rs.getString("amount"),rs.getString("quantity"));
                cTable.add(cTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
      
       public ArrayList<Costcentertable> getCC() {
        ArrayList<Costcentertable> cTable = new ArrayList<Costcentertable>();
        String query = "select * from finsys.m_costcenter ";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Costcentertable cTab;
            while (rs.next()) {
                cTab = new Costcentertable(rs.getInt("centerid"),rs.getString("centercode"),rs.getString("centername"));
                cTable.add(cTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
       
       public ArrayList<Stockouttable> getStockout(String issue_returncode) {
        ArrayList<Stockouttable> cTable = new ArrayList<Stockouttable>();
        String query = "select * from finsys.t_issue_return where issue_returncode='"+issue_returncode+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockouttable cTab;
            while (rs.next()) {
                cTab = new Stockouttable(rs.getString("issue_returncode"), rs.getString("acc_post"), rs.getString("issuedate"),
               rs.getString("receiptno"), rs.getString("issue_or_return"), rs.getInt("costcenterid"),rs.getDouble("issueamt_value"),rs.getDouble("transportation_amt") );
               cTable.add(cTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
       
    public ArrayList<Stockoutitemtable> getStockoutitem(String issue_returncode,int itemid) {
        ArrayList<Stockoutitemtable> cTable = new ArrayList<Stockoutitemtable>();
        String query = "select * from finsys.t_issue_items t inner join finsys.m_item m on m.itemid=m.itemid where issue_returncode='"+issue_returncode+"' and t.itemid='"+itemid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Stockoutitemtable cTab;
            while (rs.next()) {
                cTab =new Stockoutitemtable(rs.getString("issue_returncode"),rs.getString("itemcode"),rs.getString("itemcode"),rs.getInt("itemid"), rs.getInt("ledgerid"),
               rs.getDouble("reqquantity"), rs.getDouble("issuequantity"), rs.getDouble("itemvalue"),rs.getInt("categoryid") );
                cTable.add(cTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cTable;
    }
    
     public ArrayList<Itemtable> getItem1(int catid) {
        ArrayList<Itemtable> iTable = new ArrayList<Itemtable>();
        String query = "select * from finsys.m_item where categoryid='"+catid+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Itemtable iTab;
            while (rs.next()) {
                iTab = new Itemtable(rs.getInt("categoryid"),rs.getInt("itemid"),rs.getInt("itemtypeid"),
                        rs.getString("itemcode"),rs.getString("itemname"),rs.getString("uomcode"));
                iTable.add(iTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iTable;
    }
}
