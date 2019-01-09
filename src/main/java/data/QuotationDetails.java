
package data;

public class QuotationDetails {
String qid,srno,cid,spost,hrs,ex,civilian;

    public QuotationDetails(String qid, String srno, String cid, String spost, String hrs, String ex, String civilian) {
        this.qid = qid;
        this.srno = srno;
        this.cid = cid;
        this.spost = spost;
        this.hrs = hrs;
        this.ex = ex;
        this.civilian = civilian;
    }

    public QuotationDetails() {
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSpost() {
        return spost;
    }

    public void setSpost(String spost) {
        this.spost = spost;
    }

    public String getHrs() {
        return hrs;
    }

    public void setHrs(String hrs) {
        this.hrs = hrs;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getCivilian() {
        return civilian;
    }

    public void setCivilian(String civilian) {
        this.civilian = civilian;
    }
    
}
