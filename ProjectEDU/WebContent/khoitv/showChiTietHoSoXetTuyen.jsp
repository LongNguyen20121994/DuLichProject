<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Chi tiết hồ sơ xét tuyển</title>
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
					<h1 style="margin-bottom:0px; text-align: center">Hồ sơ xét tuyển<br><small>${hs.maTruong}</small></h1>
						<table align="center" style="margin-top:30px;">
							<tr>
								<td style="width:150px;"><b>Mã hồ sơ</b></td>
								<td><b>: ${hs.maHS }</b></td>
							</tr>
							<tr>
								<td><b>Đợt xét tuyển</b></td>
								<td><b>: ${hs.maDotXT }</b></td>
							</tr>
							<tr>
								<td><b>Ngày nộp</b></td>
								<td><b>: <s:property value="hs.ngayNop"/> </b></td>
							</tr>
						</table>
			            <br>
			            <h4>Danh sách ngành đăng ký vào trường</h4>
       			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
					        <thead>
					            <tr>
					                <th style="text-align: center;">Mã ngành</th>
					                <th style="text-align: center;">Tên ngành</th>
					                <th style="text-align: center;">Khối thi</th>
					            </tr>
					        </thead>
					       	<tfoot>
					       		 <tr>
					                <th style="text-align: center;">Mã Ngành</th>
					                <th style="text-align: center;">Tên ngành</th>
					                <th style="text-align: center;">Khối thi</th>
					            </tr>
					        </tfoot>
					        <tbody>
					 			<s:iterator value="listNganh" >
					       			<tr>
						                <td align="center"><s:property value="maNganh"/></td>
						                <td><s:property value="tenNganh"/></td>
						                <td align="center"><s:property value="ghiChu"/></td>
					           		 </tr>
					       		</s:iterator>
					       	</tbody>
					    </table>
			    		<s:if test="info != null">
                        	<div align="right">${info.noiDung} </div>
                        </s:if>
                        <br/>
                        <h4>Ghi chú</h4>
                        <ul>
                        	<li>DHCQ : Đại học chính quy</li>
                        	<li>DHLT : Đại học liên thông</li>
                        	<li>CDCQ : Cao đẳng chính quy</li>
                        	<li>CDLT : Cao đẳng liên thông</li>
                        	<li>TCCN : Trung cấp chuyên nghiệp</li>
                        </ul>
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