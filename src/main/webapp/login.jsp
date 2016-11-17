<!DOCTYPE html
   PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <title>Lab3 - Glinskiy Vladislav</title>
      <link href="resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="resources/css/dataTables.bootstrap.css" rel="stylesheet">
      <link href="resources/css/sb-admin.css" rel="stylesheet" rel="stylesheet">
      <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
      <script type="text/javascript" src="resources/js/jquery.js"></script>
      <script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
      <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="resources/js/dataTables.bootstrap.min.js"></script>
      <script type="text/javascript" src="resources/js/dataTables.responsive.min.js"></script>
   </head>
   <body>
      <div id="wrapper">
         <!-- Navigation -->
         <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="/">Lab3</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">

              <?php
              if(isset($_SESSION['user']) != ""){
              ?>
              <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                   <b><?php echo $_SESSION['user']; ?></b> <b class="caret"></b></a>
                   <ul class="dropdown-menu">
                       <li>
                           <a href="logout.jsp?logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                       </li>
                   </ul>
               </li>
               <?php
                } else {
                ?>
               <a href="login.jsp" class="navbar-brand">Login</a>
               <a href="signup.jsp" class="navbar-brand">Sign up</a>
               <?php
                }
                ?>
            </ul>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
               <ul class="nav navbar-nav side-nav">
                  <li>
                     <a href="/"><i class="fa fa-fw fa-taxi"></i> Vehicles</a>
                  </li>
                  <li>
                     <a href="vendors.jsp"><i class="fa fa-fw fa-dollar"></i> Vendors</a>
                  </li>
                  <li>
                     <a href="raw-materials.jsp"><i class="fa fa-fw fa-tint"></i> Raw materials</a>
                  </li>
                  <li>
                     <a href="function.jsp"><i class="fa fa-fw fa-superscript"></i> Function</a>
                  </li>
               </ul>
            </div>
         </nav>
         <div id="page-wrapper">
            <div class="container-fluid">
               <!-- Page Heading -->
               <div class="row">
                  <div class="col-lg-12">
                     <h1 class="page-header">
                        Login  <small>Please enter your email and password</small>
                     </h1>
                  </div>
               </div>
               <form method="post">
                  <fieldset class="col-lg-10">
                    <?php if ( isset($MSG) ) { ?>
                    <div class="form-group">
                       <div class="alert alert-danger">
                          <?php echo $MSG; ?>
                       </div>
                    </div>
                    <?php } ?>
                     <div class="form-group">
                        <label for="author">Email: </label>
                        <input class="form-control" type="text" name="email" placeholder="Enter email" />
                        <span class="text-danger"><?php echo $emailError; ?></span>
                     </div>
                     <div class="form-group">
                        <label for="year">Password: </label>
                        <input class="form-control" type="password" name="password" placeholder="Enter password" />
                        <span class="text-danger"><?php echo $passError; ?></span>
                     </div>
                     <div class="submit">
                        <button class="btn btn-success" type="submit" name="btn-login">Submit</button>
                     </div>
                  </fieldset>
               </form>
            </div>
         </div>
      </div>
   </body>
</html>
