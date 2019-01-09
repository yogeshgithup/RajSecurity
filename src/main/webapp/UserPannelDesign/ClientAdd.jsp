<%-- 
    Document   : ClientAdd
    Created on : Mar 13, 2018, 12:44:07 PM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        
        <%@include file="headerfiles.jsp" %>
        <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
  <script>
     var count=0;
     function createTF()
{
	var ctrl;
        count = count+1;
	ctrl='<div class="row form-group"><div class="col col-md-3"><label for="address" class=" form-control-label"> Site Address</label></div><div class="\n\
col-12 col-md-9"><input type="text" id="address" name="address'+count+'" placeholder="Client Site Address" class="form-control" required></div>\n\
\n\</div><div class="row form-group"><div class="col col-md-3"><label for="address" class=" form-control-label"> Site Area</label></div><div class="\n\
col-12 col-md-9"><input type="text" id="area" name="area'+count+'" placeholder="Client Site Area" class="form-control" required></div></div>\n\
</div><div class="row form-group"> <div class="col col-md-3"><label for="cntno" class=" form-control-label">Contact No</label></div><div class="col-12 col-md-9"><input type="number" id="cntno" name="cntno'+count+'" placeholder="Site Person Contact No" class="form-control" required></div></div>';
	return ctrl;
}       
$(document).ready(function()
{
    $('#success').delay(3000).fadeOut();
    var values = [];
        $("input[id='address']").each(function() {
        values.push($(this).val());
        });
   $("#add").click(function()
        {
          ctrl=createTF();
          $("#mydiv").append(ctrl);
        }); 
       $("#form").submit(function(e)
       {
         $("#count").val(count);  
       });
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
     <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
    <body>
        <!-- Left Panel -->

        <!-- /#left-panel -->
        <%@include file="leftpanel.jsp" %>
        <!-- Left Panel -->

        <!-- Right Panel -->

        <div id="right-panel" class="right-panel">

            <!-- Header-->
            <%@include file="header.jsp" %>
            <!-- Header-->
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
                                    <strong class="card-title">Client </strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <form id="form" action="<%= application.getContextPath() %>/ClientAddServlet" method="post"  class="form-horizontal">
                                        <div class="card-title">
                                                <h3 class="text-center">Client Details</h3>
                                            </div>
                                        <hr>

                                            

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="fname" class=" form-control-label">First Name</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="fname" name="fname" placeholder="Firstname" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="mname" class=" form-control-label">Middle Name</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="mname" name="mname" placeholder="Middlename" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="lname" class=" form-control-label">Last Name</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="lname" name="lname" placeholder="Lastname" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="email" class=" form-control-label">E-Mail Id</label></div>
                                                    <div class="col-12 col-md-9"><input type="email" id="email" name="email" placeholder="E-Mail Id" class="form-control"   ></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="no" class=" form-control-label">Contact No</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="no" name="no" placeholder="Contact No" class="form-control" required></div>
                                                </div>
                                        <div class="card-title">
                                                <h3 class="text-center">Organisation Details</h3>
                                            </div>
                                        <hr>
                                            <div class="row form-group">
                                                    <div class="col col-md-3"><label for="orgname" class=" form-control-label">Name Of Organisation</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="orgname" name="orgname" placeholder="Organisation Name" class="form-control" required></div>
                                                </div>
                                        <div class="row form-group">
                                                    <div class="col col-md-3"><label for="orgurl" class=" form-control-label">Organisation Url</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="orgurl" name="orgurl" placeholder="http://www.example.com" class="form-control"></div>
                                                </div>
                                        <div class="row form-group">
                                                    <div class="col col-md-3"><label for="orgurl" class=" form-control-label">GST No</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="gst" name="gst" placeholder="GST No" class="form-control"></div>
                                                </div>
                                        <div class="card-title">
                                                <h3 class="text-center">Site Details</h3>
                                            </div>
                                        <hr>
                                       <div id="mydiv" >
                                           <div class="row form-group">
                                                    <div class="col col-md-3"><input type="button" id="add" value="Add Site Address" class="btn btn-lg btn-info"></div>
                                                    <div class="col col-md-9"></div>
                                                    <input type="hidden" value="" name="count" id="count">
                                                    <br/>
                                                </div>
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

