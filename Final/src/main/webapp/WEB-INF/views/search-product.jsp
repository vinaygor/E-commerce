<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
<h1>Search Products Page</h1>



<a href="${contextPath}/user/products.htm" >View All Products</a>&nbsp;&nbsp;&nbsp;&nbsp;

<a href="${contextPath}/user/searchproducts.htm" >Search Products</a>&nbsp;&nbsp;&nbsp;&nbsp;

<a href="${contextPath}/user/vieworders.htm" >My Orders</a> <br />

<br />
<form>
<H3>Input your search string below:</H3>
<input type ="text" maxlength="4" name="search_box" id="search_box" placeholder="Keywords,Product Name" /> &nbsp;&nbsp;&nbsp;&nbsp;<select id="dropdown">
  <option value="asc">Low to High</option>
  <option value="desc">High to Low</option>
</select>
<br />
</form>
<br />
<br />
<div id="result">
<c:if test="${productList ne null}">
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>PRODUCT TITLE</b></td>
			<td><b>DESCRIPTION</b></td>
			<td><b>PRICE</b></td>
		</tr>
		<c:forEach var="prod" items="${productList}">
			<tr data-id="${prod.id}">
				<td class="status-${prod.id}">${prod.title}</td>
				<td>${prod.description}</td>
				<td>${prod.price}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
</div>


<script>

$("#search_box").keyup(function(){
	var value = $(this).val();
	if(true)
		{
		var urlPath="getProductList.htm?val="+value;
		$.ajax({
			url:urlPath,
			type:'GET',
			success:function(response){
				//alert(response);
				//$('#result').text(response);
				//$('#result').load(window.location.href + '#result');
				if(response=='0')
					$('#result').text("No products found");
				else
					$("#result").load(location.href + " #result>*", "");
				
			}
			
		});
		}
});
</script>

<script>
$("#dropdown").change(function () {
	var pick = $('#dropdown option:selected').val();
	//$('#result').text(pick);
	var urlPath="getProductListOrder.htm?order="+pick;
	$.ajax({
		url:urlPath,
		type:'GET',
		success:function(response){
			//alert(response);
			//$('#result').text(response);
			//$('#result').load(window.location.href + '#result');
			if(response=='0')
				$('#result').text("No products found");
			else
				$("#result").load(location.href + " #result>*", "");
			
		}
		
	});
	
});
</script>


</body>
</html>

