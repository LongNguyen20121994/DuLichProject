<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hiển thị danh sách tour</title>
<jsp:include page="css/style.html"/>
</head>
<body>
	<%@include file="frame/header.jsp"%>﻿
	<div class="container content">
		<div class="row">
			<h2>Tour miền Bắc</h2>
			  <div class="row">
			    <div class="col-md-4">
			      <div class="thumbnail">
			        <a href="images/w3images/lights.jpg" target="_blank">
			          <img src="images/w3images/lights.jpg" alt="Lights" style="width:100%">
			         <!-- <div class="caption"> -->
			            <!-- <p> -->Lorem ipsum donec id elit non mi porta gravida at eget metus.<!-- </p> -->
			          <!-- </div> -->
			        </a>
			      </div>
			    </div>
			    <div class="col-md-4">
			      <div class="thumbnail">
			        <a href="images/w3images/nature.jpg" target="_blank">
			          <img src="images/w3images/nature.jpg" alt="Nature" style="width:100%">
			          <!-- <div class="caption"> -->
			            <!-- <p> -->Lorem ipsum donec id elit non mi porta gravida at eget metus.<!-- </p> -->
			          <!-- </div> -->
			        </a>
			      </div>
			    </div>
			    <div class="col-md-4">
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