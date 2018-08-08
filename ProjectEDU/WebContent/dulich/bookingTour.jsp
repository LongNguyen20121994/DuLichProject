<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông tin chi tiết</title>
<jsp:include page="../css/style.html"/>
	
	<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script lang="Javascript">
$(document).ready(function() {
    $('input[type=file]').ajaxfileupload({
        'action' : 'uploadHinhAnh.trip',
        'onComplete' : function(response) {
            $('#upload').hide();
            
            var statusVal = JSON.stringify(response.status);

            if(statusVal == "false")
            {
                $("#anhCaNhan").html("<font color='red'>"+ JSON.stringify(response.message) +" </font>");
            }   
            if(statusVal == "true")
            {
                $("#anhCaNhan").html("<img src='anhThanhVien\\"+$('input[type=file]').val().split('\\').pop()+"' class='img-thumbnail' width='100%'/>");
            }           
        },
        'onStart' : function() {
            $('#upload').show();
        }
    });
});
</script>
</head>
<body>
	<%@include file="../frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<div class="col-md-8">
				<div class="col-md-12">
		            <div class="tab-content">
			            <div class="col-md-4" id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
				        	<img src='${hinhAnh }' class="img-thumbnail" width="100%" />
				        </div>
				        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
				        <div class="col-md-8">
				        	<h1 style="color:#2698eb !important;font-size:2em;"><s:property value="tour.tieuDe"></s:property></h1>
				        	<table class="table" style="color:#2698eb;border-style:hidden;">
				        		<tr>
				        			<td style="width:100px;padding:0px">Số ngày:</td>
				        			<td style="padding:0px"><s:property value="tourTrangChu.soNgayDem"></s:property></td>
				        		</tr>
				        		<tr>
				        			<td style="border-style:hidden;padding:0px">Khởi hành:</td>
				        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.ngayKhoiHanh"></s:property></td>
				        		</tr>
				        		<tr>
				        			<td style="border-style:hidden;padding:0px">Vận chuyển:</td>
				        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.ngayKhoiHanh"></s:property></td>
				        		</tr>
				        		<tr>
				        			<td style="border-style:hidden;padding:0px">Khách sạn:</td>
				        			<td style="border-style:hidden;padding:0px"><s:property value="tourTrangChu.khachSan"></s:property></td>
				        		</tr>
			        		</table>
			        	</div>
						<ul>
							<li style="color:#FF0000">Các khoản phí phát sinh (nếu có) như: phụ thu dành cho khách nước ngoài, việt kiều; phụ thu phòng đơn; phụ thu chênh lệch giá tour… Nhân viên Du Lịch Việt sẽ gọi điện thoại tư vấn cho quý khách ngay sau khi có phiếu xác nhận booking. (Trong giờ hành chính)</li>
							<li style="color:#FF0000">Trường hợp quý khách không đồng ý các khoản phát sinh, phiếu xác nhận booking của quý khách sẽ không có hiệu lực.</li>
						</ul>
		        		<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#input">Lịch trình tham khảo</a></li>
							<li ><a data-toggle="tab" href="#listds">Bảng giá chi tiết</a></li>
						</ul>
						<br/>
						<div class="tab-content">
							<div id="input" class="tab-pane fade in active">
								<s:property value="tour.lichTrinh"/>
							</div>
							<div id="listds" class="tab-pane fade" style="width:100%;">
								<table id="table" class="table table-striped table-bordered"  style="width:100%;">
							        <thead>
							            <tr>
							                <th style="text-align: center;">Người lớn(Trên 11 tuổi)</th>
							                <th style="text-align: center;">Trẻ em(5 - 11 tuổi)</th>
							                <th style="text-align: center;">Trẻ nhỏ(2 - 5 tuổi)</th>
							                <th style="text-align: center;;">Sơ sinh( < 2 tuổi)</th>
							            </tr>
							        </thead>
							       	<tfoot>
							       		 <tr>
							                <th style="text-align: center;"><s:property value="ctTour.giaVeNguoiLon"/></th>
							                <th style="text-align: center;"><s:property value="ctTour.getGiaVeTreEm()"/></th>
							                <th style="text-align: center;"><s:property value="ctTour.getGiaVeTreNho()"/></th>
							                <th style="text-align: center;"><s:property value="ctTour.getGiaVeSoSinh()"/></th>
							            </tr>
							        </tfoot>
							    </table>
							</div>
						</div>
						<div style="color:#e30050 !important;font-size:2em;"><h3>Thông tin liên hệ</h3></div>
						<div class="col-md-6">
							<div style="font-weight: bold;">Họ tên *:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
							<div style="font-weight: bold; margin-top: 10px">Số điện thoại *:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
						</div>
						<div class="col-md-6">
							<div style="font-weight: bold;">Email*:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
							<div style="font-weight: bold; margin-top: 10px">Địa chỉ *:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
						</div>
						<div class="col-md-12">
							<div style="font-weight: bold; margin-top: 10px">Ghi chú:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
						</div>
						<div class="col-md-6">
							<div style="font-weight: bold; margin-top: 10px">Người lớn:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
							<div style="font-weight: bold; margin-top: 10px">Trẻ nhỏ( 2 - < 5 tuổi):</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
						</div>
						<div class="col-md-6">
							<div style="font-weight: bold; margin-top: 10px">Trẻ em(5 - 11 tuổi)	:</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
							</div>
							<div style="font-weight: bold; margin-top: 10px">Sơ sinh(nhỏ hơn 2 tuổi):</div>
							<div>
								<s:textfield name="gv.soCMND" cssClass="form-control" cssStyle="border-radius: 3px" aria-describedby="basic-addon1" />
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