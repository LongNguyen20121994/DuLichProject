<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật tài khoản Quản lý ĐH-CĐ</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
	function changeTinh(){
		$.post("showHuyen.trip",
	    {
	    	maTinh: $("#maTinh").val()
	    },
	    function(data,status){
	    	$("#maHuyen").html(data);
	    });
	}
	function changeHuyen(){
		$.post("showXa.trip",
	    {
			maTinh: $("#maTinh").val(),
	    	maHuyen: $("#maHuyen").val()
	    },
	    function(data,status){
	    	$("#maXa").html(data);
	    });
	}
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
        $( "#maTinh" ).change(function() {
        	changeTinh();
        	changeHuyen();
       	});
        $( "#maHuyen" ).change(function() {
        	changeHuyen();
       	});
    });
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				    <h2 style="margin-bottom:0px;">Cập nhật thông tin giảng viên</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="capNhatGiangVien.trip">
				        <div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
				        	<img src='${hinhAnh }' class="img-thumbnail" width="100%" />
				        </div>
				        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
				        <div class="col-md-8">
				        	<table class="table">
				        		<tr>
				        			<td style="width:80px; padding-top:15px;">Số CMND</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
											<s:textfield name="gv.soCMND" cssClass="form-control" readonly="true" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Họ tên</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
											<s:textfield name="gv.hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Ngày sinh</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
											<s:textfield name="gv.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
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
				        			<td style="padding-top:55px;">Hộ khẩu</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
										</div>
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maHuyen" id="maHuyen" cssClass="form-control" list="listHuyen" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
										</div>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maXa" id="maXa" cssClass="form-control" list="listXa" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:10px;">Giới tính</td>
				        			<td>
										<s:radio name="gv.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="true"/>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Số ĐT</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-earphone"></span></span>
											<s:textfield name="gv.soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Email</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
											<s:textfield name="gv.email" cssClass="form-control" placeholder="Địa chỉ email" aria-describedby="basic-addon1" />
											<s:hidden name="gv.maTruong" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td colspan="2" align="right">
				        				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
					        			<s:submit value="Cập nhật" cssClass="btn btn-primary"/>
				        			</td>
				        		</tr>
				        	</table>
				        </div>			        
			        </s:form>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>