<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<!-- Bootstrap core CSS -->
<link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="http://getbootstrap.com/examples/offcanvas/offcanvas.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="http://getbootstrap.com/assets/js/ie-emulation-modes-warning.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  <nav class="navbar navbar-fixed-top navbar-inverse">
    <tiles:insertAttribute name="header" />
  </nav>
  <!-- /.navbar -->
  <div class="container">
    <div class="row row-offcanvas row-offcanvas-right">
      <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
          <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
        </p>
        <div class="jumbotron">
          <h3>
            <tiles:getAsString name="contentTitle"></tiles:getAsString>
          </h3>
          <tiles:insertAttribute name="body" />
        </div>
      </div>
      <!-- <div class="col-xs-12 col-sm-9"> -->
      <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
        <tiles:insertAttribute name="sidebarMenu" />
      </div>
      <!--/.sidebar-offcanvas-->
    </div>
    <!--/row-->
    <hr>
    <footer>
      <tiles:insertAttribute name="footer" />
    </footer>
  </div>
  <!--/.container-->
  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
  <script src="http://getbootstrap.com/examples/offcanvas/offcanvas.js"></script>
</body>
</html>
