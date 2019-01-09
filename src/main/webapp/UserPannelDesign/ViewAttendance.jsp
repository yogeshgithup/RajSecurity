<%-- 
    Document   : ViewAttendance
    Created on : Mar 19, 2018, 7:14:33 PM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%@include file="headerfiles.jsp" %>
         <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/bootstrap-confirmation.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/bootstrap-confirmation.min.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
             

    <script>
        var getMonth = function(month)
        {
            if(parseInt(month)===1)
                return "January";
            if(parseInt(month)===2)
                return "February";
            if(parseInt(month)===3)
                return "March";
            if(parseInt(month)===4)
                return "April";
            if(parseInt(month)===5)
                return "May";
            if(parseInt(month)===6)
                return "June";
            if(parseInt(month)===7)
                return "July";
            if(parseInt(month)===8)
                return "August";
            if(parseInt(month)===9)
                return "September";
            if(parseInt(month)===10)
                return "October";
            if(parseInt(month)===11)
                return "November";
            if(parseInt(month)===12)
                return "December";
        };
        var getDaysInMonth = function(month,year) {
  return new Date(year, month, 0).getDate();
};
        function getAttendance(day,obj)
{   atv=1;
    $.each(obj,function(k,v){
       if(parseInt(k)===day)
       {
           atv=v;
       }
    });
    return atv;
}
        $( function() {
            
    $( "#att" ).dialog({
      autoOpen: false,
       height: 400,
      width: 350,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });
    $( "#amountdesc" ).dialog({
      autoOpen: false,
       height: 400,
      width: 350,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "clip",
        duration: 1000
      }
    });
        });
        $(document).ready(function()
        {
            
            
            
            $('#monthpicker').on('change',function()
            {
                if($('#yearpicker').val()==="Not Selected")
                    alert("Please Select Year First");
                else{
        $('#hi').css("visibility","visible");
            if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();
                            
                            table.destroy();
                            
                        }    
            $('#example').dataTable({
                       
                               "ajax": '<%=application.getContextPath()%>/ViewSecurityJson?month='+$('#monthpicker').val()+'&year='+$('#yearpicker').val(),

                              "columns": [

                                   { "data": "id" },

                                   { "data": "fname" },
                                    
                                   {"data" : "email"},
                                   
                                   {"data" : "days"}
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                
                                var id = aData['id'];
                                var name = aData['fname']+" "+aData['mname']+" "+aData['lname'];
                                 var id = aData['id'];
                                                              
                                $('td:eq(0)', nRow).html(id); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(name);
                                $('td:eq(2)', nRow).html("<a href='<%=application.getContextPath()%>/GetDetails?type=days&id="+id+"&month="+$('#monthpicker').val()+"&year="+$('#yearpicker').val()+"' class='open' id='open'>"+aData['days']+"</a>");
                                $('td:eq(3)', nRow).html("<a href='<%=application.getContextPath()%>/GetDetails?type=salary&id="+id+"&month="+$('#monthpicker').val()+"&year="+$('#yearpicker').val()+"' class='open1' id='open1'>"+aData['sum']+"</a>");
                                
                                return nRow;

                            }
                    }); 
                }
            });
            $( document ).on( "click","#open1", function(e) {
                e.preventDefault();
                 var url = $(this).attr("href");
                 $.ajax({
                  url:url,
                  success:function(res) 
                  {
                      var obj = JSON.parse(res);
                      
                      for(i=0;i<obj.length;i++)
                      {
                          var obj1 = obj[i];
                          var name = obj1.name;
                          var app = obj1.app;
                          var sum = obj1.sum;
                          $('#nameamt').html(name);
                          $('#monthlblamt').html(getMonth($('#monthpicker').val()));
                          $('#sal').html(sum);
                          $('#adv').html(app);
                          $('#less').html(parseFloat(sum)-parseFloat(app));
                      }

                  },
                  error:function(res)
                  {
                  }
              });
              $( "#amountdesc" ).dialog( "open" );
          });
              $( document ).on( "click","#open", function(e) {
                e.preventDefault();
                 var url = $(this).attr("href");
                 $.ajax({
                  url:url,
                  success:function(res) 
                  {
                      var obj = JSON.parse(res);
                       $('#detbod').empty();
                      for(i=0;i<obj.length;i++)
                      {
                        var obj1 = obj[i];
                        $('#name').html("<td>"+obj1.name+"</td>");
                        
                        objatt=obj1.att;
                        var month = getMonth($('#monthpicker').val());
                        $('#monthlbl').html(month);
                        var day = getDaysInMonth($('#monthpicker').val(),$('#yearpicker').val());
                        $('#total').html(obj1.sum);
                        for(j=1;j<=day;j++)
                        {
                            $('#detbod').append("<tr>");
                            $('#detbod').append("<td>"+j+"</td>");
                            aatvalue=getAttendance(j,objatt);
                            if(aatvalue===1)
                            $('#detbod').append('<td>NA</td>');
                            if(parseInt(aatvalue)===8)
                            $('#detbod').append('<td>P</td>');
                            if(parseInt(aatvalue)===16)
                            $('#detbod').append('<td>2P</td>');
                            if(parseInt(aatvalue)===0)
                            {
                            $('#detbod').append('<td>AB</td>');
                            }
                            $('#detbod').append("</tr>");
                        }
                      }

                  },
                  error:function(res)
                  {
                  }
              });
      $( "#att" ).dialog( "open" );
    });
        });
        </script>
        <style>
            #hi
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
                                        <strong class="card-title">View Attendance </strong>
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
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Year</label></div>
                                                    <div class="col-12 col-md-3"><select id="yearpicker">
                                                            <option value="Not Selected">Select Year</option>
                                                            <option value="2018">2018</option>
                                                            <option value="2019">2019</option>
                                                            <option value="2020">2020</option>
                                                            <option value="2021">2021</option>
                                                            <option value="2022">2022</option>
                                                            </select></div>
                                            </div>
                                                <div class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Month</label></div>
                                                    <div class="col-12 col-md-3"><select id="monthpicker">
                                                            <option value="Not Selected">Select Month</option>
                                                            <option for="January" value="1">January</option>
                                                            <option for="February" value="2">February</option>
                                                            <option for="March" value="3">March</option>
                                                            <option for="April" value="4">April</option>
                                                            <option for="May" value="5">May</option>
                                                            <option for="June" value="6">June</option>
                                                            <option for="July" value="7">July</option>
                                                            <option for="August" value="8">August</option>
                                                            <option for="September" value="9">September</option>
                                                            <option for="October" value="10">October</option>
                                                            <option for="November" value="11">November</option>
                                                            <option for="December" value="12">December</option>
                                                            </select></div>
                                            </div>
                                            <div class="row form-group" id="hi">
                                            <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>ID</th>
                <th>Name</th>
                <th> No Of Days Present </th>
                <th>Salary(As Per Current Attendance)</th>
                
            </tr>
        </thead>
                                            <tbody>
                                            </tbody>
                                            </table>
        
                                        </div>
                                            <div id="att">
                                                <center>
                                                <label id="name">Name</label>
                                                <br>
                                                <label id="monthlbl">Month</label>
                                                </center>
                                                <table class="table" id="details">
                                                    <thead>
                                                    <th>Day</th>
                                                    <th>Present/Absent</th>
                                                    </thead>
                                                    <tbody id="detbod">
                                                       
                                                    </tbody>
                                                    <tfoot>
                                                    
                                                    <th>
                                                        Total
                                                    </th>
                                                    <th id="total"></th>
                                                    </tfoot>
                                                </table>
                                            </div>
                                            <div id="amountdesc">
                                                <center>
                                                <label id="nameamt">Name</label>
                                                <br>
                                                <label id="monthlblamt">Month</label>
                                                <table class="table" id="amt">
                                                    <thead>
                                                    <th colspan="2"></th>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Salary</td>
                                                            <td id="sal"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Advance Payment</td>
                                                            <td id="adv"></td>
                                                        </tr>
                                                    </tbody>
                                                    <tfoot>
                                                    <th>Total</th>
                                                    <th id="less"></th>
                                                    </tfoot>
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
                                            

