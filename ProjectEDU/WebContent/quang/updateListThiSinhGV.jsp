<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cập nhật thông tin thí sinh</title>
<jsp:include page="../css/style.html"/>
<script src="js/validateQuang.js"></script>
<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
</head>

<script type="text/javascript">
	
	
	function checkSua(){
		var list = new Array();
		$("input:checkbox[name=listSoCMND]:checked").each(function(){
			list.push($(this).val());
		});
		
		if(list.length == 0){
			$("#error").text("Bạn chưa chọn thí sinh!");
			return false;
		}
		if(list.length > 1){
			$("#error").text("Cập nhật chỉ được chọn 1 thí sinh!");
			return false;
		}
	}
	
	function checkXoa(){
		var list = new Array();
		$("input:checkbox[name=listSoCMND]:checked").each(function(){
			list.push($(this).val());
		});
		
		if(list.length == 0){
			$("#error").text("Bạn chưa chọn thí sinh!");
			return false;
		}
	}

	$(document).ready(function() {
	    $('#table').DataTable();
	} );
	
</script>
<body>
	<%@include file="../frame/header.jsp"%>
	﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">Cập nhật thông tin thí sinh</a></li>
					<li><a data-toggle="tab" href="#menu1">Upload danh sách cập nhật</a></li>
				</ul>
				<div class="col-md-12">
				
				
				<!-- Tab 1 -->
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<h2 style="margin-bottom:0px;">Danh sách thí sinh đã đăng ký</h2>
			            <s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
			            <br><br>
				        <s:form method="post" action="updateListThiSinhGV.edu">
				        	<s:hidden name="namTS"></s:hidden>
			        		<s:if test="%{info !=null}">
								<s:label cssStyle="color:red;" value="%{info.noiDung}"/>
							</s:if>
        			        <table id="table" class="table table-striped table-bordered"  style="width:100%;">
						        <thead>
						            <tr>
						                <th style="text-align: center; width:70px;">Số CMND</th>
						                <th style="text-align: center;">Họ lót</th>
						                <th style="text-align: center; width:70px;">Tên</th>
						                <th style="text-align: center; width:70px;">Ngày sinh</th>
						                <th style="text-align: center;width:65px;">Giới tính</th>
						                <th style="text-align: center;width:65px;">Cập nhật</th>
						            </tr>
						        </thead>
						       	<tfoot>
						       		 <tr>
						                <th style="text-align: center;">Số CMND</th>
						                <th style="text-align: center;">Họ lót</th>
						                <th style="text-align: center;">Tên</th>
						                <th style="text-align: center;">Ngày sinh</th>
						                <th style="text-align: center;">Giới tính</th>
						                <th style="text-align: center;">Cập nhật</th>
						            </tr>
						        </tfoot>
						        <tbody>
						 			<s:iterator id="list" value="listTS" >
						       			<tr>
							                <td><s:property value="soCMND"/></td>
							                <td><s:property value="hoTen"/></td>
							                <td><s:property value="matKhau"/></td>
							                <td style="text-align: center;"><s:property value="ngaySinh"/></td>
							                <td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
							                <td align="center"><s:checkbox cssClass="listSoCMND" name="listSoCMND" fieldValue="%{soCMND}" value="false" /></td>
						           		 </tr>
						       		</s:iterator>
						       	</tbody>
						    </table>     
						    <div id="error" style="color:red;"></div>   
						    <div  align="center">
			       				<s:reset value="Làm mới" cssClass="btn btn-primary"/>
				                <s:submit value="Sửa" name="btnSua" cssClass="btn btn-primary" onclick="return checkSua();"/>
				                <s:submit value="Xóa" name="btnXoa" cssClass="btn btn-primary" onclick="return checkXoa();"/>
					    	</div>
				        </s:form>
					</div>
					
					<script type="text/javascript">
						function checkFormUpload(){
							if($("#danhSachCN").val() == "" && $("#listAnh").val() == ""){
								$("#errorCN").text("Bạn chưa chọn file upload!");
								return false;
							}
						}
					</script>
					
					<!-- Tab 00000000000000002 -->
					<div id="menu1" class="tab-pane fade" style="width:100%;">
						<h2 style="margin-bottom:0px;">Upload danh sách cập nhật</h2>
						<s:a href=""><small><small><i>Bạn phải chịu tất cả trách nhiệm về thông tin cung cấp.</i></small></small></s:a>
						<s:form action="updateListThiSinhExcel.edu" method="post" enctype="multipart/form-data">
							<div class="input-group" style="margin-top:20px ">
							
								<div id="errorCN" style="color:red;"></div>
								<table class="table" style="margin-bottom: 5px;">
                                       <tr>
                                           <td>Danh sách thí sinh</td>
                                           <td>
                                           	<div class="input-group">
                                           		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-floppy-open"></span></span>
                                           		<s:file name="danhSachCN" id="danhSachCN" cssClass="form-control"/>
                                           	</div>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td>Danh sách ảnh thí sinh</td>
                                           <td>
                                           	<div class="input-group">
                                           		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-paperclip"></span></span>
                                           		<s:file name="listAnh" id="listAnh" cssClass="form-control" aria-describedby="basic-addon1"/>
                                           	</div>
                                           </td>
                                       </tr>
                                       <tr>
                                          <td colspan="2">
                                              <div style="width:100%;" align="right">
                                                  <s:submit cssClass="btn btn-info" value="Upload" onclick="return checkFormUpload();" />
                                              </div>
                                          </td>
                                       </tr>
                                   </table>
                                   <div>
                                    	<div style="float:left; color: red">Lưu ý: </div>
                                    	<div style="color: red; float:left; margin-left: 2px;">
                                    		File danh sách là file excel 2003 hoặc 2007(.xls hoặc .xlsx).<br />
                                    		File ảnh thí sính phải là file rar(.rar).
                                    	</div>
                                    </div>
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