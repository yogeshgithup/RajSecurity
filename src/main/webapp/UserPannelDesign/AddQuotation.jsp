<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%@include file="headerfiles.jsp" %>
        <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        $(document).ready(function(e){
           $(document).on('change','.click',function(e){
             if($(this).val()!=="-")
             {
                 var obj='$(input[for='+$(this).attr('name')+'])';
                 $.each($('input[for='+$(this).attr('name')+']'),function(index,value){
                     $(this).removeAttr('disabled');
                     $(this).removeAttr('value');
                 });
             }
             else
             {
                 
                 $.each($('input[for='+$(this).attr('name')+']'),function(index,value){
                     $(this).attr('disabled','disabled');
                     $(this).val("-");
                    
                 });
             }
           });
           $(document).on('submit','#frm',function(e){
              
              var a="";
              $.each($('input[type=text]'),function(index,value){
                 a=a+$(this).val();
              });
              if(a!=="------------")
              {
                  
              }
              else
              {
                  alert("Please Input Atleast One Value");
                  e.preventDefault();
                  
              }
           });
        });
        </script>
    <body>
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
                                    <strong class="card-title">Quotation</strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                            <form id="frm" method="post" action="<%=application.getContextPath()%>/NewAddQuotation">
                                            <div class="row form-group">
                                                <div class="col col-md-2"><label  class="form-control-label">To,</label></div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-4"><input name="name" id="orgname" class="w3-input" type="search" required=""></div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-4"><input name="orgname" class="w3-input" type="search"></div>
                                            </div>
                                              
                                            <div class="row form-group">
                                                <div class="col col-md-4"><input name="city" class="w3-input" type="search"></div>
                                            </div>
                                              <div class="row form-group"></div>
                                              <br>
                                                <div class="row form-group">
                                                    <table class="table">
                                                        <thead>
                                                        <th>Sr No.</th>
                                                        <th>Post</th>
                                                        <th>HRS Shift</th>
                                                        <th>Ex- Servicemen</th>
                                                        <th>Civilian Trained</th>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>Gunmen</td>
                                                                <td><select name="gunmenhrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" name="gunmenex" for="gunmenhrs" class="w3-input" value="-" disabled=""></td>
                                                                <td><input type="text" name="gunmenct" for="gunmenhrs" class="w3-input" value="-" disabled=""></td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>Body Guard</td>
                                                                <td><select name="bodyguardhrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" for="bodyguardhrs" name="bodyguardex" class="w3-input" value="-" disabled=""></td>
                                                                <td><input type="text" for="bodyguardhrs" name="bodyguardct" class="w3-input" value="-" disabled=""></td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>Security Officers</td>
                                                                <td><select name="officershrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" for="officershrs" name="officerex" class="w3-input" value="-" disabled=""></td>
                                                                <td><input type="text" for="officershrs" name="officerct" class="w3-input" value="-" disabled=""></td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>Security Supervisors</td>
                                                                <td><select name="supervisorshrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" for="supervisorshrs" name="supervisorsex" value="-" class="w3-input" disabled=""></td>
                                                                <td><input type="text" for="supervisorshrs" name="supervisorsct" value="-" class="w3-input" disabled=""></td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td>Security Guards</td>
                                                                <td><select name="guardshrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" for="guardshrs" name="guardsex" value="-" class="w3-input" disabled=""></td>
                                                                <td><input type="text" for="guardshrs" name="guardsct" value="-" class="w3-input" disabled=""></td>
                                                            </tr>
                                                            <tr>
                                                                <td>6</td>
                                                                <td>Lady Searcher</td>
                                                                <td><select name="ladyhrs" class="click"><option value="-"></option><option value="8.00">8.00</option><option value="12.00">12.00</option></select></td>
                                                                <td><input type="text" name="ladysex" for="ladyhrs" value="-" class="w3-input" disabled=""></td>
                                                                <td><input type="text" name="ladyct" for="ladyhrs" value="-" class="w3-input" disabled=""></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <br>
                                                    
                                                </div>
                                              <div class="row form-group">
                                                        <div class="col col-md-2">Service Charges</div>
                                                        <div class="col col-md-2"><input name="scharge" class="w3-input" type="search"></div>
                                                    </div>
                                                    <br>
                                                    <center>
                                                    <div class="row form-group">
                                                    <button type="submit" class="btn btn-info" >Add Quotation</button>
                                                    </div>
                                                    </center>
                                            </form>
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
