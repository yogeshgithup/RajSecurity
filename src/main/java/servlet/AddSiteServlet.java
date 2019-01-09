
package servlet;

import data.SiteAddress;
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

public class AddSiteServlet extends HttpServlet {
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
            String client = request.getParameter("clientpicker");
            String site = op.getSiteId();
            String add = request.getParameter("add");
            String area = request.getParameter("area");
            String cnt = request.getParameter("cnt");
            SiteAddress sa = new SiteAddress();
            sa.setAddress(add);
            sa.setArea(area);
            sa.setCid(client);
            sa.setCno(cnt);
            sa.setSid(site);
            String ad = op.addSiteAddress(sa);
            if(ad.equals("success"))
            {
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AddSite.jsp?msg=Client Site Added Succesfully&div=isa_success&fa=fa fa-check");
            }
            else
            {
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AddSite.jsp?msg=Error While Adding Site Address&div=isa_error&fa=fa fa-times-circle");
            }
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception " + e.getMessage());
        }
        finally {
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
