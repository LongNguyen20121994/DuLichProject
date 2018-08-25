<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thanh toán</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script lang="Javascript">
window.onload = function()
{
	checkForm(true);
};
function checkForm(value){
	if(value === "ptThanhToantrue"){
		name = document.getElementById("giaTien").value * 1;
		$("#sotientt1").html(name + " VND");
		document.getElementById("sotientt").value = name;
	}else{
		name = document.getElementById("giaTien").value * 0.3;
		$("#sotientt1").html(name + " VND");
		document.getElementById("sotientt").value = name;
	}
}
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="content" style="margin: 0px 0px 0px 30px">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
		            <div class="tab-content">
		            	<s:if test="listCtkhNL != null || listCtkhTE != null || listCtkhTN != null || listCtkhSS != null">
							<div style="color:#e30050 !important;font-size:2em;"><h3>Thông tin hành khách đi cùng</h3></div>
						</s:if>
						<s:if test="listCtkhNL != null">
							<div style="color:#e30050 !important;font-size:2em;"><h4>-- Người lớn</h4></div>
							<s:form enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
									<thead>
							            <tr>
							                <th style="text-align: center; width:200px;">Tên Khách hàng</th>
							                <th style="text-align: center;">Ngày sinh</th>
							                <th style="text-align: center; width:95px;">Giới tính</th>
							            </tr>
							        </thead>
							        <tbody>
										<s:iterator value="listCtkhNL" >
											<tr>
								                <td align="center">
								                	<s:textfield name="ctkh.hoTen" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Nhập tên Khách Hàng"></s:textfield>
								                </td>
								                <td>
								                	<s:textfield name="ctkh.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
								                	<script src="js/bootstrap-datepicker.js"></script>
										            <script type="text/javascript">
										                // When the document is ready
										                $(document).ready(function () {
										                    $('#datepicker').datepicker({
										                        format: "dd/mm/yyyy"
										                    });
										                });
										            </script>
								                </td>
								                <td><s:select name="ctkh.gioiTinh" cssClass="form-control" list="#{'1':'Nam', '2':'Nữ'}" aria-describedby="basic-addon1" headerKey="-1" /></td>
								                <%-- <td align="center"><s:checkbox name="listMaPT" fieldValue="%{maPT}" value="false"/></td> --%>
							           		 </tr>
										</s:iterator>
									</tbody>
								</table>
							</s:form>
						</s:if>
						<s:if test="listCtkhTE != null">
							<div style="color:#e30050 !important;font-size:2em;"><h4>-- Trẻ em</h4></div>
							<s:form action="capNhatListPhuongTien.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
									<thead>
							            <tr>
							                <th style="text-align: center; width:200px;">Tên Khách hàng</th>
							                <th style="text-align: center;">Ngày sinh</th>
							                <th style="text-align: center; width:95px;">Giới tính</th>
							            </tr>
							        </thead>
							        <tbody>
										<s:iterator value="listCtkhTE" >
											<tr>
								                <td align="center">
								                	<s:textfield name="ctkh.hoTen" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Nhập tên Khách Hàng"></s:textfield>
								                </td>
								                <td>
								                	<s:textfield name="ctkh.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
								                	<script src="js/bootstrap-datepicker.js"></script>
										            <script type="text/javascript">
										                // When the document is ready
										                $(document).ready(function () {
										                    $('#datepicker').datepicker({
										                        format: "dd/mm/yyyy"
										                    });
										                });
										            </script>
								                </td>
								                <td><s:select name="ctkh.gioiTinh" cssClass="form-control" list="#{'1':'Nam', '2':'Nữ'}" aria-describedby="basic-addon1" headerKey="-1" /></td>
								                <%-- <td align="center"><s:checkbox name="listMaPT" fieldValue="%{maPT}" value="false"/></td> --%>
							           		 </tr>
										</s:iterator>
									</tbody>
								</table>
							</s:form>
						</s:if>
						<s:if test="listCtkhTN != null">
							<div style="color:#e30050 !important;font-size:2em;"><h4>-- Trẻ nhỏ</h4></div>
							<s:form action="capNhatListPhuongTien.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
									<thead>
							            <tr>
							                <th style="text-align: center; width:200px;">Tên Khách hàng</th>
							                <th style="text-align: center;">Ngày sinh</th>
							                <th style="text-align: center; width:95px;">Giới tính</th>
							            </tr>
							        </thead>
							        <tbody>
										<s:iterator value="listCtkhTN" >
											<tr>
								                <td align="center">
								                	<s:textfield name="ctkh.hoTen" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Nhập tên Khách Hàng"></s:textfield>
								                </td>
								                <td>
								                	<s:textfield name="ctkh.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
								                	<script src="js/bootstrap-datepicker.js"></script>
										            <script type="text/javascript">
										                // When the document is ready
										                $(document).ready(function () {
										                    $('#datepicker').datepicker({
										                        format: "dd/mm/yyyy"
										                    });
										                });
										            </script>
								                </td>
								                <td><s:select name="ctkh.gioiTinh" cssClass="form-control" list="#{'1':'Nam', '2':'Nữ'}" aria-describedby="basic-addon1" headerKey="-1" /></td>
								                <%-- <td align="center"><s:checkbox name="listMaPT" fieldValue="%{maPT}" value="false"/></td> --%>
							           		 </tr>
										</s:iterator>
									</tbody>
								</table>
							</s:form>
						</s:if>
						<s:if test="listCtkhSS != null">
							<div style="color:#e30050 !important;font-size:2em;"><h4>-- Trẻ sơ sinh</h4></div>
							<s:form action="capNhatListPhuongTien.trip" method="post" enctype="multipart/form-data">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
									<thead>
							            <tr>
							                <th style="text-align: center; width:200px;">Tên Khách hàng</th>
							                <th style="text-align: center;">Ngày sinh</th>
							                <th style="text-align: center; width:95px;">Giới tính</th>
							            </tr>
							        </thead>
							        <tbody>
										<s:iterator value="listCtkhSS" >
											<tr>
								                <td align="center">
								                	<s:textfield name="ctkh.hoTen" cssClass="form-control" aria-describedby="basic-addon1" placeholder="Nhập tên Khách Hàng"></s:textfield>
								                </td>
								                <td>
								                	<s:textfield name="ctkh.ngaySinh" id="datepicker" cssClass="form-control" placeholder="Ngày sinh" aria-describedby="basic-addon1" />
								                	<script src="js/bootstrap-datepicker.js"></script>
										            <script type="text/javascript">
										                // When the document is ready
										                $(document).ready(function () {
										                    $('#datepicker').datepicker({
										                        format: "dd/mm/yyyy"
										                    });
										                });
										            </script>
								                </td>
								                <td><s:select name="ctkh.gioiTinh" cssClass="form-control" list="#{'1':'Nam', '2':'Nữ'}" aria-describedby="basic-addon1" headerKey="-1" /></td>
								                <%-- <td align="center"><s:checkbox name="listMaPT" fieldValue="%{maPT}" value="false"/></td> --%>
							           		 </tr>
										</s:iterator>
									</tbody>
								</table>
							</s:form>
						</s:if>
					</div>
					<div style="color:#e30050 !important;font-size:2em;"><h3>Thanh toán</h3></div>
					<s:form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
						<!-- Địa chỉ email người nhận tiền -->
				        <input type="hidden" name="business" value="testnhan@test.com">
				
				        <!-- tham số cmd có giá trị _xclick chỉ rõ cho paypal biết là người dùng nhất nút thanh toán -->
				        <input type="hidden" name="cmd" value="_xclick">
				
				        <!-- Thông tin mua hàng. -->
				        <input type="hidden" name="item_name" value="HoaDonMuaHang">
						<!--Trị giá của giỏ hàng, vì paypal không hỗ trợ tiền việt nên phải đổi ra tiền $-->
						
						<input type="hidden" id="giaTien" name="hd.giaTien" value="${hd.giaTien}">
						
						<h4 style="float: left;">-- Tổng giá trị tour: <s:property value="hd.giaTien"></s:property> VND</h4>
						<br/>
						<div><br/></div>
						<tr>
		        			<td><h4>-- Phương thức thanh toán: </h4></td>
		        			<td>
								<s:radio name="ptThanhToan" id="ptThanhToan" list="#{'true':'&nbsp;Thanh toán online(100% online)','false':'&nbsp;Thanh toán trực tiếp(30% online)'}" onclick="checkForm(this.id)"/>
		        			</td>
		        		</tr><br>
						<tr>
							<td style="width:180px; padding-top:8px;"><strong style="float: left;">Bắt buộc thanh toán Online số tiền: </strong><p id="sotientt1" style="font-weight: bold;font-size: 16px; color: #075d77 !important"></p></td>
		        		</tr>
				        <input type="hidden" id="sotientt" type="number" name="amount" placeholder="Nhập số tiền vào" value="">
				        
						<!--Loại tiền-->
				        <input type="hidden" name="currency_code" value="USD">
						
						<!--Đường link mình cung cấp cho Paypal biết để sau khi xử lí thành công nó sẽ chuyển về theo đường link này-->
						<input type="hidden" name="return" value="http://localhost:8081/ProjectEDU/sendMailMatKhau.trip">
						
						<!--Đường link mình cung cấp cho Paypal biết để nếu  xử lí KHÔNG thành công nó sẽ chuyển về theo đường link này-->
				        <input type="hidden" name="cancel_return" value="http://localhost:8081/ProjectEDU/loi.html">
				        
				        <!-- Nút bấm. -->
				        <input type="submit" name="submit" value="Thanh toán qua Paypal">
					</s:form>
				</div>
			</div>
			<%@include file="../frame/right.jsp"%>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>