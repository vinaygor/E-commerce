<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Store</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/">Home</a><br/>
	<h2>Login</h2>
	<form action="${contextPath}/user/login.htm" method="post">
	
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>
<c:if test="${errorMessage==true }">
		<p style="color:red">Username/Password combination does not match</p>
		</c:if>
	<c:if test="${autherror==true }">
		<p style="color:red">Your account is not active. Please contact admin.</p>
		</c:if>
	</form>
	

</body>
</html>

