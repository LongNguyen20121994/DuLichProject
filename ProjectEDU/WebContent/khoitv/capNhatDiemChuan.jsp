<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật điểm chuẩn</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
    $(document).ready(function() {
        $("#nganh" ).change(function() {
        	$.post("showListKhoiThiNganhDHCD.trip",
   		    {
   				maNganh: $("#nganh").val(),
   				namTuyenSinh: $("#namTuyenSinh").val()
   		    },
   		    function(data,status){
   		    	if($("#nganh").val() != "-1"){
   	   		    	$("#listKhoiThi").html(data);
   		    	} else {
   		    		$("#listKhoiThi").html("");
   		    	}
   		    });
       	});
        $.post("showListKhoiThiNganhDHCD.trip",
	    {
			maNganh: $("#nganh").val(),
			namTuyenSinh: $("#namTuyenSinh").val()
	    },
	    function(data,status){
	    	if($("#nganh").val() != "-1"){
   		    	$("#listKhoiThi").html(data);
	    	} else {
	    		$("#listKhoiThi").html("");
	    	}
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
				    <h2 style="margin-bottom:0px;">Cập nhật điểm chuẩn</h2>
		            <s:a action="downloadDanhSachKhoiThiNganhDHCD.trip"><small><small><i>Bấm vào đây để tải về danh sách Khối thi - Ngành đã có để cập nhật.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#input">Nhập thông tin</a></li>
						<li><a data-toggle="tab" href="#excel">Tải lên file excel</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatDiemChuan.trip" method="post">
								<table class="table">
									<tr>
                                        <th style="padding-top: 15px; width:130px;">Năm tuyển sinh</th>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="namTS" id="namTuyenSinh" readonly="true" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Năm tuyển sinh"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="padding-top: 15px; width:120px;">Ngành</th>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
                                        		<s:select name="maNganh" list="listNganh" id="nganh" cssClass="form-control" aria-describedby="basic-addon1"  headerKey="-1" headerValue="Chọn ngành đào tạo"></s:select>
                                        	</div>
                                        </td>
                                    </tr>
                                </table>
                                <div id='listKhoiThi'>
	           					</div>
	           					<div style="width:100%;" align="right">
		                       		<s:reset cssClass="btn btn-info" onclick="setDefault();" value="Làm mới"></s:reset>
		                           	<s:submit cssClass="btn btn-info" onclick="return checkForm();" value="Cập nhật"></s:submit>
		                        </div>
							</s:form>
						</div>
						<div id="excel" class="tab-pane fade" style="width:100%;">
							<h3>Upload Danh sách</h3>
							<s:form action="capNhatDiemChuanExcel.trip" method="post" enctype="multipart/form-data">
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