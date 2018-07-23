<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hướng dẫn đăng ký dự thi</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
			    <h2 style="margin-bottom:0px;">Hướng dẫn upload danh sách thí sinh đăng ký dự thi</h2>
	            <br><br>
		        <div>
		        	<h4><b><u>Bước 1</u>:</b> Download file <a href="template/DanhSachThiSinhDangKy.xls" download>DanhSachThiSinhDangKy.xls</a> và điền thông tin các thí sinh vào file.</h4>
		        	<div style="margin-left:15px;">
			        	<br>
			        	<img src="images/HDDKDTGV/hd1.jpg">
			        	<br>
			        	<h4><font color="red"><u>Lưu ý</u>:</font> Nên copy định dạng(<font color="red">Format Painter</font>) 
			        	dòng mẫu để tránh sai định dạng.</h4>
		        	</div>
		        	
		       		<h4><b><u>Bước 2</u>:</b> Chọn thao tác muốn xử lý: Đăng ký danh sách mới hoặc cập nhật danh sách <br/>
		       		--> Hiện trang đăng ký danh sách thí sinh dự thi/ Cập nhật danh sách thí sinh dự thi.</h4>
		        	<div style="margin-left:15px;">
			        	<br>
			        	<img src="images/HDDKDTGV/hd2.jpg">
			        	<br>
		        	</div>
		        	<h4><b><u>Bước 3</u>:</b> Chọn tab thứ 2 (Upload danh sách đăng ký/ upload danh sách cập nhật).</h4>
		        	<div style="margin-left:15px;">
			        	<h4>Ảnh các thí sinh phải được nén lại trong một file rar(.rar)</h4>
			        	<br>
			        	<h4>- Đăng ký dự thi</h4>
			        	<img src="images/HDDKDTGV/hd3.jpg" width="80%">
			        	<br>
			        	<h4>- Cập nhật danh sách đăng ký dự thi</h4>
			        	<img src="images/HDDKDTGV/hd4.jpg" width="80%">
		        	</div>
		        	<h4>Màn hình upload thành công.</h4>
		        	<div style="margin-left:15px;">
			        	<br>
			        	<h4>- Thông báo</h4>
			        	<img src="images/HDDKDTGV/hd5.jpg" width="80%">
			        	<br>
			        	<h4>- Download danh sách thí sinh đã đăng ký dự thi</h4>
			        	<img src="images/HDDKDTGV/hd6.jpg" width="80%">
		        	</div>
		        </div>	
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>