<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>

	<body>
		 
		     <s:iterator value="#request.lianjieList" id="lianjie">
		         <a href="<s:property value="#lianjie.lianjieWeb"/>" title=""> <s:property value="#lianjie.lianjieName"/> </a> 
		     </s:iterator>
		 
	</body>
</html>
