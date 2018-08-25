<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Màn hình thông báo</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script lang="Javascript">
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="content" style="margin: 0px 0px 0px 30px">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
					<div style="color:#e30050 !important;font-size:2em;"><h3>Đặt Tour thành công!</h3></div>
					<div style="color:#e30050 !important;font-size:2em;">
						<h4>Nếu chưa nhận được mail đăng nhập vui lòng click link:</h4>
						<s:a action="sendMailMatKhau.trip">Nhận mail tài khoản quản lý tour</s:a>
						<h4> hệ thống sẽ gửi lại mail cho bạn!</h4>
					</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>