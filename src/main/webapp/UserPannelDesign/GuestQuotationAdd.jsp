<%-- 
    Document   : ClientQuotationAdd
    Created on : Mar 30, 2018, 2:47:53 PM
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
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        $(document).ready(function(){
           n=$('#clientpicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetGuestServlet',
                  data:{'client':n},
                  success:function(res) 
                  {
                       $('#clientpicker').empty();
                           $('#clientpicker').append('<option value="Not Selected"> Select Guest</option>');
                           var obj = JSON.parse(res);
                           $('#hidden').css("visibility","visible");
                           
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
                if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        } 
                        $('#bill').css('visibility','visible');
              $('#example1').dataTable({
         "autoWidth": true,
        "deferRender": true,
         "info": false,
         "searching":false,
         "sort":false,
         "language": {
      "emptyTable": "No Pending Quotation Request From This Guest"
    },
         "paging": false,
                               "ajax": '<%=application.getContextPath()%>/viewGuestQuotation?id='+$('#clientpicker').val(),
                               
                              "columns": [

                                   { "data": "post" },

                                   { "data": "type" },

                                   { "data": "no" },
                                   {"data":"guest"}
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {
                                
                                
                                 
                                                              
                                $('td:eq(0)', nRow).html('<form>'+aData['type']); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(aData['post']);
                                $('td:eq(2)', nRow).html(aData['no']);
                                $('td:eq(3)', nRow).html('<input type="text" id="'+aData['type']+'" for="'+aData['qtid']+'" name="wage">');
                                
                                return nRow;
                                 
                            }
                    });
                    $('#submit').on('click',function(e){
                       var val = [];
                       obj=$('input[type=text]');
                       var json = "[";
                       $.each(obj,function (index,value){
                       var w =  $(this).val();
                       val[index]=value.value;
                       c = $('#clientpicker').val();
                       var info = val[index];
                       var qid = $(this).attr('for');
                       var type = $(this).attr('id');
                       json = json + '{"qid":"'+qid+'","type":"'+type+'","wage":"'+value.value+'"},';
                       });
                       json=json.substr(0,json.length-1);
                       json = json + "]";
                       if(json!=="]")
                       location.href='<%=application.getContextPath()%>/AddQuotationServlet?re='+json+'&type=guest';
                       else
                           alert("No Quotation Request Selected");
                    });
           });
        });
    </script>
    <style>
        #bill
        {
            visibility: hidden;
        }
    </style>
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
            
            <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Guest Quotation</strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                            <div class="row form-group">
                                                 <div class="col col-md-3"><label for="date" class=" form-control-label">To</label></div>
                                                 <div class="col-12 col-md-9"><select id="clientpicker">
                                                                    
                                                            </select></div>
                                            </div>
                                            <div class="row form-group" id="bill">
                                                <center> <h6 style="color: purple"><strong>Requirement Details</strong></h6>
                                                 <br>
                                                 <table id="example1" class="row-border	" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>TYPE</th>
                <th>POST</th>
                <th>No Of Person</th>
                <th>Wage</th>
            </tr>
        </thead>
        <tfoot>
        <th colspan="4"><input type="button" id="submit" value="Send Quotation"></th>
        </tfoot>
                                            <tbody>
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
