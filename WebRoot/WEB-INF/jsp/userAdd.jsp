<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
	<link rel="stylesheet" href="css/list.css" />
	<script type="text/javascript">
		function myclick(){
			var name = document.getElementsByName("username")[0].value;
			var password = document.getElementsByName("password")[0].value;
			var usertype = document.getElementsByName("usertype")[0].value;
			if(name.trim().length==0){
				alert("用户名不能为空");
				return false;
			}
			if(password==""){
				alert("密码不能为空");
				return false;
			}
			if(usertype<0){
				alert("请选择用户类型");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body >
	  <c:if test="${!empty msg}">
	  	<input type="hidden" id="msg" value='${msg}'>
	  </c:if>
	  <jsp:include page="head.jsp"></jsp:include>
	  
    <div id="subMenu">
    	<h1 style="color:red">用户增加页面</h1>
			 <form action="<%=basePath%>/user/addUser.do"  method="post" onsubmit="return myclick()">
				<div id="addBackground1">
					用户名<input type="text" name="username"><br />
				</div>
				<div id="addBackground2">
		    	密码&nbsp;&nbsp;&nbsp;<input type="text" name="password"><br />
		    	</div>
		    	<div id="addBackground3" >
		    	类型&nbsp;&nbsp;&nbsp;<select name="usertype" style="width: 160">
		    		<option value="-1" >请选择用户类型</option>
		    		<option value="0" >管理员</option>
		    		<option value="1">销售人员</option>
		    		<option value="2">仓库管理员</option>
		    	</select>
		    	</div>
		    	<input type="submit" value="增加" onclick="my()" id="addBox" style="width: 210px">
		    </form>
	</div>
  </body>
</html>

