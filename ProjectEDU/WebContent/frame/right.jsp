<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
﻿<div class="col-md-4 right" id="rightMenu">
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
    </ul>
</div>