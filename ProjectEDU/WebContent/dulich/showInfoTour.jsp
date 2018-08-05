<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý Khách Sạn</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
		            <div class="tab-content">
			        	<h1 style="color:#2698eb !important;font-size:2em;"><s:property value="tour.tieuDe"></s:property></h1>
			        	<table class="table" style="color:#2698eb;border-style:hidden;">
			        		<tr>
			        			<td style="width:100px;padding:0px">Số ngày:</td>
			        			<td style="padding:0px"><s:property value="tourTrangChu.soNgayDem"></s:property></td>
			        		</tr>
			        		<tr>
			        			<td style="border-style:hidden;padding:0px">Khởi hành:</td>
			        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.ngayKhoiHanh"></s:property></td>
			        		</tr>
			        		<tr>
			        			<td style="border-style:hidden;padding:0px">Vận chuyển:</td>
			        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.ngayKhoiHanh"></s:property></td>
			        		</tr>
			        		<tr>
			        			<td style="border-style:hidden;padding:0px">Khách sạn:</td>
			        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.khachSan"></s:property></td>
			        		</tr>
		        		</table>
		        		<s:property value="tour.moTaTongQuan"/>
		        		<div style="color:#e30050 !important;font-size:2em;"><h3>LỊCH TRÌNH TOUR</h3></div>
		        		<s:property value="tour.lichTrinh"/>
		        		<div style="color:#e30050 !important;font-size:2em;"><h3>LỊCH KHỞI HÀNH</h3></div>
		        		<table id="table" class="table table-striped table-bordered"  style="width:100%;">
					        <thead>
					            <tr>
					                <th style="text-align: center; width:100px;">Ngày khởi hành</th>
					                <th style="text-align: center;">Đặc điểm</th>
					                <th style="text-align: center;">Giá từ</th>
					                <th style="text-align: center;">Số chỗ</th>
					                <th style="text-align: center;width:65px;">Book tour</th>
					            </tr>
					        </thead>
					        <tbody>
					 			<s:iterator value="listCtTour" >
					       			<tr>
						                <td align="center"><s:property value="ngayKhoiHanh"/></td>
						                <td><s:property value="dacDiem"/></td>
						                <td><s:property value="giaVeNguoiLon"/></td>
						                <td><s:property value="soCho"/></td>
						                <td><s:submit value="Book" name="btnUpdate" cssClass="btn btn-primary"/>
						                	<s:submit value="Chi tiết" name="btnUpdate" cssClass="btn btn-primary"/>
						                </td>
					           		 </tr>
					       		</s:iterator>
					       	</tbody>
					    </table>
					</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>