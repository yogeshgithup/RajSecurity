
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.BillOperation;
import operation.Operation;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

public class ViewQuotationJson extends HttpServlet {
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
        try {
            c = (Connection)sc.getAttribute("con");
            if(c!=null)
            {
                Operation op = new Operation(c);
                String id = request.getParameter("id");
                String y = request.getParameter("year");    
                String m = request.getParameter("month");
                BillOperation bo = new BillOperation(c);
                double amt = bo.getAmount(id, m, y);
                int days = bo.getFinal(id, m, y);
                System.out.println(amt +" "+days);
                JsonArray ja = op.getRequireJson(id,y,m);
                JSONObject jo = new JSONObject();
                jo.put("data", ja);
                out.println(jo);
            }
        } finally {
            out.close();
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
