<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Danh sách hồ sơ xét tuyển</title>
<jsp:include page="../css/style.html"/>

<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
</head>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
				<!-- Tab 1 -->
				<div class="tab-content">
					<div class="tab-pane fade in active">
					<h2 style="margin-bottom:0px;">Danh sách hồ sơ xét tuyển</h2>
			            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về các tác vụ thao tác.</i></small></small></s:a>
			            <br><br>
       			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
					        <thead>
					            <tr>
					                <th style="text-align: center; width:70px;">Mã HS</th>
					                <th style="text-align: center;">Tên Trường</th>
					                <th style="text-align: center;">Đợt XT</th>
					                <th style="text-align: center;width:70px;">Ngày Nộp</th>
					                <th style="text-align: center;width:65px;">Chi tiết</th>
					            </tr>
					        </thead>
					       	<tfoot>
					       		 <tr>
					                <th style="text-align: center;">Mã HS</th>
					                <th style="text-align: center;">Tên Trường</th>
					                <th style="text-align: center;">Đợt XT</th>
					                <th style="text-align: center;">Ngày Nộp</th>
					                <th style="text-align: center;">Chi tiết</th>
					            </tr>
					        </tfoot>
					        <tbody>
					 			<s:iterator value="list" >
					       			<tr>
						                <td><s:a action="showChiTietHoSoXetTuyen.trip?maHS=%{maHS}"><s:property value="maHS"/></s:a></td>
						                <td><s:property value="maTruong"/></td>
						                <td align="center"><s:property value="maDotXT"/></td>
						                <td align="center"><s:property value="ngayNop"/></td>
						                <td align="center">
						                	<s:if test="trangThai">
						                		<s:a action="showDangKyXetTuyen.trip?hs.maTruong=%{soCMND}">Cập nhật</s:a>
						                	</s:if>
						                	<s:else>
						                		Đã đóng
						                	</s:else>
						                </td>
					           		 </tr>
					       		</s:iterator>
					       	</tbody>
					    </table>
					</div>
				</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>