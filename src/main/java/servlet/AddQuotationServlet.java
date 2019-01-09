
package servlet;

import data.ClientQuotation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.Operation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddQuotationServlet extends HttpServlet {
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
                    String type1=request.getParameter("type");
                    String re = request.getParameter("re");
                    JSONParser parser = new JSONParser();
                JSONArray object = (JSONArray) parser.parse(re);
                out.println(re);
                out.println("\nobject size:" +object.size() );
                if(type1.equals("client"))
                {
                    for(int i=0;i<object.size();i++)
                    {
                       ClientQuotation cq = new ClientQuotation();
                       JSONObject obj = (JSONObject) object.get(i);
                       Set set= obj.keySet();
                       Iterator it=set.iterator();
                       String k=(String)it.next();
                       String type=(String)obj.get("type");
                       String qid = (String)obj.get("qid");
                       String wage = (String)obj.get("wage");
                       cq.setRate(Double.parseDouble(wage));
                       cq.setQid(qid);
                       cq.setStype(type);
                       out.println(type + qid + wage);
                       out.println(op.updateQuotation(cq));
                    }
                    
                }
                if(type1.equals("guest"))
                {
                    for(int i=0;i<object.size();i++)
                    {
                       ClientQuotation cq = new ClientQuotation();
                       JSONObject obj = (JSONObject) object.get(i);
                       Set set= obj.keySet();
                       Iterator it=set.iterator();
                       String k=(String)it.next();
                       String type=(String)obj.get("type");
                       String qid = (String)obj.get("qid");
                       String wage = (String)obj.get("wage");
                       cq.setRate(Double.parseDouble(wage));
                       cq.setQid(qid);
                       cq.setStype(type);
                       out.println(type + qid + wage);
                       out.println(op.updateGuestQuotation(cq));
                    }
                    
                }
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/GuestQuotationAdd.jsp?msg=Requirement Saved Succesfully&div=isa_success&fa=fa fa-check");
                }
        } catch (Exception ex) 
        {
            System.out.println("Error At Quotation Servlet");
            ex.printStackTrace();
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
