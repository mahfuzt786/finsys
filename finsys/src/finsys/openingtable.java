
package finsys;

public class openingtable {
    private int slno;
    private int id;
    private int month;
    private int year;
    private double quantity;
    private double value;
    
    public openingtable(int Slno,int ID, int Month, int Year, double Quantity, double Value){
        this.slno=Slno;
        this.id=ID;
        this.month=Month;
        this.year=Year;
        this.quantity=Quantity;
        this.value=Value;
    }

    public openingtable() {
        
        this.slno=0;
        this.id=0;
        this.month=0;
        this.year=0;
        this.quantity=0.0;
        this.value=0.0;
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
    public double getQuantity(){
        return quantity;
    }
    public double getValue(){
        return value;
    }

    /**
     * @param slno the slno to set
     */
    public void setSlno(int slno) {
        this.slno = slno;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    
}
