<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> Create vendor</title>

    <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./resources/css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">Milk Factory</a>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">

                    <li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
            <%--TODO check if user authenticated --%>
            <a href="login" class="navbar-brand">Login</a>
            <a href="signup" class="navbar-brand">Sign up</a>
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                        <!-- /input-group -->
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-table fa-fw"></i> Tables<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="vendors">Vendors</a>
                            </li>
                            <li>
                                <a href="vehicles">Vehicles</a>
                            </li>
                            <li>
                                <a href="materials">Raw materials</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>

                        <a href="#"><i class="fa fa-wrench fa-fw"></i> New record<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="create-vendor">Vendor</a>
                            </li>
                            <li>
                                <a href="create-vehicle">Vehicle</a>
                            </li>
                            <li>
                                <a href="create-material">Raw material</a>
                            </li>
                        </ul>


                    </li>
                    <li>
                        <a href="function"><i class="fa fa-magic fa-fw"></i> Function<span></span></a>
                    </li>

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Vendor</h1>
                <h4 id="info"></h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label>First name</label>
                    <input class="form-control" type="text" id="first_name">
                </div>
                <div class="form-group">
                    <label>Last name</label>
                    <input class="form-control" type="text" id="last_name">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input class="form-control" type="text" id="address">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input class="form-control" type="tel" id="phone">
                </div>
                <div><br>
                    <button type="button" class="btn btn-outline btn-primary" id="create-vendor-btn">Create</button>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="./resources/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="./resources/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./resources/js/sb-admin-2.js"></script>

</body>
</html>
