<%@page contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cao đẳng công nghệ-Ðại học Ðà Nẵng</title>
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
						        			<td style="width:80px; padding-top:15px;">Số CMND</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
													<s:textfield name="ts.soCMND" cssClass="form-control" placeholder="Số chứng minh thư" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Họ tên</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.hoTen" cssClass="form-control" placeholder="Họ và tên" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Ngày sinh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
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
													<s:select name="ts.maTinh" id="maTinh" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.maHuyen" id="maHuyen" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn huyện (quận)" />
												</div>
												<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.maXa" id="maXa" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn xã (phường)" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:10px;">Giới tính</td>
						        			<td>
												<s:radio name="ts.gioiTinh" list="#{'true':'&nbsp;Nam','false':'&nbsp;Nữ'}" value="true"/>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Số ĐT</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.soDT" cssClass="form-control" placeholder="Số điện thoại" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Email</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.email" cssClass="form-control" placeholder="Email của bạn" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Dân tộc</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="ts.danToc" cssClass="form-control" list="listDanToc" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn dân tộc" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Nơi sinh</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.noiSinh" cssClass="form-control" placeholder="Nơi sinh" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Địa chỉ liện hệ</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.diaChi" cssClass="form-control" placeholder="Địa chỉ liên hệ" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Năm tốt nghiệp</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:textfield name="ts.namTN" cssClass="form-control" placeholder="Năm tốt nghiệp THPT hoặc tương đương" aria-describedby="basic-addon1" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td style="padding-top:15px;">Đối tượng ưu tiên</td>
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
						        			<td style="padding-top:35px;">Lớp 10</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr10.maTinh" id="maTinhTHPT10" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr10.maTruong" id="maTruong10" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
												</div>
											</td>
						        		</tr>
						        		
						        		<!-- Lớp 11 THPT -->
						        		<tr>
						        			<td style="padding-top:35px;">Lớp 11</td>
						        			<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr11.maTinh" id="maTinhTHPT11" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr11.maTruong" id="maTruong11" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
												</div>
											</td>
										</tr>
													<!-- Lớp 12 THPT -->
										<tr>
											<td style="padding-top:35px;">Lớp 12</td>
											<td>
							        			<div class="input-group">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr12.maTinh" id="maTinhTHPT12" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn tỉnh (thành phố)" />
												</div>
												<div class="input-group" style="margin:5px 0px;">
													<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
													<s:select name="tstr12.maTruong" id="maTruong12" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường" />
												</div>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td colspan="2">
							        			<s:submit value="Đăng ký" cssClass="btn btn-primary pull-right"/>
						        				<s:reset value="Làm mới" cssClass="btn btn-primary pull-right" cssStyle="margin-right:15px;"/>
						        			</td>
						        		</tr>
						        	</table>
						        </div>			        
					        </s:form>
						</div>
						
						
						<!-- Tab 00000000000000002 -->
						<div id="menu1" class="tab-pane fade" style="width:100%;">
							<h2 style="margin-bottom:0px;">Thông tin thí sinh</h2>
							<s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
							<s:form action="uploadDSDK.trip" method="post" enctype="multipart/form-data">
								<div class="input-group" style="margin-top:20px ">
									<table class="table">
                                        <tr>
                                            <td>Danh sách đăng ký</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-floppy-open"></span></span>
                                            		<s:file name="danhSachDK" cssClass="form-control"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Danh sách ảnh thí sinh</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                            		<s:file name="listAnh" cssClass="form-control" aria-describedby="basic-addon1"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td colspan="2">
                                               <div style="width:100%;" align="right">
                                                   <s:submit cssClass="btn btn-info" value="Upload"></s:submit>
                                               </div>
                                           </td>
                                        </tr>
                                    </table>
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