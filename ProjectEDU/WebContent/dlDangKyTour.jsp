<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tạo tour mới</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<jsp:include page="css/style.html"/>
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
			<!-- <div class="col-md-8"> -->
				    <h2 style="margin-bottom:0px;">Tạo Tour mới</h2>
		            <br><br>
			        <s:form method="post" action="dangKyTour.trip">
				        <div>
				        	<table class="table">
				        		<tr>
				        			<td style="width:80px; padding-top:15px;">Chọn Tỉnh</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="tour.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh" />
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
                                   	<td colspan="2">
                                       	<s:textarea name="tour.tieuDe" id="tieuDe"></s:textarea>
										<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
                               			<script type="text/javascript">CKEDITOR.replace('tieuDe',{
                               				filebrowserBrowseUrl: 'ckfinder/ckfinder.html',
                               				filebrowserImageBrowseUrl: 'ckfinder/ckfinder.html?type=Images',
                               				filebrowserFlashBrowseUrl: 'ckfinder/ckfinder.html?type=Flash',
                               				filebrowserUploadUrl: 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                               				filebrowserImageUploadUrl: 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
                               				filebrowserFlashUploadUrl: 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
                               			}); </script>
                                    </td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Mô tả tổng quan</td>
				        			<td colspan="2">
                                       	<s:textarea name="tour.moTaTongQuan" id="moTaTongQuan"></s:textarea>
										<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
                               			<script type="text/javascript">CKEDITOR.replace('moTaTongQuan'); </script>
                                    </td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Lịch trình</td>
				        			<td colspan="2">
                                       	<s:textarea name="tour.lichTrinh" id="lichTrinh"></s:textarea>
										<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
                               			<script type="text/javascript">CKEDITOR.replace('lichTrinh'); </script>
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
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="tour.soNgay" cssClass="form-control" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Số đêm</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="tour.soDem" cssClass="form-control" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Ghi chú</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
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
			<!-- </div> -->
			<%-- <%@include file="frame/right.jsp"%> --%>
		</div>
	</div>
	<%@include file="frame/footer.jsp"%>
</body>
</html>