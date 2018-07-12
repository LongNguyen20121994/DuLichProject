<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thêm khối thi - Ngành</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
	function checkKhoiThi(){
		$.post("checkKhoiThi.trip",
	    {
	    	maNganh: $("#nganh").val(),
	    	maKhoi: $("#khoiThi").val(),
	    	namTuyenSinh: $("#namTuyenSinh").val()
	    },
	    function(data,status){
	    	if($.trim(data) == "invalid"){
		    	$("#khoiThi").css("border-color", "red");
		    	alert("Khối thi đã tồn tại!");
	    	} else {
	    		$("#khoiThi").css("border-color", "#ccc");
	    	}
	    });
	}
	function checkForm(){
		if($("#nganh").val() == "-1"){
			alert("Chưa chọn ngành thi!");
			return false;
		}
		if(document.getElementById("khoiThi").style.borderColor == "red"){
			alert("Khối thi đã tồn tại!");
			return false;
		}
	}
	function setDefault(){
		$("#khoiThi").css("border-color", "#ccc");
		$("#listKhoiThi").html("");
	}
    $(document).ready(function() {
        $("#nganh" ).change(function() {
        	$.post("showListKhoiThi.trip",
   		    {
   				maNganh: $("#nganh").val(),
   				namTuyenSinh: $("#namTuyenSinh").val()
   		    },
   		    function(data,status){
   		    	$("#listKhoiThi").html(data);
   		    });
        	checkKhoiThi();
       	});
        $.post("showListKhoiThi.trip",
	    {
			maNganh: $("#nganh").val(),
			namTuyenSinh: $("#namTuyenSinh").val()
	    },
	    function(data,status){
	    	$("#listKhoiThi").html(data);
	    });
        $("#khoiThi" ).change(function() {
        	checkKhoiThi();
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
				    <h2 style="margin-bottom:0px;">Thêm khối thi - Ngành</h2>
		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách Khối thi - Ngành đã có để cập nhật.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#input">Nhập thông tin</a></li>
						<li><a data-toggle="tab" href="#excel">Tải lên file excel</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="themKhoiThiNganhDHCD.trip" method="post">
								<table class="table">
									<tr>
                                        <td style="padding-top: 15px; width:100px;">Năm tuyển sinh</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="ktn.namTuyenSinh" id="namTuyenSinh" readonly="true" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Năm tuyển sinh"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px; width:120px;">Ngành</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
                                        		<s:select name="ktn.maNganh" list="listNganh" id="nganh" cssClass="form-control" aria-describedby="basic-addon1"  headerKey="-1" headerValue="Chọn ngành đào tạo"></s:select>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px; width:100px;">Khối thi</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
                                        		<s:select name="ktn.maKhoi" list="listKhoiThi" id="khoiThi" cssClass="form-control" aria-describedby="basic-addon1"  headerKey="-1" headerValue="Chọn khối thi"></s:select>
                                        	</div>
                                        </td>
                                    </tr>
                                </table>
                                <table class='table table-bordered' id='listKhoiThi'>
	           				
	           					</table>
	           					<div style="width:100%;" align="right">
		                       		<s:reset cssClass="btn btn-info" onclick="setDefault();" value="Làm mới"></s:reset>
		                           	<s:submit cssClass="btn btn-info" onclick="return checkForm();" value="Cập nhật"></s:submit>
		                        </div>
							</s:form>
						</div>
						<div id="excel" class="tab-pane fade" style="width:100%;">
							<h3>Upload Danh sách</h3>
							<s:form action="themKhoiThiNganhDHCDExcel.trip" method="post" enctype="multipart/form-data">
								<div class="input-group">
									<table class="table">
                                        <tr>
                                            <td style="padding-top: 15px; width:110px;">Chọn file excel</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                            		<s:file name="file" cssClass="form-control" aria-describedby="basic-addon1"></s:file>
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
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>