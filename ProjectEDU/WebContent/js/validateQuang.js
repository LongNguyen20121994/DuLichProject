//check null
function isNull(value) {
	if (value == "")
		return false;
	return true;
}
// check is email
function isEmail(email) {
	var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	if (!email.match(regex))
		return false;
	else
		return true;
}
// check borrower SoDT
function isNumber(id) {
	var regex = /^([0-9]+)$/;

	if (!id.match(regex))
		return false;
	else
		return true;
}

// check length SoDT
function checkLengthPhoneNo(id) {
	if (id.length < 8)
		return false;
	else
		return true;
}

// check SoCMND
function isSoCMND(id) {

	if (id.length > 8 && id.length < 11)
		return true;
	else
		return false;
}
// check length pass
function checkMatKhau(password) {
	if (password.length < 6)
		return false;
	return true;
}
// check confirm pass
function checkConfirmPassword(password, confirmPass) {
	if (password == confirmPass)
		return true;
	return false;
}

// check HoTen
function checkLetter(st) {
	var regex = /^[^0-9]+$/;

	if (!st.match(regex))
		return false;
	else
		return true;
}

// check length HoTen
function checkLengthHoTen(st) {
	if (st.length < 6 || st.length >= 100)
		return false;
	else
		return true;
}

// check date of birth
function checkDOB(date) {
	var d = new Date();
	var part = date.split("/");
	var n = d.getFullYear() - part[2];

	if (n > 17) {
		return true;
	}
	return false;
}

// Check JavaScript
// check error Dang ky giao vien
function validationDKGV() {
	var soCMND = "123456789";
	if ($('#soCMND').length > 0) {
		soCMND = $("#soCMND").val();
	}

	var hoTen = $("#hoTen").val();
	var dob = $("#datepicker").val();
	var maTinh = $("#maTinh").val();
	var maHuyen = $("#maHuyen").val();
	var maTinhTHPT = $("#maTinhTHPT").val();
	var truongTHPT = $("#truongTHPT").val();
	var email = $("#email").val();
	var soDT = $("#soDT").val();

	// check SoCMND
	if (document.getElementById("soCMND").style.borderColor != "red") {
		if (soCMND == "") {
			$("#errorSoCMND").text("Số chứng minh thư không được để trống!");
			$("#soCMND").css("border-color", "red");
		} else {
			if (!isSoCMND(soCMND)) {
				$("#errorSoCMND").text("Số chứng minh thư sai định dạng!");
				$("#soCMND").css("border-color", "red");
			} else {
				$("#errorSoCMND").text("");
				$("#soCMND").css("border-color", "#ccc");
			}
		}
	}

	// check hoTen
	if (hoTen == "") {
		$("#errorHoTen").text("Họ tên không được để trống!");
		$("#hoTen").css("border-color", "red");
	} else {
		if (!checkLetter(hoTen)) {
			$("#errorHoTen").text("Họ tên không được nhập số!");
			$("#hoTen").css("border-color", "red");
		} else {
			if (!checkLengthHoTen(hoTen)) {
				$("#errorHoTen").text("Họ tên phải lớn hơn 5 và nhỏ hơn 100 ký tự!");
				$("#hoTen").css("border-color", "red");
			} else {
				$("#errorHoTen").text("");
				$("#hoTen").css("border-color", "#ccc");
			}
		}
	}

	// check ngay sinh
	if (dob == "") {
		$("#errorDOB").text("Ngày sinh không được để trống!");
		$("#datepicker").css("border-color", "red");
	} else {
		if (!checkDOB(dob)) {
			$("#errorDOB").text("Bạn chưa đủ 18 tuổi!");
			$("#datepicker").css("border-color", "red");
		} else {
			$("#errorDOB").text("");
			$("#datepicker").css("border-color", "#ccc");
		}
	}

	// check combobox
	if (maTinh == "-1") {
		$("#errorTinh").text("Tỉnh không được để trống!");
		$("#maTinh").css("border-color", "red");
	} else {
		$("#errorTinh").text("");
		$("#maTinh").css("border-color", "#ccc");
	}

	if (maHuyen == "-1") {
		$("#errorHuyen").text("Huyện không được để trống!");
		$("#maHuyen").css("border-color", "red");
	} else {
		$("#errorHuyen").text("");
		$("#maHuyen").css("border-color", "#ccc");
	}

	// truong THPT
	if (maTinhTHPT == "-1") {
		$("#errorTinhTHPT").text("Tỉnh không được để trống!");
		$("#maTinhTHPT").css("border-color", "red");
	} else {
		$("#errorTinhTHPT").text("");
		$("#maTinhTHPT").css("border-color", "#ccc");
	}

	if (truongTHPT == "-1") {
		$("#errorTruong").text("Trường THPT không được để trống!");
		$("#truongTHPT").css("border-color", "red");
	} else {
		$("#errorTruong").text("");
		$("#truongTHPT").css("border-color", "#ccc");
	}

	// check so dien thoai
	if (soDT == "") {
		$("#errorSoDT").text("Số điện thoại không được để trống!");
		$("#soDT").css("border-color", "red");
	} else {
		if (!isNumber(soDT)) {
			$("#errorSoDT").text("Số điện thoại chỉ được nhập số!");
			$("#soDT").css("border-color", "red");
		} else {
			if (!checkLengthPhoneNo(soDT)) {
				$("#errorSoDT").text("Số điện thoại ít nhất là 8 số!");
				$("#soDT").css("border-color", "red");
			} else {
				$("#errorSoDT").text("");
				$("#soDT").css("border-color", "#ccc");
			}
		}
	}

	// check email
	if (email == "") {
		$("#errorEmail").text("Email không được để trống!");
		$("#email").css("border-color", "red");
	} else {
		if (!isEmail(email)) {
			$("#errorEmail").text("Email sai định dạng!");
			$("#email").css("border-color", "red");
		} else {
			$("#errorEmail").text("");
			$("#email").css("border-color", "#ccc");
		}
	}
}

// check form Dang ky giao vien
function checkDKGV() {
	validationDKGV();

	if ($('#soCMND').length > 0) {
		if (document.getElementById("soCMND").style.borderColor == "red") {
			$("#soCMND").focus();
			return false;
		}
	}

	if (document.getElementById("hoTen").style.borderColor == "red") {
		$("#hoTen").focus();
		return false;
	}

	if (document.getElementById("datepicker").style.borderColor == "red") {
		$("#datepicker").focus();
		return false;
	}

	if (document.getElementById("maTinh").style.borderColor == "red") {
		$("#maTinh").focus();
		return false;
	}

	if (document.getElementById("maHuyen").style.borderColor == "red") {
		$("#maHuyen").focus();
		return false;
	}

	if (document.getElementById("soDT").style.borderColor == "red") {
		$("#soDT").focus();
		return false;
	}

	if (document.getElementById("email").style.borderColor == "red") {
		$("#email").focus();
		return false;
	}

	if (document.getElementById("maTinhTHPT").style.borderColor == "red") {
		$("#maTinhTHPT").focus();
		return false;
	}

	if (document.getElementById("truongTHPT").style.borderColor == "red") {
		$("#truongTHPT").focus();
		return false;
	}
}

// check error Dang ky thi sinh
function validationDKTS() {
	var soCMND = "123456789";
	if ($('#soCMND').length > 0) {
		soCMND = $("#soCMND").val();
	}

	var hoTen = $("#hoTen").val();
	var dob = $("#datepicker").val();
	var maTinh = $("#maTinh").val();
	var maHuyen = $("#maHuyen").val();
	var email = $("#email").val();
	var soDT = $("#soDT").val();
	var danToc = $("#danToc").val();
	var noiSinh = $("#noiSinh").val();
	var diaChi = $("#diaChi").val();
	var namTN = $("#namTN").val();
	var maTinhTHPT10 = $("#maTinhTHPT10").val();
	var maTruong10 = $("#maTruong10").val();
	var maTinhTHPT11 = $("#maTinhTHPT11").val();
	var maTruong11 = $("#maTruong11").val();
	var maTinhTHPT12 = $("#maTinhTHPT12").val();
	var maTruong12 = $("#maTruong12").val();

	// check SoCMND
	if (document.getElementById("soCMND").style.borderColor != "red") {
		if (soCMND == "") {
			$("#errorSoCMND").text("Số chứng minh thư không được để trống!");
			$("#soCMND").css("border-color", "red");
		} else {
			if (!isSoCMND(soCMND)) {
				$("#errorSoCMND").text("Số chứng minh thư sai định dạng!");
				$("#soCMND").css("border-color", "red");
			} else {
				$("#errorSoCMND").text("");
				$("#soCMND").css("border-color", "#ccc");
			}
		}
	}

	// check hoTen
	if (hoTen == "") {
		$("#errorHoTen").text("Họ tên không được để trống!");
		$("#hoTen").css("border-color", "red");
	} else {
		if (!checkLetter(hoTen)) {
			$("#errorHoTen").text("Họ tên không được nhập số!");
			$("#hoTen").css("border-color", "red");
		} else {
			if (!checkLengthHoTen(hoTen)) {
				$("#errorHoTen").text(
						"Họ tên phải lớn hơn 5 và nhỏ hơn 100 ký tự!");
				$("#hoTen").css("border-color", "red");
			} else {
				$("#errorHoTen").text("");
				$("#hoTen").css("border-color", "#ccc");
			}
		}
	}

	// check ngay sinh
	if (dob == "") {
		$("#errorDOB").text("Ngày sinh không được để trống!");
		$("#datepicker").css("border-color", "red");
	} else {
		if (!checkDOB(dob)) {
			$("#errorDOB").text("Bạn chưa đủ 18 tuổi!");
			$("#datepicker").css("border-color", "red");
		} else {
			$("#errorDOB").text("");
			$("#datepicker").css("border-color", "#ccc");
		}
	}

	// check ho khau
	if (maTinh == "-1") {
		$("#errorTinh").text("Tỉnh không được để trống!");
		$("#maTinh").css("border-color", "red");
	} else {
		$("#errorTinh").text("");
		$("#maTinh").css("border-color", "#ccc");
	}

	if (maHuyen == "-1") {
		$("#errorHuyen").text("Huyện không được để trống!");
		$("#maHuyen").css("border-color", "red");
	} else {
		$("#errorHuyen").text("");
		$("#maHuyen").css("border-color", "#ccc");
	}

	// check học truong THPT 10
	if (maTinhTHPT10 == "-1") {
		$("#errorTinh10").text(
				"Tỉnh học trường THPT lớp 10 không được để trống!");
		$("#maTinhTHPT10").css("border-color", "red");
	} else {
		$("#errorTinh10").text("");
		$("#maTinhTHPT10").css("border-color", "#ccc");
	}

	if (maTinhTHPT10 == "-1") {
		$("#errorTruong10").text("Trường THPT lớp 10 không được để trống!");
		$("#maTruong10").css("border-color", "red");
	} else {
		$("#errorTruong10").text("");
		$("#maTruong10").css("border-color", "#ccc");
	}

	// check học truong THPT 11
	if (maTinhTHPT11 == "-1") {
		$("#errorTinh11").text(
				"Tỉnh học trường THPT lớp 11 không được để trống!");
		$("#maTinhTHPT11").css("border-color", "red");
	} else {
		$("#errorTinh11").text("");
		$("#maTinhTHPT11").css("border-color", "#ccc");
	}

	if (maTruong11 == "-1") {
		$("#errorTruong11").text("Trường THPT lớp 11 không được để trống!");
		$("#maTruong11").css("border-color", "red");
	} else {
		$("#errorTruong11").text("");
		$("#maTruong11").css("border-color", "#ccc");
	}

	// check học truong THPT 12
	if (maTinhTHPT12 == "-1") {
		$("#errorTinh12").text(
				"Tỉnh học trường THPT lớp 12 không được để trống!");
		$("#maTinhTHPT12").css("border-color", "red");
	} else {
		$("#errorTinh12").text("");
		$("#maTinhTHPT12").css("border-color", "#ccc");
	}

	if (maTruong12 == "-1") {
		$("#errorTruong12").text("Trường THPT lớp 12 không được để trống!");
		$("#maTruong12").css("border-color", "red");
	} else {
		$("#errorTruong12").text("");
		$("#maTruong12").css("border-color", "#ccc");
	}

	// check so dien thoai
	if (soDT == "") {
		$("#errorSoDT").text("Số điện thoại không được để trống!");
		$("#soDT").css("border-color", "red");
	} else {
		if (!isNumber(soDT)) {
			$("#errorSoDT").text("Số điện thoại chỉ được nhập số!");
			$("#soDT").css("border-color", "red");
		} else {
			if (!checkLengthPhoneNo(soDT)) {
				$("#errorSoDT").text("Số điện thoại ít nhất là 8 số!");
				$("#soDT").css("border-color", "red");
			} else {
				$("#errorSoDT").text("");
				$("#soDT").css("border-color", "#ccc");
			}
		}
	}

	// check email
	if (email == "") {
		$("#errorEmail").text("Email không được để trống!");
		$("#email").css("border-color", "red");
	} else {
		if (!isEmail(email)) {
			$("#errorEmail").text("Email sai định dạng!");
			$("#email").css("border-color", "red");
		} else {
			$("#errorEmail").text("");
			$("#email").css("border-color", "#ccc");
		}
	}

	// check dan toc
	if (danToc == "") {
		$("#errorDanToc").text("Dân tộc không được để trống!");
		$("#danToc").css("border-color", "red");
	} else {
		$("#checkLetter").text("");
		$("#danToc").css("border-color", "#ccc");
	}

	// check noi sinh
	if (noiSinh == "") {
		$("#errorNoiSinh").text("Nơi sinh không được để trống!");
		$("#noiSinh").css("border-color", "red");
	} else {
		$("#errorNoiSinh").text("");
		$("#noiSinh").css("border-color", "#ccc");
	}

	// check dia chi
	if (diaChi == "") {
		$("#errorDiaChi").text("Địa chỉ liên hệ không được để trống!");
		$("#diaChi").css("border-color", "red");
	} else {
		$("#errorDiaChi").text("");
		$("#diaChi").css("border-color", "#ccc");
	}

	// check nam tot nghiep
	if (namTN == "") {
		$("#errorNamTN").text("Năm tốt nghiệp không được để trống!");
		$("#namTN").css("border-color", "red");
	} else {
		if (!isNumber(namTN)) {
			$("#errorNamTN").text("Năm tốt nghiệp chỉ được nhập số!");
			$("#namTN").css("border-color", "red");
		} else {
			var ns = new Date();
			var y = ns.getFullYear();
			if (namTN > y) {
				$("#errorNamTN").text("Năm tốt nghiệp không hợp lệ!");
				$("#namTN").css("border-color", "red");
			} else {
				$("#errorNamTN").text("");
				$("#namTN").css("border-color", "#ccc");
			}
		}
	}
}

// check form Dang ky giao vien
function checkDKTS() {
	validationDKTS();

	if ($('#soCMND').length > 0) {
		if (document.getElementById("soCMND").style.borderColor == "red") {
			$("#soCMND").focus();
			return false;
		}
	}

	if (document.getElementById("hoTen").style.borderColor == "red") {
		$("#hoTen").focus();
		return false;
	}

	if (document.getElementById("datepicker").style.borderColor == "red") {
		$("#datepicker").focus();
		return false;
	}

	if (document.getElementById("maTinh").style.borderColor == "red") {
		$("#maTinh").focus();
		return false;
	}

	if (document.getElementById("maHuyen").style.borderColor == "red") {
		$("#maHuyen").focus();
		return false;
	}

	if (document.getElementById("soDT").style.borderColor == "red") {
		$("#soDT").focus();
		return false;
	}

	if (document.getElementById("email").style.borderColor == "red") {
		$("#email").focus();
		return false;
	}

	if (document.getElementById("danToc").style.borderColor == "red") {
		$("#danToc").focus();
		return false;
	}

	if (document.getElementById("noiSinh").style.borderColor == "red") {
		$("#noiSinh").focus();
		return false;
	}

	if (document.getElementById("diaChi").style.borderColor == "red") {
		$("#diaChi").focus();
		return false;
	}

	if (document.getElementById("namTN").style.borderColor == "red") {
		$("#namTN").focus();
		return false;
	}

	if (document.getElementById("maTinhTHPT10").style.borderColor == "red") {
		$("#maTinhTHPT10").focus();
		return false;
	}

	if (document.getElementById("maTruong10").style.borderColor == "red") {
		$("#maTruong10").focus();
		return false;
	}

	if (document.getElementById("maTinhTHPT11").style.borderColor == "red") {
		$("#maTinhTHPT11").focus();
		return false;
	}

	if (document.getElementById("maTruong11").style.borderColor == "red") {
		$("#maTruong11").focus();
		return false;
	}

	if (document.getElementById("maTinhTHPT12").style.borderColor == "red") {
		$("#maTinhTHPT12").focus();
		return false;
	}

	if (document.getElementById("maTruong12").style.borderColor == "red") {
		$("#maTruong12").focus();
		return false;
	}
}
