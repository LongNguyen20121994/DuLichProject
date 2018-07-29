<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Tạo tour mới</title>
	<script type="text/javascript" src="http://localhost:8081/ckfinder/ckfinder/ckfinder.js"></script>
	<jsp:include page="../css/style.html"/>
	
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
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Tạo Tour</h2>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#input">Tạo mới tour</a></li>
						<li><a data-toggle="tab" href="#excel">Lịch trình chi tiết</a></li>
					</ul>
		            <br><br>
		            <div class="tab-content">
						<div id="input" class="tab-pane fade in active">
					        <s:form method="post" action="dangKyTour.trip">
						        <div>
						        	<table class="table">
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Chọn Loại Tour</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tour.maLoai" id="maLoai" cssClass="form-control" list="listLoaiTour" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn loại Tour" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Ảnh bìa</td>
						        			<td colspan="2">
						        				<div id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
										        	<img src="images/addImage.jpg" class="img-thumbnail" width="20%" />
										        </div>
										        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Tiêu đề</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-header"></span></span>
													<s:textfield name="tour.tieuDe" cssClass="form-control" placeholder="Tiêu đề" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Mô tả tổng quan</td>
		                                    <td colspan="2">
		                                       	<s:textarea name="tour.moTaTongQuan" id="moTaTongQuan"></s:textarea>
												<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
		                               			<script type="text/javascript">
		                               				CKEDITOR.replace('moTaTongQuan',{
		                               				filebrowserBrowseUrl: '../ckfinder/ckfinder/ckfinder.html',
		                               				filebrowserImageBrowseUrl: '../ckfinder/ckfinder/ckfinder.html?type=Images',
		                               				filebrowserFlashBrowseUrl: '../ckfinder/ckfinder/ckfinder.html?type=Flash',
		                               				filebrowserUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
		                               				filebrowserImageUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		                               				filebrowserFlashUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
		                               			}); </script>
		                                    </td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Lịch trình</td>
		                                    <td colspan="2">
		                                       	<s:textarea name="tour.lichTrinh" id="lichTrinh"></s:textarea>
												<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
		                               			<script type="text/javascript">
		                               				CKEDITOR.replace('lichTrinh',{
		                               				filebrowserBrowseUrl: '../ckfinder/ckfinder/ckfinder.html',
		                               				filebrowserImageBrowseUrl: '../ckfinder/ckfinder/ckfinder.html?type=Images',
		                               				filebrowserFlashBrowseUrl: '../ckfinder/ckfinder/ckfinder.html?type=Flash',
		                               				filebrowserUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
		                               				filebrowserImageUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		                               				filebrowserFlashUploadUrl: '../ckfinder/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
		                               			}); </script>
		                                    </td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Địa điểm khởi hành</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-map-marker"></span></span>
													<s:textfield name="tour.diaDiemKhoiHanh" cssClass="form-control" placeholder="Địa điểm khởi hành" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số ngày</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span></span>
													<s:textfield name="tour.soNgay" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số đêm</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span></span>
													<s:textfield name="tour.soDem" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Ghi chú</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
													<s:textfield name="tour.ghiChu" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td colspan="2" align="right">
						        				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
							        			<s:submit value="Lưu tour" cssClass="btn btn-primary"/>
						        			</td>
						        		</tr>
						        	</table>
						        </div>			        
					        </s:form>
						</div>
						<%-- <div id="excel" class="tab-pane fade" style="width:100%;">
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
					        		<tr>
					        			<td style="width:80px; padding-top:15px;">Đặc điểm</td>
					        			<td>
						        			<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
												<s:textfield name="ctTour.dacDiem" cssClass="form-control" placeholder="Đặc điểm" aria-describedby="basic-addon1" />
											</div>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td style="width:80px; padding-top:15px;">Chọn Khách Sạn</td>
					        			<td>
						        			<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
												<s:select name="ctTour.maKS" id="maKS" cssClass="form-control" list="listKhachSan" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn khách sạn" />
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
						</div> --%>
					</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>