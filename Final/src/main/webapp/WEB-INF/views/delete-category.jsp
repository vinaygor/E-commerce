<%@page import="org.hibernate.mapping.Value"%>
<%@page import="com.my.spring.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Category</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('.update').click(function(e){
		e.preventDefault();
		var answer;
		var id=$(this).parent().parent().data('id');
		var urlPath='deleteCategory.htm?title='+id;
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
<h1>Delete Category</h1>
<a href ="${contextPath}/admin/dashboard.htm">Dashboard</a>
<h3>List of Categories</h3>
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Category Name</b></td>
			<td><b>Delete</b></td>
		</tr>
		<%System.out.println("Hello"); %>
		<c:forEach var="cat" items="${category}">
			<tr data-id="${cat.title}">
				<td class="status-${cat.title}">${cat.title}</td>
				<td><button name="update" class="update">Delete status</button></td>
			</tr>
		</c:forEach>
	</table>

<hr/>


</body>
</html>