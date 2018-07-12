<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<li class="listnone item2 list0-item" style="border-bottom: 1px solid #eee;">
	<h4 style="color: #428bca;">
		<span class="glyphicon glyphicon-th-large" style="font-size:large;"></span> Trang giáo viên
	</h4>
</li>
<li class="listnone item2 list0-item"><s:a action="showInfoHienThi.trip"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Xem thông tin cá nhân</s:a></li>
<li class="listnone item2 list0-item"><s:a action="showUploadDSDK.trip"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Upload danh sách đăng ký dự thi</s:a></li>
<li class="listnone item2 list0-item"><s:a action="showUpdateListThiSinhGV.trip"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Cập nhật danh sách đăng ký dự thi</s:a></li>
<li class="listnone item2 list0-item"><s:a action="downloadDanhSachMatKhau.trip"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Download danh sách thí sinh đã đăng ký</s:a></li>
<li class="listnone item2 list0-item"><a href="template/DanhSachThiSinhDangKy.xls" download><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Download mẫu upload danh sách thí sinh</a></li>
<li class="listnone item2 list0-item"><s:a action="showHuongDanDKDTGV.trip"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Hướng dẫn đăng ký dự thi</s:a></li>
<hr class="list0-item" />