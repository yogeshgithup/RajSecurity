<%-- 
    Document   : ClientBill
    Created on : Mar 28, 2018, 11:18:06 AM
    Author     : Yogesh Chawla
--%>

<%-- 
    Document   : Bill
    Created on : Mar 28, 2018, 11:08:39 AM
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
  <script>
      var no = [];
      var getDaysInMonth = function(month,year) {
  return new Date(year, month, 0).getDate();
};
var day;
var type = [];
var post = [];
      $(document).ready(function(){
          var count=1;
          $('#gstyn').hide();
          $('#stsyn').hide();
          $( "#datepicker" ).datepicker({
        changeMonth: true,
            changeYear: false
             
        });
         $('#yearpicker').on('change',function(){
            $('#month').css('visibility','visible'); 
         });
         $('#monthpicker').on('change',function()
         {
            n=$('#clientpicker').val();
        if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }
                        $('#quotation').css('visibility','hidden');
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
                $('#client').css('visibility','visible');
         });
         $('#clientpicker').on('change',function(){
             $('#date').css('visibility','visible');
         });
         $('#datepicker').on('change',function(){
             if($('#clientpicker').val()==='Not Selected')
             {
                 $('#quotation').css('visibility','hidden');
                 alert('Please Select Client');
             }
             $('#quotation').css('visibility','visible');
             $('#gstyn').show();
             $('#stsyn').show();
        if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }   
                        var i=0;
        var n = $('#clientpicker').val();
      
        $('#example1').dataTable({
         "autoWidth": true,
        "deferRender": true,
         "info": false,
         "searching":false,
         "sort":false,
         "paging": false,
                               "ajax": '<%=application.getContextPath()%>/ViewQuotationJson?id='+n+"&month="+$('#monthpicker').val()+"&year="+$('#yearpicker').val(),
                               
                              "columns": [

                                   { "data": "post" },

                                   { "data": "type" },

                                   { "data": "no" },
                                   {"data":"client"}
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {
                                
                                
                                 
        
                                $('td:eq(0)', nRow).html(aData['type']); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(aData['post']);
                                $('td:eq(2)', nRow).html(aData['no']);
                                $('td:eq(3)', nRow).html('<input type="text" value="'+aData['amt']+'" id="wage'+count+'" readonly>');
                                no[i] = aData['no'];
                                 day=aData['day'];
                                 type[i]=aData['type'];
                                 post[i]=aData['post'];
                                count++;
                                i++;
                                return nRow;
                                 
                            }
                    });  
         });
         $('#generate').on('click',function(){
            var b=0;
           obj=$('input[type=text]');
           var j=0,c=0;
           $('#app').empty();
           $.each(obj,function (index,value){
             var w =  $(this).val();
             c = c+(w*day);
             var dd = parseFloat(w*day).toString();
             var pdd = dd.indexOf(".");
             dd = dd.substr(0,pdd+3);
             $('#app').append("<tr>");
             $('#app').append("<td>"+type[j]+"</td>");
             $('#app').append("<td>"+post[j]+"</td>");
             $('#app').append("<td>"+day+"</td>");
             $('#app').append("<td>"+w+"</td>");
             $('#app').append("<td>"+dd+"</td>");
             $('#app').append("</tr>");
             j++;
           });
           $('#bill').css("visibility","visible");
           //$('#det').css("visibility","visible");
           var cgst = parseFloat(c*(9/100)).toString();
           var pcgst = cgst.indexOf(".");
           cgst = cgst.substr(0,pcgst+3);
          
           var billamt = parseFloat(c+(2*(parseFloat(c*(9/100))))).toString();
           var pbillamt = billamt.indexOf(".");
           billamt = billamt.substr(0,pbillamt+3);
         
           $('#billamt').html("&nbsp <strong> "+billamt+" </strong>&nbsp No Of Days <strong>"+day+"</strong>");
           $('#sgst').html("&nbsp <strong> "+cgst+" </strong>");
           $('#cgst').html("&nbsp <strong> "+cgst+" </strong>");
           var b = parseFloat(c).toString();
           var pb = b.indexOf(".");
           c = b.substr(0,pb+3);
           $('#total').html(c);
                        
           
         });
         $('#schk').on('change',function(e)
         {
             if($('#schk').is(':checked'))
             {
                 $('#sval').removeAttr('disabled');
             }
             else
             {
                 $('#sval').attr('disabled','disabled');
             }
         });
         $('#gstchk').on('change',function(e){
            
         });
         $(document).on('click','#make',function(){
             var sts = $('#sval').val();
             if($('#gstchk').is(':checked'))
            {
                if($('#schk').is(':checked'))
                {
                    window.open("<%=application.getContextPath()%>/BillServlet1?id="+$('#clientpicker').val()+"&mon="+day+"&year="+$('#yearpicker').val()+"&m="+$('#monthpicker').val()+"&date="+$('#datepicker').val()+"&gstt=yes&schk=yes&sval="+sts);
                }
                else
                {
                    window.open("<%=application.getContextPath()%>/BillServlet1?id="+$('#clientpicker').val()+"&mon="+day+"&year="+$('#yearpicker').val()+"&m="+$('#monthpicker').val()+"&date="+$('#datepicker').val()+"&gstt=yes&schk=no&sval=0");
                }
            
            }
            else
            {
                if($('#schk').is(':checked'))
                {
                    window.open("<%=application.getContextPath()%>/BillServlet1?id="+$('#clientpicker').val()+"&mon="+day+"&year="+$('#yearpicker').val()+"&m="+$('#monthpicker').val()+"&date="+$('#datepicker').val()+"&gstt=no&schk=yes&sval="+sts);
                }
                else
                {
                    window.open("<%=application.getContextPath()%>/BillServlet1?id="+$('#clientpicker').val()+"&mon="+day+"&year="+$('#yearpicker').val()+"&m="+$('#monthpicker').val()+"&date="+$('#datepicker').val()+"&gstt=no&schk=no&sval=0");
                }
            }
             //window.open("<%=application.getContextPath()%>/BillServlet1?id="+$('#clientpicker').val()+"&mon="+day+"&year="+$('#yearpicker').val()+"&m="+$('#monthpicker').val()+"&date="+$('#datepicker').val());
             location.reload();
         });
      });
     
      </script>
      <style>
          #month
          {
              visibility: hidden;
          }
          #date
          {
              visibility: hidden;
          }
          #bill
          {
              visibility: hidden;
          }
          #client
          {
              visibility: hidden;
          }
          #quotation
          {
              visibility: hidden;
          }
          #det
          {
              visibility: hidden;
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
     <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Bill</strong>
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
                                                <div class="col col-md-2"><label for="date" class="form-control-label">Select Year</label></div>
                                                <div class="col-12 col-md-3">
                                                    <select name="yearpicker" id="yearpicker" class="form form-control-label">
                                                        <option value="select">Select</option>
                                                        <option value="2018">2018 </option>
                                                        <option value="2019">2019 </option>
                                                        <option value="2020">2020 </option>
                                                        <option value="2021">2021 </option>
                                                        <option value="2022">2022 </option>
                                                    </select>
                                                </div>
                                                            
                                            </div>
                                            <div class="row form-group" id="month">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Select Month</label></div>
                                                <div class="col-12 col-md-3">
                                                    <select name="monthpicker" id="monthpicker" class="form form-control-label">
                                                        <option value="select">Select</option>
                                                        <option value="1">January</option>
                                                        <option value="2">February</option>
                                                        <option value="3">March</option>
                                                        <option value="4">April</option>
                                                        <option value="5">May</option>
                                                        <option value="6">June</option>
                                                        <option value="7">July</option>
                                                        <option value="8">August</option>
                                                        <option value="9">September</option>
                                                        <option value="10">October</option>
                                                        <option value="11">November</option>
                                                        <option value="12">December</option>
                                                    </select>
                                                </div>
                                                            
                                            </div>
                                            <div class="row form-group" id="client">
                                                <div class="col col-md-2"><label for="client" class=" form-control-label">Select Client</label></div>
                                                    <div class="col-12 col-md-3"><select id="clientpicker">
                                                          
                                                            </select></div>
                                                            
                                            </div>
                                            <div class="row form-group" id="date">
                                                <div class="col col-md-2"><label for="client" class=" form-control-label">Select Date</label></div>
                                                    <div class="col-12 col-md-3"><input type="text" id="datepicker"></div>
                                                            
                                            </div>
                                            <div class="row form-group" id="gstyn">
                                                <div class="col col-md-2"><label for="client" class=" form-control-label">Include Gst : </label></div>
                                                <div class="col-12 col-md-3"><input type="checkbox" name="gstchk" id="gstchk" checked=""></div>
                                                            
                                            </div>
                                            <div class="row form-group" id="stsyn">
                                                <div class="col col-md-2"><label for="client" class=" form-control-label">Include SCharge : </label></div>
                                                <div class="col-12 col-md-1"><input type="checkbox" name="schk" id="schk"></div>
                                                <div class="col-12 col-md-3"><input type="text" value="0" name="sval" id="sval" disabled=""></div>
                                                
                                            </div>
                                            <div class="row form-group" id="quotation">
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
        <th colspan="3"><input type="button" id="generate" value="Generate"></th>
        <th><input type="button" id="make" value="Download Bill"></th>
        </tfoot>
                                            <tbody>
                                            </tbody>
                                            </table>
                                                 </center>
                                            </div>
                                            <div class="row form-group" id="bill">
                                                The Bill Amount Is <label id="billamt"></label>
                                            </div>
                                            
                                            <div class="row form-group" id="det">
                                                 <center> <h6 style="color: purple"><strong>Bill Details</strong></h6>
                                                 <br>
                                                 <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <th>Type Of Security Guards</th>
                                                    <th>Post Of Guards</th>
                                                    <th>Days</th>
                                                    <th>Per Day Wage</th>
                                                    <th>Amt</th>
                                                    </thead>
                                                    <tbody id="app">
                                                        
                                                    </tbody>
                                                    <tfoot>
                                                    <th colspan="4"></th>
                                                    <th id="total"></th>
                                                    </tfoot>
                                                </table>
                                                     <br>
                                                <br>
                                                <br>
                                                9% State Gst <label id="sgst"></label>
                                                <br>
                                                9% Central Gst <label id="cgst"></label>
                                                <br>
                                                     </div>
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
