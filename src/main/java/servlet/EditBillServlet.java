
package servlet;

import data.ClientQuotation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.BillOperation;
import org.json.simple.*;


public class EditBillServlet extends HttpServlet {
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
                    BillOperation bop = new BillOperation(c);
                    String clientid = null,billdate = null,total=null;
                    String billid = request.getParameter("billno");
                    Statement st1 = c.createStatement();
                    Statement st = c.createStatement();
                    ResultSet rs = st.executeQuery("select * from bill_details where bill_id="+billid);
                    ArrayList<ClientQuotation> cq;
                    if(rs.next())
                    {
                        clientid = rs.getString("client_id");
                        total = rs.getString("grand_total");
                        billdate = rs.getString("bill_date");
                    }
                    int in = billdate.indexOf('/');
                    int mo = Integer.parseInt(billdate.substring(0,in));
                    int iny = billdate.lastIndexOf('/');
                    int year = Integer.parseInt(billdate.substring(iny+1, billdate.length()));
                    
                    //out.println("Bill No = "+billid + "\nBillDate = "+billdate);
                    //out.println("Month = "+mo);
                    //out.println("Year = "+year);
                    String wagem = null;
                    //out.println("\nGrand Total =  "+total);
                    ResultSet rs1 = st1.executeQuery("select max(wagem) as wagem from client_quotation where client_id='"+clientid+"' and month(date)="+mo+" and year(date)="+year+" and status=1");
                    while(rs1.next())
                    {
                        wagem = rs1.getString("wagem");
                    }
                    //out.println("Wage = "+wagem);
                    int days = bop.getFinal(clientid, mo+"", year+"");
                    //out.print(" Days = "+days);
                    cq = bop.getCQ(clientid,mo+"",year+"");
                    JsonArray ja = new JsonArray();
                    for (ClientQuotation cq1 : cq) {
                        JSONObject jo = new JSONObject();
                        jo.put("billno", billid);
                        jo.put("date", billdate);
                        jo.put("wage", cq1.getRate());
                        jo.put("no", cq1.getNo());
                        jo.put("days", bop.getFinal1(clientid, mo+"", year+"",cq1.getRate()+""));
                         System.out.println( bop.getFinal1(clientid, mo+"", year+"",cq1.getRate()+""));
                        jo.put("qid", cq1.getQid());
                        jo.put("id", clientid);
                        ja.add(jo);
                    }
                   
                    out.print(ja);
                    System.out.println("ja\n"+ja);
                    rs.close();
                    rs1.close();
                    st1.close();
                    st.close();
                    
                }
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getLocalizedMessage());
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
