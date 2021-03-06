<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>



</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Registration new user</h2>

	<form:form action="${contextPath}/user/customer_register.htm" commandName="user"
		method="post" data-toggle="validator">
		
		<table>

			<tr>
				<td>Full Name:</td>
				<td><form:input path="name" size="30" required="required" pattern="[a-zA-z ]+" placeholder="Characters only" />
					<font color="red"><form:errors path="name" /></font></td>
			</tr>
			
						
			<tr>
				<td>Role:</td>
				<td><form:input path="role" size="30" required="required" value="Customer" disabled="true" />
					<font color="red"><form:errors path="role" /></font></td>
			</tr>
			
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30" pattern="[a-zA-z0-9@.]+"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>
			
			<tr>
				<td>Card Details:</td>
				<td><form:input path="cardDetails" size="30"
						 required="required" pattern="[0-9]{16,16}"/> <font color="red"><form:errors
							path="cardDetails" /></font></td>
			</tr>
						

			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" pattern="[_A-z0-9]{6,}" />
					<font color="red"><form:errors path="username" /></font>
					<c:if test="${flag ==true}">
		<span style="color:red;">User name already exist. Please choose a different username.</span>
		</c:if>
		</td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" pattern="[A-z0-9@_!$]{6,}$"/> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Address Type:</td>
				<td><form:input path="address[0].addressType" size="30" disabled="true" value="Billing Address"/>
					<font color="red"><form:errors path="address[0].addressType" /></font></td>
			</tr>
			
						<tr>
				<td>Street Address:</td>
				<td><form:input path="address[0].streetAddress" size="30" required="required" pattern="[A-z0-9, ]{1,}"/>
					<font color="red"><form:errors path="address[0].streetAddress" /></font></td>
			</tr>
			
			<tr>
				<td>City:</td>
				<td><form:input path="address[0].city" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[0].city" /></font></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><form:input path="address[0].state" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[0].state" /></font></td>
			</tr>
			
			<tr>
				<td>Country:</td>
				<td><form:input path="address[0].country" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[0].country" /></font></td>
			</tr>
			
			<tr>
				<td>Zip code:</td>
				<td><form:input path="address[0].zipCode" size="30" required="required" pattern="[0-9]{5,5}"/>
					<font color="red"><form:errors path="address[0].zipCode" /></font></td>
			</tr>
			
			<tr>
				<td>Address Type:</td>
				<td><form:input path="address[1].addressType" size="30" disabled="true" value="Shipping Address"/>
					<font color="red"><form:errors path="address[1].addressType" /></font></td>
			</tr>
			
						<tr>
				<td>Street Address:</td>
				<td><form:input path="address[1].streetAddress" size="30" required="required" pattern="[A-z0-9, ]{1,}"/>
					<font color="red"><form:errors path="address[1].streetAddress" /></font></td>
			</tr>
			
			<tr>
				<td>City:</td>
				<td><form:input path="address[1].city" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[1].city" /></font></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><form:input path="address[1].state" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[1].state" /></font></td>
			</tr>
			
			<tr>
				<td>Country:</td>
				<td><form:input path="address[1].country" size="30" required="required" pattern="[A-z ]{1,}"/>
					<font color="red"><form:errors path="address[1].country" /></font></td>
			</tr>
			
			<tr>
				<td>Zip code:</td>
				<td><form:input path="address[1].zipCode" size="30" required="required" pattern="[0-9]{5,5}"/>
					<font color="red"><form:errors path="address[1].zipCode" /></font></td>
			</tr>
			
			
			<tr>
				<td colspan="2"><input type="submit" value="Register User" /></td>
			</tr>
		</table>
		

	</form:form>
	

</body>
</html>