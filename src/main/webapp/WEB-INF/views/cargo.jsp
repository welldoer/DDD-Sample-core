<%@ include file="/WEB-INF/views/include.jspf" %>

<html>
<head>
  <title>Cargo</title>
</head>
<body>
  <c:choose>
    <c:when test="${location ne null}">
      <p>Your cargo is currently at: <strong>${location}</strong></p>
    </c:when>
    <c:otherwise>
      <p>Unknown cargo id</p>
    </c:otherwise>
  </c:choose>

  <br/>
  <a href="start">Start page</a>

</body>
</html> 