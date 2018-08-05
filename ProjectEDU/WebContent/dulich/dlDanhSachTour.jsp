<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Du lịch Việt</title>
<jsp:include page="../css/style.html"/>

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
	<div style="margin: 0px 18px 22px 20px">
		<div class="row">
			<div style="margin: 0px 45px 5px 45px">
				<s:a style="float: right; margin-top: 11px; ">Xem thêm >></s:a>
				<h2  style="border-bottom: 1px solid #e30050;">Tour miền Bắc</h2>
			</div>
			<s:iterator value="listToursMB">
				<div class="col-md-3" style="padding: 0px 2px 0px 0px">
			      <div style="background: #e3e3e3">
			        <a href="images/w3images/lights.jpg" target="_blank">
			          <img src='${hinhAnh }' alt="Lights" style="width:100%;height: 220px">
			        </a>
			        <div style="margin: 3px">
			        	<div style="font-weight: bold; font-size: 16px; ">
				        <s:a action="showInfoTour.trip?maTour=%{maTour}"><s:property value="tieuDe"/></s:a></div>
				        <div style="width: 60%; height:100%; float: left; padding-left: 7px;">
					        <p class="glyphicon glyphicon-time" style="float: left; margin: 2px 5px 0px 0px; font-size: 13px" />
					        <div style="margin-bottom: 2px;"><s:property value="soNgayDem"/></div>
				        </div>
				        <div style="width: 100%;font-size: 17px; font-weight: bold; color: #e30050; margin: 0px; padding: 0px">
				        	<s:property value="giaVe"/>
				        </div>
				        <p class="glyphicon glyphicon-calendar" style="float: left; margin: 2px 5px 0px 7px; font-size: 13px" />
					    <div style="margin-bottom: 2px;">
					    	<s:property value="ngayKhoiHanh"/>
					    </div>
			        </div>
			      </div>
			    </div>
			</s:iterator>
		</div>
		
		<div class="row">
			<div style="margin: 0px 45px 5px 45px">
				<s:a style="float: right; margin-top: 11px; ">Xem thêm >></s:a>
				<h2 style="border-bottom: 1px solid #e30050;">Tour miền Trung</h2>
			</div>
			<s:iterator value="listToursMT">
				<div class="col-md-3" style="padding: 0px 2px 0px 0px">
			      <div style="background: #e3e3e3">
			        <a href="images/w3images/lights.jpg" target="_blank">
			          <img src='${hinhAnh }' alt="Lights" style="width:100%">
			        </a>
			        <div style="margin: 3px">
			        	<div style="font-weight: bold; font-size: 16px; ">
				        <s:a action="showInfoTour.trip?maTour=%{maTour}"><s:property value="tieuDe"/></s:a></div>
				        <div style="width: 60%; height:100%; float: left; padding-left: 7px;">
					        <p class="glyphicon glyphicon-time" style="float: left; margin: 2px 5px 0px 0px; font-size: 13px" />
					        <div style="margin-bottom: 2px;"><s:property value="soNgayDem"/></div>
				        </div>
				        <div style="width: 100%;font-size: 17px; font-weight: bold; color: #e30050; margin: 0px; padding: 0px">
				        	<s:property value="giaVe"/>
				        </div>
				        <p class="glyphicon glyphicon-calendar" style="float: left; margin: 2px 5px 0px 7px; font-size: 13px" />
					    <div style="margin-bottom: 2px;">
					    	<s:property value="ngayKhoiHanh"/>
					    </div>
			        </div>
			      </div>
			    </div>
			</s:iterator>
		</div>
		
		<div class="row">
			<div style="margin: 0px 45px 5px 45px">
				<s:a style="float: right; margin-top: 11px; ">Xem thêm >></s:a>
				<h2 style="border-bottom: 1px solid #e30050;">Tour miền Nam</h2>
			</div>
			<s:iterator value="listToursMN">
				<div class="col-md-3" style="padding: 0px 2px 0px 0px">
			      <div style="background: #e3e3e3">
			        <a href="images/w3images/lights.jpg" target="_blank">
			          <img src='${hinhAnh }' alt="Lights" style="width:100%">
			        </a>
			        <div style="margin: 3px">
			        	<div style="font-weight: bold; font-size: 16px; ">
				        <s:a action="showInfoTour.trip?maTour=%{maTour}"><s:property value="tieuDe"/></s:a></div>
				        <div style="width: 60%; height:100%; float: left; padding-left: 7px;">
					        <p class="glyphicon glyphicon-time" style="float: left; margin: 2px 5px 0px 0px; font-size: 13px" />
					        <div style="margin-bottom: 2px;"><s:property value="soNgayDem"/></div>
				        </div>
				        <div style="width: 100%;font-size: 17px; font-weight: bold; color: #e30050; margin: 0px; padding: 0px">
				        	<s:property value="giaVe"/>
				        </div>
				        <p class="glyphicon glyphicon-calendar" style="float: left; margin: 2px 5px 0px 7px; font-size: 13px" />
					    <div style="margin-bottom: 2px;">
					    	<s:property value="ngayKhoiHanh"/>
					    </div>
			        </div>
			      </div>
			    </div>
			</s:iterator>
		</div>
	</div>
	<%@include file="../frame/footer.jsp"%>
</body>
</html>