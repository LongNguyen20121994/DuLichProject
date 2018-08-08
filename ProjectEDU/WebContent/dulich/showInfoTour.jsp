<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông tin chi tiết</title>
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
					                <th style="text-align: center;width:100px;">Ngày khởi hành</th>
					                <th style="text-align: center;">Đặc điểm</th>
					                <th style="text-align: center;width:100px;">Giá từ</th>
					                <th style="text-align: center;width:50px;">Số chỗ</th>
					                <th style="text-align: center;width:120px;">Book tour</th>
					            </tr>
					        </thead>
					        <tbody>
					 			<s:iterator value="listCtTour" >
					       			<tr>
						                <td align="center"><s:property value="ngayKhoiHanh"/></td>
						                <td><s:property value="dacDiem"/></td>
						                <td><s:property value="giaVeNguoiLon"/></td>
						                <td style="text-align: center;"><s:property value="soCho"/></td>
						                <td style="text-align: center;">
						                	<s:a action="bookingTour.trip?maTour=%{maTour}" target="_blank">
						                		<button style="color: #fff;background-color:#d2322d;border: 1px solid #ac2925;padding: 5px 10px;border-radius: 10px;">Book</button>
									        </s:a>
						                	<%-- <s:submit value="Chi tiết" name="btnUpdate" cssClass="btn button-chitiet"/> --%>
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