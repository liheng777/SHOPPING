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
    
    <title>My JSP 'stockDrugList.jsp' starting page</title>
    
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/datepicker/WdatePicker.js"></script> 
	<script type="text/javascript">
		function preAdd(){
			document.userList.action="<%=basePath%>saleDrug/preAdd.do";
			document.userList.submit();
		}
		function del(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="saleDrug/delete.do";
			document.userList.submit();
		}
		function preEdit(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="saleDrug/preEdit.do";
			document.userList.submit();
		}
		
	</script>
  </head>
  
  <body>
   	<c:if test="${!empty msg}">
	  	<input type="hidden" id="msg" value='${msg}'>
	 </c:if>
    <jsp:include page="head.jsp"></jsp:include>
    	
			<div id="subMenu">
				<h1 style="color:red">售药页面</h1>
				<div id="subMenuSon">
					<form action="<%=basePath%>saleDrug/querySaleDrugByCondition.do" name="userList">
						<input type="hidden" value="" name="id">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  		药品编码<input type="text" name="drug_id" style="width: 100px">
				  		销售日期<input type="text" name="date" style="width: 100px">
			    		<input type="submit" id="box" value="查询">
				  	<div id="subMenuSon2">
				  			<input type="button" id="box" onclick="preAdd()" value="增加">
				  	</div>
				  	<br><br><br>
				   		 <table border="1" width="600px">
					    	<tbody>
					    		<tr>
					    			<th>顾客号</th>
					    			<th>药品编码</th>
					    			<th>销售量</th>
					    			<th>销售日期</th>
					    			<th>单价</th>
					    			<th>操作</th>
					    		</tr>
					    		<c:if test="${!empty list}">
					    			<c:forEach var="saleDrug" items="${list}">
					    				<tr>
					    					<td>${saleDrug.customer_id}</td>
					    					<td>${saleDrug.drug_id}</td>
					    					<td>${saleDrug.sale_number}</td>
					    					<td>${saleDrug.date}</td>
					    					<td>${saleDrug.price}</td>
					    					<td>
					    						<input type="button" id="box" value="修改" onclick="preEdit(${saleDrug.id})">
					    						<input type="button" id="box" value="删除" onclick="del(${saleDrug.id})">
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