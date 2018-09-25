<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8" />
		<title>联系我们</title>
		<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
		<link rel="stylesheet" href="../../css/lianxiwomen.css" />
		
		<script language="JavaScript" src="../../js/lianxiwomen.js" rel="stylesheet"></script>
		<link href="<%=path %>/css/layout.css" type="text/css" rel="stylesheet" />
		
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>

	</head>

	<body >
<jsp:include  flush="true" page="/qiantai/inc/incTop.jsp"></jsp:include>
		<!--中部-->
		<div class="content">
			<hr style="border-top:3px solid #E0E0E0;margin-top: 0;">
			<div class="content_left">
				<div class="content_left_1">
					<h2>留言板</h2>
					<form action="#" id="myform">
						<table>
							<tr>
								<td>用户账号:</td>
								<td>&nbsp;&nbsp;<input type="text" id="userid" name="userid" style="width:198px; height:30px;" required="required" />
								</td>
							</tr>
							<tr>
								<td>电话号码:</td>
								<td>&nbsp;&nbsp;<input type="tel" id="tel" name="tel" style="width:198px; height:30px;" required="required" pattern="^1[34578]\d{9}$" title="格式不正确" /></td>

							</tr>
							<tr>
								<td>用户邮箱:</td>
								<td>&nbsp;&nbsp;<input type="email" id="email" name="email" style="width:198px; height:30px;" required="required"></td>

							</tr>
							<tr>
								<td>留言信息:</td>
								<td>&nbsp;&nbsp;<input type="textarea" value="" id="message" style="width:198px; height:160px;" required="required"></td>

							</tr>
							
							
							<tr>
							
								<td colspan="2"  style="text-align: center;padding-top: 30px">&nbsp;&nbsp;<button type="submit" id="submit" onclick="submitMessage();">提交</button>
							<button type="button" onclick="dealReset();" id="reset">重置</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="content_left_2">

				</div>
			</div>
			<div class="content_right">

				<div class="content_right_1">
					<ul>
						<li>电话：029-88888888</li>
						<li>官方网址：www.yunhe.cn</li>
						<li>地址：西安市高新区科技路高科大厦</li>
					</ul>
				</div>
				<div class="content_right_2">
					<div style="width:100%;height:600px;border:#ccc solid 1px;margin-top:-30px;font-size:12px" id="map"></div>
				</div>
			</div>
		</div>
		<hr style="border-top:5px solid DarkGray;margin-top: 0;">
<div class="foot">
		   <jsp:include flush="true" page="/qiantai/inc/incFoot.jsp"></jsp:include>
		  
	    </div>

	</body>
	<script type="text/javascript">
		initMap();
	</script>
	<script type="text/javascript">
		function submitMessage() {
			var form = document.forms[0];
			var value = {};
			for(var i = 0, len = form.elements.length; i < len; i++) {
				var el = form.elements[i];
				if(/text|tel|email|textArea/.test(el.type)) {
					value[el.name] = el.value;
				}
			}
		}
		function dealReset(){
			var userid = document.getElementById("userid");
			var tel = document.getElementById("tel");
			var email = document.getElementById("email");
			var message = document.getElementById("message");
			var user = JSON.parse(sessionStorage.getItem("logineduser"));
			if(user!=null){
				email.value="";
				message.value="";
			}else{
				userid.value="";
				tel.value="";
				email.value="";
				message.value="";
			}
		}
	</script>
</html>