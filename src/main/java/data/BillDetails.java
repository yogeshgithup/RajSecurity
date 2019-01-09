
package data;

public class BillDetails {
String bid,cname,bill_date,address,total,cgst,sgst,scharge,gtotal,word,paisa,gst,gstps,pscharge;

    public BillDetails(String bid, String cname, String bill_date, String address, String total, String cgst, String sgst, String scharge, String gtotal,String word,String paisa,String gst,String gstps) {
        this.bid = bid;
        this.cname = cname;
        this.bill_date = bill_date;
        this.address = address;
        this.total = total;
        this.cgst = cgst;
        this.sgst = sgst;
        this.scharge = scharge;
        this.gtotal = gtotal;
        this.word = word;
        this.paisa = paisa;
        this.gst = gst;
        this.gstps = gstps;
    }
    public BillDetails(String bid, String cname, String bill_date, String address, String total, String cgst, String sgst, String scharge,String pscharge, String gtotal,String word,String paisa,String gst,String gstps) {
        this.bid = bid;
        this.cname = cname;
        this.bill_date = bill_date;
        this.address = address;
        this.total = total;
        this.cgst = cgst;
        this.sgst = sgst;
        this.scharge = scharge;
        this.gtotal = gtotal;
        this.word = word;
        this.paisa = paisa;
        this.gst = gst;
        this.gstps = gstps;
        this.pscharge = pscharge;
    }

    public void setPscharge(String pscharge) {
        this.pscharge = pscharge;
    }

    public String getPscharge() {
        return pscharge;
    }
    
    public void setGstps(String gstps) {
        this.gstps = gstps;
    }

    public String getGstps() {
        return gstps;
    }

    public BillDetails() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCname() {
        return cname;
    }

    public String getPaisa() {
        return paisa;
    }

    public String getWord() {
        return word;
    }

    public String getGst() {
        return gst;
    }

    public void setPaisa(String paisa) {
        this.paisa = paisa;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getScharge() {
        return scharge;
    }

    public void setScharge(String scharge) {
        this.scharge = scharge;
    }

    public String getGtotal() {
        return gtotal;
    }

    public void setGtotal(String gtotal) {
        this.gtotal = gtotal;
    }
    
}
