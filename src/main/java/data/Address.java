package data;
public class Address{
String id,add_type,add1,add2,area,district,pincode,state;
    public Address()
    {

    }
    public Address(String id,String add_type,String add1,String add2,String area, String district,String pincode,String state)
    {
        this.id=id;
        this.add_type=add_type;
        this.add1=add1;
        this.add2=add2;
        this.area=area;
        this.district=district;
        this.pincode=pincode;
        this.state=state;
    }

    public String getAdd1() {
        return add1;
    }

    public String getAdd2() {
        return add2;
    }

    public String getAdd_type() {
        return add_type;
    }

    public String getArea() {
        return area;
    }

    public String getDistrict() {
        return district;
    }

    public String getId() {
        return id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getState() {
        return state;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public void setAdd_type(String add_type) {
        this.add_type = add_type;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
