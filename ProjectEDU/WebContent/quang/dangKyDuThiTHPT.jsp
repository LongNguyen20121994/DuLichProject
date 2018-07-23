<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng ký môn thi</title>
<jsp:include page="../css/style.html"/>

<script lang="Javascript">
	var cumThi;

	function checkCumThiDKDT(){
		$.post("checkCumThiDKDT.edu",
	    {
			namTS: $("#namTS").val(),
			maCumThi: $("#cumThi").val(),
			soCMNDUpdate: $("#soCMNDUpdate").val()
	    },
	    function(data,status){
	    	if($.trim(data) == "invalid"){
		    	$("#cumThi").css("border-color", "red");
		    	$("#errorCumThi").text("Bạn chỉ được đăng ký thi ở một cụm thi!");
	    	} else {
	    		$("#errorCumThi").text("");
	    		$("#cumThi").css("border-color", "#ccc");
	    	}
	    });
	}
	
	function checkMonThiDKDT(){
		$.post("checkMonThiDKDT.edu",
	    {
			namTS: $("#namTS").val(),
	    	maMon: $("#maMon").val(),
	    	soCMNDUpdate: $("#soCMNDUpdate").val()
	    },
	    function(data,status){
	    	if($.trim(data) == "invalid"){
		    	$("#maMon").css("border-color", "red");
		    	$("#errorMonThi").text("Môn thi đã tồn tại!");
	    	}
	    	else{
	    		$("#maMon").css("border-color", "#ccc");
	    		$("#errorMonThi").text("");
	    	}
	    });
	}
	
	function showMonThi(){
        $.post("showListMonDKDT.edu",
	    {
			namTS: $("#namTS").val(),
			soCMNDUpdate: $("#soCMNDUpdate").val()
			
	    },
	    function(data,status){
	    	$("#listMonThi").html(data);
	    });
	}
	
	function checkForm(){
		var list = new Array();
		$("input:checkbox[name=listMT]:checked").each(function(){
			list.push($(this).val());
		});
		
		
		if($("#cumThi").val() == "-1"){
			$("#errorCumThi").text("Cụm thi không được để trống!");
			$("#cumThi").css("border-color", "red");
			$("#cumThi").focus();
			return false;
		}
		
		if($("#maMon").val() == "-1" && list.length == 0){
			$("#errorMonThi1").text("Bạn chưa chọn môn thi!");
			return false;
		}
		
		if(document.getElementById("cumThi").style.borderColor == "red"){
			$("#cumThi").focus();
			return false;
		}
		if(document.getElementById("maMon").style.borderColor == "red"){
			$("#maMon").focus();
			return false;
		}
	}
	
	function setDefault(){
		$("#cumThi").css("border-color", "#ccc");
		$("#maMon").css("border-color", "#ccc");
		showMonThi();
		
	}
	
    $(document).ready(function() {
    	$("#cumThi" ).change(function() {
    		checkCumThiDKDT();
       	});
        $("#maMon" ).change(function() {
        	checkMonThiDKDT();
       	});
        showMonThi();
    });
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Đăng ký môn thi</h2>
<%-- 		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách ngành đã có để cập nhật.</i></small></small></s:a> --%>
		            <br><br>
					<s:form action="dangKyDuThiTHPT.edu" method="post">
						<s:hidden name="soCMNDUpdate" id="soCMNDUpdate"></s:hidden>
						<table class="table">
                           <tr>
                               <td style="padding-top: 15px; width:110px;">Cụm thi</td>
                               <td>
	                               	<div class="input-group">
	                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
	                               		<s:select name="dkdt.maCumThi" id="cumThi" list="listCumThi" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn cụm thi"/>
	                               	</div>
	                               	<div id="errorCumThi" style="color:red;"></div>
                               </td>
                           </tr>
                           <tr>
                               <td style="padding-top: 15px;">Thêm Môn thi</td>
                               <td>
	                               	<div class="input-group">
	                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
	                               		<s:select name="ctDKDT.maMonThi" list="listMon" id="maMon" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn môn thi"/>
	                               	</div>
	                               	<div id="errorMonThi" style="color:red;"></div>
	                               	
	                               	<script type="text/javascript">
									 	cumThi = $("#cumThi").val();
									</script>
                               </td>
                           </tr>
                           <tr>
                               <td style="padding-top: 15px;">Kỳ thi</td>
                               <td>
	                               	<div class="input-group" style="width:100%;">
	                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock"></span></span>
	                               		<s:textfield id="namTS" name="namTS" cssClass="form-control" readonly="true" aria-describedby="basic-addon1" ></s:textfield>
                            		</div>
                               </td>
                           </tr>
                       </table>
                       <div id="errorMonThi1" style="color:red;"></div>
	           			<table class='table table-bordered' id='listMonThi'>
	           				
	           			</table>
                   		<div style="width:100%;" align="right">
                       		<s:reset cssClass="btn btn-info" onclick="setDefault();" value="Làm mới"></s:reset>
                           	<s:submit cssClass="btn btn-info" onclick="return checkForm();" value="Cập nhật"></s:submit>
                        </div>
					</s:form>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>



