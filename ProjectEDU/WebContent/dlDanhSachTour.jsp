<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hiển thị danh sách tour</title>
<jsp:include page="css/style.html"/>
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
	<%@include file="frame/header.jsp"%>﻿
	<div class="container content">
		<div>
			<s:iterator value="listTours">
				<div class="col-md-4" style="padding: 0px 15px 0px 0px">
			      <div class="thumbnail">
			      	<div id="anhCaNhan" style="margin:0px 0px 10px 0px; cursor: pointer;" onclick="$('input[type=file]').click()">
			        	<img src="images/addImage.jpg" class="img-thumbnail" width="20%" />
			        </div>
			        <s:file name="hinhAnh" cssStyle="display:none;" accept="image/*"></s:file>
			        <!-- <a href="images/w3images/lights.jpg" target="_blank">
			          <img src="images/w3images/lights.jpg" alt="Lights" style="width:100%">
			         <div class="caption">
			            <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
			          </div>
			        </a> -->
			      </div>
			    </div>
			</s:iterator>
		</div>
		<div class="row">
			<h2>Tour miền Bắc</h2>
			  <div class="row">
			    <div class="col-md-4" style="padding: 0px 15px 0px 0px">
			      <div class="thumbnail">
			        <a href="images/w3images/lights.jpg" target="_blank">
			          <img src="images/w3images/lights.jpg" alt="Lights" style="width:100%">
			         <!-- <div class="caption"> -->
			            <!-- <p> -->Lorem ipsum donec id elit non mi porta gravida at eget metus.<!-- </p> -->
			          <!-- </div> -->
			        </a>
			      </div>
			    </div>
			    <div class="col-md-4" style="padding: 0px 15px 0px 0px">
			      <div class="thumbnail">
			        <a href="images/w3images/nature.jpg" target="_blank">
			          <img src="images/w3images/nature.jpg" alt="Nature" style="width:100%">
			          <!-- <div class="caption"> -->
			            <!-- <p> -->Lorem ipsum donec id elit non mi porta gravida at eget metus.<!-- </p> -->
			          <!-- </div> -->
			        </a>
			      </div>
			    </div>
			    <div class="col-md-4" style="padding: 0px 15px 0px 0px">
			      <div class="thumbnail">
			        <a href="images/w3images/fjords.jpg" target="_blank">
			          <img src="images/w3images/fjords.jpg" alt="Fjords" style="width:100%">
			          <!-- <div class="caption"> -->
			            <!-- <p> -->Lorem ipsum donec id elit non mi porta gravida at eget metus.<!-- </p> -->
			          <!-- </div> -->
			        </a>
			      </div>
			    </div>
			  </div>
			<%-- <%@include file="frame/right.jsp"%> --%>
		</div>
	</div>
	<%@include file="frame/footer.jsp"%>
</body>
</html>