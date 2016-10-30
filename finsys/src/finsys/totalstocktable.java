
package finsys;

public class totalstocktable {
    private final int quantity;
    private final int amount;
    private final String itemcode;
    private final String itemname;
    private final String categorycode;
    private final String categoryname;
    private final String uomname;
    private final String uomabbr;
    private final String itemtypename;
    public totalstocktable(int qty, int amt, String itemCode, String itemName, String categoryCode, String categoryName, String uomName, String uomAbbr, String itemType){
        this.quantity=qty;
        this.amount=amt;
        this.itemcode=itemCode;
        this.itemname=itemName;
        this.categorycode=categoryCode;
        this.categoryname=categoryName;
        this.uomname=uomName;
        this.uomabbr=uomAbbr;
        this.itemtypename=itemType;
    }
    public int getQty(){
        return quantity;
    }
    public int getAmt(){
        return amount;
    }
    public String getItemCode(){
        return itemcode;
    }
    public String getItemName(){
        return itemname;
    }
    public String getCategoryCode(){
        return categorycode;
    }
    public String getCategoryName(){
        return categoryname;
    }
    public String getUomName(){
        return uomname;
    }
    public String getUomAbbr(){
        return uomabbr;
    }
    public String getItemTypeName(){
        return itemtypename;
    }
}
