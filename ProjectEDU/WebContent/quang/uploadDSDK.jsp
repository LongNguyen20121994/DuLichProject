<%@page contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Upload danh sách thí sinh</title>
<jsp:include page="../css/style.html"/>
<script src="js/validateQuang.js"></script>
<script lang="Javascript">

	function changeSoCMND(){
		if($("#soCMND").val() > 8 ){
			$.post("checkThiSinh.trip",
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
	
	function changeTinhTHPT10(){
		$.post("showTHPT.trip",
	    {
	    	maTinh: $("#maTinhTHPT10").val()
	    },
	    function(data,status){
	    	$("#maTruong10").html(data);
	    });
	}
	function changeTinhTHPT11(){
		$.post("showTHPT.trip",
	    {
	    	maTinh: $("#maTinhTHPT11").val()
	    },
	    function(data,status){
	    	$("#maTruong11").html(data);
	    });
	}
	
	function changeTinhTHPT12(){
		$.post("showTHPT.trip",
	    {
	    	maTinh: $("#maTinhTHPT12").val()
	    },
	    function(data,status){
	    	$("#maTruong12").html(data);
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
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home">Thêm thí sinh đăng ký</a></li>
						<li><a data-toggle="tab" href="#menu1">Upload danh sách đăng ký</a></li>
					</ul>
					<div class="col-md-12">
					
					
					<!-- Tab 1 -->
					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
							<h2 style="margin-bottom:0px;">Thông tin thí sinh</h2>
				            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
				            <br><br>
					        <s:form method="post" action="dangKyTSTuDo.trip">
					        	<s:hidden name="ts.nguoiDK" value="#session.soCMND"></s:hidden>
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
													<s:textfield name="ts.soCMND" id="soCMND" cssClass="form-control" placeholder="Số chứng minh thư" aria-describedby="basic-addon1" />
												</div>
												<div id="errorSoCMND" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Họ tên<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
													<s:textfield name="ts.hoTen" id="hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
												</div>
												<div id="errorHoTen" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Ngày sinh<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
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
													<s:select name="ts.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div id="errorTinh" style="color:red;"></div>
												
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.maHuyen" id="maHuyen" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
												</div>
												<div id="errorHuyen" style="color:red;"></div>
												
												<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.maXa" id="maXa" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:10px;">Giới tính<font color="red">*</font></td>
						        			<td>
												<s:radio name="ts.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="true"/>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số ĐT<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-earphone"></span></span>
													<s:textfield name="ts.soDT" id="soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
												</div>
												<div id="errorSoDT" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Email<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
													<s:textfield name="ts.email" id="email" cssClass="form-control" placeholder="Địa chỉ email" aria-describedby="basic-addon1" />
												</div>
												<div id="errorEmail" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Dân tộc<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.danToc" id="danToc" cssClass="form-control" list="listDanToc" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn dân tộc" />
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
						        			<td style="padding-top:15px;">Địa chỉ<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.diaChi" id="diaChi" cssClass="form-control" placeholder="Địa chỉ liên hệ" aria-describedby="basic-addon1" />
												</div>
												<div id="errorDiaChi" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Năm TN<font color="red">*</font></td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.namTN" id="namTN" cssClass="form-control" placeholder="Năm tốt nghiệp THPT hoặc tương đương" aria-describedby="basic-addon1" />
												</div>
												<div id="errorNamTN" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Đối tượng</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.doiTuongUT" cssClass="form-control" list="listDoiTuongUT" aria-describedby="basic-addon1" headerKey="-1" headerValue="Đối tượng ưu tiên" />
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
													<s:select name="tstr10.maTinhTHPT" id="maTinhTHPT10" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div id="errorTinh10" style="color:red;"></div>
												
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr10.maTruong" id="maTruong10" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
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
													<s:select name="tstr11.maTinhTHPT" id="maTinhTHPT11" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div id="errorTinh11" style="color:red;"></div>
												
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr11.maTruong" id="maTruong11" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
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
													<s:select name="tstr12.maTinhTHPT" id="maTinhTHPT12" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div id="errorTinh12" style="color:red;"></div>
												
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr12.maTruong" id="maTruong12" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
												</div>
												<div id="errorTruong12" style="color:red;"></div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td colspan="2">
							        			<s:submit value="Đăng ký" cssClass="btn btn-primary pull-right" onclick="return checkDKTS();"/>
						        				<s:reset value="Làm mới" cssClass="btn btn-primary pull-right" cssStyle="margin-right:15px;"/>
						        			</td>
						        		</tr>
						        	</table>
						        </div>			        
					        </s:form>
						</div>
						
						<script type="text/javascript">
							function checkForm(){
								if($("#danhSachDK").val() == "" && $("#listAnh").val() == ""){
									$("#errorDK").text("Bạn chưa chọn file upload!");
									return false;
								}
							}
						</script>
						
						<!-- Tab 00000000000000002 -->
						<div id="menu1" class="tab-pane fade" style="width:100%;">
							<h2 style="margin-bottom:0px;">Upload danh sách thí sinh </h2>
							<s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
							<s:form action="uploadDSDK.trip" method="post" enctype="multipart/form-data">
								<div class="input-group" style="margin-top:20px ">
								
									<div id="errorDK" style="color:red;"></div>
									<table class="table" style="margin-bottom: 5px;">
                                        <tr>
                                            <td>Danh sách đăng ký</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-floppy-open"></span></span>
                                            		<s:file name="danhSachDK" id="danhSachDK" cssClass="form-control"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Danh sách ảnh thí sinh</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                            		<s:file name="listAnh" id="listAnh" cssClass="form-control" aria-describedby="basic-addon1"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td colspan="2">
                                               <div style="width:100%;" align="right">
                                                   <s:submit cssClass="btn btn-info" value="Upload" onclick="return checkForm();" />
                                               </div>
                                           </td>
                                        </tr>
                                    </table>
                                    <div>
                                    	<div style="float:left; color: red">Lưu ý: </div>
                                    	<div style="color: red; float:left; margin-left: 2px;">
                                    		File danh sách là file excel 2003 hoặc 2007(.xls hoặc .xlsx).<br />
                                    		File ảnh thí sính phải là file rar(.rar).
                                    	</div>
                                    </div>
								</div>
							</s:form>
						</div>
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