<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
		
        <script language="javascript">
           function lianjieAdd()
           {
              var url="<%=path %>/admin/lianjie/lianjieAdd.jsp";
              window.location.href=url;
           }
           
           function lianjieDetail(lianjieId)
           {
                 var url="<%=path %>/lianjieDetail.action?lianjieId="+lianjieId;
                 var n="";
                 var w="480px";
                 var h="500px";
                 var s="resizable:no;help:no;status:no;scroll:yes";
				 openWin(url,n,w,h,s);
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="14" background="<%=path %>/img/tbg.gif">&nbsp;公告&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="40%">名称</td>
					<td width="10%">链接</td>
					 <td width="10%">操作</td>
				 
		        </tr>	
				<s:iterator value="#request.lianjieList" id="lianjie" status="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
					 <s:property value="#ss.index+1"/> 
						
					</td>
					<td bgcolor="#FFFFFF" align="center">
						
						 ${lianjie.lianjieName } <%--  <s:property value="#lianjie.lianjieName"/>  --%>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					  <%--  <s:property value="#lianjie.lianjieWeb"/>  --%>
					    ${lianjie.lianjieWeb }
					</td>
					 
					<td bgcolor="#FFFFFF" align="center">
						<a class="pn-loperator" href="<%=path %>/lianjieDel.action?lianjieId=${lianjie.lianjieId }">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="lianjieAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
