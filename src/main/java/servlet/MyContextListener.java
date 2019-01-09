package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {
    String driver,url,usr,pass;
    ServletContext ctx;
    @Override
    public void contextInitialized(ServletContextEvent sce) 
    {
        ctx=sce.getServletContext();
        driver=ctx.getInitParameter("Driver");
        url=ctx.getInitParameter("url");
        usr=ctx.getInitParameter("user");
        pass=ctx.getInitParameter("pass");
        try
        {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url,usr,pass);
            System.out.println("Driver Connected");
            ctx.setAttribute("con", c);
        }
        catch(Exception e)
        {
            System.out.println("Context "+e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ctx=sce.getServletContext();
        Connection c = (Connection)ctx.getAttribute("con");
        try 
        {
            if(c!=null)
            c.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
}

