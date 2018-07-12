<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật đối tượng ưu tiên</title>
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
				    <h2 style="margin-bottom:0px;">Cập nhật đối tượng ưu tiên</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu trách nhiệm về các tác vụ đang thực hiện.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="${classInput}"><a data-toggle="tab" href="#input">Nhập thông tin</a></li>
						<li class="${classList}"><a data-toggle="tab" href="#excel">Danh sách đối tượng ưu tiên</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in ${classInput}">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatDoiTuongUT.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:110px;">Mã đối tượng</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
                                        		<s:textfield name="dt.maDT" cssClass="form-control" readonly="%{(maDT != null?'true':'false')}" aria-describedby="basic-addon1" placeholder="Mã đối tượng"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Tên khu vực</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="dt.tenDT" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Tên đối tượng"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Điểm cộng</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<input type="number" name="dt.diemCong" step="0.01" value="${dt.diemCong}" class="form-control" aria-describedby="basic-addon1" placeholder="Điểm cộng"/>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;" colspan="2">Ghi chú</td>
                                    </tr>
                                    <tr>
                                    	<td colspan="2">
                                        	<s:textarea name="dt.ghiChu" id="ghiChu"></s:textarea>
											<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
                                			<script type="text/javascript">CKEDITOR.replace('ghiChu'); </script>
                                        </td>
                                    </tr>
                                    <tr>
                                       <td colspan="2">
                                           <div style="width:100%;" align="right">
	                                           	<s:if test="info != null && classInput != null">
					                            	<div align="right">${info.noiDung} </div>
					                            </s:if>
                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
                                           		<s:if test="maDT != null">
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
						<div id="excel" class="tab-pane fade in ${classList}" style="width:100%;">
							<h3>Danh sách đối tượng ưu tiên</h3>
							<s:form action="capNhatListDoiTuongUT.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center; width:100px;">Mã đối tượng</th>
							                <th style="text-align: center;">Tên đối tượng</th>
							                <th style="text-align: center; width:80px;">Điểm cộng</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;">Mã đối tượng</th>
							                <th style="text-align: center;">Tên đối tượng</th>
							                <th style="text-align: center;">Điểm cộng</th>
							                <th style="text-align: center;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="maDT"/></td>
								                <td><s:property value="tenDT"/></td>
								                <td align="center"><s:property value="diemCong"/></td>
								                <td align="center"><s:checkbox name="listMaDT" fieldValue="%{maDT}" value="false"/></td>
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
									var cboxes = document.getElementsByName('listMaDT');
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