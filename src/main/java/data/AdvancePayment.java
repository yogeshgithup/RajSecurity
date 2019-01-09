
package data;

public class AdvancePayment {
    String date,id,amt;
    public AdvancePayment()
    {
        
    }
    public AdvancePayment(String date,String id,String amt)
    {
        this.date=date;
        this.id=id;
        this.amt=amt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
    
}
