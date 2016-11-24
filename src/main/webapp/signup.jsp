<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> Sign up</title>

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
                <h1 class="page-header">Sign up</h1>
                <h4 id="info"></h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-4">
                <form method="post" action="./registration">
                    <fieldset class="col-lg-10">

                        <% if (session.getAttribute("error") != null) { %>
                        <div class="form-group">
                            <div class="alert alert-danger">
                                ${error} <% session.removeAttribute("error"); %>
                            </div>
                        </div>
                        <% }%>

                        <div class="form-group">
                            <label>Name</label>
                            <input class="form-control" type="text" id="name" name="name">
                            <span class="text-danger">${userNameError} <% session.removeAttribute("userNameError"); %> </span>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input class="form-control" type="email" id="email" name="email">
                            <span class="text-danger">${emailError} <% session.removeAttribute("emailError"); %> </span>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input class="form-control" type="password" id="password" name="password">
                            <span class="text-danger">${passwordError} <% session.removeAttribute("passwordError"); %> </span>
                        </div>
                        <div class="form-group">
                            <label>Repeat password</label>
                            <input class="form-control" type="password" id="repeat_password" name="repeat_password">
                            <span class="text-danger">${repeatPasswordError} <% session.removeAttribute("repeatPasswordError"); %> </span>
                        </div>
                        <div><br>
                            <button type="submit" class="btn btn-outline btn-primary" id="sign-up-btn">Sign up</button>
                        </div>
                    </fieldset>
                </form>
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
