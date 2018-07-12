<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
﻿<div class="col-md-4 right" id="rightMenu"><!--content right---->
    <ul>
    	<s:if test='#session.account=="1"'>
    		<%@include file="rightTSTuDo.jsp"%>
    	</s:if>
    	<s:if test='#session.account=="2"'>
    		<%@include file="rightGiaoVien.jsp"%>
    	</s:if>
    	<s:elseif test='#session.account=="3"'>
    		<%@include file="rightGiangVien.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account=="4"'>
    		<%@include file="rightCumThi.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account=="5"'>
    		<%@include file="rightQuanTriVien.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account=="6"'>
    		<%@include file="rightAdmin.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account!="1" && #session.account!="2" && #session.account!="3" && #session.account!="4" && #session.account!="5" && #session.account!="6"'>
    		<%@include file="rightDungChung.jsp"%>
    	</s:elseif>
        <li class="listnone head">
            <ul style="padding-left:0px;">
                <li class="listnone item2 after list1"><span>Tin nóng - hot news <span class="caret"></span></span>
	                <span class="pull-right ">
		                <small><small>
		                	<i class="list1-turn" style="cursor: pointer;">Đóng</i>
		                </small></small>
	                </span>
                </li>
                <li class="listnone item2 list1-item"> <span style="color:green;">~</span> Quy chế tuyển sinh năm 2015</li>
                <li class="listnone item2 list1-item"> <span style="color:green;">~</span> Những điều cần biết khi tuyển sinh&nbsp;&nbsp;<span class="badge">NEW</span> </li>
                <li class="listnone item2 list1-item"> <span style="color:green;">~</span> Những đổi mới ở kỳ thi tiếp theo</li>
                <li class="listnone item2 list1-item"> <span style="color:green;">~</span> Phân bố các cụm thi</li>
                <li class="listnone item2 list1-item"> <span style="color:green;">~</span> Các diện ưu tiên&nbsp;&nbsp;<span class="badge">NEW</span> </li>
            </ul>
        </li>
        <hr>
        <li class="listnone head">
            <ul style="padding-left:0px;">
                <li class="listnone item after list2"><span>Trang cá nhân <span class="caret"></span></span>
                	<span class="pull-right ">
		                <small><small>
		                	<i class="list2-turn" style="cursor: pointer;">Đóng</i>
		                </small></small>
	                </span>
                </li>
                <li class="listnone item2 list2-item"> <span style="color:green;">~</span> Thí sinh tự do</li>
                <li class="listnone item2 list2-item"> <span style="color:green;">~</span> Giáo viên</li>
                <li class="listnone item2 list2-item"> <span style="color:green;">~</span> Giảng viên</li>
                <li class="listnone item2 list2-item"> <span style="color:green;">~</span> Quản lý cụm thi</li>
                <li class="listnone item2 list2-item"> <span style="color:green;">~</span> Quản trị viên</li>
            </ul>
        </li>
        <hr>
        <li class="listnone head">
            <ul style="padding-left:0px;">
                <li class="listnone item after list4"><span>Kế hoạch tuyển sinh 2016 <span class="caret"></span></span>
                	<span class="pull-right ">
		                <small><small>
		                	<i class="list4-turn" style="cursor: pointer;">Đóng</i>
		                </small></small>
	                </span>
                </li>
                <li class="listnone item2 list4-item"> <span style="color:green;">~</span> Hệ đại học chính quy</li>
                <li class="listnone item2 list4-item"> <span style="color:green;">~</span> Hệ đại học liên thông&nbsp;&nbsp;<span class="badge">NEW</span> </li>
                <li class="listnone item2 list4-item"> <span style="color:green;">~</span> Hệ cao đẳng chính quy&nbsp;&nbsp;<span class="badge">NEW</span> </li>
                <li class="listnone item2 list4-item"> <span style="color:green;">~</span> Hệ cao đẳng liên thông</li>
                <li class="listnone item2 list4-item"> <span style="color:green;">~</span> Hệ trung cấp chuyên nghiệp</li>
            </ul>
        </li>
        <hr>
        <li class="listnone head" style="margin-top:16px;">
            <ul style="padding-left:0px;">
                <li class="listnone item2 after list5"><span>Phản hồi của người dùng <span class="caret"></span></span>
                	<span class="pull-right ">
		                <small><small>
		                	<i class="list5-turn" style="cursor: pointer;">Đóng</i>
		                </small></small>
	                </span>
                </li>
                <li class="listnone item2 list5-item"> <span style="color:green;">~</span> Phản hồi của người dùng</li>
                <li class="listnone item2 list5-item"> <span style="color:green;">~</span> Về quản lí phục vụ&nbsp;&nbsp;<span class="badge">NEW</span> </li>
            </ul>
        </li>
    </ul>
</div>