<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng ký tài khoản Quản lý ĐH-CĐ</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
			    <h2 style="margin-bottom:0px;">Đăng ký lịch đi tour</h2>
	            <br><br>
		        <s:form method="post" action="taoChiTietTour.trip">
			        <div class="col-md-8">
			        	<table class="table">
			        		<tr>
			        			<td style="width:150px; padding-top:15px;">Ngày khởi hành</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
										<s:textfield name="ctTour.ngayKhoiHanh" id="datepicker" cssClass="form-control" placeholder="Ngày khởi hành" aria-describedby="basic-addon1" />
									</div>
									<script src="js/bootstrap-datepicker.js"></script>
						            <script type="text/javascript">
						                // When the document is ready
						                $(document).ready(function () {
						                    $('#datepicker').datepicker({
						                        format: "dd/mm/yyyy"
						                    });
						                });
						            </script>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Giờ khởi hành</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span></span>
										<s:textfield name="gio" cssClass="form-control" placeholder="Giờ khởi hành" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Giá vé người lớn</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-usd"></span></span>
										<s:textfield name="ctTour.giaVeNguoiLon" cssClass="form-control" placeholder="Đơn vị VND" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Giá vé trẻ em</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-usd"></span></span>
										<s:textfield name="ctTour.giaVeTreEm" cssClass="form-control" placeholder="Đơn vị VND" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Giá vé trẻ nhỏ</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-usd"></span></span>
										<s:textfield name="ctTour.giaVeTreNho" cssClass="form-control" placeholder="Đơn vị VND" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Giá vé trẻ sơ sinh</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-usd"></span></span>
										<s:textfield name="ctTour.giaVeSoSinh" cssClass="form-control" placeholder="Đơn vị VND" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td style="width:80px; padding-top:15px;">Số chỗ</td>
			        			<td>
				        			<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-plus"></span></span>
										<s:textfield name="ctTour.soCho" cssClass="form-control" placeholder="Số chỗ" aria-describedby="basic-addon1" />
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td colspan="2" align="right">
			        				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
				        			<s:submit value="Đăng ký" cssClass="btn btn-primary"/>
			        			</td>
			        		</tr>
			        	</table>
			        </div>			        
		        </s:form>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>