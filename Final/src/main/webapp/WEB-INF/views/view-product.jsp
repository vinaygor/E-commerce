<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<link rel="stylesheet" type="text/css" href="../resources/style.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>
$(function(){
    var $select = $(".1-10");
    for (i=1;i<=10;i++){
        $select.append($('<option></option>').val(i).html(i))
    }
});

//Code to add the products to cart
$(document).ready(function(){
 $('.addtocart').click(function(e){
	e.preventDefault();
	var id = $(this).parent().parent().attr('data-pid');
	var qty = $( "#select-"+id+" option:selected" ).val();
	var urlPath = 'user/addToCart.htm?id='+id+'&qty='+qty;
	//alert("Added to cart : Product Id:"+id+", Quantity:"+qty+" URL:"+urlPath);
	$.ajax({
		url:urlPath,
		type:'GET',
		success:function(){
			
		}
	
	});
	alert("Added Successfully");
	
});
});
</script>
</head>
<body>

<div id="mainbox">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="currentUser" value="${sessionScope.user}" />

<c:choose>
            <c:when test="${empty requestScope.products}">
                <h3>No Product are posted. Try again later.</h3>                
        </c:when>
        
        <c:otherwise>
             <c:forEach items="${requestScope.products}" var="product">
                                  
<div class="card" data-pid="${product.id}">

  <img src="../resources/${product.filename}" alt="image" />
  <h1>${product.title}</h1>
  <p>${product.description}. </p>
  <p>Price :$${product.price}</p>
  <p>Select Quantity: &nbsp;<select class="1-10" id="select-${product.id}"></select></p>
  <p><a href="#" class="addtocart">Add to Cart</a></p>
</div>
        </c:forEach>
        </c:otherwise>
       </c:choose>
       
</div>
<div class="center">
  <div class="pagination">
    <a href="${contextPath}/user/products.htm?side=back">&laquo;</a>
    <a href="${contextPath}/user/products.htm?side=next">&raquo;</a>
  </div>
</div>

<div><a id="cart" style="float:right;" href="viewCart.htm">Go to Cart</a></div>
</body>
</html>