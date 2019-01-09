<aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">

            <div class="navbar-header">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
<!--                <a class="navbar-brand" href="./"><img src="images/logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="images/logo2.png" alt="Logo"></a>-->
            </div>

            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<%=application.getContextPath()%>/UserPannelDesign/ChangePassword.jsp"> <i class="menu-icon fa fa-dashboard"></i>Change Password </a>
                    </li>
                    <h3 class="menu-title">Security Person </h3><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="SecurityPersonAdd.jsp" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-laptop"></i>Profile Details</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-puzzle-piece"></i><a href="SecurityPersonAdd.jsp">Add</a></li>
                            <li><i class="fa fa-id-badge"></i><a href="SecurityPersonView.jsp">View</a></li>                            
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Attendance</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="NewAddAttendance.jsp">Mark Attendance</a></li>
                             <li><i class="fa fa-table"></i><a href="ViewAttendance.jsp">View salary</a></li>
                           
                        </ul>
                    </li>
                    <li>
                        <a href="AdvancePayment.jsp"> <i class="menu-icon ti-email"></i>Advance Payment </a>
                    </li>
                    
                    
                    

                    <h3 class="menu-title">Client</h3><!-- /.menu-title -->

                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-tasks"></i>Client Profile</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-fort-awesome"></i><a href="ClientAdd.jsp">Add Client</a></li>
                            <li><i class="menu-icon ti-themify-logo"></i><a href="ClientView.jsp">View</a></li>
                            <li><i class="menu-icon ti-themify-logo"></i><a href="AddSite.jsp">Add Client Site</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-tasks"></i>Security Assignment</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-fort-awesome"></i><a href="AssignClient.jsp">Assign</a></li>
                            <li><i class="menu-icon ti-themify-logo"></i><a href="ViewAssignment.jsp">View Assignment</a></li>
                        </ul>
                    </li>
                    
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Client Bill</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-fort-awesome"></i><a href="ClientBill.jsp">Make Bill</a></li>
                             <li><i class="menu-icon fa fa-fort-awesome"></i><a href="EditBill.jsp">Edit/Print Bill</a></li>
                            
                        </ul>
                    </li>
                    <h3 class="menu-title">Quotation</h3>
<li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Add Quotation</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-fort-awesome"></i><a href="AddQuotation.jsp">Make Quotation</a></li>
                            
                        </ul>
                    </li>
                    
                    <h3 class="menu-title">Requirement</h3><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>Add Requirement</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-sign-in"></i><a href="NewClientQuotation.jsp">Client</a></li>
                            <!--<li><i class="menu-icon fa fa-sign-in"></i><a href="GuestQuotationAdd.jsp">Guest</a></li>-->                         
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside>