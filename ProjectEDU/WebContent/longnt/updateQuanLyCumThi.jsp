<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cao đẳng công nghệ-Ðại học Ðà Nẵng</title>
<jsp:include page="../css/style.html" />

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
	<%@include file="../frame/header.jsp"%>

	<s:set id="msg" value="%{info.noiDung}"></s:set>
	<s:hidden id="msg" name="info.noiDung"></s:hidden>
	<s:if test="%{info !=null}">
		<script type="text/javascript">
			alert($("#msg").val());
		</script>

	</s:if>
	﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">Cập
							nhật thông tin quản lý cụm thi</a></li>
				</ul>
				<div class="col-md-12">

					<!-- Tab 1 -->
					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
							<h2 style="margin-bottom: 0px;">Danh sách quản lý cụm thi đã
								đăng ký</h2>
							<s:a href="">
								<small><small><i>Bạn phải chịu tất cả trách
											nhiệm về thông tin cung cấp.</i></small></small>
							</s:a>
							<br>
							<br>
							<s:form method="post" action="updateQuanLyCumThi.trip">
								<table id="table" class="table table-striped table-bordered"
									style="width: 100%;">
									<thead>
										<tr>
											<th style="text-align: center; width: 50px;">Số CMND</th>
											<th style="text-align: center;width: 200px">Họ tên</th>
											<th style="text-align: center; width: 140px;">Ngày sinh</th>
											<th style="text-align: center; width: 40px;">Giới tính</th>
											<th style="text-align: center; width: 50px;">Số ĐT</th>
											<th style="text-align: center; width: 70px;">Đơn vị thi</th>
											<th style="text-align: center; width: 65px;">Cập nhật</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th style="text-align: center;">Số CMND</th>
											<th style="text-align: center;">Họ tên</th>
											<th style="text-align: center;">Ngày sinh</th>
											<th style="text-align: center;">Giới tính</th>
											<th style="text-align: center;">Số ĐT</th>
											<th style="text-align: center;">Đơn vị thi</th>
											<th style="text-align: center;">Cập nhật</th>
										</tr>
									</tfoot>
									<tbody>
										<s:iterator id="list" value="listqlct">
											<tr>
												<td><s:property value="soCMND" /></td>
												<td><s:property value="hoTen" /></td>
												<td style="text-align: center;"><s:property
														value="ngaySinh" /></td>
												<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
												<td><s:property value="soDT" /></td>
												<td><s:property value="donViThi" /></td>
												<td align="center"><s:checkbox name="listSoCMND"
														fieldValue="%{soCMND}" value="false" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<div align="center">
									<s:reset value="Làm mới" cssClass="btn btn-primary" />
									<s:submit value="Sửa" name="btnSua" cssClass="btn btn-primary" />
									<s:submit value="Xóa" name="btnXoa" cssClass="btn btn-primary" />
								</div>
							</s:form>
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