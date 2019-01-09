<%-- 
    Document   : AddSite
    Created on : Mar 27, 2018, 9:14:48 AM
    Author     : Yogesh Chawla
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    
    <head>
        <%@include file="headerfiles.jsp" %>
          <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
        <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
                    <script>
                        $(document).ready(function()
                        {
                            $('#success').delay(3000).fadeOut();
                        });
                        </script>
        <style>
      .isa_info, .isa_success, .isa_warning, .isa_error {
margin: 10px 0px;
padding:12px;
 
}
.isa_info {
    color: #00529B;
    background-color: #BDE5F8;
}
.isa_success {
    color: #4F8A10;
    background-color: #DFF2BF;
}
.isa_warning {
    color: #9F6000;
    background-color: #FEEFB3;
}
.isa_error {
    color: #D8000C;
    background-color: #FFD2D2;
}
.isa_info i, .isa_success i, .isa_warning i, .isa_error i {
    margin:10px 22px;
    font-size:2em;
    vertical-align:middle;
}
  </style>
    </head>
    <body>
        <!-- Left Panel -->

        <!-- /#left-panel -->
        <%@include file="leftpanel.jsp" %>
        <!-- Left Panel -->

        <!-- Right Panel -->

        <div id="right-panel" class="right-panel">

            <!-- Header-->
            <%@include file="header.jsp" %>
            <% 
if(msg!=null)
{
            %>
            <div id="success" class="<%=d%>"><i class="<%=fa%>"></i>
     <%=msg%> </div>
     <%
}
  %>    
            <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-8">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Site Address</strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <form id="form" action="<%= application.getContextPath() %>/AddSiteServlet" method="post"  class="form-horizontal">
                                        <div class="card-title">
                                                
                                            </div>
                                        <hr>

                                            

                                                <div class="row form-group">
                                                    <div class="col col-md-2"><label for="date" class=" form-control-label">Client Site</label></div>
                                                    <div class="col-12 col-md-3"><select id="clientpicker" name="clientpicker">
                                                            <option value="Not Selected">Select Client</option>
                                                            <%
                                                                try{
                                                                Connection c = (Connection)application.getAttribute("con");
                                                                Statement s = c.createStatement();
                                                                ResultSet rs = s.executeQuery("select * from client");
                                                                while(rs.next())
                                                                {
                                                                    String id = rs.getString("client_id");
                                                                    String name = rs.getString("organisation_name");
                                                                    %>
                                                                    <option value="<%=id%>"><%=name%> </option>
                                                                    <%
                                                                }
                                                                s.close();
rs.close();
                                                                }
                                                                catch(Exception e)
                                                                {
                                                                    System.out.println("Error Client Details "+e.getMessage());
                                                                }
                                                            %>
                                                            </select></div>
                                                </div>
                                                            <div class="row form-group">
                                                              <div class="col col-md-2"><label for="date" class=" form-control-label">Site Address</label></div> 
                                                              <div class="col-12 col-md-9"><input type="text" id="add" name="add" placeholder="Site Address" class="form-control" required></div>
                                                            </div>
                                                            <div class="row form-group">
                                                              <div class="col col-md-2"><label for="date" class=" form-control-label">Site Area</label></div> 
                                                              <div class="col-12 col-md-9"><input type="text" id="add" name="area" placeholder="Site Area" class="form-control" required></div>
                                                            </div>
                                                            <div class="row form-group">
                                                              <div class="col col-md-2"><label for="date" class=" form-control-label">Site Person Contact No</label></div> 
                                                              <div class="col-12 col-md-9"><input type="text" id="cnt" name="cnt" placeholder="Contact No" class="form-control" required></div>
                                                            </div>
                                                            <div>
                                                    <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                                        <span id="payment-button-amount">SUBMIT</span>
                                                    </button>
                                                </div>
                                                            
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                         <%@include file="footerfiles.jsp" %> 
    </body>
</html>