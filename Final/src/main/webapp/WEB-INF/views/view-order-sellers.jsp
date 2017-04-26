<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View My Orders</title>
</head>
<body>

<a href="${contextPath}/seller/product/add.htm" >Add Products</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${contextPath}/seller/product/view.htm" >View/Update Products</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="${contextPath}/seller/vieworders.htm" >View Orders Placed by Customers</a> 
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a><br /><br />


<h2>List of Orders Placed</h2>

<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Sales Order Number</b></td>
			<td><b>Product ID</b></td>
			<td><b>Product Name</b></td>
			<td><b>Quantity</b></td>
			<td><b>Ordered By</b></td>
	
		</tr>
		<c:forEach var="order" items="${list}">
			<tr>
				<td>${order.orderid}</td>
				<td>${order.product.id}</td>
				<td>${order.product.title}</td>
				<td>${order.quantity}</td>
				<td>${order.user.name}</td>
	
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>