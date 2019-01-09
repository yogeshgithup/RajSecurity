
package data;


public class SiteAddress {
    String sid,cid,address,cno,area;
    public SiteAddress()
    {
        
    }
    public SiteAddress(String sid,String cid,String address,String cno,String area)
    {
        this.sid = sid;
        this.cid = cid;
        this.address = address;
        this.cno = cno;
        this.area = area;
    }

    public String getCid() {
        return cid;
    }

    public String getAddress() {
        return address;
    }

    public String getCno() {
        return cno;
    }

    public String getSid() {
        return sid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    
}
