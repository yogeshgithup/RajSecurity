
package data;


public class Quotation {
String client_id,cname,orgname,city,refno,qdate,scharge;

    public Quotation(String client_id, String cname, String orgname, String city, String refno, String qdate, String scharge) {
        this.client_id = client_id;
        this.cname = cname;
        this.orgname = orgname;
        this.city = city;
        this.refno = refno;
        this.qdate = qdate;
        this.scharge = scharge;
    }

    public Quotation() {
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getQdate() {
        return qdate;
    }

    public void setQdate(String qdate) {
        this.qdate = qdate;
    }

    public String getScharge() {
        return scharge;
    }

    public void setScharge(String scharge) {
        this.scharge = scharge;
    }

}
