
package servlet;

import data.ClientQuotation;
import data.QuotationDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.BillOperation;
import operation.Operation;
import operation.QuotationOperation;
public class SaveBillSer extends HttpServlet {
ServletContext sc;
Connection c;
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
                    ClientQuotation cq = new ClientQuotation();
                    String amt = request.getParameter("amt");
                    double oamt = Double.parseDouble(request.getParameter("oamt"));
                    int days = Integer.parseInt(request.getParameter("day"));
                    String billno = request.getParameter("bill");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String date = request.getParameter("date");
                    String ddate = date;
                    System.out.println("Date : "+date);

                    String dddd = ddate;
                    System.out.println("Date :: "+dddd);
                    Date d = new Date(dddd);
                    double m = d.getDate();
                    String id = request.getParameter("id");
                    double damt = Double.parseDouble(amt);
                    double mrate = damt/m;
                    System.out.println("Day : "+m);
                    String smrate = String.format("%.2f", mrate);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String newdate = formatter.format(d);
                    amt = String.format("%.2f", damt);
                    if(days!=0)
                    {
                    cq.setClid(id);
                    cq.setQid(op.getQuotationId());
                    cq.setRate(damt);
                    cq.setMrate(mrate);
                    cq.setStype("EX-SERVICEMEN");
                    cq.setSpost("Security Guard");
                    cq.setNo(days+"");
                    cq.setQdate(newdate);
                    cq.setReq(status+"");
                    op.insertQuotation(cq);
                    }
                    System.out.println("Client_Id"+id);
                     String soamt = String.format("%.2f", oamt);
                    PreparedStatement ps = c.prepareStatement("update client_quotation set amount=?,wagem=? where client_id=? and amount=?");
                    System.out.println("Amt"+amt);
                    System.out.println("OAmt"+soamt);
                   
                    ps.setString(1, amt);
                    ps.setString(2, damt*days+"");
                    ps.setString(3, id);
                    ps.setDouble(4, Double.parseDouble(soamt));
                    int mm = d.getMonth()+1;
                    BillOperation bo = new BillOperation(c);
                    String year = Integer.toString(d.getYear());
                    year=year.substring(1, year.length());
                    year = "20"+year;
                    System.out.println("Rows : "+ps.executeUpdate());
                    int dd = bo.getFinal1(id, mm+"", year,amt);
                    out.println(dd);
                    System.out.println(dd);
                }
        }
        catch(Exception w)
        {
            System.out.println("SaveBillSer Error : "+w.getLocalizedMessage());
            w.printStackTrace();
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
