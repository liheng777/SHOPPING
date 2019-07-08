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
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/login.css" />
	<script type="text/javascript">
		function my(){
			var msg =document.getElementById("msg");
			alert(msg.value);
		}
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
  <body onload="my()">
  <c:if test="${!empty msg}">
  	<input type="hidden" id="msg" value='${msg}'>
  </c:if>
   <div id="bigBackground">
			<div id="loginBackground">
				<form action="<%=basePath%>login/login.do"  method="post" onsubmit="return myclick()">
					<div id="loginBackground1">
						用户名<input type="text" name="username"><br />
					</div>
					<div id="loginBackground2">
			    	密码&nbsp;&nbsp;&nbsp;<input type="password" name="password"><br />
			    	</div>
			    	<div id=loginBackground3 >
			    	类型&nbsp;&nbsp;&nbsp;<select name="usertype" style="width: 160">
			    		<option value="-1" >请选择用户类型</option>
			    		<option value="0" >管理员</option>
			    		<option value="1">销售人员</option>
			    		<option value="2">仓库管理员</option>
			    	</select>
			    	</div>
			    	<input type="submit" value="登录" id="loginBox" style="width: 200px">
		    	</form>
	    	</div>
	    </div>
	</body>
  </body>
</html>
