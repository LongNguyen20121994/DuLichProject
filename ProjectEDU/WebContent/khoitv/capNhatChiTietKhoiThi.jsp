<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật chi tiết khối thi</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
	function checkMonThi(){
		$.post("checkMonThi.trip",
	    {
	    	maKhoi: $("#khoiThi").val(),
	    	maMon: $("#monThi").val()
	    },
	    function(data,status){
	    	if($.trim(data) == "invalid"){
		    	$("#monThi").css("border-color", "red");
		    	alert("Môn thi đã tồn tại!");
	    	} else {
	    		$("#monThi").css("border-color", "#ccc");
	    	}
	    });
	}
	function checkForm(){
		if($("#khoiThi").val() == "-1"){
			alert("Chưa chọn khối thi!");
			return false;
		}
		if(document.getElementById("monThi").style.borderColor == "red"){
			alert("Môn thi đã tồn tại!");
			return false;
		}
	}
	function setDefault(){
		$("#monThi").css("border-color", "#ccc");
		$("#listMonThi").html("");
	}
    $(document).ready(function() {
        $("#khoiThi" ).change(function() {
        	$.post("showListMT.trip",
   		    {
   				maKhoi: $("#khoiThi").val()
   		    },
   		    function(data,status){
   		    	$("#listMonThi").html(data);
   		    });
        	//alert();
        	//alert($("#monThi").css("border-color") == "rgb(255, 0, 0)");
        	checkMonThi();
       	});
        $.post("showListMT.trip",
       		    {
       				maKhoi: $("#khoiThi").val()
       		    },
       		    function(data,status){
       		    	$("#listMonThi").html(data);
       		    });
        $("#monThi" ).change(function() {
        	checkMonThi();
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
				    <h2 style="margin-bottom:0px;">Cập nhật chi tiết khối thi</h2>
<%-- 		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách ngành đã có để cập nhật.</i></small></small></s:a> --%>
		            <br><br>
					<s:form action="capNhatChiTietKhoiThi.trip" method="post">
						<table class="table">
                           <tr>
                               <td style="padding-top: 15px; width:110px;">Khối thi</td>
                               <td>
	                               	<div class="input-group">
	                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
	                               		<s:select name="ct.maKhoi" list="listKhoi" id="khoiThi" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn khối thi"/>
	                               	</div>
                               </td>
                           </tr>
                           <tr>
                               <td style="padding-top: 15px;">Thêm Môn thi</td>
                               <td>
	                               	<div class="input-group">
	                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
	                               		<s:select name="ct.maMon" list="listMon" id="monThi" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn môn thi"/>
	                               	</div>
                               </td>
                           </tr>
                       </table>
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