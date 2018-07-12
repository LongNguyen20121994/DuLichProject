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
				    <h2 style="margin-bottom:0px;">Quên mật khẩu</h2>
		            <s:a href=""><small><small><i>Thông tin tài khoản sẽ được gửi đến email đăng ký tài khoản của bạn.</i></small></small></s:a>
		            <br><br>
			        <s:form method="post" action="quenMatKhau.trip">
						<div>
			            	<label><b>Loại tài khoản đăng ký</b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
							<s:select name="account" cssClass="form-control" list="list" aria-describedby="basic-addon1" ></s:select>
						</div>
			            <br>
			            <div>
			            	<label><b>Số chứng minh thư</b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
							<s:textfield name="soCMND" cssClass="form-control" placeholder="Số chứng minh thư" aria-describedby="basic-addon1" />
						</div>
			            <br>
			            <div>
			                <label><b>Email đăng ký </b></label>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
							<s:textfield name="email" cssClass="form-control" placeholder="Email đăng ký tài khoản" aria-describedby="basic-addon1" />
						</div>
			            <br>
			            <div align="right">
				            <s:reset value="Làm mới" cssClass="btn btn-primary"></s:reset>
				            <s:submit value="Gửi yêu cầu" cssClass="btn btn-primary"></s:submit>
				        </div>
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