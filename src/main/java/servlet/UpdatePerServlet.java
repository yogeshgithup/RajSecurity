
package servlet;

import data.Address;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.Operation;


public class UpdatePerServlet extends HttpServlet {
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
            Operation op = new Operation(c);
            try {
                           String id = request.getParameter("id");
                           String type=request.getParameter("type");
                           String line1=request.getParameter("line1");
                           String line2=request.getParameter("line2");
                           String area=request.getParameter("area");
                           String district=request.getParameter("district");
                           String pin = request.getParameter("pin");
                           String state = request.getParameter("state");
                           Address ad = new Address();
                           ad.setAdd1(line1);
                           ad.setAdd2(line2);
                           ad.setAdd_type(type);
                           System.out.println(type);
                           ad.setArea(area);
                           ad.setDistrict(district);
                           ad.setPincode(pin);
                           ad.setState(state);
                           ad.setId(id);
                           System.out.println(op.updateAdd(ad));
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
