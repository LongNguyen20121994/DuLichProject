<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thống kê</title>
<jsp:include page="../css/style.html"/>

<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->

</head>
<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
<body>
	<%@include file="../frame/header.jsp"%>
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
					<div class="tab-content">
						<div class="tab-pane fade in active">
							<div>
								<div class="col-md-6" style="padding-top:15px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
										<s:textfield name="txtFrom" id="datepicker1" cssClass="form-control" placeholder="Từ ngày" aria-describedby="basic-addon1" />
									</div>
									<div id="errorDOB" style="color:red;"></div>
									
									<script src="js/bootstrap-datepicker.js"></script>
						            <script type="text/javascript">
						                // When the document is ready
						                $(document).ready(function () {
						                    $('#datepicker1').datepicker({
						                        format: "dd/mm/yyyy"
						                    });
						                });
						            </script>
								</div>
								<div class="col-md-6" style="padding-top:15px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
										<s:textfield name="txtTo" id="datepicker" cssClass="form-control" placeholder="Đến ngày" aria-describedby="basic-addon1" />
									</div>
									<div id="errorDOB" style="color:red;"></div>
									
									<script src="js/bootstrap-datepicker.js"></script>
						            <script type="text/javascript">
						                // When the document is ready
						                $(document).ready(function () {
						                    $('#datepicker').datepicker({
						                        format: "dd/mm/yyyy"
						                    });
						                });
						            </script>
								</div>
							</div>
							<div>
								<div class="col-md-6" style="padding-top:15px;text-align: right;">
									<s:a name="btDaDat" target="_blank">
				                		<button class="btn btn-primary">Tour đã đặt</button>
							        </s:a>
								</div>
								<div class="col-md-6" style="padding-top:15px;">
									<s:a name="btChuaDat" target="_blank">
				                		<button class="btn btn-primary">Tour chưa được đặt</button>
							        </s:a>
								</div>
							</div>
					        <br/>
							<div id="danhSach">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center; width:100px;">Mã Tour</th>
							                <th style="text-align: center;">Tiêu đề</th>
							                <th style="text-align: center;">Ngày Khởi hành</th>
							                <th style="text-align: center;width:100px;">Tác vụ</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;width:100px;">Mã Tour</th>
							                <th style="text-align: center;">Tiêu đề</th>
							                <th style="text-align: center;">Ngày Khởi hành</th>
							                <th style="text-align: center;width:100px;">Tác vụ</th>
							            </tr>
							        </tfoot>
							        <tbody>
							 			<s:iterator value="listTourThongKe" >
							       			<tr>
								                <td align="center"><s:property value="maTour"/></td>
								                <td><s:property value="tieuDe"/></td>
								                <td><s:property value="ngayKhoiHanh"/></td>
								                <td align="center">
								                	<s:a action="showInfoTour.trip?maTour=%{maTour}">Xem</s:a>
								                </td>
							           		 </tr>
							       		</s:iterator>
							       	</tbody>
						    	</table>
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