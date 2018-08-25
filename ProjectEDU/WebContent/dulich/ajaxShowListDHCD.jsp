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
	        <th style="text-align: center;width:40px;">M.Trường</th>
	        <th style="text-align: center;">Tên trường</th>
	        <th style="text-align: center;width:60px;">Tên tỉnh</th>
	        <th style="text-align: center;width:40px;">C.Tiêu</th>
	        <th style="text-align: center;width:65px;">Tác vụ</th>
	    </tr>
	</thead>
	<tfoot>
		 <tr>
	        <th style="text-align: center;">Mã trường</th>
	        <th style="text-align: center;">Tên trường</th>
	        <th style="text-align: center;">Tên tỉnh</th>
	        <th style="text-align: center;">Chỉ tiêu</th>
	        <th style="text-align: center;">Tác vụ</th>
	    </tr>
	</tfoot>
	<tbody>
	<s:iterator value="listTruongDHCD" >
			<tr>
	         <td align="center"><s:property value="maTruong"/></td>
	         <td><s:property value="tenTruong"/></td>
	         <td align="center"><s:property value="diaChi"/></td>
	         <td align="center"><s:property value="ghiChu"/></td>
	         <td align="center">
	         	<s:a action="showInfoDHCD.trip?maTruong=%{maTruong}">Xem</s:a>
	         	<s:a action="showDangKyXetTuyen.trip?hs.maTruong=%{maTruong}">Nộp</s:a>
	         </td>
	   		 </tr>
		</s:iterator>
   	</tbody>
</table>