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
    
    <title>My JSP 'updateUser.jsp' starting page</title>
    
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
	</script>
  </head>
  
  <body >
  	
   	 <c:if test="${!empty msg}">
	  	<input type="hidden" id="msg" value='${msg}'>
	  </c:if>
	  <jsp:include page="head.jsp"></jsp:include>
    
     <div id="subMenu">
	     <h1 style="color:red">人员修改页面</h1>
	     <form action="user/updateUser.do"  method="post">
	     	<input type="hidden" name="id" value="${user.id}"><br>
				<div id="addBackground1">
					用户名<input type="text" name="username" value="${user.username}"><br />
				</div>
				<div id="addBackground2">
					   密码&nbsp;&nbsp;&nbsp;<input type="text" name="password" value="${user.password}"><br />
				 </div>
				 <div id="addBackground3" >
				          类型&nbsp;&nbsp;&nbsp;<select name="usertype" style="width: 160">
				 <c:if test="${user.usertype==0}">
		    		<option value="0" selected="selected">管理员</option>
		    		<option value="1">销售人员</option>
		    		<option value="2">仓库管理员</option>
	    		</c:if>
	    		<c:if test="${user.usertype==1}">
		    		<option value="0" >管理员</option>
		    		<option value="1" selected="selected">销售人员</option>
		    		<option value="2">仓库管理员</option>
	    		</c:if>
	    		<c:if test="${user.usertype==2}">
		    		<option value="0" >管理员</option>
		    		<option value="1" >销售人员</option>
		    		<option value="2" selected="selected">仓库管理员</option>
	    		</c:if>
				</select>
		      </div>
			<input type="submit" value="修改" onclick="my()" id="addBox" style="width: 210px">
		 </form>
	</div>
  </body>
</html>
