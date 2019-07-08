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
	<script type="text/javascript" src="js1/list.js" ></script>
	<script type="text/javascript">
		function preEdit(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="wareHouse/preEdit.do";
			document.userList.submit();
		}
	</script>
  </head>
  <body>
    <jsp:include page="head.jsp"></jsp:include>
	<div id="subMenu">
		<h1 style="color:red">库存页面</h1>
		<div id="subMenuSon">
			<form action="<%=basePath%>wareHouse/queryWareHouseByCondition.do" name="userList">
				<input type="hidden" value="" name="id">
		  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		药品编码<input type="text" name="drug_id" style="width: 100px">
		  		药品名称<input type="text" name="drug_name" style="width: 100px">
		    		<input type="submit" id="box" value="查询">
		  		<br><br><br>
		   		 <table border="1" width="600px">
			    	<tbody>
			    		<tr>
			    			
			    			<th>药品编码</th>
			    			<th>药品名称</th>
			    			<th>库存量</th>
			    			<th>生产厂家</th>
			    			<th>规格</th>
			    			<th>零售价</th>
			    			<th>操作</th>
			    		</tr>
			    		<c:if test="${!empty list}">
			    			<c:forEach var="wareHouse" items="${list}">
			    				<tr>
			    					<td>${wareHouse.drug_id}</td>
			    					<td>${wareHouse.drug_name}</td>
			    					<td>${wareHouse.stock_number}</td>
			    					<td>${wareHouse.manufacturer}</td>
			    					<td>${wareHouse.standard}</td>
			    					<td>${wareHouse.sale_price}</td>
			    					<td>
			    						<input type="button" id="box" value="修改" onclick="preEdit(${wareHouse.id})">
			    					</td>
			    				</tr>
			    			</c:forEach>
			    		</c:if>
			    	</tbody>
		  		  </table>
		  	 </form>
		  	 <div class='page all'>
	           	   <b>共${pageUtil.pageNumber}</b>条,当前第<span>${pageUtil.pageIndex}</span>页
	               <a href="wareHouse/wareHouseList.do?pageIndex=1" class='first'>首页</a> 
	               <a href="wareHouse/wareHouseList.do?pageIndex=${pageUtil.pageIndex>1?pageUtil.pageIndex-1:1}" class='pre'>上一页</a>
	               <c:forEach begin="1" end="${pageUtil.pageCount}" var="i">  
	                 <a href="wareHouse/wareHouseList.do?pageIndex=${i}" style="text-decoration: none;">${i}</a>
	                </c:forEach>
	                <a href="wareHouse/wareHouseList.do?pageIndex=${pageUtil.pageIndex<pageUtil.pageCount?pageUtil.pageIndex+1:pageUtil.pageCount}" class='next'>下一页</a>
	                <a href="wareHouse/wareHouseList.do?pageIndex=${pageUtil.pageCount}" class='last'>末页</a>
       		 </div>
     	</div>
	</div>
  </body>
</html>