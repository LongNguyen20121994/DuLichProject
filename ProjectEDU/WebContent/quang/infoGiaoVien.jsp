<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông tin cá nhân</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
			    <h2 style="margin-bottom:0px;">Thông tin giáo viên</h2>
	            <br><br>
		        <div class="col-md-4" style="margin:0px 0px 10px 0px; ">
		        	<img src="${gv.hinhAnh}" class="img-thumbnail" width="100%" />
		        </div>
		        <div class="col-md-8">
		        	<table class="table">
		        		<tr>
		        			<td style="width:180px; padding-top:8px;"><strong>Số CMND</strong></td>
		        			<td><b>: </b><s:property value="gv.soCMND"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Họ tên</strong></td>
		        			<td><b>: </b><s:property value="gv.hoTen"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Ngày sinh</strong></td>
		        			<td><b>: </b><s:property value="gv.ngaySinh"></s:property></td>
		        		</tr>
		        		
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Giới tính</strong></td>
		        			<td><b>: </b>${gv.gioiTinh == true ?"Nam":"Nữ" }</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Số ĐT</strong></td>
		        			<td><b>: </b><s:property value="gv.soDT"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:8px;"><strong>Email</strong></td>
		        			<td><b>: </b><s:property value="gv.email"></s:property></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-top:15px;text-align:center;"><strong> Hộ khẩu</strong></td>
		        			<td></td>
	        			</tr>
	        			<tr>
	        				<td style="padding-top:8px; padding-bottom:0px; "><strong>Tỉnh(Thành phố)<br/>Huyện(Quận)</strong></td>
		        			<td style="padding-bottom:0px;">
	        					<b>: </b><s:property value="gv.tenTinh"></s:property><br/>
								<b>: </b><s:property value="gv.tenHuyen"></s:property><br/>
		        			</td>
		        		</tr>

		        		<tr>
	        				<td style="border: none; padding-top:0px;"><strong>Xã(Phường)</strong></td>
		        			<td style="border: none; padding-top:0px;">
								<b>: </b><s:property value="gv.tenXa"></s:property>
		        			</td>
		        		</tr>
		        		
		        		<tr>
		        			<td style="padding-top:15px; text-align:center;"><strong>Trường THPT</strong></td>
		        			<td></td>
	        			</tr>
	        			<tr>
	        				<td style="padding-top:8px;"><strong>Tỉnh<br/>Trường</strong></td>
		        			<td>
	        					<b>: </b><s:property value="gv.tenTinhTHPT"></s:property><br/>
		        				<b>: </b><s:property value="gv.tenTruongTHPT"></s:property>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td colspan="2" style="text-align:center;">
		        				<s:a action="showUpdateInfoGiaoVien.edu" cssClass="btn btn-primary pull" cssStyle="margin-right:15px;">Cập nhật</s:a>
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