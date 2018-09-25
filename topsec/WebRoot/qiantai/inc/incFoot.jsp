<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  
  <script type="text/javascript">

  </script>
  </head>
  
  <body>
        <div class="foot_pic"></div>
		<div class="left foot_msg">
			<a href="http://www.yunhe.cn/gsgk" target="_blank">关于我们</a>-
			<a href="<%=path %>/qiantai/lianjie/lianxiwomen.jsp" >联系我们</a>- <a href="http://www.yunhe.cn/joinus" target="_blank">诚聘英才</a> - <a href="#" >友情链接</a> -
			<a>程序下载</a>- <a href="http://www.yunhe.cn" target="_blank" >合作服务</a> -
			<a>许可协议</a>-
			<a>设为首页</a> -
			<a>加入收藏 </a>-
			<a target="_blank" href="<%=path %>/login.jsp">管理登录</a>
			<br />
			 <%-- <s:action name="lianjieQian" executeResult="true" flush="true"> </s:action>  --%>
			<br>
			
			电话：029-88888888 15389071915 &nbsp&nbsp&nbsp 地址：陕西省西安市高新区科技路高科大厦三楼
			<br />
		</div>
		<div style="clear: both"></div>
  </body>
</html>
