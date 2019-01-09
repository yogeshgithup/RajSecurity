<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        
        <%@include file="headerfiles.jsp" %>
        <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        $(document).ready(function(){
            $('#month').hide();
            $('#btn').hide();
            $('#date').hide();
           $( "#datepicker" ).datepicker({
        changeMonth: true,
            changeYear: false
        });
        $('#yearpicker').on('change',function(){
        $('#month').show();    
        $('#date').hide();
        $('#btn').hide();
        });
        $('#monthpicker').on('change',function(){
           $('#date').show();
           $('#btn').hide();
        });
        $('#datepicker').on('change',function(){
           $('#btn').show(); 
        });
        });
    </script>
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
                                    <strong class="card-title">Download Bill</strong>
                                </div>
                                <div class="card-body" >
                                    <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                       
                                        <div class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Select Year</label></div>
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
                                        <div class="row form-group" id="date">
                                                <div class="col col-md-2"><label for="client" class=" form-control-label">Select Date</label></div>
                                                <div class="col-12 col-md-3"><input type="text" id="datepicker"></div>
                                        </div>
                                        <div class="row form-group" id="btn">
                                         <div class="col col-md-3">
                                            <center>
                                            <button type="button" class="btn btn-danger btn-sm"><i class="fa fa-download"></i>&nbsp;Download All Bills</button>
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
