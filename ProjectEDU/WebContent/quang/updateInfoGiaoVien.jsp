<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật thông tin cá nhân</title>
<jsp:include page="../css/style.html"/>
<script src="js/validateQuang.js"></script>
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
	
	function changeTinhTHPT(){
		$.post("showTHPT.trip",
	    {
	    	maTinh: $("#maTinhTHPT").val()
	    },
	    function(data,status){
	    	$("#truongTHPT").html(data);
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
       	$( "#maTinhTHPT" ).change(function() {
        	changeTinhTHPT();
       	});
    });
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				    <h2 style="margin-bottom:0px;">Cập nhật thông tin giáo viên</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="updateInfoGiaoVien.trip">
				        <div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
				        	<img src="${gv.hinhAnh}" class="img-thumbnail" width="100%" />
				        </div>
				        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
				        <div class="col-md-8">
				        	<table class="table">
				        		<tr>
				        			<td style="width:83px; padding-top:15px;">Số CMND<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
											<s:textfield name="gv.soCMND" readonly="true" cssClass="form-control" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Họ tên<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="gv.hoTen" id="hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
										</div>
										<div id="errorHoTen" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Ngày sinh<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="gv.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
										</div>
										<div id="errorDOB" style="color:red;"></div>
										
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
				        			<td style="padding-top:55px;">Hộ khẩu<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
										</div>
										<div id="errorTinh" style="color:red;"></div>
										
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maHuyen" id="maHuyen" cssClass="form-control" list="listHuyen" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
										</div>
										<div id="errorHuyen" style="color:red;"></div>
										
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maXa" id="maXa" cssClass="form-control" list="listXa" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:10px;">Giới tính<font color="red">*</font></td>
				        			<td>
										<s:radio name="gv.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="gv.gioiTinh"/>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Số ĐT<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="gv.soDT" id="soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
										</div>
										<div id="errorSoDT" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Email<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="gv.email" id="email" cssClass="form-control" placeholder="Email của bạn" aria-describedby="basic-addon1" />
										</div>
										<div id="errorEmail" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:25px; text-align:center;">Trường THPT<font color="red">*</font></td>
				        			<td>
				        				<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select id="maTinhTHPT" name="gv.maTinhTHPT" id="maTinhTHPT" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn Tỉnh (thành phố)" />
										</div>
										<div id="errorTinhTHPT" style="color:red;"></div>
										
					        			<div class="input-group" style="margin-top:5px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maTruongTHPT" id="truongTHPT" cssClass="form-control" list="listTHPT" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường THPT" />
										</div>
										<div id="errorTruong" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td colspan="2">
					        			<s:submit value="Lưu" cssClass="btn btn-primary pull-right" onclick="return checkDKGV();"/>
				        				<s:reset value="Làm mới" cssClass="btn btn-primary pull-right" cssStyle="margin-right:15px;"/>
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