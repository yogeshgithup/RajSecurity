/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.InputStream;

/**
 *
 * @author Yogesh Chawla
 */
public class SecurityPerson extends User {
    String fathername,DOB,nationality,qualification,s_type,status,other,pstation,nstation,iname,site,post;
    InputStream image;
    public SecurityPerson()
    {
        
    }
    public SecurityPerson(String fathername,String DOB,String nationality,String qualification,String s_type,String status,String other,String pstation,String nstation,String iname)
    {
        this.fathername = fathername;
        this.DOB = DOB;
        this.nationality = nationality;
        this.qualification = qualification;
        this.s_type = s_type;
        this.status = status;
        this.other = other;
        this.pstation = pstation;
        this.nstation = nstation;
        this.iname = iname;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    

    public String getDOB() {
        return DOB;
    }

    


    

    public String getFathername() {
        return fathername;
    }

    public InputStream getImage() {
        return image;
    }

    public String getIname() {
        return iname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNationality() {
        return nationality;
    }

    public String getNstation() {
        return nstation;
    }

    public String getOther() {
        return other;
    }

    public String getStatus() {
        return status;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getPstation() {
        return pstation;
    }

    public String getQualification() {
        return qualification;
    }

    public String getS_type() {
        return s_type;
    }

    

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setNstation(String nstation) {
        this.nstation = nstation;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setPstation(String pstation) {
        this.pstation = pstation;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
public void setIname(String iname)
{
    this.iname=iname;
}
    
}
