<% if (session.getAttribute("username") == null) {
    response.sendRedirect("login");
}
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Vehicles</title>

    <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./resources/css/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="./resources/css/dataTables.bootstrap.css" rel="stylesheet">

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
        <!-- /.navbar-header -->
        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    <b>${username}</b> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="authentication"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
            <!-- /.dropdown -->
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

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Vehicles</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">

                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">

                            <table class="table table-striped table-bordered table-hover" id="vehicles-table">
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
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- /#wrapper -->
<!-- jQuery -->
<script src="./resources/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="./resources/js/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="./resources/js/jquery.dataTables.min.js"></script>
<script src="./resources/js/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./resources/js/sb-admin-2.js"></script>

<%--TODO move to separate file--%>
<script type="text/javascript">

    var table;
    $(document).ready(function () {
        table = $('#vehicles-table').DataTable({
            responsive: true
        });
        fillTable();
    });

    function fillTable() {
        $.ajax({
            type: 'GET',
            crossDomain: true,
            url: './api/vehicles/',
            dataType: 'json',
            complete: function (response) {

                var vehicles = response.responseJSON;

                for (var index = 0; index < vehicles.length; index++) {
                    table.row.add(
                            [
                                vehicles[index].id,
                                vehicles[index].name || "",
                                vehicles[index].carryingCapacity || "",
                                vehicles[index].mileage || "",
                                "<button type='button' class='btn btn-outline btn-success' onclick='editVehicle(" + vehicles[index].id + ");'>Edit</button>",
                                "<button type='button' class='btn btn-outline btn-danger' onclick='deleteVehicle(" + vehicles[index].id + ");'>Delete</button>"
                            ]
                    ).id(index);
                }
                table.draw();
            }
        });
    }

    function editVehicle(id) {
        var rowToEdit = table.row(findRowIndexByVehicle(id));
        var rowData = rowToEdit.data();
        rowData[1] = '<input type="text" class="form-control" style="width: 120px;" id="name' + id + '" value="' + (rowData[1] || '') + '"/>';
        rowData[2] = '<input type="text" class="form-control" style="width: 120px;" id="carryingCapacity' + id + '" value="' + (rowData[2] || '') + '"/>';
        rowData[3] = '<input type="text" class="form-control " style="width: 120px;" id="mileage' + id + '" value="' + (rowData[3] || '') + '"/>';
        rowData[4] = '<button type="button" class="btn btn-outline btn-warning" onclick=confirmEdition(' + id + ');>Confirm</button>';
        rowToEdit.data(rowData);
    }

    function findRowIndexByVehicle(vehicle_id) {
        var res;
        table.rows().every(function () {
            if (this.data()[0] == vehicle_id) {
                res = this.index();
                // return from 'every()' function
                return false;
            }
        });
        return res;
    }

    var confirmEdition = function (id) {
        var newData = {
            "name": $("#name" + id).val(),
            "carryingCapacity": $("#carryingCapacity" + id).val(),
            "mileage": $("#mileage" + id).val()
        };
        $.ajax({
            type: 'PUT',
            crossDomain: true,
            url: './api/vehicles/' + id,
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(newData),
            complete: function () {
                var rowToEdit = table.row(findRowIndexByVehicle(id));
                var rowData = rowToEdit.data();
                rowData[1] = newData.name;
                rowData[2] = newData.carryingCapacity;
                rowData[3] = newData.mileage;
                rowData[4] = "<button type='button' class='btn btn-outline btn-success' onclick='editVehicle(" + id + ");'>Edit</button>";
                rowToEdit.data(rowData).draw();
            }
        });
    }

    var deleteVehicle = function (id) {
        $.ajax({
            type: 'DELETE',
            url: './api/vehicles/' + id,
            complete: function () {
                table.row(findRowIndexByVehicle(id)).remove();
                table.draw();
            }
        });
    }

</script>

</body>
</html>
