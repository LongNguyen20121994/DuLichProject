<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cao đẳng công nghệ-Ðại học Ðà Nẵng</title>
<jsp:include page="../css/style.html"/>

<!-- table -->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

<!-- /table -->
</head>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#example').DataTable();
	} );
</script>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
			    <h2 style="margin-bottom:0px;">Danh sách thí sinh đã đăng ký</h2>
	            <br><br>
		        
		        <table id="example" class="table table-striped table-bordered"  style="width:100%;">
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th>Position</th>
		                <th>Office</th>
		                <th>Age</th>
		                <th>Start date</th>
		                <th>Salary</th>
		            </tr>
		        </thead>
		 
		        <tfoot>
		            <tr>
		                <th>Name</th>
		                <th>Position</th>
		                <th>Office</th>
		                <th>Age</th>
		                <th>Start date</th>
		                <th>Salary</th>
		            </tr>
		        </tfoot>
		 
		        <tbody>
		            <tr>
		                <td>Tiger Nixon</td>
		                <td>System Architect</td>
		                <td>Edinburgh</td>
		                <td>61</td>
		                <td>2011/04/25</td>
		                <td>$320,800</td>
		            </tr>
		            <tr>
		                <td>Garrett Winters</td>
		                <td>Accountant</td>
		                <td>Tokyo</td>
		                <td>63</td>
		                <td>2011/07/25</td>
		                <td>$170,750</td>
		            </tr>
		            <tr>
		                <td>Ashton Cox</td>
		                <td>Junior Technical Author</td>
		                <td>San Francisco</td>
		                <td>66</td>
		                <td>2009/01/12</td>
		                <td>$86,000</td>
		            </tr>
		            <tr>
		                <td>Cedric Kelly</td>
		                <td>Senior Javascript Developer</td>
		                <td>Edinburgh</td>
		                <td>22</td>
		                <td>2012/03/29</td>
		                <td>$433,060</td>
		            </tr>
		            <tr>
		                <td>Donna Snider</td>
		                <td>Customer Support</td>
		                <td>New York</td>
		                <td>27</td>
		                <td>2011/01/25</td>
		                <td>$112,000</td>
		            </tr>
		        </tbody>
		    </table>
		        
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>