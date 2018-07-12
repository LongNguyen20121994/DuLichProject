<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đổi mật khẩu</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">	
			    <div class="col-md-9">
				    <h2 style="margin-bottom:0px;">Đổi mật khẩu</h2>
		            <s:a href=""><small><small><i>Bấm vào đây để cập nhật thông tin cá nhân.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="doiMatKhau.trip">
			        
						<s:hidden name="account"/>
			            <div>
			            	<label><b>Số chứng minh thư</b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
							<s:textfield name="soCMND" cssClass="form-control" aria-describedby="basic-addon1" readonly="true" />
						</div>
			            <br>
			            <div>
			                <label><b>Mật khẩu mới </b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock"></span></span>
							<s:password name="matKhauMoi" cssClass="form-control" placeholder="Mật khẩu mới" aria-describedby="basic-addon1" />
						</div>
			            <br>
			            <div>
			                <label><b>Nhập lại mật khẩu </b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock"></span></span>
							<s:password name="matKhauRe" cssClass="form-control" placeholder="Nhập lại mật khẩu" aria-describedby="basic-addon1" />
						</div>
			            <br>
			            <s:submit value="Đổi mật khẩu" cssClass="btn btn-primary pull-right"></s:submit>
			        </s:form>
			    </div>
			    <div class="col-md-3"></div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>