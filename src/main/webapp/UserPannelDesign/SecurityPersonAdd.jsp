<!doctype html>

<html class="no-js" lang="">
    <head>
        <%
             if(session.getAttribute("email")==null)
        response.sendRedirect(application.getContextPath()+"/UserPannelDesign");
        %>
        <%@include file="headerfiles.jsp" %>
       <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
        <script>
            var count=0;
            var chk = document.getElementById("#resper");
            
            function createTF()
{
	var ctrl;
        count = count+1;
	ctrl='<div class="col col-md-3"><label for="orgname" class=" form-control-label"> Organisation Name</label></div><div class="\n\
col-12 col-md-9"><input type="text" id="orgname" name="orgname'+count+'" placeholder="Organisation Name" class="form-control" required></div><div class="col col-md-3"><label for="exp" class=" form-control-label"> No Of Years Of Experience</label></div><div class="col-12 col-md-9"><input type="text" id="exp" name="exp'+count+'" placeholder="No Of Years Of Experience" class="form-control" required></div><div class="col col-md-3"><label for="address" class=" form-control-label"> Organisation Address</label></div><div class="col-12 col-md-9" ><input type="text" id="address" name="address'+count+'" placeholder="Organisation Address" class="form-control" required></div><hr>';
        return ctrl;
        
}
               $(document).ready(function()
            {
                
               $( function() {
        $( "#datepicker" ).datepicker({
        changeMonth: true,
            changeYear: true,
            maxDate: "-18Y" 
        });
        <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
         $('#success').delay(3000).fadeOut();
    
               });
               $("#image").on("change",function(e)
               {
                  var fileInput = document.getElementById('image');
                  var filePath = fileInput.value;
                  var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
                  if(!allowedExtensions.exec(filePath)){
                  alert('Please Select Image File Only');
                  fileInput.value = '';
                  e.preventDefault();
                  return false;
                } 
               });
        
        $("#add").click(function()
        {
          ctrl=createTF();
          $("#mydiv").append(ctrl);
        });
        
    $("#payment-button").blur(function(e)
    {
       
    });
    $("#resper").change(function(){
        if($("#resper").is(":checked"))
        {
            $("#peradd1").val($("#resadd1").val());
            $("#peradd2").val($("#resadd2").val());
            $("#perarea").val($("#resarea").val());
            $("#perdis").val($("#resdis").val());
            $("#perpin").val($("#respin").val());
            $("#perstate").val($("#resstate").val());
        }
        else
        {
            $("#peradd1").val("");
            $("#peradd2").val("");
            $("#perarea").val("");
            $("#perdis").val("");
            $("#perpin").val("");
            $("#perstate").val(""); 
        }
    });
    $("#form").submit(function(e)
    {
       
        if($("#qualification").val()==="select")
       {
           alert("Please Select Qualification");
           e.preventDefault();
       }
       
       
       if($("#post").val()==="select")
       {
           alert("Please Select Post");
           e.preventDefault();
       }
       
       var values = [];
        $("input[id='orgname']").each(function() {
        values.push($(this).val());
        });
        $("#count").val(values.length);
        var len = $("#perpin").val().length;
        if(len!==6)
        {
            alert("Pincode Must Be Of 6 Digits");
            e.preventDefault();
        }
        var len1 = $("#respin").val().length;
        if(len1!==6)
        {
            alert("Pincode Must Be Of 6 Digits");
            e.preventDefault();
        }
        
   
  } ); 
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
                                    <strong class="card-title">Security Person </strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center">Personal Details</h3>
                                            </div>
                                            <hr>

                                            <form id="form" action="<%= application.getContextPath() %>/AddSecurityPersonServlet" method="post" enctype="multipart/form-data" class="form-horizontal">

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="fname" class=" form-control-label">First Name*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="fname" name="fname" placeholder="Firstname" class="form-control" required></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="mname" class=" form-control-label">Middle Name*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="mname" name="mname" placeholder="Middlename" class="form-control" required></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="lname" class=" form-control-label">Last Name*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="lname" name="lname" placeholder="Lastname" class="form-control" required></div>
                                                </div>
                                                
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label class=" form-control-label">Gender*</label></div>
                                                    <div class="col col-md-9">
                                                        <div class="form-check-inline form-check">
                                                            <label for="male" class="form-check-label ">
                                                                <input type="radio" id="male" name="gender" value="Male" class="form-check-input" checked="">Male  
                                                            </label>
                                                            &nbsp;
                                                            <label for="female" class="form-check-label ">
                                                                <input type="radio" id="female" name="gender" value="Female" class="form-check-input">Female
                                                            </label>

                                                        </div>
                                                    </div>
                                                </div>

                                                
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="fathername" class=" form-control-label">Father Name*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="fathername" name="fathername" placeholder="Fathername" class="form-control" required></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="number" class=" form-control-label"> Contact Number*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="number" name="number" placeholder="Contact No" class="form-control" required></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="email" class=" form-control-label">Email</label></div>
                                                    <div class="col-12 col-md-9"><input type="email" id="email" name="email" placeholder="Email Id" class="form-control"></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="datepicker" class=" form-control-label">Birth Date*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="datepicker" name="datepicker" placeholder="Date Of Birth" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-3"><label for="image" class="form-control-label"> Image* </label></div>
                                                    <div class="col-12 col-md-9"><input type="file" id="image" name="image" placeholder="Guard Image" class="form-control" required></div>
                                                </div>
                                                <div class="card-title">
                                                    <h3 class="text-center">Professional Details</h3>
                                                </div>
                                                <hr>
                                                
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="select" class=" form-control-label">Qualification*</label></div>
                                                    <div class="col-12 col-md-9">
                                                        <select name="select" id="qualification" class="form-control">
                                                            <option value="select">select</option>
                                                            <option value="Not Educated">Not Educated</option>
                                                            <option value="4th Std"> 4th Std</option>
                                                            <option value="8th Std">8th Std</option>
                                                            <option value="10th Std">10th Std</option>
                                                            <option value="12th Std">12th Std</option>
                                                            <option value="Under Graduate">Under Graduate</option>
                                                            <option value="Post Graduate">Post Graduate</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label class=" form-control-label">S-Type*</label></div>
                                                    <div class="col col-md-9">
                                                        <div class="form-check">
                                                            
                                                            <div class="radio">
                                                                <label for="servicemen" class="form-check-label ">
                                                                    <input type="radio" id="servicemen" name="s_type" value="EX-SERVICEMEN" class="form-check-input">EX-SERVICEMEN
                                                                </label>
                                                            </div>
                                                            <div class="radio">
                                                                <label for="civilian" class="form-check-label ">
                                                                    <input type="radio" id="civilian" name="s_type" value="CIVILIAN TRAINED" class="form-check-input">CIVILIAN TRAINED
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="select" class=" form-control-label">POST*</label></div>
                                                    <div class="col-12 col-md-9">
                                                        <select name="post" id="post" class="form-control">
                                                            <option value="select">select</option>
                                                            <option value="Gunmen">Gunmen</option>
                                                            <option value="Body Gyuard">Body Guard</option>
                                                            <option value="Security Officer">Security Officer</option>
                                                            <option value="Security Supervisor">Security Supervisor</option>
                                                            <option value="Security Guard">Security Guard</option>                                                            
                                                            <option value="Lady Searcher">Lady Searcher</option>
                                                        </select>
                                                    </div>
                                                </div>


                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label class=" form-control-label">Nationality*</label></div>
                                                    <div class="col col-md-9">
                                                        <div class="form-check-inline form-check">
                                                            <label for="indian" class="form-check-label ">
                                                                <input type="radio" id="indian" name="nationality" value="Indian" class="form-check-input" checked="">Indian
                                                            </label>
                                                            &nbsp;
                                                            <label for="nepalian" class="form-check-label ">
                                                                <input type="radio" id="nepalian" name="nationality" value="Nepalian" class="form-check-input">Nepalian
                                                            </label>

                                                        </div>
                                                    </div>
                                                </div>

                                                
                                               
                                                
                                                
                                                <div class="card-title">
                                                    <h3 class="text-center">Experience  Details</h3>
                                                </div>
                                                <hr>
                                                <div id="mydiv" class=" row form-group">
                                                    <div class="col col-md-3"><input type="button" id="add" value="Add" class="btn btn-lg btn-info"></div>
                                                    <div class="col col-md-9"></div>
                                                    <input type="hidden" value="" name="count" id="count">
                                                    <br/>
                                                </div>
                                                    
                                                <div class="card-title">
                                                    <h3 class="text-center">Address  Details</h3>
                                                </div>
                                                <hr>
                                                <div class="card-title">
                                                    <h6 class="text-center"><b>Resident Address</b></h6>
                                                </div>
                                                <hr>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="resadd1" class=" form-control-label">Address Line 1*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="resadd1" name="resadd1" placeholder="Address Line 1" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="resadd2" class=" form-control-label">Address Line 2*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="resadd2" name="resadd2" placeholder="Address Line 2" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="resarea" class=" form-control-label">Area*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="resarea" name="resarea" placeholder="Area" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="resdis" class=" form-control-label">District*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="resdis" name="resdis" placeholder="District" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="respin" class=" form-control-label">Pin Code*</label></div>
                                                    <div class="col-12 col-md-9"><input type="number" id="respin" name="respin" placeholder="Pincode" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="resstate" class=" form-control-label">State*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="resstate" name="resstate" placeholder="state" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><input type="checkbox" id="resper" name="resper" ></div>
                                                    <div class="col-12 col-md-9"><label for="resper" class=" form-control-label">Tick If Resident Address And Permanent Address Is Same</label></div>
                                                </div>
                                                <div class="card-title">
                                                    <h6 class="text-center"><b>Permanant Address</b></h6>
                                                </div>
                                                <hr>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="peradd1" class=" form-control-label">Address Line 1*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="peradd1" name="peradd1" placeholder="Address Line 1" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="peradd2" class=" form-control-label">Address Line 2*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="peradd2" name="peradd2" placeholder="Address Line 2" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="perarea" class=" form-control-label">Area*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="perarea" name="perarea" placeholder="Area" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="perdis" class=" form-control-label">District*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="perdis" name="perdis" placeholder="District" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="perpin" class=" form-control-label">Pin Code*</label></div>
                                                    <div class="col-12 col-md-9"><input type="number" id="perpin" name="perpin" placeholder="Pincode" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="perstate" class=" form-control-label">State*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="perstate" name="perstate" placeholder="state" class="form-control" required></div>
                                                </div>
                                                 <div class="card-title">
                                                    <h3 class="text-center">Other Details</h3>
                                                </div>
                                                <hr>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="account" class=" form-control-label">Bank Account Number</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="account" name="account" placeholder="Bank Account Number" class="form-control"></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="ifsc" class=" form-control-label">IFSC Code</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="ifsc" name="ifsc" placeholder="IFSC Code" class="form-control" ></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="bank" class=" form-control-label">Bank Name</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="bank" name="bank" placeholder="Bank Name" class="form-control" ></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="branch" class=" form-control-label">Bank Branch Name</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="branch" name="branch" placeholder="Bank Branch Name" class="form-control" ></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="city" class=" form-control-label">Bank City</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="city" name="city" placeholder="Bank City" class="form-control" ></div>
                                                </div>
                                                
                                                  <div class="row form-group">
                                                    <div class="col col-md-3"><label for="rstation" class=" form-control-label">Nearest Railway Station*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="rstation" name="rstation" placeholder="Nearest Railway Station" class="form-control" required></div>
                                                </div>
                                                
                                                  <div class="row form-group">
                                                    <div class="col col-md-3"><label for="pstation" class=" form-control-label">Nearest Police Station*</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="pstation" name="pstation" placeholder="Nearest Police Station" class="form-control" required></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="other" class=" form-control-label">Other Remarks</label></div>
                                                    <div class="col-12 col-md-9"><textarea name="other" id="other" class="form-control" placeholder="Other Remarks"></textarea></div>
                                                </div>
                                                <div>
<!--                                                    <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                                        <span id="payment-button-amount">SUBMIT</span>
                                                       
                                                    </button>-->
<center>
                                                    <input id="payment-button" class="btn btn-info" type="submit" value="Submit">
</center>
                                                    </div>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div> <!-- .card -->

                        </div><!--/.col-->
                    </div>
                </div><!-- .animated -->
            </div><!-- .content -->
        </div><!-- /#right-panel -->

        <!-- Right Panel -->
        <%@include file="footerfiles.jsp" %>
    </body>
</html>
