
package data;

public class Assign {
String spid,siteid,date,wage;
double dwage;

    public Assign() {
    }

    public Assign(String spid, String siteid, String date, String wage,double dwage) {
        this.spid = spid;
        this.siteid = siteid;
        this.date = date;
        this.wage = wage;
        this.dwage = dwage;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDwage(double dwage) {
        this.dwage = dwage;
    }

    public double getDwage() {
        return dwage;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

}
