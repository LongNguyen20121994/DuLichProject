<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng ký giáo viên THPT</title>
<jsp:include page="../css/style.html"/>
<script src="js/validateQuang.js"></script>
<script lang="Javascript">
	//ajax
	function changeSoCMND(){
		if($("#soCMND").val() > 8 ){
			$.post("checkGiaoVien.edu",
		    {
		    	SoCMND: $("#soCMND").val()
		    },
		    function(data,status){
		    	if($.trim(data) == "invalid"){
			    	$("#soCMND").css("border-color", "red");
			    	$("#errorSoCMND").text("Số chứng minh nhân dân đã tồn tại!");
		    	} else {
		    		$("#errorSoCMND").text("");
		    		$("#soCMND").css("border-color", "#ccc");
		    	}
		    });
		}
	}
	
	function changeTinh(){
		$.post("showHuyen.edu",
	    {
	    	maTinh: $("#maTinh").val()
	    },
	    function(data,status){
	    	$("#maHuyen").html(data);
	    });
	}
	
	function changeTinhTHPT(){
		$.post("showTHPT.edu",
	    {
	    	maTinh: $("#maTinhTHPT").val()
	    },
	    function(data,status){
	    	$("#truongTHPT").html(data);
	    });
	}
	
	function changeHuyen(){
		$.post("showXa.edu",
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
            'action' : 'uploadHinhAnh.edu',
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
       	
        $( "#guiMa" ).click(function() {
        	$("#ketQua").html("Thông tin đang được xác nhận. vui lòng chờ ít phút");
        	$.post("sendMailXacNhan.edu",
   		    {
   		    	email: $("#email").val()
   		    },
   		    function(data,status){
   		    	$("#ketQua").html(data);
   		    });
       	});
    });
</script>

</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				    <h2 style="margin-bottom:0px;">Đăng ký thông tin giáo viên</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="dangKyGiaoVien.edu">
				        <div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
				        	<img src="images/addImage.jpg" class="img-thumbnail" width="100%" />
				        </div>
				        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
				        <div class="col-md-8">
				        	<table class="table">
				        		<tr>
				        			<td style="width:86px; padding-top:15px;">Số CMND<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
											<input type="number" name="gv.soCMND" id="soCMND" min="1" onchange="changeSoCMND();" maxlength="10" class="form-control" placeholder="Số chứng minh thư" aria-describedby="basic-addon1" />
										</div>
										<div id="errorSoCMND" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Họ tên<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
											<s:textfield name="gv.hoTen" id="hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
										</div>
										<div id="errorHoTen" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Ngày sinh<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
											<s:textfield name="gv.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
											<div id="errorNgaySinh" style="color:red;"></div>
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
											<s:select name="gv.maHuyen" id="maHuyen" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
										</div>
										<div id="errorHuyen" style="color:red;"></div>		
												
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maXa" id="maXa" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:10px;">Giới tính<font color="red">*</font></td>
				        			<td>
										<s:radio name="gv.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="true"/>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Số ĐT<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-earphone"></span></span>
											<s:textfield name="gv.soDT" id="soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
										</div>
										<div id="errorSoDT" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Email<font color="red">*</font></td>
				        			<td>
				        				<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
											<s:textfield name="gv.email" id="email" cssClass="form-control" placeholder="Địa chỉ email" aria-describedby="basic-addon1" />
											<span class="input-group-addon btn btn-primary" style="cursor: pointer;" id="guiMa">Gửi mã</span>
										</div>
										<div class="input-group" style="margin-top: 5px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock"></span></span>
											<s:password name="xacNhan" cssClass="form-control" placeholder="Mã xác nhận email" aria-describedby="basic-addon1" />
										</div>
										<span id="ketQua" style="font-size: small; color: red;"></span>
										<div id="errorEmail" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:25px;">Trường<font color="red">*</font></td>
				        			<td>
				        				<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select id="maTinhTHPT" name="gv.maTinhTHPT" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
										</div>
										<div id="errorTinhTHPT" style="color:red;"></div>
										
					        			<div class="input-group" style="margin-top:5px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="gv.maTruongTHPT" id="truongTHPT" cssClass="form-control" list="listTHPT" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường THPT " />
										</div>
										<div id="errorTruong" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td colspan="2">
					        			<s:submit value="Đăng ký" cssClass="btn btn-primary pull-right" onclick="return checkDKGV();"/>
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