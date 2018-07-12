<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật xét tuyển</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
    $(document).ready(function() {
        $("#nganh" ).change(function() {
        	$.post("showListKhoiThiXetTuyen.trip",
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
        $.post("showListKhoiThiXetTuyen.trip",
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
				    <h2 style="margin-bottom:0px;">Cập nhật xét tuyển</h2>
		            <s:a href=""><small><small><i>Chọn các ngành để điều chỉnh hệ số các môn xét tuyển</i></small></small></s:a>
		            <br><br>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatXetTuyenNganhDHCD.trip" method="post">
								<table class="table">
									<tr>
                                        <td style="padding-top: 15px; width:100px;">Năm tuyển sinh</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="namTS" id="namTuyenSinh" readonly="true" cssClass="form-control" aria-describedby="basic-addon1"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px; width:120px;">Ngành</td>
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
					</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>