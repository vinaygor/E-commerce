<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
<h1>Hi, ${user.name}</h1>




<a href="${contextPath}/user/products.htm" >View All Products</a> <br />
<br />

<a href="${contextPath}/user/searchproducts.htm" >Search Products</a> <br />
<br />

<a href="${contextPath}/user/vieworders.htm" >My Orders</a> <br />
<br />
</body>
</html>