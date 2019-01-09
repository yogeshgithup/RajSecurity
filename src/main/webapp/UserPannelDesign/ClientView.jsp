<%-- 
    Document   : ClientViewJsp
    Created on : Mar 16, 2018, 11:01:31 AM
    Author     : Yogesh Chawla
--%>

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
        var idd;
            $(document).ready(function() {
                
                $('#go').click(function()
                {
                    var type=$('#filter').val();
                    var v = $('#other').val();
                    $('#hi').css("visibility","visible");
                    
                    oTable =  $('#example').dataTable({
                       
                               "ajax": '<%=application.getContextPath()%>/viewClientJson?type='+type+'&v='+v,

                              "columns": [
                                  {"data":"name2"},
                                  {"data":"pname"},
                                  {"data":"no"},
                                   { "data": "name" },

                                   { "data": "url" },
                                   {"data":"gst"},
                                   { "data": "id2" },
                                   
                                   {"data" : "id"},
                                    
                                   {"data" : "id1"}
                                   
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                
                                var address = aData['url'];
                                var name = aData['name'];
                                 var id = aData['id'];
                                 var gst = aData['gst'];
                                 var no = aData['no'];
                                 var pname = aData['pname'];
                                 idd = id;
                                 $('td:eq(0)', nRow).html(id);
                                 $('td:eq(1)', nRow).html(pname);
                                 $('td:eq(2)', nRow).html(no);
                                $('td:eq(3)', nRow).html(name); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(4)', nRow).html(address);
                                $('td:eq(5)', nRow).html(gst);
                                $('td:eq(6)', nRow).html("<a href='<%=application.getContextPath()%>/viewSiteJson?id="+id+"' id='cl' class='cl'>View Site Details</a></td>");
                                $('td:eq(7)',nRow).html("<a href='<%=application.getContextPath()%>/DeleteClientServlet?id="+id+"&type=client' >Remove</a></td>");
                                $('td:eq(8)',nRow).html("<a href='' class='edit'>Edit</a></td>");

                                return nRow;

                            }
                    });
                    
                });
                $(document).on('click','#cl.cl',function(e){
                    e.preventDefault();
                    $('#hi1').css("visibility","visible");
                    if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }
                        url = $(this).attr('href');
                    $('#example1').dataTable({
                       "searching":false,
                       "info":false,
                       "paging":false,
                               "ajax": url,

                              "columns": [

                                   { "data": "cid" },

                                   { "data": "sid" },

                                   { "data": "address" },
                                   
                                   {"data" : "no"}
                                    

                                   
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                
                                var address = aData['address'];
                                var name = aData['no'];
                                 var id = aData['sid'];
                                $('td:eq(0)', nRow).html(address); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(name);
                                $('td:eq(2)',nRow).html("<a href='<%=application.getContextPath()%>/DeleteClientServlet?id="+id+"&type=site' >Remove</a></td>");
                                $('td:eq(3)',nRow).html("<a href='' class='edit'>Edit</a></td>");

                                return nRow;

                            }
                        });
                });
                var nEditing = null;

                $( document ).on( 'click','#example a.edit', function (e){
                   
                   
                     e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                   
					if ( nEditing !== null && nEditing !== nRow ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRow( oTable, nEditing );
						editRow( oTable, nRow );
						nEditing = nRow;
					}
					else if (nEditing === nRow && this.innerHTML === "Save" ) {
                       
						/* Editing this row and want to save it */
						
                        saveRow( oTable, nEditing );
						nEditing = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRow( oTable, nRow );
						nEditing = nRow;
					}
                });
    
            });
            function editRow ( oTable, nRow )
			{
				var aData = oTable.fnGetData(nRow); //newer version of fnGetData(nRow);
                               
				var jqTds = $('>td', nRow);
                                
                                jqTds[0].innerHTML = '<input type="text" id="id" name="id" value="'+jqTds[0].innerHTML+'" readonly>';
                                jqTds[1].innerHTML = '<form id="for" method="post"><input type="text" id="pname" name="pname" value="'+jqTds[1].innerHTML+'">';
				jqTds[2].innerHTML = '<input type="text" id="no" name="no" value="'+jqTds[2].innerHTML+'">';
                                jqTds[3].innerHTML = '<input type="text" id="name" name="name" value="'+jqTds[3].innerHTML+'">';
                                jqTds[4].innerHTML = '<input type="text" id="url" name="url" value="'+jqTds[4].innerHTML+'">';
                                jqTds[5].innerHTML = '<input type="text" id="gst" name="gst" value="'+jqTds[5].innerHTML+'">';
				jqTds[8].innerHTML = '<a class="edit" href="" id="save">Save</a>';

        
			}
                 
        
            function saveRow ( oTable, nRow )
			{ 
                            var data1 = new FormData();
                            pname=document.getElementById("pname").value;
                            no=document.getElementById("no").value;
                            name=document.getElementById("name").value;
                            url=document.getElementById("url").value;
                            gst=document.getElementById("gst").value;
                            id=document.getElementById("id").value;
                           
                            
                                $.ajax({
            type: "post",
            url: "<%=application.getContextPath()%>/UpdateClient",
            data: {"pname":pname,"no":no,"name":name,"url":url,"gst":gst,"id":id},
            success: function (data) {
         var jqInputs = $('input', nRow);
    oTable.fnUpdate( jqInputs[0].value, nRow, 0, false );     
    oTable.fnUpdate( jqInputs[1].value, nRow, 1, false );
    oTable.fnUpdate( jqInputs[2].value, nRow, 2, false );
    oTable.fnUpdate( jqInputs[3].value, nRow, 3, false );
    oTable.fnUpdate( jqInputs[4].value, nRow, 4, false );
    oTable.fnUpdate( jqInputs[5].value, nRow, 5, false );
    oTable.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 8, false );
    oTable.fnDraw();
            },
            error: function (e) {

                alert(e);

            }
        });
           

			}
                      

            function restoreRow ( oTable, nRow ){
                    var aData = oTable.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable.fnDraw();
             }
            </script>
            <style>
                #hi{
                    visibility: hidden;
                }  
                #hi1{
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
                                    <strong class="card-title">Client View</strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="row form-group">
                                                        <div class="col col-md-2"><select id="filter" class="form-group">
                                                                <option value="all">All</option>
                                                                <option value="firstname">By Name</option>
                                                                <option value="client_id">By ID</option>
                                                        </select>
                                                        </div>
                                                    <div class="col col-md-3"><input type="text" name="other" id="other" class="form-control" placeholder=""></div>
                                                    <div class="col col-md-3 "><input type="button" name="go" value="Go" id="go"></div>
                                            </div>
                                        <div class="row form-group" id="hi">
                                            <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Client Name</th>
                <th>Contact No</th>
                <th>Organisation Name</th>
                <th>Ornaganisation Url</th>
                <th>GST No</th>
                <th>Site Details</th>
                <th>Remove</th>
                <th>Edit</th>
            </tr>
        </thead>
                                            <tbody>
                                            </tbody>
                                            </table>
        
                                        </div>
                                        <div class="row form-group" id="hi1">
                                            <table id="example1" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>Site Address</th>
                <th>Site Person Contact No</th>
                <th>Remove</th>
                <th>Edit</th>
            </tr>
        </thead>
                                            <tbody>
                                            </tbody>
                                            </table>
        
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

