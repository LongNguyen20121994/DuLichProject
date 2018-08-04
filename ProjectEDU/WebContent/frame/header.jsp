<%@taglib prefix="s" uri="/struts-tags" %>
<%@page errorPage="info.jsp" contentType="text/html" pageEncoding="UTF-8" %>
<div class="row" id="slide" style="margin-left: 0px; margin-right: 0px;">
	<div class="col-md-12 block-slide" height="250"
		style="padding-left: 0px; padding-right: 0px;">
		<div id="carousel-example-generic" data-interval="false"
			class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"
					class=""></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"
					class=""></li>
			</ol>
			<script type="text/javascript">
				function change(){
				    $('#next').click();
				}
				setInterval(function(){ change(); }, 5000);
			</script>
			<div class="carousel-inner" role="listbox">
				<div class="item active" style="margin-top: 0px; color:">
					<img alt="First slide" src="images/head1.jpg"
						data-holder-rendered="true" width="100%">
					<div class="container">
						<div class="carousel-caption" id="list1">
							<h1 class="title">Các vấn đề đã và đang được thực hiện để tuyên truyền và phổ biến về ...</h1>
							<p>Các chính sách nhà nước đưa ra nhằm bổ sung vào các ...</p>
							<p>
								<s:a cssClass="btn btn-lg btn-primary bt-chitiet" href="#"
									role="button">Chi tiết</s:a>
							</p>
						</div>
					</div>
				</div>
				<div class="item" style="margin-top: 0px;">
					<img alt="Second slide" src="images/head1.jpg"
						data-holder-rendered="true" width="100%">
					<div class="container">
						<div class="carousel-caption">
							<h1 class="title">Các trường trung học phổ thông đã sẵn sàng để tiến thẳng tới kỳ thi năm nay</h1>
							<p>Mặc dù bỡ ngỡ về các điều khoản đổi mới trong năm nay nhưng ở bậc trung học đã ...</p>
							<p>
								<s:a cssClass="btn btn-lg btn-primary bt-chitiet" href="#"
									role="button">Chi tiết</s:a>
							</p>
						</div>
					</div>
				</div>
				<div class="item" style="margin-top: 0px;">

					<img alt="Third slide" src="images/head1.jpg"
						data-holder-rendered="true" width="100%">
					<div class="container">
						<div class="carousel-caption">
							<h1 class="title">Những thay đổi mang tính đột phá trong kỳ thi tuyển sinh sắp tới</h1>
							<p>Các điểm khác biệt so với năm trước ...</p>
							<p>
								<s:a cssClass="btn btn-lg btn-primary bt-chitiet" href="#"
									role="button">Chi tiết</s:a>
							</p>
						</div>
					</div>
				</div>

			</div>
			<s:a cssClass="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></s:a>
			<s:a cssClass="right carousel-control" href="#carousel-example-generic"
				data-slide="next" id="next"><span
				class="glyphicon glyphicon-chevron-right"></span></s:a>
		</div>
	</div>
</div>

<nav class="navbar navbar-static-top"
	style="background-color: #075d77 !important; margin-bottom: 40px;">
	<div class="container" style="position: relative;">
		<div class="navbar-header">
			<span class="navbar-toggle collapsed glyphicon glyphicon-th"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar" style="color:white; cursor:pointer; font-size: x-large; margin:1px;">
				<span class="sr-only">Toggle navigation</span>
			</span>
			<s:a cssClass="navbar-brand home" href="/ProjectEDU"><span class="glyphicon glyphicon-home" style="color: white;"></span></s:a>
			<s:if test="#session.soCMND == null">
				<span class="navbar-brand account">
					<s:a action="showLogin.trip" cssStyle="color:white;">Đăng nhập</s:a>
				</span>
			</s:if>
			<s:if test="#session.soCMND != null">
				<span class="navbar-brand account" id="account">
					<img alt="hình đại diện" src="${session.hinhAnh}" class="img-circle img" 
						style="margin-top: -5px;" width="30px" height="30px"/> 
						<s:property value="#session.hoTen" />
				</span>
				<div class="showInfoAccout">
					<s:a href="#" cssClass="linkAccout">
						<b>
							<span class="glyphicon glyphicon-edit" style="position: relative; top:2px;"></span>
							<s:property value="#session.hoTen" />
						</b>
		            </s:a><br/>
					<img alt="hình đại diện" src="${session.hinhAnh}" class="img-circle img"/>
					<s:a action="showInfoHienThi.trip" cssClass="btn btn-block btn-info btn-flat">
	                    Thông tin cá nhân
	                    <i class="glyphicon glyphicon-user"></i>
	                </s:a>
	                <s:a action="showDoiMatKhau.trip" cssClass="btn btn-block btn-info btn-flat">
	                    Đổi mật khẩu
	                    <i class="glyphicon glyphicon-edit"></i>
	                </s:a>
	                <s:a action="logout.trip" cssClass="btn btn-block btn-info btn-flat">
	                    Thoát tài khoản
	                    <i class="glyphicon glyphicon-off"></i>
	                </s:a>
				</div>
			</s:if>
		</div>
		<script type="text/javascript">
		    $(document).ready(function() {
		        $("#account").click(function() {
		            $(".showInfoAccout").toggle(500);
		        });
		    });
		</script>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="home-mb"><s:a cssStyle="color: white;">Trang chủ</s:a></li>
				<li class="dropdown"><s:a href="#" cssClass="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Giới thiệu <!--<span class="caret">--></span></s:a>
<!-- 					<ul class="dropdown-menu" role="menu">  -->
<%-- 						<li><s:a href="#">Thông điệp của Hiệu trưởng</s:a></li> --%>
<%-- 						<li><s:a href="#">Lịch sử phát triển</s:a></li> --%>
<%-- 						<li><s:a href="#">Sứ mạng tầm nhìn</s:a></li> --%>
<%-- 						<li><s:a href="#">Cơ cấu tổ chức</s:a></li> --%>
<%-- 						<li><s:a href="#">Hội đồng Khoa học & Ðào tạo</s:a></li> --%>
<%-- 						<li><s:a href="#">Tổng quan các chuyên ngành</s:a></li> --%>
<!-- 					</ul> -->
				</li>
				<li class="dropdown"><s:a href="#" cssClass="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Thông tin du lịch<span class="caret"></span></s:a>
					<ul class="dropdown-menu" role="menu">
						<li><s:a action="showListDaiHocCaoDang.trip">Danh sách các trường ĐH-CĐ</s:a></li>
						<li><s:a href="#">Quy chế tuyển sinh</s:a></li>
						<li><s:a href="#">Chế độ và chính sách</s:a></li>
					</ul></li>
				<li class="dropdown"><s:a href="#" cssClass="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Hướng dẫn thủ tục<span class="caret"></span></s:a>
					<ul class="dropdown-menu" role="menu">
						<li><s:a action="showDangKyDuThiTHPT.trip">Đăng ký dự thi</s:a></li>
						<li><s:a action="showDangKyXetTuyen.trip">Đăng ký xét tuyển</s:a></li>
<%-- 						<li><s:a href="#">Xem thông tin dự thi</s:a></li> --%>
<%-- 						<li><s:a href="#">Nhận giấy báo dự thi</s:a></li> --%>
					</ul></li>
					<%-- <li>
	        			<div class="input-group" style="width: 300px; margin-top: 6px">
							<s:select list="listTinh" name="pt.maTinh" cssClass="form-control" aria-describedby="basic-addon1" headerKey="-1" headerValue="Tìm kiếm ..."/>
						</div>
        			</li> --%>
			</ul>
		</div>
	</div>
</nav>
