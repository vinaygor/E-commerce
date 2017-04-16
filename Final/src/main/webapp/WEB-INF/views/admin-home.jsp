<%@page import="org.hibernate.mapping.Value"%>
<%@page import="com.my.spring.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h1>Welcome Admin</h1>

<h3>List of Customers</h3>
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>User Name</b></td>
			<td><b>Role</b></td>
			<td><b>Active Status</b></td>
			<td><b>Email Id</b></td>
		</tr>
		<%System.out.println("Hello"); %>
		<c:forEach var="user" items="${users}">
		<c:if test="${user.role=='Customer'}">
			<tr>
				<td>${user.username}</td>
				<td>${user.role}</td>
				<td>${user.activeStatus}</td>
				<td>${user.email.emailAddress}</td>
				<td><a href="${contextPath}/admin/updateStatus.htm?id=${user.personID}">Update Active/Inactive Status</a></td>
			</tr>
			</c:if>
		</c:forEach>
	</table>

<hr/>
<h3>List of Sellers</h3>
<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>User Name</b></td>
			<td><b>Role</b></td>
			<td><b>Active Status</b></td>
			<td><b>Email Id</b></td>
		</tr>
		
		<c:forEach var="user" items="${users}">
		<c:if test="${user.role=='Seller'}">
			<tr>
				<td>${user.username}</td>
				<td>${user.role}</td>
				<td>${user.activeStatus}</td>
				<td>${user.email.emailAddress}</td>
				<td><a href="${contextPath}/admin/updateStatus.htm?id=${user.personID}">Update Active/Inactive Status</a></td>
			</tr>
			</c:if>
		</c:forEach>
	</table>


</body>
</html>