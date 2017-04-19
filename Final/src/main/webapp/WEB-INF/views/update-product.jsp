<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Product Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/seller/seller-home">Home</a><br/>

	<h2>Update a product</h2>


	<form:form method="post" commandName="product" action="${contextPath}/seller/product/update.htm" enctype="multipart/form-data">

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
						multiple="true" required="required" disabled="true"/></td>
			</tr>

			<tr>
				<td>Product Title:</td>
				<td><form:input type="text" path="title" size="30" required="required" value="${product.title}" disabled="true"/></td>
				
			</tr>

			<tr>
				<td>Decription:</td>
				<td><form:input type="text" path="description" size="30" required="required" value="${product.description}"/></td>
			</tr>

			<tr>
				<td>Price:</td>
				<td><form:input type="number" path="price" size="30" required="required" value="${product.price}"/></td>
			</tr>
			
			<tr>
			<td>Select photo: <input type="file" name="photo" required="required" value="E:/Semester 2/Web Tools/Project_Spring/Final/src/main/webapp/resources/${product.filename}"><br/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit Product" /></td>
			</tr>
		</table>
	<input type="hidden" value="${sessionScope.user.personID}" name="personID">
	<input type="hidden" value="${product.title}" name="title" />
	<input type="hidden" value="${product.id}" name="id" />
	</form:form>

</body>
</html>