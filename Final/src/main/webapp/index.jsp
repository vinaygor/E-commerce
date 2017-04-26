<%@page import="org.springframework.web.context.annotation.RequestScope"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Online Store</title>
</head>
<body style="text-align:center;">
<h1>
	Welcome to Online store
</h1>
<div style="text-align:center;">

<a style="float:center" href="user/customer_register.htm">New Customer?</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a style="float:center" href="user/seller_registration.htm">New Seller?</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a style="float:center" href="user/login.htm">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a style="float:center" href="admin/login.htm">Admin Login</a>&nbsp;&nbsp;&nbsp;
</div>
<br />
<br />

<div>
<p>We are Boston based company.</p>
<iframe
  width="600"
  height="450"
  frameborder="0" style="border:0"
  src="https://www.google.com/maps/embed/v1/place?key=AIzaSyABNIqhuwDXPWQGRaTaDdnq080J-dGCxsw
    &q=Northeastern+university+boston" allowfullscreen>
</iframe>
</div>

</body>
</html>