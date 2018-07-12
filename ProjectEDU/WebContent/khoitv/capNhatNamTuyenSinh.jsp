<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật năm tuyển sinh</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Cập nhật năm tuyển sinh</h2>
		            <s:a href=""><small><small><i>Năm tuyển sinh lấy bằng năm hiện hành. Chỉ có thể cập nhật ghi chú của năm.</i></small></small></s:a>
		            <br><br>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="capNhatNamTS.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:135px;">Năm tuyển sinh</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
                                        		<s:textfield name="namTS.namTS" cssClass="form-control" readonly="true" aria-describedby="basic-addon1"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">Ghi chú</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        	<s:textarea name="namTS.ghiChu" id="ghiChu" class="form-control"></s:textarea>
											<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
                                			<script type="text/javascript">CKEDITOR.replace('ghiChu'); </script>
                                        </td>
                                    </tr>
                                    <s:if test="info != null">
                                    	<tr>
                                    		<td colspan="2" align="right">${info.noiDung} </td>
                                    	</tr>
                                    </s:if>
                                    <tr>
                                       <td colspan="2">
                                           <div style="width:100%;" align="right">
                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
                                               	<s:submit cssClass="btn btn-info" value="Cập nhật"></s:submit>
                                           </div>
                                       </td>
                                    </tr>
                                </table>
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