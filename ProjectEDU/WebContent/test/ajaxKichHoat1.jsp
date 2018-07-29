<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%-- <jsp:include page="css/style.html"/> --%>
<!-- table css + js-->
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<!-- /table -->
<script type="text/javascript">
	$(document).ready(function() {
	    $('#table').DataTable();
	});
</script>
<table id="table" class="table table-striped table-bordered"
	style="width: 100%;">
	<thead>
		<tr>
			<th style="text-align: center; width: 70px;">Số CMND</th>
			<th style="text-align: center;">Họ tên</th>
			<th style="text-align: center; width: 70px;">Ngày sinh</th>
			<th style="text-align: center; width: 65px;">Giới tính</th>
			<th style="text-align: center; width: 65px;">Kích hoạt</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th style="text-align: center;">Số CMND</th>
			<th style="text-align: center;">Họ tên</th>
			<th style="text-align: center;">Ngày sinh</th>
			<th style="text-align: center;">Giới tính</th>
			<th style="text-align: center;">Kích hoạt</th>
		</tr>
	</tfoot>
	<tbody>
		<s:if test='account == "1"'>
			<s:iterator id="list" value="listThiSinh">
				<tr>
					<td><s:a action="showInfoHienThi.trip?soCMND=%{soCMND}&&account=1">${soCMND}</s:a></td>
					<td><s:property value="hoTen" /></td>
					<td style="text-align: center;"><s:property value="ngaySinh" /></td>
					<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
					<td align="center"><s:checkbox name="listSoCMND" fieldValue="%{soCMND}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:if test='account == "2"'>
			<s:iterator id="list" value="listGiaoVien">
				<tr>
					<td><s:a action="showInfoHienThi.trip?soCMND=%{soCMND}&&account=2">${soCMND}</s:a></td>
					<td><s:property value="hoTen" /></td>
					<td style="text-align: center;"><s:property value="ngaySinh" /></td>
					<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
					<td align="center"><s:checkbox name="listSoCMND" fieldValue="%{soCMND}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:if test='account == "3"'>
			<s:iterator id="list" value="listGiangVien">
				<tr>
					<td><s:a action="showInfoHienThi.trip?soCMND=%{soCMND}&&account=3">${soCMND}</s:a></td>
					<td><s:property value="hoTen" /></td>
					<td style="text-align: center;"><s:property value="ngaySinh" /></td>
					<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
					<td align="center"><s:checkbox name="listSoCMND" fieldValue="%{soCMND}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:if test='account == "4"'>
			<s:iterator id="list" value="listQuanLyCumThi">
				<tr>
					<td><s:a action="showInfoHienThi.trip?soCMND=%{soCMND}&&account=4">${soCMND}</s:a></td>
					<td><s:property value="hoTen" /></td>
					<td style="text-align: center;"><s:property value="ngaySinh" /></td>
					<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
					<td align="center"><s:checkbox name="listSoCMND" fieldValue="%{soCMND}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:if test='account == "5"'>
			<s:iterator id="list" value="listQuanTriVien">
				<tr>
					<td><s:a action="showInfoHienThi.trip?soCMND=%{soCMND}&&account=5">${soCMND}</s:a></td>
					<td><s:property value="hoTen" /></td>
					<td style="text-align: center;"><s:property value="ngaySinh" /></td>
					<td align="center">${gioiTinh == true ? "Nam":"Nữ"}</td>
					<td align="center"><s:checkbox name="listSoCMND" fieldValue="%{soCMND}" /></td>
				</tr>
			</s:iterator>
		</s:if>
	</tbody>
</table>
<div align="right">
	<s:submit value="Kích hoạt" name="btnSua" cssClass="btn btn-primary" />
	<s:submit value="Xóa" name="btnXoa" cssClass="btn btn-primary" />
</div>