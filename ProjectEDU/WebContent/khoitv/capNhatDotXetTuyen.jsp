<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật đợt xét tuyển</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
				    <h2 style="margin-bottom:0px;">Cập nhật đợt xét tuyển</h2>
		            <s:a href=""><small><small><i>Dùng để điều khiển thời hạn thu hồ sơ các đợt xét tuyển.</i></small></small></s:a>
		            <br><br>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#input">Thêm đợt xét tuyển</a></li>
						<li><a data-toggle="tab" href="#excel">Cập nhật đợt xét tuyển</a></li>
					</ul>
					<div class="tab-content">
						<div id="input" class="tab-pane fade in active">
							<h3>Nhập thông tin</h3>
							<s:form action="themDotXT.trip" method="post">
								<table class="table">
                                    <tr>
                                        <td style="padding-top: 15px; width:135px;">Mã đợt xét tuyển</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-refresh"></span></span>
                                        		<s:textfield name="dxt.maDotXT" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Mã đợt xét tuyển"></s:textfield>
                                        	</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 15px;">Tên đợt xét tuyển</td>
                                        <td>
                                        	<div class="input-group">
                                        		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file"></span></span>
                                        		<s:textfield name="dxt.tenDotXT" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Tên đợt xét tuyển"></s:textfield>
                                        	</div>
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
                                               	<s:submit cssClass="btn btn-info" value="Thêm"></s:submit>
                                           </div>
                                       </td>
                                    </tr>
                                </table>
							</s:form>
						</div>
						<div id="excel" class="tab-pane fade" style="width:100%;">
							<h3>Cập nhật đợt xét tuyển</h3>
							<s:form action="capNhatDotXT.trip" method="post" enctype="multipart/form-data">
								<table class="table">
                                       <tr>
                                           <td style="padding-top: 15px; width:150px;">Chọn đợt xét tuyển</td>
                                           <td>
                                           	<div class="input-group">
                                           		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-transfer"></span></span>
                                           		<s:select name="maDotXT" cssClass="form-control" list="listDotXT" listKey="maDotXT" listValue="tenDotXT" aria-describedby="basic-addon1" headerKey="-1" headerValue="Chọn đợt xét tuyển để thao tác" />
                                           	</div>
                                           </td>
                                       </tr>
                                       <tr>
                                          <td colspan="2">
                                              <div style="width:100%;" align="right">
                                                  <s:submit cssClass="btn btn-info" name="btnDong" value="Đóng"></s:submit>
                                                  <s:submit cssClass="btn btn-info" name="btnMo" value="Mở"></s:submit>
                                                  <s:submit cssClass="btn btn-info" name="btnXoa" value="Xóa"></s:submit>
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