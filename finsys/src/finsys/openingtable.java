
package finsys;

public class openingtable {
    private final int slno;
    private final int id;
    private final int month;
    private final int year;
    private final int quantity;
    private final int value;
    
    public openingtable(int Slno,int ID, int Month, int Year, int Quantity, int Value){
        this.slno=Slno;
        this.id=ID;
        this.month=Month;
        this.year=Year;
        this.quantity=Quantity;
        this.value=Value;
    }
    public int getSlno(){
        return slno;
    }
    public int getId(){
        return id;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getValue(){
        return value;
    }
}
