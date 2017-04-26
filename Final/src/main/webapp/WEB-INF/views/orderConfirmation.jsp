<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Success Page</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="../resources/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="../resources/assets/css/custom.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	</head>

	<body>
	<%
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
           response.setHeader("Pragma", "no-cache"); //HTTP 1.0
           response.setDateHeader("Expires", 0); //prevents caching at the proxy server
     %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
	<form action = "${pageContext.request.contextPath}/user/order.pdf" method = "POST">
		<c:set var="grandTotal" value="${0}" />
		<nav class="navbar">
			<div class="container">
				<a class="navbar-brand" href="#">Your online store</a>
				<div class="navbar-right">
					<div class="container minicart"></div>
				</div>
			</div>
		</nav>
		
		<div class="container-fluid breadcrumbBox text-center">
			<ol class="breadcrumb">
			<a style="float:left;" href="${contextPath}/user/products.htm" class="btn btn-default btn-success">Products Home</a>
				<li class="active"><a href="#">Order Placed Successfully</a></li>
				<li class="active"><a href="#">Sales Order ID:</a></li>
				<li class="active"><a href="#">- ${requestScope.salesOrder}</a></li>
				<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
			</ol>
		</div>
		
		<div class="container text-center">

			<div class="col-md-5 col-sm-12">
				<div class="bigcart"></div>
				<h1>Your shopping cart</h1>
				<p>
					<strong>Your Products will be shipped to following address:</strong>
					<strong><br />${address.streetAddress}</strong>
					<strong><br />${address.city}, ${address.state}</strong>
					<strong><br />${address.country}, ${address.zipCode}</strong>
				</p>
			</div>
			
			<div class="col-md-7 col-sm-12 text-left">
				<ul>
					<li class="row list-inline columnCaptions">
						<span>Qty</span>
						<span>Product Name</span>
						<span>Seller Name</span>
						
					</li>
					<c:forEach var="orderList" items="${orderList}">
					<li class="row">
						<span class="quantity">${orderList.quantity}</span>
						<span class="itemName">${orderList.product.title}</span>
						<span class="price" ">${orderList.product.user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span></span>
						<c:set var="grandTotal" value="${grandTotal+(orderList.product.price*orderList.quantity)}" />
					</li>
					</c:forEach>
					<li class="row totals">
						<span class="itemName">Total:</span>
						<span class="price" id="finalprice" data-id="${grandTotal}">$${grandTotal}</span>
						<span class="order"> <input type="submit" value="View PDF Receipt" class="btn btn-default btn-success" /><span class="fa fa-shopping-cart"></span>&nbsp;</span>
					</li>
				</ul>
			</div>

		</div>

		<!-- The popover content -->

		<div id="popover" style="display: none">
			
		</div>
		
		<!-- JavaScript includes -->

		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
		<script src="../resources/assets/js/bootstrap.min.js"></script>
		<script src="../resources/assets/js/customjs.js"></script>
</form>
	</body>
</html>