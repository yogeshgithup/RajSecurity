package servlet;

import data.Address;
import data.BankDetails;
import data.Experience;
import data.SecurityPerson;
import data.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import operation.Operation;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

public class ViewDetailsServlet extends HttpServlet {
    ServletContext sc;
    Connection c;
    @Override
    
    public void init(ServletConfig config)
              throws ServletException
    {
        super.init(config);
        sc=this.getServletContext();
            
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        c = (Connection)sc.getAttribute("con");
        Operation op = new Operation(c);
        if(c!=null)
        {
            try 
            {
                File f = new File(sc.getRealPath("")+"download");
                if(!f.exists())
                {
                    boolean x = f.mkdir();
                    System.out.println("X Created At JSP " + x);
                }
                ArrayList<SecurityPerson> sa = op.viewAllSecurityPerson("select * from security_person");
                for(int i=0;i<sa.size();i++)
                {
                    SecurityPerson sp1 = sa.get(i);
                InputStream image = sp1.getImage();
                String iname = sp1.getIname();
                    byte[] data = new byte[image.available()];
                    image.read(data);
                    FileOutputStream fos = new FileOutputStream(sc.getRealPath("")+"/download/"+iname);
                    fos.write(data);
                }
                String key = request.getParameter("type");
                String v = request.getParameter("v");
                String userquery = null;
                String securityquery = null;
                if(key.equals("s_type"))
                {
                    userquery="select * from users where userid in (select sp_id from security_person where s_type='"+v+"')";
                    securityquery="select * from security_person where 1=1";
                }
                if(key.equals("firstname"))
                {
                    userquery="select * from users where firstname='"+v+"' and userid in (select sp_id from security_person)";
                    securityquery="select * from security_person where 1=1";
                }
                if(key.equals("sp_id"))
                {
                    userquery="select * from users where userid in (select sp_id from security_person where sp_id='"+v+"')";
                    securityquery="select * from security_person where 1=1";
                }
                if(key.equals("all"))
                {
                    userquery="select * from users where userid in (select sp_id from security_person)";
                    securityquery="select * from security_person where 1=1";
                }
                JsonArray ja = op.viewAll(userquery, securityquery);
                JSONObject jo = new JSONObject();
                jo.put("data",ja);
                out.println(jo);
//                ArrayList<User> user = op.viewAllUsers(userquery);
//                ArrayList<SecurityPerson> sp = op.viewAllSecurityPerson(securityquery);
//                HttpSession hs = request.getSession(true);
//                hs.setAttribute("user", user);
//                hs.setAttribute("security", sp);
//                hs.setAttribute("bank", bank);
//                hs.setAttribute("experience", exp);
//                hs.setAttribute("address", ad);
//                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonView.jsp");
            } 
            finally 
            {
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
