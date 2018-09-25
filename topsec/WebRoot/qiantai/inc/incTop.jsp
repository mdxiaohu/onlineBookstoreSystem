<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <style type="text/css">
          .Header {background: url(<%=path %>/img/banner.jpg) #d10e00 repeat-x left top; height: 70px;width: auto;}
          .HeaderTop {margin: 0px auto;}
      </style>
      <script type="text/javascript">
	        function myXinxi()
	        {
	            <s:if test="#session.user==null">
	                  alert("请先登录");
	            </s:if>
	            
	            <s:else>
	                var url="<%=path %>/qiantai/userinfo/userXinxi.jsp";
	                var n="";
	                var w="480px";
	                var h="500px";
	                var s="resizable:no;help:no;status:no;scroll:yes";
				    openWin(url,n,w,h,s);
	            </s:else>
	        }
	        function myCart()
	        {
	            <s:if test="#session.user==null">
	                  alert("请先登录");
	            </s:if>
	            
	            <s:else>
	                 var s="<%=path %>/myCart.action";
	                 window.location.href=s;
	            </s:else>
	        }
	        
	        function myOrder()
	        {
	            <s:if test="#session.user==null">
	                  alert("请先登录");
	            </s:if>
	            
	            <s:else>
	                 var s="<%=path %>/myOrder.action";
	                 window.location.href=s;
	            </s:else>
	        }
	        
	        function liuyanAll()
	        {
	            <s:if test="#session.user==null">
	                  alert("请先登录");
	            </s:if>
	            
	            <s:else>
	                 var url="<%=path %>/liuyanAll.action";
				     window.open(url,"_blank");
	            </s:else>
	        }
	        
	        						
			function startTime()   
			{   
				var today=new Date();//定义日期对象   
				var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年    
				var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年    
				var dd = today.getDate();//通过日期对象的getDate()方法返回年     
				var hh=today.getHours();//通过日期对象的getHours方法返回小时   
				var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟   
				var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒   
				// 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09   
				MM=checkTime(MM);
				dd=checkTime(dd);
				mm=checkTime(mm);   
				ss=checkTime(ss);    
				var day; //用于保存星期（getDay()方法得到星期编号）
				if(today.getDay()==0)   day   =   "星期日 " 
				if(today.getDay()==1)   day   =   "星期一 " 
				if(today.getDay()==2)   day   =   "星期二 " 
				if(today.getDay()==3)   day   =   "星期三 " 
				if(today.getDay()==4)   day   =   "星期四 " 
				if(today.getDay()==5)   day   =   "星期五 " 
				if(today.getDay()==6)   day   =   "星期六 " 
				document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"年"+MM +"月"+ dd +"日  " + hh+":"+mm+":"+ss+" " + day;   
				setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法 
			}   
			
			function checkTime(i)   
			{   
				if (i<10){
					i="0" + i;
				}   
				  return i;
			}  
		
			
      </script>
  </head>
  
  <body onload="startTime()">
     <!--    <div class="Header HeaderTop">
			<br/>
			<font style="font-size: 36px;color: red;font-weight: bolder;display: block;text-align: center;font-family: '隶书' ">积分兑换系统</font>
		</div> -->
		<div class="topmenu cbody1">
			<ul>
				<li class="thisclass">
					<A href="<%=path %>/qiantai/default.jsp">商城首页</A>
				</li>
				<li class="thisclass">
					<A href="#" onclick="myXinxi()">我的信息</A>
				</li>
				<li class="thisclass">
					<A href="#" onclick="myCart()">我的购物车</A>
				</li>
				<li class="thisclass">
					<A href="#" onclick="myOrder()">我的订单</A>
				</li>
				<li class="thisclass">
					<A href="#" onclick="liuyanAll()">我要留言</A>
				</li>
				<li style=" margin-top:8px ; float: right; width: 280px; font-family:仿宋; font-size: 14px ; ">
					<font color="white " ><span id="nowDateTimeSpan"></span></font>	
				</li>
				<li style=" margin-top:8px ; float: right; width: 700px; font-family:仿宋; font-size: 14px ; ">
					<font color="white " ><span > </span></font>	
				</li>
			</ul>
		</div>
		<form id="searchForm" action="<%=path %>/goodSearch.action" method="post">
			<div class="topsearch">
				<div class="title"></div>
				<div id="page_search_left">
					<input class="inputText" id="goodsName" size="16" onkeypress="if(event.keyCode==13){searchFormSubmit();return false;}" name="goodsName" type="text" />
				</div>
				<div id="page_search_btn">
					<input type="submit" value="搜索">
				</div>
				<!-- <div id="page_search_center"><iframe   width="300"  scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=10&icon=1&site=12"></iframe></div> -->
				<div id="page_search_right">
				<iframe style="margin-top:5px ;"  width="240"  scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=10&icon=1&site=12&py=xian"></iframe>
				</div>
				<div style="clear: both"></div>
			</div>
		</form>
  </body>
</html>
