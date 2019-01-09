package servlet;

import data.ClientQuotation;
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


public class AddQuotation extends HttpServlet {
Connection c;
ServletContext sc;
public void init(ServletConfig config) throws ServletException
{
    super.init(config);
    sc = this.getServletContext();
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
                int count = Integer.parseInt(request.getParameter("hi"));
                String client = request.getParameter("client");
                out.println(client);
                String date = request.getParameter("datepicker");
                String qtype=request.getParameter("qtype");
                String days = request.getParameter("days");
                String status = request.getParameter("status");
                int day = Integer.parseInt(days);
                
                String qid = op.getQuotationId();
                for (int i=1;i<=count;i++)
                {
                    
                    ClientQuotation cq = new ClientQuotation();
                    cq.setQid(qid);
                    cq.setClid(client);
                    String wage=request.getParameter("wage"+i);
                    
                    if(!wage.equals(""))
                    {
                    double w = Double.parseDouble(wage);
                    System.out.println(w+"");
                    System.out.println(day+"");
                    double wa = w/day;
                    System.out.println(wa+"");
                    String wages = String.format("%.2f", wa);
                    wa = Double.parseDouble(wages);
                    if(!wage.equals(""))
                    {
                    cq.setRate(wa);
                    cq.setNo(request.getParameter("no"+i));
                    cq.setSpost(request.getParameter("post"+i));
                    cq.setStype(request.getParameter("type"+i));
                    cq.setMrate(w);
                    cq.setQdate(date);
                    cq.setReq(status);
                    op.insertQuotation(cq);
                    }
                    }
                }
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/NewClientQuotation.jsp?msg=Requirement Saved Succesfully&div=isa_success&fa=fa fa-check");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            response.sendRedirect(request.getContextPath()+"/UserPannelDesign/NewClientQuotation.jsp?msg=There Was Error in saving Last Requirement&div=isa_error&fa=fa fa-times-circle");
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
