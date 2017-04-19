<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<link rel="stylesheet" type="text/css" href="../resources/style.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<script type="text/javascript" src="thumbnailviewer2.js">

/***********************************************
* Image Thumbnail Viewer II script- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* Please keep this notice intact
***********************************************/

</script>
</head>
<body>
<div id="mainbox">


<c:choose>
            <c:when test="${empty requestScope.products}">
                <h3>No Product are posted. Try again later.</h3>                
        </c:when>
        
        <c:otherwise>
             <c:forEach items="${requestScope.products}" var="product">
                                  
<div class="card">

  <img src="../resources/${product.filename}" alt="image" width= "400" heigth="300"/>
  <h1>${product.title}</h1>
  <p>${product.description}. </p>
  <p>Price :$${product.price}</p>
</div>
        </c:forEach>
        </c:otherwise>
       </c:choose>
</div>

</body>
</html>