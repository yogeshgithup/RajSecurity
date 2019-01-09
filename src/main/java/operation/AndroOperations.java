
package operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class AndroOperations {
Connection c;
public AndroOperations(Connection c)
{
    this.c=c;
}
public JSONObject getClientDetails(String cid)
{
    JSONObject jo = new JSONObject();
    Statement st,st1,st2;
    ResultSet rs,rs1,rs2;
    try
    {
        st=c.createStatement();
        st1=c.createStatement();
        st2=c.createStatement();
        rs=st.executeQuery("select * from client where client_id='"+cid+"'");
        while(rs.next())
        {
           jo.put("name", rs.getString("organisation_name"));
           jo.put("url", rs.getString("url"));
           rs1=st1.executeQuery("select * from client_site_address where client_id='"+cid+"'");
           JSONArray ja = new JSONArray();
           rs2=st2.executeQuery("select * from users where userid = '"+cid+"'");
           while(rs2.next())
           {
               jo.put("pname", rs2.getString("firstname")+" "+rs2.getString("lastname"));
           }
           while(rs1.next())
           {
               JSONObject jj = new JSONObject();
               jj.put("area", rs1.getString("area"));
               jj.put("add", rs1.getString("address"));
               jj.put("sid", rs1.getString("site_id"));
               jj.put("cno", rs1.getString("contact_no"));
               ja.add(jj);
           }
           jo.put("site", ja);
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
    return jo;
}
public JSONObject getSiteDetails(String sid)
{
    JSONObject jo = new JSONObject();
    ResultSet rs;
    Statement st;
    try
    {
        st=c.createStatement();
        rs=st.executeQuery("select * from client_site_address where site_id='"+sid+"'");
        while(rs.next())
        {
            jo.put("address", rs.getString("address"));
            jo.put("area", rs.getString("area"));
            jo.put("contact", rs.getString("contact_no"));
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
    return jo;
}
public JSONArray getSecurityPerson(String path)
{
    JSONArray ja = new JSONArray();
    ResultSet rs,rs1;
    Statement st,st1;
    try
    {
        st=c.createStatement();
        st1=c.createStatement();
        rs=st.executeQuery("select * from users where userid in (select sp_id from security_person)");
        while(rs.next())
        {
            JSONObject jo = new JSONObject();
            jo.put("userid", rs.getString("userid"));
            String id = rs.getString("userid");
            jo.put("name", rs.getString("firstname")+" "+rs.getString("lastname"));
            jo.put("email", rs.getString("email"));
            jo.put("number", rs.getString("contact_number"));
            rs1=st1.executeQuery("select * from security_person where sp_id='"+id+"'");
            while(rs1.next())
            {
                String iname = rs1.getString("imagename");
                jo.put("image", path+iname);
            }
            ja.add(jo);
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
    return ja;
}
}
