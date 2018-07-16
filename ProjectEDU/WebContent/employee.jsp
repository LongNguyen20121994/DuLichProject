<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật tài khoản Admin</title>
<jsp:include page="css/style.html"/>
</head>
<body>
	<%@include file="frame/header.jsp"%>
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
					<div class="tab-content">
						<div class="tab-pane fade in active">
						<h2 style="margin-bottom:0px; text-align: center">Hiển thị danh sách Tour</h2>
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
						 			<s:iterator value="listTours" >
						       			<tr>
							                <td align="center"><s:property value="maTour"/></td>
							                <td><s:property value="tieuDe"/></td>
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
							<s:iterator value="listTours">
								<s:property value="maTour"/> , 
								<s:property value="tieuDe"/><br/>
							</s:iterator>
						</div>
					</div>
				</div>
			</div>
			<%@include file="frame/right.jsp"%>
		</div>
	</div>
	<%@include file="frame/footer.jsp"%>
</body>
</html>