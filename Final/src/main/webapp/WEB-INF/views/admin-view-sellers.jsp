<%@page import="org.hibernate.mapping.Value"%>
<%@page import="com.my.spring.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller's Details</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('.update').click(function(e){
		e.preventDefault();
		var answer;
		var id=$(this).parent().parent().data('id');
		var urlPath='updateStatus.htm?id='+id;
		//alert(urlPath);
		$.ajax({
			url:urlPath,
			type:'GET',
			success:function(response){
				//alert(response);
				var a = $('.status-'+response).text();
				if(a=="true")
				{
					$('.status-'+response).text("false");
				}
				else
				{
					$('.status-'+response).text("true");
				}
				
			}
			
		});
		
		
	});
	
});
</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h1>Welcome Admin</h1>
<a href ="${contextPath}/admin/dashboard.htm">Dashboard</a>
<h3>List of Sellers</h3>
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>User Name</b></td>
			<td><b>Role</b></td>
			<td><b>Active Status</b></td>
			<td><b>Email Id</b></td>
		</tr>
		<%System.out.println("Hello"); %>
		<c:forEach var="user" items="${users}">
		<c:if test="${user.role=='Seller'}">
			<tr data-id="${user.personID}">
				<td>${user.username}</td>
				<td>${user.role}</td>
				<td class="status-${user.personID}">${user.activeStatus}</td>
				<td>${user.email.emailAddress}</td>
				<td><button name="update" class="update">Change Active status</button></td>
			</tr>
			</c:if>
		</c:forEach>
	</table>

<hr/>


</body>
</html>