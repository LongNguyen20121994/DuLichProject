<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thêm ngành</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Thêm ngành</h2>
		            <s:a href=""><small><small><i>Bấm vào đây để tải về danh sách ngành đã có để cập nhật.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#input">Nhập thông tin</a></li>
						<li><a data-toggle="tab" href="#excel">Tải lên file excel</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="themNganh.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:100px;">Mã ngành</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
                                        		<s:textfield name="nganh.maNganh" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Mã ngành"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Tên ngành</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="nganh.tenNganh" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Tên ngành"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                       <td colspan="2">
                                           <div style="width:100%;" align="right">
                                           		<s:reset cssClass="btn btn-info" value="Làm mới"></s:reset>
                                               	<s:submit cssClass="btn btn-info" value="Thêm"></s:submit>
                                           </div>
                                       </td>
                                    </tr>
                                </table>
							</s:form>
						</div>
						<div id="excel" class="tab-pane fade" style="width:100%;">
							<h3>Upload Danh sách</h3>
							<s:form action="themNganhExcel.trip" method="post" enctype="multipart/form-data">
								<div class="input-group">
									<table class="table">
                                        <tr>
                                            <td style="padding-top: 15px; width:110px;">Chọn file excel</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                            		<s:file name="file" cssClass="form-control" aria-describedby="basic-addon1"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td colspan="2">
                                               <div style="width:100%;" align="right">
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
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>