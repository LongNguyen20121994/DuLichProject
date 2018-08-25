<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
﻿<div class="col-md-4 right" id="rightMenu">
    <ul>
    	<s:if test='#session.account=="1"'>
    		<%@include file="rightAdmin.jsp"%>
    	</s:if>
    	<s:elseif test='#session.account=="2"'>
    		<%@include file="rightNhanVien.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account=="3"'>
    		<%@include file="rightKhachHang.jsp"%>
    	</s:elseif>
    	<s:elseif test='#session.account!="1" && #session.account!="2" && #session.account!="3"'>
    		<%@include file="rightDungChung.jsp"%>
    	</s:elseif>
    </ul>
</div>