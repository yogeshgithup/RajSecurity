
package data;

public class AttendanceMain {
String aid,date,id,value,client,site;
    public AttendanceMain()
    {

    }
    public AttendanceMain(String aid,String date,String id,String value)
    {
        this.aid=aid;
        this.date=date;
        this.id=id;
        this.value=value;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getClient() {
        return client;
    }

    public String getAid() {
        return aid;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
     
}
