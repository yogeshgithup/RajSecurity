package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SerDelBill extends HttpServlet {
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
                c = (Connection)sc.getAttribute("con");
                String id = request.getParameter("id");
                long date = Date.parse(request.getParameter("date"));
                String cid = request.getParameter("cid");
                System.out.println("Date5:"+date);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("Date4:"+formatter.format(date));
                Date dd = new Date(date);
                
                String no = request.getParameter("no");
                String amt =  request.getParameter("amt");
                System.out.println("Del Amt"+Double.parseDouble(amt));
               
                PreparedStatement pstmt = c.prepareStatement("delete from client_quotation where client_id=? and amount=? and month(date)=? and year(date)=?");
                pstmt.setString(1, cid);
                pstmt.setDouble(2,Double.parseDouble(amt));
                pstmt.setInt(3, dd.getMonth()+1);
                pstmt.setInt(4, dd.getYear()+1900);
                int nn = dd.getMonth()+1;
                //System.out.println("Date:"+dd);
                int client_quotation = pstmt.executeUpdate();
                System.out.println("delete from client_quotation where client_id="+cid+" and amount="+Double.parseDouble(amt)+" and month(date)="+nn+" and year(date)="+(dd.getYear()+1900));
                ResultSet ra=pstmt.executeQuery("select count(*) from client_quotation where client_id='"+cid+"' and month(date)="+nn+" and year(date)="+(dd.getYear()+1900)+" and status=1");
                int count=1;
                if(ra.next())
                {
                    count=ra.getInt(1);
                }
                System.out.println("select count(*) from client_quotation where client_id='"+cid+"' and month(date)="+nn+" and year(date)="+(dd.getYear()+1900)+" and status=1");
                System.out.println("Count : "+count);
                System.out.println("No : "+no);
                if(count==0)
                {
                    PreparedStatement pst = c.prepareStatement("delete from bill_details where bill_id=?");
                    System.out.println("delete from bill_details where bill_id="+id);
                    pst.setString(1, id);
                    int bill_details= pst.executeUpdate();
                    System.out.println("Bill_Details="+bill_details+" Client_quotation="+client_quotation);
                }
                   System.out.println(" Client_quotation="+client_quotation);
               response.sendRedirect(request.getContextPath()+"/UserPannelDesign/EditBill.jsp");
               }
        catch(Exception e)
        {
            System.out.println("Error in Deleting : "+e.getLocalizedMessage());
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
