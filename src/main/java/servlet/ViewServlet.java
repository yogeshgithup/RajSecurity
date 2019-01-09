
package servlet;

import data.Address;
import data.BankDetails;
import data.Experience;
import java.io.IOException;
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

public class ViewServlet extends HttpServlet {
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
        if(c!=null)
            {
            try {
                    Operation op = new Operation(c);
                    String type=request.getParameter("type");
                    HttpSession hs = request.getSession(true);
                    if(type.equals("exp"))
                    {
                        String id=request.getParameter("id");
                        System.out.println("---"+id);
                        JsonArray ja = op.getExpJson(id);
                        JSONObject jo = new JSONObject();
                        System.out.println("---"+ja.toString());
                        jo.put("data", ja);
                        out.println(jo);
                    }
                    if(type.equals("bank"))
                    {
                        String id=request.getParameter("id");
                        JsonArray ja = op.getBankJson(id);
                        JSONObject jo = new JSONObject();
                        jo.put("data", ja);
                        out.println(jo);
                    }
                    if(type.equals("add"))
                    {
                        String id=request.getParameter("id");
                        JsonArray ja = op.getResJson(id);
                        JSONObject jo = new JSONObject();
                        jo.put("data", ja);
                        out.println(jo);
                        
                    }
                    if(type.equals("per"))
                    {
                        String id=request.getParameter("id");
                        JsonArray ja = op.getPerJson(id);
                        JSONObject jo = new JSONObject();
                        jo.put("data", ja);
                        out.println(jo);
                    }
            } finally {
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
