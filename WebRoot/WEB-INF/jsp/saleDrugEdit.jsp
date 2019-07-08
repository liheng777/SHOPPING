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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/datepicker/WdatePicker.js"></script> 
	<script type="text/javascript">
		function myclick(){
			var customer_id = document.getElementsByName("customer_id")[0].value;
			var drug_id = document.getElementsByName("drug_id")[0].value;
			var sale_number = document.getElementsByName("sale_number")[0].value;
			var date = document.getElementsByName("date")[0].value;
			var price = document.getElementsByName("price")[0].value;
			if(customer_id==null||customer_id==""){
				alert("顾客号不能为空！");
				return false;
			}
			if(drug_id.trim().length==0){
				alert("药品编码不能为空");
				return false;
			}
			if(sale_number<=0 || sale_number>9999999){
				alert("所售数量必须大于0且小于99999999");
				return false;
			}
			if(sale_number%1!==0){
				alert("请输入正确的销售量");
				return false;
			}
			if(sale_number==""||sale_number==null || isNaN(sale_number)){
				alert("请输入正确的销售量");
				return false;
			}
			if(date==""||date==null){
				alert("销售日期不能为空");
				return false;
			}
			if(price<=0 || price>10000){
				alert("价格必须大于0且小于10000");
				return false;
			}
			
			if(price==""||price==null || isNaN(price)){
				alert("请输入正确的价格");
				return false;
			}
			return true;
		}
		
		
	</script>
  </head>
  
  <body>
   	 <c:if test="${!empty msg}">
	  	<input type="hidden" id="msg" value='${msg}'>
	  </c:if>
	  <jsp:include page="head.jsp"></jsp:include>
    
     <div id="subMenu">
     	<h1 style="color:red">售药修改页面</h1>
    	 <form action="<%=basePath%>/saleDrug/edit.do"  method="post" onsubmit="return myclick()">
     		<input type="hidden" name="id" value="${saleDrug.id}"><br>
			<div id="addBackground4">
				顾客号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="customer_id" value="${saleDrug.customer_id}"><br />
				</div>
				<div id="addBackground5">
		    	药品编码&nbsp;&nbsp;&nbsp;<input type="text" name="drug_id" value="${saleDrug.drug_id}"><br />
		    	</div>
		    	<div id="addBackground6" >
		    	销售量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="sale_number" value="${saleDrug.sale_number}"><br />
		    	</div>
		    	<div id="addBackground1" >
		    	销售日期&nbsp;&nbsp;&nbsp;<input name="date" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="${saleDrug.date}"/>
		    	</div>
		    	<div id="addBackground2" >
		    	单价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price" value="${saleDrug.price}"><br />
		    	</div>
		    	<input type="submit" value="修改" id="addBox" style="width: 240px">
		  </form>
	</div>
  </body>
</html>