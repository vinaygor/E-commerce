<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advert Created Successfully</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
	<a href="${contextPath}/seller/seller-home">Home</a><br/>
	<a href="${contextPath}/seller/product/add.htm">Add More</a><br/>

	<h2>Product has been added successfully.</h2>
</body>
</html>