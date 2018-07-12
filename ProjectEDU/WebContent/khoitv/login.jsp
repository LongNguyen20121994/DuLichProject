<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng nhập</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">	
			    <div class="col-md-9">
				    <h2 style="margin-bottom:0px;">Vui lòng đăng nhập</h2>
		            <a><small><small><i>Đăng ký tài khoản nếu chưa có.</i></small></small></a>
		            <br><br>
			        <s:form method="post" action="login.trip">
			        	<div>
			            	<label><b>Chọn loại tài khoản</b></label>
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
			                <label><b>Mật khẩu </b></label>
			                <span class="pull-right"><s:a action="showQuenMatKhau.trip"><i><small><small>Quên mật khẩu?</small></small></i></s:a></span>
			            </div>
			            <div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock"></span></span>
							<s:password name="matKhau" cssClass="form-control" placeholder="Mật khẩu" aria-describedby="basic-addon1" />
						</div>
			            <br>
			            <s:submit value="Đăng nhập" cssClass="btn btn-primary pull-right"></s:submit>
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