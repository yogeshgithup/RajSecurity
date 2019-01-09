
package data;


public class SalaryOperation {
    String security,site,wage;
    int value;

    public SalaryOperation(String security, String site, String wage, int value) {
        this.security = security;
        this.site = site;
        this.wage = wage;
        this.value = value;
    }

    public SalaryOperation() {
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
