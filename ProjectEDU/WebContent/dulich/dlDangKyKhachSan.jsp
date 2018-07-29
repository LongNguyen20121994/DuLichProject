<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Tạo Khách Sạn</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
	<jsp:include page="../css/style.html"/>

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
				    <h2 style="margin-bottom:0px;">Tạo Khách Sạn</h2>
		            <br><br>
		            <div class="tab-content">
						<div id="input" class="tab-pane fade in active">
					        <s:form method="post" action="dangKyKhachSan.trip">
						        <div>
						        	<table class="table">
						        		<tr>
						        			<td style="width:80px; padding-top:15px;">Chọn Tỉnh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ks.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh" />
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
						        			<td style="width:80px; padding-top:15px;">Tên Khách Sạn</td>
		                                   	<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.tenKS" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Địa chỉ</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.diaChi" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Sao</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.sao" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Mô tả</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ks.moTa" cssClass="form-control" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td colspan="2" align="right">
						        				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
							        			<s:submit value="Lưu Khách Sạn" cssClass="btn btn-primary"/>
						        			</td>
						        		</tr>
						        	</table>
						        </div>			        
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