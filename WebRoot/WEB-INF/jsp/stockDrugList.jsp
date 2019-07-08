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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/datepicker/WdatePicker.js"></script> 
	<link rel="stylesheet" href="css/list.css" />
	<script type="text/javascript">
		function preAdd(){
			document.userList.action="<%=basePath%>stockDrug/preAdd.do";
			document.userList.submit();
		}
		function preEdit(id){
			document.getElementsByName("id")[0].value=id;
			document.userList.action="stockDrug/preEdit.do";
			document.userList.submit();
		}
	</script>
  </head>
  
  <body>
    <jsp:include page="head.jsp"></jsp:include>
			<div id="subMenu">
				<h1 style="color:red">进药页面</h1>
				<div id="subMenuSon">
					<form action="<%=basePath%>stockDrug/queryStockDrugByCondition.do" name="userList">
						<input type="hidden" value="" name="id">
				  		药品编码<input type="text" name="drug_id" style="width: 100px">
				  		采购日期<input type="text" name="date" style="width: 100px">
				  		供应商<input type="text" name="supplier" style="width: 100px">
			    		<input type="submit" id="box" value="查询">
				  	<div id="subMenuSon2">
				  			<input type="button" id="box" onclick="preAdd()" value="增加">
				  	</div>
				  	<br><br><br>
				   		 <table border="1" width="600px">
					    	<tbody>
					    		<tr>
					    			<th>单据号</th>
					    			<th>药品编码</th>
					    			<th>采购价</th>
					    			<th>采购数量</th>
					    			<th>采购日期</th>
					    			<th>供应商</th>
					    			<th>操作</th>
					    		</tr>
					    		<c:if test="${!empty list}">
					    			<c:forEach var="stockDrug" items="${list}">
					    				<tr>
					    					<td>${stockDrug.bill_id}</td>
					    					<td>${stockDrug.drug_id}</td>
					    					<td>${stockDrug.price}</td>
					    					<td>${stockDrug.buy_num}</td>
					    					<td>${stockDrug.date}</td>
					    					<td>${stockDrug.supplier}</td>
					    					<td>
					    						<input type="button" id="box" value="修改" onclick="preEdit(${stockDrug.id})">
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
