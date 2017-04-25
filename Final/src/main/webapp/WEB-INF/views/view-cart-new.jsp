<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Shopping Cart</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="../resources/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="../resources/assets/css/custom.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script>
$(document).ready(function(){
	var total = $('#finalprice').data('id');
	if(total == 0)
		$('.order').fadeOut('300');
    $('.deleteFromCart').click(function(e){
        e.preventDefault();
        var answer;
        var id=$(this).parent().parent().data('id');
        var urlPath='deleteFromCart.htm?id='+id;
        var deletedValue = $(this).parent().data('id');
       	var newTotal = total-deletedValue;
       	total= newTotal;
       	var x = $('#finalprice').attr('data-id');
       	x=newTotal;
       	$('#finalprice').text('$'+newTotal);
       	if(total == 0)
       		$('.order').fadeOut('300');
        //alert(urlPath);
        $.ajax({
            url:urlPath,
            type:'GET',
            success:function(response){
                //alert(response);
                
                
                $('#'+response).fadeOut();
                
                
            }
            
        });
        
        
    });
    
});
</script>		
	</head>

	<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<form action = "${pageContext.request.contextPath}/user/checkout.htm" method = "post">
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
				<li><a href="#">Review</a></li>
				<li class="active"><a href="#">Order</a></li>
				<li><a href="#">Payment</a></li>
				<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
			</ol>
			
		</div>
		
		<div class="container text-center">

			<div class="col-md-5 col-sm-12">
				<div class="bigcart"></div>
				<h1>Your shopping cart</h1>
				<p>
					Check all the products that are added to your cart before you checkout.
				</p>
			</div>
			
			<div class="col-md-7 col-sm-12 text-left">
				<ul>
					<li class="row list-inline columnCaptions">
						<span>Qty</span>
						<span>Product Name</span>
						<span>Price</span>
					</li>
					<c:forEach var="cartList" items="${cartList}">
					<li class="row" id="${cartList.product.id}" data-id="${cartList.product.id}">
						<span class="quantity">${cartList.quantity}</span>
						<span class="itemName">${cartList.product.title}</span>
						
						<span class="price" id="price-${cartList.product.id}" data-id="${cartList.product.price}">$${cartList.product.price*cartList.quantity}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class ="deleteFromCart" href="#"><span class="glyphicon glyphicon-remove"></span></a></span>
						<c:set var="grandTotal" value="${grandTotal+(cartList.product.price*cartList.quantity)}" />
					</li>
					</c:forEach>
					<li class="row totals">
						<span class="itemName">Total:</span>
						<span class="price" id="finalprice" data-id="${grandTotal}">$${grandTotal}</span>
						<span class="order"> <input type="submit" class="btn btn-default btn-success" value="CheckOut"/><span class="fa fa-shopping-cart"></span></span>
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