/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Yogesh Chawla
 */
public class Client extends User {
    String orgname,url,gst;
    public Client()
    {
        
    }
    public Client(String orgname,String url)
    {
        this.orgname = orgname;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
    
}
