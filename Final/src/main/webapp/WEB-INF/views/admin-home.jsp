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
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
<h1>Welcome Admin</h1>
<a href="category/add.htm">Add Category for Products</a><br /><br />
<a href="view-seller.htm">View Sellers Details</a><br /><br />
<a href="category/delete.htm">Delete Category</a><br /><br />
</body>
</html>