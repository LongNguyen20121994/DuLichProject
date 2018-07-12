<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông tin</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">	
				<s:if test="info.tieuDe!='null'">
					<h3 style="border-bottom: 1px solid #ccc; width:100%;" id="tieuDe"><s:property value="info.tieuDe" /></h3>
				</s:if>
				<s:else>
					<h3 style="border-bottom: 1px solid #ccc; width:100%;" id="tieuDe">Trang báo lỗi</h3>
				</s:else>
                <div style="padding-left: 5px;">
                	<s:if test="info.noiDung!='null'">
                    	<h4>${info.noiDung} </h4>
                	</s:if>
                	<s:else>
                		<h4>Có lỗi trong quá trình thực hiện! vui lòng kiểm tra lại!</h4>
                	</s:else>
                    <i>Click vào <a onclick="history.back(-1);" style="cursor: pointer;">Đây</a> để quay lại trang trước!</i>
                </div>
			</div>
			<script>
				window.document.title = document.getElementById("tieuDe").innerHTML;
			</script>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>