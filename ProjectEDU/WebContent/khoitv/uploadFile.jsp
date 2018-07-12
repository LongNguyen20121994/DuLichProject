<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="/uploadFile.trip" method="post" enctype="multipart/form-data">
        	<s:file name="file"></s:file>
        	<s:submit></s:submit>
        </s:form>
    </body>
</html>
