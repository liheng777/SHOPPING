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
    
    <title>Cookie</title>
    <title>My JSP 'userList.jsp' starting page</title>
    
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
	<script type="text/javascript" src="js1/list.js" ></script>
	<script type="text/javascript">
		function preAdd(){
			document.userList.action="<%=basePath%>user/preAdd.do";
			document.userList.submit();
		}
		function preUpdate(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="user/preUpdate.do";
			document.userList.submit();
		}
		function del(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="user/delete.do";
			document.userList.submit();
		}
		
	</script>
  </head>
  
  <body >
	<jsp:include page="head.jsp"></jsp:include>
	<div id="subMenu">
		<h1 style="color:red">人员列表</h1>
		<div id="subMenuSon">
			<form action="<%=basePath%>user/queryUserByCondition.do" name="userList">
				<input type="hidden" value="" name="id">
		  		用户名<input type="text" name="username" style="width: 200px">
		  		用户类型<select name="usertype" style="width: 170px">
				  		<option value="-1">请选择用户类型</option>
				    	<option value="0">管理员</option>
				    	<option value="1">销售人员</option>
				    	<option value="2">仓库管理员</option>
		    		</select>
		    		<input type="submit" id="box" value="查询">
		  	<div id="subMenuSon2">
		  			<input type="button" id="box" onclick="preAdd()" value="增加用户">
		  	</div>
		  	<br><br><br>
		   		 <table border="1" width="600px">
			    	<tbody>
			    		<tr>
			    			<th>用户名</th>
			    			<th>密码</th>
			    			<th>用户类型</th>
			    			<th>操作</th>
			    		</tr>
			    		<c:if test="${!empty list}">
			    			<c:forEach var="user" items="${list}">
			    				<tr>
			    					<td>${user.username}</td>
			    					<td>${user.password}</td>
			    					<td>
			    						<c:if test="${user.usertype==0}">
			    							管理员
			    							<!-- <input type="text" value="管理员"> -->
			    						</c:if>
			    						<c:if test="${user.usertype==1}">
			    							销售人员
			    						<!-- 	<input type="text" value="销售人员"> -->
			    						</c:if>
			    						<c:if test="${user.usertype==2}">
			    							仓库管理员
			    							<!-- <input type="text" value="仓库管理员"> -->
			    						</c:if>
			    					</td>
			    					<td>
			    						<input type="button" id="box" value="修改" onclick="preUpdate(${user.id})">
			    						<input type="button" value="删除" id="box" onclick="del(${user.id})">
			    					</td>
			    				</tr>
			    			</c:forEach>
			    		</c:if>
			    	</tbody>
		  		  </table>
		  	 </form>
		</div>
	</div>
  </body>
</html>
