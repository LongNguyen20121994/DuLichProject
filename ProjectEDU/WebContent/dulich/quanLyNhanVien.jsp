<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý Tài khoản</title>
<jsp:include page="../css/style.html"/>

<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
</head>

<script type="text/javascript">
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
<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="content" style="margin: 0px 0px 0px 30px">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="${classInput}"><a data-toggle="tab" href="#input">Thông tin Tài khoản</a></li>
						<li class="${classList}"><a data-toggle="tab" href="#excel">Danh sách Tài khoản</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in ${classInput}">
							<h3>Nhập thông tin</h3>
							<s:form action="themNhanVien.trip" method="post">
								<div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
						        	<img src="images/addImage.jpg" class="img-thumbnail" width="100%" />
						        </div>
						        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
						        <div class="col-md-8">
						        	<table class="table">
						        		<tr>
						        			<td style="width:90px; padding-top:15px;">Số CMND</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
													<s:textfield name="nv.soCMND" cssClass="form-control" placeholder="Số chứng minh thư" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Họ tên</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="nv.hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
				        					<td style="padding-top:15px;">Ngày sinh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
													<s:textfield name="nv.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
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
						        			<td style="padding-top:10px;">Giới tính</td>
						        			<td>
												<s:radio name="nv.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="true"/>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số ĐT</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="nv.soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Email</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="nv.email" cssClass="form-control" placeholder="Địa chỉ email" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Cấp quyền</td>
						        			<td>
							        			<div class="input-group">
													<s:checkbox name="nv.isAdmin" cssStyle="margin-top:5px;margin-right:5px;" value="false" /><span >Admin</span>
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
	                                           		<s:if test="soCMND != null">
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
						<div id="excel" class="tab-pane fade in ${classList}" style="width:100%;">
							<s:form action="capNhatListNhanVien.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center; width:100px;">Số CMND</th>
							                <th style="text-align: center;">Họ tên</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center; width:100px;">Số CMND</th>
							                <th style="text-align: center;">Họ tên</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="soCMND"/></td>
								                <td><s:property value="hoTen"/></td>
								                <td align="center"><s:checkbox name="listMaNV" fieldValue="%{soCMND}" value="false"/></td>
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
									var cboxes = document.getElementsByName('listMaNV');
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