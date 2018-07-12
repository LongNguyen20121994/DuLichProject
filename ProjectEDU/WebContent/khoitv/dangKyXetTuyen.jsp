<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng ký xét tuyển</title>
<jsp:include page="../css/style.html"/>
<script lang="Javascript">
	function showKhoiThi(){
		$.post("showKhoiThi.trip",
	    {
			maTruong: $("#truongDHCD").val(),
	    	maNganh: $("#nganhDHCD").val(),
	    	namTuyenSinh: $("#namTuyenSinh").val()
	    },
	    function(data,status){
	    	$("#khoiThi").html(data);
	    });
	}
	
	function changeTinhDH(){
		$.post("showDHCD.trip",
	    {
	    	maTinh: $("#maTinhDH").val()
	    },
	    function(data,status){
	    	$("#truongDHCD").html(data);
	    });
	}
	
	function changeTruongDH(){
		$.post("showNganhDHCD.trip",
	    {
			maTruong: $("#truongDHCD").val()
	    },
	    function(data,status){
	    	$("#nganhDHCD").html(data);
	    });
	}
	
	function getListChiTietHoSo(){
		$.post("showChiTietHoSo.trip",
	    {
			maTruong: $("#truongDHCD").val(),
	    	maDotXT: $("#maDotXT").val(),
	    	namTuyenSinh: $("#namTuyenSinh").val()
	    },
	    function(data,status){
	    	$("#listNganhTS").html(data);
	    });
	}
	
	function setDefault(){
		$("#listNganhTS").html("");
	}
	var check ="";
	function addListNganh(){
		var tmp = $("#nganhDHCD").val()+"-"+$("#khoiThi").val();
		var str = "<tr><td>" + $("#nganhDHCD").val().split('-')[0] + "</td><td>"+ $("#nganhDHCD option:selected").text();
		str +="</td><td>"+$("#khoiThi option:selected").text()+"</td><td align='center'><input type='checkbox' name='ktNganh' value='"+tmp+"' checked='true' /></td></tr>";
		if(check.indexOf(tmp) == -1){
			if($("#khoiThi").val() != "-1" && $("#nganhDHCD").val() != "-1"){
				if($("#listNganhTS").html() != "") {
					$("#listNganhTS").html($("#listNganhTS").html()+str);
					check += str+"|";
				}
				else {
					$("#listNganhTS").html("<tr><th>Mã Ngành</th><th>Tên ngành</th><th>Khối thi</th><th style='text-align:center'>Đăng ký | Xóa</th></tr>" + str);
					check += str+"|";
				}
			}
		} else {
			alert("Khối " + $("#khoiThi").val() + " ngành " + $("#nganhDHCD option:selected").text() + " đã có trong danh sách!");
		}
			
	}
    $(document).ready(function() {
       	$("#maTinhDH" ).change(function() {
        	changeTinhDH();
        	$("#listNganhTS").html("");
    		check = "";
       	});
    	$("#truongDHCD" ).change(function() {
    		changeTruongDH();
    		getListChiTietHoSo();
       	});
    	$("#nganhDHCD" ).change(function() {
        	showKhoiThi();
       	});
    	$("#maDotXT" ).change(function() {
    		getListChiTietHoSo();
       	});
    	$("#khoiThi" ).change(function() {
    	    var x = document.getElementsByName("ktNganh");
    	    check="";
    	    for (var i=0; i<x.length; i++) {
       		    check += x[i].value + "|";
       		}
        	addListNganh();
       	});
    	changeTruongDH();
    	showKhoiThi();
    	getListChiTietHoSo();
    });
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Đăng ký xét tuyển</h2>
<%-- 		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách ngành đã có để cập nhật.</i></small></small></s:a> --%>
		            <br><br>
					<s:form action="dangKyXetTuyen.trip" method="post">
						<table class="table">
							<tr>
                               <td style="padding-top: 15px; width:130px;">Năm tuyển sinh</td>
                               <td>
                               	<div class="input-group">
                               		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                               		<s:textfield name="hs.namTS" id="namTuyenSinh" readonly="true" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Năm tuyển sinh"></s:textfield>
                               	</div>
                                </td>
                            </tr>
							<tr>
			        			<td style="padding-top:35px;">Trường ĐH - CĐ</td>
			        			<td>
			        				<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
										<s:select id="maTinhDH" cssClass="form-control" list="listTinh" aria-describedby="basic-addon1" headerKey="-1" headerValue="Trường thuộc tỉnh (thành phố)" />
									</div>
				        			<div class="input-group" style="margin-top:5px;">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
										<s:select name="hs.maTruong" id="truongDHCD" cssClass="form-control" list="listDHCD" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn trường Đại học (Cao đẳng)" />
									</div>
			        			</td>
							</tr>
							<tr>
								<td style="padding-top: 15px;">Đợt xét tuyển</td>
								<td>
									<div class="input-group" style="margin-top:5px;">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
										<s:select name="hs.maDotXT" id="maDotXT" cssClass="form-control" list="listDotXT" aria-describedby="basic-addon1" />
									</div>
								</td>
							</tr>
							<tr>
			        			<td style="padding-top:35px;">Ngành ĐH - CĐ</td>
			        			<td>
			        				<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
										<s:select id="nganhDHCD" name="nganhDHCD" cssClass="form-control" list="{}" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn ngành tuyển sinh" />
									</div>
				        			<div class="input-group" style="margin-top:5px;">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
										<s:select id="khoiThi" name="khoiThi" cssClass="form-control" list="{}" aria-describedby="basic-addon1" listKey="maKhoi" listValue="tenKhoi" headerKey="-1" headerValue="Chọn khối thi" />
									</div>
			        			</td>
							</tr>
                        </table>
	           			<table class='table table-bordered' id='listNganhTS'></table>
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