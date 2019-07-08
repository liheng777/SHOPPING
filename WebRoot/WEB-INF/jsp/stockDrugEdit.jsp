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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/datepicker/WdatePicker.js"></script> 
	<link rel="stylesheet" href="css/list.css" />
	<script type="text/javascript">
		function myclick(){
			var bill_id = document.getElementsByName("bill_id")[0].value;
			var drug_id = document.getElementsByName("drug_id")[0].value;
			var buy_num = document.getElementsByName("buy_num")[0].value;
			var price = document.getElementsByName("price")[0].value;
			var buy_num = document.getElementsByName("buy_num")[0].value;
			var date = document.getElementsByName("date")[0].value;
			var supplier = document.getElementsByName("supplier")[0].value;
			if(bill_id.trim().length==0){
				alert("单据号不能为空");
				return false;
			}
			if(drug_id.trim().length==0){
				alert("药品编码不能为空");
				return false;
			}
			if(price==""||price==null || isNaN(price)|| price<=0 || price>99999){
				alert("请输入正确的采购价");
				return false;
			}
			if(buy_num%1!==0){
				alert("请输入正确的采购数量");
				return false;
			}
			if(buy_num==""||buy_num==null ||isNaN(buy_num)|| buy_num<=0 || buy_num>99999){
				alert("请输入正确的采购数量");
				return false;
			}
			if(date==""||date==null){
				alert("采购日期为空");
				return false;
			}
			if(supplier==""||supplier==null){
				alert("供应商不能为空");
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
     	<h1 style="color:red">进药修改页面</h1>
    	 <form action="<%=basePath%>/stockDrug/edit.do"  method="post" onsubmit="return myclick()">
     		<input type="hidden" name="id" value="${stockDrug.id}"><br>
			<div id="addBackground4">
				单据号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="bill_id" value="${stockDrug.bill_id}"><br />
			</div>
			<div id="addBackground5">
		    	药品编码&nbsp;&nbsp;&nbsp;<input type="text" name="drug_id" value="${stockDrug.drug_id}"><br />
		    </div>
		    <div id="addBackground6" >
		    	采购价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price" value="${stockDrug.price}"><br />
		    </div>
		    <div id="addBackground1" >
		    	采购数量&nbsp;&nbsp;&nbsp;<input type="text" name="buy_num" value="${stockDrug.buy_num}"><br />
		    </div>
		    <div id="addBackground2" >	
		    	采购日期&nbsp;&nbsp;&nbsp;<input  name="date" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="${stockDrug.date}"/>
		    </div>
		    	<div id="addBackground3" >
		    	供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name=supplier value="${stockDrug.supplier}"><br />
		    </div>
		    <input type="submit" value="修改" onclick="my()" id="addBox" style="width: 238px">
		  </form>
	</div>
  </body>
</html>

