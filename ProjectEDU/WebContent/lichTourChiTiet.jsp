<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Tạo tour mới</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
	<jsp:include page="css/style.html"/>
	
	<!-- table css + js-->
	<!-- <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script> -->
	<!-- /table -->
	
	<script lang="Javascript">
	    $(document).ready(function() {
	        $('input[type=file]').ajaxfileupload({
	            'action' : 'uploadHinhAnh.trip',
	            'onComplete' : function(response) {
	                $('#upload').hide();
	                
	                var statusVal = JSON.stringify(response.status);
	    
	                if(statusVal == "false")
	                {
	                    $("#anhCaNhan").html("<font color='red'>"+ JSON.stringify(response.message) +" </font>");
	                }   
	                if(statusVal == "true")
	                {
	                    $("#anhCaNhan").html("<img src='anhThanhVien\\"+$('input[type=file]').val().split('\\').pop()+"' class='img-thumbnail' width='100%'/>");
	                }           
	            },
	            'onStart' : function() {
	                $('#upload').show();
	            }
	        });
	    });
	</script>
</head>
<body>
	<%@include file="frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Tạo lịch Tour chi Tiết</h2>
		            <br><br>
		            <div class="tab-content">
						<div class="tab-pane fade" style="width:100%;">
							<s:form method="post" action="taoChiTietTour.trip">
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
					        		<%-- <tr>
					        			<td style="width:80px; padding-top:15px;">Đặc điểm</td>
					        			<td>
						        			<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
												<s:textfield name="ctTour.dacDiem" cssClass="form-control" placeholder="Đặc điểm" aria-describedby="basic-addon1" />
											</div>
					        			</td>
					        		</tr> --%>
					        		<%-- <tr>
					        			<td style="width:80px; padding-top:15px;">Chọn Khách Sạn</td>
					        			<td>
						        			<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
												<s:select name="ctTour.maKS" id="maKS" cssClass="form-control" list="listKhachSan" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn khách sạn" />
											</div>
					        			</td>
					        		</tr> --%>
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
							</s:form>
							<div id="danhSach">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center;width:40px;">STT</th>
							                <th style="text-align: center;">Ngày khởi hành</th>
							                <th style="text-align: center;width:65px;">Đăc điểm</th>
							                <th style="text-align: center;width:65px;">Giá</th>
							                <th style="text-align: center;width:65px;">Số chỗ</th>
							                <th style="text-align: center;width:65px;">Tác vụ</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;width:40px;">STT</th>
							                <th style="text-align: center;">Ngày khởi hành</th>
							                <th style="text-align: center;width:65px;">Đăc điểm</th>
							                <th style="text-align: center;width:65px;">Giá</th>
							                <th style="text-align: center;width:65px;">Số chỗ</th>
							                <th style="text-align: center;width:65px;">Tác vụ</th>
							            </tr>
							        </tfoot>
						    	</table>
							</div>
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