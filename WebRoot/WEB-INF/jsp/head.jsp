<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Cookie[] cArr = request.getCookies();
	String name="";
	if(cArr!=null){
		for(Cookie c : cArr){
			if("loginName".equals(c.getName())){
				name= URLDecoder.decode(c.getValue(),"utf-8");
			}
		}
	}
 %>
 <script type="text/javascript">
	function menuClick(url ,obj){
		var elt= obj.parentNode;
		var menuIndex = [].indexOf.call(elt.parentNode.querySelectorAll(elt.tagName),elt);
		document.cookie = "MENUFLAG="+menuIndex+";path=/";
		document.getElementById("menuForm").action = url;
		document.getElementById("menuForm").submit();
		
	}
	window.onload = function(){
		my1();
		my();
	} 
	function my(){
		var msg =document.getElementById("msg");
		alert(msg.value);
	}
	function my1(){
		var index;
		var strcookie = document.cookie;//获取cookie字符串
		var arrcookie = strcookie.split("; ");//分割
		//遍历匹配
		for ( var i = 0; i < arrcookie.length; i++) {
			var arr = arrcookie[i].split("=");
			if (arr[0] == "MENUFLAG"){
				index = arr[1];
			}
		}
		$("#list li").eq(index).addClass("bgred").siblings().removeClass("bgred");
	}
</script>
 <form id="menuForm" action=""></form>
  <div id="loginBox">
			<div id="logonBox">
				企业logon，说明
			</div>
			
			<div id="cookieBox">
				欢迎， <%=name %> 访问药物管理系统<br>
			</div>
			<div id="logoutBox">
				<a href="login/logout.do">退出</a>
			</div>
	</div>
	
	<div id="mainMenu">
		<div id="menuName">菜单</div><br>
		<ul id="list">
		<c:if test="${cookie['userType'].value==0}">
			<li><a href="javascript:void(0)" onclick="menuClick('user/userList.do',this)">人员管理</a></li><br>
			<li><a href="javascript:void(0)" onclick="menuClick('stockDrug/stockDrugList.do',this)">进药管理</a></li><br>
			<li><a href="javascript:void(0)" onclick="menuClick('saleDrug/saleDrugList.do',this)">售药管理</a></li><br>
			<li><a href="javascript:void(0)" onclick="menuClick('wareHouse/wareHouseList.do',this)">库存管理</a></li><br>
		</c:if>
		<c:if test="${cookie['userType'].value==1}">
			<li><a href="javascript:void(0)" onclick="menuClick('saleDrug/saleDrugList.do',this)">售药管理</a></li><br>
			<li><a href="javascript:void(0)" onclick="menuClick('wareHouse/wareHouseList.do',this)">库存管理</a></li><br>
		</c:if>
		<c:if test="${cookie['userType'].value==2}">
			<li><a href="javascript:void(0)" onclick="menuClick('stockDrug/stockDrugList.do',this)">进药管理</a></li><br>
			<li><a href="javascript:void(0)" onclick="menuClick('wareHouse/wareHouseList.do',this)">库存管理</a></li><br>
		</c:if> 
		</ul>
	</div>
	
