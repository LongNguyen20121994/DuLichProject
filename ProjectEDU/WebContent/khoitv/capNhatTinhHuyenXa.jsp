<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật Tỉnh(Thành phố) - Huyện(Quận) - Xã(Phường)</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h3 style="margin-bottom:0px;">Cập nhật Tỉnh(Thành phố) - Huyện(Quận) - Xã(Phường)</h3>
		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách đã có để cập nhật.</i></small></small></s:a>
		            <br><br>
					<div id="excel" style="width:100%;">
						<h3>Upload Danh sách</h3>
						<s:form action="addTinhHuyenXaExcel.trip" method="post" enctype="multipart/form-data">
							<div class="input-group">
								<table class="table">
									<tr>
										<td style="padding-top: 15px; width: 110px;">Chọn file excel</td>
										<td>
											<div class="input-group">
												<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
												<s:file name="file" cssClass="form-control" aria-describedby="basic-addon1"></s:file>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div style="width: 100%;" align="right">
												<s:submit cssClass="btn btn-info" value="Upload"></s:submit>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</s:form>
					</div>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>