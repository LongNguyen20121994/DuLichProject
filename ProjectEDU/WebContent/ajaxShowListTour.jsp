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
            <th style="text-align: center;width:40px;">M.Tour</th>
            <th style="text-align: center;">Tiêu đề</th>
            <th style="text-align: center;width:65px;">Tác vụ</th>
        </tr>
    </thead>
   	<tfoot>
   		 <tr>
            <th style="text-align: center;">M.Tour</th>
            <th style="text-align: center;">Tiêu đề</th>
            <th style="text-align: center;">Tác vụ</th>
        </tr>
    </tfoot>
    <tbody>
		<s:iterator value="listTours" >
   			<tr>
             <td align="center"><s:property value="maTour"/></td>
             <td><s:property value="tieuDe"/></td>
             <td align="center">
             	<s:a action="showInfoTour.trip?maTour=%{maTour}">Xem</s:a>
             </td>
       		 </tr>
   		</s:iterator>
   	</tbody>
</table>