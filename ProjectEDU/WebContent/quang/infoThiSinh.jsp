<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông báo</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
			    <h2 style="margin-bottom:0px;">Thông tin thí sinh</h2>
	            <br><br>
		        <div class="col-md-4" style="margin:0px 0px 10px 0px; ">
		        	<img src="${ts.hinhAnh}" class="img-thumbnail" width="100%" />
		        </div>
		        <div class="col-md-8">
		        	<table class="table">
		        		<tr>
		        			<td style="width:180px; padding-top:8px;"><strong>Số CMND</strong></td>
		        			<td><b>: </b><s:property value="ts.soCMND"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Họ tên</strong></td>
		        			<td><b>: </b><s:property value="ts.hoTen"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Ngày sinh</strong></td>
		        			<td><b>: </b><s:property value="ts.ngaySinh"></s:property></td>
		        		</tr>
		        		
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Giới tính</strong></td>
		        			<td><b>: </b>${ts.gioiTinh == true ?"Nam":"Nữ" }</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Số ĐT</strong></td>
		        			<td><b>: </b><s:property value="ts.soDT"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Email</strong></td>
		        			<td><b>: </b><s:property value="ts.email"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Nơi sinh</strong></td>
		        			<td><b>: </b><s:property value="ts.noiSinh"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Địa chỉ liên hệ</strong></td>
		        			<td><b>: </b><s:property value="ts.diaChi"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Dân tộc</strong></td>
		        			<td><b>: </b><s:property value="tenDanToc"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Đối tượng ưu tiên</strong></td>
		        			<td><b>: </b><s:property value="tenDoiTuongUT"></s:property></td>
		        		</tr>
		        		
		        		<!-- Hộ khẩu -->
		        		<tr>
		        			<td style="padding-top:15px;text-align:center;"><strong> Hộ khẩu</strong></td>
		        			<td></td>
	        			</tr>
	        			<tr>
	        				<td style="padding-top:8px; padding-bottom:0px; "><strong>Tỉnh(Thành phố)<br/>Huyện(Quận)</strong></td>
		        			<td style="padding-bottom:0px;">
	        					<b>: </b><s:property value="tenTinhHK"></s:property><br/>
								<b>: </b><s:property value="tenHuyenHK"></s:property><br/>
		        			</td>
		        		</tr>
		        		<tr>
	        				<td style="border: none; padding-top:0px;"><strong>Xã(Phường)</strong></td>
		        			<td style="border: none; padding-top:0px;">
								<b>: </b><s:property value="tenXaHK"></s:property>
		        			</td>
		        		</tr>
		        		
		        		<!--  Trường cấp 3 -->
		        		<tr>
		        			<td colspan="2" style="padding-top:15px;padding-left: 65px;"><strong>Nơi học THPT hoặc tương đương</strong></td>
		        		
	        			</tr>
	        			<tr>
	        				<td style="padding-top:8px;"><strong>Tỉnh<br/>Trường 10</strong></td>
		        			<td>
	        					<b>: </b><s:property value="tenTinhTHPT10"></s:property><br/>
		        				<b>: </b><s:property value="tenTruongTHPT10"></s:property>
		        			</td>
		        		</tr>
		        		<tr>
	        				<td style="padding-top:8px;"><strong>Tỉnh<br/>Trường 11</strong></td>
		        			<td>
	        					<b>: </b><s:property value="tenTinhTHPT11"></s:property><br/>
		        				<b>: </b><s:property value="tenTruongTHPT11"></s:property>
		        			</td>
		        		</tr>
		        		<tr>
	        				<td style="padding-top:8px;"><strong>Tỉnh<br/>Trường 12</strong></td>
		        			<td>
	        					<b>: </b><s:property value="tenTinhTHPT12"></s:property><br/>
		        				<b>: </b><s:property value="tenTruongTHPT12"></s:property>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Khu vực ưu tiên</strong></td>
		        			<td><b>: </b><s:property value="tenKhuVucUT"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Năm tốt nghiệp</strong></td>
		        			<td><b>: </b><s:property value="ts.namTN"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td colspan="2" style="text-align:center;">
		        				<s:a action="showUpdateInfoThiSinh.edu" cssClass="btn btn-primary pull" cssStyle="margin-right:15px;">Cập nhật</s:a>
		        			</td>
		        		</tr>
		        	</table>
		        </div>	
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>