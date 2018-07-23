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
		$.post("showHuyen.edu",
	    {
	    	maTinh: $("#maTinh").val()
	    },
	    function(data,status){
	    	$("#maHuyen").html(data);
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
	
	function changeTinhTHPT10(){
		$.post("showTHPT.edu",
	    {
	    	maTinh: $("#maTinhTHPT10").val()
	    },
	    function(data,status){
	    	$("#maTruong10").html(data);
	    });
	}
	function changeTinhTHPT11(){
		$.post("showTHPT.edu",
	    {
	    	maTinh: $("#maTinhTHPT11").val()
	    },
	    function(data,status){
	    	$("#maTruong11").html(data);
	    });
	}
	
	function changeTinhTHPT12(){
		$.post("showTHPT.edu",
	    {
	    	maTinh: $("#maTinhTHPT12").val()
	    },
	    function(data,status){
	    	$("#maTruong12").html(data);
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
        $( "#maTinhTHPT10" ).change(function() {
        	changeTinhTHPT10();
       	});
        $( "#maTinhTHPT11" ).change(function() {
        	changeTinhTHPT11();
       	});
        $( "#maTinhTHPT12" ).change(function() {
        	changeTinhTHPT12();
       	});
    });
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				    <h2 style="margin-bottom:0px;">Cập nhật thông tin thí sinh tự do</h2>
		            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="updateInfoThiSinh.edu" >
				        <div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
				        	<img src="${ts.hinhAnh}" class="img-thumbnail" width="100%" />
				        </div>
				        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
				        <div class="col-md-8">
				        	<table class="table">
				        		<tr>
				        			<td style="width:80px; padding-top:15px;">Số CMND<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
					        				<s:hidden name="SoCMNDUpdate"></s:hidden>
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
											<s:textfield name="ts.soCMND" cssClass="form-control" readonly="true" aria-describedby="basic-addon1" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Họ tên<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.hoTen" id="hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
										</div>
										<div id="errorHoTen" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Ngày sinh<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
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
											<s:select name="ts.maTinh" id="maTinh" value="ts.maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
										</div>
										<div id="errorTinh" style="color:red;"></div>
										
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="ts.maHuyen" id="maHuyen" value="ts.maHuyen" cssClass="form-control" list="listHuyen" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
										</div>
										<div id="errorHuyen" style="color:red;"></div>
										
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="ts.maXa" id="maXa" value="ts.maXa" cssClass="form-control" list="listXa" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:10px;">Giới tính<font color="red">*</font></td>
				        			<td>
										<s:radio name="ts.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="ts.gioiTinh"/>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Số ĐT<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.soDT" id="soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
										</div>
										<div id="errorSoDT" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Email<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.email" id="email" cssClass="form-control" placeholder="Email của bạn" aria-describedby="basic-addon1" />
										</div>
										<div id="errorEmail" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Dân tộc<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="ts.danToc" id="danToc" list="listDanToc" value="ts.danToc" cssClass="form-control"  aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn dân tộc" />
										</div>
										<div id="errorDanToc" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Nơi sinh<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.noiSinh" id="noiSinh" cssClass="form-control" placeholder="Nơi sinh" aria-describedby="basic-addon1" />
										</div>
										<div id="errorNoiSinh" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Địa chỉ liện hệ<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.diaChi" id="diaChi" cssClass="form-control" placeholder="Địa chỉ liên hệ" aria-describedby="basic-addon1" />
										</div>
										<div id="errorDiaChi" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Năm tốt nghiệp<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:textfield name="ts.namTN" id="namTN" cssClass="form-control" placeholder="Năm tốt nghiệp THPT hoặc tương đương" aria-describedby="basic-addon1" />
										</div>
										<div id="errorNamTN" style="color:red;"></div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;">Đối tượng ưu tiên</td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:select name="ts.doiTuongUT" value="ts.doiTuongUT" cssClass="form-control" list="listDoiTuongUT" aria-describedby="basic-addon1" headerKey="-1" headerValue="Đối tượng ưu tiên" />
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td style="padding-top:15px;" colspan="2">Nơi học THPT hoặc tương đương</td>
				        		</tr>
				        		
				        		<!-- Lớp 10 THPT -->
				        		<tr>
				        			<td style="padding-top:35px;">Lớp 10<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr10 != null}">
												<s:select name="tstr10.maTinhTHPT" id="maTinhTHPT10" value="tstr10.maTinhTHPT" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
											</s:if>
											<s:else>
												<s:select name="tstr10.maTinhTHPT" id="maTinhTHPT10" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />										
											</s:else>
										</div>
										<div id="errorTinh10" style="color:red;"></div>
										
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr10 != null}">
												<s:select name="tstr10.maTruong" id="maTruong10" value="tstr10.maTruong" cssClass="form-control" list="listTruong10" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:if>
											<s:else>
												<s:select name="tstr10.maTruong" id="maTruong10" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:else>
										</div>
										<div id="errorTruong10" style="color:red;"></div>
									</td>
				        		</tr>
				        		
				        		<!-- Lớp 11 THPT -->
				        		<tr>
				        			<td style="padding-top:35px;">Lớp 11<font color="red">*</font></td>
				        			<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr11 != null}">
												<s:select name="tstr11.maTinhTHPT" id="maTinhTHPT11" value="tstr11.maTinhTHPT" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
											</s:if>
											<s:else>
												<s:select name="tstr11.maTinhTHPT" id="maTinhTHPT11" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />										
											</s:else>
										</div>
										<div id="errorTinh11" style="color:red;"></div>
										
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr11 != null}">
												<s:select name="tstr11.maTruong" id="maTruong11" value="tstr11.maTruong" cssClass="form-control" list="listTruong11" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:if>
											<s:else>
												<s:select name="tstr11.maTruong" id="maTruong11" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:else>
										</div>
										<div id="errorTruong11" style="color:red;"></div>
										
									</td>
								</tr>
											<!-- Lớp 12 THPT -->
								<tr>
									<td style="padding-top:35px;">Lớp 12<font color="red">*</font></td>
									<td>
					        			<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr12 != null}">
												<s:select name="tstr12.maTinhTHPT" id="maTinhTHPT12" value="tstr12.maTinhTHPT" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
											</s:if>
											<s:else>
												<s:select name="tstr12.maTinhTHPT" id="maTinhTHPT12" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />										
											</s:else>
										</div>
										<div id="errorTinh12" style="color:red;"></div>
										
										<div class="input-group" style="margin:5px 0px;">
											<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
											<s:if test="%{tstr12 != null}">
												<s:select name="tstr12.maTruong" id="maTruong12" value="tstr12.maTruong" cssClass="form-control" list="listTruong12" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:if>
											<s:else>
												<s:select name="tstr12.maTruong" id="maTruong12" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
											</s:else>
										</div>
										<div id="errorTruong12" style="color:red;"></div>
										
				        			</td>
				        		</tr>
				        		<tr>
				        			<td colspan="2">
				        			<s:if test="soCMNDUpdate != null">
					        				<s:hidden name="soCMNDUpdate"></s:hidden>
				        					<s:submit name="showDangKyDuThiTHP" value="Cập nhật môn thi" cssClass="btn btn-primary pull-right" cssStyle="margin-left:5px;" />
				        				</s:if>
					        			<s:submit value="Lưu" cssClass="btn btn-primary pull-right" name="luu" cssStyle="width:80px;" onclick="return checkDKTS();" />
				        				<s:reset value="Làm mới" cssClass="btn btn-primary pull-right" cssStyle="margin-right:5px;"/>
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