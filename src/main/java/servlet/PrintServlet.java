package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.BillOperation;


public class PrintServlet extends HttpServlet {
Connection c;
ServletContext sc;
public void init(ServletConfig config) throws ServletException
{
    super.init(config);
    sc=this.getServletContext();
}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                BillOperation bo = new BillOperation(c);
                String billid = request.getParameter("bill");
                String id = request.getParameter("id");
                String dd = request.getParameter("date");
                String gstt = request.getParameter("gstt");
                System.out.println("Date : "+dd);
                Date d = new Date(dd);
                String year = Integer.toString(d.getYear());
                String m = Integer.toString(d.getMonth()+1);
                year = year.substring(1,year.length());
                year = "20"+year;
                int mon = bo.getFinal(id, m, year);
                System.out.println("/BillServlet?id="+id+"&mon="+mon+"&m="+m+"&year="+year+"&date="+dd+"&billno="+billid);
                response.sendRedirect(sc.getContextPath()+"/BillServlet?id="+id+"&mon="+mon+"&m="+m+"&year="+year+"&date="+dd+"&billno="+billid+"&gstt="+gstt);
            }
        }
        catch(Exception e)
        {
            System.out.println("PrintBillServlet Error : "+e.getLocalizedMessage());
            e.printStackTrace();
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
