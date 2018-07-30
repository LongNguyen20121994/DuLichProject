<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý Khách Sạn</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
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
						<li class="${classInput}"><a data-toggle="tab" href="#input">Thông tin Khách Sạn</a></li>
						<li class="${classList}"><a data-toggle="tab" href="#listds">Danh sách Khách Sạn</a></li>
					</ul>
		            <br><br>
		            <div class="tab-content">
						<div id="input" class="tab-pane fade in ${classInput}">
					        <s:form method="post" action="taoKhackSan.trip">
						        <div>
						        	<table class="table">
						        		<tr>
						        			<td style="padding-top:15px;width:100px;">Mã Khách Sạn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.maKS" cssClass="form-control" readonly="true" aria-describedby="basic-addon1"></s:textfield>
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Tên Khách Sạn</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.tenKS" cssClass="form-control" placeholder="Nhập tên Khách Sạn" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Hình ảnh</td>
						        			<td colspan="2">
						        				<div id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
										        	<img src="images/addImage.jpg" class="img-thumbnail" width="20%" />
										        </div>
										        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Tỉnh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select list="listTinh" name="ks.maTinh" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn Tỉnh"/>
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Địa chỉ</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-map-marker"></span></span>
													<s:textfield name="ks.diaChi" cssClass="form-control" placeholder="Nhập địa chỉ" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Sao</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.sao" cssClass="form-control" placeholder="Nhập số sao" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
	                                    <tr>
						        			<td style="padding-top:15px;">Mô tả</td>
		                                    <td colspan="2">
		                                       	<s:textarea name="ks.moTa" id="moTa"></s:textarea>
												<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
		                               			<script type="text/javascript">
		                               				CKEDITOR.replace('moTa',{
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
	                                       <td colspan="2">
	                                           <div style="width:100%;" align="right">
		                                           	<s:if test="info != null && classInput != null">
						                            	<div align="right">${info.noiDung} </div>
						                            </s:if>
	                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
	                                           		<s:if test="ks.maKS != null">
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
							<s:form action="capNhatKhachSan.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center; width:100px;">Mã Khách Sạn</th>
							                <th style="text-align: center;">Tên Khách Sạn</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center; width:100px;">Mã Khách Sạn</th>
							                <th style="text-align: center;">Tên Khách Sạn</th>
							                <th style="text-align: center;width:65px;">Cập nhật</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="list" >
							       			<tr>
								                <td align="center"><s:property value="maKS"/></td>
								                <td><s:property value="tenKS"/></td>
								                <td align="center"><s:checkbox name="listMaKs" fieldValue="%{maKS}" value="false"/></td>
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
									var cboxes = document.getElementsByName('listMaKs');
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