<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý Khách Hàng</title>
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
	    $('#table').DataTable();
	});
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Quản lý Khách Hàng</h2>
				    <s:if test="info != null && classList != null">
                    	<div align="right">${info.noiDung} </div>
                    </s:if>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="${classList }"><a data-toggle="tab" href="#excel">Danh sách Khách Hàng</a></li>
						<li class="${classInput }"><a data-toggle="tab" href="#input">Cập nhật thông tin</a></li>
					</ul>
					<div class="tab-content">
						<div id="excel" class="tab-pane fade in ${classList }" style="width:100%;">
							<h3>Danh sách Khách Hàng</h3>
							<s:form action="capNhatListKhachHang.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center;width:100px;">Mã Khách Hàng</th>
							                <th style="text-align: center;">Họ tên</th>
							                <th style="text-align: center;">Email</th>
							                <th style="text-align: center;">Số điện thoại</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;width:100px;">Mã Khách Hàng</th>
							                <th style="text-align: center;">Họ tên</th>
							                <th style="text-align: center;">Email</th>
							                <th style="text-align: center;">Số điện thoại</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="maKH"/></td>
								                <td><s:property value="hoTen"/></td>
								                <td><s:property value="email"/></td>
								                <td><s:property value="soDT"/></td>
								                <td align="center"><s:checkbox name="listMaKH" fieldValue="%{maKH}" value="false"/></td>
							           		 </tr>
							       		</s:iterator>
							       	</tbody>
							    </table>
	                            <div align="right">
								    <div class="btn-group">
								    	<s:reset value="Làm mới" onclick="document.getElementById('check').value='Chọn hết';" cssClass="btn btn-primary"/>
								    	<input type="button" onclick="setCheck();" id="check" value="Chọn hết" class="btn btn-primary"/>
						                <s:submit value="Sửa" name="btnUpdate" cssClass="btn btn-primary"/>
						                <s:submit value="Xóa" name="btnDelete" cssClass="btn btn-primary"/>
						                <s:submit value="Gửi thông tin đăng nhập" name="btnSend" cssClass="btn btn-primary"/>
							    	</div>
						    	</div>
							</s:form>
							<script>
								function setCheck() {
									var cboxes = document.getElementsByName('listMaKH');
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
						<div id="input" class="tab-pane fade in ${classInput }">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatKhachHang.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:100px;">Mã Khách Hàng</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
                                        		<s:textfield name="kh.maKH" cssClass="form-control" readonly="%{(maKH!=null?'true':'false')}" aria-describedby="basic-addon1" placeholder="Mã Khách Hàng"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Tên Khách Hàng</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
                                        		<s:textfield name="kh.hoTen" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Tên Khách Hàng"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Email</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
                                        		<s:textfield name="kh.email" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Địa chỉ email"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Số điện thoại</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-earphone"></span></span>
                                        		<s:textfield name="kh.soDT" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Số điện thoại"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                       <td colspan="2">
	                                       <s:if test="info != null && classInput != null">
				                            	<div align="right">${info.noiDung} </div>
				                           </s:if>
                                           <div style="width:100%;" align="right">
                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
                                           		<s:if test="maKH != null">
                                               		<s:submit cssClass="btn btn-info" name="btnUpdate" value="Cập nhật"></s:submit>
                                           		</s:if>
                                           		<s:else>
                                               		<s:submit cssClass="btn btn-info" name="btnAddNew" value="Phải chọn Khách Hàng trước khi thao tác!"></s:submit>
                                           		</s:else>
                                           </div>
                                       </td>
                                    </tr>
                                </table>
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