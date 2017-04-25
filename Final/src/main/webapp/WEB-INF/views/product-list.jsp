<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Products</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('.delete').click(function(e){
		e.preventDefault();
		var answer;
		var id=$(this).parent().parent().data('id');
		var urlPath='deleteProduct.htm?id='+id;
		//alert(urlPath);
		$.ajax({
			url:urlPath,
			type:'GET',
			success:function(response){
				//alert(response);
				$('.status-'+response).parent().fadeOut();
				
			}
			
		});
		
		
	});
	
});
</script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
	<a href="${contextPath}/seller/seller-home">Home</a><br/>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>PRODUCT TITLE</b></td>
			<td><b>DESCRIPTION</b></td>
			<td><b>ACTION</b></td>
		</tr>
		<c:forEach var="prod" items="${productList}">
			<tr data-id="${prod.id}">
				<td class="status-${prod.id}">${prod.title}</td>
				<td>${prod.description}</td>
				<td><a href="updateProduct.htm?id=${prod.id}">Update</a></td>
				<td><button name="delete" class="delete">Delete</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>