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
	style="background-color: #1f3136 !important; margin-bottom: 40px;">
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
					data-toggle="dropdown" role="button" aria-expanded="false">Thông tin tuyển sinh<span class="caret"></span></s:a>
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
				<li><s:form autocomplete="off" action="getListTour.trip">
					  <div class="autocomplete" style="width:300px;">
					    <input id="myInput" type="text" name="listGetName" placeholder="Tìm kiếm...">
					  </div>
					</s:form></li>
					<li>
					  <input type="submit"></li>
			</ul>
		</div>
	</div>
</nav>
<script>
function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
          b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
      });
}

/*An array containing all the country names in the world:*/
var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua & Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia & Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central Arfrican Republic","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre & Miquelon","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","St Kitts & Nevis","St Lucia","St Vincent","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Turks & Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"];

/*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
autocomplete(document.getElementById("myInput"), listGetName);
</script>
