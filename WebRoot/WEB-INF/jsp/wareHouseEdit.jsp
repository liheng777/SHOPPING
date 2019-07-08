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
		function myclick(){
			var drug_name = document.getElementsByName("drug_name")[0].value;
			var manufacturer = document.getElementsByName("manufacturer")[0].value;
			var standard = document.getElementsByName("standard")[0].value;
			var sale_price = document.getElementsByName("sale_price")[0].value;
			if(drug_name==""||drug_name==null){
				alert("药品名称不能为空");
				return false;
			}
			if(manufacturer==""||manufacturer==null){
				alert("生产厂家不能为空");
				return false;
			}
			if(standard==""||standard==null){
				alert("规格不能为空");
				return false;
			}
			if(sale_price<=0 || sale_price>111111){
				alert("零售价必须大于0小于111111");
				return false;
			}
			 if(sale_price==""||sale_price==null || isNaN(sale_price)){
				alert("请输入正确的零售价");
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
     	<h1 style="color:red">库存修改页面</h1>
    	 <form action="<%=basePath%>/wareHouse/edit.do"  method="post" onsubmit="return myclick()">
     		<input type="hidden" name="id" value="${wareHouse.id}"><br>
			<div id="addBackground4">
				药品编码:&nbsp;&nbsp;&nbsp;<input type="text" value="${wareHouse.drug_id}" readonly="readonly" >
			</div>
			<div id="addBackground5">
		    	药品名称:&nbsp;&nbsp;&nbsp;<input type="text" name="drug_name" value="${wareHouse.drug_name}"><br />
		    </div>
		    <div id="addBackground6" >
		    	库存量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${wareHouse.stock_number}" readonly="readonly" >
		    </div>
		    <div id="addBackground1" >
		    	生产厂家:&nbsp;&nbsp;&nbsp;<input type="text" name="manufacturer" value="${wareHouse.manufacturer}"><br />
		    </div>
		    <div id="addBackground2" >	
		    	规格:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="standard" value="${wareHouse.standard}"><br />
		    </div>
		    	<div id="addBackground3" >
		    	零售价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name=sale_price value="${wareHouse.sale_price}"><br />
		    </div>
		    <input type="submit" value="修改" onclick="my()" id="addBox" style="width: 250px">
		  </form>
	</div>
  </body>
</html>

