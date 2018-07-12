<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Danh sách ngành đại học cao đẳng</title>
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
					<h2 style="margin-bottom:0px; text-align: center">${truongDHCD.maTruong} - ${truongDHCD.tenTruong }</h2>
					<h4 style="text-align: center">Trường thuộc tỉnh (thành phố) ${truongDHCD.diaChi }</h4>
					    <div align="center">
					    	<s:a action="showDangKyXetTuyen.trip?hs.maTruong=%{truongDHCD.maTruong }" cssClass="btn btn-info">Đăng ký xét tuyển vào trường</s:a>
					    </div>
			            <br>
			            <p>${truongDHCD.ghiChu }</p>
			            <h3>Danh sách ngành đào tạo của trường</h3>
				        <br><br>
       			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
					        <thead>
					            <tr>
					                <th style="text-align: center;width:50px;">Ngành</th>
					                <th style="text-align: center;">Tên ngành</th>
					                <th style="text-align: center;width:70px;">Đào tạo</th>
					                <th style="text-align: center;width:40px;">Khối</th>
					            </tr>
					        </thead>
					       	<tfoot>
					       		 <tr>
					                <th style="text-align: center;">Mã Ngành</th>
					                <th style="text-align: center;">Tên ngành</th>
					                <th style="text-align: center;">Đào tạo</th>
					                <th style="text-align: center;">Khối thi</th>
					            </tr>
					        </tfoot>
					        <tbody>
					 			<s:iterator value="listNganhDHCD" >
					       			<tr>
						                <td align="center"><s:property value="maNganh"/></td>
						                <td><s:property value="tenNganh"/></td>
						                <td align="center"><s:property value="daoTao"/></td>
						                <td align="center"><s:property value="maKhoi"/></td>
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