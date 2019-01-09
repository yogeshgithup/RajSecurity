package data;

public class Experience 
{
    String id,years,name,add;
    public Experience()
    {
        
    }
    public Experience(String id,String years,String name,String add)
    {
       this.id=id;
       this.add=add;
       this.name=name;
       this.years=years;
    }

    public String getAdd() {
        return add;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYears() {
        return years;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYears(String years) {
        this.years = years;
    }
    
}
