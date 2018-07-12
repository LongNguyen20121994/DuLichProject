<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Danh sách trường đại học cao đẳng</title>
<jsp:include page="../css/style.html"/>

<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
</head>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	    $("#selectDS" ).change(function() {
	    	$.post("ajaxShowListDHCD.trip",
   		    {
   				maTinh: $("#selectDS").val(),
   		    },
   		    function(data,status){
   		  		    $("#danhSach").html(data);
   		    });
       	});
	});
</script>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
				<!-- Tab 1 -->
				<div class="tab-content">
					<div class="tab-pane fade in active">
					<h2 style="margin-bottom:0px; text-align: center">Danh sách các trường đại học - cao đẳng</h2>
			            <br>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
							<s:select list="listTinh" name="maTinh" id="selectDS" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Trường thuộc tỉnh (thành phố)"/>
						</div>
				        <br>
				        <div id="danhSach">
	       			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
						        <thead>
						            <tr>
						                <th style="text-align: center;width:40px;">M.Trường</th>
						                <th style="text-align: center;">Tên trường</th>
						                <th style="text-align: center;width:60px;">Tên tỉnh</th>
						                <th style="text-align: center;width:40px;">C.Tiêu</th>
						                <th style="text-align: center;width:65px;">Tác vụ</th>
						            </tr>
						        </thead>
						       	<tfoot>
						       		 <tr>
						                <th style="text-align: center;">Mã trường</th>
						                <th style="text-align: center;">Tên trường</th>
						                <th style="text-align: center;">Tên tỉnh</th>
						                <th style="text-align: center;">Chỉ tiêu</th>
						                <th style="text-align: center;">Tác vụ</th>
						            </tr>
						        </tfoot>
						        <tbody>
						 			<s:iterator value="listTruongDHCD" >
						       			<tr>
							                <td align="center"><s:property value="maTruong"/></td>
							                <td><s:property value="tenTruong"/></td>
							                <td align="center"><s:property value="diaChi"/></td>
							                <td align="center"><s:property value="ghiChu"/></td>
							                <td align="center">
							                	<s:a action="showInfoDHCD.trip?maTruong=%{maTruong}">Xem</s:a> |
							                	<s:a action="showDangKyXetTuyen.trip?hs.maTruong=%{maTruong}">Nộp</s:a>
							                </td>
						           		 </tr>
						       		</s:iterator>
						       	</tbody>
						    </table>
					    </div>
			    		<s:if test="info != null">
                        	<div align="right">${info.noiDung} </div>
                        </s:if>
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