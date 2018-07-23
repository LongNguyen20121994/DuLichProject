<%@page contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cao đẳng công nghệ-Ðại học Ðà Nẵng</title>
<jsp:include page="../css/style.html"/>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			﻿﻿<div class="col-md-8 left">
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home">Thêm thí sinh đăng ký</a></li>
						<li><a data-toggle="tab" href="#menu1">Upload danh sách điểm thi</a></li>
					</ul>
					<div class="col-md-12">
					
					
					<!-- Tab 0000000000000000001 -->
					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
							<h2 style="margin-bottom:0px;">Thông tin thí sinh</h2>
				            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
				            <br><br>
						</div>
						
						
						<!-- Tab 00000000000000002 -->
						<div id="menu1" class="tab-pane fade" style="width:100%;">
							<h2 style="margin-bottom:0px;">Upload danh sách điểm thi </h2>
							<s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
							<s:form action="uploadDiemThiExcel.edu" method="post" enctype="multipart/form-data">
								<div class="input-group" style="margin-top:20px ">
									<table class="table">
                                        <tr>
                                            <td>Danh sách điểm thi</td>
                                            <td>
                                            	<div class="input-group">
                                            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-floppy-open"></span></span>
                                            		<s:file name="danhSachDiem" cssClass="form-control"></s:file>
                                            	</div>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td colspan="2">
                                               <div style="width:100%;" align="right">
                                               <s:hidden name="namTS"></s:hidden>
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
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>