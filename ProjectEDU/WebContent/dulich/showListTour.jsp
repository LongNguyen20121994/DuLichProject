<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật tài khoản Admin</title>
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
	    	$.post("ajaxShowListTour.trip",
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
	<%@include file="../frame/header.jsp"%>
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
					<div class="tab-content">
						<div class="tab-pane fade in active">
							<h2 style="margin-bottom:0px; text-align: center">Danh sách Tour</h2>
					        <br>
				            <div class="input-group">
								<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
								<s:select list="listLoaiTour" name="maTinh" id="selectDS" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn loại Tour"/>
							</div>
					        <br>
							<div id="danhSach">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
						        <thead>
						            <tr>
						                <th style="text-align: center; width:100px;">Mã Tour</th>
						                <th style="text-align: center;">Tiêu đề</th>
						                <th style="text-align: center;width:100px;">Tác vụ</th>
						            </tr>
						        </thead>
						       	<tfoot>
						       		 <tr>
						                <th style="text-align: center;width:100px;">Mã Tour</th>
						                <th style="text-align: center;">Tiêu đề</th>
						                <th style="text-align: center;width:100px;">Tác vụ</th>
						            </tr>
						        </tfoot>
						        <tbody>
						 			<s:iterator value="listTours" >
						       			<tr>
							                <td align="center"><s:property value="maTour"/></td>
							                <td><s:property value="tieuDe"/></td>
							                <td align="center">
							                	<s:a action="showInfoTour.trip?maTour=%{maTour}">Xem</s:a> |
							                	<s:a action="showCapNhatTour.trip?maTour=%{maTour}">Nộp</s:a>
							                </td>
						           		 </tr>
						       		</s:iterator>
						       	</tbody>
						    </table>
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