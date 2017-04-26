<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Orders</title>
</head>
<body>
    
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
   <a href="${contextPath}/user/products.htm" >View All Products</a>&nbsp;&nbsp;&nbsp;&nbsp;

<a href="${contextPath}/user/searchproducts.htm" >Search Products</a>&nbsp;&nbsp;&nbsp;&nbsp;

<a href="${contextPath}/user/vieworders.htm" >My Orders</a> <br />
<a style="float:right;" href="${contextPath}/logout.htm">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;
<br />
    <h1>Completed Orders History</h1>
    <c:choose>
        <c:when test="${!empty hashmap}">
            <c:forEach items="${hashmap}" var="entry">
            <c:set var="total" value="${0}" />
            <c:out value="Order ID: ${entry.key}" />
            <br/>
            <table id="table" border="1" cellpadding="5">
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Seller Name</th>
                    
                
                </tr>
                <c:forEach var="order" items="${entry.value}">        
                <c:if test="${order.completed==true}">
                    <tr id="row-${order.orderid}" data-id="${order.orderid}">
                        <td>${order.product.title}</td>
                        <td>${order.quantity}</td>
                        <td><c:set var="totalPrice" value="${order.quantity * order.product.price}" />${order.quantity * order.product.price}</td>
                        <td>${order.product.user.name}</td>
                        
                        <c:set var="total" value="${total+totalPrice}" />
              
                    </tr>
                </c:if>
                </c:forEach>
            </table>
            <h3>Total Price: $<c:out value="${total}" /></h3>
            <br/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:out value="No Orders" />
        </c:otherwise>
    </c:choose>
    
    <h1>Pending Orders History</h1>
    <c:choose>
        <c:when test="${!empty pendingHashmap}">
            <c:forEach items="${pendingHashmap}" var="entry">
            <c:set var="total" value="${0}" />
            <c:out value="Order ID: ${entry.key}" />
            <br/>
            <table id="table" border="1" cellpadding="5">
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Seller Name</th>
                    
                
                </tr>
                <c:forEach var="order" items="${entry.value}">        
                <c:if test="${order.completed==false}">
                    <tr id="row-${order.orderid}" data-id="${order.orderid}">
                        <td>${order.product.title}</td>
                        <td>${order.quantity}</td>
                        <td><c:set var="totalPrice" value="${order.quantity * order.product.price}" />${order.quantity * order.product.price}</td>
                        <td>${order.product.user.name}</td>
                        
                        <c:set var="total" value="${total+totalPrice}" />
              
                    </tr>
                </c:if>
                </c:forEach>
            </table>
            <h3>Total Price: $<c:out value="${total}" /></h3>
            <br/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:out value="No Orders" />
        </c:otherwise>
    </c:choose>
   
</body>
</html>