<%-- 
    Document   : AssignClient
    Created on : Mar 20, 2018, 1:33:51 PM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%@include file="headerfiles.jsp" %>
         <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
         <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.min.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
<!--   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.min.css">
   
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.css">-->
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/alert.sass">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
    $(document).ready(function(){
       n=$('#clientpicker').val();
        $.ajax({
                  
                  url:'<%=application.getContextPath()%>/SetClientServlet',
                  data:{'client':n},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
//                         $('#hidden').css("visibility","visible");
                           $('#clientpicker').empty();
                           $('#clientpicker').append('<option value="Not Selected"> Select Client</option>');
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1 = obj[i];
                               
                               $('#clientpicker').append('<option value="'+obj1.cid+'">'+obj1.cname+'</option>');
                           }
                       
                  },
                  error:function(xhr,res)
                  {
                      alert(xhr.status);
                  }
                });
                $('#clientpicker').on('change',function(){
                      g=$('#clientpicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetSiteServlet',
                  data:{'client':g},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
//                         $('#hidden').css("visibility","visible");
                           $('#sitepicker').empty();
                           $('#sitepicker').append('<option value="Not Selected"> Select Client Site</option>');
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1 = obj[i];
                               
                               $('#sitepicker').append('<option value="'+obj1.cid+'">'+obj1.cname+'</option>');
                           }
                       
                  },
                  error:function(xhr,res)
                  {
                      alert(xhr.status);
                  }
                });
                });
                $('#sitepicker').on('change',function(){
                   g=$('#sitepicker').val();
                   $.ajax({
                      url:'<%=application.getContextPath()%>/getAssignJson?id='+g,
                      success:function(res)
                      {
                          $('#bo').empty();
                          var obj=JSON.parse(res);
                          for(i=0;i<obj.length;i++)
                          {
                              var obj1=obj[i];
                              $('#bo').append('<tr>');
                              $('#bo').append('<td>'+obj1.name+'</td>');
                              $('#bo').append('<td>'+obj1.mobile+'</td>');
                              $('#bo').append('<td>'+obj1.type+'</td>');
                              $('#bo').append('<td>'+obj1.post+'</td>');
                              $('#bo').append('<td>'+obj1.wage+'</td>');
                              $('#bo').append('</tr>');
                          }
                      },
                      error:function(xhr,res)
                      {
                          alert(xhr.status);
                      }
                   });
                });
    });
    </script>
    
     <body>
        <%@include file="leftpanel.jsp" %>
                <div id="right-panel" class="right-panel">

        
            <%@include file="header.jsp" %>
        
            <div class="content mt-3">
                <div class="animated fadeIn">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Assignment</strong>
                                </div>
                                <div class="card-body" >
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                            <div class="row form-group">
                                                    <div class="col col-md-2"><label for="client" class=" form-control-label">Client</label></div>
                                                    <div class="col-12 col-md-3"><select id="clientpicker">
                                                          
                                                            </select></div>
                                                </div>
                                            <div id="hidden" class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Client Site</label></div>
                                                    <div class="col-12 col-md-3"><select id="sitepicker">
                                                            <option value="Not Selected">Select Client Site</option>
                                                            </select></div>
                                            </div>
                                         
                                            <br>
                                            <br>
                                           
                                                <br>
                                            <div  class="row form-group" id="hidden1">
                                            <center> <h6 style="color: purple"><strong>View Security Person Assignment</strong></h6>
                                            <br>
                                            
                                                <table  id="example" class="table" cellspacing="0" >
        <thead>
            <tr>
                
                <th>Name</th>
                <th>Mobile No</th>
                <th>Type</th>
                <th>Post</th>
                <th>Per Day Wage</th>
       
            </tr>
        </thead>
       
                                            <tbody id="bo">
                                            </tbody>
                                            </table>
                                            </center>
                                            </div>
                                        </div>
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
   
