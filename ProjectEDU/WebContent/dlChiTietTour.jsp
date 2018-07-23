<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Tạo chi tiết tour mới</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
	<jsp:include page="css/style.html"/>
	
	<!-- table css + js-->
	<!-- <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script> -->
	<!-- /table -->
</head>
<body>
	<%@include file="frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Tạo Tour</h2>
		            <br><br>
		            <div class="tab-content">
						<div id="input" class="tab-pane fade in active">
					        <s:form method="post" action="chiTietTour.trip">
						        <div>
						        	<table class="table">
						        		<tr>
						        			<td style="width:120px; padding-top:15px;">Chọn Tỉnh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:120px; padding-top:15px;">Chọn Tour</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ct.maTour" id="maTour" cssClass="form-control" list="listTour" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tour" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:120px; padding-top:15px;">Chọn Khách sạn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ct.maKS" id="maKS" cssClass="form-control" list="listKhachSan" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn khách sạn" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Ngày khởi hành</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
													<s:textfield name="ct.ngayKhoiHanh" id="datepicker" cssClass="form-control" placeholder="Ngày khởi hành" aria-describedby="basic-addon1" />
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
						        			<td style="padding-top:15px;">Đặc điểm</td>
						        			<td colspan="2">
		                                       	<s:textarea name="tour.dacDiem" id="dacDiem"></s:textarea>
												<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
		                               			<script type="text/javascript">CKEDITOR.replace('dacDiem'); </script>
		                                    </td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Giá vé người lớn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-map-marker"></span></span>
													<s:textfield name="ct.giaVeNguoiLon" cssClass="form-control" placeholder="VND" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Giá vé trẻ em</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ct.giaVeTreEm" cssClass="form-control" placeholder="VND" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Giá vé trẻ nhỏ</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ct.giaVeTreNho" cssClass="form-control" placeholder="VND" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Giá vé sơ sinh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ct.giaVeSoSinh" cssClass="form-control" placeholder="VND" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số chỗ</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ct.soCho" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td colspan="2" align="right">
						        				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
							        			<s:submit value="Lưu chi tiết" cssClass="btn btn-primary"/>
						        			</td>
						        		</tr>
						        	</table>
						        </div>			        
					        </s:form>
						</div>
					</div>
				</div>
			</div>
			<%@include file="frame/right.jsp"%>
		</div>
	</div>
	<%@include file="frame/footer.jsp"%>
</body>
</html>