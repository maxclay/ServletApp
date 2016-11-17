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
                  <li class="active">
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
                        Vehicles
                     </h1>
                  </div>
               </div>
               <div class="row">
                  <div class="col-lg-12">
                     <div id="vehicle_form_container"></div>
                     <button id="add_vehicle_btn" type='button' class='btn btn-outline btn-success'>Add new vehicle</button>
                  </div>
               </div>
               <div class="row">
                  <div class="col-lg-12">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                           <h4>Existing vehicles</h4>
                        </div>
                        <div class="panel-body">
                           <div class="dataTable_wrapper">
                              <table class="table table-striped table-bordered table-hover" id="dataTables">
                                 <thead>
                                    <tr>
                                       <th class="col-lg-1"></th>
                                       <th>Name</th>
                                       <th>Carrying Capacity</th>
                                       <th>Mileage</th>
                                       <th></th>
                                       <th></th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <%--<?php--%>
                                       <%--foreach ($vehicles as &$vehicle) {--%>
                                         <%--echo "<tr>";--%>
                                         <%--echo "<td>".$vehicle->getId()."</td>";--%>
                                         <%--echo "<td>".$vehicle->getName()."</td>";--%>
                                         <%--echo "<td>".$vehicle->getCarryingCapacity()."</td>";--%>
                                         <%--echo "<td>".$vehicle->getMileage()."</td>";--%>
                                         <%--echo "<td><button type='button' class='btn btn-outline btn-success' onclick='editVehicle(".$vehicle->getId().");'>Edit</button></td>";--%>
                                         <%--echo "<td><button type='button' class='btn btn-outline btn-danger' onclick='deleteVehicle(".$vehicle->getId().");'>Delete</button></td>";--%>
                                         <%--echo "</tr>";--%>
                                       <%--}--%>
                                       <%--?>--%>
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
      <script type="text/javascript">
        var table;
        $(document).ready(function() {
          table = $('#dataTables').DataTable({
              responsive: true
          });
        });

        function editVehicle(id) {

          var rowToEdit = table.row(findRowIndexByVehicle(id));
          var rowData = rowToEdit.data();
          rowData[1] = '<input type="text" class="form-control" style="width: 120px;" id="name' + id + '" value="' + (rowData[1] || '') + '"/>';
          rowData[2] = '<input type="text" class="form-control" style="width: 120px;" id="carrying_capacity' + id + '" value="' + (rowData[2] || '') + '"/>';
          rowData[3] = '<input type="text" class="form-control " style="width: 120px;" id="mileage' + id + '" value="' + (rowData[3] || '') + '"/>';
          rowData[4] = '<button type="button" class="btn btn-outline btn-warning" onclick=confirmEdition(' + id + ');>Confirm</button>';
          rowToEdit.data(rowData);

        }

        function findRowIndexByVehicle(vehicle_id) {
          var res;
          table.rows().every(function() {
              if (this.data()[0] == vehicle_id) {
                  res = this.index();
                  // return from 'every()' function
                  return false;
              }
          });
          return res;
        }

        var confirmEdition = function(id) {
          var newData = {
              "name": $("#name" + id).val(),
              "carrying_capacity": $("#carrying_capacity" + id).val(),
              "mileage": $("#mileage" + id).val()
          };
          $.ajax({
              type: 'PUT',
              crossDomain: true,
              url: '../src/api/vehicles.jsp?id=' + id,
              contentType: "application/json",
              dataType: 'json',
              data: JSON.stringify(newData),
              complete: function() {
                  var rowToEdit = table.row(findRowIndexByVehicle(id));
                  var rowData = rowToEdit.data();

                  rowData[1] = newData.name;
                  rowData[2] = newData.carrying_capacity;
                  rowData[3] = newData.mileage;
                  rowData[4] = "<button type='button' class='btn btn-outline btn-success' onclick='editVehicle(" + id + ");'>Edit</button>";
                  rowToEdit.data(rowData).draw();
              }
          });
        }

        var deleteVehicle = function(id) {
          $.ajax({
              type: 'DELETE',
              url: '../src/api/vehicles.jsp?id=' + id,
              complete: function() {
                  table.row(findRowIndexByVehicle(id)).remove();
                  table.draw();
              }
          });
        }

        $('#add_vehicle_btn').on('click', function(event) {
          if ($('#add_vehicle_btn').text() === "Add new vehicle") {

              showVehicleForm();
              $('#add_vehicle_btn').text("Confirm");
          } else if ($('#add_vehicle_btn').text() === "Confirm") {

              confirmVehicle();
              $('#add_vehicle_btn').text("Add new vehicle");
          }
        });

        var showVehicleForm = function() {
          $('#vehicle_form_container').html('<form id="vehicle-form" class="form-inline">' +
              '<div class="form-group" style="margin-right: 1%;" >' +
              '<input class="form-control" id="name_input" placeholder="Enter name">' +
              '</div>' +
              '<div class="form-group" style="margin-right: 1%;" >' +
              '<input type="number" class="form-control" id="carrying_capacity_input" placeholder="Enter capacity">' +
              '</div>' +
              '<div class="form-group" >' +
              '<input type="number" class="form-control" id="mileage_input" placeholder="Enter mileage">' +
              '</div>' +
              '</form>');
        }

        var confirmVehicle = function() {
          createVehicle(function(response) {
            $('#vehicle_form_container').html("");

            var createdVehicle = response.responseJSON;
            console.log(createdVehicle);
            table.row.add([
               createdVehicle.id,
               createdVehicle.name,
               createdVehicle.carrying_capacity,
               createdVehicle.mileage,
               "<button type='button' class='btn btn-outline btn-success' onclick='editVehicle(" + createdVehicle.id + ");'>Edit</button>",
               "<button type='button' class='btn btn-outline btn-danger' onclick='deleteVehicle(" + createdVehicle.id + ");'>Delete</button>"
             ]);
            table.draw();
          });
        }

        var createVehicle = function(callback) {

          var vehicleData = {
            "name": $("#name_input").val(),
            "carrying_capacity": $("#carrying_capacity_input").val(),
            "mileage": $("#mileage_input").val()
          };

          $.ajax({
              type: 'POST',
              crossDomain: true,
              url: './src/api/vehicles.jsp',
              contentType: "application/json",
              dataType: 'json',
              data: JSON.stringify(vehicleData),
              complete: function(createdVehicle) {
                  if(callback) {
                    callback(createdVehicle);
                  }
              }
          });
        }
      </script>
   </body>
</html>
