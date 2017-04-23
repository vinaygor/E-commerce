<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>
</head>
<body>
<c:set var="grandTotal" value="${0}" />
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Product Name</b></td>
			<td><b>Seller</b></td>
			<td><b>Quantity</b></td>
			<td><b>Price Per Product</b></td>
			<td><b>Total Price</b></td>
		</tr>
		<c:forEach var="cartList" items="${cartList}">
			<tr>
				<td>${cartList.product.title}</td>
				<td>${cartList.product.user.name}</td>
				<td>${cartList.quantity}</td>
				<td>${cartList.product.price}</td>
				<td>${cartList.product.price*cartList.quantity}</td>
				<c:set var="grandTotal" value="${grandTotal+(cartList.product.price*cartList.quantity)}" />
			</tr>
		</c:forEach>
		<td></td>
		<td></td>
		<td></td>
		<td><b>Grand Total</b></td>
		<td>${grandTotal}</td>
	</table>
</body>
</html>