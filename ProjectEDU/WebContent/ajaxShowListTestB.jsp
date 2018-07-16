<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%-- <jsp:include page="css/style.html"/> --%>
<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
<table id="table" class="table table-striped table-bordered"  style="width:100%;">
	<thead>
	    <tr>
	        <th style="text-align: center;width:40px;">M.Test B</th>
	        <th style="text-align: center;">Tên B</th>
	    </tr>
	</thead>
	<tfoot>
		 <tr>
	        <th style="text-align: center;width:40px;">M.Test B</th>
	        <th style="text-align: center;">Tên B</th>
	    </tr>
	</tfoot>
	<tbody>
	<s:iterator value="listTestB" >
			<tr>
	         <td align="center"><s:property value="maB"/></td>
	         <td><s:property value="tenB"/></td>
	   		 </tr>
		</s:iterator>
   	</tbody>
</table>