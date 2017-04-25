<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<form action="${contextPath}/logout.htm" method="POST">
<input style="float:right;" type="submit" name="logout" value="Logout" />
<input type="hidden" name="logout" value="logout">
</form>
</body>
</html>