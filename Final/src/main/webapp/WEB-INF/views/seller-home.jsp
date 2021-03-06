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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
<h1>Hi, ${user.name}</h1>



<c:if test="${user.role == 'Seller' }">

<a href="${contextPath}/seller/product/add.htm" >Add Products</a> <br /><br />
<a href="${contextPath}/seller/product/view.htm" >View/Update Products</a> <br /><br />
<a href="${contextPath}/seller/vieworders.htm" >View Orders Placed by Customers</a> <br /><br />
<input type="hidden" value="${user.personID}" name="personID" />

</c:if>


</body>
</html>