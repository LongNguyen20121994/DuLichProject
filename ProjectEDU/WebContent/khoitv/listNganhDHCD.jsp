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
					<h2 style="margin-bottom:0px;">Danh sách ngành đại học - cao đẳng</h2>
			            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về các tác vụ thao tác.</i></small></small></s:a>
			            <br><br>
			            <s:form method="post" action="capNhatListNganhDHCD.trip">
					        <br><br>
        			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
						        <thead>
						            <tr>
						                <th style="text-align: center; width:70px;">Mã Ngành</th>
						                <th style="text-align: center;">Tên ngành</th>
						                <th style="text-align: center; width:70px;">Đào tạo</th>
						                <th style="text-align: center;width:65px;">Chỉ tiêu</th>
						                <th style="text-align: center;width:65px;">Xóa</th>
						            </tr>
						        </thead>
						       	<tfoot>
						       		 <tr>
						                <th style="text-align: center;">Mã Ngành</th>
						                <th style="text-align: center;">Tên ngành</th>
						                <th style="text-align: center;">Đào tạo</th>
						                <th style="text-align: center;">Chỉ tiêu</th>
						                <th style="text-align: center;">Xóa</th>
						            </tr>
						        </tfoot>
						        <tbody>
						 			<s:iterator value="list" >
						       			<tr>
							                <td><s:property value="maNganh"/></td>
							                <td><s:property value="ghiChu"/></td>
							                <td align="center"><s:property value="daoTao"/></td>
							                <td align="center"><s:property value="chiTieu"/></td>
							                <td align="center"><s:checkbox name="listMaNganh" fieldValue="%{maNganh}-%{daoTao}"/></td>
						           		 </tr>
						       		</s:iterator>
						       	</tbody>
						    </table>
				    		<s:if test="info != null">
                            	<div align="right">${info.noiDung} </div>
                            </s:if>
                            <div align="right">
							    <div class="btn-group">
					                <s:submit value="Cập nhật điểm" name="btnUpdateDiem" cssClass="btn btn-primary"/>
					                <s:submit value="Cập nhật khối" name="btnUpdateKhoi" cssClass="btn btn-primary"/>
					                <s:submit value="Cập nhật hệ số" name="btnUpdateHS" cssClass="btn btn-primary"/>
					                <s:submit value="Xóa ngành" cssClass="btn btn-primary"/>
						    	</div>
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