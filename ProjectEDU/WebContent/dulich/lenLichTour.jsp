<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý lịch trình</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
	
<script lang="Javascript">	
	function changeTinh(){
		$.post("showKhachSan.trip",
	    {
	    	maTinh: $("#maTinh").val()
	    },
	    function(data,status){
	    	$("#maKhachSan").html(data);
	    });
	}
	
	function changeLoaiTour(){
		$.post("showTour.trip",
	    {
	    	maLoai: $("#maLoai").val()
	    },
	    function(data,status){
	    	$("#maTour").html(data);
	    });
	}
	$(document).ready(function() {
	    $( "#maTinh" ).change(function() {
	      	changeTinh();
	    });
	    $( "#maLoai" ).change(function() {
	    	changeLoaiTour();
	    });        
    });
</script>

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
		            <ul class="nav nav-tabs">
						<li class="${classInput}"><a data-toggle="tab" href="#input">Lịch trình chi tiết</a></li>
						<li class="${classList}"><a data-toggle="tab" href="#listds">Danh sách lịch trình</a></li>
					</ul>
		            <br><br>
		            <div class="tab-content">
						<div id="input" class="tab-pane fade in ${classInput}">
					        <s:form method="post" action="lenLichTour.trip">
						        <div>
						        	<table class="table">
						        		<tr>
						        			<td style="padding-top:15px;width:100px;">Mã lịch trình</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-map-marker"></span></span>
													<s:textfield name="ct.maChiTietTour" cssClass="form-control" readonly="true" aria-describedby="basic-addon1"></s:textfield>
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Loại Tour</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select list="listLoaiTour" name="maLoai" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn loai Tour"/>
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Tour</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ct.maTour" id="maTour" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn Tour" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Tỉnh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select list="listTinh" name="maTinh" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn Tỉnh"/>
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Khách Sạn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ct.maKS" id="maKS" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn Khách Sạn" />
												</div>
						        			</td>
						        		</tr>
						        		<%-- <tr>
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
						        		</tr> --%>
						        		<tr>
						        			<td style="padding-top:15px;">Đặc điểm</td>
		                                    <td colspan="2">
		                                       	<s:textarea name="ct.dacDiem" id="dacDiem"></s:textarea>
												<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
		                               			<script type="text/javascript">
		                               				CKEDITOR.replace('dacDiem',{
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
						        			<td style="padding-top:15px;">Vé người lớn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-map-marker"></span></span>
													<s:textfield name="ct.giaVeNguoiLon" cssClass="form-control" placeholder="Giá vé người lớn (VND)" aria-describedby="basic-addon1" value="000" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số ngày</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span></span>
													<s:textfield name="ct.soCho" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
	                                       <td colspan="2">
	                                           <div style="width:100%;" align="right">
		                                           	<s:if test="info != null && classInput != null">
						                            	<div align="right">${info.noiDung} </div>
						                            </s:if>
	                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
	                                           		<s:if test="ct.maChiTietTour != null">
	                                               		<s:submit cssClass="btn btn-info" name="btnUpdate" value="Cập nhật"></s:submit>
	                                           		</s:if>
	                                           		<s:else>
	                                               		<s:submit cssClass="btn btn-info" name="btnAddNew" value="Thêm"></s:submit>
	                                           		</s:else>
	                                           </div>
	                                       </td>
	                                    </tr>
						        	</table>
						        </div>			        
					        </s:form>
						</div>
						<div id="listds" class="tab-pane fade in ${classList}" style="width:100%;">
							<s:form action="capNhatListLichTour.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center; width:100px;">Mã lịch trình</th>
							                <th style="text-align: center;">Ngày khởi hành</th>
							                <th style="text-align: center;width:65px;">Giá vé người lớn</th>
							                <th style="text-align: center;width:65px;">Số chỗ</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center; width:100px;">Mã lịch trình</th>
							                <th style="text-align: center;width:90px;">Ngày khởi hành</th>
							                <th style="text-align: center;width:90px;">Giá vé người lớn</th>
							                <th style="text-align: center;width:65px;">Số chỗ</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="maTour"/></td>
								                <td><s:property value="ngayKhoiHanh"/></td>
								                <td><s:property value="giaVeNguoiLon"/></td>
								                <td><s:property value="soCho"/></td>
								                <td align="center"><s:checkbox name="listMaCTTour" fieldValue="%{maChiTietTour}" value="false"/></td>
							           		 </tr>
							       		</s:iterator>
							       	</tbody>
							    </table>
							    <s:if test="info != null && classList != null">
	                            	<div align="right">${info.noiDung} </div>
	                            </s:if>
	                            <div align="right">
								    <div class="btn-group">
						                <s:reset value="Làm mới" onclick="document.getElementById('check').value='Chọn hết';" cssClass="btn btn-primary"/>
								    	<input type="button" onclick="setCheck();" id="check" value="Chọn hết" class="btn btn-primary"/>
						                <s:submit value="Thêm" name="btnAddNew" cssClass="btn btn-primary"/>
						                <s:submit value="Sửa" name="btnUpdate" cssClass="btn btn-primary"/>
						                <s:submit value="Xóa" name="btnDelete" cssClass="btn btn-primary"/>
							    	</div>
						    	</div>
							</s:form>
							<script>
								function setCheck() {
									var cboxes = document.getElementsByName('listMaCTTour');
									var chk = document.getElementById('check');
									if (chk.value=="Chọn hết") {
										for (var i = 0; i < cboxes.length; i++) {
											cboxes[i].checked = true;
										}
										chk.value="Bỏ chọn";
									} else {
										for (var i = 0; i < cboxes.length; i++) {
											cboxes[i].checked = false;
										}
										chk.value="Chọn hết";
									}
								}
							</script>
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