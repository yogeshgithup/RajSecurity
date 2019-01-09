
package operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

public class ClientOperation {
Connection c;
public ClientOperation(Connection c)
{
    this.c=c;
}
public JsonArray getAssignment(String id)
{
    JsonArray ja = new JsonArray();
    ResultSet rs,rs1;
    Statement st,st1;
    try{
        st=c.createStatement();
        st1=c.createStatement();
        rs=st.executeQuery("select * from client_site_assignment where site_id in (select site_id from client_site_address where client_id='"+id+"')");
        
        while(rs.next())
        {
            JSONObject jo = new JSONObject();
            String sid = rs.getString("sp_id");
            rs1=st1.executeQuery("select * from users where userid='"+sid+"'");
            while(rs1.next())
            {
                String name = rs1.getString("firstname")+" "+rs1.getString("middlename")+" "+rs1.getString("lastname");
                String mo = rs1.getString("contact_number");
                jo.put("name", name);
                jo.put("no", mo);   
            }
           ja.add(jo);
        }
    }   
    catch(Exception e)
    {
        System.out.println("error red");
        e.printStackTrace();
    }
    return ja;
}
public JsonArray getAttendanceDetails(String id)
{
    JsonArray ja = new JsonArray();
    Statement st,st1,st2;
    ResultSet rs,rs1,rs2;
    try
    {
        st2=c.createStatement();
        st=c.createStatement();
        st1=c.createStatement();
        System.out.println("select * from client_site_assignment where site_id in (select site_id from client_site_address where client_id='"+id+"')");
        rs=st.executeQuery("select * from client_site_assignment where site_id in (select site_id from client_site_address where client_id='"+id+"')");
        while(rs.next())
        {
            JSONObject jo = new JSONObject();
            String sid=rs.getString("sp_id");
            rs2=st2.executeQuery("select * from users where userid='"+sid+"'");
            while(rs2.next())
            {
                String name=rs2.getString("firstname")+" "+rs2.getString("lastname");
                jo.put("name", name);
            }
            Date d = new Date();
            int mo = d.getMonth();
            int ye = d.getYear();
            JSONObject j = new JSONObject();
            rs1=st1.executeQuery("select attendance_value as val,day(attendance_date) as day from attendance_main where month(attendance_date)=4 and year(attendance_date)=2018 and sp_id='"+sid+"'");
            while(rs1.next())
            {
                
                String day = rs1.getString("day");
                String val = Integer.toString(rs1.getInt("val"));
                System.out.println(sid+" "+day+" "+val);
                j.put(day, val);
                
            }
            jo.put("attendance", j);
            ja.add(jo);
        }
    }
    catch(Exception e)
    {
        System.out.println("Error green");
        e.printStackTrace();
    }
    System.out.println("the json "+ja);
    return ja;
}
}
