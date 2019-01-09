
package data;
public class BillAllDetails {
    String srno,particulars,qty,rate,total,bid,indexby,rs,ps;

    public BillAllDetails(String srno, String particulars, String qty, String rate, String total, String bid, String indexby, String ps) {
        this.srno = srno;
        this.particulars = particulars;
        this.qty = qty;
        this.rate = rate;
        this.total = total;
        this.bid = bid;
        this.indexby = indexby;
        this.ps=ps;
    }
    public BillAllDetails() {
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getPs() {
        return ps;
    }

    public String getRs() {
        return rs;
    }
    
    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getIndexby() {
        return indexby;
    }

    public void setIndexby(String indexby) {
        this.indexby = indexby;
    }
    
}
