<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>

<h1>Hi, ${user.name}</h1>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:if test="${user.role == 'Customer' }">
<a href="${contextPath}/user/products.htm" >View All Products</a> <br />
</c:if>

</body>
</html>