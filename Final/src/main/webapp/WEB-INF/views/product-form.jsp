<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add new Product Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a style="float:right;" href="${contextPath}/logout.htm" class="btn btn-default btn-success">Logout</a>
	<a href="${contextPath}/seller/seller-home">Home</a><br/>

	<h2>Adding a new product</h2>


	<form:form method="post" commandName="product" action="${contextPath}/seller/product/add.htm" enctype="multipart/form-data">

		<c:out value="${product.title}"></c:out>
		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.name}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>

			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Product Title:</td>
				<td><form:input type="text" path="title" size="30" required="required"/></td>
			</tr>

			<tr>
				<td>Decription:</td>
				<td><form:input type="text" path="description" size="30" required="required"/></td>
			</tr>

			<tr>
				<td>Price:</td>
				<td><form:input type="number"  path="price" size="30" required="required"/></td>
			</tr>
			
			<tr>
			<td>Select photo: <input type="file" name="photo" required="required"/><br/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit Product" /></td>
			</tr>
		</table>
	<input type="hidden" value="${personID}" name="personID">
	</form:form>

</body>
</html>