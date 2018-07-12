<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật địa điểm thi</title>
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
				    <h2 style="margin-bottom:0px;">Cập nhật địa điểm thi</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu trách nhiệm về các tác vụ đang thực hiện.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="${classInput }"><a data-toggle="tab" href="#input">Nhập thông tin</a></li>
						<li class="${classList }"><a data-toggle="tab" href="#excel">Danh sách địa điểm thi</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in ${classInput }">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatDiaDiemThi.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:150px;">Mã địa điểm thi</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                        		<s:textfield name="ddt.maDDT" cssClass="form-control" readonly="%{(maDDT!=null?'true':'false')}" aria-describedby="basic-addon1" placeholder="Mã địa điểm thi"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Tên địa điểm thi</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                        		<s:textfield name="ddt.tenDDT" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Tên địa điểm thi"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
										<td style="padding-top: 15px;">Hội đồng thi</td>
										<td>
											<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span
													class="glyphicon glyphicon-transfer"></span></span>
												<s:select name="ddt.maHDT" id="maHDT" cssClass="form-control"
													list="listHoiDongThi" aria-describedby="basic-addon1"
													headerKey="-1" headerValue="Chọn hội đồng thi" />
											</div>
										</td>
									</tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Địa chỉ</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                        		<s:textfield name="ddt.diaChi" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Địa chỉ thi"></s:textfield>
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
                                           		<s:if test="maDDT != null">
                                               		<s:submit cssClass="btn btn-info" name="btnUpdate" value="Cập nhật"></s:submit>
                                           		</s:if>
                                           		<s:else>
                                               		<s:submit cssClass="btn btn-info" name="btnAddNew" value="Thêm"></s:submit>
                                           		</s:else>
                                           </div>
                                       </td>
                                    </tr>
                                </table>
							</s:form>
						</div>
						<div id="excel" class="tab-pane fade in ${classList }" style="width:100%;">
							<h3>Danh sách địa điểm thi</h3>
							<s:form action="capNhatListDiaDiemThi.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center;width:100px;">Mã địa điểm thi</th>
							                <th style="text-align: center;">Tên địa điểm thi</th>
							                <th style="text-align: center;">Hội đồng thi</th>
							                <th style="text-align: center;">Địa chỉ</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;">Mã địa điểm thi</th>
							                <th style="text-align: center;">Tên địa điểm thi</th>
							                <th style="text-align: center;">Hội đồng thi</th>
							                <th style="text-align: center;">Địa chỉ</th>
							                <th style="text-align: center;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="maDDT"/></td>
								                <td><s:property value="tenDDT"/></td>
								                <td><s:property value="maHDT"/></td>
								                <td><s:property value="diaChi"/></td>
								                <td align="center"><s:checkbox name="listMaDDT" fieldValue="%{maDDT}" value="false"/></td>
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
									var cboxes = document.getElementsByName('listMaDDT');
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