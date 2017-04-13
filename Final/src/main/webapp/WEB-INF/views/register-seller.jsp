<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Seller Form</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Registration new seller</h2>

	<form:form action="${contextPath}/user/seller_register.htm" commandName="user"
		method="post">

		<table>

			<tr>
				<td>Company Name:</td>
				<td><form:input path="name" size="30" required="required" />
					<font color="red"><form:errors path="name" /></font></td>
			</tr>
						<tr>
				<td>Address Type:</td>
				<td><form:input path="address[0].addressType" size="30" disabled="true" value="Billing Address"/>
					<font color="red"><form:errors path="address[0].addressType" /></font></td>
			</tr>
			
						<tr>
				<td>Street Address:</td>
				<td><form:input path="address[0].streetAddress" size="30" required="required" />
					<font color="red"><form:errors path="address[0].streetAddress" /></font></td>
			</tr>
			
			<tr>
				<td>City:</td>
				<td><form:input path="address[0].city" size="30" required="required" />
					<font color="red"><form:errors path="address[0].city" /></font></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><form:input path="address[0].state" size="30" required="required" />
					<font color="red"><form:errors path="address[0].state" /></font></td>
			</tr>
			
			<tr>
				<td>Country:</td>
				<td><form:input path="address[0].country" size="30" required="required" />
					<font color="red"><form:errors path="address[0].country" /></font></td>
			</tr>
			
			<tr>
				<td>Zip code:</td>
				<td><form:input path="address[0].zipCode" size="30" required="required" />
					<font color="red"><form:errors path="address[0].zipCode" /></font></td>
			</tr>
						
			<tr>
				<td>Role:</td>
				<td><form:input path="role" size="30" required="required" value="Seller" disabled="true" />
					<font color="red"><form:errors path="role" /></font></td>
			</tr>
			
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>
			
			<tr>
				<td>Card Details:</td>
				<td><form:input path="cardDetails" size="30"
						 required="required" /> <font color="red"><form:errors
							path="cardDetails" /></font></td>
			</tr>
						

			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			

			<tr>
				<td colspan="2"><input type="submit" value="Register Seller" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>